package com.android.jarvis.gui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.jarvis.R;
import com.android.jarvis.app.MiProgressDialog;
import com.android.jarvis.conexion.Arduino;
import com.android.jarvis.conexion.Cliente;
import com.android.jarvis.conexion.Servidor;
import com.android.jarvis.gui.fragments.IdentificacionFragment;
import com.android.jarvis.gui.fragments.ListaAccesosFragment;
import com.android.jarvis.gui.fragments.ListaComandosFragment;
import com.android.jarvis.gui.fragments.ListaConexionesFragment;
import com.android.jarvis.gui.fragments.ListaCuentasFragment;
import com.android.jarvis.gui.fragments.ListaGrabacionesFragment;
import com.android.jarvis.gui.fragments.PrincipalFragment;
import com.android.jarvis.modelos.Cuenta;
import com.android.jarvis.reconocedor.Algoritmo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.zelory.compressor.Compressor;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   IdentificacionFragment.Callbacks,
                   TextToSpeech.OnInitListener {

    private IdentificacionFragment identificacionFragment;
    private PrincipalFragment principalFragment;
    private ListaCuentasFragment listaCuentasFragment;
    private ListaAccesosFragment listaAccesosFragment;
    private ListaConexionesFragment listaConexionesFragment;

    public MenuItem menuItemIniciarSesion;
    public MenuItem menuItemCerrarSesion;
    public MenuItem menuItemAdministrarCuentas;
    public MenuItem menuItemConsultarAccesos;

    private SharedPreferences preferences;
    private File archivoFoto;

    public static final int CAPTURA_FOTO = 1;
    public static final int HAY_PERMISOS = 2;
    public static String FRAGMENT_ACTUAL;
    public static Servidor servidor;
    @SuppressLint("StaticFieldLeak")
    public static Cliente cliente;
    @SuppressLint("StaticFieldLeak")
    public static Realm realm;
    public static TextToSpeech textToSpeech;
    public static boolean configuroTextToSpeech;
    public static boolean puedoHablar;
    public Arduino arduino;
    private MiProgressDialog miProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        menuItemIniciarSesion = navigationView.getMenu().getItem(0).getSubMenu().getItem(0);
        menuItemCerrarSesion = navigationView.getMenu().getItem(0).getSubMenu().getItem(1);
        menuItemAdministrarCuentas = navigationView.getMenu().getItem(2).getSubMenu().getItem(0);
        menuItemConsultarAccesos = navigationView.getMenu().getItem(2).getSubMenu().getItem(1);

        realm = Realm.getDefaultInstance();
        preferences = getSharedPreferences("PreferencesJarvis", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ruido", "false");
        editor.apply();
        String cuenta_dni = preferences.getString("cuenta_dni", "");
        if (!cuenta_dni.equals("")) {
            menuItemIniciarSesion.setEnabled(true);
            menuItemCerrarSesion.setEnabled(true);
            menuItemIniciarSesion.setIcon(R.drawable.ic_menu_record_voice);
            menuItemIniciarSesion.setTitle("Comandos por Voz");
            Cuenta cuenta = MainActivity.realm.where(Cuenta.class).equalTo("dni", cuenta_dni).findFirst();
            if (cuenta != null) {
                if (cuenta.getTipo().equals("Administrador")) {
                    menuItemAdministrarCuentas.setVisible(true);
                    menuItemConsultarAccesos.setVisible(true);
                } else {
                    menuItemAdministrarCuentas.setVisible(false);
                    menuItemConsultarAccesos.setVisible(false);
                }
            }
        } else {
            menuItemIniciarSesion.setEnabled(true);
            menuItemCerrarSesion.setEnabled(false);
            menuItemAdministrarCuentas.setVisible(false);
            menuItemConsultarAccesos.setVisible(false);
        }

        identificacionFragment = new IdentificacionFragment();
        identificacionFragment.mainActivity = this;
        principalFragment = new PrincipalFragment();
        listaConexionesFragment = new ListaConexionesFragment();
        listaCuentasFragment = new ListaCuentasFragment();
        listaAccesosFragment = new ListaAccesosFragment();

        archivoFoto = null;
        servidor = null;
        cliente = null;
        arduino = new Arduino("192.168.1.105", 8888, this);

        textToSpeech = new TextToSpeech(this,this);
        textToSpeech.setLanguage(new Locale("spa","ESP"));
        configuroTextToSpeech = false;

        revisarPermisos(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        insertarFragmento(principalFragment, "PrincipalFragment", "Jarvis");
    }

    private void revisarPermisos(Context context) {
        String[] permisos = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (context != null) {
            for (int i = 0; i < permisos.length; i++) {
                if (ActivityCompat.checkSelfPermission(context, permisos[i]) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, permisos, HAY_PERMISOS);
                    i = permisos.length;
                }
            }
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.LANG_MISSING_DATA | status == TextToSpeech.LANG_NOT_SUPPORTED) {
            puedoHablar = false;
            Toast.makeText(this,"No puedo hablar", Toast.LENGTH_SHORT ).show();
        } else {
            puedoHablar = true;
            configurarTextToSpeech();
        }
    }

    public static void configurarTextToSpeech() {
        textToSpeech.setSpeechRate(1.5f);
        textToSpeech.setPitch(0.0f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            configuroTextToSpeech = true;
            for (Voice temporalVoice : textToSpeech.getVoices()) {
                if (temporalVoice.getName().equals("es-es-x-ana#male_1-local")) {
                    textToSpeech.setVoice(temporalVoice);
                }
            }
        }
    }

    public static void speak(String texto) {
        if (!configuroTextToSpeech) {
            configurarTextToSpeech();
        }
        textToSpeech.speak(texto, TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ruido", "false");
        File[] archivos = new File(getFilesDir().getPath()+"/Audios/Patrones/"+preferences.getString("admin_dni", "")).listFiles();
        if (archivos != null && archivos.length > 0) {
            editor.putString("admin_audios", "true");
        }
        editor.apply();
        realm.close();
        arduino = null;
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        if (MainActivity.cliente != null) {
            MainActivity.cliente.enviarInformacion("apagar");
            MainActivity.cliente = null;
            if (MainActivity.servidor != null) {
                try {
                    MainActivity.servidor.apagar();
                } catch (IOException e) {
                    Toast.makeText(this, "No se pudo apagar correctamente el servidor", Toast.LENGTH_SHORT).show();
                }
                MainActivity.servidor = null;
            }
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (FRAGMENT_ACTUAL.equals("IdentificacionFragment")) {
            if (identificacionFragment.grabando) {
                identificacionFragment.cancelarGrabacion(principalFragment, "PrincipalFragment", "Jarvis", false);
            } else if (identificacionFragment.hayEnvio) {
                identificacionFragment.cancelarEnvio(principalFragment, "PrincipalFragment", "Jarvis", false);
            } else {
                insertarFragmento(principalFragment, "PrincipalFragment", "Jarvis");
            }
        } else if (FRAGMENT_ACTUAL.equals("ListaCuentasFragment") ||
                   FRAGMENT_ACTUAL.equals("ListaAccesosFragment") ||
                   FRAGMENT_ACTUAL.equals("ListaConexionesFragment")) {
            insertarFragmento(principalFragment, "PrincipalFragment", "Jarvis");
        } else if (FRAGMENT_ACTUAL.equals("CuentaFragment")) {
            insertarFragmento(listaCuentasFragment, "ListaCuentasFragment", "Administrar Cuentas");
        } else if (FRAGMENT_ACTUAL.equals("ListaComandosFragment")) {
            insertarFragmento(listaCuentasFragment.cuentaFragment, "CuentaFragment", listaCuentasFragment.cuentaFragment.titleFragment);
        } else if (FRAGMENT_ACTUAL.equals("ListaGrabacionesFragment")) {
            if (listaCuentasFragment.cuentaFragment.menuAccion.equals("patron")) {
                insertarFragmento(listaCuentasFragment.cuentaFragment, "CuentaFragment", listaCuentasFragment.cuentaFragment.titleFragment);
            } else if (listaCuentasFragment.cuentaFragment.menuAccion.equals("comando")) {
                listaCuentasFragment.cuentaFragment.listaComandosFragment = new ListaComandosFragment();
                listaCuentasFragment.cuentaFragment.listaComandosFragment.cuentaFragment = listaCuentasFragment.cuentaFragment;
                insertarFragmento(listaCuentasFragment.cuentaFragment.listaComandosFragment, "ListaComandosFragment", "Lista de Comandos");
            }
        } else if (FRAGMENT_ACTUAL.equals("GrabarFragment")) {
            if (listaCuentasFragment.cuentaFragment.menuAccion.equals("patron")) {
                ListaGrabacionesFragment listaGrabacionesFragment = new ListaGrabacionesFragment();
                listaGrabacionesFragment.URI = listaCuentasFragment.cuentaFragment.cuenta.getDni();
                insertarFragmento(listaGrabacionesFragment, "ListaGrabacionesFragment", "Patron de Voz");
            } else if (listaCuentasFragment.cuentaFragment.menuAccion.equals("comando")) {
                String comando = listaCuentasFragment.cuentaFragment.listaComandosFragment.comandoSeleccionado;
                ListaGrabacionesFragment listaGrabacionesFragment = new ListaGrabacionesFragment();
                listaGrabacionesFragment.URI = listaCuentasFragment.cuentaFragment.cuenta.getDni()+"/"+comando;
                insertarFragmento(listaGrabacionesFragment, "ListaGrabacionesFragment", "Comando - "+comando);
            }
        } else if (FRAGMENT_ACTUAL.equals("PrincipalFragment")) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_iniciar_sesion:
                if (!preferences.getString("cuenta_dni", "").equals("")) {
                    insertarFragmento(identificacionFragment, "IdentificacionFragment", "Comandos por Voz");
                } else {
                    insertarFragmento(identificacionFragment, "IdentificacionFragment", "Iniciar Sesión");
                }
                break;
            case R.id.nav_cerrar_sesion:
                if (FRAGMENT_ACTUAL.equals("IdentificacionFragment")) {
                    if (identificacionFragment.grabando) {
                        identificacionFragment.cancelarGrabacion(principalFragment, "PrincipalFragment", "Jarvis", true);
                    } else if (identificacionFragment.hayEnvio) {
                        identificacionFragment.cancelarEnvio(principalFragment, "PrincipalFragment", "Jarvis", true);
                    } else {
                        cerrarSesion();
                        insertarFragmento(principalFragment, "PrincipalFragment", "Jarvis");
                    }
                } else {
                    cerrarSesion();
                    insertarFragmento(principalFragment, "PrincipalFragment", "Jarvis");
                }
                break;
            case R.id.nav_micofono_externo:
                if (FRAGMENT_ACTUAL.equals("IdentificacionFragment")) {
                    if (identificacionFragment.grabando) {
                        identificacionFragment.cancelarGrabacion(listaConexionesFragment, "ListaConexionesFragment", "Micrófonos Externos", false);
                    } else if (identificacionFragment.hayEnvio) {
                        identificacionFragment.cancelarEnvio(listaConexionesFragment, "ListaConexionesFragment", "Micrófonos Externos", false);
                    } else {
                        insertarFragmento(listaConexionesFragment, "ListaConexionesFragment", "Micrófonos Externos");
                    }
                } else {
                    insertarFragmento(listaConexionesFragment, "ListaConexionesFragment", "Micrófonos Externos");
                }
                break;
            case R.id.nav_dispositivo_arduino:
                ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connManager != null) {
                    NetworkInfo wifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                    if (wifi.isConnected()) {
                        if (!arduino.conectado) {
                            arduino.conectar();
                        } else {
                            arduino.desconectar();
                        }
                    } else {
                        Toast.makeText(this, "Encienda su Wi-Fi y conectese a una red", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.nav_admnistrar_cuentas:
                if (FRAGMENT_ACTUAL.equals("IdentificacionFragment")) {
                    if (identificacionFragment.grabando) {
                        identificacionFragment.cancelarGrabacion(listaCuentasFragment, "ListaCuentasFragment", "Administrar Cuentas", false);
                    } else if (identificacionFragment.hayEnvio) {
                        identificacionFragment.cancelarEnvio(listaCuentasFragment, "ListaCuentasFragment", "Administrar Cuentas", false);
                    } else {
                        insertarFragmento(listaCuentasFragment, "ListaCuentasFragment", "Administrar Cuentas");
                    }
                } else {
                    insertarFragmento(listaCuentasFragment, "ListaCuentasFragment", "Administrar Cuentas");
                }
                break;
            case R.id.nav_consultar_accesos:
                if (FRAGMENT_ACTUAL.equals("IdentificacionFragment")) {
                    if (identificacionFragment.grabando) {
                        identificacionFragment.cancelarGrabacion(listaAccesosFragment, "ListaAccesosFragment", "Consultar Accesos", false);
                    } else if (identificacionFragment.hayEnvio) {
                        identificacionFragment.cancelarEnvio(listaAccesosFragment, "ListaAccesosFragment", "Consultar Accesos", false);
                    } else {
                        insertarFragmento(listaAccesosFragment, "ListaAccesosFragment", "Consultar Accesos");
                    }
                } else {
                    insertarFragmento(listaAccesosFragment, "ListaAccesosFragment", "Consultar Accesos");
                }
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("StaticFieldLeak")
    public void cerrarSesion() {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                Algoritmo.obtenerUmbralesParaDecision(MainActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("cuenta_dni", "");
                File[] archivos = new File(getFilesDir().getPath()+"/Audios/Patrones/"+preferences.getString("admin_dni", "")).listFiles();
                if (archivos != null && archivos.length > 0) {
                    editor.putString("admin_audios", "true");
                }
                editor.apply();
                if (arduino.conectado) {
                    arduino.enviar("cerrar");
                }
                return null;
            }

            @Override
            protected void onPreExecute() {
                miProgressDialog = new MiProgressDialog(MainActivity.this, "Cerrando Sesión", "Por favor, espere un momento...");
                miProgressDialog.iniciar();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                miProgressDialog.detener();
                menuItemIniciarSesion.setIcon(R.drawable.ic_menu_user_online);
                menuItemIniciarSesion.setTitle("Iniciar Sesión");
                menuItemCerrarSesion.setEnabled(false);
                menuItemAdministrarCuentas.setVisible(false);
                menuItemConsultarAccesos.setVisible(false);
                identificacionFragment = new IdentificacionFragment();
                identificacionFragment.mainActivity = MainActivity.this;
                listaCuentasFragment = new ListaCuentasFragment();
                listaAccesosFragment = new ListaAccesosFragment();
                Toast.makeText(MainActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
                if (MainActivity.puedoHablar) {
                    MainActivity.speak("Sesión cerrada");
                }
            }
        }.execute();
    }

    private void insertarFragmento(Fragment newFragment, String nameFragment, String titleFragment) {
        setTitle(titleFragment);
        FRAGMENT_ACTUAL = nameFragment;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragmentOld = fm.findFragmentById(R.id.mainFrame);
        if (fragmentOld != null) {
            if (!fragmentOld.equals(newFragment)) {
                ft.remove(fragmentOld);
                ft.add(R.id.mainFrame, newFragment);
            }
        } else {
            ft.add(R.id.mainFrame, newFragment);
        }
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURA_FOTO && resultCode == Activity.RESULT_OK) {
            if (archivoFoto != null && archivoFoto.exists()) {
                String cuenta_dni = preferences.getString("cuenta_dni", "");
                if (!cuenta_dni.equals("")) {
                    try {
                        File compressFile = new Compressor(this).setDestinationDirectoryPath(getFilesDir().getPath()+"/Fotos").compressToFile(archivoFoto);
                        Cuenta cuenta = realm.where(Cuenta.class).equalTo("dni", cuenta_dni).findFirst();
                        if (cuenta != null) {
                            File file = new File(cuenta.getFoto());
                            if (file.delete()) {
                                realm.beginTransaction();
                                cuenta.setFoto(compressFile.getPath());
                                realm.copyToRealmOrUpdate(cuenta);
                                realm.commitTransaction();
                                identificacionFragment.setImageViewFotoUsuario(compressFile.getPath());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (!eliminarFotoMemoriaInterna() || !archivoFoto.delete()) {
                    Toast.makeText(this, "No se pudo eliminar la foto", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void tomar_foto() {
        Intent intentCapturaFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intentCapturaFoto.resolveActivity(getPackageManager()) != null) {
            String cuenta_dni = preferences.getString("cuenta_dni", "");
            String fecha = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date());
            String nombreArchivo =  cuenta_dni + "_" + fecha + ".jpg";
            archivoFoto = new File(getFilesDir(), nombreArchivo);
            Uri uri = FileProvider.getUriForFile(this, "com.android.jarvis.fileprovider", archivoFoto);
            intentCapturaFoto.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            List<ResolveInfo> cameraActivities = getPackageManager().queryIntentActivities(intentCapturaFoto, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo activity : cameraActivities) {
                grantUriPermission(activity.activityInfo.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            startActivityForResult(intentCapturaFoto, MainActivity.CAPTURA_FOTO);
        }
    }

    private boolean eliminarFotoMemoriaInterna() {
        boolean borro = true;
        File directorioCamera = new File(Environment.getExternalStorageDirectory().getPath()+"/DCIM/Camera");
        File[] filesPorFecha = directorioCamera.listFiles();
        if (filesPorFecha != null && filesPorFecha.length > 0) {
            Arrays.sort(filesPorFecha, new Comparator<File>(){
                @Override
                public int compare(File f1, File f2) {
                    return Long.compare(f1.lastModified(), f2.lastModified());
                }
            });
            borro = filesPorFecha[filesPorFecha.length-1].delete();
        }
        return borro;
    }

}
