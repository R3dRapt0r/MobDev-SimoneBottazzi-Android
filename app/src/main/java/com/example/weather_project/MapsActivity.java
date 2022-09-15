package com.example.weather_project;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.weather_project.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    SharedPreferences sp;
    ArrayList<CityClass> cList;

    ArrayList<String> condWeatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = this.getSharedPreferences("com.example.weather_project", Context.MODE_PRIVATE);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //fa il setup della mappa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //al fine del caricamento della mappa, aggiunge i marker delle citta
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //faccio lo zoom sull ultima coordinata passata
        double zoomLat = sp.getFloat("passedLat", 0);
        double zoomLon = sp.getFloat("passedLon", 0);
        LatLng forZoom = new LatLng(zoomLat, zoomLon);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(forZoom, 9));

        cList = new ArrayList<>();
        condWeatherList = new ArrayList<>();
        //recupero la lista dalle shared preferences deseriallizzandola
        try {
            cList = (ArrayList<CityClass>) ObjectSerializer.deserialize(sp.getString("cList", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }


        boolean comp;
        int cont = 0;
        do {
            comp = true;
            try {
                for(int i = 0; i < cList.size(); i++) {
                    double lat = cList.get(i).getLatitude();
                    double lon = cList.get(i).getLongitude();
                    String locationName = cList.get(i).getLocationName();
                    LatLng coords = new LatLng(lat, lon);

                    //ottengo la condizione meteo per impostare l'icona del marker

                    JSONObject obj = new jsonObjGetter().execute("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&units=metric&exclude=minutely,hourly,alerts&lang=it&appid=d902ed4c248578b9aca94d5d60d583ee").get();
                    JSONObject current = obj.getJSONObject("current");
                    JSONArray wedata = current.getJSONArray("weather");
                    JSONObject weobj = (JSONObject) wedata.get(0);
                    String westring = weobj.getString("main");

                    switch (westring) {
                        case "Clear":
                            mMap.addMarker(new MarkerOptions().position(coords).title(locationName).icon(BitmapDescriptorFactory.fromResource(R.drawable.sunmk)));
                            break;
                        case "Clouds":
                            mMap.addMarker(new MarkerOptions().position(coords).title(locationName).icon(BitmapDescriptorFactory.fromResource(R.drawable.cloudmk)));
                            break;
                        case "Snow":
                            mMap.addMarker(new MarkerOptions().position(coords).title(locationName).icon(BitmapDescriptorFactory.fromResource(R.drawable.snowmk)));

                            break;
                        case "Rain":
                            mMap.addMarker(new MarkerOptions().position(coords).title(locationName).icon(BitmapDescriptorFactory.fromResource(R.drawable.rainmk)));
                            break;
                        case "Thunderstorm":
                            mMap.addMarker(new MarkerOptions().position(coords).title(locationName).icon(BitmapDescriptorFactory.fromResource(R.drawable.stormmk)));
                            break;
                        default:
                            mMap.addMarker(new MarkerOptions().position(coords).title(locationName).icon(BitmapDescriptorFactory.fromResource(R.drawable.sunmk)));
                    }
                }

            } catch (Exception e) {
        comp = false;
        ++cont;
        if(cont == 2500) {
            cont = 0;
            Toast.makeText(MapsActivity.this, "La connessione internet potrebbe essere " +
                    "assente o instabile", Toast.LENGTH_LONG).show();
        }
    }
} while(!comp);

}

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finishActivity(0);
    }
}