package reconocedor;

import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class Grafica extends Canvas {
    
    private int ancho, altura;
    private double escalaX, escalaY;
    private double xMax, xMin, yMax, yMin;
    private double xEspacioMarcas, yEspacioMarcas;
    private double xIntervaloMarcas, yIntervaloMarcas;
    
    private int tipoGrafica;   //grafica una señal o un espectro
    private double[] datosV;   //datos para grafica de amplitud de la señal
    private double[][] datosM; //datos para grafica del espectrograma
    
    private boolean si; //habilita limites para segmentos inutiles
    private int pi, pf; //puntos de inicio y fin para segmentos inutiles
    
    public Grafica() {
        this.tipoGrafica = -1;
        this.datosV = null;
        this.datosM = null;
        this.si = false;
        this.pi = -1;
        this.pf = -1;
    }
    
    public void limpiarCanvas() {
        this.tipoGrafica = -1;
        this.datosV = null;
        this.datosM = null;
        this.si = false;
        this.pi = -1;
        this.pf = -1;
    }
    
    public void tamano(int ancho, int altura) {
        this.setSize(ancho, altura);
    }

    public void setTipoGrafica(int tipoGrafica) {
        this.tipoGrafica = tipoGrafica;
    }
    
    public void setSI(boolean si) {
        this.si = si;
    }
    
    public void setPosicionSI(int pi, int pf) {
        this.pi = pi;
        this.pf = pf;
    }
			
    public void graficarDatos(double[] datos, double mayor) {
        this.datosV = datos;
        xMin = 0.0;
        xMax = datos.length;
        yMax = mayor;
        if (tipoGrafica == 1) {
            yMin = -2; //para fourier
        } else {
            yMin = -mayor;
        }
        ancho = this.getWidth();
        altura = this.getHeight();
        xEspacioMarcas = (yMax-yMin)/50;
        yEspacioMarcas = (xMax-xMin)/50;
        xIntervaloMarcas = xMax/100;
        yIntervaloMarcas = yMax/5;
        escalaX = ancho/(xMax-xMin);
        escalaY = altura/(yMax-yMin);
    }
    
    public void graficarDatos(double[][] datos) {
        this.datosM = datos;    
        xMin = 0.0;
        xMax = datos.length;    //filas - tiempo 
        yMax = datos[0].length; //columnas - frecuencia
        yMin = -2;
        ancho = this.getWidth();
        altura = this.getHeight();
        xEspacioMarcas = (yMax-yMin)/50;  
        yEspacioMarcas = (xMax-xMin)/50;
        xIntervaloMarcas = xMax/100;
        yIntervaloMarcas = yMax/5;
        escalaX = ancho/(xMax-xMin);
        escalaY = altura/(yMax-yMin);
    }

    public int obtenerx(double x) {
        int valx = (int)(x*escalaX);
        return valx;
    }

    public int obtenery(double y) {	
        double valory = (yMax+yMin)-y;
        int valy = (int)(valory*escalaY);
        return valy;		
    }	
	    
    public void dibujarEjes(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(""+(int)xMin, obtenerx(xMin), obtenery(xEspacioMarcas/2)-2);
        g.drawString(""+(int)yMin, obtenerx(yEspacioMarcas/2)+2, obtenery(yMin));
        String xMaxStr = ""+(int)xMax;
        char[] array = xMaxStr.toCharArray();
        FontMetrics fontMetrics = g.getFontMetrics();
        Rectangle2D r2d = fontMetrics.getStringBounds(array, 0, array.length, g);
        int labWidth = (int)(r2d.getWidth());
        int labHeight =(int)(r2d.getHeight());
        g.drawString(""+(int)xMax, obtenerx(xMax)-labWidth, obtenery(xEspacioMarcas/2)-2);
        g.drawString(""+(int)yMax, obtenerx(yEspacioMarcas/2)+2, obtenery(yMax)+(int)(labHeight/1.5));
        g.drawLine(obtenerx(xMin), obtenery(0.0), obtenerx(xMax), obtenery(0.0));
        g.drawLine(obtenerx(0.0), obtenery(yMin), obtenerx(0.0), obtenery(yMax));
        xDivisiones(g);
        yDivisiones(g);
    }

    void xDivisiones(Graphics g) {
        double xDoub = 0;
        int x;
        int topEnd = obtenery(xEspacioMarcas/2);
        int bottomEnd = obtenery(-xEspacioMarcas/2);	   
        if (topEnd < 5) {
          topEnd = 5;
          bottomEnd = -5;
        }
        while(xDoub < xMax) {
          x = obtenerx(xDoub);
          g.drawLine(x, topEnd, x, bottomEnd);
          xDoub += xIntervaloMarcas;
        }
        xDoub = 0;
        while (xDoub > xMin) {
          x = obtenerx(xDoub);
          g.drawLine(x, topEnd, x, bottomEnd);
          xDoub -= xIntervaloMarcas;
        }
    }

    void yDivisiones(Graphics g) {
        double yDoub = 0;
        int y;
        int rightEnd = obtenerx(yEspacioMarcas/2);
        int leftEnd = obtenerx(-yEspacioMarcas/2);
        while (yDoub < yMax) {
          y = obtenery(yDoub);
          g.drawLine(rightEnd, y, leftEnd, y);
          yDoub += yIntervaloMarcas;
        }
        yDoub = 0;
        while (yDoub > yMin) {
          y = obtenery(yDoub);
          g.drawLine(rightEnd, y, leftEnd, y);
          yDoub -= yIntervaloMarcas;
        }
    }
	    
    @Override
    public void paint(Graphics g) {
	try {
            int x1, y1, x2, y2;
            if (tipoGrafica == 0 || tipoGrafica == 1) {
                //establece el origen de coordenadas
                g.translate((int)((0-xMin)*escalaX), (int)((0-yMin)*escalaY));
                dibujarEjes(g);
                g.setColor(Color.blue);
                int valorx = (int)xMin;
                x1 = obtenerx(valorx); 
                y1 = obtenery(datosV[0]);
                while (valorx < xMax) {
                    x2 = obtenerx(valorx);
                    y2 = obtenery(datosV[valorx]);                    
                    g.drawLine(x1, y1, x2, y2);
                    //incrementa a travez del eje de las x
                    valorx++;
                    //guarda el punto para usarlo como punto de inicio 
                    //para dibujar el siguiente segmento de linea
                    x1 = x2;
                    y1 = y2;
                }
                if (si) {
                    g.setColor(Color.GREEN);
                    g.drawLine(obtenerx(pi), -altura/2, obtenerx(pi), altura/2);
                    g.drawLine(obtenerx(pf), -altura/2, obtenerx(pf), altura/2);
                }
            } else if (tipoGrafica == 2) { //dibujar espectro
                g.translate((int)((0-xMin)*escalaX), (int)((0-yMin)*escalaY));
                dibujarEjes(g);
                Graphics2D g2 = (Graphics2D)g;
                for (int i = 0; i < (int)xMax; i++) {
                    x1 = obtenerx(i);
                    for (int j = 0; j < (int)yMax; j++) {
	    		y1 = obtenery(j);
                        g2.setColor(TABLA_COLOR[(int)datosM[i][j]]);
                        if (ancho > xMax) {
                            g2.fillRect(x1, y1, ancho/(int)xMax + 1, 1);
                        } else {
                            g2.fillRect(x1, y1, 1, 1);
                        }
                    }		
                }
            }	
	} catch(Exception e){}		
    }
    
    //matrizEspectroFourier
    final static Color[] TABLA_COLOR = {
        new Color(0.0f, 0.0f, 0.5625f),
        new Color(0.0f, 0.0f, 0.5625f),
        new Color(0.0f, 0.0f, 0.5625f),
        new Color(0.0f, 0.0f, 0.5625f),
        new Color(0.0f, 0.0f, 0.6250f),
        new Color(0.0f, 0.0f, 0.6250f),
        new Color(0.0f, 0.0f, 0.6250f),
        new Color(0.0f, 0.0f, 0.6250f),
        new Color(0.0f, 0.0f, 0.6875f),
        new Color(0.0f, 0.0f, 0.6875f),
        new Color(0.0f, 0.0f, 0.6875f),
        new Color(0.0f, 0.0f, 0.6875f),
        new Color(0.0f, 0.0f, 0.7500f),
        new Color(0.0f, 0.0f, 0.7500f),
        new Color(0.0f, 0.0f, 0.7500f),
        new Color(0.0f, 0.0f, 0.7500f),
        new Color(0.0f, 0.0f, 0.8125f),
        new Color(0.0f, 0.0f, 0.8125f),
        new Color(0.0f, 0.0f, 0.8125f),
        new Color(0.0f, 0.0f, 0.8125f),
        new Color(0.0f, 0.0f, 0.8750f),
        new Color(0.0f, 0.0f, 0.8750f),
        new Color(0.0f, 0.0f, 0.8750f),
        new Color(0.0f, 0.0f, 0.8750f),
        new Color(0.0f, 0.0f, 0.9375f),
        new Color(0.0f, 0.0f, 0.9375f),
        new Color(0.0f, 0.0f, 0.9375f),
        new Color(0.0f, 0.0f, 0.9375f),
        new Color(0.0f, 0.0f, 1.0f),
        new Color(0.0f, 0.0f, 1.0f),
        new Color(0.0f, 0.0f, 1.0f),
        new Color(0.0f, 0.0f, 1.0f),
        new Color(0.0f, 0.0625f, 1.0f),
        new Color(0.0f, 0.0625f, 1.0f),
        new Color(0.0f, 0.0625f, 1.0f),
        new Color(0.0f, 0.0625f, 1.0f),
        new Color(0.0f, 0.1250f, 1.0f),
        new Color(0.0f, 0.1250f, 1.0f),
        new Color(0.0f, 0.1250f, 1.0f),
        new Color(0.0f, 0.1250f, 1.0f),
        new Color(0.0f, 0.1875f, 1.0f),
        new Color(0.0f, 0.1875f, 1.0f),
        new Color(0.0f, 0.1875f, 1.0f),
        new Color(0.0f, 0.1875f, 1.0f),
        new Color(0.0f, 0.2500f, 1.0f),
        new Color(0.0f, 0.2500f, 1.0f),
        new Color(0.0f, 0.2500f, 1.0f),
        new Color(0.0f, 0.2500f, 1.0f),
        new Color(0.0f, 0.3125f, 1.0f),
        new Color(0.0f, 0.3125f, 1.0f),
        new Color(0.0f, 0.3125f, 1.0f),
        new Color(0.0f, 0.3125f, 1.0f),
        new Color(0.0f, 0.3750f, 1.0f),
        new Color(0.0f, 0.3750f, 1.0f),
        new Color(0.0f, 0.3750f, 1.0f),
        new Color(0.0f, 0.3750f, 1.0f),
        new Color(0.0f, 0.4375f, 1.0f),
        new Color(0.0f, 0.4375f, 1.0f),
        new Color(0.0f, 0.4375f, 1.0f),
        new Color(0.0f, 0.4375f, 1.0f),
        new Color(0.0f, 0.5000f, 1.0f),
        new Color(0.0f, 0.5000f, 1.0f),
        new Color(0.0f, 0.5000f, 1.0f),
        new Color(0.0f, 0.5000f, 1.0f),
        new Color(0.0f, 0.5625f, 1.0f),
        new Color(0.0f, 0.5625f, 1.0f),
        new Color(0.0f, 0.5625f, 1.0f),
        new Color(0.0f, 0.5625f, 1.0f),
        new Color(0.0f, 0.6250f, 1.0f),
        new Color(0.0f, 0.6250f, 1.0f),
        new Color(0.0f, 0.6250f, 1.0f),
        new Color(0.0f, 0.6250f, 1.0f),
        new Color(0.0f, 0.6875f, 1.0f),
        new Color(0.0f, 0.6875f, 1.0f),
        new Color(0.0f, 0.6875f, 1.0f),
        new Color(0.0f, 0.6875f, 1.0f),
        new Color(0.0f, 0.7500f, 1.0f),
        new Color(0.0f, 0.7500f, 1.0f),
        new Color(0.0f, 0.7500f, 1.0f),
        new Color(0.0f, 0.7500f, 1.0f),
        new Color(0.0f, 0.8125f, 1.0f),
        new Color(0.0f, 0.8125f, 1.0f),
        new Color(0.0f, 0.8125f, 1.0f),
        new Color(0.0f, 0.8125f, 1.0f),
        new Color(0.0f, 0.8750f, 1.0f),
        new Color(0.0f, 0.8750f, 1.0f),
        new Color(0.0f, 0.8750f, 1.0f),
        new Color(0.0f, 0.8750f, 1.0f),
        new Color(0.0f, 0.9375f, 1.0f),
        new Color(0.0f, 0.9375f, 1.0f),
        new Color(0.0f, 0.9375f, 1.0f),
        new Color(0.0f, 0.9375f, 1.0f),
        new Color(0.0f, 1.0f, 1.0f),
        new Color(0.0f, 1.0f, 1.0f),
        new Color(0.0f, 1.0f, 1.0f),
        new Color(0.0f, 1.0f, 1.0f),
        new Color(0.0625f, 1.0f, 0.9375f),
        new Color(0.0625f, 1.0f, 0.9375f),
        new Color(0.0625f, 1.0f, 0.9375f),
        new Color(0.0625f, 1.0f, 0.9375f),
        new Color(0.1250f, 1.0f, 0.8750f),
        new Color(0.1250f, 1.0f, 0.8750f),
        new Color(0.1250f, 1.0f, 0.8750f),
        new Color(0.1250f, 1.0f, 0.8750f),
        new Color(0.1875f, 1.0f, 0.8125f),
        new Color(0.1875f, 1.0f, 0.8125f),
        new Color(0.1875f, 1.0f, 0.8125f),
        new Color(0.1875f, 1.0f, 0.8125f),
        new Color(0.2500f, 1.0f, 0.7500f),
        new Color(0.2500f, 1.0f, 0.7500f),
        new Color(0.2500f, 1.0f, 0.7500f),
        new Color(0.2500f, 1.0f, 0.7500f),
        new Color(0.3125f, 1.0f, 0.6875f),
        new Color(0.3125f, 1.0f, 0.6875f),
        new Color(0.3125f, 1.0f, 0.6875f),
        new Color(0.3125f, 1.0f, 0.6875f),
        new Color(0.3750f, 1.0f, 0.6250f),
        new Color(0.3750f, 1.0f, 0.6250f),
        new Color(0.3750f, 1.0f, 0.6250f),
        new Color(0.3750f, 1.0f, 0.6250f),
        new Color(0.4375f, 1.0f, 0.5625f),
        new Color(0.4375f, 1.0f, 0.5625f),
        new Color(0.4375f, 1.0f, 0.5625f),
        new Color(0.4375f, 1.0f, 0.5625f),
        new Color(0.5000f, 1.0f, 0.5000f),
        new Color(0.5000f, 1.0f, 0.5000f),
        new Color(0.5000f, 1.0f, 0.5000f),
        new Color(0.5000f, 1.0f, 0.5000f),
        new Color(0.5625f, 1.0f, 0.4375f),
        new Color(0.5625f, 1.0f, 0.4375f),
        new Color(0.5625f, 1.0f, 0.4375f),
        new Color(0.5625f, 1.0f, 0.4375f),
        new Color(0.6250f, 1.0f, 0.3750f),
        new Color(0.6250f, 1.0f, 0.3750f),
        new Color(0.6250f, 1.0f, 0.3750f),
        new Color(0.6250f, 1.0f, 0.3750f),
        new Color(0.6875f, 1.0f, 0.3125f),
        new Color(0.6875f, 1.0f, 0.3125f),
        new Color(0.6875f, 1.0f, 0.3125f),
        new Color(0.6875f, 1.0f, 0.3125f),
        new Color(0.7500f, 1.0f, 0.2500f),
        new Color(0.7500f, 1.0f, 0.2500f),
        new Color(0.7500f, 1.0f, 0.2500f),
        new Color(0.7500f, 1.0f, 0.2500f),
        new Color(0.8125f, 1.0f, 0.1875f),
        new Color(0.8125f, 1.0f, 0.1875f),
        new Color(0.8125f, 1.0f, 0.1875f),
        new Color(0.8125f, 1.0f, 0.1875f),
        new Color(0.8750f, 1.0f, 0.1250f),
        new Color(0.8750f, 1.0f, 0.1250f),
        new Color(0.8750f, 1.0f, 0.1250f),
        new Color(0.8750f, 1.0f, 0.1250f),
        new Color(0.9375f, 1.0f, 0.0625f),
        new Color(0.9375f, 1.0f, 0.0625f),
        new Color(0.9375f, 1.0f, 0.0625f),
        new Color(0.9375f, 1.0f, 0.0625f),
        new Color(1.0f, 1.0f, 0.0f),
        new Color(1.0f, 1.0f, 0.0f),
        new Color(1.0f, 1.0f, 0.0f),
        new Color(1.0f, 1.0f, 0.0f),
        new Color(1.0f, 0.9375f, 0.0f),
        new Color(1.0f, 0.9375f, 0.0f),
        new Color(1.0f, 0.9375f, 0.0f),
        new Color(1.0f, 0.9375f, 0.0f),
        new Color(1.0f, 0.8750f, 0.0f),
        new Color(1.0f, 0.8750f, 0.0f),
        new Color(1.0f, 0.8750f, 0.0f),
        new Color(1.0f, 0.8750f, 0.0f),
        new Color(1.0f, 0.8125f, 0.0f),
        new Color(1.0f, 0.8125f, 0.0f),
        new Color(1.0f, 0.8125f, 0.0f),
        new Color(1.0f, 0.8125f, 0.0f),
        new Color(1.0f, 0.7500f, 0.0f),
        new Color(1.0f, 0.7500f, 0.0f),
        new Color(1.0f, 0.7500f, 0.0f),
        new Color(1.0f, 0.7500f, 0.0f),
        new Color(1.0f, 0.6875f, 0.0f),
        new Color(1.0f, 0.6875f, 0.0f),
        new Color(1.0f, 0.6875f, 0.0f),
        new Color(1.0f, 0.6875f, 0.0f),
        new Color(1.0f, 0.6250f, 0.0f),
        new Color(1.0f, 0.6250f, 0.0f),
        new Color(1.0f, 0.6250f, 0.0f),
        new Color(1.0f, 0.6250f, 0.0f),
        new Color(1.0f, 0.5625f, 0.0f),
        new Color(1.0f, 0.5625f, 0.0f),
        new Color(1.0f, 0.5625f, 0.0f),
        new Color(1.0f, 0.5625f, 0.0f),
        new Color(1.0f, 0.5000f, 0.0f),
        new Color(1.0f, 0.5000f, 0.0f),
        new Color(1.0f, 0.5000f, 0.0f),
        new Color(1.0f, 0.5000f, 0.0f),
        new Color(1.0f, 0.4375f, 0.0f),
        new Color(1.0f, 0.4375f, 0.0f),
        new Color(1.0f, 0.4375f, 0.0f),
        new Color(1.0f, 0.4375f, 0.0f),
        new Color(1.0f, 0.3750f, 0.0f),
        new Color(1.0f, 0.3750f, 0.0f),
        new Color(1.0f, 0.3750f, 0.0f),
        new Color(1.0f, 0.3750f, 0.0f),
        new Color(1.0f, 0.3125f, 0.0f),
        new Color(1.0f, 0.3125f, 0.0f),
        new Color(1.0f, 0.3125f, 0.0f),
        new Color(1.0f, 0.3125f, 0.0f),
        new Color(1.0f, 0.2500f, 0.0f),
        new Color(1.0f, 0.2500f, 0.0f),
        new Color(1.0f, 0.2500f, 0.0f),
        new Color(1.0f, 0.2500f, 0.0f),
        new Color(1.0f, 0.1875f, 0.0f),
        new Color(1.0f, 0.1875f, 0.0f),
        new Color(1.0f, 0.1875f, 0.0f),
        new Color(1.0f, 0.1875f, 0.0f),
        new Color(1.0f, 0.1250f, 0.0f),
        new Color(1.0f, 0.1250f, 0.0f),
        new Color(1.0f, 0.1250f, 0.0f),
        new Color(1.0f, 0.1250f, 0.0f),
        new Color(1.0f, 0.0625f, 0.0f),
        new Color(1.0f, 0.0625f, 0.0f),
        new Color(1.0f, 0.0625f, 0.0f),
        new Color(1.0f, 0.0625f, 0.0f),
        new Color(1.0f, 0.0f, 0.0f),
        new Color(1.0f, 0.0f, 0.0f),
        new Color(1.0f, 0.0f, 0.0f),
        new Color(1.0f, 0.0f, 0.0f),
        new Color(0.9375f, 0.0f, 0.0f),
        new Color(0.9375f, 0.0f, 0.0f),
        new Color(0.9375f, 0.0f, 0.0f),
        new Color(0.9375f, 0.0f, 0.0f),
        new Color(0.8750f, 0.0f, 0.0f),
        new Color(0.8750f, 0.0f, 0.0f),
        new Color(0.8750f, 0.0f, 0.0f),
        new Color(0.8750f, 0.0f, 0.0f),
        new Color(0.8125f, 0.0f, 0.0f),
        new Color(0.8125f, 0.0f, 0.0f),
        new Color(0.8125f, 0.0f, 0.0f),
        new Color(0.8125f, 0.0f, 0.0f),
        new Color(0.7500f, 0.0f, 0.0f),
        new Color(0.7500f, 0.0f, 0.0f),
        new Color(0.7500f, 0.0f, 0.0f),
        new Color(0.7500f, 0.0f, 0.0f),
        new Color(0.6875f, 0.0f, 0.0f),
        new Color(0.6875f, 0.0f, 0.0f),
        new Color(0.6875f, 0.0f, 0.0f),
        new Color(0.6875f, 0.0f, 0.0f),
        new Color(0.6250f, 0.0f, 0.0f),
        new Color(0.6250f, 0.0f, 0.0f),
        new Color(0.6250f, 0.0f, 0.0f),
        new Color(0.6250f, 0.0f, 0.0f),
        new Color(0.5625f, 0.0f, 0.0f),
        new Color(0.5625f, 0.0f, 0.0f),
        new Color(0.5625f, 0.0f, 0.0f),
        new Color(0.5625f, 0.0f, 0.0f),
        new Color(0.5000f, 0.0f, 0.0f),
        new Color(0.5000f, 0.0f, 0.0f),
        new Color(0.5000f, 0.0f, 0.0f),
        new Color(0.5000f, 0.0f, 0.0f)};
}
