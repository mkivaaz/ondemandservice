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
import com.google.firebase.firestore.SetOptions;

import java.util.List;

import kivaaz.com.ondemandserviceslibrary.UserData.SellerData;

/**
 * Created by Muguntan on 2/21/2018.
 */

public class FireStoreHandler {

    Context context;
    FirebaseFirestore db;
    Boolean accountExists = false;
    SellerData sellerData;
    public FireStoreHandler(Context context, FirebaseFirestore db) {
        this.context = context;
        this.db = db;
    }

    public void addNewUser(SellerData data, String type){

        db.collection(type).document(data.getEmail()).set(data,SetOptions.merge())
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

    public void checkandSetUser(final SellerData data, final String type) {
        DocumentReference user = db.collection(type).document(data.getEmail());

        user.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    sellerData = documentSnapshot.toObject(SellerData.class);
                    accountExists = true;

                }else{
                    accountExists = false;
                    addNewUser(data,type);
                }


            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("ACCOUNT EXISTS: ", e.getMessage());
            }
        });
        Log.d("ACCOUNT EXISTS: ", String.valueOf(accountExists));

    }
    public void getSingleUser(final String email,String type) {
        DocumentReference user = db.collection(type).document(email);

        user.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    SellerData sellerData = documentSnapshot.toObject(SellerData.class);
                    setSellerData(sellerData);
                }else{
                }
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("ACCOUNT EXISTS: ", e.getMessage());
            }
        });
        Log.d("ACCOUNT EXISTS: ", String.valueOf(accountExists));

    }

    public void updateUserDetails(List<String> userData) {
        String company_name = userData.get(0).toString();
        String company_address = userData.get(1).toString();
        String company_about = userData.get(2).toString();
        String company_imgURL = userData.get(3).toString();
        String ssm_regisNo = userData.get(4).toString();
        String firstName = userData.get(5).toString();
        String lastName = userData.get(6).toString();
        String email = userData.get(7).toString();
        String mobileNo = userData.get(8).toString();
        String dateOfBirth = userData.get(9).toString();
        String ic_No = userData.get(10).toString();

        DocumentReference user = db.collection("Buyer").document(email);
        user.update("company_Name", company_name,
                "company_address", company_address,
                    "company_About", company_about,
                    "company_imgURL",company_imgURL,
                    "ssm_RegisterNo", ssm_regisNo,
                    "firstName", firstName,
                    "lastName", lastName,
                    "mobileNo", mobileNo,
                    "dateOfBirth", dateOfBirth,
                    "ic_No",ic_No
        );
    }

    public SellerData getSellerData() {
        return sellerData;
    }

    public void setSellerData(SellerData sellerData) {
        this.sellerData = sellerData;
    }
}
