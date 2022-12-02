package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import sqlite.doctor_appointment_db_halper;
import realtimedb.showappointmentmodel;

import java.util.ArrayList;

public class show_appointment extends AppCompatActivity {

    private ListView listView;
    private sqlite.doctor_appointment_db_halper doctor_appointment_db_halper;
    private customadapter customadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appointment);
        getSupportActionBar().setTitle("Hospital Management System");

        listView = findViewById(R.id.msqllistviewid);

        doctor_appointment_db_halper = new doctor_appointment_db_halper(this);

        loaddata();
    }
    public void loaddata(){

        ArrayList<showappointmentmodel> listData = new ArrayList<>();

        Cursor cursor = doctor_appointment_db_halper.displayalldata();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data is available", Toast.LENGTH_SHORT).show();
        }
        else{

            while (cursor.moveToNext())
            {
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String date = cursor.getString(4);
                String doctor = cursor.getString(5);


                showappointmentmodel apls = new showappointmentmodel(name,date,doctor,phone);
                listData.add(apls);
            }

        }

        customadapter = new customadapter(this,listData);
        listView.setAdapter(customadapter);



    }
}