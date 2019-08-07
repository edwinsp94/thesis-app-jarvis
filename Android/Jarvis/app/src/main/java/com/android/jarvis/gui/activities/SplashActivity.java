package com.android.jarvis.gui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (crearDirectorios(getFilesDir().getPath())) {
            SharedPreferences preferences = getSharedPreferences("PreferencesJarvis", Context.MODE_PRIVATE);
            if (!preferences.getString("admin_dni", "").equals("")) {
                if (!preferences.getString("cuenta_dni", "").equals("")) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    if (preferences.getString("admin_audios", "").equals("true")) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(this, Setting1Activity.class);
                        startActivity(intent);
                    }
                }
            } else {
                Intent intent = new Intent(this, Setting1Activity.class);
                startActivity(intent);
            }
            finish();
        } else {
            Toast.makeText(this, "No se pudo crear los directorios", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean crearDirectorios(String ruta) {
        boolean creoFotos = true;
        boolean creoAudios = true;
        boolean creoAudiosComandos = true;
        boolean creoAudiosPatrones = true;
        boolean creoMFCC = true;
        boolean creoMFCCComandos = true;
        boolean creoMFCCPatrones = true;

        File directorio = new File(ruta+"/Fotos");
        if (!directorio.exists()) {
            creoFotos = directorio.mkdir();
        }
        directorio = new File(ruta+"/Audios");
        if (!directorio.exists()) {
            creoAudios = directorio.mkdir();
        }
        directorio = new File(ruta+"/Audios/Comandos");
        if (!directorio.exists()) {
            creoAudiosComandos = directorio.mkdir();
        }
        directorio = new File(ruta+"/Audios/Patrones");
        if (!directorio.exists()) {
            creoAudiosPatrones = directorio.mkdir();
        }
        directorio = new File(ruta+"/MFCC");
        if (!directorio.exists()) {
            creoMFCC = directorio.mkdir();
        }
        directorio = new File(ruta+"/MFCC/Comandos");
        if (!directorio.exists()) {
            creoMFCCComandos = directorio.mkdir();
        }
        directorio = new File(ruta+"/MFCC/Patrones");
        if (!directorio.exists()) {
            creoMFCCPatrones = directorio.mkdir();
        }

        return (creoFotos && creoAudios && creoAudiosComandos && creoAudiosPatrones &&
                creoMFCC && creoMFCCComandos && creoMFCCPatrones);
    }

}
