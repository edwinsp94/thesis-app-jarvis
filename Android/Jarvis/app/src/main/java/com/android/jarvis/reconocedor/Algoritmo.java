package com.android.jarvis.reconocedor;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;

public class Algoritmo {

    public static ArchivoMFCC crearArchivoMFCC(Audio audioVoz, Audio audioRuido, String URI) {
        double men, may, max;
        double[] senalFinal = audioVoz.getDatos().getAmplitudes();

        if (audioRuido != null) {
            double[] senalEntrada = Preprocesamiento.normalizar(senalFinal, 32768);
            double[] senalRuido = Preprocesamiento.normalizar(audioRuido.getDatos().getAmplitudes(), 32768);
            LMS lms = new LMS(senalEntrada, senalRuido);
            lms.NLMS(0.0025, 128, null);
            senalFinal = lms.getSenalDeseada();
            men = Utilitarios.getMenor(senalFinal);
            may = Utilitarios.getMayor(senalFinal);
            max = Utilitarios.getMaximo(may, men);
            senalFinal = Preprocesamiento.normalizarInt(senalFinal, -max, max, -32768, 32767);
        }

        senalFinal = Preprocesamiento.preEnfasis(senalFinal, 0.95);
        men = Utilitarios.getMenor(senalFinal);
        may = Utilitarios.getMayor(senalFinal);
        max = Utilitarios.getMaximo(may, men);
        if (max < 32768) {
            max = 32768;
        }
        senalFinal = Preprocesamiento.normalizarDouble(senalFinal, -max, max, -1, 1);

        int[] puntos = Preprocesamiento.eliminarSegmentosInutiles_RabinerSambur(128, senalFinal, 1);
        senalFinal = Utilitarios.recortar(senalFinal, puntos[0], puntos[1]);

        Ventanamiento ventanamiento = new Ventanamiento(senalFinal, 448, 288, 1);

        double fs = audioVoz.getFormato().getSampleRate();
        MFCC mfcc = new MFCC(ventanamiento.getMatriz(), 448, 15, 24, fs);
        double[][] moMFCC = mfcc.getResultMFCC(0, fs/2);

        String ruta = audioVoz.getContexto().getFilesDir().getPath();
        String usuario = "", palabra = "";
        if (URI.contains("/")) {
            ruta += "/MFCC/Comandos/"+URI;
            usuario = URI.split("/")[0];
            palabra = URI.split("/")[1];
        } else if(!URI.equals("")) {
            ruta += "/MFCC/Patrones/"+URI;
            usuario = URI;
            palabra = "Clave";
        }
        ruta += "/"+audioVoz.getArchivo().getName().split(".wav")[0]+".mfcc";

        ArchivoMFCC archivoMFCC = new ArchivoMFCC(ruta, usuario, palabra, 0, moMFCC);
        archivoMFCC.guardarMO();

        return archivoMFCC;
    }

    public static ArchivoMFCC obtenerAudioParecido(ArchivoMFCC archivoPrueba, String rutaDirectorio) {
        ArchivoMFCC archivoMFCCParecido = null;
        double menorDistancia = 1000000;

        File[] sub_directorios = new File(rutaDirectorio).listFiles();
        if (sub_directorios != null && sub_directorios.length > 0) {
            for (File sub_directorio : sub_directorios) {
                File[] archivos = sub_directorio.listFiles();
                if (archivos != null && archivos.length > 0) {
                    for (File archivo : archivos) {
                        ArchivoMFCC archivoMFCC = new ArchivoMFCC(archivo.getPath());
                        archivoMFCC.abrirMO();
                        DTW dtw = new DTW(archivoPrueba.getMo(), archivoMFCC.getMo(),40, 2);
                        dtw.dtwSimetricoP12();
                        if (dtw.getDistanciaDTW() < menorDistancia) {
                            menorDistancia = dtw.getDistanciaDTW();
                            archivoMFCCParecido = archivoMFCC;
                        }
                    }
                }
            }
        }

        return archivoMFCCParecido;
    }

