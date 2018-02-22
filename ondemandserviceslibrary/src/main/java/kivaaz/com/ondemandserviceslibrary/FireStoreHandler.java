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

    public void addNewUser(SellerData data, String type, OnSuccessListener onSuccessListener, OnFailureListener onFailureListener){

        db.collection(type).document(data.getEmail()).set(data,SetOptions.merge())
                .addOnSuccessListener(onSuccessListener)
                .addOnFailureListener(onFailureListener);


    }

    public void checkandSetUser(final SellerData data, final String type,final OnSuccessListener<DocumentSnapshot> checkSuccessListener, final OnSuccessListener successListener, final OnFailureListener failureListener) {
        DocumentReference user = db.collection(type).document(data.getEmail());

        user.get().addOnSuccessListener(checkSuccessListener)
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("ACCOUNT EXISTS: ", e.getMessage());
            }
        });

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

    public void updateUserDetails(List<String> userData, String type) {
        String company_name = ((String)userData.get(0)).toString();
        String companyaddress = ((String)userData.get(1)).toString();
        String company_about = ((String)userData.get(2)).toString();
        String company_imgURL = ((String)userData.get(3));
        String ssm_regisNo = ((String)userData.get(4)).toString();
        String firstName = ((String)userData.get(5)).toString();
        String lastName = ((String)userData.get(6)).toString();
        String email2 = ((String)userData.get(7)).toString();
        String mobileNo = ((String)userData.get(8)).toString();
        String dateOfBirth = ((String)userData.get(9)).toString();
        String ic_No = ((String)userData.get(10)).toString();
        String gst_No = ((String)userData.get(11)).toString();
        DocumentReference user = db.collection(type).document(email2);
        user.update("company_Name", company_name, "company_address", companyaddress, "company_About", company_about, "company_imgURL", company_imgURL, "ssm_RegisterNo", ssm_regisNo,"gstNo", gst_No, "firstName", firstName, "lastName", lastName, "mobileNo", mobileNo, "dateOfBirth", dateOfBirth, "ic_No", ic_No);
        Toast.makeText(context,"Info Saved Successfully",Toast.LENGTH_SHORT).show();

    }

    public SellerData getSellerData() {
        return sellerData;
    }

    public void setSellerData(SellerData sellerData) {
        this.sellerData = sellerData;
    }
}
