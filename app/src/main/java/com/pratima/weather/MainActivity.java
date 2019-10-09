package com.pratima.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String STOCKHOLM_ID = "2673730";
    private static final String PUNE_ID = "1259229";
    private static final String MALMO_ID = "2692969";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new WeatherFetcher((TextView) findViewById(R.id.Stockholm)).execute(STOCKHOLM_ID);
        new WeatherFetcher((TextView) findViewById(R.id.Pune)).execute(PUNE_ID);
        new WeatherFetcher((TextView) findViewById(R.id.Malmo)).execute(MALMO_ID);
    }
}
