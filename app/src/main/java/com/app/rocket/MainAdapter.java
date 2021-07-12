package com.app.rocket;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainAdapter extends BaseAdapter {
    //
    private final String[] color = {"#3D4246"};
    Context context;
    String flight_number[];
    String mission_name[];
    String years[];
    String smallIcon[];
    LayoutInflater inflter;

    public MainAdapter(Context applicationContext, String[] flight_number, String[] mission_name,String[] year,String[] smallIcon) {
        this.context = context;
        this.flight_number = flight_number;
        this.mission_name = mission_name;
        this.years = year;
        this.smallIcon = smallIcon;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flight_number.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //
        viewGroup.setBackgroundColor(Color.parseColor("#3D4246"));
        view = inflter.inflate(R.layout.main_flight, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView year = (TextView) view.findViewById(R.id.year);
        TextView count = (TextView) view.findViewById(R.id.count);
        ImageView img = (ImageView)view.findViewById(R.id.img);
        if(smallIcon[i] != null){
            Picasso.get().load(smallIcon[i]).into(img);
        }
        name.setText(mission_name[i]);
        year.setText(years[i]);
        count.setText(flight_number[i]);
        return view;
    }

}