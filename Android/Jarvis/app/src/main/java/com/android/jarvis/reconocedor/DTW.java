package com.android.jarvis.reconocedor;

import java.util.ArrayList;

public class DTW {

    private final double[][] mfcc1;
    private final double[][] mfcc2;
    private double[][] mDistancia;
    private double[][] mPesos;
    private final int radio;
    private final int tipoDistancia;
    private ArrayList<int[]> caminoDTW;
    private double distanciaDTW;
    private double distanciaCamino;

    public DTW(double[][] mfcc1, double[][] mfcc2, int radio, int tipoDistancia) {
        this.mfcc1 = mfcc1;
        this.mfcc2 = mfcc2;
        this.radio = radio;
        this.tipoDistancia = tipoDistancia;
        this.caminoDTW = null;
        this.distanciaDTW = 0;
        this.distanciaCamino = 0;
    }

    public ArrayList<int[]> getCaminoDTW() {
        return caminoDTW;
    }

    public double getDistanciaDTW() {
        return distanciaDTW;
    }

    public double getDistanciaCamino() {
        return distanciaCamino;
    }

    public double[][] getmDistancia() {
        return mDistancia;
    }

    public double[][] getmPesos() {
        return mPesos;
    }

    public double[][] getmCamino() {
        int f = caminoDTW.get(0)[0] + 1;
        int c = caminoDTW.get(0)[1] + 1;
        double[][] mCamino = new double[f][c];
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                mCamino[i][j] = 0;
            }
        }
        for (int k = caminoDTW.size()-1; k > -1; k--) {
            mCamino[caminoDTW.get(k)[0]][caminoDTW.get(k)[1]] = 1;
        }
        return mCamino;
    }

    private void obtenerMatricesDistancias() {
        int nc1 = mfcc1[0].length, nc2 = mfcc2[0].length, nca, ncb;
        //1. La palabra de menor duracion se utiliza en la abscisa (segun Furui Sadaoki)
        boolean invertir;
        if (nc1 < nc2) {
            nca = nc1; ncb = nc2; invertir = false;
        } else {
            nca = nc2; ncb = nc1; invertir = true;
        }
        double[][] mg = new double[nca][ncb], md = new double[nca][ncb];
        double[] v1, v2;

        //2. Llenar la matriz de distancias.
        for (int i = 0; i < nca; i++) {
            if (!invertir) {
                v1 = Utilitarios.getColMatriz(mfcc1, i);
            } else {
                v1 = Utilitarios.getColMatriz(mfcc2, i);
            }
            for (int j = 0; j < ncb; j++) {
                if (!invertir) {
                    v2 = Utilitarios.getColMatriz(mfcc2, j);
                } else {
                    v2 = Utilitarios.getColMatriz(mfcc1, j);
                }
                if (tipoDistancia == 1) {
                    md[i][j] = distanciaEuclidianaCuadratica(v1, v2);
                } else {
                    md[i][j] = distanciaErrorCuadraticoMedio(v1, v2);
                }
                mg[i][j] = 0.0;
            }
        }

        mDistancia = md;
        mPesos = mg;
    }

    public static double distanciaEuclidianaCuadratica(double[] v1, double[] v2) {
        double suma = 0.0;
        for (int i = 0; i < v1.length; i++) {
            suma += Math.pow(v1[i]-v2[i], 2);
        }
        return suma;
        //return Math.sqrt(suma);
    }

    public static double distanciaErrorCuadraticoMedio(double[] v1, double[] v2) {
        double suma = 0;
        int N = v1.length;
        for (int i = 0; i < N; i++) {
            suma += Math.pow(v1[i]-v2[i], 2);
        }
        return suma/N;
    }

    private void calcularCaminoDTW(double[][] mg, int i, int j) {
        caminoDTW = null;
        caminoDTW = new ArrayList<>();
        int[] indices = new int[2];
        indices[0] = i; indices[1] = j;
        while (indices[0] >= 0 && indices[1] >= 0) {
            caminoDTW.add(indices);
            indices = indicesMinAnterior(mg, indices[0], indices[1]);
        }
    }

    private int[] indicesMinAnterior(double[][] mg, int i, int j) {
        int[] indices = new int[2];
        double[] aux = new double[3];
        if (i-1 >= 0) aux[0] = mg[i-1][j];
        else aux[0] = 10000;
        if (i-1 >= 0 && j-1 >= 0) aux[1] = mg[i-1][j-1];
        else aux[1] = 10000;
        if (j-1 >= 0) aux[2] = mg[i][j-1];
        else aux[2] = 10000;
        double[] r = Utilitarios.getMenorConPosicion(aux);
        switch((int)r[1]) {
            case 0: indices[0] = i-1; indices[1] = j;   break;
            case 1: indices[0] = i-1; indices[1] = j-1; break;
            case 2: indices[0] = i;   indices[1] = j-1; break;
        }
        return indices;
    }

    private void calcularDistanciaCamino(double[][] mg) {
        distanciaCamino = 0;
        for (int i = 0, pi, pj; i < caminoDTW.size(); i++) {
            pi = (int)caminoDTW.get(i)[0];
            pj = (int)caminoDTW.get(i)[1];
            distanciaCamino += mg[pi][pj];
        }
    }

    public void dtwSimetricoP0() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[3];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        for (int i = 0; i < nca; i++) {
            for (int j = 0; j < ncb; j++) {
                rama[0] = 10000; rama[1] = 10000; rama[2] = 10000;
                if (j-r <= i && i <= j+r && !(i == 0 && j == 0)) {
                    if (j-1 >= 0) {
                        rama[0] = mg[i][j-1] + md[i][j];
                    }
                    if (i-1 >= 0 && j-1 >= 0) {
                        rama[1] = mg[i-1][j-1] + 2*md[i][j];
                    }
                    if (i-1 >= 0) {
                        rama[2] = mg[i-1][j] + md[i][j];
                    }
                    mg[i][j] = Utilitarios.getMenor(rama);
                }
            }
        }

        //2.3. Calcular el mejor camino
        calcularCaminoDTW(mg, nca-1, ncb-1);
        //2.4. Calcular distancias
        calcularDistanciaCamino(mg);
        distanciaDTW = mg[nca-1][ncb-1]/(nca+ncb);
    }

    public void dtwAsimetricoP0() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[3];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        for (int i = 0; i < nca; i++) {
            for (int j = 0; j < ncb; j++) {
                rama[0] = 10000; rama[1] = 10000; rama[2] = 10000;
                if (j-r <= i && i <= j+r && !(i == 0 && j == 0)) {
                    if (j-1 >= 0) {
                        rama[0] = mg[i][j-1];
                    }
                    if (i-1 >= 0 && j-1 >= 0) {
                        rama[1] = mg[i-1][j-1] + md[i][j];
                    }
                    if (i-1 >= 0) {
                        rama[2] = mg[i-1][j] + md[i][j];
                    }
                    mg[i][j] = Utilitarios.getMenor(rama);
                }
            }
        }
        //2.3. Calcular el mejor camino
        calcularCaminoDTW(mg, nca-1, ncb-1);
        //2.4. Calcular distancias
        calcularDistanciaCamino(mg);
        distanciaDTW = mg[nca-1][ncb-1]/nca;
    }

    public void dtwSimetricoP12() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[5];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        for (int i = 0; i < nca; i++) {
            for (int j = 0; j < ncb; j++) {
                rama[0] = 1000; rama[1] = 1000; rama[2] = 1000; rama[3] = 1000; rama[4] = 1000;
                if (j-r <= i && i <= j+r && !(i == 0 && j == 0)) {
                    if (i-1 >= 0 && j-3 >= 0) {
                        rama[0] = mg[i-1][j-3] + 2*md[i][j-2] + md[i][j-1] + md[i][j];
                    }
                    if (i-1 >= 0 && j-2 >= 0) {
                        rama[1] = mg[i-1][j-2] + 2*md[i][j-1] + md[i][j];
                    }
                    if (i-1 >= 0 && j-1 >= 0) {
                        rama[2] = mg[i-1][j-1] + 2*md[i][j];
                    }
                    if (i-2 >= 0 && j-1 >= 0) {
                        rama[3] = mg[i-2][j-1] + 2*md[i-1][j] + md[i][j];
                    }
                    if (i-3 >= 0 && j-1 >= 0) {
                        rama[4] = mg[i-3][j-1] + 2*md[i-2][j] + md[i-1][j] + md[i][j];
                    }
                    mg[i][j] = Utilitarios.getMenor(rama);
                }
            }
        }

        //2.3. Calcular el mejor camino
        calcularCaminoDTW(mg, nca-1, ncb-1);
        //2.4. Calcular distancias
        calcularDistanciaCamino(mg);
        distanciaDTW = mg[nca-1][ncb-1]/(nca+ncb);
    }

    public void dtwAsimetricoP12() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[5];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        for (int i = 0; i < nca; i++) {
            for (int j = 0; j < ncb; j++) {
                rama[0] = 1000; rama[1] = 1000; rama[2] = 1000; rama[3] = 1000; rama[4] = 1000;
                if (j-r <= i && i <= j+r && !(i == 0 && j == 0)) {
                    if (i-1 >= 0 && j-3 >= 0) {
                        rama[0] = mg[i-1][j-3] + (md[i][j-2] + md[i][j-1] + md[i][j])/3;
                    }
                    if (i-1 >= 0 && j-2 >= 0) {
                        rama[1] = mg[i-1][j-2] + (md[i][j-1] + md[i][j])/2;
                    }
                    if (i-1 >= 0 && j-1 >= 0) {
                        rama[2] = mg[i-1][j-1] + md[i][j];
                    }
                    if (i-2 >= 0 && j-1 >= 0) {
                        rama[3] = mg[i-2][j-1] + md[i-1][j] + md[i][j];
                    }
                    if (i-3 >= 0 && j-1 >= 0) {
                        rama[4] = mg[i-3][j-1] + md[i-2][j] + md[i-1][j] + md[i][j];
                    }
                    mg[i][j] = Utilitarios.getMenor(rama);
                }
            }
        }

        //2.3. Calcular el mejor camino
        calcularCaminoDTW(mg, nca-1, ncb-1);
        //2.4. Calcular distancias
        calcularDistanciaCamino(mg);
        distanciaDTW = mg[nca-1][ncb-1]/nca;
    }

    public void dtwSimetricoP12_2() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[5];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        int i = 0, j = 0; boolean fin = false;
        do{
            i = i + 1;
            if (i > j+r) {
                j = j + 1;
                if (j >= ncb) {
                    //2.3. Calcular el mejor camino
                    calcularCaminoDTW(mg, nca-1, ncb-1);
                    //2.4. Calcular distancias
                    calcularDistanciaCamino(mg);
                    distanciaDTW = mg[nca-1][ncb-1]/(nca+ncb);
                    fin = true;
                } else {
                    i = j - r;
                }
            } else {
                if (i >= 0) {
                    if (i < nca) {
                        //calcular DWT
                        rama[0] = 1000; rama[1] = 1000; rama[2] = 1000; rama[3] = 1000; rama[4] = 1000;
                        if (i-1 >= 0 && j-3 >= 0) {
                            rama[0] = mg[i-1][j-3] + 2*md[i][j-2] + md[i][j-1] + md[i][j];
                        }
                        if (i-1 >= 0 && j-2 >= 0) {
                            rama[1] = mg[i-1][j-2] + 2*md[i][j-1] + md[i][j];
                        }
                        if (i-1 >= 0 && j-1 >= 0) {
                            rama[2] = mg[i-1][j-1] + 2*md[i][j];
                        }
                        if (i-2 >= 0 && j-1 >= 0) {
                            rama[3] = mg[i-2][j-1] + 2*md[i-1][j] + md[i][j];
                        }
                        if (i-3 >= 0 && j-1 >= 0) {
                            rama[4] = mg[i-3][j-1] + 2*md[i-2][j] + md[i-1][j] + md[i][j];
                        }
                        if (i != 0 || j != 0) {
                            mg[i][j] = Utilitarios.getMenor(rama);
                        }
                    }
                }
            }
        } while(!fin);
    }

    public void dtwSimetricoP1() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[3];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        for (int i = 0; i < nca; i++) {
            for (int j = 0; j < ncb; j++) {
                rama[0] = 10000; rama[1] = 10000; rama[2] = 10000;
                if (j-r <= i && i <= j+r && !(i == 0 && j == 0)) {
                    if (i-1 >= 0 && j-2 >= 0) {
                        rama[0] = mg[i-1][j-2] + 2*md[i][j-1] + md[i][j];
                    }
                    if (i-1 >= 0 && j-1 >= 0) {
                        rama[1] = mg[i-1][j-1] + 2*md[i][j];
                    }
                    if (i-2 >= 0 && j-1 >= 0) {
                        rama[2] = mg[i-2][j-1] + 2*md[i-1][j] + md[i][j];
                    }
                    mg[i][j] = Utilitarios.getMenor(rama);
                }
            }
        }

        //2.3. Calcular el mejor camino
        calcularCaminoDTW(mg, nca-1, ncb-1);
        //2.4. Calcular distancias
        calcularDistanciaCamino(mg);
        distanciaDTW = mg[nca-1][ncb-1]/(nca+ncb);
    }

    public void dtwAsimetricoP1() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[3];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        for (int i = 0; i < nca; i++) {
            for (int j = 0; j < ncb; j++) {
                rama[0] = 10000; rama[1] = 10000; rama[2] = 10000;
                if (j-r <= i && i <= j+r && !(i == 0 && j == 0)) {
                    if (i-1 >= 0 && j-2 >= 0) {
                        rama[0] = mg[i-1][j-2] + (md[i][j-1] + md[i][j])/2;
                    }
                    if (i-1 >= 0 && j-1 >= 0) {
                        rama[1] = mg[i-1][j-1] + md[i][j];
                    }
                    if (i-2 >= 0 && j-1 >= 0) {
                        rama[2] = mg[i-2][j-1] + md[i-1][j] + md[i][j];
                    }
                    mg[i][j] = Utilitarios.getMenor(rama);
                }
            }
        }

        //2.3. Calcular el mejor camino
        calcularCaminoDTW(mg, nca-1, ncb-1);
        //2.4. Calcular distancias
        calcularDistanciaCamino(mg);
        distanciaDTW = mg[nca-1][ncb-1]/nca;
    }

    public void dtwSimetricoP1_2() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[3];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        int i = 0, j = 0; boolean fin = false;
        do{
            i = i + 1;
            if (i > j+r) {
                j = j + 1;
                if (j >= ncb) {
                    //2.3. Calcular el mejor camino
                    calcularCaminoDTW(mg, nca-1, ncb-1);
                    //2.4. Calcular distancias
                    calcularDistanciaCamino(mg);
                    distanciaDTW = mg[nca-1][ncb-1]/(nca+ncb);
                    fin = true;
                } else {
                    i = j - r;
                }
            } else {
                if (i >= 0) {
                    if (i < nca) {
                        //calcular DWT
                        rama[0] = 1000; rama[1] = 1000; rama[2] = 1000;
                        if (i-1 >= 0 && j-2 >= 0) {
                            rama[0] = mg[i-1][j-2] + 2*md[i][j-1] + md[i][j];
                        }
                        if (i-1 >= 0 && j-1 >= 0) {
                            rama[1] = mg[i-1][j-1] + 2*md[i][j];
                        }
                        if (i-2 >= 0 && j-1 >= 0) {
                            rama[2] = mg[i-2][j-1] + 2*md[i-1][j] + md[i][j];
                        }
                        if (i != 0 || j != 0) {
                            mg[i][j] = Utilitarios.getMenor(rama);
                        }
                    }
                }
            }
        } while(!fin);
    }

    public void dtwSimetricoP2() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[3];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        for (int i = 0; i < nca; i++) {
            for (int j = 0; j < ncb; j++) {
                rama[0] = 10000; rama[1] = 10000; rama[2] = 10000;
                if (j-r <= i && i <= j+r && !(i == 0 && j == 0)) {
                    if (i-2 >= 0 && j-3 >= 0) {
                        rama[0] = mg[i-2][j-3] + 2*md[i-1][j-2]+ 2*md[i][j-1] + md[i][j];
                    }
                    if (i-1 >= 0 && j-1 >= 0) {
                        rama[1] = mg[i-1][j-1] + 2*md[i][j];
                    }
                    if (i-3 >= 0 && j-2 >= 0) {
                        rama[2] = mg[i-3][j-2] + 2*md[i-2][j-1] + 2*md[i-1][j] + md[i][j];
                    }
                    mg[i][j] = Utilitarios.getMenor(rama);
                }
            }
        }

        //2.3. Calcular el mejor camino
        calcularCaminoDTW(mg, nca-1, ncb-1);
        //2.4. Calcular distancias
        calcularDistanciaCamino(mg);
        distanciaDTW = mg[nca-1][ncb-1]/(nca+ncb);
    }

    public void dtwAsimetricoP2() {
        //1. Llenar las matrices de distancias.
        obtenerMatricesDistancias();
        double[][] md = mDistancia, mg = mPesos;
        int nca = md.length, ncb = md[0].length;
        double[] rama = new double[3];
        int r = Math.abs(mfcc1[0].length-mfcc2[0].length) + radio;

        //2. Algoritmo DTW
        //2.1. Condición Inicial
        mg[0][0] = 2*md[0][0];
        //2.2. Proceso con ecuación DP con ventana de ajuste
        for (int i = 0; i < nca; i++) {
            for (int j = 0; j < ncb; j++) {
                rama[0] = 10000; rama[1] = 10000; rama[2] = 10000;
                if (j-r <= i && i <= j+r && !(i == 0 && j == 0)) {
                    if (i-2 >= 0 && j-3 >= 0) {
                        rama[0] = mg[i-2][j-3] + 2/3*(md[i-1][j-2] + md[i][j-1] + md[i][j]);
                    }
                    if (i-1 >= 0 && j-1 >= 0) {
                        rama[1] = mg[i-1][j-1] + md[i][j];
                    }
                    if (i-3 >= 0 && j-2 >= 0) {
                        rama[2] = mg[i-3][j-2] + md[i-2][j-1] + md[i-1][j] + md[i][j];
                    }
                    mg[i][j] = Utilitarios.getMenor(rama);
                }
            }
        }

        //2.3. Calcular el mejor camino
        calcularCaminoDTW(mg, nca-1, ncb-1);
        //2.4. Calcular distancias
        calcularDistanciaCamino(mg);
        distanciaDTW = mg[nca-1][ncb-1]/nca;
    }

    public double[][] fusionarAudios() {
        int nfmo = mfcc1.length;
        int ncmo = caminoDTW.size();
        double[][] MF = new double[nfmo][ncmo];
        double[] x, y;
        boolean invertir = mfcc1[0].length >= mfcc2[0].length;

        for (int j = ncmo-1, pi, pj; j > -1; j--) {
            pi = caminoDTW.get(j)[0];
            pj = caminoDTW.get(j)[1];
            if (!invertir) {
                x = Utilitarios.getColMatriz(mfcc1, pi);
                y = Utilitarios.getColMatriz(mfcc2, pj);
            } else {
                x = Utilitarios.getColMatriz(mfcc2, pi);
                y = Utilitarios.getColMatriz(mfcc1, pj);
            }
            for (int i = 0; i < nfmo; i++) {
                MF[i][ncmo-1-j] = (x[i]+y[i])/2;
            }
        }

        return MF;
    }

    public double[][] fusionarAudios2() {
        int nfmo = mfcc1.length;
        int ncmo = caminoDTW.size();
        double[][] MF = new double[nfmo][ncmo];
        double[] x, y;
        boolean invertir = mfcc1[0].length >= mfcc2[0].length;
        ArrayList pos_repet = new ArrayList();

        for (int j = ncmo-1, pi, pj, pi_a = -1, pj_a = -1; j > -1; j--) {
            pi = caminoDTW.get(j)[0];
            pj = caminoDTW.get(j)[1];
            if (pi_a == pi || pj_a == pj) {
                pos_repet.add(ncmo-1-j);
            }
            if (!invertir) {
                x = Utilitarios.getColMatriz(mfcc1, pi);
                y = Utilitarios.getColMatriz(mfcc2, pj);
            } else {
                x = Utilitarios.getColMatriz(mfcc2, pi);
                y = Utilitarios.getColMatriz(mfcc1, pj);
            }
            for (int i = 0; i < nfmo; i++) {
                MF[i][ncmo-1-j] = (x[i]+y[i])/2;
            }
            pi_a = pi;
            pj_a = pj;
        }

        double[][] MF2 = new double[nfmo][MF[0].length - pos_repet.size()];
        boolean repetido; double suma;
        for (int j = 0, jm = 0, cont; j < MF[0].length; j++) {
            repetido = false;
            cont = 0;
            while (Utilitarios.existePosicion(j+cont, pos_repet)) {
                cont++;
                repetido = true;
            }
            if (!repetido) {
                for (int i = 0; i < MF.length; i++) {
                    MF2[i][jm] = MF[i][j];
                }
                jm++;
            } else {
                for (int i = 0; i < MF.length; i++) {
                    suma = 0;
                    for (int k = 0; k <= cont; k++) {
                        suma += MF[i][j-1+k];
                    }
                    MF2[i][jm-1] = suma/(cont+1);
                }
                j += (cont-1);
            }
        }

        return MF2;
    }

}
