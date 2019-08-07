package reconocedor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class ArchivoMFCC {

    private final String ruta;
    private String nombreArchivo;
    private String usuario;
    private String palabra;
    private double umbral;
    private double[][] mo;
    
    public ArchivoMFCC(String ruta, String usuario, String palabra, double umbral, double[][] mo) {
        this.ruta = ruta;
        this.nombreArchivo = "";
        this.usuario = usuario;
        this.palabra = palabra;
        this.umbral = umbral;
        this.mo = mo;
    }
    
    public ArchivoMFCC(String ruta) {
        this.ruta = ruta;
        this.nombreArchivo = "";
        this.usuario = "";
        this.palabra = "";
        this.umbral = 0;
        this.mo = null;
    }

    public String getRuta() {
        return ruta;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public String getPalabra() {
        return palabra;
    }
    
    public double[][] getMo() {
        return mo;
    }
    
    public double getUmbral() {
        return umbral;
    }
    
    public void setUmbral(double umbral) {
        this.umbral = umbral;
    }
    
    public void guardarMO() {
        File f = new File(ruta);
        String[] nombre = ruta.split(Pattern.quote("\\"));
        nombreArchivo = nombre[nombre.length-1];
        int nf = mo.length, nc = mo[0].length;
        try {
            PrintWriter fs = new PrintWriter(f);
            fs.println(usuario);
            fs.println(palabra);
            fs.println(umbral);
            fs.print(nf);
            fs.print(" ");
            fs.print(nc);
            fs.println();
            for (int i = 0; i < nf; i++) {
                for (int j = 0; j < nc; j++) {
                    fs.print(mo[i][j]);
                    fs.print(" ");
                }
                fs.println();
            }
            fs.close();
        } catch(FileNotFoundException ex) {
            String msj = "No se pudo guardar el archivo!";
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void abrirMO() {
        File f = new File(ruta);
        String[] nombre = ruta.split(Pattern.quote("\\"));
        nombreArchivo = nombre[nombre.length-1];
        try {
            BufferedReader fIn = new BufferedReader(new FileReader(f));
            usuario = fIn.readLine(); //usuario
            palabra = fIn.readLine(); //palabra
            umbral = Double.valueOf(fIn.readLine()); //umbral
            StringTokenizer tokens = new StringTokenizer(fIn.readLine(), " ");
            try {
                int nf = Integer.valueOf(tokens.nextToken());
                int nc = Integer.valueOf(tokens.nextToken());
                mo = new double[nf][nc];
                for (int i = 0; i < nf; i++) {
                    tokens = new StringTokenizer(fIn.readLine(), " ");
                    for (int j = 0; j < nc; j++) {
                        mo[i][j] = Double.valueOf(tokens.nextToken());
                    }
                }
            } catch (NumberFormatException ex) {
                mo = null;
                String msj = "No se pudo abrir el archivo!";
                JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
            fIn.close();
        } catch(IOException ex) {
            String msj = "Archivo no encontrado!\ncon ruta: "+ruta;
            JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
