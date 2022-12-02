package com.example.hospital_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBasr;
    int progresxs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();



        setContentView(R.layout.activity_main);


        progressBasr = findViewById(R.id.splashprogressbarid);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                dotWork();
                startapp();

            }
        });
        thread.start();
    }

    public void dotWork(){

        for(progresxs=20;progresxs<=100;progresxs+=20){
            try {
                Thread.sleep(1000);
                progressBasr.setProgress(progresxs);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
    public void startapp(){

        Intent myIntent= new Intent(this, login.class);
        startActivity(myIntent);
        finish();
    }

}