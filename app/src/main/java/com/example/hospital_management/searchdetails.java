package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import realtimedb.showappointmentmodel;

import java.util.ArrayList;

import realtimedb.showappointmentmodel;

public class searchdetails extends AppCompatActivity {


    private ListView listViewe;
    private SearchView searchView;
    private sqlite.add_patient_db_helper add_patient_db_helper;
    private customadapter customadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdetails);

        getSupportActionBar().setTitle("Hospital Management System");

        listViewe = findViewById(R.id.msqllistviewid2);
        searchView = findViewById(R.id.searchid2);



        add_patient_db_helper = new sqlite.add_patient_db_helper(this);

        loaddata();

    }

    public void loaddata(){

        ArrayList<showappointmentmodel> listDatas = new ArrayList<>();

        Cursor cursor = add_patient_db_helper.displayalldata();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data is available", Toast.LENGTH_SHORT).show();
        }
        else{

            while (cursor.moveToNext())
            {
                String name = cursor.getString(1);
                String phone = cursor.getString(0);
                String bed = cursor.getString(5);
                String gender = cursor.getString(3);



                showappointmentmodel apl = new showappointmentmodel(name,phone,bed,gender);
                listDatas.add(apl);
            }

        }

        customadapter = new customadapter(this,listDatas);
        listViewe.setAdapter(customadapter);
        listViewe.setTextFilterEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {




                return false;
            }
        });


    }

}