package com.example.hospital_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospital_management.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener{


    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    EditText usernameEditText, passwordEdiTTextId;
    Button loginbtn, signupbtn;
    TextView reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        usernameEditText = findViewById(R.id.usernameEdiTTextId);
        passwordEdiTTextId = findViewById(R.id.passwordEdiTTextId);

        reset = findViewById(R.id.forgetpassword);

        loginbtn = findViewById(R.id.loginbtn);
        signupbtn = findViewById(R.id.signupbtn);

        loginbtn.setOnClickListener(this);
        signupbtn.setOnClickListener(this);
        reset.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        String username = binding.usernameEdiTTextId.getText().toString().trim();
        String pass = binding.passwordEdiTTextId.getText().toString().trim();


        if (view.getId() == R.id.loginbtn) {

            if(!username.isEmpty() && !pass.isEmpty()){
                progressDialog.setTitle("Loading..");

                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(username,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.cancel();
                        Toast.makeText(login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this,dashboard.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.cancel();

                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
            else {
                Toast.makeText(this, "Enter email & password", Toast.LENGTH_SHORT).show();
            }









        }
        else if (view.getId() == R.id.signupbtn) {
            startActivity(new Intent(login.this,signup.class));
        }

        else if (view.getId() == R.id.forgetpassword) {


            if(!username.isEmpty()){

                String email = binding.usernameEdiTTextId.getText().toString();
                progressDialog.setTitle("Sending Mail");
                progressDialog.show();

                firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.cancel();


                        Toast.makeText(login.this, "Email Send", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.cancel();

                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
            else{
                Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
            }


        }


    }
}