package com.android.jarvis.reconocedor;

public class Preprocesamiento {

    //[c,d]: rango de entrada minmimo y maximo
    //[m,n]: rango de salida minmimo y maximo
    public static double[][] normalizar(double[][] me, double c, double d, double m, double n) {
        int nf = me.length;
        int nc = me[0].length;
        double[][] ms = new double[nf][nc];
        double A = (m-n)/(c-d);
        double B = m - A*c;
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                ms[i][j] = A*me[i][j] + B;
                if (ms[i][j] < 0) {
                    ms[i][j] = 0;
                }
            }
        }
        return ms;
    }

    public static double[] normalizarInt(double[] vEntrada, double c, double d, double m, double n) {
        double[] vNormalizado = new double[vEntrada.length];
        double A = (m-n)/(c-d);
        double B = m - A*c;

        for (int i = 0; i < vNormalizado.length; i++) {
            vNormalizado[i] = (int)(A*vEntrada[i] + B);
        }

        return vNormalizado;
    }

    public static double[] normalizarDouble(double[] vEntrada, double c, double d, double m, double n) {
        double[] vNormalizado = new double[vEntrada.length];
        double A = (m-n)/(c-d);
        double B = m - A*c;

        for (int i = 0; i < vNormalizado.length; i++) {
            vNormalizado[i] = A*vEntrada[i] + B;
        }

        return vNormalizado;
    }

    public static double[] normalizar(double[] vEntrada, double max) {
        double[] vNormalizado = new double[vEntrada.length];
        double m = -1, n = 1, c = -max, d = max;
        double A = (m-n)/(c-d);
        double B = m - A*c;

        for (int i = 0; i < vNormalizado.length; i++) {
            vNormalizado[i] = A*vEntrada[i] + B;
        }

        return vNormalizado;
    }

    public static int[] eliminarSegmentosInutiles_Energia(int tamTrama, double umbral, double[] vEntrada) {
        double[] vEnergias = Preprocesamiento.getEnergias(tamTrama, vEntrada);
        int n = vEnergias.length, pi = 0, pf = vEntrada.length-1;
        double umb = umbral*Preprocesamiento.energia(vEntrada)/n;
        double e1, e2, e3;

        for (int i = 0; i < n; i++) {
            if (i+2 >= n || i+1 >= n) {
                break;
            }
            e1 = vEnergias[i]; e2 = vEnergias[i+1]; e3 = vEnergias[i+2];
            if (e1 > umb && e2 > umb && e3 > umb) {
                pi = tamTrama*i;
                break;
            }
        }
        for (int i = n-1; i >= 0; i--) {
            if (i-2 < 0 || i-1 < 0) {
                break;
            }
            e1 = vEnergias[i]; e2 = vEnergias[i-1]; e3 = vEnergias[i-2];
            if (e1 > umb && e2 > umb && e3 > umb) {
                if (i < n-1)
                    pf = tamTrama*(i+1);
                break;
            }
        }

        int[] puntos = new int[2];
        puntos[0] = pi; puntos[1] = pf;

        return puntos;
    }

    public static double[] getEnergias(int tamTrama, double[] vEntrada) {
        int n = vEntrada.length;
        double[] vAux, vSalida;

        if (n%tamTrama == 0) {
            vSalida = new double[n/tamTrama];
        } else {
            vSalida = new double[n/tamTrama+1];
        }
        for (int i = 0, k = 0; i < n; i+=tamTrama, k++) {
            if (i+tamTrama >= n) {
                vAux = Utilitarios.recortar(vEntrada, i, n-1);
            } else {
                vAux = Utilitarios.recortar(vEntrada, i, i+tamTrama-1);
            }
            vSalida[k] = Preprocesamiento.energia(vAux);
        }

        return vSalida;
    }

    public static double energia(double[] vEntrada) {
        double e = 0.0;
        for (int i = 0; i < vEntrada.length; i++) {
            e += Math.pow(vEntrada[i], 2);
        }
        return e;
    }

    public static int[] eliminarSegmentosInutiles_RabinerSambur(int tamTrama, double[] vEntrada, int tipo) {
        int[] puntos = new int[2];

        if (tipo == 1) {
            puntos[0] = Preprocesamiento.rabinerSambur1(tamTrama, vEntrada);
            puntos[1] = Preprocesamiento.rabinerSambur1(tamTrama, Utilitarios.invertir(vEntrada));
        } else {
            puntos[0] = Preprocesamiento.rabinerSambur2(tamTrama, vEntrada);
            puntos[1] = Preprocesamiento.rabinerSambur2(tamTrama, Utilitarios.invertir(vEntrada));
        }

        puntos[0] = puntos[0]*tamTrama;
        puntos[1] = (vEntrada.length - 1) - (puntos[1]*tamTrama);

        return puntos;
    }

    public static int rabinerSambur1(int tamTrama, double[] vEntrada) {
        //PASO 1.1: Obtener la Magnitud Promedio
        double[] M = Preprocesamiento.getMagnitudes(tamTrama, vEntrada);
        //PASO 1.2: Obtener el Cruce por Ceros de la Señal
        double[] Z = Preprocesamiento.getCrucePorCeros(tamTrama, vEntrada);

        //PASO 2: Obtener Estadisticas del Ruido Ambiental
        //Para ello se escogen las 10 primeras tramas
        double[] Ms = new double[10];
        System.arraycopy(M, 0, Ms, 0, 10);
        double[] Zs = new double[10];
        System.arraycopy(Z, 0, Zs, 0, 10);

        //PASO 3: Obtener Umbrales
        double[] Mx = new double[M.length-10];
        System.arraycopy(M, 10, Mx, 0, M.length-10);
        double UmbSupEnrg = 0.5*Utilitarios.getMayor(Mx);
        double uMs = Preprocesamiento.media(Ms);
        double UmbInfEnrg = uMs + 2*Preprocesamiento.desviacionEstandar(Ms, uMs);
        double uZs = Preprocesamiento.media(Zs);
        double UmbCruCero = uZs + 2*Preprocesamiento.desviacionEstandar(Zs, uZs);
//        double UmbSupEnrg = Utilitarios.maximo(0.5*Utilitarios.mayor(Mx),
//                          uMs + 3*Preprocesamiento.desviacionEstandar(Ms, uMs));

        //PASO 4: Hallar Inicio de la Palabra
        int inicio = 0;
        int in = -1;
        for (int n = 11; n < M.length; n++) {
            if (M[n] > UmbSupEnrg) {
                in = n;
                n = M.length;
            }
        }

        if (in != -1) {
            int ie = -1;
            for (int n = in; n > 10 ; n--) {
                if (M[n] <= UmbInfEnrg) {
                    ie = n;
                    n = 10;
                }
            }
            if (ie != -1) {
                int iz = -1; int cont = 0; int k = 11;
                if (ie-25 >= 1) {
                    k = ie-25;
                }
                for (int n = ie; iz == -1 && n > k; n--) {
                    if (Z[n] <= UmbCruCero) {
                        inicio = ie;
                        cont = 0;
                    } else if (Z[n] > UmbCruCero) {
                        cont++;
                        if (cont < 3 && Z[n-1] < UmbCruCero) {
                            inicio = ie;
                        } else if (cont >= 3) {
                            for (iz = n; iz > k && Z[iz] > UmbCruCero; iz--) {}
                            inicio = iz;
                        }
                    }
                }
            }
        }

        return inicio;
    }

    public static int rabinerSambur2(int tamTrama, double[] vEntrada) {
        //PASO 1.1: Obtener la Magnitud Promedio
        double[] M = Preprocesamiento.getMagnitudes(tamTrama, vEntrada);
        //PASO 1.2: Obtener el Cruce por Ceros de la Señal
        double[] Z = Preprocesamiento.getCrucePorCeros(tamTrama, vEntrada);

        //PASO 2: Obtener Estadisticas de Ruido Ambiental
        //Para ello se escogen las 10 primeras tramas
        double[] Ms = new double[10];
        System.arraycopy(M, 0, Ms, 0, 10);
        double[] Zs = new double[10];
        System.arraycopy(Z, 0, Zs, 0, 10);

        //PASO 3: Obtener Umbrales
        double uZs = Preprocesamiento.media(Zs);
        double UmbCruCero = uZs + 2*Preprocesamiento.desviacionEstandar(Zs, uZs);
        double IF = 25/tamTrama;
        double IZCT = Math.min(IF, UmbCruCero);
        double[] Mx = new double[M.length-10];
        System.arraycopy(M, 10, Mx, 0, M.length-10);
        double IMX = Utilitarios.getMayor(Mx);
        double IMN = Preprocesamiento.media(Ms);
        double I1 = 0.03*(IMX-IMN) + IMN;
        double I2 = 4*IMN;
        double ITL = Math.min(I1, I2);
        double ITU = 5*ITL;

        //PASO 4: Hallar Inicio de la Palabra
        int inicio = 0;
        int N = -1;
        for (int m = 11; m < M.length; m++) {
            if (M[m] >= ITL) {
                for (int i = m; i < M.length; i++) {
                    if (M[i] < ITL) {
                        m = i + 1;
                        i = M.length;
                    } else {
                        if (M[i] >= ITU) {
                            N = i;
                            if (i == m) {
                                N--;
                            }
                            i = M.length;
                            m = M.length;
                        }
                    }
                }
            }
        }
        if (N != -1) {
            int iz = -1; int cont = 0; int k = 11;
            if (N-25 >= 1) {
                k = N-25;
            }
            for (int n = N; iz == -1 && n > k; n--) {
                if (Z[n] < IZCT) {
                    inicio = N;
                    cont = 0;
                } else if (Z[n] >= IZCT) {
                    cont++;
                    if (cont < 3 && Z[n-1] < IZCT) {
                        inicio = N;
                    } else if (cont >= 3) {
                        for (iz = n; iz > k && Z[iz] >= IZCT; iz--) {}
                        inicio = iz;
                    }
                }
            }
        }

        return inicio;
    }

    public static double[] getMagnitudes(int tamTrama, double[] vEntrada) {
        int n = vEntrada.length;
        double[] vAux, vSalida;

        if (n%tamTrama == 0) {
            vSalida = new double[n/tamTrama];
        } else {
            vSalida = new double[n/tamTrama+1];
        }
        for (int i = 0, k = 0; i < n; i+=tamTrama, k++) {
            if (i+tamTrama >= n) {
                vAux = Utilitarios.recortar(vEntrada, i, n-1);
            } else {
                vAux = Utilitarios.recortar(vEntrada, i, i+tamTrama-1);
            }
            vSalida[k] = Preprocesamiento.magnitud(vAux);
        }

        return vSalida;
    }

    public static double magnitud(double[] vEntrada) {
        double m = 0.0;
        for (int i = 0; i < vEntrada.length; i++) {
            m += Math.abs(vEntrada[i]);
        }
        return m;
    }

    public static double[] getCrucePorCeros(int tamTrama, double[] vEntrada) {
        int n = vEntrada.length;
        double[] vAux, vSalida;

        if (n%tamTrama == 0) {
            vSalida = new double[n/tamTrama];
        } else {
            vSalida = new double[n/tamTrama+1];
        }
        for (int i = 0, k = 0; i < n; i+=tamTrama, k++) {
            if (i+tamTrama >= n) {
                vAux = Utilitarios.recortar(vEntrada, i, n-1);
            } else {
                vAux = Utilitarios.recortar(vEntrada, i, i+tamTrama-1);
            }
            vSalida[k] = Preprocesamiento.crucePorCero(vAux);
        }

        return vSalida;
    }

    public static double crucePorCero(double[] vEntrada) {
        double s = 0.0;
        int n = vEntrada.length;

        for (int i = 0; i <= n-2; i++) {
            s += Math.abs(Preprocesamiento.sign(vEntrada[i+1]) -
                          Preprocesamiento.sign(vEntrada[i]));
        }

        return (s/2)/n;
    }

    public static double sign(double x) {
        if (x >= 0) {
            return 1.0;
        } else {
            return -1.0;
        }
    }

    public static double crucePorCero1(double[] vEntrada) {
        double s = 0.0;
        int n = vEntrada.length;

        for (int i = 0; i <= n-2; i++) {
            if (vEntrada[i] > 0 && vEntrada[i+1] < 0) {
                s++;
            } else if (vEntrada[i] < 0 && vEntrada[i+1] > 0) {
                s++;
            }
        }

        return s/n;
    }

    public static double crucePorCero2(double[] vEntrada) {
        double s = 0.0;
        int n = vEntrada.length;

        // Paso 1: x > 0
        double[] p1 = new double[n];
        for (int i = 0; i < n; i++) {
            if (vEntrada[i] > 0) {
                p1[i] = 1;
            } else {
                p1[i] = 0;
            }
        }
        //Paso 2: sum(abs(diff()))
        for (int i = 0; i < n-1; i++) {
            s += Math.abs(p1[i+1] - p1[i]);
        }

        return s/n;
    }

    public static double crucePorCero3(double[] vEntrada) {
        double s = 0.0;
        int n = vEntrada.length;

        //Paso 1: multiplicacion
        double[] p1 = new double[n-1];
        for (int i = 0; i <= n-2; i++) {
            p1[i] = vEntrada[i]*vEntrada[i+1];
        }
        //Paso 2: x <= 0 y sum()
        for (int i = 0; i < n-1; i++) {
            if (p1[i] <= 0) {
                s += 1;
            } else {
                s += 0;
            }
        }

        return s/n;
    }

    public static double media(double[] vEntrada) {
        double m = 0.0;
        int n = vEntrada.length;
        for (int i = 0; i < n; i++) {
            m += vEntrada[i];
        }
        return m/n;
    }

    public static double desviacionEstandar(double[] vEntrada, double media) {
        double s = 0.0;
        int n = vEntrada.length;
        for (int i = 0; i < n; i++) {
            s += Math.pow(vEntrada[i]-media, 2);
        }
        return Math.sqrt(s/(n-1));
    }

    public static double[] preEnfasis(double[] vEntrada, double alfa) {
        int n = vEntrada.length;
        double[] vSalida = new double[n];
        vSalida[0] = vEntrada[0];
        for (int i = 1; i < n; i++) {
            vSalida[i] = vEntrada[i] - alfa*vEntrada[i-1];
        }
        return vSalida;
    }

}
