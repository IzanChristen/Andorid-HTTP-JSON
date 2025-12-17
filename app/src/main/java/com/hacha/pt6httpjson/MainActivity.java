package com.hacha.pt6httpjson;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;


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