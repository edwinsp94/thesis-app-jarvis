package com.android.jarvis.gui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.jarvis.R;
import com.android.jarvis.modelos.Acceso;
import com.android.jarvis.modelos.Cuenta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class Setting2Activity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences preferences;
    private EditText editTextDni;
    private EditText editTextNombre;
    private Spinner spinnerSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
        editTextDni = findViewById(R.id.ActivitySetting2_editText_dni);
        editTextNombre = findViewById(R.id.ActivitySetting2_editText_nombre);
        spinnerSexo = findViewById(R.id.ActivitySetting2_spinner_sexo);
        Button buttonRegistrar = findViewById(R.id.ActivitySetting2_button_registrar);
        buttonRegistrar.setOnClickListener(this);
        preferences = getSharedPreferences("PreferencesJarvis", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        String dni = editTextDni.getText().toString().trim();
        if (!TextUtils.isEmpty(dni) && dni.length() == 8) {
            String nombre = editTextNombre.getText().toString().trim();
            if (!TextUtils.isEmpty(nombre)) {
                if (crearDirectorios(getFilesDir().getPath(), dni)) {
                    String sexo = spinnerSexo.getSelectedItem().toString();
                    String tipo = "Administrador";
                    String fecha = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date());
                    String foto = getFilesDir().getPath() + "/Fotos/" + dni + "_" + fecha + ".jpg";
                    try {
                        FileOutputStream out = new FileOutputStream(new File(foto));
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.foto_usuario_default);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        out.flush();
                        out.close();

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        String[] fechas = fecha.split("_");
                        Acceso acceso = new Acceso(fechas[0]+"/"+fechas[1]+"/"+fechas[2], fechas[3]+":"+fechas[4], dni);
                        Cuenta cuenta = new Cuenta(dni, nombre, sexo, tipo, foto);
                        cuenta.getAccesos().add(acceso);
                        realm.copyToRealm(cuenta);
                        realm.commitTransaction();
                        realm.close();

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("admin_dni", dni);
                        editor.putString("admin_audios", "false");
                        editor.putString("cuenta_dni", dni);
                        editor.putString("ruido", "false");
                        editor.apply();

                        Intent intent = new Intent(this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "No se pudo crear los directorios", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Por favor, ingrese el nombre", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "DNI inválido, debe tener 8 digitos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean crearDirectorios(String ruta, String dni) {
        boolean creoAudiosComandos = true;
        boolean creoAudiosPatrones = true;
        boolean creoMFFCComandos = true;
        boolean creoMFCCPatrones = true;

        File directorio = new File(ruta+"/Audios/Comandos/"+dni);
        if (!directorio.exists()) {
            creoAudiosComandos = directorio.mkdir();
        }
        directorio = new File(ruta+"/Audios/Patrones/"+dni);
        if (!directorio.exists()) {
            creoAudiosPatrones = directorio.mkdir();
        }
        directorio = new File(ruta+"/MFCC/Comandos/"+dni);
        if (!directorio.exists()) {
            creoMFFCComandos = directorio.mkdir();
        }
        directorio = new File(ruta+"/MFCC/Patrones/"+dni);
        if (!directorio.exists()) {
            creoMFCCPatrones = directorio.mkdir();
        }

        return  (creoAudiosComandos && creoAudiosPatrones && creoMFFCComandos && creoMFCCPatrones);
    }

}
