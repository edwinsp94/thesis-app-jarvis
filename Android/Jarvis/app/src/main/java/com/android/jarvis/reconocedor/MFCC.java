package com.android.jarvis.reconocedor;

import java.util.ArrayList;

public class MFCC {

    private final double[][] senalVentaneada;
    private final int dimensionVentana;
    private final int M;  //Nro de Coeficientes
    private final int BC; //Nro de Bandas Criticas o Filtros
    private final double fs; //Frecuencia de Muestreo
    private ArrayList<double[]> H; //Matriz de Bandas Criticas Triangulares

    public MFCC(double[][] senalVentaneada, int dimensionVentana, int M, int BC, double fs) {
        this.senalVentaneada = senalVentaneada;
        this.dimensionVentana = dimensionVentana;
        this.M = M;
        this.BC = BC;
        this.fs = fs;
        this.H = null;
    }

    public int[][] getBandasCriticasTriangulares(double fMin, double fMax) {
        double mel1, mel2, mel3, hz1, hz2, hz3, f;
        double[] hm;
        H = new ArrayList<>();
        int[][] pos = new int[BC][2];

        double fMinMel = toMel2(fMin);
        double fMaxMel = toMel2(fMax);
        double pasoMel = (fMaxMel-fMinMel)/(BC+1.0);
        int nFFT = (int)Math.pow(2, (int)Math.ceil(Math.log(dimensionVentana)/Math.log(2)));
        double pasoHz = fs/nFFT;

        for (int bc = 0; bc < BC; bc++) {
            mel1 = bc*pasoMel + fMinMel;
            mel2 = (bc+1)*pasoMel + fMinMel;
            mel3 = (bc+2)*pasoMel + fMinMel;

            hz1 = toMelInv2(mel1);
            hz2 = toMelInv2(mel2);
            hz3 = toMelInv2(mel3);

            pos[bc][0] = (int)Math.round(hz1/pasoHz);
            pos[bc][1] = (int)Math.round(hz3/pasoHz);

            //Crear Hm[k]
            hm = new double[pos[bc][1] - pos[bc][0] + 1];
            for (int p = pos[bc][0], i = 0; p <= pos[bc][1]; p++, i++) {
                f = p*pasoHz;
                if (f <= hz1) {
                    hm[i] = 0;
                } else if (f > hz1 && f < hz2) {
                    hm[i] = (f-hz1)/(hz2-hz1);
                } else if (f == hz2) {
                    hm[i] = 1;
                } else if (f > hz2 && f < hz3) {
                    hm[i] = (hz3-f)/(hz3-hz2);
                } else if (f >= hz3) {
                    hm[i] = 0;
                }
            }
            H.add(hm);
        }
        return pos;
    }

    public double[][] getResultMFCC(double fMin, double fMax) {
        double[][] mo = new double[M][senalVentaneada.length];//Matriz de Observaciones
        double[] Psc = new double[BC], P;
        double suma;

        //Paso 1. Calcular bins-MEL
        int[][] pos = getBandasCriticasTriangulares(fMin, fMax);
        for (int k2 = 0; k2 < mo[0].length; k2++) {
            //Paso 2. Transformada Discreta de Fourier por Ventanas
            //Paso 3. Obtencion del Espectro de Potencia
            P = Utilitarios.getEspectroPotencia(Fourier.fft2(senalVentaneada[k2]));
            //P = Utilitarios.getModulo(Fourier.fft2(senalVentaneada[k2]));
            for (int bc = 0; bc < BC; bc++) {
                //Paso 5. Filtrado e Integracion o Remuestreo por Bandas Criticas
                Psc[bc] = Utilitarios.getSuma(Utilitarios.multiplicar(H.get(bc),
                                              Utilitarios.recortar(P, pos[bc][0], pos[bc][1])));
                if (Psc[bc] != 0) { //Paso 6. Compresion
                    Psc[bc] = Math.log10(Psc[bc]);
                }
            }
            for (int j = 0; j < M; j++) {
                suma = 0; //Paso 7. Transformada Discreta Inversa de Fourier
                for (int k1 = 0; k1 < BC; k1++) {
                    suma += Psc[k1] * Math.cos(Math.PI*(j+1.0)*(k1+0.5)/(double)BC);
                }
                //Paso 8. Coeficientes MFCC
                mo[j][k2] = Math.sqrt(2.0/(double)BC) * suma;
            }
        }

        return mo;
    }

