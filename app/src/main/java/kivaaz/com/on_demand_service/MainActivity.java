package kivaaz.com.on_demand_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseChatMessage;
import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseChatUpdater;
import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseConstants;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    RecyclerView chatList;
    EditText messageET, roomIdET;
    ImageButton sendBtn, setRoomBtn;
    FirebaseDatabase database;
    DatabaseReference myRef;
    boolean isRoomIDSet = false;
    Intent chatService;
    String firebaseDir = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        chatList = findViewById(R.id.chatList);
        messageET = findViewById(R.id.chatMessage);
        roomIdET = findViewById(R.id.roomIdET);
        sendBtn = findViewById(R.id.sendBtn);
        setRoomBtn = findViewById(R.id.setRoomBtn);
        chatService = new Intent(getBaseContext(), FirebaseChatUpdater.class);
        setRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRoomIDSet){
                    firebaseDir = roomIdET.getText().toString().trim();
                    roomIdET.setEnabled(false);
                    setRoomBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_cancel));
                    isRoomIDSet = !isRoomIDSet;

                    chatService.putExtra(FirebaseConstants.FIREBASE_CHAT_GROUP_TAG,firebaseDir + "-ChatGroup");
                    startService(chatService);
                    myRef = database.getReference(firebaseDir + "-ChatGroup");
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            List<FirebaseChatMessage> messageList = new ArrayList<>();
                            int position = 0;
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                FirebaseChatMessage message = snapshot.getValue(FirebaseChatMessage.class);
                                position++;
                                messageList.add(message);
                                Log.d("FIREBASEMESSAGE: ",message.getMessage());
                            }
                            if(messageList.size() != 0){
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
                                ChatListAdapter adapter = new ChatListAdapter(getBaseContext(),messageList,currentUser.getEmail());
                                chatList.setLayoutManager(layoutManager);
                                chatList.setAdapter(adapter);
                                chatList.smoothScrollToPosition(position);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }else {
                    roomIdET.setText("");
                    roomIdET.setEnabled(true);
                    setRoomBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle));
                    isRoomIDSet = !isRoomIDSet;
                    stopService(chatService);
                    chatList.setAdapter(null);
                }
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRoomIDSet){
                    myRef = database.getReference(firebaseDir + "-ChatGroup");
                    FirebaseChatMessage chatMessage = new FirebaseChatMessage(messageET.getText().toString(),mAuth.getCurrentUser().getEmail(), new Date().getTime(),
                            "False");
                    String pushKey = myRef.push().getKey();
                    chatMessage.setMessageID(pushKey);
                    myRef.child(pushKey).setValue(chatMessage);

                }else {
                    Toast.makeText(getBaseContext(), "Please Enter Room ID",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(FirebaseConstants.FIREBASE_MESSAGE_TAG);
            String messageId = intent.getStringExtra(FirebaseConstants.FIREBASE_MESSAGE_ID_TAG);
            database.getReference(firebaseDir + "-ChatGroup/" + messageId + "/isNotified").setValue("True");
            Toast.makeText(getBaseContext(),"There's a New Message",Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(receiver, new IntentFilter(FirebaseConstants.FIREBASE_CHAT_INTENT));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(chatService);
    }
}
