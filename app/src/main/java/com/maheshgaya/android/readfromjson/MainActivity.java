package com.maheshgaya.android.readfromjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Data in JSON file obtained from http://api.sba.gov/geodata/city_links_for_state_of/ia.json
 * data.gov
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this inflates the activity_main.xml
        setContentView(R.layout.activity_main);
        //Then it dynamically adds a fragment to the container_main in the activity_main.xml
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_main, new MainFragment())
                    .commit();
        }

    }
}
