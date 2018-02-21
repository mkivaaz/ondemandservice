package kivaaz.com.ondemandserviceslibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by Muguntan on 2/21/2018.
 */

public class FireStoreHandler {

    Context context;
    FirebaseFirestore db;

    public FireStoreHandler(Context context, FirebaseFirestore db) {
        this.context = context;
        this.db = db;
    }

    public void addNewUser(String email, String name, String mobileNo, String address){
        UserData userData = new UserData();
        userData.setFirstName(name);
        userData.setEmail(email);
        userData.setCompany_address(address);
        userData.setMobileNo(mobileNo);

        db.collection("Buyer").document(email).set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "User Registered",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "ERROR" +e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });
    }

    public void ReadSingleUser(String email) {
        DocumentReference user = db.collection("Buyer").document(email);
        user.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserData userData = documentSnapshot.toObject(UserData.class);

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}
