package com.example.weather_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Intent;

//quello giusto

public class MainActivity extends AppCompatActivity {
    public static final String CITY_LIST = "com.example.weather_project.CITYLIST";
    Button listButton;
    Button mapButton;
    ArrayList cListBackup;
    String resultString;
    SharedPreferences sp;

    public class DownloadTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try{
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                int giorno = 0; //lo uso per scorrere il json
                int indexTag = 0; //lo uso per scorrere i tag
                JSONObject jsonObject = new JSONObject(result);
                String cityName = sp.getString("passedCity", ":(");

                JSONArray weatherObject = jsonObject.getJSONArray("daily");

                //prendo il mio vertical layout che conbiene gli 8 horizontal layout figli
                LinearLayout rootLayout = (LinearLayout) findViewById(R.id.verticalLayout);

                //giorno corrente
                JSONObject current = jsonObject.getJSONObject("current");
                TextView currentTemp = (TextView) findViewById(R.id.currentDayDTemp);
                TextView currentDescr = (TextView) findViewById(R.id.currentDayDescription);
                currentTemp.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
                currentTemp.setText(String.valueOf(current.getInt("temp")) + "°C");
                currentDescr.setText(cityName);
                currentTemp.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                currentDescr.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                JSONObject descriptionCurrent = current.getJSONArray("weather").getJSONObject(0);
                String descriptionStringCurrent = descriptionCurrent.getString("main");

                View view = findViewById(R.id.currentLayout);
                switch (descriptionStringCurrent){
                    case "Clouds":
                        view.setBackgroundResource(R.drawable.cloudy);
                        break;
                    case "Snow":
                        view.setBackgroundResource(R.drawable.snowy);
                        break;
                    case "Rain":
                        view.setBackgroundResource(R.drawable.rainy);
                        break;
                    case "Drizzle":
                        view.setBackgroundResource(R.drawable.drizzly);
                        break;
                    case "Thunderstorm":
                        view.setBackgroundResource(R.drawable.stormy);
                        break;
                    default:
                        view.setBackgroundResource(R.drawable.sunny);
                }


                //imposto i dati ricevuti dalla chiamata per creare la descrizione dei giorni
                while(giorno < 8){

                    JSONObject day = weatherObject.getJSONObject(giorno);
                    JSONObject temp = day.getJSONObject("temp");

                    long date = day.getLong("dt");
                    Date d = new Date(date * 1000);
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE", Locale.ITALIAN);
                    String ds = formatter.format(d);
                    ds = ds.substring(0, 1).toUpperCase(Locale.ROOT) + ds.substring(1);

                    //chiamo 4 volte per modificare in ordine text view e image view
                    //prima text view
                    TextView textView = (TextView) rootLayout.findViewWithTag(Integer.toString(indexTag));
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                    textView.setText(ds);
                    indexTag++;

                    //image view
                    ImageView weatherIcon = (ImageView) rootLayout.findViewWithTag(Integer.toString(indexTag));
                    weatherIcon.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                    JSONObject description = day.getJSONArray("weather").getJSONObject(0);
                    String descriptionString = description.getString("main");
                    switch (descriptionString){
                        case "Clear":
                            weatherIcon.setImageResource(R.drawable.sun);
                            break;
                        case "Clouds":
                            weatherIcon.setImageResource(R.drawable.cloud);
                            break;
                        case "Snow":
                            weatherIcon.setImageResource(R.drawable.snow);
                            break;
                        case "Rain":
                            weatherIcon.setImageResource(R.drawable.rain);
                            break;
                        case "Drizzle":
                            weatherIcon.setImageResource(R.drawable.drizzle);
                            break;
                        case "Thunderstorm":
                            weatherIcon.setImageResource(R.drawable.storm);
                            break;
                        default:
                            weatherIcon.setImageResource(R.drawable.default_miku);
                    }
                    indexTag ++;


                    //seconda text view
                    textView = (TextView) rootLayout.findViewWithTag(Integer.toString(indexTag));
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                    int minTemp = (int) temp.getDouble("min");
                    textView.setText(Integer.toString(minTemp) + "C°");
                    indexTag++;

                    //terza text view
                    textView = (TextView) rootLayout.findViewWithTag(Integer.toString(indexTag));
                    textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                    int maxTemp = (int) temp.getDouble("max");
                    textView.setText(Integer.toString(maxTemp) + "C°");
                    indexTag++;

                    giorno++;
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
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

    //avviato solo una volta all'apertura dell'app, prendo la poszione attuale come poszione di default
    @Override
    protected void onCreate(Bundle extras) {
        super.onCreate(extras);
        setContentView(R.layout.activity_main);
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        listButton = findViewById(R.id.btnCities);
        sp = this.getSharedPreferences("com.example.weather_project", Context.MODE_PRIVATE);

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCityList(view);
            }
        });

        mapButton = findViewById(R.id.btnMap);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap(view);
            }
        });

        //controllo i permessi per la localizzazione
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder alertdiagBuilder = new AlertDialog.Builder(MainActivity.this);
                alertdiagBuilder.setTitle("Permessi di geolocalizzazione");
                alertdiagBuilder.setMessage("Per alcune funzionalità di MyWeather è necessaria la posizione");
                alertdiagBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                        dialog.cancel();
                    }
                });
                AlertDialog alert = alertdiagBuilder.create();
                alert.show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        //inizializzo il location manager
        LocationManager locationManager = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);

        //prendo la poszione attuale
        Location l = getLastKnownLocation();
        //trovo il nome della posizione attuale
        Geocoder gc = new Geocoder(MainActivity.this, Locale.getDefault());

        double lat = l.getLatitude();
        double lon = l.getLongitude();

        //salvo il nome della posizione attuale
        try {
            Address loc = gc.getFromLocation(l.getLatitude(), l.getLongitude(), 1).get(0);
            String location = loc.getLocality();
            if(location == null) location = loc.getFeatureName();
            sp.edit().putString("passedCity", location).apply();
        } catch (Exception e) {
            sp.edit().putString("passedCity", "Burundi").apply();
        }

        DownloadTask task = new DownloadTask();
        task.execute("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&units=metric&exclude=minutely,hourly,alerts&lang=it&appid=d902ed4c248578b9aca94d5d60d583ee");
    }

    //viene chiamata quando torno sulla vista, e carica le ultime latitudine e longitudine salvate nelle shared preferences
    @Override
    protected void onResume() {
        super.onResume();

        sp = this.getSharedPreferences("com.example.weather_project", Context.MODE_PRIVATE);
        TextView textView;

        double lat = sp.getFloat("passedLat", (float)45.0477);
        double lon = sp.getFloat("passedLon", (float)9.7004);

        DownloadTask task = new DownloadTask();
        task.execute("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&units=metric&exclude=minutely,hourly,alerts&lang=it&appid=d902ed4c248578b9aca94d5d60d583ee");
    }

    //funzione per aprire la lista delle citta e possare la lista salvata
    public void openCityList(View view){
        Intent intent = new Intent(this,cityList.class);
        startActivity(intent);
    }

    //funzione per aprire la mappa
    public void openMap(View view){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
}