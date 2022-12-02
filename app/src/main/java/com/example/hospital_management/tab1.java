package com.example.hospital_management;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import sqlite.add_patient_db_helper;
import sqlite.doctor_appointment_db_halper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import realtimedb.Usermodel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class tab1 extends Fragment implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userid;

    private Button btn1,btn2,btn3,btn4,btn5,btn6;
    private TextView textView1,textView2,textView3,textView4;
    sqlite.add_patient_db_helper add_patient_db_helper;
    sqlite.doctor_appointment_db_halper doctor_appointment_db_halper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab1,container,false);


        add_patient_db_helper = new sqlite.add_patient_db_helper(getContext());
        SQLiteDatabase sqLiteDatabase = add_patient_db_helper.getReadableDatabase();

        doctor_appointment_db_halper = new sqlite.doctor_appointment_db_halper(getContext());
        SQLiteDatabase sqLiteDatabase2 = doctor_appointment_db_halper.getReadableDatabase();

        firebaseAuth = FirebaseAuth.getInstance();



        textView1 = view.findViewById(R.id.textview1);
        textView2 = view.findViewById(R.id.textview2);
        textView3 = view.findViewById(R.id.textview3);
        textView4 = view.findViewById(R.id.textview4);


        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);

        patient();
        bed();
        doctor();


        // Inflate the layout for this fragment
        return view;




    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.btn1)
        {
            Intent intent = new Intent(getContext(), add_patient.class);
            startActivity(intent);

        }
        else if (view.getId()==R.id.btn2)
        {
            Intent intent = new Intent(getContext(), rp.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.btn3)
        {
            Intent intent = new Intent(getContext(), appointment.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.btn4)
        {
            Intent intent = new Intent(getContext(), show_appointment.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.btn5)
        {
            Intent intent = new Intent(getContext(), searchdetails.class);
            startActivity(intent);
        }
        else if (view.getId()==R.id.btn6)
        {
            Intent intent = new Intent(getContext(), payment.class);
            startActivity(intent);
        }

    }
    public void patient(){


        Cursor cursor = add_patient_db_helper.displaypa();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(getContext(), "No Data is available", Toast.LENGTH_SHORT).show();
        }
        else{

            while (cursor.moveToNext())
            {
                textView2.setText("Total Patient : "+(cursor.getString(0)));
            }

        }


    }
    public void bed(){


        Cursor cursor = add_patient_db_helper.displaybed();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(getContext(), "No Data is available", Toast.LENGTH_SHORT).show();
        }
        else{

            while (cursor.moveToNext())
            {
                textView3.setText("Total Bed : "+(cursor.getString(0)));
            }

        }


    }
    public void doctor(){


        Cursor cursor = doctor_appointment_db_halper.displaydoc();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(getContext(), "No Data is available", Toast.LENGTH_SHORT).show();
        }
        else{

            while (cursor.moveToNext())
            {
                textView4.setText("Total Doctor : "+(cursor.getString(0)));
            }

        }


    }
    @Override
    public void onStart(){

        super.onStart();

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();

        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usermodel usermodel = snapshot.getValue(Usermodel.class);

                if(usermodel != null){
                    String name=usermodel.getName();

                    textView1.setText("Name : "+name);
                }
                else{
                    startActivity(new Intent(getContext(),login.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.menu,menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.settingsid){

            Toast.makeText(getContext(), "Coming Soon..", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId()==R.id.shareid){

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String subject = "B.M.Samiul Haque Real";
            String body = "Visit my website : https://sites.google.com/diu.edu.bd/samiulhaquereal";

            intent.putExtra(Intent.EXTRA_SUBJECT,subject);
            intent.putExtra(Intent.EXTRA_TEXT,body);

            startActivity(Intent.createChooser(intent," share with "));


            return true;
        }
        else if(item.getItemId()==R.id.logoutid){

            firebaseAuth.signOut();
            startActivity(new Intent(getContext(),login.class));
            getActivity().finish();
            return true;
        }


        else if(item.getItemId()==R.id.aboutid){
            Toast.makeText(getContext(), "Developer : B.M.Samiul Haque Real \n Version : 1.0", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}