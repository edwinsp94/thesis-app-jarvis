package reconocedor;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class Fourier {
    
    public static Complejo[] fft1(double[] vEntrada) {
        //Completar vector con ceros para potencia de 2
        Complejo[] va = rellenadoCeros(vEntrada);
        int N = va.length;
        Complejo[] vb = rellenadoCeros(N);
        
        //Computar factores mariposa
        Complejo[] w = new Complejo[N/2];
        for (int i = 0; i < N/2; i++) {
            w[i] = new Complejo(Math.cos(2*Math.PI*i/N), (-1)*Math.sin(2*Math.PI*i/N));
        }
        
        //fft Radix-2 DIF
        int nCifras = (int)(Math.log(N)/Math.log(2));
        for (int i = 0, aux0, aux1, aux2, j2; i < nCifras; i++) {
            aux0 = (int)Math.pow(2,i);
            aux1 = N/aux0;
            aux2 = N/(aux0*2);
            for (int j = 0; j < N; j++) {
                j2 = j % aux1;
                if (j2 < aux2) {
                    vb[j].setComplejo(va[j].sumar(va[j+aux2]));
                } else {
                    vb[j].setComplejo(va[j-aux2].restar(va[j]).multiplicar(w[aux0*(j2%aux2)]));
                }
            }
            for (int j = 0; j < N; j++) {
                va[j].setComplejo(vb[j]);
            }
        }
        
        //Reordenamiento
        for (int i = 0; i < N; i++) {
            vb[bin2num(num2binInv(i, nCifras))].setComplejo(va[i]);
        }
        
        return vb;
    }
    
    public static int[] num2binInv(int numero, int nCifras) {
        int[] nBinario = new int[nCifras]; 
        int k;
        
        for (int i = 0; i < nCifras; i++) {
            nBinario[i] = 0;
        }
        for (k = 0; numero >= 2; k++, numero /= 2) {
            nBinario[k] = numero%2;
        }
        nBinario[k] = numero;
        
        return nBinario;
    }
    
    public static int bin2num(int[] nBinario) {
        int n = nBinario.length, numero = 0;
        for (int i = n-1; i >= 0; i--) {
            numero += nBinario[(n-1)-i] * (int)Math.pow(2,i);
        }
        return numero;
    }
    
    public static Complejo[] rellenadoCeros(double[] vEntrada) {
        int N = vEntrada.length;
        int Nfft = (int)Math.pow(2, (int)Math.ceil(Math.log(N)/Math.log(2)));
        Complejo[] vSalida = new Complejo[Nfft];
        
        for (int n = 0; n < Nfft; n++) {
            if (n < N) {
                vSalida[n] = new Complejo(vEntrada[n], 0);
            } else {
                vSalida[n] = new Complejo(0, 0);
            }
        }
        
        return vSalida;
    }
    
    public static Complejo[] rellenadoCeros(int Nfft) {
        Complejo[] vSalida = new Complejo[Nfft];
        for (int n = 0; n < Nfft; n++) {
            vSalida[n] = new Complejo(0, 0);
        }
        return vSalida;
    }
    
    public static Complejo[] fft2(double[] vEntrada) {
        Complejo[] Xzp = rellenadoCeros(vEntrada);
        return transformada(Xzp, Xzp.length);
    }
    
    public static Complejo[] transformada(Complejo[] vEntrada, int N) {
        Complejo[] vSalida = new Complejo[N];
        
        if (N == 1) {
            vSalida[0] = vEntrada[0];
        } else {
            Complejo[] fe = new Complejo[N/2];
            Complejo[] fo = new Complejo[N/2];
            
            for (int i = 0; i < N/2; i++) {
                fe[i] = vEntrada[2*i];
                fo[i] = vEntrada[2*i+1];
            }
            
            Complejo[] Fe = transformada(fe, N/2);
            Complejo[] Fo = transformada(fo, N/2);
            Complejo w, aux; 
            double teta;
            
            for (int k = 0; k < N/2; k++) {
                //Twiddle Factor (Factor Mariposa)
                teta = 2*Math.PI*k/N;
                w = new Complejo(Math.cos(teta), (-1)*Math.sin(teta));
                aux = Fo[k].multiplicar(w);
                vSalida[k] = Fe[k].sumar(aux);
                vSalida[k+N/2] = Fe[k].restar(aux);
            }
        }
        
        return vSalida;
    }
    
    public static double[][] espectro(double[][] senalVentaneada) {
        int tHamming = senalVentaneada[0].length;
        int nTramas = senalVentaneada.length;
        int Nfft = (int)Math.pow(2, (int)Math.ceil(Math.log(tHamming)/Math.log(2)));
        double[][] mEspectro = new double[nTramas][Nfft/2 + 1];
        double[] P;
        
        for (int k = 0; k < nTramas; k++) {
            //Paso 1. Transformada Discreta de Fourier por Ventanas
            //Paso 2. Obtencion del Espectro de Potencia
            P = Utilitarios.getEspectroPotencia(Fourier.fft2(senalVentaneada[k]));
            //Paso 3. Calcular Logaritmo del Espectro de Potencia y Transponemos
            for (int j = 0; j < P.length; j++) {
                mEspectro[k][j] = Math.log10(P[j]);
            }
        }
        
        return mEspectro;
    }
    
}
