package kivaaz.com.on_demand_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseChatMessage;
import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseChatUpdater;
import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseConstants;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ListView chatList;
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

        if(isRoomIDSet){
            myRef = database.getReference(firebaseDir + "-ChatGroup");
            FirebaseListOptions<FirebaseChatMessage> options = new FirebaseListOptions.Builder<FirebaseChatMessage>()
                    .setQuery(myRef, FirebaseChatMessage.class)
                    .setLayout(R.layout.message_adapter)
                    .build();
            ListAdapter adapter = new FirebaseListAdapter<FirebaseChatMessage>(options) {
                @Override
                protected void populateView(View v, FirebaseChatMessage model, int position) {
                    TextView message = v.findViewById(R.id.message_text);
                    TextView messagesender = v.findViewById(R.id.message_user);
                    message.setText(model.getMessage());
                    message.setText(model.getMessageSender());
                }

            };
            chatList.setAdapter(adapter);
        }



        setRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRoomIDSet){
                    firebaseDir = roomIdET.getText().toString().trim();
                    roomIdET.setEnabled(false);
                    setRoomBtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_backspace));
                    isRoomIDSet = !isRoomIDSet;

                    chatService.putExtra(FirebaseConstants.FIREBASE_CHAT_GROUP_TAG,firebaseDir + "-ChatGroup");
                    startService(chatService);
                    myRef = database.getReference(firebaseDir + "-ChatGroup");
                    FirebaseListOptions<FirebaseChatMessage> options = new FirebaseListOptions.Builder<FirebaseChatMessage>()
                            .setQuery(myRef, FirebaseChatMessage.class)
                            .setLayout(R.layout.message_adapter)
                            .build();
                    ListAdapter adapter = new FirebaseListAdapter<FirebaseChatMessage>(options) {
                        @Override
                        protected void populateView(View v, FirebaseChatMessage model, int position) {
                            TextView message = v.findViewById(R.id.message_text);
                            TextView messagesender = v.findViewById(R.id.message_user);
                            message.setText("hello");
                            messagesender.setText(model.getMessageSender());
                        }

                    };
                    chatList.setAdapter(adapter);
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
                    FirebaseChatMessage chatMessage = new FirebaseChatMessage(messageET.getText().toString(),currentUser.getEmail(), new Date().getTime(),
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
            Toast.makeText(getBaseContext(),"Message: " + message,Toast.LENGTH_SHORT).show();
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
