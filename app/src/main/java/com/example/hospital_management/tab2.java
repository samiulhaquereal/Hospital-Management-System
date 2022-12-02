package com.example.hospital_management;

import android.content.Intent;
import android.os.Bundle;

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

import model.GlobalResponse;
import network.ApiClientPrivate;
import network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class tab2 extends Fragment implements View.OnClickListener {
    TextView cases,deaths,recovered,active,affectedCountries;
    Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2,container,false);


        cases =(TextView)view.findViewById(R.id.id2);
        deaths = (TextView)view.findViewById(R.id.id6);
        recovered = (TextView)view.findViewById(R.id.id10);
        active =(TextView)view.findViewById(R.id.id12);
        affectedCountries = (TextView)view.findViewById(R.id.id16);

        btn = (Button)view.findViewById(R.id.btn1);
        btn.setOnClickListener(this);

        apicall();

        return view;
    }

    private void apicall() {

        ApiInterface apiInterface;
        apiInterface = ApiClientPrivate.getApiClient().create(ApiInterface.class);

        Call<GlobalResponse> call = apiInterface.globalresponse();

        call.enqueue(new Callback<GlobalResponse>() {
            @Override
            public void onResponse(Call<GlobalResponse> call, Response<GlobalResponse> response) {

                if(response.isSuccessful()){

                    String stcase = String.valueOf(response.body().getCases());
                    String stdeaths = String.valueOf(response.body().getDeaths());
                    String strecovered = String.valueOf(response.body().getRecovered());
                    String stactive = String.valueOf(response.body().getActive());
                    String staffectedCountries = String.valueOf(response.body().getAffectedCountries());



                    cases.setText(stcase);
                    deaths.setText(stdeaths);
                    recovered.setText(strecovered);
                    active.setText(stactive);
                    affectedCountries.setText(staffectedCountries);

                }

            }

            @Override
            public void onFailure(Call<GlobalResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent (getContext(),country.class));

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

            //this.finish();

            return true;
        }


        else if(item.getItemId()==R.id.aboutid){
            Toast.makeText(getContext(), "Developer : B.M.Samiul Haque Real \n Version : 1.0", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}