    public double[][] getResultMFCCDelta(double fMin, double fMax) {
        double[][] mo = new double[26][senalVentaneada.length];//Matriz de Observaciones
        double[] Psc = new double[BC], P;
        double suma;

        //Paso 1. Calcular bins-MEL
        int[][] pos = getBandasCriticasTriangulares(fMin, fMax);
        for (int k2 = 0; k2 < mo[0].length; k2++) {
            //Paso 2. Transformada Discreta de Fourier por Ventanas
            //Paso 3. Obtencion del Espectro de Potencia
            P = Utilitarios.getEspectroPotencia(Fourier.fft2(senalVentaneada[k2]));
            for (int bc = 0; bc < BC; bc++) {
                //Paso 5. Filtrado e Integracion o Remuestreo por Bandas Criticas
                Psc[bc] = Utilitarios.getSuma(Utilitarios.multiplicar(H.get(bc),
                                              Utilitarios.recortar(P, pos[bc][0], pos[bc][1])));
                if (Psc[bc] != 0) { //Paso 6. Compresion
                    Psc[bc] = Math.log10(Psc[bc]);
                }
            }
            for (int j = 0; j < 13; j++) {
                suma = 0; //Paso 7. Transformada Discreta Inversa de Fourier
                for (int k1 = 0; k1 < BC; k1++) {
                    suma += Psc[k1] * Math.cos(Math.PI*(j+1.0)*(k1+0.5)/(double)BC);
                }
                //Paso 8. Coeficientes MFCC
                mo[j][k2] = Math.sqrt(2.0/(double)BC) * suma;
            }
        }
        //Paso 9. Agregar las 1ra derivadas a mo
        for (int j = 0; j < mo[0].length; j++) {
            for (int i = 0; i < 13; i++) {
                if (i-2 < 0 || i+2 > 12) {
                    mo[i+13][j] = mo[i][j];
                } else {
                    mo[i+13][j] = mo[i+2][j] - mo[i-2][j];
                }
            }
        }

        return mo;
    }

    public double[][] getResultMFCCDeltaDelta(double fMin, double fMax) {
        double[][] mo = new double[39][senalVentaneada.length];//Matriz de Observaciones
        double[] Psc = new double[BC], P;
        double suma;

        //Paso 1. Calcular bins-MEL
        int[][] pos = getBandasCriticasTriangulares(fMin, fMax);
        for (int k2 = 0; k2 < mo[0].length; k2++) {
            //Paso 2. Transformada Discreta de Fourier por Ventanas
            //Paso 3. Obtencion del Espectro de Potencia
            P = Utilitarios.getEspectroPotencia(Fourier.fft2(senalVentaneada[k2]));
            for (int bc = 0; bc < BC; bc++) {
                //Paso 5. Filtrado e Integracion o Remuestreo por Bandas Criticas
                Psc[bc] = Utilitarios.getSuma(Utilitarios.multiplicar(H.get(bc),
                                              Utilitarios.recortar(P, pos[bc][0], pos[bc][1])));
                if (Psc[bc] != 0) { //Paso 6. Compresion
                    Psc[bc] = Math.log10(Psc[bc]);
                }
            }
            for (int j = 0; j < 13; j++) {
                suma = 0; //Paso 7. Transformada Discreta Inversa de Fourier
                for (int k1 = 0; k1 < BC; k1++) {
                    suma += Psc[k1] * Math.cos(Math.PI*(j+1.0)*(k1+0.5)/(double)BC);
                }
                //Paso 8. Coeficientes MFCC
                mo[j][k2] = Math.sqrt(2.0/(double)BC) * suma;
            }
        }
        //Paso 9. Agregar las 1ra derivadas a mo
        for (int j = 0; j < mo[0].length; j++) {
            for (int i = 0; i < 13; i++) {
                if (i-2 < 0 || i+2 > 12) {
                    mo[i+13][j] = mo[i][j];
                } else {
                    mo[i+13][j] = mo[i+2][j] - mo[i-2][j];
                }
            }
        }
        //Paso 10. Agregar las 2da derivadas a mo
        for (int j = 0; j < mo[0].length; j++) {
            for (int i = 13; i < 26; i++) {
                if (i-1 < 13 || i+1 > 25) {
                    mo[i+13][j] = mo[i][j];
                } else {
                    mo[i+13][j] = mo[i+1][j] - mo[i-1][j];
                }
            }
        }

        return mo;
    }

    public static double toMel(double f) {
        return 1125*Math.log(1+f/700.0);
    }

    public static double toMelInv(double b) {
        return 700*(Math.exp(b/1125.0)-1);
    }

    public static double toMel2(double f) {
        return (1000.0/Math.log(2))*Math.log(1+f/1000.0);
    }

    public static double toMelInv2(double b) {
        return 1000.0*(Math.exp(b/1000.0*Math.log(2))-1);
    }

    public static double toMel3(double f) {
        return 2595*Math.log10(1+f/700.0);
    }

    public static double toMelInv3(double b) {
        return 700*(Math.pow(10,(b/2595.0))-1);
    }

    public static double toMel4(double f) {
        return (1000.0/Math.log(2))*(1+f/1000.0);
    }

    public static double toMelInv4(double b) {
        return 1000.0*((b/1000.0*Math.log(2))-1);
    }

}
