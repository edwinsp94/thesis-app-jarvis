package com.android.jarvis.gui.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jarvis.R;
import com.android.jarvis.app.Cronometro;
import com.android.jarvis.app.MiProgressDialog;
import com.android.jarvis.gui.activities.MainActivity;
import com.android.jarvis.modelos.Acceso;
import com.android.jarvis.modelos.Cuenta;
import com.android.jarvis.reconocedor.Algoritmo;
import com.android.jarvis.reconocedor.ArchivoMFCC;
import com.android.jarvis.reconocedor.Audio;
import com.android.jarvis.reconocedor.Datos;
import com.android.jarvis.reconocedor.ThreadGrabar;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class IdentificacionFragment extends Fragment implements View.OnClickListener {

    private Callbacks callbacks;
    private Cronometro cronometro;
    private ImageButton imageButtonAceptar;
    private ImageButton imageButtonCancelar;
    private ImageButton imageButtonGrabar;
    private ImageView imageViewFotoUsuario;
    private FloatingActionButton fabCamara;
    private TextView textViewNombreUsuario;
    private View view;
    private Audio audioVoz;
    private ThreadGrabar threadGrabar;
    private SharedPreferences preferences;
    private String sexo;

    public TextView textViewTiempo;
    public MainActivity mainActivity;
    public boolean grabando;
    public boolean hayEnvio;
    private MiProgressDialog miProgressDialog;

    public IdentificacionFragment() {
        this.cronometro = new Cronometro(this);
        this.view = null;
        this.audioVoz = null;
        this.threadGrabar = null;
        this.preferences = null;
    }

    public interface Callbacks {
        void tomar_foto();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbacks = (Callbacks) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_identificacion, container, false);
            imageButtonAceptar = view.findViewById(R.id.FragmentIdentificacion_imageButton_aceptar);
            imageButtonCancelar = view.findViewById(R.id.FragmentIdentificacion_imageButton_cancelar);
            imageButtonGrabar = view.findViewById(R.id.FragmentIdentificacion_imageButton_grabar);
            imageViewFotoUsuario = view.findViewById(R.id.FragmentIdentificacion_imageView_foto_usuario);
            fabCamara = view.findViewById(R.id.FragmentIdentificacion_fab_camara);
            textViewNombreUsuario = view.findViewById(R.id.FragmentIdentificacion_textView_nombre_usuario);
            textViewTiempo = view.findViewById(R.id.FragmentIdentificacion_textView_tiempo);
            imageButtonGrabar.setOnClickListener(this);
            imageButtonAceptar.setOnClickListener(this);
            imageButtonCancelar.setOnClickListener(this);
            fabCamara.setOnClickListener(this);
        }
        sexo = "";
        preferences = mainActivity.getSharedPreferences("PreferencesJarvis", Context.MODE_PRIVATE);
        String cuenta_dni = preferences.getString("cuenta_dni", "");
        if (!cuenta_dni.equals("")) {
            Cuenta cuenta = MainActivity.realm.where(Cuenta.class).equalTo("dni", cuenta_dni).findFirst();
            if (cuenta != null) {
                setImageViewFotoUsuario(cuenta.getFoto());
                fabCamara.setVisibility(View.VISIBLE);
                textViewNombreUsuario.setVisibility(View.VISIBLE);
                textViewNombreUsuario.setText(cuenta.getNombre());
                sexo = cuenta.getSexo();
            }
        } else {
            Toast.makeText(mainActivity, "Por favor, diga su clave", Toast.LENGTH_SHORT).show();
            if (MainActivity.puedoHablar) {
                MainActivity.speak("Por favor, diga su clave");
            }
        }
        grabando = false;
        hayEnvio = false;
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.FragmentIdentificacion_imageButton_grabar:
                clickImageButtonGrabar();
                break;
            case R.id.FragmentIdentificacion_imageButton_aceptar:
                clickImageButtonAceptar();
                break;
            case R.id.FragmentIdentificacion_imageButton_cancelar:
                clickImageButtonCancelar();
                break;
            case R.id.FragmentIdentificacion_fab_camara:
                callbacks.tomar_foto();
                break;
        }
    }

    private void clickImageButtonGrabar() {
        if (grabando) {
            pararGrabacion();
        } else {
            File temporal = new File(mainActivity.getFilesDir().getPath() + "/temp.raw");
            if (temporal.exists()) {
                if (!temporal.delete()) {
                    Toast.makeText(mainActivity, "No se pudo eliminar, temp.raw", Toast.LENGTH_SHORT).show();
                }
            }
            File mfcc = new File(mainActivity.getFilesDir().getPath() + "/grabacion.mfcc");
            if (mfcc.exists()) {
                if (!mfcc.delete()) {
                    Toast.makeText(mainActivity, "No se pudo eliminar, grabacion.mfcc", Toast.LENGTH_SHORT).show();
                }
            }
            File audio = new File(mainActivity.getFilesDir().getPath() + "/grabacion.wav");
            if (audio.exists()) {
                if (!audio.delete()) {
                    Toast.makeText(mainActivity, "No se pudo eliminar, grabacion.wav", Toast.LENGTH_SHORT).show();
                }
            }
            audioVoz = null;
            audioVoz = new Audio(mainActivity, mainActivity.getFilesDir().getPath()+"/grabacion.wav");
            audioVoz.setFormato(16000, 16, 1, AudioFormat.ENCODING_PCM_16BIT, false);
            if (preferences.getString("ruido", "").equals("true")) {
                String formato = 16000.0+"_"+16+"_"+1+"_"+false;
                MainActivity.cliente.enviarInformacion("empezar@"+MainActivity.cliente.getOtroUsuario()+"@"+formato);
            }
            AudioRecord audioRecord =  audioVoz.grabarAudio();
            if (audioRecord != null) {
                audioRecord.startRecording();
                threadGrabar = new ThreadGrabar(audioRecord);
                threadGrabar.setFileOutputStream(threadGrabar.crearArchivoTemporal(getContext()));
                threadGrabar.start();
                grabando = true;
                hayEnvio = false;
                imageButtonGrabar.setImageResource(R.drawable.ic_pause_white);
                textViewTiempo.setVisibility(View.VISIBLE);
                cronometro.empezar(0, -1, "00:10");
            } else {
                Toast.makeText(getContext(), "Micrófono no disponible", Toast.LENGTH_SHORT).show();
                if (MainActivity.puedoHablar) {
                    MainActivity.speak("Micrófono no disponible");
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void clickImageButtonAceptar() {
        if (audioVoz != null && audioVoz.getDatos() != null && audioVoz.getDatos().getBits() != null) {
            final String cuenta_dni = preferences.getString("cuenta_dni", "");
            if (cuenta_dni.equals("")) {
                new AsyncTask<Void, Void, Void>() {
                    @SuppressLint("WrongThread")
                    @Override
                    protected Void doInBackground(Void... voids) {
                        audioVoz.guardarAudio(mainActivity.getFilesDir().getPath() + "/temp.raw");
                        audioVoz.getDatos().convertirByteADouble();
                        final ArchivoMFCC archivoMFCC;
                        if (preferences.getString("ruido", "").equals("false")) {
                            archivoMFCC = Algoritmo.crearArchivoMFCC(audioVoz, null, "");
                        } else {
                            Audio audioRuido = new Audio();
                            audioRuido.abrirAudio(new File(Objects.requireNonNull(getContext()).getFilesDir().getPath()+"/ruido.wav"));
                            archivoMFCC = Algoritmo.crearArchivoMFCC(audioVoz, audioRuido, "");
                        }
                        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArchivoMFCC archivoMFCCParecido = Algoritmo.obtenerAudioParecido(archivoMFCC,mainActivity.getFilesDir().getPath()+"/MFCC/Patrones");
                                if (archivoMFCCParecido != null) {
                                    String decision = Algoritmo.tomaDeDecision(archivoMFCC, archivoMFCCParecido, "locutor");
                                    if (decision.equals("no identificacion")) {
                                        Toast.makeText(mainActivity, "Lo siento, no puedo identificarte", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(mainActivity, "Por favor, inténtalo nuevamente", Toast.LENGTH_SHORT).show();
                                        if (MainActivity.puedoHablar) {
                                            MainActivity.speak("Lo siento, no puedo identificarte. Por favor, inténtalo nuevamente");
                                        }
                                    } else {
                                        Cuenta cuenta = MainActivity.realm.where(Cuenta.class).equalTo("dni", archivoMFCCParecido.getUsuario()).findFirst();
                                        if (cuenta != null) {
                                            if (decision.equals("falsa identificacion")) {
                                                if (cuenta.getSexo().equals("Masculino")) {
                                                    Toast.makeText(mainActivity, "Usted no es el señor, "+cuenta.getNombre().split(" ")[0], Toast.LENGTH_SHORT).show();
                                                    if (MainActivity.puedoHablar) {
                                                        MainActivity.speak("Usted no es el señor, "+cuenta.getNombre().split(" ")[0]);
                                                    }
                                                } else {
                                                    Toast.makeText(mainActivity, "Usted no es la señora, "+cuenta.getNombre().split(" ")[0], Toast.LENGTH_SHORT).show();
                                                    if (MainActivity.puedoHablar) {
                                                        MainActivity.speak("Usted no es la señora, "+cuenta.getNombre().split(" ")[0]);
                                                    }
                                                }
                                            } else {
                                                if (cuenta.getTipo().equals("Sin Acceso")) {
                                                    Toast.makeText(mainActivity, "Lo sentimos, pero usted ya no tiene acceso", Toast.LENGTH_SHORT).show();
                                                    if (MainActivity.puedoHablar) {
                                                        MainActivity.speak("Lo sentimos, pero usted ya no tiene acceso");
                                                    }
                                                } else {
                                                    mainActivity.menuItemIniciarSesion.setIcon(R.drawable.ic_menu_record_voice);
                                                    mainActivity.menuItemIniciarSesion.setTitle("Comandos por Voz");
                                                    setImageViewFotoUsuario(cuenta.getFoto());
                                                    fabCamara.setVisibility(View.VISIBLE);
                                                    textViewNombreUsuario.setVisibility(View.VISIBLE);
                                                    textViewNombreUsuario.setText(cuenta.getNombre());
                                                    mainActivity.setTitle("Comandos por Voz");
                                                    mainActivity.menuItemCerrarSesion.setEnabled(true);
                                                    if (cuenta.getTipo().equals("Administrador")) {
                                                        mainActivity.menuItemAdministrarCuentas.setVisible(true);
                                                        mainActivity.menuItemConsultarAccesos.setVisible(true);
                                                    }
                                                    String[] fecha = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date()).split("_");
                                                    Acceso acceso = new Acceso(fecha[0]+"/"+fecha[1]+"/"+fecha[2], fecha[3]+":"+fecha[4], cuenta.getDni());
                                                    MainActivity.realm.beginTransaction();
                                                    MainActivity.realm.copyToRealm(acceso);
                                                    cuenta.getAccesos().add(acceso);
                                                    MainActivity.realm.commitTransaction();
                                                    SharedPreferences.Editor editor = preferences.edit();
                                                    editor.putString("cuenta_dni", cuenta.getDni());
                                                    editor.apply();
                                                    sexo = cuenta.getSexo();
                                                    if (sexo.equals("Masculino")) {
                                                        Toast.makeText(mainActivity, "Bienvenido señor", Toast.LENGTH_SHORT).show();
                                                        if (MainActivity.puedoHablar) {
                                                            MainActivity.speak("Bienvenido señor, "+cuenta.getNombre().split(" ")[0]);
                                                        }
                                                    } else {
                                                        Toast.makeText(mainActivity, "Bienvenido señora", Toast.LENGTH_SHORT).show();
                                                        if (MainActivity.puedoHablar) {
                                                            MainActivity.speak("Bienvenida señora, "+cuenta.getNombre().split(" ")[0]);
                                                        }
                                                    }
                                                    if (mainActivity.arduino.conectado) {
                                                        mainActivity.arduino.enviar("abrir");
                                                        SystemClock.sleep(500);
                                                        mainActivity.arduino.enviar("cerrar");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    Toast.makeText(mainActivity, "No hay patrones en la base de datos", Toast.LENGTH_SHORT).show();
                                    if (MainActivity.puedoHablar) {
                                        MainActivity.speak("No hay patrones en la base de datos");
                                    }
                                }
                            }
                        });
                        return null;
                    }

                    @Override
                    protected void onPreExecute() {
                        miProgressDialog = new MiProgressDialog(getContext(), "Iniciando Sesión", "Por favor, espere un momento...");
                        miProgressDialog.iniciar();
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        miProgressDialog.detener();
                    }
                }.execute();
            } else {
                new AsyncTask<Void, Void, Void>() {
                    @SuppressLint("WrongThread")
                    @Override
                    protected Void doInBackground(Void... voids) {
                        audioVoz.guardarAudio(mainActivity.getFilesDir().getPath() + "/temp.raw");
                        audioVoz.getDatos().convertirByteADouble();
                        final ArchivoMFCC archivoMFCC;
                        if (preferences.getString("ruido", "").equals("false")) {
                            archivoMFCC = Algoritmo.crearArchivoMFCC(audioVoz, null, "");
                        } else {
                            Audio audioRuido = new Audio();
                            audioRuido.abrirAudio(new File(Objects.requireNonNull(getContext()).getFilesDir().getPath()+"/ruido.wav"));
                            archivoMFCC = Algoritmo.crearArchivoMFCC(audioVoz, audioRuido, "");
                        }
                        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArchivoMFCC archivoMFCCParecido = Algoritmo.obtenerAudioParecido(archivoMFCC,mainActivity.getFilesDir().getPath()+"/MFCC/Comandos/"+cuenta_dni);
                                if (archivoMFCCParecido != null) {
                                    String decision = Algoritmo.tomaDeDecision(archivoMFCC, archivoMFCCParecido, "palabra");
                                    if (decision.equals("no identificacion")) {
                                        Toast.makeText(mainActivity, "Lo siento, no puedo reconocer el comando", Toast.LENGTH_SHORT).show();
                                        Toast.makeText(mainActivity, "Por favor, inténtalo nuevamente", Toast.LENGTH_SHORT).show();
                                        if (MainActivity.puedoHablar) {
                                            MainActivity.speak("Lo siento, no puedo reconocer el comando. Por favor, inténtalo nuevamente");
                                        }
                                    } else {
                                        if (archivoMFCCParecido.getPalabra().toLowerCase().equals("jarvis")) {
                                            if (sexo.equals("Masculino")) {
                                                Toast.makeText(mainActivity, "Dígame señor", Toast.LENGTH_SHORT).show();
                                                if (MainActivity.puedoHablar) {
                                                    MainActivity.speak("Dígame señor");
                                                }
                                            } else {
                                                Toast.makeText(mainActivity, "Dígame señora", Toast.LENGTH_SHORT).show();
                                                if (MainActivity.puedoHablar) {
                                                    MainActivity.speak("Dígame señora");
                                                }
                                            }
                                        } else {
                                            if (archivoMFCCParecido.getPalabra().toLowerCase().equals("encender")) {
                                                if (mainActivity.arduino.conectado) {
                                                    mainActivity.arduino.enviar("led1");
                                                }
                                                Toast.makeText(mainActivity, "Luz encendida", Toast.LENGTH_SHORT).show();
                                                if (MainActivity.puedoHablar) {
                                                    MainActivity.speak("Luz encendida");
                                                }
                                            } else if (archivoMFCCParecido.getPalabra().toLowerCase().equals("apagar")) {
                                                if (mainActivity.arduino.conectado) {
                                                    mainActivity.arduino.enviar("led0");
                                                }
                                                Toast.makeText(mainActivity, "Luz apagada", Toast.LENGTH_SHORT).show();
                                                if (MainActivity.puedoHablar) {
                                                    MainActivity.speak("Luz apagada");
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    Toast.makeText(mainActivity, "No hay comandos en la base de datos", Toast.LENGTH_SHORT).show();
                                    if (MainActivity.puedoHablar) {
                                        MainActivity.speak("No hay comandos en la base de datos");
                                    }
                                }
                            }
                        });
                        return null;
                    }

                    @Override
                    protected void onPreExecute() {
                        miProgressDialog = new MiProgressDialog(getContext(), "Reconociendo Comando", "Por favor, espere un momento...");
                        miProgressDialog.iniciar();
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        miProgressDialog.detener();
                    }
                }.execute();
            }
        } else {
            Toast.makeText(mainActivity, "Ocurrió un problema en la grabación", Toast.LENGTH_SHORT).show();
            Toast.makeText(mainActivity, "Por favor, inténtalo nuevamente", Toast.LENGTH_SHORT).show();
            if (MainActivity.puedoHablar) {
                MainActivity.speak("Ocurrió un problema en la grabación. Por favor, inténtalo nuevamente.");
            }
        }
        imageButtonGrabar.setEnabled(true);
        imageButtonAceptar.setVisibility(View.INVISIBLE);
        imageButtonCancelar.setVisibility(View.INVISIBLE);
        textViewTiempo.setVisibility(View.INVISIBLE);
        textViewTiempo.setText("00:00");
        hayEnvio = false;
    }

    private void clickImageButtonCancelar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("¿Desea cancelar el envio?");
        builder.setMessage("Si cancela, el comando de voz no se enviará.");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                File file = new File(mainActivity.getFilesDir().getPath() + "/temp.raw");
                if (file.exists() && file.delete()) {
                    imageButtonGrabar.setEnabled(true);
                    imageButtonAceptar.setVisibility(View.INVISIBLE);
                    imageButtonCancelar.setVisibility(View.INVISIBLE);
                    textViewTiempo.setVisibility(View.INVISIBLE);
                    textViewTiempo.setText("00:00");
                    hayEnvio = false;
                    Toast.makeText(mainActivity, "Comando de voz cancelado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mainActivity, "No se pudo eliminar temp.raw", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    public void pararGrabacion() {
        grabando = false;
        hayEnvio = true;
        cronometro.parar();
        if (preferences.getString("ruido", "").equals("true")) {
            MainActivity.cliente.context = this.getContext();
            MainActivity.cliente.enviarInformacion("parar@"+MainActivity.cliente.getOtroUsuario());
        }
        threadGrabar.setStopGrabar(true);
        audioVoz.setDatos(new Datos(threadGrabar.getByteArrayOutputStream().toByteArray(), audioVoz.getFormato().isBigEndian()));
        threadGrabar = null;
        imageButtonGrabar.setImageResource(R.drawable.ic_mic);
        imageButtonGrabar.setEnabled(false);
        imageButtonAceptar.setVisibility(View.VISIBLE);
        imageButtonCancelar.setVisibility(View.VISIBLE);
    }

    public void setImageViewFotoUsuario(String rutaImagen) {
        Picasso.get().load(new File(rutaImagen)).fit().into(imageViewFotoUsuario);
    }

    public void cancelarGrabacion(final Fragment fragmentNew, final String nameFragment, final String titleFragment, final boolean cerrarSesion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("¿Desea cancelar la grabación?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pararGrabacion();
                File file = new File(mainActivity.getFilesDir().getPath() + "/temp.raw");
                if (file.delete()) {
                    Toast.makeText(mainActivity, "Grabación cancelada", Toast.LENGTH_SHORT).show();
                    imageButtonGrabar.setEnabled(true);
                    imageButtonAceptar.setVisibility(View.INVISIBLE);
                    imageButtonCancelar.setVisibility(View.INVISIBLE);
                    textViewTiempo.setVisibility(View.INVISIBLE);
                    textViewTiempo.setText("00:00");
                    hayEnvio = false;
                    if (cerrarSesion) {
                        mainActivity.cerrarSesion();
                    }
                    insertarFragmento(fragmentNew, nameFragment, titleFragment);
                } else {
                    Toast.makeText(mainActivity, "No se pudo eliminar temp.raw", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    public void cancelarEnvio(final Fragment fragmentNew, final String nameFragment, final String titleFragment, final boolean cerrarSesion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("¿Desea cancelar el envio?");
        builder.setMessage("Si cancela, el comando de voz no se enviará.");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                File file = new File(mainActivity.getFilesDir().getPath() + "/temp.raw");
                if (file.delete()) {
                    Toast.makeText(mainActivity, "Comando de voz cancelado", Toast.LENGTH_SHORT).show();
                    imageButtonGrabar.setEnabled(true);
                    imageButtonAceptar.setVisibility(View.INVISIBLE);
                    imageButtonCancelar.setVisibility(View.INVISIBLE);
                    textViewTiempo.setVisibility(View.INVISIBLE);
                    textViewTiempo.setText("00:00");
                    hayEnvio = false;
                    if (cerrarSesion) {
                        mainActivity.cerrarSesion();
                    }
                    insertarFragmento(fragmentNew, nameFragment, titleFragment);
                } else {
                    Toast.makeText(mainActivity, "No se pudo eliminar temp.raw", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }

    private void insertarFragmento(Fragment fragmentNew, String nameFragment, String titleFragment){
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(titleFragment);
            MainActivity.FRAGMENT_ACTUAL = nameFragment;
            FragmentManager fm = activity.getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fragment fragmentOld = fm.findFragmentById(R.id.mainFrame);
            if (fragmentOld != null) {
                ft.remove(fragmentOld);
            }
            ft.add(R.id.mainFrame, fragmentNew);
            ft.commit();
        }
    }

}
