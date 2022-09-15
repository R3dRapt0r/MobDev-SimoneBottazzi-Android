package com.example.weather_project;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class jsonObjGetter extends AsyncTask<String /* tipo di cosa viene passato */, Void, JSONObject /* tipo di cosa viene restituito */> {
    @Override
    protected JSONObject doInBackground(String... strings) {
        String json = "";

        try {
            URL url = new URL(strings[0]); // passerò solo un URL
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String current;

            while ((current = bufferedReader.readLine()) != null)
                json += current;

            bufferedReader.close();

            return new JSONObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
