package com.example.weather_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import java.io.IOException;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class cityList extends AppCompatActivity {
    private static final String TAG = "cityList";
    private ListView cListView;

    Geocoder geocoder;
    ArrayList<CityClass> cList;
    ArrayList<String> cListNames;
    EditText editText;
    SharedPreferences sp;

    //prendo l indice, cerco la citta corr dalla lista, me la savlo come stringa, la passo nell intent con il bundle
    //nel main faccio il geocoding
    //oppure faccio il geocoding direttamente 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        sp = this.getSharedPreferences("com.example.weather_project", Context.MODE_PRIVATE);

        editText = findViewById(R.id.cityText);
        cListView = findViewById(R.id.cityList);
        Button currentLoc = findViewById(R.id.currentLocBtn);
        editText.setText("");

        cList = new ArrayList<>();
        cListNames = new ArrayList<>();

        //recupero la lista dalle shared preferences deseriallizzandola
        try {
            cList = (ArrayList<CityClass>) ObjectSerializer.deserialize(sp.getString("cList", ObjectSerializer.serialize(new ArrayList<String>())));
            setcListView(cList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        editText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    String cityName = editText.getText().toString();
                    double lat = 0;
                    double lon = 0;
                    /*prendo il nome della città e lo passo alla funzione di geocoding:
                    * se la citta esiste, la aggiungo alla lista e la mostro nella lista,
                    * altrimenti catturo l'eccezione e mostro un toast
                     */
                    try {
                        //List<Address> cityCoordinates =  geocoding(cityName);
                        Geocoder gc = new Geocoder(getApplicationContext(), Locale.getDefault());
                        try {
                            Address loc = gc.getFromLocationName(cityName, 1).get(0);
                            lat = loc.getLatitude();
                            lon = loc.getLongitude();
                        } catch (Exception e) {

                        }
                        if(lat != 0 && lon != 0){
                            CityClass city = new CityClass(cityName, lat, lon);
                            cList.add(city);
                            setcListView(cList);
                        }
                        editText.setText("");
                    }catch (Exception e) {
                        editText.setText("");
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        cListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "City " +  cList.get(position).getLocationName() + " removed",Toast.LENGTH_LONG).show();
                cList.remove(position);
                setcListView(cList);
                return false;
            }
        });

        //faccio un bottone che fa geocoding e poi passa i dati al main mediante passedLon e passedLat e passedCity
        currentLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationManager locationManager = obtainLocationManager();

                //prendo la poszione attuale
                Location l = getLastKnownLocation();
                //trovo il nome della posizione attuale
                Geocoder gc = new Geocoder(cityList.this, Locale.getDefault());

                double lat = l.getLatitude();
                double lon = l.getLongitude();
                sp.edit().putFloat("passedLat", (float) lat).apply();
                sp.edit().putFloat("passedLon", (float)lon).apply();

                //salvo il nome della posizione attuale
                try {
                    Address loc = gc.getFromLocation(l.getLatitude(), l.getLongitude(), 1).get(0);
                    String location = loc.getLocality();
                    if(location == null) location = loc.getFeatureName();
                    sp.edit().putString("passedCity", location).apply();
                } catch (Exception e) {
                    sp.edit().putString("passedCity", "Burundi").apply();
                }

                onBackPressed();
            }
        });

        cListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sp.edit().remove("passedLat").commit();
                sp.edit().remove("passedLon").commit();

                sp.edit().putFloat("passedLat", (float)cList.get(position).getLatitude()).apply();
                sp.edit().putFloat("passedLon", (float)cList.get(position).getLongitude()).apply();
                sp.edit().putString("passedCity", cList.get(position).getLocationName()).apply();

                onBackPressed();
            }
        });
    }

    public void setcListView(ArrayList<CityClass> cList){
        cListNames.clear();
        for(int i = 0; i < cList.size(); i++){
            cListNames.add(cList.get(i).getLocationName());
        }
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cListNames);
        cListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        try {
            sp.edit().putString("cList",ObjectSerializer.serialize(cList)).apply();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        finishActivity(0);
    }

    public LocationManager obtainLocationManager() {
        if (ContextCompat.checkSelfPermission(cityList.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(cityList.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder alertdiagBuilder = new AlertDialog.Builder(cityList.this);
                alertdiagBuilder.setTitle("Permessi di geolocalizzazione");
                alertdiagBuilder.setMessage("Per alcune funzionalità di MyWeather è necessaria la posizione");
                alertdiagBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(cityList.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        dialog.cancel();
                    }
                });
                AlertDialog alert = alertdiagBuilder.create();
                alert.show();
            } else {
                ActivityCompat.requestPermissions(cityList.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        //inizializzo il location manager
        LocationManager locationManager = (LocationManager) cityList.this.getSystemService(Context.LOCATION_SERVICE);
        return locationManager;
    }

    //metodo per ottenere precisamente la poszione
    private Location getLastKnownLocation() {
        Location bestLocation = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return bestLocation;
        }
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        for (String provider : providers) {
            if(locationManager.isProviderEnabled(provider)) {
                locationManager.requestLocationUpdates(provider, 1000, 0, new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        locationManager.removeUpdates(this::onLocationChanged);
                    }
                });
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    bestLocation = l;
                }
            }
        }
        return bestLocation;
    }

}