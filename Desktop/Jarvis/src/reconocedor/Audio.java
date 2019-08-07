package reconocedor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class Audio {
    
    private File archivo;
    private AudioFormat formato;
    private Datos datos;
    
    public Audio() {
    }

    public File getArchivo() {
        return archivo;
    }

    public AudioFormat getFormato() {
        return formato;
    }
    
    public Datos getDatos() {
        return datos;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }
    
    public void setFormato(float sampleRate, int sampleSizeInBits, int channels, 
                           boolean signed, boolean bigEndian) {
        //sampleRate = 8000,11025,16000,22050,44100
        //sampleSizeInBits = 16,8
        //channels = 1,2
        //signed = true,false
        //bigEndian = false,true
        this.formato = new AudioFormat(sampleRate, sampleSizeInBits, channels, 
                                       signed, bigEndian);
    }
    
    public void setDatos(Datos datos) {
        this.datos = datos;
    }
    
    public boolean igualFormato(Audio audio) {
        return (this.formato.getSampleRate() == audio.getFormato().getSampleRate() &&
        this.formato.getSampleSizeInBits() == audio.getFormato().getSampleSizeInBits() &&
        this.formato.getChannels() == audio.getFormato().getChannels() &&
        this.formato.isBigEndian() == audio.getFormato().isBigEndian());
    }
    
    public String abrirAudio(File archivoEntrada) {
        /* 
        1. El objeto flujoEntradaAudio trata al archivo de entrada como un archivo 
           de audio y es util para establecer y leer propiedades de los archivos de audio.
        2. Los archivos de audio se guardan con unidades minimas llamadas frames, 
           el valor de cada frame determina la amplitud del sonido, un frame 
           puede tener 1 byte, 2 bytes, etc.
        3. Se utilizara un buffer especificado por el arreglo audioBuffer para 
           leer los datos del archivo de audio a memoria.
        */
        String info = "";
        int LONGITUD_BUFFER = 1024;
    	try {
            AudioInputStream flujoEntradaAudio = AudioSystem.getAudioInputStream(archivoEntrada);
            archivo = archivoEntrada;
            formato = flujoEntradaAudio.getFormat();
            int bytesPorFrame = formato.getFrameSize();
            byte[] buffer = new byte[LONGITUD_BUFFER*bytesPorFrame];
            int nBytesArchivo = bytesPorFrame*(int)flujoEntradaAudio.getFrameLength();
            datos = new Datos(nBytesArchivo, formato.isBigEndian());
            for (int pos = 0, nBytesLeidos; 
                (nBytesLeidos = flujoEntradaAudio.read(buffer)) != -1; 
                pos += nBytesLeidos) {
                System.arraycopy(buffer, 0, datos.getBits(), pos, nBytesLeidos);
            }
            datos.convertirByteADouble();
            info += archivoEntrada.getName()+"\n\n";
            info += "  NroBytes	Frames	Formato"+ "\n";
            info += "  "+nBytesArchivo+
                    "	"+flujoEntradaAudio.getFrameLength()+
                    "	"+formato.toString()+"\n";            
        } catch (IOException ex) {
            String msj = "No se puede leer los datos!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (UnsupportedAudioFileException ex) {
            String msj = "Audio no soportado!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return info;
    }
    
    public TargetDataLine grabarAudio() {
        TargetDataLine targetDataLine = null;
        try {
            //Obtiene un TargetDataLine del Sistema (Controlador de captura de sonido)
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, formato);
            targetDataLine = (TargetDataLine)AudioSystem.getLine(dataLineInfo);
            //Prepara la linea para usarla
            targetDataLine.open(formato);
            targetDataLine.start();
        } catch (LineUnavailableException ex) {
            String msj = "Controlador de microfono no disponible!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return targetDataLine;
    }
    
    public void reproducirAudio() {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(datos.getBits());
            long nFrameArchivo = datos.getBits().length/formato.getFrameSize();
            AudioInputStream flujoEntradaAudio = new AudioInputStream(bais, formato, nFrameArchivo);
            //Obtiene un SourceDataLine del Sistema (Controlador de sonido)
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, formato);
            SourceDataLine sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(formato);
            sourceDataLine.start();
            byte[] buffer = new byte[10000];
            try {//Lee hasta que el entrada del stream este vacio y retorne -1
                int nBytesLeidos;
                while ((nBytesLeidos = flujoEntradaAudio.read(buffer, 0, buffer.length)) != -1) {
                    if (nBytesLeidos > 0) {
                        //Escribe los datos del buffer interno al dataLine
                        //(Controlador de audio) donde se entregara al portavoz
                        //para su reproduccion
                        sourceDataLine.write(buffer, 0, nBytesLeidos);
                    }
                }
                //Bloquea y espera que el buffer interno
                //vacie los datos y cerramos el dataLine
                sourceDataLine.drain();
                sourceDataLine.close();
            } catch (IOException ex) {
                String msj = "No se puede leer los datos!";
                JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (LineUnavailableException ex) {
            String msj = "Controlador de parlantes no disponible!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void guardarAudio(String nombreArchivo) {
        try {
            File archivoSalida = new File(nombreArchivo);
            ByteArrayInputStream bais = new ByteArrayInputStream(datos.getBits());
            long nFrameArchivo = datos.getBits().length/formato.getFrameSize();
            AudioInputStream flujoEntradaAudio = new AudioInputStream(bais, formato, nFrameArchivo);
            AudioSystem.write(flujoEntradaAudio, AudioFileFormat.Type.WAVE, archivoSalida);
            archivo = archivoSalida;
        } catch (IOException ex) {
            String msj = "No se puede escribir los datos!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
/*
Nota:
    1. Un targetDataLine es un tipo de DataLine, del cual los datos pueden ser 
    leidos, esos datos leidos los almaceno en un byteArrayOutputStream, que es 
    un tipo de datos en el cual puedo write array de datos.

    2. Un audioInputStream es un InputStream (flujo de datos de entrada y por 
    tanto solo pueden ser leidos) con un especifico formato de audio y una 
    longitud (longitud expresada en frames sampled, no em bytes) leo los datos 
    del AudioInputStream y los pongo el el SourceDataLine que tiene un buffer 
    interno, es decir escribir los datos al mixer via al sourceDataLine
*/