package com.hacha.pt6httpjson;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;


public class PreferenciasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Prefs())
                .commit();
    }

    public static class Prefs extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle b, String root) {
            setPreferencesFromResource(R.xml.preferencias, root);
        }
    }
}

