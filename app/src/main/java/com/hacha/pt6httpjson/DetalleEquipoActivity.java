package com.hacha.pt6httpjson;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class DetalleEquipoActivity extends AppCompatActivity {


    TextView txtEstadio, txtTitulos;
    ImageView imgEscudo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_equipo);


        txtEstadio = findViewById(R.id.txtEstadio);
        txtTitulos = findViewById(R.id.txtTitulos);

        imgEscudo = findViewById(R.id.imgEscudo);



        String liga = getIntent().getStringExtra("liga");
        String codigo = getIntent().getStringExtra("codigo");


        String baseUrl = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("url_base", "https://www.vidalibarraquer.net/android/sports/");


        String url = baseUrl + liga + "/" + codigo.toLowerCase() + ".json";
        new DescargarDetalle().execute(url);
        cargarEscudo(liga, codigo);

    }
    private void cargarEscudo(String liga, String codigo) {
        String urlImg = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("url_base", "https://www.vidalibarraquer.net/android/sports/")
                + liga + "/" + codigo.toLowerCase() + ".png";

        new Thread(() -> {
            try {
                InputStream is = new URL(urlImg).openStream();
                Bitmap bmp = BitmapFactory.decodeStream(is);
                runOnUiThread(() -> imgEscudo.setImageBitmap(bmp));
            } catch (Exception ignored) {}
        }).start();
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
                JSONObject root = new JSONObject(json);
                JSONArray data = root.getJSONArray("data");
                JSONObject obj = data.getJSONObject(0);

                txtEstadio.setText("Estadio: " + obj.getString("team_stadium"));
                txtTitulos.setText("Títulos: " + obj.getString("titles"));

            } catch (Exception e) {
                txtEstadio.setText("Estadio: No disponible");
                txtTitulos.setText("Títulos: No disponibles");
            }
        }

    }
}