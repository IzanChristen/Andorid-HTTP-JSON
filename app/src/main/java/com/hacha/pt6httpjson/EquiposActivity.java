package com.hacha.pt6httpjson;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class EquiposActivity extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<String> equipos = new ArrayList<>();
    String liga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);

        liga = getIntent().getStringExtra("liga");

        recycler = findViewById(R.id.recyclerEquipos);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        if (!hayInternet()) {
            Toast.makeText(this, "No hay conexión a Internet", Toast.LENGTH_LONG).show();
            return;
        }

        String url = "https://www.vidalibarraquer.net/android/sports/" + liga + ".json";
        new DescargarJSON().execute(url);
    }

    private boolean hayInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }

    class DescargarJSON extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String linea, texto = "";
                while ((linea = br.readLine()) != null) texto += linea;
                return texto;
            } catch (Exception e) {
                return "";
            }
        }

        protected void onPostExecute(String json) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    equipos.add(obj.getString("nombre"));
                }
                recycler.setAdapter(new EquiposAdapter(equipos));
            } catch (Exception e) {
                Toast.makeText(EquiposActivity.this, "Error leyendo JSON", Toast.LENGTH_LONG).show();
            }
        }
    }
}
