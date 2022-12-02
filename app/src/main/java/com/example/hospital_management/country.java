package com.example.hospital_management;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import adapter.Countryadapter;
import model.allcountries.Countryinfo;


public class country extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Countryinfo> countryinfoList;
    Countryadapter countryadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("All Countries");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        recyclerView = findViewById(R.id.recyclid);
        countryinfoList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apicall();
    }

    private void apicall() {

        String URL = "https://disease.sh/v3/covid-19/countries";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);


                    for( int i = 0;i <jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String countyname= jsonObject.getString("country");

                        JSONObject jsonObject1 = jsonObject.getJSONObject("countryInfo");
                        String flag= jsonObject1.getString("flag");


                        Countryinfo countryinfo = new Countryinfo(countyname,flag);
                        countryinfoList.add(countryinfo);



                    }

                    countryadapter = new Countryadapter(country.this,countryinfoList);
                    recyclerView.setAdapter(countryadapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(country.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue =  Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}