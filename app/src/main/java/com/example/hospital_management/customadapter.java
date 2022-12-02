package com.example.hospital_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import realtimedb.showappointmentmodel;

public class customadapter extends BaseAdapter {



    Context context;
    private LayoutInflater inflater;

    ArrayList<showappointmentmodel> arrayList;


    public customadapter(Context context, ArrayList<showappointmentmodel> arrayList){
        this.context=context;
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mysql_listview_semple,null);
        }
        TextView date = view.findViewById(R.id.dateid);
        TextView namet = view.findViewById(R.id.nameid);
        TextView doctorname = view.findViewById(R.id.docid);
        TextView phone = view.findViewById(R.id.phoneid);

        showappointmentmodel a = arrayList.get(i);

        namet.setText(a.getName());
        date.setText(a.getDate());
        doctorname.setText(a.getDoctorname());
        phone.setText(a.getPhone());
        return view;
    }


}
