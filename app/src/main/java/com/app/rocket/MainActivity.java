package com.app.rocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    public String[] flight_number;
    public String[] missionName;
    public String[] year;
    public String[] smallIcon;
    ///details
    public String[] rocket_id;
    public String[] rocket_name;
    public String[] rocket_type;
    public String[] core_serial;
    public String[] land_success;
    //
    public String[] payload_id;
    public String[] nationality;
    public String[] manufacturer;
    public String[] orbit;
    public String[] reference_system;
    public String[] mission_patch;
    public String[] mission_patch_small;
    public String[] details;
    ///details
    String url = "https://api.spacexdata.com/v2/launches/";
    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instance = this;
        listView = (ListView)findViewById(R.id.listView);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                DetailActivity.rocketPos = position;
                startActivity(new Intent(MainActivity.this,DetailActivity.class));
            }
        });
        //true=reverse
        getDataFromSpaceX(true);
    }
    void getDataFromSpaceX(final boolean reverse){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String data) {
                try {
                    JSONArray arr1= new JSONArray(data);
                    flight_number = new String[data.length()];
                    missionName = new String[data.length()];
                    year = new String[data.length()];
                    smallIcon = new String[data.length()];
                    // start
                    rocket_id = new String[data.length()];
                    nationality = new String[data.length()];
                    payload_id = new String[data.length()];
                    manufacturer = new String[data.length()];
                    orbit = new String[data.length()];
                    reference_system = new String[data.length()];
                    rocket_name = new String[data.length()];
                    rocket_type = new String[data.length()];
                    core_serial = new String[data.length()];
                    land_success= new String[data.length()];
                    mission_patch = new String[data.length()];
                    mission_patch_small = new String[data.length()];
                    details = new String[data.length()];
                    //endd
                    if(reverse) {
                        JSONArray jsonArray = new JSONArray(data);
                        List list = new ArrayList();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            list.add(jsonArray.getJSONObject(i));
                        }
                        Collections.reverse(list);
                        JSONArray sortedJsonArray = new JSONArray(list);
                        listJsonData(sortedJsonArray);
                    }else{
                        listJsonData(arr1);
                    }
                    MainAdapter adapter = new MainAdapter(MainActivity.this,flight_number,missionName,year,smallIcon);
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
        queue.add(request);
    }

    void listJsonData(JSONArray array) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            flight_number[i] = obj.getString("flight_number");
            missionName[i] = obj.getString("mission_name");
            year[i] = obj.getString("launch_year");
            smallIcon[i] = obj.getJSONObject("links").getString("mission_patch_small");
            //details data.
            rocket_id[i] = obj.getJSONObject("rocket").getString("rocket_id");
            rocket_name[i] = obj.getJSONObject("rocket").getString("rocket_name");
            payload_id[i] =obj.getJSONObject("rocket").getJSONObject("second_stage").getJSONArray("payloads").getJSONObject(0).getString("payload_id");
            manufacturer[i]=obj.getJSONObject("rocket").getJSONObject("second_stage").getJSONArray("payloads").getJSONObject(0).getString("manufacturer");
            orbit[i]=obj.getJSONObject("rocket").getJSONObject("second_stage").getJSONArray("payloads").getJSONObject(0).getString("orbit");
            nationality[i] = obj.getJSONObject("rocket").getJSONObject("second_stage").getJSONArray("payloads").getJSONObject(0).getString("nationality");
            manufacturer[i]=obj.getJSONObject("rocket").getJSONObject("second_stage").getJSONArray("payloads").getJSONObject(0).getString("manufacturer");
            reference_system[i]=obj.getJSONObject("rocket").getJSONObject("second_stage").getJSONArray("payloads").getJSONObject(0).getJSONObject("orbit_params").getString("reference_system");
            rocket_type[i] = obj.getJSONObject("rocket").getString("rocket_type");
            core_serial[i] = obj.getJSONObject("rocket").getJSONObject("first_stage").getJSONArray("cores").getJSONObject(0).getString("core_serial");
            land_success[i] = obj.getString("launch_success");
            mission_patch_small[i] = obj.getJSONObject("links").getString("mission_patch_small");
            mission_patch[i] = obj.getJSONObject("links").getString("mission_patch");
        }
    }


}
