package com.hacha.pt6httpjson;


import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class DetalleEquipoActivity extends AppCompatActivity {


    TextView txtEstadio, txtTitulos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_equipo);


        txtEstadio = findViewById(R.id.txtEstadio);
        txtTitulos = findViewById(R.id.txtTitulos);


        String liga = getIntent().getStringExtra("liga");
        String codigo = getIntent().getStringExtra("codigo");


        String baseUrl = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("url_base", "https://www.vidalibarraquer.net/android/sports/");


        String url = baseUrl + liga + "/" + codigo.toLowerCase() + ".json";
        new DescargarDetalle().execute(url);
    }


    class DescargarDetalle extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new URL(urls[0]).openStream()));
                String l, t = "";
                while ((l = br.readLine()) != null) t += l;
                return t;
            } catch (Exception e) {
                return "";
            }
        }


        protected void onPostExecute(String json) {
            try {
                JSONObject o = new JSONObject(json);
                txtEstadio.setText("Estadio: " + o.getString("estadio"));
                txtTitulos.setText("Títulos: " + o.getString("titulos"));
            } catch (Exception ignored) {}
        }
    }
}