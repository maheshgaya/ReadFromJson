package com.maheshgaya.android.readfromjson;

/**
 * Created by Mahesh Gaya on 10/11/16.
 * City Model
 * This is a Getter and Setter class for City model
 */

public class City {
    private String county; //full_county_name
    private String cityName; //name
    private String longitude; //primary_longitude
    private String latitude; //primary_latitude

    public City(String cityName, String county, String longitude, String latitude){
        this.cityName = cityName;
        this.county = county;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
