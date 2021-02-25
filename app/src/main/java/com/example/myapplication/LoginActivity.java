package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {


    EditText email,pass;
    TextView TextSignUp;
    Button BtnLogin;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.Email);
        pass = findViewById(R.id.Password);
        TextSignUp = findViewById(R.id.TextSignUp);
        BtnLogin = findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        ProgressDialog pd= new ProgressDialog(LoginActivity.this);








        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
pd.setTitle("Signing In");
pd.setMessage("Checking Credentials...");
pd.setCancelable(false);
pd.show();
                String e = email.getText().toString().toLowerCase().trim();
                String p = pass.getText().toString().trim();

                if (!isValidEmailId(e)) {
                    Toast.makeText(LoginActivity.this, "InValid Email Address.", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
                if (p.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password is Required", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
                else {
                    mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                ref.child("Users").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        for (DataSnapshot ds:snapshot.getChildren()) {

                                            if(e.equals(ds.getValue(Users.class).getEmail())){
                                                Users u = new Users();

                                                u.email = ds.getValue(Users.class).getEmail();
                                                u.name = ds.getValue(Users.class).getName();
                                                u.pass = ds.getValue(Users.class).getPass();
                                                u.token = FirebaseInstanceId.getInstance().getToken();
                                            ref.child("Users").child(ds.getKey()).setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if(task.isSuccessful()){
                                                        pd.dismiss();
                                                        Log.d("Login ","logged In and Token refreshed");
                                                    }else{
                                                        pd.dismiss();
                                                        Log.d("Login ","logged In and Token refresh failed");
                                                    }

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.d("Login fail token refresh ",""+e);
                                                    pd.dismiss();
                                                }

                                            });
                                            }

                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                        pd.dismiss();
                                    }
                                });

                                pd.dismiss();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();

                            } else {
                                pd.dismiss();
                                Toast.makeText(LoginActivity.this, "email or password is Invalid ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("Exception of Auth user Login", "" + e);
                            pd.dismiss();
                        }
                    });
                }

            }
        });






        TextSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);

            }
        });

    }


    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"+ "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

}