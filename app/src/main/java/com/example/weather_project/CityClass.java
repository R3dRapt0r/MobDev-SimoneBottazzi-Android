package com.example.weather_project;

import java.io.Serializable;

public class CityClass implements Serializable {
    private String name;
    private double latitude;
    private double longitude;
    private String wth;

    //costruttore
    public CityClass(String n, double la, double lo){
        name = n;
        latitude = la;
        longitude = lo;
    }

    //metodi getter
    public String getLocationName(){
        return name;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }
}