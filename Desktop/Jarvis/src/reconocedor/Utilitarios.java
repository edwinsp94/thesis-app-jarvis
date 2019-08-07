package reconocedor;

import java.util.ArrayList;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class Utilitarios {
    
    public static void reportar(byte[] ve) {
        for (int i = 0; i < ve.length; i++) {
            System.out.print(ve[i]+" ");
        }
        System.out.println("");
    }
    
    public static void reportar(int[] ve) {
        for (int i = 0; i < ve.length; i++) {
            System.out.print(ve[i]+" ");
        }
        System.out.println("");
    }
    
    public static void reportar(double[] ve) {
        for (int i = 0; i < ve.length; i++) {
            System.out.print(ve[i]+" ");
        }
        System.out.println("");
    }
    
    public static void reportar(int[][] me) {
        for (int[] fila : me) {
            for (int j = 0; j < me[0].length; j++) {
                System.out.print(fila[j]+" ");
            }
            System.out.println("");
        }
    }
    
    public static void reportar(double[][] me) {
        for (double[] fila : me) {
            for (int j = 0; j < me[0].length; j++) {
                System.out.print(fila[j]+"   ");
            }
            System.out.println("");
        }
    }
    
    public static void reportar(Complejo[] ve) {
        for (Complejo c : ve) {
            System.out.println("[" + c.getReal() + "," + c.getImag() + "]" + " ");
        }
        System.out.println("");
    }
    
    public static double[] multiplicar(double[] v1, double[] v2) {
        double[] vs = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            vs[i] = v1[i] * v2[i];
        }
        return vs;
    }
    
    public static double[] convulucionar(double[] v1, double[] v2) {
        double[] vs = new double[v1.length + v2.length - 1];
        double suma;
        
        for (int k = 0; k < vs.length; k++) {
            suma = 0;
            for (int j = 0; j < v1.length; j++) {
                if (k-j < 0 || k-j >= v2.length) {
                    suma += v1[j]*0;
                } else {
                    suma += v1[j]*v2[k-j];
                }
            }
            vs[k] = suma;
        }
        
        return vs;
    }
    
    public static double getNorma(double[] ve) {
        double s = 0;
        for (int i = 0; i < ve.length; i++) {
            s += ve[i]*ve[i];
        }
        return Math.sqrt(s);
    }
    
    public static double getSuma(double[] ve) {
        double s = 0.0;
        for (int i = 0; i < ve.length; i++) {
            s += ve[i];
        }
        return s;
    }
    
    public static double getMenor(double[] ve) {
        double menor = ve[0];
        for (int i = 1; i < ve.length; i++) {
            if (ve[i] < menor) {
                menor = ve[i];
            }
        }
        return menor;
    }
    
    public static double getMayor(double[] ve) {
        double mayor = ve[0];
        for (int i = 1; i < ve.length; i++) {
            if (ve[i] > mayor) {
                mayor = ve[i];
            }
        }
        return mayor;
    }
    
    public static double getMaximo(double a, double b) {
        return Math.max(Math.abs(a), Math.abs(b));
    }
    
    public static double[] getMenorConPosicion(double[] ve) {
        double[] vs = new double[2];
        vs[0] = ve[0];  //menor
        vs[1] = 0;      //posicion
        for (int i = 1; i < ve.length; i++) {
            if (ve[i] < vs[0]) {
                vs[0] = ve[i];
                vs[1] = i;
            }
        }
        return vs;
    }
    
    public static double[] concatenar(double[] v1, double[] v2) {
        double[] vs = new double[v1.length + v2.length];
        for (int i = 0, j = 0; i < vs.length; i++) {
            if (i < v1.length) {
                vs[i] = v1[i];
            } else {
                vs[i] = v2[j];
                j++;
            }
        }
        return vs;
    }
    
    public static double[] recortar(double[] ve, int pi, int pf) {
        double[] vs = new double[Math.abs(pf-pi)+1];
        if (pi <= pf) {
            for (int i = pi; i <= pf; i++) {
                vs[i-pi] = ve[i];
            }
        } else {
            for (int i = pi; i >= pf; i--) {
                vs[pi-i] = ve[i];
            }
        }
        return vs;
    }
    
    public static double[] invertir(double[] ve) {
        int n = ve.length;
        double[] vs = new double[n];
        for (int i = n-1; i >= 0; i--) {
            vs[n-1-i] = ve[i];
        }
        return vs;
    }
    
    public static byte[] invertir(byte[] ve) {
        int n = ve.length;
        byte[] vs = new byte[n];
        for (int i = n-1; i >= 0; i--) {
            vs[n-1-i] = ve[i];
        }
        return vs;
    }
    
    public static double[] getModulo(Complejo[] ve) {
        int n = ve.length;
        double[] vs = new double[n];
        for (int i = 0; i < n; i++) {
            vs[i] = ve[i].getModulo();
        }
        return vs;
    }
    
    public static double[] getEspectroPotencia(Complejo[] ve) {
        int Np = ve.length/2;
        double[] vs = new double[Np+1];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = ve[i].getModulo2();
        }
        return vs;
    }
    
    public static double getMenorMatriz(double[][] me) {
        double menor = me[0][0];
        for (double[] fila : me) {
            for (int j = 0; j < me[0].length; j++) {
                if (fila[j] < menor) {
                    menor = fila[j];
                }
            }
        }
        return menor;
    }
    
    public static double getMayorMatriz(double[][] me) {
        double mayor = me[0][0];
        for (double[] fila : me) {
            for (int j = 0; j < me[0].length; j++) {
                if (fila[j] > mayor) {
                    mayor = fila[j];
                }
            }
        }
        return mayor;
    }
    
    public static double[] getFilMatriz(double[][] me, int nfil) {
        int n = me[0].length;
        double[] vf = new double[n];
        System.arraycopy(me[nfil], 0, vf, 0, n);
        return vf;
    }
    
    public static double[] getColMatriz(double[][] me, int nCol) {
        int nf = me.length;
        double[] vc = new double[nf];
        for (int i = 0; i < nf; i++) {
            vc[i] = me[i][nCol];
        }
        return vc;
    }
    
    public static double[][] copiar(double[][] me) {
        int nf = me.length, nc = me[0].length;
        double[][] ms = new double[nf][nc];
        for (int i = 0; i < nf; i++) {
            System.arraycopy(me[i], 0, ms[i], 0, nc);
        }
        return ms;
    }
    
    public static boolean existePosicion(int pos, ArrayList ve) {
        boolean existe = false;
        for (int i = 0; !existe && i < ve.size(); i++) {
            if (pos == Integer.parseInt(ve.get(i).toString())) {
                existe = true;
            }
        }
        return existe;
    }
    
    public static double[] convertirListToArray(ArrayList<Double> list) {
        double[] array = new double[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    
}
