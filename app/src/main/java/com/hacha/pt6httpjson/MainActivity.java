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

        ImageView premier = findViewById(R.id.imgPremier);
        ImageView laliga = findViewById(R.id.imgLaLiga);
        ImageView seriea = findViewById(R.id.imgSerieA);
        ImageView bundes = findViewById(R.id.imgBundesliga);
        ImageView ligue1 = findViewById(R.id.imgLigue1);

        premier.setOnClickListener(v -> abrirEquipos("premier"));
        laliga.setOnClickListener(v -> abrirEquipos("laliga"));
        seriea.setOnClickListener(v -> abrirEquipos("seriea"));
        bundes.setOnClickListener(v -> abrirEquipos("bundesliga"));
        ligue1.setOnClickListener(v -> abrirEquipos("ligue1"));
    }

    private void abrirEquipos(String liga) {
        Intent intent = new Intent(this, EquiposActivity.class);
        intent.putExtra("liga", liga);
        startActivity(intent);
    }
}
