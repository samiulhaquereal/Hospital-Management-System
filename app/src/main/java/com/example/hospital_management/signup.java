package com.example.hospital_management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import realtimedb.Usermodel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hospital_management.databinding.ActivityMainBinding;
import com.example.hospital_management.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity implements View.OnClickListener {

    ActivitySignupBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference reference;
    FirebaseDatabase database;

    private EditText namesignup , emailt , passwordt , numbert ;
    private Button signup , loginsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();




        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        progressDialog = new ProgressDialog(this);


        namesignup = findViewById(R.id.nameEdiTTextIdsignup);
        emailt = findViewById(R.id.emailEdiTTextIdsignup);
        passwordt = findViewById(R.id.passwordEdiTTextIdsignup);
        numbert = findViewById(R.id.phoneEdiTTextIdsignup);

        signup = findViewById(R.id.submitsignupbtn);
        loginsignup = findViewById(R.id.loginbtnsignup);

        signup.setOnClickListener(this);
        loginsignup.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String name = namesignup.getText().toString();
        String email = emailt.getText().toString();
        String phone = numbert.getText().toString();
        String password = passwordt.getText().toString();


        if(view.getId()==R.id.submitsignupbtn)
        {
            progressDialog.setTitle("Loading..");
            progressDialog.show();
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("Users");

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    startActivity(new Intent(signup.this,login.class));
                    progressDialog.cancel();


                    firebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getUid()).set(new Usermodel(name,phone,email));
                    Usermodel usermodel = new Usermodel(name,phone,email);
                    reference.child(FirebaseAuth.getInstance().getUid()).setValue(usermodel);

                    Toast.makeText(signup.this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();

                }
            });


        }
        else if(view.getId()==R.id.loginbtnsignup)
        {
            Intent intent = new Intent(signup.this, login.class);
            startActivity(intent);
        }


    }
}