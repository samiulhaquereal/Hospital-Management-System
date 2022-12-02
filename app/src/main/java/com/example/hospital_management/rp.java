package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import sqlite.add_patient_db_helper;

public class rp extends AppCompatActivity implements View.OnClickListener {


    private EditText patient_id;
    private Button saver , cancelr;

    sqlite.add_patient_db_helper add_patient_db_helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rp);

        getSupportActionBar().setTitle("Hospital Management System");

        add_patient_db_helper = new sqlite.add_patient_db_helper(this);
        SQLiteDatabase sqLiteDatabase = add_patient_db_helper.getReadableDatabase();


        patient_id = findViewById(R.id.edit1id);

        saver = findViewById(R.id.submit1);
        cancelr = findViewById(R.id.cancel1);

        saver.setOnClickListener(this);
        cancelr.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        String id = patient_id.getText().toString();

        if(view.getId()==R.id.submit1)
        {
          int isdelete =  add_patient_db_helper.delete_data(id);
          if(isdelete>0)
            {

             Toast.makeText(this, "Delete Successfull", Toast.LENGTH_SHORT).show();
              patient_id.setText("");
            }
          else{
               Toast.makeText(this, "Delete Not Successfull", Toast.LENGTH_SHORT).show();
           }

         }
         else if(view.getId()==R.id.cancel1) {
            Intent intent = new Intent(this, dashboard.class);
            startActivity(intent);
        }

    }
}