package reconocedor;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class Ventanamiento {
    
    private final double[][] matriz; //Captura los diferentes espectros por fila
    private final int TV;            //Tamano de la ventana
    private final int DS;            //Distancia de solapamiento
    private final int tipo;          //Tipo de ventana
    
    public Ventanamiento(double[] datos, int TV, int DS, int tipo) {
        this.TV = TV;
        this.DS = DS;
        this.tipo = tipo;
        this.matriz = obtenerMatrizVentanamiento(datos);
    }
    
    public double[][] getMatriz() {
        return matriz;
    }

    public int getTV() {
        return TV;
    }

    public int getDS() {
        return DS;
    }

    public int getTipo() {
        return tipo;
    }
    
    private double [][] obtenerMatrizVentanamiento(double[] datos) {
        int a = 0, b = TV, j = 0, k = 0;
        int tam = datos.length;
        int piezas = (int)Math.floor((tam-TV)/DS)+1;
        boolean todo = (tam == DS*(piezas-1) + TV);
        if (!todo) {//Verificamos si se coge toda la senal
            piezas++;
        }
        double[][] mat = new double[piezas][];
        double[] vh;
        
        switch (tipo) {
            case 1:
                vh = crearHamming(TV);
                break;
            case 2:
                vh = crearHanning(TV);
                break;
            default:
                vh = crearRectangular(TV);
                break;
        }
        while (b <= tam) {
            mat[j] = new double[TV];
            for (int i = a; i < b; i++, k++) {
                mat[j][k] = datos[i]*vh[k];
            }
            if(b == tam) {
                return mat;
            }
            a += DS; b += DS; j++; k = 0;
        }
        if (b > tam) {
            mat[j] = new double[TV];
            for (int i = a; i < b; i++, k++) {
                if (i < tam) {
                    mat[j][k] = datos[i]*vh[k];
                } else {//extrapolacion
                    mat[j][k] = 2*mat[j][k-1] - mat[j][k-2];
                }
            }
        }
        
        return mat;
    }
    
    private double[] crearHamming(int N) {
        double[] v = new double[N];
        for (int i = 0; i < N; i++) {
            v[i] = 0.54 - 0.46*Math.cos(2*Math.PI*i/(N-1));
        }
        return v;
    }
    
    private double[] crearHanning(int N) {
        double[] v = new double[N];
        for (int i = 0; i < N; i++) {
            v[i] = 0.50 - 0.50*Math.cos(2*Math.PI*i/(N-1));
        }
        return v;
    }
    
    private double[] crearRectangular(int N) {
        double[] v = new double[N];
        for (int i = 0; i < N; i++) {
            v[i] = 1;
        }
        return v;
    }
    
}
