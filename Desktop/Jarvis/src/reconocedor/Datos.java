package reconocedor;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class Datos {
    
    private byte[] bits;
    private boolean tipo;
    private double mayor;
    private double menor;
    private double[] amplitudes;
    
    public Datos() {
    }
    
    public Datos(int tamano, boolean tipo) {
	this.bits = new byte[tamano];
	this.tipo = tipo;
	this.mayor = 0;
	this.menor = 0;
    }
    
    public Datos(byte[] bits, boolean tipo) {
	this.bits = bits;
	this.tipo = tipo;
	this.mayor = 0;
	this.menor = 0;
    }
    
    public Datos(double[] amplitudes, boolean tipo) {
	this.amplitudes = amplitudes;
	this.tipo = tipo;
	this.mayor = 0;
	this.menor = 0;
    }
    
    public byte[] getBits() {
        return bits;
    }
    
    public boolean getTipo() {
        return tipo;
    }
    
    public double getMayor() {
        return mayor;
    }
    
    public double getMenor() {
        return menor;
    }

    public double[] getAmplitudes() {
        return amplitudes;
    }
    
    /*
    Ejemplo: bits[2]=2 (00000010) y bits[3]=3 (00000011)
    se aplica bits[2]<<8 quedando ahora 10 00000000,
    luego 11111111(0x000000FF) & bits[3]|bits[2]
    en total da 10 00000011  que es el numero 515, este es un short de 16 bits,
    han entrado dos bytes en uno (short[i] = contacenar(byte[i]+byte[i+1])),
    los valores negativos estan en complemento a 2
    */

    public void convertirByteADouble() {
        byte[] b = new byte[2];
        double[] arrayDouble = new double[bits.length/2];
        
        for (int i = 0, j = 0; j < arrayDouble.length ; j++) {
            b[0] = bits[i]; 
            b[1] = bits[i+1];
            arrayDouble[j] = (double)byteArrayAInt(b, tipo);
            i += 2;
            //Calculamos el mayor y el menor esto nos servira para establecer
            //los parametros de los ejes en la grafica
            if (mayor < arrayDouble[j]) {
                mayor = arrayDouble[j];
            }
            if (menor > arrayDouble[j]) {
                menor = arrayDouble[j];
            }
        }
        
	amplitudes = arrayDouble;
    }
    
    public void convertirDoubleAByte() {
        byte[] b;
        byte[] arrayByte = new byte[amplitudes.length*2];
        
        for (int i = 0, j = 0; i < amplitudes.length ; i++) {
            b = IntAByteArray((int)amplitudes[i], tipo, Short.SIZE);
            arrayByte[j] = b[0];
            arrayByte[j+1] = b[1];
            j += 2;
            //Calculamos el mayor y el menor esto nos servira para establecer
            //los parametros de los ejes en la grafica
            if (mayor < amplitudes[i]) {
                mayor = amplitudes[i];
            }
            if (menor > amplitudes[i]) {
                menor = amplitudes[i];
            }
        }
        
	bits = arrayByte;
    }
    
    public static int byteArrayAInt(byte[] b) {
        ByteBuffer bb = ByteBuffer.wrap(b);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        return bb.getInt();
    }

    public static byte[] IntAByteArray(int i) {
        ByteBuffer bb = ByteBuffer.allocate(Integer.SIZE / Byte.SIZE);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putInt(i);
        return bb.array();
    }
    
    public static int byteArrayAInt(byte[] valorCodificado, boolean tipo) {
        if (tipo) valorCodificado = Utilitarios.invertir(valorCodificado);
        int n = valorCodificado.length;
        int valor = (valorCodificado[n-1] << (Byte.SIZE * (n-1)));
        for (int i = n-2; i > 0; i--) {
            valor |= (valorCodificado[i] & 0xFF) << (Byte.SIZE * i);
        }
        valor |= (valorCodificado[0] & 0xFF);
        return valor;
    }

    public static byte[] IntAByteArray(int valor, boolean tipo, int tamano) {
        byte[] valorCodificado = new byte[tamano/Byte.SIZE];
        int n = valorCodificado.length;
        for (int i = n-1; i > 0; i--) {
            valorCodificado[i] = (byte)(valor >> Byte.SIZE * i);
        }
        valorCodificado[0] = (byte)valor;
        if (tipo) valorCodificado = Utilitarios.invertir(valorCodificado);
        return valorCodificado;
    }
    
}
