package com.android.jarvis.app;

import android.os.Handler;

import com.android.jarvis.gui.fragments.GrabarFragment;
import com.android.jarvis.gui.fragments.IdentificacionFragment;

public class Cronometro implements Runnable {

    private Handler escribirEnUI;
    private GrabarFragment grabarFragment;
    private IdentificacionFragment identificacionFragment;
    private String tiempo;
    private boolean detenido;
    private int segundos, minutos;
    private String duracion;

    public Cronometro(IdentificacionFragment fragment) {
        this.escribirEnUI = new Handler();
        this.grabarFragment = null;
        this.identificacionFragment = fragment;
    }

    public Cronometro(GrabarFragment fragment) {
        this.escribirEnUI = new Handler();
        this.grabarFragment = fragment;
        this.identificacionFragment = null;
    }

    public void empezar(int minutos, int segundos, String duracion) {
        this.tiempo = "";
        this.detenido = false;
        this.minutos = minutos;
        this.segundos = segundos;
        this.duracion = duracion;
        Thread t = new Thread(this);
        t.start();
    }

    public void parar() {
        this.detenido = true;
    }

    @Override
    public void run() {
        try {
            while (!detenido) {
                tiempo = "";
                segundos++;
                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                }
                // Formateo del tiempo
                if (minutos <= 9) {
                    tiempo += "0";
                }
                tiempo += minutos + ":";
                if (segundos <= 9) {
                    tiempo += "0";
                }
                tiempo += segundos;
                // Modifico la UI
                escribirEnUI.post(new Runnable() {
                    @Override
                    public void run() {
                        if (tiempo.equals(duracion)) {
                            if (identificacionFragment != null) {
                                identificacionFragment.textViewTiempo.setText(tiempo);
                                identificacionFragment.pararGrabacion();
                            } else {
                                grabarFragment.textViewTiempo.setText(tiempo);
                                grabarFragment.clickImageButtonDetener();
                            }
                        } else {
                            if (identificacionFragment != null) {
                                identificacionFragment.textViewTiempo.setText(tiempo);
                            } else {
                                grabarFragment.textViewTiempo.setText(tiempo);
                            }
                        }
                    }
                });
                if (!detenido) {
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
