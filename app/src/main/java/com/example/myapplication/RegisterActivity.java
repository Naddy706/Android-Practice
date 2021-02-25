package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    TextView AlreadyHaveAccount;
    EditText name,email,pass,cpass;
    Button reg;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AlreadyHaveAccount = findViewById(R.id.AlreadyHaveAccount);
        name = findViewById(R.id.UserName);
        email = findViewById(R.id.Email);
        pass = findViewById(R.id.Password);
        cpass = findViewById(R.id.ConfirmPassword);
        reg = findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        ProgressDialog pd= new ProgressDialog(RegisterActivity.this);



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.setTitle("Registeration");
                pd.setMessage("Registeration is in process");
                pd.show();
                String n = name.getText().toString().trim();
                String e = email.getText().toString().toLowerCase().trim();
                String p = pass.getText().toString().trim();
                String cp= cpass.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(n.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Name is Required", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
                if(!isValidEmailId(e)){
                    Toast.makeText(RegisterActivity.this, "InValid Email Address.", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
                if(p.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Password is Required", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
                else if(p.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Password must be greater then 6", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
                else if(!p.equals(cp)){
                    Toast.makeText(RegisterActivity.this, "Password and Confirm Password are not Matched", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
                else {

                    mAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Users u = new Users();
                                u.name = n;
                                u.email = e;
                                u.pass = p;
                                u.token = FirebaseInstanceId.getInstance().getToken();
                                ref.child("Users").push().setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "User Registered Sucessfully", Toast.LENGTH_SHORT).show();
                                            pd.dismiss();
                                        }
                                        else {
                                            Toast.makeText(RegisterActivity.this, "User Registered failed", Toast.LENGTH_SHORT).show();
                                            pd.dismiss();
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d("Exception of ref user", "" + e);
                                        pd.dismiss();
                                    }
                                });

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Toast.makeText(RegisterActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                            Log.d("Exception of Auth", "" + e);
                            pd.dismiss();
                        }
                    });


                }

            }
        });








        AlreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent  i = new Intent(getApplicationContext(),LoginActivity.class);
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