    public static String tomaDeDecision(ArchivoMFCC archivoPrueba, ArchivoMFCC archivoEntrena, String tipoReconocedor) {
        String decision;

        DTW dtw = new DTW(archivoPrueba.getMo(), archivoEntrena.getMo(),40, 2);
        dtw.dtwSimetricoP12();
        if (tipoReconocedor.equals("locutor")) {
            if (dtw.getDistanciaDTW() > 0.51) { //0.45 //archivoEntrena.getUmbral()
                decision = "no identificacion";
            } else {
                if (dtw.getDistanciaDTW() < 0.31) {
                    decision = "identificacion correcta";
                } else {
                    decision = "falsa identificacion";
                }
            }
        } else {
            if (dtw.getDistanciaDTW() > 0.31) {
                decision = "no identificacion";
            } else {
                decision = "identificacion correcta";
            }
        }

        return decision;
    }

    public static void obtenerUmbralesParaDecision(@NonNull Context context) {
        File directorio = new File(context.getFilesDir().getPath()+"/MFCC/Patrones");
        ArrayList<ArchivoMFCC> archivosMFCC = new ArrayList<>();

        if (directorio.exists()) {
            File[] subDirectorios = directorio.listFiles();
            if (subDirectorios != null && subDirectorios.length > 0) {
                for (File subDirectorio : subDirectorios) {
                    File[] archivos = subDirectorio.listFiles();
                    if (archivos != null && archivos.length > 0) {
                        for (File archivo : archivos) {
                            ArchivoMFCC archivoMFCC = new ArchivoMFCC(archivo.getPath());
                            archivoMFCC.abrirMO();
                            archivosMFCC.add(archivoMFCC);
                        }
                    }
                }
            }
        }

        if (!archivosMFCC.isEmpty()) {
            double[][] tabla = new double[archivosMFCC.size()][archivosMFCC.size()];
            for (int i = 0; i < archivosMFCC.size(); i++) {
                for (int j = i+1; j < archivosMFCC.size(); j++) {
                    if (archivosMFCC.get(i).getPalabra().equals(archivosMFCC.get(j).getPalabra())) {
                        DTW dtw = new DTW(archivosMFCC.get(i).getMo(), archivosMFCC.get(j).getMo(), 40, 2);
                        dtw.dtwSimetricoP12();
                        tabla[i][j] = dtw.getDistanciaDTW();
                        tabla[j][i] = dtw.getDistanciaDTW();
                    }
                }
            }

            ArrayList<Double> intraLocutor, interLocutor;
            for (int i = 0; i < archivosMFCC.size(); i++) {
                intraLocutor = new ArrayList<>();
                interLocutor = new ArrayList<>();
                for (int j = 0; j < archivosMFCC.size(); j++) {
                    if (i != j) {
                        if (archivosMFCC.get(i).getPalabra().equals(archivosMFCC.get(j).getPalabra())) {
                            if (archivosMFCC.get(i).getUsuario().equals(archivosMFCC.get(j).getUsuario())) {
                                intraLocutor.add(tabla[i][j]);
                            } else {
                                interLocutor.add(tabla[i][j]);
                            }
                        }
                    }
                }
                if (interLocutor.isEmpty()) {
                    archivosMFCC.get(i).setUmbral(Preprocesamiento.media(Utilitarios.convertirListToArray(intraLocutor)));
                } else {
                    archivosMFCC.get(i).setUmbral(calcularUmbralDecision(Utilitarios.convertirListToArray(intraLocutor),
                                                                         Utilitarios.convertirListToArray(interLocutor)));
                }
                archivosMFCC.get(i).guardarMO();
            }
        }
    }

    private static double calcularUmbralDecision(double[] intraLocutor, double[] interLocutor) {
        double u_inter = Preprocesamiento.media(interLocutor);
        double u_intra = Preprocesamiento.media(intraLocutor);
        double o_inter = Preprocesamiento.desviacionEstandar(interLocutor, u_inter);
        double o_intra = Preprocesamiento.desviacionEstandar(intraLocutor, u_intra);

        return (o_intra*u_inter + o_inter*u_intra)/(o_intra + o_inter);
    }
}
