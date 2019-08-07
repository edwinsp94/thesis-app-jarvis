package com.android.jarvis.gui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.jarvis.R;
import com.android.jarvis.modelos.Acceso;
import com.android.jarvis.modelos.Cuenta;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class Setting1Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextDni;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting1);
        editTextDni = findViewById(R.id.ActivitySetting1_editText_dni);
        Button buttonCrearCuenta = findViewById(R.id.ActivitySetting1_button_crearCuenta);
        Button buttonIniciar = findViewById(R.id.ActivitySetting1_button_iniciar);
        buttonCrearCuenta.setOnClickListener(this);
        buttonIniciar.setOnClickListener(this);
        preferences = getSharedPreferences("PreferencesJarvis", Context.MODE_PRIVATE);
        if (preferences.getString("admin_dni", "").equals("") &&
            preferences.getString("admin_audios", "").equals("")) {
            buttonCrearCuenta.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ActivitySetting1_button_crearCuenta:
                clickButtonCrearCuenta();
                break;
            case R.id.ActivitySetting1_button_iniciar:
                clickButtonIniciar();
                break;
        }
    }

    private void clickButtonCrearCuenta() {
        Intent intent = new Intent(this, Setting2Activity.class);
        startActivity(intent);
    }

    private void clickButtonIniciar() {
        String cuenta_dni = editTextDni.getText().toString().trim();
        if (!TextUtils.isEmpty(cuenta_dni) && cuenta_dni.length() == 8) {
            if (preferences.getString("admin_dni", "").equals(cuenta_dni)) {
                Realm realm = Realm.getDefaultInstance();
                Cuenta cuenta = realm.where(Cuenta.class).equalTo("dni", cuenta_dni).findFirst();
                if (cuenta != null) {
                    String[] fechas = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date()).split("_");
                    realm.beginTransaction();
                    Acceso acceso = new Acceso(fechas[0]+"/"+fechas[1]+"/"+fechas[2], fechas[3]+":"+fechas[4], cuenta.getDni());
                    realm.copyToRealm(acceso);
                    cuenta.getAccesos().add(acceso);
                    realm.commitTransaction();
                    realm.close();

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("cuenta_dni", cuenta_dni);
                    editor.apply();

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    realm.close();
                }
            } else {
                Toast.makeText(this, "DNI no registrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "DNI inválido, debe tener 8 digitos", Toast.LENGTH_SHORT).show();
        }
    }

}
