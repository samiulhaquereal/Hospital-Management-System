package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import sqlite.payment_db_halper;

import realtimedb.addpatientmodel;
import realtimedb.paymentmodel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class payment extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] payment={"Nagad","bkash","Upay","Rocket","SureCash","Card"};
    Spinner mySpinner;
    String spinnertype;
    EditText editText1,editText2;
    payment_db_halper payment_db_halper;
    DatabaseReference reference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().setTitle("Hospital Management System");

        payment_db_halper = new payment_db_halper(this);
        SQLiteDatabase sqLiteDatabase = payment_db_halper.getReadableDatabase();

        mySpinner=findViewById(R.id.spinner);
        editText1=findViewById(R.id.edittext1);
        editText2=findViewById(R.id.edittext2);

        ArrayAdapter myAA=new ArrayAdapter(this, android.R.layout.simple_spinner_item,payment);
        myAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAA);
        mySpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnertype=payment[i];
        //String label = mySpinner.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {



    }

    public void submit(View view) {

        String patientid = editText1.getText().toString();
        String transactionid = editText2.getText().toString();
        String label = (String) mySpinner.getSelectedItem();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Payment");


        long rowid = payment_db_halper.insertData(patientid,transactionid,label);

        if (rowid == -1) {
            Toast.makeText(this, "Unsuccessfull", Toast.LENGTH_SHORT).show();
        } else {

            paymentmodel a = new paymentmodel(patientid,transactionid,label);
            reference.child(patientid).setValue(a);
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

            editText1.setText("");
            editText2.setText("");

        }



    }

    public void cancel(View view) {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }
}