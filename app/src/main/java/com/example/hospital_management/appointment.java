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
import sqlite.doctor_appointment_db_halper;

import realtimedb.addpatientmodel;
import realtimedb.appointmentmodel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class appointment extends AppCompatActivity implements  View.OnClickListener{

    private EditText name , phone , gender,docname;
    private Button save , cancel,pickdate;
    private TextView date;
    String datet;
    DatePickerDialog datePickerdialog;
    sqlite.doctor_appointment_db_halper doctor_appointment_db_halper;
    RadioGroup myq2RadioGroup;
    RadioButton radioButton;
    DatabaseReference reference;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        getSupportActionBar().setTitle("Hospital Management System");
        doctor_appointment_db_halper = new doctor_appointment_db_halper(this);
        SQLiteDatabase sqLiteDatabase = doctor_appointment_db_halper.getReadableDatabase();

        name = findViewById(R.id.edit1);
        phone = findViewById(R.id.edit3);
        date = findViewById(R.id.dateid);
        docname = findViewById(R.id.edit6);
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
        String phonet = phone.getText().toString();
        String userGender = radioButton.getText().toString();
        String docnam = docname.getText().toString();


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
            reference = database.getReference("Appointment");
            String dateet = datet;
            long rowid = doctor_appointment_db_halper.insertData(namet,phonet,userGender,dateet,docnam);

            if (rowid == -1) {
                Toast.makeText(this, "Unsuccessfull", Toast.LENGTH_SHORT).show();
            } else {
                appointmentmodel a = new appointmentmodel(namet,phonet,userGender,dateet,docnam);
                reference.child(phonet).setValue(a);
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

                name.setText("");
                phone.setText("");
                date.setText("Date");
                docname.setText("");

            }

        }
        else if(view.getId()==R.id.cancel)
        {
            Intent intent = new Intent(appointment.this, dashboard.class);
            startActivity(intent);
        }

    }
}