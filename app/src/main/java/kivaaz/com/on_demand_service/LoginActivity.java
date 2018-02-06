package kivaaz.com.on_demand_service;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailET, passwordET;
    CheckBox signupCb, loginCb;
    Button signupBtn, loginBtn,scanBtn,genBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passET);

        signupCb = findViewById(R.id.signupCB);
        loginCb = findViewById(R.id.loginCB);

        signupBtn = findViewById(R.id.signupBTN);
        loginBtn = findViewById(R.id.goBtn);
        scanBtn= findViewById(R.id.qrScannerBtn);
        genBtn= findViewById(R.id.qrGenerateBtn);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),BarcodeScanner.class));
            }
        });
        genBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),QrGeneratorActivity.class));
            }
        });

        signupCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    loginCb.setChecked(false);
                    signupBtn.setVisibility(View.VISIBLE);
                    loginBtn.setVisibility(View.GONE);
                }
            }
        });
        loginCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    signupCb.setChecked(false);
                    signupBtn.setVisibility(View.GONE);
                    loginBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString().trim();
                final String password = passwordET.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString().trim();
                final String password = passwordET.getText().toString().trim();
                loginUser(email,password);
            }
        });
    }

    public void loginUser(String email, final String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = mAuth.getCurrentUser();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
