package com.android.jarvis.reconocedor;

import android.content.Context;
import android.media.AudioRecord;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadGrabar extends Thread {

    private AudioRecord recorder;
    private ByteArrayOutputStream baos;
    private FileOutputStream fileOutputStream;
    private byte[] buffer;
    private boolean stopGrabar;

    public ThreadGrabar(AudioRecord recorder) {
        this.recorder = recorder;
        this.baos = null;
        this.buffer = null;
        this.stopGrabar = false;
    }

    public ByteArrayOutputStream getByteArrayOutputStream() {
        return baos;
    }

    public void setStopGrabar(boolean stopGrabar) {
        this.stopGrabar = stopGrabar;
    }

    public void setFileOutputStream(String filename) {
        try {
            fileOutputStream = new FileOutputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String crearArchivoTemporal(Context contexto) {
        String rutaArchivoTemporal = contexto.getFilesDir().getPath()+"/temp.raw";
        File tempFile = new File(rutaArchivoTemporal);
        if (tempFile.exists()) {
            tempFile.delete();
        }
        return rutaArchivoTemporal;
    }

    @Override
    public void run() {
        baos = new ByteArrayOutputStream();
        buffer = new byte[2048];
        //Bucle hasta que StopGrabar es "true" entonces el hilo para
        while (!stopGrabar) {
            //Lee datos en el buffer interno desde el controlador de audio
            //y luego los almacena en el buffer externo "buffer"
            int nBytesLeidos = recorder.read(buffer, 0, buffer.length);
            //Guarda los datos del buufer en el objeto de flujo de salida
            if (AudioRecord.ERROR_INVALID_OPERATION != nBytesLeidos) {
                baos.write(buffer, 0, nBytesLeidos);
                try {
                    fileOutputStream.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recorder.stop();
        recorder.release();
        recorder = null;
    }

}
