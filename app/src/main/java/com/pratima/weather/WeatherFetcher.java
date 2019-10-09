package com.pratima.weather;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFetcher extends AsyncTask<String, Void, Object> {
    @SuppressLint("StaticFieldLeak")
    private TextView textView;

    private static final String OPEN_WEATHER_MAP_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?id=";
    private static final String APP_ID = "&appid=d2cc7d350effb1c5e4e5f5e7cf5e0960&units=metric";
    public WeatherFetcher(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected Object doInBackground(String... strings) {
        String temprature = "UNDEFINED";
        String weather = "UNDEFINED";
        try {
            URL url = new URL(OPEN_WEATHER_MAP_BASE_URL + strings[0] + APP_ID);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }
            JSONObject topLevel = new JSONObject(builder.toString());
            JSONObject weatherJson = (JSONObject) topLevel.getJSONArray("weather").get(0);
            weather = weatherJson.getString("main");
            JSONObject main = topLevel.getJSONObject("main");
            temprature = String.valueOf(main.getDouble("temp"));

            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return weather + "," + temprature;
    }

    @Override
    protected void onPostExecute(Object weatherAndTemp) {
        String[] condition = weatherAndTemp.toString().split(",");
        textView.setText("Condition:" + condition[0] + "\nTemprature:" + condition[1] + "°C");
    }
}
