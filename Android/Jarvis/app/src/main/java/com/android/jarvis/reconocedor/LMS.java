package com.android.jarvis.reconocedor;

public class LMS {

    private final double[] senalEntrada; // x = d + v1 (señal deseada + ruido1)
    private double[] senalRuido; // v2 (ruido2)
    private double[] senalDeseada; // est_d (estimacion de la señal deseada)

    public LMS(double[] senalEntrada, double[] senalRuido) {
        this.senalEntrada = senalEntrada;
        this.senalRuido = senalRuido;
        this.senalDeseada = null;
    }

    public double[] getSenalEntrada() {
        return senalEntrada;
    }

    public double[] getSenalRuido() {
        return senalRuido;
    }

    public double[] getSenalDeseada() {
        return senalDeseada;
    }

    public void LMS(double mu, int nord, double[] a0) {
        verificarLongitudArreglos();
        int N = senalEntrada.length;
        double[] W = a0; //coeficientes del LMS
        if (W == null) {
            W = zeros(nord);
        }
        senalDeseada = new double[N]; //estimacion de d(n)
        double[] ruido = Utilitarios.concatenar(zeros(nord-1), senalRuido);
        double[] X;
        for (int i = 0; i < N; i++) {
            X = Utilitarios.recortar(ruido, i+nord-1, i);
            // Y = Wt*X (salida del filtro o estimacion de v1(n))
            // est_d(n) = x(n) - est_v1(n)
            senalDeseada[i] = senalEntrada[i] - FIR(W, X);
            // Wi(n+1) = Wi(n) + u*e(n)*X
            for (int j = 0; j < nord; j++) {
                W[j] = W[j] + mu*senalDeseada[i]*X[j];
            }
        }
    }

    public void NLMS(double beta, int nord, double[] a0) {
        verificarLongitudArreglos();
        int N = senalEntrada.length;
        double[] W = a0; //coeficientes del LMS
        if (W == null) {
            W = zeros(nord);
        }
        senalDeseada = new double[N]; //estimacion de d(n)
        double[] ruido = Utilitarios.concatenar(zeros(nord-1), senalRuido);
        double[] X;
        double DEN;
        for (int i = 0; i < N; i++) {
            X = Utilitarios.recortar(ruido, i+nord-1, i);
            // Y = Wt*X (salida del filtro o estimacion de v1(n))
            // est_d(n) = x(n) - est_v1(n)
            senalDeseada[i] = senalEntrada[i] - FIR(W, X);
            // DEN = ||x||!2 + alfa
            DEN = FIR(X, X) + 0.0001;
            // Wi(n+1) = Wi(n) + b*(X/||x||!2 + alfa)*e(n)
            for (int j = 0; j < nord; j++) {
                W[j] = W[j] + (beta/DEN)*X[j]*senalDeseada[i];
            }
        }
    }

    private void verificarLongitudArreglos() {
        int nse = senalEntrada.length;
        int nsr = senalRuido.length;
        if (nse != nsr) {
            if (nse < nsr) {
                senalRuido = Utilitarios.recortar(senalRuido, 0, nse-1);
            } else {
                double[] sr = new double[nse];
                for (int i = 0; i < nse; i++) {
                    if (i < nsr) {
                        sr[i] = senalRuido[i];
                    } else {
                        sr[i] = 0;
                    }
                }
                senalRuido = sr;
            }
        }
    }

    private double[] zeros(int n) {
        double[] a0 = new double[n];
        for (int i = 0; i < n; i++) {
            a0[i] = 0.0;
        }
        return a0;
    }

    private double FIR(double[] a, double[] b) {
        return Utilitarios.getSuma(Utilitarios.multiplicar(a, b));
    }

}
