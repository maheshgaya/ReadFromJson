package com.maheshgaya.android.readfromjson;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Mahesh Gaya on 10/11/16.
 */
public class CityJsonReader {
    private ArrayList<City> mCityList; //list of cities
    private Context mContext; //context
    private static final String TAG = CityJsonReader.class.getSimpleName(); //for logging

    public CityJsonReader(Activity context){
        this.mContext = context;
    }

    public ArrayList<City> getCityList(){
        mCityList = new ArrayList<>(); //initializing
        String cityJson; //store the full json in memory

        //read from file and add to buffer
        try {
            //Get the asset file from assets folder
            InputStream inputStream = mContext.getAssets().open("county_ia.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();
            cityJson = new String(buffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        //read from the buffer to get the items
        //We are only querying for name, full_county_name, primary_longitude, primary_latitude
        try{
            JSONObject cityJsonObject = new JSONObject(cityJson); //gets the full json
            JSONArray iowaJsonArray = cityJsonObject.getJSONArray(Constant.JSON_IOWA); //gets the "iowa" array

            //loop through the iowaJsonArray and get each city
            for (int i = 0; i < iowaJsonArray.length(); i++){
                JSONObject cityObject = iowaJsonArray.getJSONObject(i);
                String cityName = cityObject.getString(Constant.JSON_CITY_NAME);
                String countyName = cityObject.getString(Constant.JSON_COUNTY);
                String longitude = cityObject.getString(Constant.JSON_LONGITUDE);
                String latitude = cityObject.getString(Constant.JSON_LATITUDE);

                //define the city using the model
                City city = new City(cityName,countyName, longitude, latitude);
                //add that to the list
                mCityList.add(city);
            }

        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return mCityList;
    }
}
