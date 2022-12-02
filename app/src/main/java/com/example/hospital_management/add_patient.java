package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import realtimedb.addpatientmodel;
import sqlite.add_patient_db_helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_patient extends AppCompatActivity implements View.OnClickListener {
    private EditText name , address , phone ,bed ;
    private Button save , cancel , pickdate;
    private TextView date;
    String datet;
    DatePickerDialog datePickerdialog;
    sqlite.add_patient_db_helper add_patient_db_helper;
    RadioGroup myq2RadioGroup;
    RadioButton radioButton;
    DatabaseReference reference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        getSupportActionBar().setTitle("Hospital Management System");

        add_patient_db_helper = new add_patient_db_helper(this);
        SQLiteDatabase sqLiteDatabase = add_patient_db_helper.getReadableDatabase();

        name = findViewById(R.id.edit1);
        address = findViewById(R.id.edit2);
        phone = findViewById(R.id.edit3);
        date = findViewById(R.id.dateid);
        bed = findViewById(R.id.edit6);
        myq2RadioGroup = findViewById(R.id.q1radiogrpId);

        save = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);
        pickdate = findViewById(R.id.pickid);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        pickdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        
        int userGenderid=myq2RadioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(userGenderid);

        DatePicker datePicker = new DatePicker(this);
        int currentDay = datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getDayOfMonth())+1;
        int currentYear = datePicker.getYear();

        String namet = name.getText().toString();
        String addresst = address.getText().toString();
        String phonet = phone.getText().toString();
        String bedt = bed.getText().toString();
        String userGender = radioButton.getText().toString();



        if(view.getId()==R.id.pickid){


            datePickerdialog = new DatePickerDialog(this,

                    new DatePickerDialog.OnDateSetListener(){

                        @Override
                        public void onDateSet(DatePicker view, int year, int month,int dayofMonth) {

                            date.setText(dayofMonth+"/"+(month+1)+"/"+year);
                            datet = dayofMonth+"/"+(month+1)+"/"+year;
                        }

                    },currentYear , currentMonth,currentDay);

                    datePickerdialog.show();


        }



        else if(view.getId()==R.id.submit)
        {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("Add_Patients");
            String dateet = datet;
            long rowid = add_patient_db_helper.insertData(namet,addresst,phonet,userGender,dateet,bedt);


            if (rowid == -1) {
                Toast.makeText(this, "Unsuccessfull", Toast.LENGTH_SHORT).show();
            } else {


                addpatientmodel a = new addpatientmodel(namet,addresst,phonet,userGender,dateet,bedt);
                reference.child(phonet).setValue(a);

                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

                name.setText("");
                address.setText("");
                phone.setText("");
                date.setText("Date");
                bed.setText("");

            }

        }
        else if(view.getId()==R.id.cancel)
        {
            Intent intent = new Intent(add_patient.this, dashboard.class);
            startActivity(intent);
        }


    }
}