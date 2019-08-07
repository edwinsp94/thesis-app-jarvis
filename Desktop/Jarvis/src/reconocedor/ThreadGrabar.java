package reconocedor;

import java.io.ByteArrayOutputStream;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class ThreadGrabar extends Thread {
    
    private final TargetDataLine targetDataLine;
    private ByteArrayOutputStream baos;
    private byte[] buffer;
    private boolean stopGrabar;

    public ThreadGrabar(TargetDataLine targetDataLine) {
        this.targetDataLine = targetDataLine;
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

    @Override
    public void run() {
        baos = new ByteArrayOutputStream();
        buffer = new byte[10000];
        int nBytesLeidos;
        //Bucle hasta que StopGrabar es "true" entonces el hilo para
        while (!stopGrabar) {
            //Lee datos en el buffer interno desde el controlador de audio
            //y luego los almacena en el buffer externo "buffer"
            nBytesLeidos = targetDataLine.read(buffer, 0, buffer.length);
            //Guarda los datos del buufer en el objeto de flujo de salida
            if (nBytesLeidos > 0) {
                baos.write(buffer, 0, nBytesLeidos);
            }
        }
        targetDataLine.close();
    }
    
}
