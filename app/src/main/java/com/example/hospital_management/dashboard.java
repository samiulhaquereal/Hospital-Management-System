package com.example.hospital_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {


    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Hospital Management System");


        viewPager = findViewById(R.id.pagerid);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Customada adapter = new Customada(fragmentManager);
        viewPager.setAdapter(adapter);



    }
}
class Customada extends FragmentStatePagerAdapter {


    public Customada(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if(position==0)
        {
            fragment = new tab1();
        }
        else if(position==1)
        {
            fragment = new tab2();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}