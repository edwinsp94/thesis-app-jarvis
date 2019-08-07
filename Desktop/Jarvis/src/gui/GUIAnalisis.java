package gui;

import java.io.File;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import reconocedor.ArchivoMFCC;
import reconocedor.Audio;
import reconocedor.Datos;
import reconocedor.Fourier;
import reconocedor.Grafica;
import reconocedor.LMS;
import reconocedor.MFCC;
import reconocedor.Preprocesamiento;
import reconocedor.Utilitarios;
import reconocedor.Ventanamiento;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class GUIAnalisis extends javax.swing.JFrame {

    private final JFileChooser fileChooser;
    private Grafica canvas1, canvas2, canvas3, canvas4, canvas5, 
                    canvas6, canvas7, canvas8, canvas9, canvas10;
    private boolean paso1, paso2, paso3, paso4, paso5, paso6, 
                    paso7, paso8, paso9, paso10, paso11;
    private Audio audio1, audio2, audio3, audio4;
    private double[] datosVozPreEnfasis, datosVozPreEnfasis2;
    private Ventanamiento ventanamiento;
    private double[][] moMFCC;
    public String[] respuestaGUI;
    public String[] infoAudios;
    private double alfa;
    
    public GUIAnalisis() {
        initComponents();
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.fileChooser.setFileFilter(
        new FileNameExtensionFilter("Todos los Archivos *.wav", "wav", "WAV"));
        inicializarJTextField();
        inicializarCanvas();
        inicializarPasos();
        this.audio1 = null;
        this.audio2 = null;
        this.audio3 = null;
        this.audio4 = null;
        this.datosVozPreEnfasis = null;
        this.datosVozPreEnfasis2 = null;
        this.ventanamiento = null;
        this.moMFCC = null;
        this.respuestaGUI = null;
        this.infoAudios = new String[2];
        this.alfa = 0;
        recargarTablaMFCC(moMFCC);
    }
    
    private void inicializarCanvas() {
        this.canvas1 = new Grafica();
        this.canvas1.tamano(800, 200);
        this.JP_Grafica1_1.add(canvas1);
        
        this.canvas2 = new Grafica();
        this.canvas2.tamano(800, 200);
        this.JP_Grafica2_1.add(canvas2);
        
        this.canvas3 = new Grafica();
        this.canvas3.tamano(800, 200);
        this.JP_Grafica3_1.add(canvas3);
        
        this.canvas4 = new Grafica();
        this.canvas4.tamano(800, 200);
        this.JP_Grafica4_1.add(canvas4);
        
        this.canvas5 = new Grafica();
        this.canvas5.tamano(800, 200);
        this.JP_Grafica5_1.add(canvas5);
        
        this.canvas6 = new Grafica();
        this.canvas6.tamano(800, 200);
        this.JP_Grafica6_1.add(canvas6);
        
        this.canvas7 = new Grafica();
        this.canvas7.tamano(800, 200);
        this.JP_Grafica7_1.add(canvas7);
        
        this.canvas8 = new Grafica();
        this.canvas8.tamano(800, 200);
        this.JP_Grafica8_1.add(canvas8);
        
        this.canvas9 = new Grafica();
        this.canvas9.tamano(800, 200);
        this.JP_Grafica9_1.add(canvas9);
        
        this.canvas10 = new Grafica();
        this.canvas10.tamano(800, 200);
        this.JP_Grafica10_1.add(canvas10);
    }
    
    private void reiniciarCanvas() {
        this.canvas1.limpiarCanvas();
        this.canvas1.repaint();
        this.canvas2.limpiarCanvas();
        this.canvas2.repaint();
        this.canvas3.limpiarCanvas();
        this.canvas3.repaint();
        this.canvas4.limpiarCanvas();
        this.canvas4.repaint();
        this.canvas5.limpiarCanvas();
        this.canvas5.repaint();
        this.canvas6.limpiarCanvas();
        this.canvas6.repaint();
        this.canvas7.limpiarCanvas();
        this.canvas7.repaint();
        this.canvas8.limpiarCanvas();
        this.canvas8.repaint();
        this.canvas9.limpiarCanvas();
        this.canvas9.repaint();
        this.canvas10.limpiarCanvas();
        this.canvas10.repaint();
    }
    
    private void inicializarPasos() {
        this.paso1 = false;
        this.paso2 = false;
        this.paso3 = false;
        this.paso4 = false;
        this.paso5 = false;
        this.paso6 = false;
        this.paso7 = false;
        this.paso8 = false;
        this.paso9 = false;
        this.paso10 = false;
        this.paso11 = false;
    }
    
    private void inicializarJTextField() {
        JTF_xMen1.setText("");
        JTF_xMay1.setText("");
        JTF_yMen1.setText("");
        JTF_yMay1.setText("");
        JTF_xMen2.setText("");
        JTF_xMay2.setText("");
        JTF_yMen2.setText("");
        JTF_yMay2.setText("");
        JTF_xMen3.setText("");
        JTF_xMay3.setText("");
        JTF_yMen3.setText("");
        JTF_yMay3.setText("");
        JTF_xMen4.setText("");
        JTF_xMay4.setText("");
        JTF_yMen4.setText("");
        JTF_yMay4.setText("");
        JTF_xMen6.setText("");
        JTF_xMay6.setText("");
        JTF_yMen6.setText("");
        JTF_yMay6.setText("");
        JTF_xMen7.setText("");
        JTF_xMay7.setText("");
        JTF_yMen7.setText("");
        JTF_yMay7.setText("");
        JTF_xMen8.setText("");
        JTF_xMay8.setText("");
        JTF_yMen8.setText("");
        JTF_yMay8.setText("");
        JTF_pi.setText("");
        JTF_pf.setText("");
        JTF_xMen5.setText("");
        JTF_xMay5.setText("");
        JTF_yMen5.setText("");
        JTF_yMay5.setText("");
        JTF_xMen9.setText("");
        JTF_xMay9.setText("");
        JTF_yMen9.setText("");
        JTF_yMay9.setText("");
        JTF_xMen10.setText("");
        JTF_xMay10.setText("");
        JTF_yMen10.setText("");
        JTF_yMay10.setText("");
        JTF_vMen10.setText("");
        JTF_vMay10.setText("");
        JTF_TV11.setText("");
        JTF_DS11.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBG_TipoMFCC = new javax.swing.ButtonGroup();
        JP_Fondo = new javax.swing.JPanel();
        JP_Menu = new javax.swing.JPanel();
        JB_Abrir = new javax.swing.JButton();
        JB_Nuevo = new javax.swing.JButton();
        JB_Patrones = new javax.swing.JButton();
        JB_Comparar = new javax.swing.JButton();
        JB_Salir = new javax.swing.JButton();
        JSP_InfoAudio = new javax.swing.JScrollPane();
        JTA_InfoAudio = new javax.swing.JTextArea();
        JTP_Procesamiento = new javax.swing.JTabbedPane();
        JP_Voz = new javax.swing.JPanel();
        JP_Grafica1 = new javax.swing.JPanel();
        JP_Grafica1_1 = new javax.swing.JPanel();
        JP_Comandos1 = new javax.swing.JPanel();
        JL_xMen1 = new javax.swing.JLabel();
        JTF_xMen1 = new javax.swing.JTextField();
        JL_xMay1 = new javax.swing.JLabel();
        JTF_xMay1 = new javax.swing.JTextField();
        JL_yMen1 = new javax.swing.JLabel();
        JTF_yMen1 = new javax.swing.JTextField();
        JL_yMay1 = new javax.swing.JLabel();
        JTF_yMay1 = new javax.swing.JTextField();
        JB_Graficar1 = new javax.swing.JButton();
        JB_Reproducir1 = new javax.swing.JButton();
        JP_VozNormalizada = new javax.swing.JPanel();
        JP_Grafica2 = new javax.swing.JPanel();
        JP_Grafica2_1 = new javax.swing.JPanel();
        JP_Comandos2 = new javax.swing.JPanel();
        JL_xMen2 = new javax.swing.JLabel();
        JTF_xMen2 = new javax.swing.JTextField();
        JL_xMay2 = new javax.swing.JLabel();
        JTF_xMay2 = new javax.swing.JTextField();
        JL_yMen2 = new javax.swing.JLabel();
        JTF_yMen2 = new javax.swing.JTextField();
        JL_yMay2 = new javax.swing.JLabel();
        JTF_yMay2 = new javax.swing.JTextField();
        JB_Graficar2 = new javax.swing.JButton();
        JP_SenalRuido = new javax.swing.JPanel();
        JP_Grafica3 = new javax.swing.JPanel();
        JP_Grafica3_1 = new javax.swing.JPanel();
        JP_Comandos3 = new javax.swing.JPanel();
        JL_xMen3 = new javax.swing.JLabel();
        JTF_xMen3 = new javax.swing.JTextField();
        JL_xMay3 = new javax.swing.JLabel();
        JTF_xMay3 = new javax.swing.JTextField();
        JL_yMen3 = new javax.swing.JLabel();
        JTF_yMen3 = new javax.swing.JTextField();
        JL_yMay3 = new javax.swing.JLabel();
        JTF_yMay3 = new javax.swing.JTextField();
        JB_Cargar3 = new javax.swing.JButton();
        JB_Reproducir3 = new javax.swing.JButton();
        JP_EliminarRuido = new javax.swing.JPanel();
        JP_Grafica4 = new javax.swing.JPanel();
        JP_Grafica4_1 = new javax.swing.JPanel();
        JP_Comandos4 = new javax.swing.JPanel();
        JL_xMen4 = new javax.swing.JLabel();
        JTF_xMen4 = new javax.swing.JTextField();
        JL_xMay4 = new javax.swing.JLabel();
        JTF_xMay4 = new javax.swing.JTextField();
        JL_yMen4 = new javax.swing.JLabel();
        JTF_yMen4 = new javax.swing.JTextField();
        JL_yMay4 = new javax.swing.JLabel();
        JTF_yMay4 = new javax.swing.JTextField();
        JB_Graficar4 = new javax.swing.JButton();
        JB_Reproducir4 = new javax.swing.JButton();
        JP_PreEnfasis = new javax.swing.JPanel();
        JP_Grafica5 = new javax.swing.JPanel();
        JP_Grafica5_1 = new javax.swing.JPanel();
        JP_Comando5 = new javax.swing.JPanel();
        JL_xMen5 = new javax.swing.JLabel();
        JTF_xMen5 = new javax.swing.JTextField();
        JL_xMay5 = new javax.swing.JLabel();
        JTF_xMay5 = new javax.swing.JTextField();
        JL_yMen5 = new javax.swing.JLabel();
        JTF_yMen5 = new javax.swing.JTextField();
        JL_yMay5 = new javax.swing.JLabel();
        JTF_yMay5 = new javax.swing.JTextField();
        JB_Graficar5 = new javax.swing.JButton();
        JP_Energia = new javax.swing.JPanel();
        JP_Grafica6 = new javax.swing.JPanel();
        JP_Grafica6_1 = new javax.swing.JPanel();
        JP_Comandos6 = new javax.swing.JPanel();
        JL_xMen6 = new javax.swing.JLabel();
        JTF_xMen6 = new javax.swing.JTextField();
        JL_xMay6 = new javax.swing.JLabel();
        JTF_xMay6 = new javax.swing.JTextField();
        JL_yMen6 = new javax.swing.JLabel();
        JTF_yMen6 = new javax.swing.JTextField();
        JL_yMay6 = new javax.swing.JLabel();
        JTF_yMay6 = new javax.swing.JTextField();
        JB_Graficar6 = new javax.swing.JButton();
        JP_CrucePorCeros = new javax.swing.JPanel();
        JP_Grafica7 = new javax.swing.JPanel();
        JP_Grafica7_1 = new javax.swing.JPanel();
        JP_Comando7 = new javax.swing.JPanel();
        JL_xMen7 = new javax.swing.JLabel();
        JTF_xMen7 = new javax.swing.JTextField();
        JL_xMay7 = new javax.swing.JLabel();
        JTF_xMay7 = new javax.swing.JTextField();
        JL_yMen7 = new javax.swing.JLabel();
        JTF_yMen7 = new javax.swing.JTextField();
        JL_yMay7 = new javax.swing.JLabel();
        JTF_yMay7 = new javax.swing.JTextField();
        JB_Graficar7 = new javax.swing.JButton();
        JP_EliminarSegmentos = new javax.swing.JPanel();
        JP_Grafica8 = new javax.swing.JPanel();
        JP_Grafica8_1 = new javax.swing.JPanel();
        JP_Comandos8 = new javax.swing.JPanel();
        JL_xMen8 = new javax.swing.JLabel();
        JTF_xMen8 = new javax.swing.JTextField();
        JL_xMay8 = new javax.swing.JLabel();
        JTF_xMay8 = new javax.swing.JTextField();
        JL_yMen8 = new javax.swing.JLabel();
        JTF_yMen8 = new javax.swing.JTextField();
        JL_yMay8 = new javax.swing.JLabel();
        JTF_yMay8 = new javax.swing.JTextField();
        JL_pi = new javax.swing.JLabel();
        JTF_pi = new javax.swing.JTextField();
        JL_pf = new javax.swing.JLabel();
        JTF_pf = new javax.swing.JTextField();
        JB_Graficar8 = new javax.swing.JButton();
        JP_FFT = new javax.swing.JPanel();
        JP_Grafica9 = new javax.swing.JPanel();
        JP_Grafica9_1 = new javax.swing.JPanel();
        JP_Comandos9 = new javax.swing.JPanel();
        JL_xMen9 = new javax.swing.JLabel();
        JTF_xMen9 = new javax.swing.JTextField();
        JL_xMay9 = new javax.swing.JLabel();
        JTF_xMay9 = new javax.swing.JTextField();
        JL_yMen9 = new javax.swing.JLabel();
        JTF_yMen9 = new javax.swing.JTextField();
        JL_yMay9 = new javax.swing.JLabel();
        JTF_yMay9 = new javax.swing.JTextField();
        JB_Graficar9 = new javax.swing.JButton();
        JP_Espectograma = new javax.swing.JPanel();
        JP_Grafica10 = new javax.swing.JPanel();
        JP_Grafica10_1 = new javax.swing.JPanel();
        JP_Comandos10 = new javax.swing.JPanel();
        JL_xMen10 = new javax.swing.JLabel();
        JTF_xMen10 = new javax.swing.JTextField();
        JL_xMay10 = new javax.swing.JLabel();
        JTF_xMay10 = new javax.swing.JTextField();
        JL_yMen10 = new javax.swing.JLabel();
        JTF_yMen10 = new javax.swing.JTextField();
        JL_yMay10 = new javax.swing.JLabel();
        JTF_yMay10 = new javax.swing.JTextField();
        JL_vMen10 = new javax.swing.JLabel();
        JTF_vMen10 = new javax.swing.JTextField();
        JL_vMay10 = new javax.swing.JLabel();
        JTF_vMay10 = new javax.swing.JTextField();
        JB_Graficar10 = new javax.swing.JButton();
        JP_MFCC = new javax.swing.JPanel();
        JSP_MFCC = new javax.swing.JScrollPane();
        JT_MFCC = new javax.swing.JTable();
        JP_Comandos11 = new javax.swing.JPanel();
        JL_TV11 = new javax.swing.JLabel();
        JTF_TV11 = new javax.swing.JTextField();
        JL_DS11 = new javax.swing.JLabel();
        JTF_DS11 = new javax.swing.JTextField();
        JL_TipoMFCC = new javax.swing.JLabel();
        JRB_MFCC13 = new javax.swing.JRadioButton();
        JRB_MFCC26 = new javax.swing.JRadioButton();
        JRB_MFCC39 = new javax.swing.JRadioButton();
        JL_M11 = new javax.swing.JLabel();
        JCB_M11 = new javax.swing.JComboBox<>();
        JL_BC11 = new javax.swing.JLabel();
        JCB_BC11 = new javax.swing.JComboBox<>();
        JB_Obtener11 = new javax.swing.JButton();
        JB_Guardar11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JARVIS (Asistente De Voz) - ANALISIS");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JP_Menu.setBackground(new java.awt.Color(255, 255, 255));
        JP_Menu.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "MENU", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JB_Abrir.setText("Abrir");
        JB_Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_AbrirActionPerformed(evt);
            }
        });

        JB_Nuevo.setText("Nuevo");
        JB_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_NuevoActionPerformed(evt);
            }
        });

        JB_Patrones.setText("Patrones");
        JB_Patrones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_PatronesActionPerformed(evt);
            }
        });

        JB_Comparar.setText("Comparar");
        JB_Comparar.setPreferredSize(new java.awt.Dimension(80, 23));
        JB_Comparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CompararActionPerformed(evt);
            }
        });

        JB_Salir.setText("Salir");
        JB_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_SalirActionPerformed(evt);
            }
        });

        JTA_InfoAudio.setEditable(false);
        JTA_InfoAudio.setColumns(25);
        JTA_InfoAudio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JTA_InfoAudio.setRows(4);
        JTA_InfoAudio.setBorder(null);
        JSP_InfoAudio.setViewportView(JTA_InfoAudio);

        javax.swing.GroupLayout JP_MenuLayout = new javax.swing.GroupLayout(JP_Menu);
        JP_Menu.setLayout(JP_MenuLayout);
        JP_MenuLayout.setHorizontalGroup(
            JP_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JSP_InfoAudio)
                    .addGroup(JP_MenuLayout.createSequentialGroup()
                        .addComponent(JB_Abrir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Patrones, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Comparar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JB_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JP_MenuLayout.setVerticalGroup(
            JP_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_Abrir)
                    .addComponent(JB_Nuevo)
                    .addComponent(JB_Comparar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Salir)
                    .addComponent(JB_Patrones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JSP_InfoAudio, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        JTP_Procesamiento.setBackground(new java.awt.Color(255, 255, 255));
        JTP_Procesamiento.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "PROCESAMIENTO DE SEÑAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JP_Voz.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica1.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica1_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica1_1Layout = new javax.swing.GroupLayout(JP_Grafica1_1);
        JP_Grafica1_1.setLayout(JP_Grafica1_1Layout);
        JP_Grafica1_1Layout.setHorizontalGroup(
            JP_Grafica1_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica1_1Layout.setVerticalGroup(
            JP_Grafica1_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica1Layout = new javax.swing.GroupLayout(JP_Grafica1);
        JP_Grafica1.setLayout(JP_Grafica1Layout);
        JP_Grafica1Layout.setHorizontalGroup(
            JP_Grafica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica1_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica1Layout.setVerticalGroup(
            JP_Grafica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica1_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comandos1.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comandos1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen1.setText("xMen");

        JTF_xMen1.setEditable(false);

        JL_xMay1.setText("xMay");

        JTF_xMay1.setEditable(false);

        JL_yMen1.setText("yMen");

        JTF_yMen1.setEditable(false);

        JL_yMay1.setText("yMay");

        JTF_yMay1.setEditable(false);

        JB_Graficar1.setText("Graficar");
        JB_Graficar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Graficar1ActionPerformed(evt);
            }
        });

        JB_Reproducir1.setText("Reproducir");
        JB_Reproducir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Reproducir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comandos1Layout = new javax.swing.GroupLayout(JP_Comandos1);
        JP_Comandos1.setLayout(JP_Comandos1Layout);
        JP_Comandos1Layout.setHorizontalGroup(
            JP_Comandos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_xMay1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMen1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMay1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(JB_Reproducir1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JB_Graficar1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comandos1Layout.setVerticalGroup(
            JP_Comandos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comandos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_xMen1)
                    .addComponent(JTF_xMen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_xMay1)
                    .addComponent(JTF_xMay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMen1)
                    .addComponent(JTF_yMen1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMay1)
                    .addComponent(JTF_yMay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Graficar1)
                    .addComponent(JB_Reproducir1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_VozLayout = new javax.swing.GroupLayout(JP_Voz);
        JP_Voz.setLayout(JP_VozLayout);
        JP_VozLayout.setHorizontalGroup(
            JP_VozLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_VozLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_VozLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Grafica1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Comandos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_VozLayout.setVerticalGroup(
            JP_VozLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_VozLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comandos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("Señal de Entrada", JP_Voz);

        JP_VozNormalizada.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica2.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica2_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica2_1Layout = new javax.swing.GroupLayout(JP_Grafica2_1);
        JP_Grafica2_1.setLayout(JP_Grafica2_1Layout);
        JP_Grafica2_1Layout.setHorizontalGroup(
            JP_Grafica2_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica2_1Layout.setVerticalGroup(
            JP_Grafica2_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica2Layout = new javax.swing.GroupLayout(JP_Grafica2);
        JP_Grafica2.setLayout(JP_Grafica2Layout);
        JP_Grafica2Layout.setHorizontalGroup(
            JP_Grafica2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica2_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica2Layout.setVerticalGroup(
            JP_Grafica2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica2_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comandos2.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comandos2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen2.setText("xMen");

        JTF_xMen2.setEditable(false);

        JL_xMay2.setText("xMay");

        JTF_xMay2.setEditable(false);

        JL_yMen2.setText("yMen");

        JTF_yMen2.setEditable(false);

        JL_yMay2.setText("yMay");

        JTF_yMay2.setEditable(false);

        JB_Graficar2.setText("Graficar");
        JB_Graficar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Graficar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comandos2Layout = new javax.swing.GroupLayout(JP_Comandos2);
        JP_Comandos2.setLayout(JP_Comandos2Layout);
        JP_Comandos2Layout.setHorizontalGroup(
            JP_Comandos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_xMay2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMen2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMay2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addComponent(JB_Graficar2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comandos2Layout.setVerticalGroup(
            JP_Comandos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comandos2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_xMen2)
                    .addComponent(JTF_xMen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_xMay2)
                    .addComponent(JTF_xMay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMen2)
                    .addComponent(JTF_yMen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMay2)
                    .addComponent(JTF_yMay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Graficar2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_VozNormalizadaLayout = new javax.swing.GroupLayout(JP_VozNormalizada);
        JP_VozNormalizada.setLayout(JP_VozNormalizadaLayout);
        JP_VozNormalizadaLayout.setHorizontalGroup(
            JP_VozNormalizadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_VozNormalizadaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_VozNormalizadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Grafica2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Comandos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_VozNormalizadaLayout.setVerticalGroup(
            JP_VozNormalizadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_VozNormalizadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comandos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("Normalizar", JP_VozNormalizada);

        JP_SenalRuido.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica3.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica3_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica3_1Layout = new javax.swing.GroupLayout(JP_Grafica3_1);
        JP_Grafica3_1.setLayout(JP_Grafica3_1Layout);
        JP_Grafica3_1Layout.setHorizontalGroup(
            JP_Grafica3_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica3_1Layout.setVerticalGroup(
            JP_Grafica3_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica3Layout = new javax.swing.GroupLayout(JP_Grafica3);
        JP_Grafica3.setLayout(JP_Grafica3Layout);
        JP_Grafica3Layout.setHorizontalGroup(
            JP_Grafica3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica3_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica3Layout.setVerticalGroup(
            JP_Grafica3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica3_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comandos3.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comandos3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen3.setText("xMen");

        JTF_xMen3.setEditable(false);

        JL_xMay3.setText("xMay");

        JTF_xMay3.setEditable(false);

        JL_yMen3.setText("yMen");

        JTF_yMen3.setEditable(false);

        JL_yMay3.setText("yMay");

        JTF_yMay3.setEditable(false);

        JB_Cargar3.setText("Cargar");
        JB_Cargar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Cargar3ActionPerformed(evt);
            }
        });

        JB_Reproducir3.setText("Reproducir");
        JB_Reproducir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Reproducir3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comandos3Layout = new javax.swing.GroupLayout(JP_Comandos3);
        JP_Comandos3.setLayout(JP_Comandos3Layout);
        JP_Comandos3Layout.setHorizontalGroup(
            JP_Comandos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_xMay3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMen3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMay3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(JB_Reproducir3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JB_Cargar3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comandos3Layout.setVerticalGroup(
            JP_Comandos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comandos3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_xMen3)
                    .addComponent(JTF_xMen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_xMay3)
                    .addComponent(JTF_xMay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMen3)
                    .addComponent(JTF_yMen3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMay3)
                    .addComponent(JTF_yMay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Cargar3)
                    .addComponent(JB_Reproducir3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_SenalRuidoLayout = new javax.swing.GroupLayout(JP_SenalRuido);
        JP_SenalRuido.setLayout(JP_SenalRuidoLayout);
        JP_SenalRuidoLayout.setHorizontalGroup(
            JP_SenalRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_SenalRuidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_SenalRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Grafica3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Comandos3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_SenalRuidoLayout.setVerticalGroup(
            JP_SenalRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_SenalRuidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comandos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("Señal de Ruido", JP_SenalRuido);

        JP_EliminarRuido.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica4.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica4_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica4_1Layout = new javax.swing.GroupLayout(JP_Grafica4_1);
        JP_Grafica4_1.setLayout(JP_Grafica4_1Layout);
        JP_Grafica4_1Layout.setHorizontalGroup(
            JP_Grafica4_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica4_1Layout.setVerticalGroup(
            JP_Grafica4_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica4Layout = new javax.swing.GroupLayout(JP_Grafica4);
        JP_Grafica4.setLayout(JP_Grafica4Layout);
        JP_Grafica4Layout.setHorizontalGroup(
            JP_Grafica4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica4_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica4Layout.setVerticalGroup(
            JP_Grafica4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica4_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comandos4.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comandos4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen4.setText("xMen");

        JTF_xMen4.setEditable(false);

        JL_xMay4.setText("xMay");

        JTF_xMay4.setEditable(false);

        JL_yMen4.setText("yMen");

        JTF_yMen4.setEditable(false);

        JL_yMay4.setText("yMay");

        JTF_yMay4.setEditable(false);

        JB_Graficar4.setText("Graficar");
        JB_Graficar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Graficar4ActionPerformed(evt);
            }
        });

        JB_Reproducir4.setText("Reproducir");
        JB_Reproducir4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Reproducir4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comandos4Layout = new javax.swing.GroupLayout(JP_Comandos4);
        JP_Comandos4.setLayout(JP_Comandos4Layout);
        JP_Comandos4Layout.setHorizontalGroup(
            JP_Comandos4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_xMay4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMen4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMay4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(JB_Reproducir4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JB_Graficar4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comandos4Layout.setVerticalGroup(
            JP_Comandos4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comandos4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_xMen4)
                    .addComponent(JTF_xMen4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_xMay4)
                    .addComponent(JTF_xMay4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMen4)
                    .addComponent(JTF_yMen4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMay4)
                    .addComponent(JTF_yMay4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Graficar4)
                    .addComponent(JB_Reproducir4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_EliminarRuidoLayout = new javax.swing.GroupLayout(JP_EliminarRuido);
        JP_EliminarRuido.setLayout(JP_EliminarRuidoLayout);
        JP_EliminarRuidoLayout.setHorizontalGroup(
            JP_EliminarRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_EliminarRuidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_EliminarRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Grafica4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Comandos4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_EliminarRuidoLayout.setVerticalGroup(
            JP_EliminarRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_EliminarRuidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comandos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("Eliminar Ruido", JP_EliminarRuido);

        JP_PreEnfasis.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica5.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica5_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica5_1Layout = new javax.swing.GroupLayout(JP_Grafica5_1);
        JP_Grafica5_1.setLayout(JP_Grafica5_1Layout);
        JP_Grafica5_1Layout.setHorizontalGroup(
            JP_Grafica5_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica5_1Layout.setVerticalGroup(
            JP_Grafica5_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica5Layout = new javax.swing.GroupLayout(JP_Grafica5);
        JP_Grafica5.setLayout(JP_Grafica5Layout);
        JP_Grafica5Layout.setHorizontalGroup(
            JP_Grafica5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica5_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica5Layout.setVerticalGroup(
            JP_Grafica5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica5_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comando5.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comando5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen5.setText("xMen");

        JTF_xMen5.setEditable(false);

        JL_xMay5.setText("xMay");

        JTF_xMay5.setEditable(false);

        JL_yMen5.setText("yMen");

        JTF_yMen5.setEditable(false);

        JL_yMay5.setText("yMay");

        JTF_yMay5.setEditable(false);

        JB_Graficar5.setText("Graficar");
        JB_Graficar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Graficar5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comando5Layout = new javax.swing.GroupLayout(JP_Comando5);
        JP_Comando5.setLayout(JP_Comando5Layout);
        JP_Comando5Layout.setHorizontalGroup(
            JP_Comando5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comando5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_xMay5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMen5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMay5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addComponent(JB_Graficar5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comando5Layout.setVerticalGroup(
            JP_Comando5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comando5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comando5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_xMen5)
                    .addComponent(JTF_xMen5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_xMay5)
                    .addComponent(JTF_xMay5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMen5)
                    .addComponent(JTF_yMen5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMay5)
                    .addComponent(JTF_yMay5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Graficar5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_PreEnfasisLayout = new javax.swing.GroupLayout(JP_PreEnfasis);
        JP_PreEnfasis.setLayout(JP_PreEnfasisLayout);
        JP_PreEnfasisLayout.setHorizontalGroup(
            JP_PreEnfasisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PreEnfasisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_PreEnfasisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Grafica5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Comando5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_PreEnfasisLayout.setVerticalGroup(
            JP_PreEnfasisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PreEnfasisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comando5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("Pre-Enfasis", JP_PreEnfasis);

        JP_Energia.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica6.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica6_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica6_1Layout = new javax.swing.GroupLayout(JP_Grafica6_1);
        JP_Grafica6_1.setLayout(JP_Grafica6_1Layout);
        JP_Grafica6_1Layout.setHorizontalGroup(
            JP_Grafica6_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica6_1Layout.setVerticalGroup(
            JP_Grafica6_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica6Layout = new javax.swing.GroupLayout(JP_Grafica6);
        JP_Grafica6.setLayout(JP_Grafica6Layout);
        JP_Grafica6Layout.setHorizontalGroup(
            JP_Grafica6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica6_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica6Layout.setVerticalGroup(
            JP_Grafica6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica6_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comandos6.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comandos6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen6.setText("xMen");

        JTF_xMen6.setEditable(false);

        JL_xMay6.setText("xMay");

        JTF_xMay6.setEditable(false);

        JL_yMen6.setText("yMen");

        JTF_yMen6.setEditable(false);

        JL_yMay6.setText("yMay");

        JTF_yMay6.setEditable(false);

        JB_Graficar6.setText("Graficar");
        JB_Graficar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Graficar6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comandos6Layout = new javax.swing.GroupLayout(JP_Comandos6);
        JP_Comandos6.setLayout(JP_Comandos6Layout);
        JP_Comandos6Layout.setHorizontalGroup(
            JP_Comandos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_xMay6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMen6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMay6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addComponent(JB_Graficar6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comandos6Layout.setVerticalGroup(
            JP_Comandos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comandos6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_xMen6)
                    .addComponent(JTF_xMen6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_xMay6)
                    .addComponent(JTF_xMay6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMen6)
                    .addComponent(JTF_yMen6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMay6)
                    .addComponent(JTF_yMay6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Graficar6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_EnergiaLayout = new javax.swing.GroupLayout(JP_Energia);
        JP_Energia.setLayout(JP_EnergiaLayout);
        JP_EnergiaLayout.setHorizontalGroup(
            JP_EnergiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_EnergiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_EnergiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Grafica6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Comandos6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_EnergiaLayout.setVerticalGroup(
            JP_EnergiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_EnergiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comandos6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("Energia", JP_Energia);

        JP_CrucePorCeros.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica7.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica7_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica7_1Layout = new javax.swing.GroupLayout(JP_Grafica7_1);
        JP_Grafica7_1.setLayout(JP_Grafica7_1Layout);
        JP_Grafica7_1Layout.setHorizontalGroup(
            JP_Grafica7_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica7_1Layout.setVerticalGroup(
            JP_Grafica7_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica7Layout = new javax.swing.GroupLayout(JP_Grafica7);
        JP_Grafica7.setLayout(JP_Grafica7Layout);
        JP_Grafica7Layout.setHorizontalGroup(
            JP_Grafica7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica7_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica7Layout.setVerticalGroup(
            JP_Grafica7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica7_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comando7.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comando7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen7.setText("xMen");

        JTF_xMen7.setEditable(false);

        JL_xMay7.setText("xMay");

        JTF_xMay7.setEditable(false);

        JL_yMen7.setText("yMen");

        JTF_yMen7.setEditable(false);

        JL_yMay7.setText("yMay");

        JTF_yMay7.setEditable(false);

        JB_Graficar7.setText("Graficar");
        JB_Graficar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Graficar7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comando7Layout = new javax.swing.GroupLayout(JP_Comando7);
        JP_Comando7.setLayout(JP_Comando7Layout);
        JP_Comando7Layout.setHorizontalGroup(
            JP_Comando7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comando7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_xMay7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMen7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_yMay7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addComponent(JB_Graficar7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comando7Layout.setVerticalGroup(
            JP_Comando7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comando7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comando7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_xMen7)
                    .addComponent(JTF_xMen7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_xMay7)
                    .addComponent(JTF_xMay7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMen7)
                    .addComponent(JTF_yMen7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMay7)
                    .addComponent(JTF_yMay7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Graficar7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_CrucePorCerosLayout = new javax.swing.GroupLayout(JP_CrucePorCeros);
        JP_CrucePorCeros.setLayout(JP_CrucePorCerosLayout);
        JP_CrucePorCerosLayout.setHorizontalGroup(
            JP_CrucePorCerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_CrucePorCerosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_CrucePorCerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Grafica7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Comando7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_CrucePorCerosLayout.setVerticalGroup(
            JP_CrucePorCerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_CrucePorCerosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comando7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("Cruce Por Ceros", JP_CrucePorCeros);

        JP_EliminarSegmentos.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica8.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica8_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica8_1Layout = new javax.swing.GroupLayout(JP_Grafica8_1);
        JP_Grafica8_1.setLayout(JP_Grafica8_1Layout);
        JP_Grafica8_1Layout.setHorizontalGroup(
            JP_Grafica8_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica8_1Layout.setVerticalGroup(
            JP_Grafica8_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica8Layout = new javax.swing.GroupLayout(JP_Grafica8);
        JP_Grafica8.setLayout(JP_Grafica8Layout);
        JP_Grafica8Layout.setHorizontalGroup(
            JP_Grafica8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica8_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica8Layout.setVerticalGroup(
            JP_Grafica8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica8_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comandos8.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comandos8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen8.setText("xMen");

        JTF_xMen8.setEditable(false);

        JL_xMay8.setText("xMay");

        JTF_xMay8.setEditable(false);

        JL_yMen8.setText("yMen");

        JTF_yMen8.setEditable(false);

        JL_yMay8.setText("yMay");

        JTF_yMay8.setEditable(false);

        JL_pi.setText("pi");

        JTF_pi.setEditable(false);

        JL_pf.setText("pf");

        JTF_pf.setEditable(false);

        JB_Graficar8.setText("Graficar");
        JB_Graficar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Graficar8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comandos8Layout = new javax.swing.GroupLayout(JP_Comandos8);
        JP_Comandos8.setLayout(JP_Comandos8Layout);
        JP_Comandos8Layout.setHorizontalGroup(
            JP_Comandos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_xMay8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_yMen8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_yMay8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_pi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_pi, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_pf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_pf, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addComponent(JB_Graficar8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comandos8Layout.setVerticalGroup(
            JP_Comandos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comandos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_Comandos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_pf)
                        .addComponent(JTF_pf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JB_Graficar8))
                    .addGroup(JP_Comandos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_pi)
                        .addComponent(JTF_pi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JP_Comandos8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_xMen8)
                        .addComponent(JTF_xMen8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_xMay8)
                        .addComponent(JTF_xMay8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_yMen8)
                        .addComponent(JTF_yMen8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_yMay8)
                        .addComponent(JTF_yMay8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_EliminarSegmentosLayout = new javax.swing.GroupLayout(JP_EliminarSegmentos);
        JP_EliminarSegmentos.setLayout(JP_EliminarSegmentosLayout);
        JP_EliminarSegmentosLayout.setHorizontalGroup(
            JP_EliminarSegmentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_EliminarSegmentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_EliminarSegmentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Grafica8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Comandos8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_EliminarSegmentosLayout.setVerticalGroup(
            JP_EliminarSegmentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_EliminarSegmentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comandos8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("Eliminar Segmentos", JP_EliminarSegmentos);

        JP_FFT.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica9.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica9_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica9_1Layout = new javax.swing.GroupLayout(JP_Grafica9_1);
        JP_Grafica9_1.setLayout(JP_Grafica9_1Layout);
        JP_Grafica9_1Layout.setHorizontalGroup(
            JP_Grafica9_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica9_1Layout.setVerticalGroup(
            JP_Grafica9_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica9Layout = new javax.swing.GroupLayout(JP_Grafica9);
        JP_Grafica9.setLayout(JP_Grafica9Layout);
        JP_Grafica9Layout.setHorizontalGroup(
            JP_Grafica9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica9_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica9Layout.setVerticalGroup(
            JP_Grafica9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica9_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comandos9.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comandos9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen9.setText("xMen");

        JTF_xMen9.setEditable(false);

        JL_xMay9.setText("xMay");

        JTF_xMay9.setEditable(false);

        JL_yMen9.setText("yMen");

        JTF_yMen9.setEditable(false);

        JL_yMay9.setText("yMay");

        JTF_yMay9.setEditable(false);

        JB_Graficar9.setText("Graficar");
        JB_Graficar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Graficar9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comandos9Layout = new javax.swing.GroupLayout(JP_Comandos9);
        JP_Comandos9.setLayout(JP_Comandos9Layout);
        JP_Comandos9Layout.setHorizontalGroup(
            JP_Comandos9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_xMay9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_yMen9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_yMay9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                .addComponent(JB_Graficar9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comandos9Layout.setVerticalGroup(
            JP_Comandos9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comandos9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_xMen9)
                    .addComponent(JTF_xMen9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_xMay9)
                    .addComponent(JTF_xMay9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMen9)
                    .addComponent(JTF_yMen9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_yMay9)
                    .addComponent(JTF_yMay9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Graficar9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_FFTLayout = new javax.swing.GroupLayout(JP_FFT);
        JP_FFT.setLayout(JP_FFTLayout);
        JP_FFTLayout.setHorizontalGroup(
            JP_FFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FFTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_FFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Grafica9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Comandos9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_FFTLayout.setVerticalGroup(
            JP_FFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FFTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comandos9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("FFT", JP_FFT);

        JP_Espectograma.setBackground(new java.awt.Color(255, 255, 255));

        JP_Grafica10.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grafica10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JP_Grafica10_1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JP_Grafica10_1Layout = new javax.swing.GroupLayout(JP_Grafica10_1);
        JP_Grafica10_1.setLayout(JP_Grafica10_1Layout);
        JP_Grafica10_1Layout.setHorizontalGroup(
            JP_Grafica10_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JP_Grafica10_1Layout.setVerticalGroup(
            JP_Grafica10_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JP_Grafica10Layout = new javax.swing.GroupLayout(JP_Grafica10);
        JP_Grafica10.setLayout(JP_Grafica10Layout);
        JP_Grafica10Layout.setHorizontalGroup(
            JP_Grafica10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_Grafica10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica10_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_Grafica10Layout.setVerticalGroup(
            JP_Grafica10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Grafica10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica10_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JP_Comandos10.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comandos10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_xMen10.setText("xMen");

        JTF_xMen10.setEditable(false);

        JL_xMay10.setText("xMay");

        JTF_xMay10.setEditable(false);

        JL_yMen10.setText("yMen");

        JTF_yMen10.setEditable(false);

        JL_yMay10.setText("yMay");

        JTF_yMay10.setEditable(false);

        JL_vMen10.setText("vMen");

        JTF_vMen10.setEditable(false);

        JL_vMay10.setText("vMay");

        JTF_vMay10.setEditable(false);

        JB_Graficar10.setText("Graficar");
        JB_Graficar10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Graficar10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comandos10Layout = new javax.swing.GroupLayout(JP_Comandos10);
        JP_Comandos10.setLayout(JP_Comandos10Layout);
        JP_Comandos10Layout.setHorizontalGroup(
            JP_Comandos10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_xMen10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMen10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_xMay10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_xMay10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_yMen10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMen10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_yMay10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_yMay10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_vMen10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_vMen10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_vMay10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_vMay10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(JB_Graficar10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comandos10Layout.setVerticalGroup(
            JP_Comandos10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comandos10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_Comandos10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_vMen10)
                        .addComponent(JTF_vMen10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_vMay10)
                        .addComponent(JTF_vMay10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JP_Comandos10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_xMen10)
                        .addComponent(JTF_xMen10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_xMay10)
                        .addComponent(JTF_xMay10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_yMen10)
                        .addComponent(JTF_yMen10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JL_yMay10)
                        .addComponent(JTF_yMay10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JB_Graficar10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_EspectogramaLayout = new javax.swing.GroupLayout(JP_Espectograma);
        JP_Espectograma.setLayout(JP_EspectogramaLayout);
        JP_EspectogramaLayout.setHorizontalGroup(
            JP_EspectogramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_EspectogramaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_EspectogramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JP_Comandos10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Grafica10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        JP_EspectogramaLayout.setVerticalGroup(
            JP_EspectogramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_EspectogramaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Grafica10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comandos10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("Espectograma", JP_Espectograma);

        JP_MFCC.setBackground(new java.awt.Color(255, 255, 255));

        JT_MFCC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JSP_MFCC.setViewportView(JT_MFCC);

        JP_Comandos11.setBackground(new java.awt.Color(255, 255, 255));
        JP_Comandos11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_TV11.setText("TV:");

        JTF_TV11.setEditable(false);

        JL_DS11.setText("DS:");

        JTF_DS11.setEditable(false);

        JL_TipoMFCC.setText("Tipo:");

        JRB_MFCC13.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoMFCC.add(JRB_MFCC13);
        JRB_MFCC13.setSelected(true);
        JRB_MFCC13.setText("MFCC13");

        JRB_MFCC26.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoMFCC.add(JRB_MFCC26);
        JRB_MFCC26.setText("MFFC26");

        JRB_MFCC39.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoMFCC.add(JRB_MFCC39);
        JRB_MFCC39.setText("MFCC39");

        JL_M11.setText("M:");

        JCB_M11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "13", "14", "15" }));

        JL_BC11.setText("BC:");

        JCB_BC11.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));

        JB_Obtener11.setText("Obtener");
        JB_Obtener11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Obtener11ActionPerformed(evt);
            }
        });

        JB_Guardar11.setText("Guardar");
        JB_Guardar11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_Guardar11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_Comandos11Layout = new javax.swing.GroupLayout(JP_Comandos11);
        JP_Comandos11.setLayout(JP_Comandos11Layout);
        JP_Comandos11Layout.setHorizontalGroup(
            JP_Comandos11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_TV11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_TV11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_DS11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_DS11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(JL_TipoMFCC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JRB_MFCC13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JRB_MFCC26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JRB_MFCC39)
                .addGap(28, 28, 28)
                .addComponent(JL_M11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCB_M11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_BC11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCB_BC11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(JB_Obtener11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JB_Guardar11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_Comandos11Layout.setVerticalGroup(
            JP_Comandos11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_Comandos11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_Comandos11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_TV11)
                    .addComponent(JTF_TV11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_DS11)
                    .addComponent(JTF_DS11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_M11)
                    .addComponent(JL_BC11)
                    .addComponent(JB_Obtener11)
                    .addComponent(JCB_M11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCB_BC11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Guardar11)
                    .addComponent(JL_TipoMFCC)
                    .addComponent(JRB_MFCC13)
                    .addComponent(JRB_MFCC26)
                    .addComponent(JRB_MFCC39))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JP_MFCCLayout = new javax.swing.GroupLayout(JP_MFCC);
        JP_MFCC.setLayout(JP_MFCCLayout);
        JP_MFCCLayout.setHorizontalGroup(
            JP_MFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MFCCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_MFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_MFCCLayout.createSequentialGroup()
                        .addComponent(JP_Comandos11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JSP_MFCC))
                .addContainerGap())
        );
        JP_MFCCLayout.setVerticalGroup(
            JP_MFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MFCCLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(JSP_MFCC, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JP_Comandos11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Procesamiento.addTab("MFCC", JP_MFCC);

        javax.swing.GroupLayout JP_FondoLayout = new javax.swing.GroupLayout(JP_Fondo);
        JP_Fondo.setLayout(JP_FondoLayout);
        JP_FondoLayout.setHorizontalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JTP_Procesamiento)
                    .addComponent(JP_Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JTP_Procesamiento)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JP_Fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_AbrirActionPerformed
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File archivoEntrada = fileChooser.getSelectedFile();
            if (archivoEntrada.getAbsolutePath().endsWith(".wav") || 
                archivoEntrada.getAbsolutePath().endsWith(".WAV")) {
                Audio audio = new Audio();
                infoAudios[0] = audio.abrirAudio(archivoEntrada);
                if (!infoAudios[0].equals("")) {
                    audio1 = audio;
                    JTA_InfoAudio.setText("Audio con Ruido:   "+infoAudios[0]);
                    inicializarJTextField();
                    reiniciarCanvas();
                    inicializarPasos();
                    this.audio2 = null;
                    this.audio3 = null;
                    this.audio4 = null;
                    this.datosVozPreEnfasis = null;
                    this.datosVozPreEnfasis2 = null;
                    this.ventanamiento = null;
                    this.moMFCC = null;
                    this.respuestaGUI = null;
                    this.alfa = 0;
                    recargarTablaMFCC(moMFCC);
                }
            } else {
                String msj = "El archivo no es un audio .wav!";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_AbrirActionPerformed

    private void JB_Graficar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Graficar1ActionPerformed
        if (audio1 != null && audio1.getDatos() != null) {
            double max = Utilitarios.getMaximo(audio1.getDatos().getMayor(), 
                                               audio1.getDatos().getMenor());
            double[] datosVoz = audio1.getDatos().getAmplitudes();
            JTF_xMen1.setText(""+0);
            JTF_xMay1.setText(""+datosVoz.length);
            JTF_yMen1.setText(""+audio1.getDatos().getMenor());
            JTF_yMay1.setText(""+audio1.getDatos().getMayor());
            canvas1.setTipoGrafica(0);
            canvas1.graficarDatos(datosVoz, max);
            canvas1.repaint();
            paso1 = true;
        } else {
            String msj = "Primero abra un audio .wav!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Graficar1ActionPerformed

    private void JB_Reproducir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Reproducir1ActionPerformed
        if (paso1) {
            audio1.reproducirAudio();
        }  else {
            String msj = "Primero grafique la señal!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Reproducir1ActionPerformed

    private void JB_Graficar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Graficar2ActionPerformed
        if (paso1) {
            //0 ≤ y ≤ 255 (8 bits)
            //-32768 ≤ y ≤ +32767 (16 bits)
            double[] datosVoz = audio1.getDatos().getAmplitudes();
            double[] datosVozNormalizado = Preprocesamiento.normalizar(datosVoz, 32768);
            JTF_xMen2.setText(""+0);
            JTF_xMay2.setText(""+datosVozNormalizado.length);
            int menor = (int)((audio1.getDatos().getMenor()/32768)*10000);
            JTF_yMen2.setText(""+(float)menor/10000);
            int mayor = (int)((audio1.getDatos().getMayor()/32768)*10000);
            JTF_yMay2.setText(""+(float)mayor/10000);
            canvas2.setTipoGrafica(0);
            canvas2.graficarDatos(datosVozNormalizado, 1);
            canvas2.repaint();
            paso2 = true;
        } else {
            String msj = "Primero realize el Paso1!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Graficar2ActionPerformed

    private void JB_Reproducir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Reproducir3ActionPerformed
        if (paso3) {
            if (audio2.getArchivo() != null) {
                audio2.reproducirAudio();
            }
        } else {
            String msj = "Primero cargue un audio .wav!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Reproducir3ActionPerformed

    private void JB_Cargar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Cargar3ActionPerformed
        if (paso2) {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Tiene el audio del ruido?");
            if (respuesta == 0) {
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File archivoEntrada = fileChooser.getSelectedFile();
                    if (archivoEntrada.getAbsolutePath().endsWith(".wav") ||
                        archivoEntrada.getAbsolutePath().endsWith(".WAV")) {
                        Audio audio = new Audio();
                        infoAudios[1] = audio.abrirAudio(archivoEntrada);
                        if (!infoAudios[1].equals("")) {
                            if (audio1.igualFormato(audio)) {
                                audio2 = audio;
                                JTA_InfoAudio.setText("Audio con Ruido:   "+infoAudios[0]+"\n"+
                                                      "Audio del Ruido:   "+infoAudios[1]);
                                double max = Utilitarios.getMaximo(audio2.getDatos().getMayor(), 
                                                                   audio2.getDatos().getMenor());
                                double[] datosVoz = audio2.getDatos().getAmplitudes();
                                JTF_xMen3.setText(""+0);
                                JTF_xMay3.setText(""+datosVoz.length);
                                JTF_yMen3.setText(""+audio2.getDatos().getMenor());
                                JTF_yMay3.setText(""+audio2.getDatos().getMayor());
                                canvas3.setTipoGrafica(0);
                                canvas3.graficarDatos(datosVoz, max);
                                canvas3.repaint();
                                paso3 = true;
                            } else {
                                String msj = "El audio no tiene el mismo formato!";
                                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        String msj = "El archivo no es un audio .wav!";
                        JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (respuesta == 1) {
                infoAudios[1] = "No Hay";
                JTA_InfoAudio.setText("Audio con Ruido:   "+infoAudios[0]+"\n"+
                                      "Audio del Ruido:   "+infoAudios[1]);
                double[] datosVoz = new double[audio1.getDatos().getAmplitudes().length];
                datosVoz[0] = 1;
                for (int i = 1; i < datosVoz.length; i++) {
                    datosVoz[i] = 0;
                }
                audio2 = new Audio();
                audio2.setDatos(new Datos(datosVoz, audio1.getDatos().getTipo()));
                JTF_xMen3.setText(""+0);
                JTF_xMay3.setText(""+datosVoz.length);
                JTF_yMen3.setText(""+0);
                JTF_yMay3.setText(""+0);
                canvas3.setTipoGrafica(0);
                canvas3.graficarDatos(datosVoz, 1);
                canvas3.repaint();
                paso3 = true;
            }
        } else {
            String msj = "Primero realize el Paso2!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Cargar3ActionPerformed

    private void JB_Reproducir4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Reproducir4ActionPerformed
        if (paso4) {
            audio3.reproducirAudio();
        } else {
            String msj = "Primero grafique la señal!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Reproducir4ActionPerformed

    private void JB_Graficar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Graficar4ActionPerformed
        if (paso3) {
            GUIEliminarRuido dialogo = new GUIEliminarRuido(this, true);
            dialogo.setVisible(true);
            if (respuestaGUI != null) {
                double[] senalEntrada = Preprocesamiento.normalizar(audio1.getDatos().getAmplitudes(), 32768);
                double[] senalRuido = Preprocesamiento.normalizar(audio2.getDatos().getAmplitudes(), 32768);
                LMS lms = new LMS(senalEntrada, senalRuido);
                int M = Integer.parseInt(respuestaGUI[1]);
                double adaptacion = Double.parseDouble(respuestaGUI[2]);
                if (respuestaGUI[0].equals("LMS")) {
                    lms.LMS(adaptacion, M, null);
                } else {
                    lms.NLMS(adaptacion, M, null);
                }
                double[] E_v = lms.getSenalDeseada();
                double men = Utilitarios.getMenor(E_v);
                double may = Utilitarios.getMayor(E_v);
                double max = Utilitarios.getMaximo(may, men);
                E_v = Preprocesamiento.normalizarInt(E_v, -max, max, -32768, 32767);
                men = Utilitarios.getMenor(E_v);
                may = Utilitarios.getMayor(E_v);
                max = Utilitarios.getMaximo(may, men);
                JTF_xMen4.setText(""+0);
                JTF_xMay4.setText(""+E_v.length);
                JTF_yMen4.setText(""+men);
                JTF_yMay4.setText(""+may);
                canvas4.setTipoGrafica(0);
                canvas4.graficarDatos(E_v, max);
                canvas4.repaint();
                //Construccion del Audio con Eliminacion de Ruido
                if (audio3 != null && audio3 != audio1) {
                    audio3.getArchivo().delete();
                    audio3 = null;
                }
                audio3 = new Audio();
                String[] nombre = audio1.getArchivo().getName().split(Pattern.quote("."));
                String nombreArchivo3 = audio1.getArchivo().getParent()+
                                        "\\"+nombre[0]+"_elimRuido.wav";
                audio3.setFormato(audio1.getFormato().getSampleRate(), 
                                  audio1.getFormato().getSampleSizeInBits(), 
                                  audio1.getFormato().getChannels(), true, 
                                  audio1.getFormato().isBigEndian());
                Datos datos3 = new Datos(E_v, audio3.getFormato().isBigEndian());
                datos3.convertirDoubleAByte();
                audio3.setDatos(datos3);
                audio3.guardarAudio(nombreArchivo3);
                paso4 = true;
                String mensaje = "Nuevo Audio Guardado!";
                JOptionPane.showMessageDialog(this, mensaje);
            } else {
                audio3 = audio1;
                double max = Utilitarios.getMaximo(audio3.getDatos().getMayor(), 
                                                   audio3.getDatos().getMenor());
                double[] datosVoz = audio3.getDatos().getAmplitudes();
                JTF_xMen4.setText(""+0);
                JTF_xMay4.setText(""+datosVoz.length);
                JTF_yMen4.setText(""+audio3.getDatos().getMenor());
                JTF_yMay4.setText(""+audio3.getDatos().getMayor());
                canvas4.setTipoGrafica(0);
                canvas4.graficarDatos(datosVoz, max);
                canvas4.repaint();
                paso4 = true;
            }
        } else {
            String msj = "Primero realize el Paso3!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Graficar4ActionPerformed

    private void JB_Graficar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Graficar6ActionPerformed
        if (paso5) {
            try {
                int TV = Integer.parseInt(JOptionPane.showInputDialog("Ingrese Tamaño de Ventana: "));  
                double[] M = Preprocesamiento.getMagnitudes(TV, datosVozPreEnfasis);
                JTF_xMen6.setText(""+0);
                JTF_xMay6.setText(""+M.length);
                double men = Utilitarios.getMenor(M);
                int menor = (int)(men*10000);
                JTF_yMen6.setText(""+(float)menor/10000);
                double may = Utilitarios.getMayor(M);
                int mayor = (int)(may*10000);
                JTF_yMay6.setText(""+(float)mayor/10000);
                canvas6.setTipoGrafica(0);
                canvas6.graficarDatos(M, Utilitarios.getMaximo(may, men));
                canvas6.repaint();
                paso6 = true;
            } catch (NumberFormatException ex) {
                String msj = "El tamaño de ventana debe ser un numero entero!";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            String msj = "Primero realize el Paso5!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Graficar6ActionPerformed

    private void JB_Graficar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Graficar7ActionPerformed
        if (paso6) {
            try {
                int TV = Integer.parseInt(JOptionPane.showInputDialog("Ingrese Tamaño de Ventana: "));
                double[] Z = Preprocesamiento.getCrucePorCeros(TV, datosVozPreEnfasis);
                JTF_xMen7.setText(""+0);
                JTF_xMay7.setText(""+Z.length);
                double men = Utilitarios.getMenor(Z);
                int menor = (int)(men*10000);
                JTF_yMen7.setText(""+(float)menor/10000);
                double may =Utilitarios.getMayor(Z);
                int mayor = (int)(may*10000);
                JTF_yMay7.setText(""+(float)mayor/10000);
                canvas7.setTipoGrafica(0);
                canvas7.graficarDatos(Z, Utilitarios.getMaximo(may, men));
                canvas7.repaint();
                paso7 = true;
            } catch(NumberFormatException ex) {
                String msj = "El tamaño de ventana debe ser un numero entero!";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            String msj = "Primero realize el Paso6!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Graficar7ActionPerformed

    private void JB_Graficar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Graficar8ActionPerformed
        if (paso7) {
            GUIEliminarSegmentos dialogo = new GUIEliminarSegmentos(this, true);
            dialogo.setVisible(true);
            if (respuestaGUI != null) {
                double max = Utilitarios.getMaximo(audio3.getDatos().getMayor(), 
                                                   audio3.getDatos().getMenor());
                double[] datosVoz = audio3.getDatos().getAmplitudes();
                JTF_xMen8.setText(""+0);
                JTF_xMay8.setText(""+datosVoz.length);
                JTF_yMen8.setText(""+audio3.getDatos().getMenor());
                JTF_yMay8.setText(""+audio3.getDatos().getMayor());
                int TV = Integer.parseInt(respuestaGUI[1]);
                int[] puntos;
                switch (respuestaGUI[0]) {
                    case "Energia": //TV = 192
                    double umb = Double.parseDouble(respuestaGUI[2]); //0.02
                    puntos = Preprocesamiento.eliminarSegmentosInutiles_Energia(TV, umb, datosVozPreEnfasis);
                    break;
                    case "Rabiner1": //TV = 128
                    puntos = Preprocesamiento.eliminarSegmentosInutiles_RabinerSambur(TV, datosVozPreEnfasis, 1);
                    break;
                    default: //TV = 128
                    puntos = Preprocesamiento.eliminarSegmentosInutiles_RabinerSambur(TV, datosVozPreEnfasis, 2);
                    break;
                }
                JTF_pi.setText(""+puntos[0]);
                JTF_pf.setText(""+puntos[1]);
                canvas8.setTipoGrafica(0);
                canvas8.graficarDatos(datosVoz, max);
                canvas8.setSI(true);
                canvas8.setPosicionSI(puntos[0], puntos[1]);
                canvas8.repaint();
                //Construccion del Audio con Eliminacion de Segmentos Inutiles
                if (audio4 != null && audio4 != audio3) {
                    audio4.getArchivo().delete();
                    audio4 = null;
                }
                audio4 = new Audio();
                String[] nombre = audio3.getArchivo().getName().split(Pattern.quote("."));
                String nombreArchivo4 = audio3.getArchivo().getParent()+
                                        "\\"+nombre[0]+"_elimSeg.wav";
                audio4.setFormato(audio3.getFormato().getSampleRate(),
                                  audio3.getFormato().getSampleSizeInBits(),
                                  audio3.getFormato().getChannels(), true,
                                  audio3.getFormato().isBigEndian());
                Datos datos4 = new Datos(Utilitarios.recortar(datosVoz, puntos[0], puntos[1]),
                                         audio4.getFormato().isBigEndian());
                datos4.convertirDoubleAByte();
                audio4.setDatos(datos4);
                audio4.guardarAudio(nombreArchivo4);
                paso8 = true;
                String msj = "Nuevo Audio Guardado!";
                JOptionPane.showMessageDialog(this, msj);
            } else {
                double max = Utilitarios.getMaximo(audio3.getDatos().getMayor(), 
                                                   audio3.getDatos().getMenor());
                double[] datosVoz = audio3.getDatos().getAmplitudes();
                JTF_xMen8.setText(""+0);
                JTF_xMay8.setText(""+datosVoz.length);
                JTF_yMen8.setText(""+audio3.getDatos().getMenor());
                JTF_yMay8.setText(""+audio3.getDatos().getMayor());
                JTF_pi.setText(""+0);
                JTF_pf.setText(""+(datosVoz.length-1));
                canvas8.setTipoGrafica(0);
                canvas8.graficarDatos(datosVoz, max);
                canvas8.setSI(true);
                canvas8.setPosicionSI(0, datosVoz.length-1);
                canvas8.repaint();
                audio4 = audio3;
                paso8 = true;
            }
        } else {
            String msj = "Primero realize el Paso7!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Graficar8ActionPerformed

    private void JB_Graficar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Graficar5ActionPerformed
        //[-32768, 32767] -> [-1, 1]
        if (paso4) {
            try {
                alfa = Double.parseDouble(JOptionPane.showInputDialog("Ingrese valor Alfa: "));
                if (alfa >= 0.8 && alfa < 1) {
                    double[] datosVoz = audio3.getDatos().getAmplitudes();
                    datosVozPreEnfasis = Preprocesamiento.preEnfasis(datosVoz, alfa);
                    double men = Utilitarios.getMenor(datosVozPreEnfasis);
                    double may = Utilitarios.getMayor(datosVozPreEnfasis);
                    double max = Utilitarios.getMaximo(may, men);
                    if (max < 32768) {
                        max = 32768;
                    }
                    datosVozPreEnfasis = Preprocesamiento.normalizarDouble(datosVozPreEnfasis, -max, max, -1, 1);
                    JTF_xMen5.setText(""+0);
                    JTF_xMay5.setText(""+datosVozPreEnfasis.length);
                    men = Utilitarios.getMenor(datosVozPreEnfasis);
                    int menor = (int)(men*10000);
                    JTF_yMen5.setText(""+(float)menor/10000);
                    may = Utilitarios.getMayor(datosVozPreEnfasis);
                    int mayor = (int)(may*10000);
                    JTF_yMay5.setText(""+(float)mayor/10000);
                    canvas5.setTipoGrafica(0);
                    canvas5.graficarDatos(datosVozPreEnfasis, 1);
                    canvas5.repaint();
                    paso5 = true;
                } else {
                    String msj = "El valor del alfa debe ser\nun numero entre 0.8 y 1!";
                    JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException ex) {
                String msj = "El valor del alfa debe ser\nun numero entre 0.8 y 1!";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            String msj = "Primero realize el Paso4!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Graficar5ActionPerformed

    private void JB_Graficar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Graficar9ActionPerformed
        if (paso8) {
            double[] datosVoz = audio4.getDatos().getAmplitudes();
            datosVozPreEnfasis2 = Preprocesamiento.preEnfasis(datosVoz, alfa);
            double men = Utilitarios.getMenor(datosVozPreEnfasis2);
            double may = Utilitarios.getMayor(datosVozPreEnfasis2);
            double max = Utilitarios.getMaximo(may, men);
            if (max < 32768) {
                max = 32768;
            }
            datosVozPreEnfasis2 = Preprocesamiento.normalizarDouble(datosVozPreEnfasis2, -max, max, -1, 1);
            double[] F = Utilitarios.getModulo(Fourier.fft2(datosVozPreEnfasis2));
            JTF_xMen9.setText(""+0);
            JTF_xMay9.setText(""+F.length);
            men = Utilitarios.getMenor(F);
            int menor = (int)(men*10000);
            JTF_yMen9.setText(""+(float)menor/10000);
            may = Utilitarios.getMayor(F);
            int mayor = (int)(may*10000);
            JTF_yMay9.setText(""+(float)mayor/10000);
            canvas9.setTipoGrafica(1);
            canvas9.graficarDatos(F, may);
            canvas9.repaint();
            paso9 = true;
        } else {
            String msj = "Primero realize el Paso8!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Graficar9ActionPerformed

    private void JB_Graficar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Graficar10ActionPerformed
        if (paso9) {
            GUIVentanamiento dialogo = new GUIVentanamiento(this, true);
            dialogo.setVisible(true);
            if (respuestaGUI != null) {
                int tipo = 0;
                if (respuestaGUI[0].equals("Hamming")) {
                    tipo = 1;
                } else if (respuestaGUI[0].equals("Hanning")) {
                    tipo = 2;
                }
                int TV = Integer.parseInt(respuestaGUI[1]); //448
                int DS = Integer.parseInt(respuestaGUI[2]); //288
                JTF_TV11.setText(""+TV);
                JTF_DS11.setText(""+DS);
                ventanamiento = new Ventanamiento(datosVozPreEnfasis2, TV, DS, tipo);
                double[][] espectro = Fourier.espectro(ventanamiento.getMatriz());
                double frecuencia = audio1.getFormato().getSampleRate();
                JTF_xMen10.setText(""+0);
                JTF_xMay10.setText(""+espectro.length);
                JTF_yMen10.setText(""+0);
                JTF_yMay10.setText(""+frecuencia/2);
                double men = Utilitarios.getMenorMatriz(espectro);
                int menor = (int)(men*10000);
                JTF_vMen10.setText(""+(float)menor/10000);
                double may = Utilitarios.getMayorMatriz(espectro);
                int mayor = (int)(may*10000);
                JTF_vMay10.setText(""+(float)mayor/10000);
                espectro = Preprocesamiento.normalizar(espectro, men, may, 0, 255);
                canvas10.setTipoGrafica(2);
                canvas10.graficarDatos(espectro);
                canvas10.repaint();
                paso10 = true;
            }
        } else {
            String msj = "Primero realize el Paso9!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Graficar10ActionPerformed

    private void JB_Obtener11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Obtener11ActionPerformed
        if (paso10) {
            int M = Integer.parseInt(JCB_M11.getSelectedItem().toString());
            int BC = Integer.parseInt(JCB_BC11.getSelectedItem().toString());
            double fs = audio1.getFormato().getSampleRate();
            MFCC mfcc = new MFCC(ventanamiento.getMatriz(), ventanamiento.getTV(), M, BC, fs);
            moMFCC = null;
            if (JRB_MFCC13.isSelected()) {
                moMFCC = mfcc.getResultMFCC(0, fs/2);
            } else if (JRB_MFCC26.isSelected()) {
                moMFCC = mfcc.getResultMFCCDelta(0, fs/2);
            } else if (JRB_MFCC39.isSelected()) {
                moMFCC = mfcc.getResultMFCCDeltaDelta(0, fs/2);
            }
            if (moMFCC != null) {
                recargarTablaMFCC(moMFCC);
                paso11 = true;
            }
        } else {
            String msj = "Primero realize el Paso10!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Obtener11ActionPerformed

    private void JB_Guardar11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_Guardar11ActionPerformed
        if (paso11) {
            String usuario = JOptionPane.showInputDialog("Ingrese Nombre Usuario: ");
            String palabra = JOptionPane.showInputDialog("Ingrese Palabra: ");
            String[] nombreArchivo = audio1.getArchivo().getAbsolutePath().split(".wav");
            ArchivoMFCC archivoMFCC = new ArchivoMFCC(nombreArchivo[0]+".mfcc", usuario, palabra, 0, moMFCC);
            archivoMFCC.guardarMO();
            String mensaje = "Archivo Patron .mfcc Guardado!";
            JOptionPane.showMessageDialog(this, mensaje);
        } else {
            String msj = "Primero obtenga la matriz de observaciones!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_Guardar11ActionPerformed

    private void JB_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_NuevoActionPerformed
        new GUINuevoAudio_Escoger(this, true).setVisible(true);
    }//GEN-LAST:event_JB_NuevoActionPerformed

    private void JB_PatronesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_PatronesActionPerformed
        this.setVisible(false);
        new GUIMFCC(this).setVisible(true);
    }//GEN-LAST:event_JB_PatronesActionPerformed

    private void JB_CompararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CompararActionPerformed
        new GUIDTW_Escoger(this, true).setVisible(true);
    }//GEN-LAST:event_JB_CompararActionPerformed

    private void JB_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_JB_SalirActionPerformed

    private void recargarTablaMFCC(double[][] mfcc) {
        if (mfcc != null) {
            int nf = mfcc.length; //M (Coeficientes)
            int nc = mfcc[0].length; // Segmentos de Vetanamiento
            String[][] data = new String[nf][nc];
            String[] col = new String[nc];
            for (int i = 0; i < nf; i++) {
                for (int j = 0; j < nc; j++) {
                    if (i == 0) {
                        col[j] = String.valueOf(j+1);
                    }
                    data[i][j] = String.valueOf(mfcc[i][j]);
                }
            }
            JT_MFCC.setModel(new javax.swing.table.DefaultTableModel(data, col) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
        } else {
            JT_MFCC.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {}, new String [] {}
            ));
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIAnalisis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUIAnalisis().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup JBG_TipoMFCC;
    private javax.swing.JButton JB_Abrir;
    private javax.swing.JButton JB_Cargar3;
    private javax.swing.JButton JB_Comparar;
    private javax.swing.JButton JB_Graficar1;
    private javax.swing.JButton JB_Graficar10;
    private javax.swing.JButton JB_Graficar2;
    private javax.swing.JButton JB_Graficar4;
    private javax.swing.JButton JB_Graficar5;
    private javax.swing.JButton JB_Graficar6;
    private javax.swing.JButton JB_Graficar7;
    private javax.swing.JButton JB_Graficar8;
    private javax.swing.JButton JB_Graficar9;
    private javax.swing.JButton JB_Guardar11;
    private javax.swing.JButton JB_Nuevo;
    private javax.swing.JButton JB_Obtener11;
    private javax.swing.JButton JB_Patrones;
    private javax.swing.JButton JB_Reproducir1;
    private javax.swing.JButton JB_Reproducir3;
    private javax.swing.JButton JB_Reproducir4;
    private javax.swing.JButton JB_Salir;
    private javax.swing.JComboBox<String> JCB_BC11;
    private javax.swing.JComboBox<String> JCB_M11;
    private javax.swing.JLabel JL_BC11;
    private javax.swing.JLabel JL_DS11;
    private javax.swing.JLabel JL_M11;
    private javax.swing.JLabel JL_TV11;
    private javax.swing.JLabel JL_TipoMFCC;
    private javax.swing.JLabel JL_pf;
    private javax.swing.JLabel JL_pi;
    private javax.swing.JLabel JL_vMay10;
    private javax.swing.JLabel JL_vMen10;
    private javax.swing.JLabel JL_xMay1;
    private javax.swing.JLabel JL_xMay10;
    private javax.swing.JLabel JL_xMay2;
    private javax.swing.JLabel JL_xMay3;
    private javax.swing.JLabel JL_xMay4;
    private javax.swing.JLabel JL_xMay5;
    private javax.swing.JLabel JL_xMay6;
    private javax.swing.JLabel JL_xMay7;
    private javax.swing.JLabel JL_xMay8;
    private javax.swing.JLabel JL_xMay9;
    private javax.swing.JLabel JL_xMen1;
    private javax.swing.JLabel JL_xMen10;
    private javax.swing.JLabel JL_xMen2;
    private javax.swing.JLabel JL_xMen3;
    private javax.swing.JLabel JL_xMen4;
    private javax.swing.JLabel JL_xMen5;
    private javax.swing.JLabel JL_xMen6;
    private javax.swing.JLabel JL_xMen7;
    private javax.swing.JLabel JL_xMen8;
    private javax.swing.JLabel JL_xMen9;
    private javax.swing.JLabel JL_yMay1;
    private javax.swing.JLabel JL_yMay10;
    private javax.swing.JLabel JL_yMay2;
    private javax.swing.JLabel JL_yMay3;
    private javax.swing.JLabel JL_yMay4;
    private javax.swing.JLabel JL_yMay5;
    private javax.swing.JLabel JL_yMay6;
    private javax.swing.JLabel JL_yMay7;
    private javax.swing.JLabel JL_yMay8;
    private javax.swing.JLabel JL_yMay9;
    private javax.swing.JLabel JL_yMen1;
    private javax.swing.JLabel JL_yMen10;
    private javax.swing.JLabel JL_yMen2;
    private javax.swing.JLabel JL_yMen3;
    private javax.swing.JLabel JL_yMen4;
    private javax.swing.JLabel JL_yMen5;
    private javax.swing.JLabel JL_yMen6;
    private javax.swing.JLabel JL_yMen7;
    private javax.swing.JLabel JL_yMen8;
    private javax.swing.JLabel JL_yMen9;
    private javax.swing.JPanel JP_Comando5;
    private javax.swing.JPanel JP_Comando7;
    private javax.swing.JPanel JP_Comandos1;
    private javax.swing.JPanel JP_Comandos10;
    private javax.swing.JPanel JP_Comandos11;
    private javax.swing.JPanel JP_Comandos2;
    private javax.swing.JPanel JP_Comandos3;
    private javax.swing.JPanel JP_Comandos4;
    private javax.swing.JPanel JP_Comandos6;
    private javax.swing.JPanel JP_Comandos8;
    private javax.swing.JPanel JP_Comandos9;
    private javax.swing.JPanel JP_CrucePorCeros;
    private javax.swing.JPanel JP_EliminarRuido;
    private javax.swing.JPanel JP_EliminarSegmentos;
    private javax.swing.JPanel JP_Energia;
    private javax.swing.JPanel JP_Espectograma;
    private javax.swing.JPanel JP_FFT;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JPanel JP_Grafica1;
    private javax.swing.JPanel JP_Grafica10;
    private javax.swing.JPanel JP_Grafica10_1;
    private javax.swing.JPanel JP_Grafica1_1;
    private javax.swing.JPanel JP_Grafica2;
    private javax.swing.JPanel JP_Grafica2_1;
    private javax.swing.JPanel JP_Grafica3;
    private javax.swing.JPanel JP_Grafica3_1;
    private javax.swing.JPanel JP_Grafica4;
    private javax.swing.JPanel JP_Grafica4_1;
    private javax.swing.JPanel JP_Grafica5;
    private javax.swing.JPanel JP_Grafica5_1;
    private javax.swing.JPanel JP_Grafica6;
    private javax.swing.JPanel JP_Grafica6_1;
    private javax.swing.JPanel JP_Grafica7;
    private javax.swing.JPanel JP_Grafica7_1;
    private javax.swing.JPanel JP_Grafica8;
    private javax.swing.JPanel JP_Grafica8_1;
    private javax.swing.JPanel JP_Grafica9;
    private javax.swing.JPanel JP_Grafica9_1;
    private javax.swing.JPanel JP_MFCC;
    private javax.swing.JPanel JP_Menu;
    private javax.swing.JPanel JP_PreEnfasis;
    private javax.swing.JPanel JP_SenalRuido;
    private javax.swing.JPanel JP_Voz;
    private javax.swing.JPanel JP_VozNormalizada;
    private javax.swing.JRadioButton JRB_MFCC13;
    private javax.swing.JRadioButton JRB_MFCC26;
    private javax.swing.JRadioButton JRB_MFCC39;
    private javax.swing.JScrollPane JSP_InfoAudio;
    private javax.swing.JScrollPane JSP_MFCC;
    private javax.swing.JTextArea JTA_InfoAudio;
    private javax.swing.JTextField JTF_DS11;
    private javax.swing.JTextField JTF_TV11;
    private javax.swing.JTextField JTF_pf;
    private javax.swing.JTextField JTF_pi;
    private javax.swing.JTextField JTF_vMay10;
    private javax.swing.JTextField JTF_vMen10;
    private javax.swing.JTextField JTF_xMay1;
    private javax.swing.JTextField JTF_xMay10;
    private javax.swing.JTextField JTF_xMay2;
    private javax.swing.JTextField JTF_xMay3;
    private javax.swing.JTextField JTF_xMay4;
    private javax.swing.JTextField JTF_xMay5;
    private javax.swing.JTextField JTF_xMay6;
    private javax.swing.JTextField JTF_xMay7;
    private javax.swing.JTextField JTF_xMay8;
    private javax.swing.JTextField JTF_xMay9;
    private javax.swing.JTextField JTF_xMen1;
    private javax.swing.JTextField JTF_xMen10;
    private javax.swing.JTextField JTF_xMen2;
    private javax.swing.JTextField JTF_xMen3;
    private javax.swing.JTextField JTF_xMen4;
    private javax.swing.JTextField JTF_xMen5;
    private javax.swing.JTextField JTF_xMen6;
    private javax.swing.JTextField JTF_xMen7;
    private javax.swing.JTextField JTF_xMen8;
    private javax.swing.JTextField JTF_xMen9;
    private javax.swing.JTextField JTF_yMay1;
    private javax.swing.JTextField JTF_yMay10;
    private javax.swing.JTextField JTF_yMay2;
    private javax.swing.JTextField JTF_yMay3;
    private javax.swing.JTextField JTF_yMay4;
    private javax.swing.JTextField JTF_yMay5;
    private javax.swing.JTextField JTF_yMay6;
    private javax.swing.JTextField JTF_yMay7;
    private javax.swing.JTextField JTF_yMay8;
    private javax.swing.JTextField JTF_yMay9;
    private javax.swing.JTextField JTF_yMen1;
    private javax.swing.JTextField JTF_yMen10;
    private javax.swing.JTextField JTF_yMen2;
    private javax.swing.JTextField JTF_yMen3;
    private javax.swing.JTextField JTF_yMen4;
    private javax.swing.JTextField JTF_yMen5;
    private javax.swing.JTextField JTF_yMen6;
    private javax.swing.JTextField JTF_yMen7;
    private javax.swing.JTextField JTF_yMen8;
    private javax.swing.JTextField JTF_yMen9;
    private javax.swing.JTabbedPane JTP_Procesamiento;
    private javax.swing.JTable JT_MFCC;
    // End of variables declaration//GEN-END:variables
}
