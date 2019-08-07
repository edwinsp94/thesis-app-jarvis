package reconocedor;

import java.util.ArrayList;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class TomaDecision {
    
    public static void obtenerUmbralesParaDecision(ArrayList<ArchivoMFCC> archivos) {
        if (archivos != null && !archivos.isEmpty()) {
            double[][] tabla = new double[archivos.size()][archivos.size()];
            for (int i = 0; i < archivos.size(); i++) {
                for (int j = i+1; j < archivos.size(); j++) {
                    //if (archivos.get(i).getPalabra().equals(archivos.get(j).getPalabra())) {
                        DTW dtw = new DTW(archivos.get(i).getMo(), archivos.get(j).getMo(), 40, 2);
                        dtw.dtwSimetricoP12();
                        tabla[i][j] = dtw.getDistanciaDTW();
                        tabla[j][i] = dtw.getDistanciaDTW();
                    //}
                }
            }
            
            ArrayList<Double> intraLocutor, interLocutor;
            for (int i = 0; i < archivos.size(); i++) {
                intraLocutor = new ArrayList<>();
                interLocutor = new ArrayList<>();
                for (int j = 0; j < archivos.size(); j++) {
                    if (i != j) {
                        //if (archivos.get(i).getPalabra().equals(archivos.get(j).getPalabra())) {
                            if (archivos.get(i).getUsuario().equals(archivos.get(j).getUsuario())) {
                                intraLocutor.add(tabla[i][j]);
                            } else {
                                interLocutor.add(tabla[i][j]);
                            }
                        //}
                    }
                }
                if (interLocutor.isEmpty()) {
                    archivos.get(i).setUmbral(Preprocesamiento.media(Utilitarios.convertirListToArray(intraLocutor)));
                } else {
                    archivos.get(i).setUmbral(calcularUmbralDecision(Utilitarios.convertirListToArray(intraLocutor),
                                                                     Utilitarios.convertirListToArray(interLocutor)));
                }
                archivos.get(i).guardarMO();
            }
        }
    }
    
    public static double calcularUmbralDecision(double[] intraLocutor, double[] interLocutor) {
        double u_inter = Preprocesamiento.media(interLocutor);
        double u_intra = Preprocesamiento.media(intraLocutor);
        double o_inter = Preprocesamiento.desviacionEstandar(interLocutor, u_inter);
        double o_intra = Preprocesamiento.desviacionEstandar(intraLocutor, u_intra);
        
        return (o_intra*u_inter + o_inter*u_intra)/(o_intra + o_inter);
    }
    
}