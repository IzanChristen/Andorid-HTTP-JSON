package com.hacha.pt6httpjson;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView nba = findViewById(R.id.imgNBA);
        ImageView nfl = findViewById(R.id.imgNFL);
        ImageView mlb = findViewById(R.id.imgMLB);
        ImageView nhl = findViewById(R.id.imgNHL);
        ImageView mls = findViewById(R.id.imgMLS);

        RadioGroup radioGroup = findViewById(R.id.radioGroupUrl);
        RadioButton radioSports = findViewById(R.id.radioSports);
        RadioButton radioLeagues = findViewById(R.id.radioLeagues);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        String urlGuardada = prefs.getString(
                "url_base",
                "https://www.vidalibarraquer.net/android/sports/"
        );

        if (urlGuardada.contains("leagues")) {
            radioLeagues.setChecked(true);
        } else {
            radioSports.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioSports) {
                editor.putString("url_base",
                        "https://www.vidalibarraquer.net/android/sports/");
            } else {
                editor.putString("url_base",
                        "https://www.vidalibarraquer.net/android/leagues/");
            }
            editor.apply();
        });

        nba.setOnClickListener(v -> abrirEquipos("nba"));
        nfl.setOnClickListener(v -> abrirEquipos("nfl"));
        mlb.setOnClickListener(v -> abrirEquipos("mlb"));
        nhl.setOnClickListener(v -> abrirEquipos("nhl"));
        mls.setOnClickListener(v -> abrirEquipos("mls"));
    }



    private void abrirEquipos(String liga) {
        Intent intent = new Intent(this, EquiposActivity.class);
        intent.putExtra("liga", liga);
        startActivity(intent);
    }
}