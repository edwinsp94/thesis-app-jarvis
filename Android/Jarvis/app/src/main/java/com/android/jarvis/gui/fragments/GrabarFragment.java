package com.android.jarvis.gui.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jarvis.R;
import com.android.jarvis.app.Cronometro;
import com.android.jarvis.gui.activities.MainActivity;
import com.android.jarvis.modelos.Grabacion;
import com.android.jarvis.reconocedor.Algoritmo;
import com.android.jarvis.reconocedor.Audio;
import com.android.jarvis.reconocedor.Datos;
import com.android.jarvis.reconocedor.LMS;
import com.android.jarvis.reconocedor.Preprocesamiento;
import com.android.jarvis.reconocedor.ThreadGrabar;
import com.android.jarvis.reconocedor.Utilitarios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class GrabarFragment extends Fragment implements View.OnClickListener {

    private ImageButton imageButtonDetener;
    private ImageButton imageButtonGrabar;
    private ImageButton imageButtonReproducir;
    private ImageView imageViewEstado;
    private LinearLayout linearLayoutEncabezado;
    private LinearLayout linearLayoutOpciones;
    private String duracionGrabacion;
    private TextView textViewNombreArchivo;
    private Cronometro cronometro;
    private Audio audioVoz;
    private ThreadGrabar threadGrabar;
    private MediaPlayer mpTemporal;
    private SharedPreferences preferences;
    private String nombreArchivo;
    private int mpPosicion;
    private boolean estaReproduciendo;
    private boolean grabando;
    private boolean guardado;

    public Grabacion grabacion;
    public TextView textViewTiempo;
    public String URI;

    public GrabarFragment() {
        this.cronometro = new Cronometro(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grabar, container, false);
        ImageButton imageButtonDescartar = view.findViewById(R.id.FragmentGrabar_imageButton_descartar);
        imageButtonDetener = view.findViewById(R.id.FragmentGrabar_imageButton_detener);
        imageButtonGrabar = view.findViewById(R.id.FragmentGrabar_imageButton_grabar);
        ImageButton imageButtonGuardar = view.findViewById(R.id.FragmentGrabar_imageButton_guardar);
        imageButtonReproducir = view.findViewById(R.id.FragmentGrabar_imageButton_reproducir);
        imageViewEstado = view.findViewById(R.id.FragmentGrabar_imageView_estado);
        linearLayoutEncabezado = view.findViewById(R.id.FragmentGrabar_linearLayout_1);
        linearLayoutOpciones = view.findViewById(R.id.FragmentGrabar_linearLayout_2);
        textViewNombreArchivo = view.findViewById(R.id.FragmentGrabar_textView_nombre_archivo);
        textViewTiempo = view.findViewById(R.id.FragmentGrabar_textView_tiempo);
        imageButtonDescartar.setOnClickListener(this);
        imageButtonDetener.setOnClickListener(this);
        imageButtonGrabar.setOnClickListener(this);
        imageButtonGuardar.setOnClickListener(this);
        imageButtonReproducir.setOnClickListener(this);
        imageButtonReproducir.setEnabled(false);
        preferences = Objects.requireNonNull(getContext()).getSharedPreferences("PreferencesJarvis", Context.MODE_PRIVATE);
        mpTemporal = null;
        nombreArchivo = "";
        mpPosicion = 0;
        estaReproduciendo = false;
        grabando = false;
        guardado = false;
        if (grabacion != null) {
            textViewNombreArchivo.setVisibility(View.VISIBLE);
            textViewNombreArchivo.setText(grabacion.getNombre());
            imageViewEstado.setVisibility(View.VISIBLE);
            imageViewEstado.setImageResource(R.drawable.ic_play_black);
            imageButtonReproducir.setEnabled(true);
            imageButtonReproducir.setImageResource(R.drawable.ic_pause_black);
            imageButtonDetener.setImageResource(R.drawable.ic_stop);
            duracionGrabacion = grabacion.getDuracion();
            nombreArchivo += Objects.requireNonNull(getContext()).getFilesDir().getPath();
            if (URI.contains("/")) {
                nombreArchivo += "/Audios/Comandos/" + URI + "/" + grabacion.getNombre();
            } else {
                nombreArchivo += "/Audios/Patrones/" + URI + "/" + grabacion.getNombre();
            }
            mpTemporal = MediaPlayer.create(getContext(), Uri.parse(String.valueOf(Uri.fromFile(new File(nombreArchivo)))));
            mpTemporal.start();
            cronometro.empezar(0, -1, duracionGrabacion);
            estaReproduciendo = true;
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.FragmentGrabar_imageButton_descartar:
                clickImageButtonDescartar();
                break;
            case R.id.FragmentGrabar_imageButton_guardar:
                clickImageButtonGuardar();
                break;
            case R.id.FragmentGrabar_imageButton_grabar:
                clickImageButtonGrabar();
                break;
            case R.id.FragmentGrabar_imageButton_reproducir:
                clickImageButtonReproducir();
                break;
            case R.id.FragmentGrabar_imageButton_detener:
                clickImageButtonDetener();
                break;
        }
    }

    private void clickImageButtonDescartar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setTitle("¿Desea cancelar la grabacion?");
        builder.setMessage("Si cancela, la grabacion no se guardará.");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                File ruido = new File(Objects.requireNonNull(getContext()).getFilesDir().getPath() + "/ruido.wav");
                if (ruido.exists()) {
                    if (!ruido.delete()) {
                        Toast.makeText(getContext(), "Archivo ruido.wav no eliminado", Toast.LENGTH_SHORT).show();
                    }
                }
                if (new File(nombreArchivo).delete()) {
                    grabando = false;
                    guardado = false;
                    textViewTiempo.setText("00:00");
                    linearLayoutEncabezado.setVisibility(View.INVISIBLE);
                    imageViewEstado.setVisibility(View.INVISIBLE);
                    textViewNombreArchivo.setVisibility(View.INVISIBLE);
                    linearLayoutOpciones.setVisibility(View.INVISIBLE);
                    imageButtonReproducir.setImageResource(R.drawable.ic_play_white);
                    imageButtonReproducir.setEnabled(false);
                    imageButtonGrabar.setImageResource(R.drawable.ic_record_red);
                    imageButtonGrabar.setEnabled(true);
                    imageButtonDetener.setImageResource(R.drawable.ic_list);
                    Toast.makeText(getActivity(), "Grabacion cancelada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Archivo .wav no eliminado", Toast.LENGTH_SHORT).show();
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

    private void clickImageButtonGuardar() {
        audioVoz.getDatos().convertirByteADouble();
        if (preferences.getString("ruido", "").equals("true")) {
            double[] senalEntrada = Preprocesamiento.normalizar(audioVoz.getDatos().getAmplitudes(), 32768);
            Audio audioRuido = new Audio();
            audioRuido.abrirAudio(new File(Objects.requireNonNull(getContext()).getFilesDir().getPath() + "/ruido.wav"));
            double[] senalRuido = Preprocesamiento.normalizar(audioRuido.getDatos().getAmplitudes(), 32768);
            LMS lms = new LMS(senalEntrada, senalRuido);
            lms.NLMS(0.0025, 128, null);
            double[] senalFinal = lms.getSenalDeseada();
            double max = Utilitarios.getMaximo(Utilitarios.getMayor(senalFinal), Utilitarios.getMenor(senalFinal));
            senalFinal = Preprocesamiento.normalizarInt(senalFinal, -max, max, -32768, 32767);
            if (audioVoz.getArchivo().delete() && audioRuido.getArchivo().delete()) {
                audioVoz = null;
                Audio audio = new Audio();
                audio.setDatos(new Datos(senalFinal, false));
                audio.setFormato(16000, 16, 1, AudioFormat.ENCODING_PCM_16BIT , false);
                audio.setContexto(getContext());
                audio.setArchivo(new File(nombreArchivo));
                audio.getDatos().convertirDoubleAByte();
                try {
                    FileOutputStream out = new FileOutputStream(audio.getArchivo());
                    long totalAudioLen = audio.getDatos().getBits().length;
                    long totalDataLen = totalAudioLen + 36;
                    Audio.WriteWaveFileHeader(audio.getFormato(), out, totalAudioLen, totalDataLen, audio.getFormato().getSampleRate(), audio.getFormato().getChannels(), audio.getFormato().getSampleSizeInBits());
                    out.write(audio.getDatos().getBits());
                    out.close();
                    Algoritmo.crearArchivoMFCC(audio, null, URI);
                    Toast.makeText(getContext(), "Grabación guardada", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Algoritmo.crearArchivoMFCC(audioVoz, null, URI);
            Toast.makeText(getContext(), "Grabación guardada", Toast.LENGTH_SHORT).show();
        }
        textViewTiempo.setText("00:00");
        imageViewEstado.setVisibility(View.INVISIBLE);
        textViewNombreArchivo.setVisibility(View.INVISIBLE);
        linearLayoutOpciones.setVisibility(View.INVISIBLE);
        imageButtonReproducir.setImageResource(R.drawable.ic_play_white);
        imageButtonReproducir.setEnabled(false);
        imageButtonGrabar.setImageResource(R.drawable.ic_record_red);
        imageButtonGrabar.setEnabled(true);
        imageButtonDetener.setImageResource(R.drawable.ic_list);
        grabando = false;
        guardado = true;
    }

    private void clickImageButtonGrabar() {
        if (!nombreArchivo.equals("") && grabacion == null && !guardado) {
            File file = new File(nombreArchivo);
            if (file.exists() && file.delete()) {
                nombreArchivo = "";
            }
        }
        audioVoz = null;
        nombreArchivo = Objects.requireNonNull(getContext()).getFilesDir().getPath();
        String fecha = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault()).format(new Date());
        if (URI.contains("/")) {
            nombreArchivo += "/Audios/Comandos/" + URI + "/" + fecha + ".wav";
        } else {
            nombreArchivo += "/Audios/Patrones/" + URI + "/" + fecha + ".wav";
        }
        audioVoz = new Audio(getContext(), nombreArchivo);
        audioVoz.setFormato(16000, 16, 1, AudioFormat.ENCODING_PCM_16BIT, false);
        AudioRecord audioRecord =  audioVoz.grabarAudio();
        if (audioRecord != null) {
            if (preferences.getString("ruido", "").equals("true")) {
                String formato = 16000.0+"_"+16+"_"+1+"_"+false;
                MainActivity.cliente.enviarInformacion("empezar@"+MainActivity.cliente.getOtroUsuario()+"@"+formato);
            }
            duracionGrabacion = "";
            linearLayoutEncabezado.setVisibility(View.VISIBLE);
            imageViewEstado.setVisibility(View.INVISIBLE);
            textViewNombreArchivo.setText(nombreArchivo);
            textViewNombreArchivo.setVisibility(View.VISIBLE);
            linearLayoutOpciones.setVisibility(View.INVISIBLE);
            imageButtonReproducir.setImageResource(R.drawable.ic_play_white);
            imageButtonReproducir.setEnabled(false);
            imageButtonGrabar.setImageResource(R.drawable.ic_record_white);
            imageButtonGrabar.setEnabled(false);
            imageButtonDetener.setImageResource(R.drawable.ic_stop);
            estaReproduciendo = false;
            guardado = false;
            grabacion = null;
            audioRecord.startRecording();
            threadGrabar = new ThreadGrabar(audioRecord);
            threadGrabar.setFileOutputStream(threadGrabar.crearArchivoTemporal(getContext()));
            threadGrabar.start();
            grabando = true;
            cronometro.parar();
            cronometro = null;
            cronometro = new Cronometro(this);
            cronometro.empezar(0, -1, "00:10");
        } else {
            Toast.makeText(getContext(), "Micrófono no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    private void clickImageButtonReproducir() {
        if (!estaReproduciendo) {
            String tiempoInicio = textViewTiempo.getText().toString();
            if (tiempoInicio.equals(duracionGrabacion)) {
                mpPosicion = 0;
                cronometro.empezar(0, -1, duracionGrabacion);
            } else {
                String[] tiempo = tiempoInicio.split(":");
                cronometro.empezar(Integer.parseInt(tiempo[0]), Integer.parseInt(tiempo[1]), duracionGrabacion);
            }
            mpTemporal = MediaPlayer.create(getContext(), Uri.parse(String.valueOf(Uri.fromFile(new File(nombreArchivo)))));
            mpTemporal.seekTo(mpPosicion);
            mpTemporal.start();
            imageViewEstado.setImageResource(R.drawable.ic_play_black);
            imageButtonReproducir.setImageResource(R.drawable.ic_pause_black);
            estaReproduciendo = true;
        } else {
            mpPosicion = mpTemporal.getCurrentPosition();
            mpTemporal.pause();
            cronometro.parar();
            imageViewEstado.setImageResource(R.drawable.ic_pause_black);
            imageButtonReproducir.setImageResource(R.drawable.ic_play_black);
            estaReproduciendo = false;
        }
        imageButtonDetener.setImageResource(R.drawable.ic_stop);
        grabando = true;
    }

    private void pararGrabacion() {
        if (estaReproduciendo) {
            mpTemporal.stop();
            mpPosicion = 0;
        }
        cronometro.parar();
        grabando = false;
        estaReproduciendo = false;
        if (duracionGrabacion.equals("")) {
            duracionGrabacion = textViewTiempo.getText().toString();
        } else {
            textViewTiempo.setText(duracionGrabacion);
        }
        if (threadGrabar != null) {
            if (preferences.getString("ruido", "").equals("true")) {
                MainActivity.cliente.context = this.getContext();
                MainActivity.cliente.enviarInformacion("parar@"+MainActivity.cliente.getOtroUsuario());
            }
            threadGrabar.setStopGrabar(true);
            audioVoz.setDatos(new Datos(threadGrabar.getByteArrayOutputStream().toByteArray(), audioVoz.getFormato().isBigEndian()));
            threadGrabar = null;
            if (audioVoz != null && audioVoz.getDatos() != null && audioVoz.getDatos().getBits() != null) {
                audioVoz.guardarAudio(Objects.requireNonNull(getContext()).getFilesDir().getPath() + "/temp.raw");
            }
        }
        linearLayoutEncabezado.setVisibility(View.INVISIBLE);
        imageViewEstado.setImageResource(R.drawable.ic_stop);
        imageViewEstado.setVisibility(View.VISIBLE);
        linearLayoutOpciones.setVisibility(View.VISIBLE);
        imageButtonReproducir.setImageResource(R.drawable.ic_play_black);
        imageButtonReproducir.setEnabled(true);
        imageButtonGrabar.setImageResource(R.drawable.ic_record_red);
        imageButtonGrabar.setEnabled(true);
        imageButtonDetener.setImageResource(R.drawable.ic_list);
    }

    public void clickImageButtonDetener() {
        if (grabacion == null) {
            if (grabando) {
                pararGrabacion();
            } else {
                ListaGrabacionesFragment listaGrabacionesFragment = new ListaGrabacionesFragment();
                listaGrabacionesFragment.URI = URI;
                if (URI.contains("/")) {
                    insertarFragmento(listaGrabacionesFragment,"Comando - "+URI.split("/")[1]);
                } else {
                    insertarFragmento(listaGrabacionesFragment,"Patron de Voz");
                }
            }
        } else {
            if (estaReproduciendo) {
                mpPosicion = 0;
                mpTemporal.stop();
                cronometro.parar();
                textViewTiempo.setText(duracionGrabacion);
                estaReproduciendo = false;
                imageViewEstado.setImageResource(R.drawable.ic_stop);
                imageButtonReproducir.setImageResource(R.drawable.ic_play_black);
                imageButtonDetener.setImageResource(R.drawable.ic_list);
            } else {
                if (grabando) {
                    grabando = false;
                    mpPosicion = 0;
                    mpTemporal.stop();
                    cronometro.parar();
                    textViewTiempo.setText(duracionGrabacion);
                    estaReproduciendo = false;
                    imageViewEstado.setImageResource(R.drawable.ic_stop);
                    imageButtonReproducir.setImageResource(R.drawable.ic_play_black);
                    imageButtonDetener.setImageResource(R.drawable.ic_list);
                } else {
                    ListaGrabacionesFragment listaGrabacionesFragment = new ListaGrabacionesFragment();
                    listaGrabacionesFragment.URI = URI;
                    if (URI.contains("/")) {
                        insertarFragmento(listaGrabacionesFragment,"Comando - "+URI.split("/")[1]);
                    } else {
                        insertarFragmento(listaGrabacionesFragment,"Patron de Voz");
                    }
                }
            }
        }
    }

    private void insertarFragmento(Fragment fragmentNew, String titleFragment) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(titleFragment);
            MainActivity.FRAGMENT_ACTUAL = "ListaGrabacionesFragment";
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

    @Override
    public void onDestroy() {
        if (mpTemporal != null) {
            mpTemporal.release();
            mpTemporal = null;
        }
        if (cronometro != null) {
            cronometro.parar();
            cronometro = null;
        }
        if (grabacion == null && !guardado && !nombreArchivo.equals("")) {
            File file = new File(nombreArchivo);
            if (file.exists() && file.delete()) {
                Toast.makeText(getContext(), "No se guardo la grabación", Toast.LENGTH_SHORT).show();
            }
        }
        audioVoz = null;
        threadGrabar = null;
        super.onDestroy();
    }
}
