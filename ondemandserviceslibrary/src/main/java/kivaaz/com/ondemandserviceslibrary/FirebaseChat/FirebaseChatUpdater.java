package kivaaz.com.ondemandserviceslibrary.FirebaseChat;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muguntan on 1/29/2018.
 */

public class FirebaseChatUpdater extends Service{
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String senderID = currentUser.getEmail();
        String messageDir = intent.getStringExtra(FirebaseConstants.FIREBASE_CHAT_GROUP_TAG);
        Log.d("Chat Service: ", "STARTED");
        myRef = database.getReference(messageDir );
        myRef.limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<FirebaseChatMessage> messageList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FirebaseChatMessage message = snapshot.getValue(FirebaseChatMessage.class);
                    if (message.isNotified.equals("False")){
                        Intent broadCastIntent = new Intent(FirebaseConstants.FIREBASE_CHAT_INTENT);
                        broadCastIntent.putExtra(FirebaseConstants.FIREBASE_MESSAGE_TAG, message.getMessage());
                        broadCastIntent.putExtra(FirebaseConstants.FIREBASE_MESSAGE_ID_TAG, message.getMessageID());
                        boolean running = LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(broadCastIntent);
                        Log.d("FIREBASE SERVICE: ", String.valueOf(running));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Chat Service: ", "DESTROYED");
    }
}
