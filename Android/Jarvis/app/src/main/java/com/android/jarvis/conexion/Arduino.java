package com.android.jarvis.conexion;

import android.widget.Toast;

import com.android.jarvis.gui.activities.MainActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class Arduino implements Runnable {

    private String ip;
    private int puerto;

    private DataOutputStream dos;
    private DataInputStream dis;
    private Socket socket;
    public boolean conectado;
    private MainActivity mainActivity;

    public Arduino(String ip, int puerto, MainActivity mainActivity) {
        this.ip = ip;         //192.168.1.105
        this.puerto = puerto; //8888
        this.dos = null;
        this.dis = null;
        this.socket = null;
        this.conectado = false;
        this.mainActivity = mainActivity;
    }

    public void conectar() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        if (!conectado) {
            try {
                socket = new Socket(ip, puerto);
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
                conectado = true;
                mostarMensaje("Arduino conectado");
            } catch (IOException ex) {
                mostarMensaje("No se pudo conectar al arduino");
            }
        }
    }

    public void enviar(String msg) {
        if (conectado) {
            try {
                dos.writeBytes(msg);
                dos.flush();
            } catch(IOException ex) {
                conectado = false;
                mostarMensaje("No se pudo enviar el comando al arduino");
            }
        }
    }

    public void desconectar() {
        try {
            dos.close();
            dis.close();
            socket.close();
            conectado = false;
            mostarMensaje("Arduino desconectado");
        } catch (IOException ex) {
            mostarMensaje("No se pudo desconectar del arduino");
        }
    }

    private void mostarMensaje(final String msg) {
        Objects.requireNonNull(mainActivity).runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(mainActivity, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

