package com.android.jarvis.reconocedor;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Audio {

    private File archivo;
    private AudioFormato formato;
    private Datos datos;
    private AudioRecord recorder;
    private Context contexto;

    public Audio(Context context, String rutaArchivo) {
        archivo = new File(rutaArchivo);
        recorder = null;
        contexto = context;
    }

    public Audio() {

    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public File getArchivo() {
        return archivo;
    }

    public AudioFormato getFormato() {
        return formato;
    }

    public Datos getDatos() {
        return datos;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public void setFormato(int sampleRate, int sampleSizeInBits, int channels, int audioEncoding, boolean bigEndian) {
        this.formato = new AudioFormato(sampleRate, sampleSizeInBits, channels, audioEncoding, bigEndian);
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public boolean igualFormato(Audio audio) {
        return (this.formato.getSampleRate() == audio.getFormato().getSampleRate() &&
                this.formato.getSampleSizeInBits() == audio.getFormato().getSampleSizeInBits() &&
                this.formato.getChannels() == audio.getFormato().getChannels() &&
                this.formato.getAudioEncoding() == audio.getFormato().getAudioEncoding() &&
                this.formato.isBigEndian() == audio.getFormato().isBigEndian());
    }

    public void abrirAudio(File archivoEntrada) {
        try {
            archivo = archivoEntrada;
            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(archivo)));
            byte[] bufferHeader = new byte[44];
            dis.read(bufferHeader);
            int sampleRate = Datos.byteArrayAInt(Arrays.copyOfRange(bufferHeader, 24, 27), false);
            int sampleSizeInBits = Datos.byteArrayAInt(Arrays.copyOfRange(bufferHeader, 34, 35), false);
            int channels = Datos.byteArrayAInt(Arrays.copyOfRange(bufferHeader, 22, 23), false);
            int audioEncoding = Datos.byteArrayAInt(Arrays.copyOfRange(bufferHeader, 20, 21), false);
            if(audioEncoding == 1 || audioEncoding == 2) {
                audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
            }
            formato = new AudioFormato(sampleRate, sampleSizeInBits, channels, audioEncoding, false);
            int nBytesArchivo = Datos.byteArrayAInt(Arrays.copyOfRange(bufferHeader, 40, 43), formato.isBigEndian());
            datos = new Datos(nBytesArchivo, formato.isBigEndian());
            dis.read(datos.getBits());
            datos.convertirByteADouble();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AudioRecord grabarAudio() {
        int sampleRate = formato.getSampleRate();
        int channels = formato.getChannels();
        int audioEncoding = formato.getAudioEncoding();
        int bufferSize = AudioRecord.getMinBufferSize(sampleRate, channels, audioEncoding);
        AudioRecord record = new AudioRecord(MediaRecorder.AudioSource.MIC , sampleRate, channels, audioEncoding, bufferSize);
        if (record.getState() == 1) {
            recorder = record;
        } else {
            recorder = null;
        }
        return recorder;
    }

    public void guardarAudio(String rutaArchivoTemporal) {
        long sampleRate = formato.getSampleRate();
        int channels = formato.getChannels();
        long byteRate = formato.getSampleSizeInBits() * formato.getSampleRate() * channels / 8;
        byte[] data = new byte[2048];
        try {
            FileInputStream in = new FileInputStream(rutaArchivoTemporal);
            FileOutputStream out = new FileOutputStream(archivo.getPath());
            long totalAudioLen = in.getChannel().size();
            long totalDataLen = totalAudioLen + 36;
            WriteWaveFileHeader(formato, out, totalAudioLen, totalDataLen, sampleRate, channels, byteRate);
            while (in.read(data) != -1) {
                out.write(data);
            }
            in.close();
            out.close();
            File file = new File(rutaArchivoTemporal);
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void WriteWaveFileHeader(AudioFormato formato, FileOutputStream out, long totalAudioLen, long totalDataLen,
                                           long longSampleRate, int channels, long byteRate) throws IOException {
        //http://www.topherlee.com/software/pcm-tut-wavformat.html
        byte[] header = new byte[44];

        header[0] = 'R'; // RIFF/WAVE header
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        header[12] = 'f'; // 'fmt ' chunk
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';
        header[16] = 16; // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        header[20] = 1; // format = 2
        header[21] = 0;
        header[22] = (byte) channels;
        header[23] = 0;
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        header[32] = (byte) (formato.getChannels() * 16 / 8); // block align
        header[33] = 0;
        header[34] = (byte) formato.getSampleSizeInBits(); // bits per sample
        header[35] = 0;
        header[36] = 'd';
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);

        out.write(header, 0, 44);
    }

}
