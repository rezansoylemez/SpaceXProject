package com.app.rocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    public static int rocketPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initView();
    }


    void initView(){
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(MainActivity.instance.missionName[rocketPos]);

        TextView nationality = (TextView)findViewById(R.id.nationality);
        nationality.setText(MainActivity.instance.nationality[rocketPos]);

        TextView payload_id = (TextView)findViewById(R.id.payload_id);
        payload_id.setText(MainActivity.instance.payload_id[rocketPos]);

        TextView manufacturer = (TextView)findViewById(R.id.manufacturer);
        manufacturer.setText(MainActivity.instance.manufacturer[rocketPos]);

        TextView orbit = (TextView)findViewById(R.id.orbit);
        orbit.setText(MainActivity.instance.orbit[rocketPos]);

        TextView reference_system = (TextView)findViewById(R.id.reference_system);
        reference_system.setText(MainActivity.instance.reference_system[rocketPos]);

        TextView type = (TextView)findViewById(R.id.type);
        type.setText(MainActivity.instance.rocket_type[rocketPos]);

        TextView rocket_type = (TextView)findViewById(R.id.rocket_type);
        rocket_type.setText(MainActivity.instance.rocket_name[rocketPos]);

        TextView rocket_id = (TextView)findViewById(R.id.rocket_id);
        rocket_id.setText(MainActivity.instance.rocket_id[rocketPos]);

        TextView core_serial = (TextView)findViewById(R.id.core_serial);
        core_serial.setText(MainActivity.instance.core_serial[rocketPos]);

        TextView launch_success = (TextView)findViewById(R.id.launch_success);
        if(MainActivity.instance.land_success[rocketPos] == "true"){
            launch_success.setText("Launch Success: ✓");
            //
            //launch_success.setBackgroundResource(R.drawable.ic_check);

        }else{
            launch_success.setText("Launch Success: ✘");
            //
            //launch_success.setBackgroundResource(R.drawable.ic_close);
        }
        ImageView img = (ImageView)findViewById(R.id.img);
        Picasso.get().load(MainActivity.instance.mission_patch_small[rocketPos]).into(img);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
