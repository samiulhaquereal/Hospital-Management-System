package com.example.hospital_management;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Details_ac extends AppCompatActivity {
    TextView cases,todayCases,deaths,todayDeaths,recovered,active,critical,affectedCountries,cname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        cases = findViewById(R.id.id2);
        todayCases = findViewById(R.id.id4);
        deaths = findViewById(R.id.id6);
        todayDeaths = findViewById(R.id.id8);
        recovered = findViewById(R.id.id10);
        active = findViewById(R.id.id12);
        critical = findViewById(R.id.id14);
        //affectedCountries = findViewById(R.id.id16);
        cname = findViewById(R.id.cnameid);


        Intent intent = getIntent();
        String countryname = intent.getStringExtra("countryName");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Live News");

        cname.setText(countryname+" States");

        Apicall(countryname);



    }

    private void Apicall(String countryname) {

        String URL = "https://disease.sh/v3/covid-19/countries/"+countryname;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    cases.setText(jsonObject.getString("cases"));
                    todayCases.setText(jsonObject.getString("todayCases"));
                    deaths.setText(jsonObject.getString("deaths"));
                    todayDeaths.setText(jsonObject.getString("todayDeaths"));
                    recovered.setText(jsonObject.getString("recovered"));
                    active.setText(jsonObject.getString("active"));
                    critical.setText(jsonObject.getString("critical"));
                    //affectedCountries.setText(jsonObject.getString("affectedCountries"));



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Details_ac.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue =  Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}