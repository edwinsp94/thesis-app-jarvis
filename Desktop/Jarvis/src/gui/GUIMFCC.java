package gui;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import reconocedor.ArchivoMFCC;
import reconocedor.Audio;
import reconocedor.LMS;
import reconocedor.MFCC;
import reconocedor.Preprocesamiento;
import reconocedor.Utilitarios;
import reconocedor.Ventanamiento;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class GUIMFCC extends javax.swing.JFrame {

    private final GUIAnalisis guiAnalisis;
    private final JFileChooser fileChooserAbrir;
    private final JFileChooser fileChooserGuardar;
    private ArrayList<String> listaAudiosVoz;
    private ArrayList<String> listaAudiosRuido;
    
    public GUIMFCC(GUIAnalisis parent) {
        initComponents();
        this.guiAnalisis = parent;
        this.fileChooserAbrir = new JFileChooser();
        this.fileChooserAbrir.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.fileChooserAbrir.setFileFilter(
        new FileNameExtensionFilter("Todos los Archivos *.wav", "wav", "WAV"));
        this.fileChooserAbrir.setMultiSelectionEnabled(true);
        this.fileChooserGuardar = new JFileChooser();
        this.fileChooserGuardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.fileChooserGuardar.addChoosableFileFilter(
        new FileNameExtensionFilter("Todos los Archivos *.mfcc", "mfcc", "MFCC"));
        this.listaAudiosVoz = null;
        this.listaAudiosRuido = null;
        JTF_Coeficientes.setEnabled(false);
        JTF_ValorAdaptacion.setEnabled(false);
        JTF_Coeficientes.setText("0");
        JTF_ValorAdaptacion.setText("0");
        JTF_SegmentosVentana.setEnabled(false);
        JTF_SegmentosUmbral.setEnabled(false);
        JTF_SegmentosVentana.setText("0");
        JTF_SegmentosUmbral.setText("0");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBG_EliminarRuido = new javax.swing.ButtonGroup();
        JBG_EliminarSegmentos = new javax.swing.ButtonGroup();
        JBG_Ventaneamiento = new javax.swing.ButtonGroup();
        JBG_MFCC = new javax.swing.ButtonGroup();
        JP_Fondo = new javax.swing.JPanel();
        JP_ConfiguracionMFCC = new javax.swing.JPanel();
        JL_EliminarRuido = new javax.swing.JLabel();
        JRB_RuidoLMS = new javax.swing.JRadioButton();
        JRB_RuidoNLMS = new javax.swing.JRadioButton();
        JRB_RuidoNinguno = new javax.swing.JRadioButton();
        JL_Coeficientes = new javax.swing.JLabel();
        JTF_Coeficientes = new javax.swing.JTextField();
        JL_ValorAdaptacion = new javax.swing.JLabel();
        JTF_ValorAdaptacion = new javax.swing.JTextField();
        JL_EliminarSegmentos = new javax.swing.JLabel();
        JRB_SegmentosEnergia = new javax.swing.JRadioButton();
        JRB_SegmentosRabiner1 = new javax.swing.JRadioButton();
        JRB_SegmentosRabiner2 = new javax.swing.JRadioButton();
        JRB_SegmentosNinguno = new javax.swing.JRadioButton();
        JL_SegmentosVentana = new javax.swing.JLabel();
        JTF_SegmentosVentana = new javax.swing.JTextField();
        JL_SegmentosUmbral = new javax.swing.JLabel();
        JTF_SegmentosUmbral = new javax.swing.JTextField();
        JL_FiltroPreEnfasis = new javax.swing.JLabel();
        JL_FiltroAlfa = new javax.swing.JLabel();
        JTF_FiltroAlfa = new javax.swing.JTextField();
        JL_Ventaneamiento = new javax.swing.JLabel();
        JRB_VentanaRectangular = new javax.swing.JRadioButton();
        JRB_VentanaHamming = new javax.swing.JRadioButton();
        JRB_VentanaHanning = new javax.swing.JRadioButton();
        JL_VentanaTamano = new javax.swing.JLabel();
        JTF_VentanaTamano = new javax.swing.JTextField();
        JL_VentanaSolapamiento = new javax.swing.JLabel();
        JTF_VentanaSolapamiento = new javax.swing.JTextField();
        JL_MFCC = new javax.swing.JLabel();
        JRB_MFCC13 = new javax.swing.JRadioButton();
        JRB_MFCC26 = new javax.swing.JRadioButton();
        JRB_MFCC39 = new javax.swing.JRadioButton();
        JL_M = new javax.swing.JLabel();
        JCB_M = new javax.swing.JComboBox<>();
        JL_BC = new javax.swing.JLabel();
        JCB_BC = new javax.swing.JComboBox<>();
        JP_ArchivosAudio = new javax.swing.JPanel();
        JL_DirectorioEntrada = new javax.swing.JLabel();
        JTF_DirectorioEntrada = new javax.swing.JTextField();
        JB_Cargar = new javax.swing.JButton();
        JL_AudiosVoz = new javax.swing.JLabel();
        JSP_AudiosVoz = new javax.swing.JScrollPane();
        JL_ListaAudiosVoz = new javax.swing.JList<>();
        JL_AudiosRuido = new javax.swing.JLabel();
        JSP_AudiosRuido = new javax.swing.JScrollPane();
        JL_ListaAudiosRuido = new javax.swing.JList<>();
        JL_DirectorioSalida = new javax.swing.JLabel();
        JTF_DirectorioSalida = new javax.swing.JTextField();
        JB_Guardar = new javax.swing.JButton();
        JB_MenuPrincipal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Obtener Patrones MFCC");
        setBackground(new java.awt.Color(255, 255, 255));

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JP_ConfiguracionMFCC.setBackground(new java.awt.Color(255, 255, 255));
        JP_ConfiguracionMFCC.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "CONFIGURACION DE ALGORITMO MFCC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JL_EliminarRuido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JL_EliminarRuido.setText("ELIMINAR RUIDO:");

        JRB_RuidoLMS.setBackground(new java.awt.Color(255, 255, 255));
        JBG_EliminarRuido.add(JRB_RuidoLMS);
        JRB_RuidoLMS.setText("LMS");
        JRB_RuidoLMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_RuidoLMSActionPerformed(evt);
            }
        });

        JRB_RuidoNLMS.setBackground(new java.awt.Color(255, 255, 255));
        JBG_EliminarRuido.add(JRB_RuidoNLMS);
        JRB_RuidoNLMS.setText("NLMS");
        JRB_RuidoNLMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_RuidoNLMSActionPerformed(evt);
            }
        });

        JRB_RuidoNinguno.setBackground(new java.awt.Color(255, 255, 255));
        JBG_EliminarRuido.add(JRB_RuidoNinguno);
        JRB_RuidoNinguno.setSelected(true);
        JRB_RuidoNinguno.setText("Ninguno");
        JRB_RuidoNinguno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_RuidoNingunoActionPerformed(evt);
            }
        });

        JL_Coeficientes.setText("Nro. Coeficientes:");

        JTF_Coeficientes.setText("0");

        JL_ValorAdaptacion.setText("Paso de Adaptacion:");

        JTF_ValorAdaptacion.setText("0");

        JL_EliminarSegmentos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JL_EliminarSegmentos.setText("ELIMINAR SEGMENTOS INUTILES:");

        JRB_SegmentosEnergia.setBackground(new java.awt.Color(255, 255, 255));
        JBG_EliminarSegmentos.add(JRB_SegmentosEnergia);
        JRB_SegmentosEnergia.setText("Energia");
        JRB_SegmentosEnergia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_SegmentosEnergiaActionPerformed(evt);
            }
        });

        JRB_SegmentosRabiner1.setBackground(new java.awt.Color(255, 255, 255));
        JBG_EliminarSegmentos.add(JRB_SegmentosRabiner1);
        JRB_SegmentosRabiner1.setText("Rabiner & Sambur 1");
        JRB_SegmentosRabiner1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_SegmentosRabiner1ActionPerformed(evt);
            }
        });

        JRB_SegmentosRabiner2.setBackground(new java.awt.Color(255, 255, 255));
        JBG_EliminarSegmentos.add(JRB_SegmentosRabiner2);
        JRB_SegmentosRabiner2.setText("Rabiner & Sambur 2");
        JRB_SegmentosRabiner2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_SegmentosRabiner2ActionPerformed(evt);
            }
        });

        JRB_SegmentosNinguno.setBackground(new java.awt.Color(255, 255, 255));
        JBG_EliminarSegmentos.add(JRB_SegmentosNinguno);
        JRB_SegmentosNinguno.setSelected(true);
        JRB_SegmentosNinguno.setText("Ninguno");
        JRB_SegmentosNinguno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_SegmentosNingunoActionPerformed(evt);
            }
        });

        JL_SegmentosVentana.setText("Tamaño de Ventana: ");

        JTF_SegmentosVentana.setText("0");

        JL_SegmentosUmbral.setText("Umbral:");

        JTF_SegmentosUmbral.setText("0");

        JL_FiltroPreEnfasis.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JL_FiltroPreEnfasis.setText("FILTRO PRE-ENFASIS:");

        JL_FiltroAlfa.setText("Alfa:");

        JL_Ventaneamiento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JL_Ventaneamiento.setText("VENTANEAMIENTO:");

        JRB_VentanaRectangular.setBackground(new java.awt.Color(255, 255, 255));
        JBG_Ventaneamiento.add(JRB_VentanaRectangular);
        JRB_VentanaRectangular.setSelected(true);
        JRB_VentanaRectangular.setText("Rectangular");

        JRB_VentanaHamming.setBackground(new java.awt.Color(255, 255, 255));
        JBG_Ventaneamiento.add(JRB_VentanaHamming);
        JRB_VentanaHamming.setText("Hamming");

        JRB_VentanaHanning.setBackground(new java.awt.Color(255, 255, 255));
        JBG_Ventaneamiento.add(JRB_VentanaHanning);
        JRB_VentanaHanning.setText("Hanning");

        JL_VentanaTamano.setText("Tamaño de Ventana:");

        JL_VentanaSolapamiento.setText("Distancia de Solapamiento:");

        JL_MFCC.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JL_MFCC.setText("MFCC:");

        JRB_MFCC13.setBackground(new java.awt.Color(255, 255, 255));
        JBG_MFCC.add(JRB_MFCC13);
        JRB_MFCC13.setSelected(true);
        JRB_MFCC13.setText("MFCC13");

        JRB_MFCC26.setBackground(new java.awt.Color(255, 255, 255));
        JBG_MFCC.add(JRB_MFCC26);
        JRB_MFCC26.setText("MFFC26");

        JRB_MFCC39.setBackground(new java.awt.Color(255, 255, 255));
        JBG_MFCC.add(JRB_MFCC39);
        JRB_MFCC39.setText("MFCC39");

        JL_M.setText("M:");

        JCB_M.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "13", "14", "15" }));

        JL_BC.setText("BC:");

        JCB_BC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));

        javax.swing.GroupLayout JP_ConfiguracionMFCCLayout = new javax.swing.GroupLayout(JP_ConfiguracionMFCC);
        JP_ConfiguracionMFCC.setLayout(JP_ConfiguracionMFCCLayout);
        JP_ConfiguracionMFCCLayout.setHorizontalGroup(
            JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                .addComponent(JRB_RuidoLMS)
                                .addGap(18, 18, 18)
                                .addComponent(JRB_RuidoNLMS)
                                .addGap(18, 18, 18)
                                .addComponent(JRB_RuidoNinguno))
                            .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                .addComponent(JL_Coeficientes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTF_Coeficientes, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(JL_ValorAdaptacion)
                                .addGap(5, 5, 5)
                                .addComponent(JTF_ValorAdaptacion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(JRB_VentanaHamming)
                                .addGap(18, 18, 18)
                                .addComponent(JRB_VentanaHanning))
                            .addComponent(JL_EliminarRuido)
                            .addComponent(JL_Ventaneamiento)
                            .addComponent(JL_MFCC)
                            .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JRB_VentanaRectangular)
                                    .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                            .addComponent(JRB_MFCC13)
                                            .addGap(18, 18, 18)
                                            .addComponent(JRB_MFCC26)
                                            .addGap(18, 18, 18)
                                            .addComponent(JRB_MFCC39)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(JL_M)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(JCB_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(JL_BC)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(JCB_BC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                            .addComponent(JL_VentanaTamano)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(JTF_VentanaTamano, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27)
                                            .addComponent(JL_VentanaSolapamiento)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(JTF_VentanaSolapamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(JL_EliminarSegmentos)
                            .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                        .addComponent(JRB_SegmentosEnergia)
                                        .addGap(18, 18, 18)
                                        .addComponent(JRB_SegmentosRabiner1)
                                        .addGap(18, 18, 18)
                                        .addComponent(JRB_SegmentosRabiner2)
                                        .addGap(18, 18, 18)
                                        .addComponent(JRB_SegmentosNinguno))
                                    .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                        .addComponent(JL_SegmentosVentana)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JTF_SegmentosVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(JL_SegmentosUmbral)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JTF_SegmentosUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(JL_FiltroPreEnfasis)
                            .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(JL_FiltroAlfa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTF_FiltroAlfa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        JP_ConfiguracionMFCCLayout.setVerticalGroup(
            JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ConfiguracionMFCCLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(JL_EliminarRuido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JRB_RuidoLMS)
                    .addComponent(JRB_RuidoNLMS)
                    .addComponent(JRB_RuidoNinguno))
                .addGap(4, 4, 4)
                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Coeficientes)
                    .addComponent(JTF_Coeficientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_ValorAdaptacion)
                    .addComponent(JTF_ValorAdaptacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(JL_FiltroPreEnfasis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_FiltroAlfa)
                    .addComponent(JTF_FiltroAlfa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(JL_EliminarSegmentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JRB_SegmentosEnergia)
                    .addComponent(JRB_SegmentosRabiner1)
                    .addComponent(JRB_SegmentosRabiner2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JRB_SegmentosNinguno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_SegmentosVentana)
                    .addComponent(JTF_SegmentosVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_SegmentosUmbral)
                    .addComponent(JTF_SegmentosUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(JL_Ventaneamiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JRB_VentanaHamming)
                    .addComponent(JRB_VentanaHanning)
                    .addComponent(JRB_VentanaRectangular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_VentanaTamano)
                    .addComponent(JL_VentanaSolapamiento)
                    .addComponent(JTF_VentanaSolapamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTF_VentanaTamano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(JL_MFCC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionMFCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_M)
                    .addComponent(JL_BC)
                    .addComponent(JCB_M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCB_BC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JRB_MFCC13)
                    .addComponent(JRB_MFCC26)
                    .addComponent(JRB_MFCC39))
                .addGap(24, 24, 24))
        );

        JP_ArchivosAudio.setBackground(new java.awt.Color(255, 255, 255));
        JP_ArchivosAudio.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "ARCHIVOS DE AUDIO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JL_DirectorioEntrada.setText("Directorio de Entrada:");

        JB_Cargar.setText("Cargar");
        JB_Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CargarActionPerformed(evt);
            }
        });

        JL_AudiosVoz.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JL_AudiosVoz.setText("AUDIOS VOZ");

        JSP_AudiosVoz.setViewportView(JL_ListaAudiosVoz);

        JL_AudiosRuido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JL_AudiosRuido.setText("AUDIOS RUIDO");

        JSP_AudiosRuido.setViewportView(JL_ListaAudiosRuido);

        javax.swing.GroupLayout JP_ArchivosAudioLayout = new javax.swing.GroupLayout(JP_ArchivosAudio);
        JP_ArchivosAudio.setLayout(JP_ArchivosAudioLayout);
        JP_ArchivosAudioLayout.setHorizontalGroup(
            JP_ArchivosAudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ArchivosAudioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_ArchivosAudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_ArchivosAudioLayout.createSequentialGroup()
                        .addComponent(JL_DirectorioEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_ArchivosAudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_ArchivosAudioLayout.createSequentialGroup()
                                .addComponent(JB_Cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(JTF_DirectorioEntrada)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_ArchivosAudioLayout.createSequentialGroup()
                        .addGroup(JP_ArchivosAudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JL_AudiosVoz, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JSP_AudiosVoz, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_ArchivosAudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JSP_AudiosRuido, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(JL_AudiosRuido, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))))
                .addContainerGap())
        );
        JP_ArchivosAudioLayout.setVerticalGroup(
            JP_ArchivosAudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ArchivosAudioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_ArchivosAudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_DirectorioEntrada)
                    .addComponent(JTF_DirectorioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(JB_Cargar)
                .addGap(18, 18, 18)
                .addGroup(JP_ArchivosAudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_AudiosVoz)
                    .addComponent(JL_AudiosRuido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_ArchivosAudioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JSP_AudiosVoz, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(JSP_AudiosRuido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JL_DirectorioSalida.setText("Directorio de Salida:");

        JB_Guardar.setText("Guardar");
        JB_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_GuardarActionPerformed(evt);
            }
        });

        JB_MenuPrincipal.setText("Menu Principal");
        JB_MenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_MenuPrincipalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_FondoLayout = new javax.swing.GroupLayout(JP_Fondo);
        JP_Fondo.setLayout(JP_FondoLayout);
        JP_FondoLayout.setHorizontalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_ConfiguracionMFCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JP_ArchivosAudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_FondoLayout.createSequentialGroup()
                                .addComponent(JL_DirectorioSalida)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JTF_DirectorioSalida)))
                        .addContainerGap())
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(JB_Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(JB_MenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(79, Short.MAX_VALUE))))
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JP_ConfiguracionMFCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addComponent(JP_ArchivosAudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JL_DirectorioSalida)
                            .addComponent(JTF_DirectorioSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JB_Guardar)
                            .addComponent(JB_MenuPrincipal))
                        .addGap(7, 7, 7)))
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
            .addComponent(JP_Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JB_MenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_MenuPrincipalActionPerformed
        guiAnalisis.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JB_MenuPrincipalActionPerformed

    private void JRB_RuidoLMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_RuidoLMSActionPerformed
        JTF_Coeficientes.setEnabled(true);
        JTF_ValorAdaptacion.setEnabled(true);
    }//GEN-LAST:event_JRB_RuidoLMSActionPerformed

    private void JRB_RuidoNLMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_RuidoNLMSActionPerformed
        JTF_Coeficientes.setEnabled(true);
        JTF_ValorAdaptacion.setEnabled(true);
    }//GEN-LAST:event_JRB_RuidoNLMSActionPerformed

    private void JRB_RuidoNingunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_RuidoNingunoActionPerformed
        JTF_Coeficientes.setEnabled(false);
        JTF_ValorAdaptacion.setEnabled(false);
        JTF_Coeficientes.setText("0");
        JTF_ValorAdaptacion.setText("0");
    }//GEN-LAST:event_JRB_RuidoNingunoActionPerformed

    private void JRB_SegmentosEnergiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_SegmentosEnergiaActionPerformed
        JTF_SegmentosVentana.setEnabled(true);
        JTF_SegmentosUmbral.setEnabled(true);
    }//GEN-LAST:event_JRB_SegmentosEnergiaActionPerformed

    private void JRB_SegmentosRabiner1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_SegmentosRabiner1ActionPerformed
        JTF_SegmentosVentana.setEnabled(true);
        JTF_SegmentosUmbral.setEnabled(false);
        JTF_SegmentosUmbral.setText("0");
    }//GEN-LAST:event_JRB_SegmentosRabiner1ActionPerformed

    private void JRB_SegmentosRabiner2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_SegmentosRabiner2ActionPerformed
        JTF_SegmentosVentana.setEnabled(true);
        JTF_SegmentosUmbral.setEnabled(false);
        JTF_SegmentosUmbral.setText("0");
    }//GEN-LAST:event_JRB_SegmentosRabiner2ActionPerformed

    private void JRB_SegmentosNingunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_SegmentosNingunoActionPerformed
        JTF_SegmentosVentana.setEnabled(false);
        JTF_SegmentosUmbral.setEnabled(false);
        JTF_SegmentosVentana.setText("0");
        JTF_SegmentosUmbral.setText("0");
    }//GEN-LAST:event_JRB_SegmentosNingunoActionPerformed

    private void JB_CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CargarActionPerformed
        if (fileChooserAbrir.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooserAbrir.getSelectedFile().getAbsolutePath();
            fileChooserAbrir.setCurrentDirectory(new File(ruta));
            String rutaDirectorio = fileChooserAbrir.getCurrentDirectory().getAbsolutePath();
            JTF_DirectorioEntrada.setText(rutaDirectorio);
            String[] listaArchivos = fileChooserAbrir.getCurrentDirectory().list();
            ArrayList<String> listaArchivosWav = new ArrayList<>();
            for (String archivo : listaArchivos) {
                if (archivo.endsWith(".wav") || archivo.endsWith(".WAV")) {
                    listaArchivosWav.add(archivo);
                }
            }
            if (listaArchivosWav.size() > 0) {
                if (JRB_RuidoNinguno.isSelected()) {
                    listaAudiosVoz = listaArchivosWav;
                    listaAudiosRuido = null;
                    listaArchivos = new String[listaAudiosVoz.size()];
                    for (int i = 0; i < listaAudiosVoz.size(); i++) {
                        listaArchivos[i] = listaAudiosVoz.get(i);
                    }
                    JL_ListaAudiosVoz.setListData(listaArchivos);
                    String[] lista = {""};
                    JL_ListaAudiosRuido.setListData(lista);
                } else {
                    listaAudiosVoz = new ArrayList<>();
                    listaAudiosRuido = new ArrayList<>();
                    for (int i = 0; i < listaArchivosWav.size(); i++) {
                        if (listaArchivosWav.contains("_ruido")) {
                            listaAudiosRuido.add(listaArchivosWav.get(i));
                        } else {
                            listaAudiosVoz.add(listaArchivosWav.get(i));
                        }
                    }
                    if (listaAudiosVoz.size() != listaAudiosRuido.size()) {
                        listaAudiosVoz = null;
                        listaAudiosRuido = null;
                        String[] lista = {""};
                        JL_ListaAudiosVoz.setListData(lista);
                        JL_ListaAudiosRuido.setListData(lista);
                        String msj = "Algunos audios de voz no tienen\n"+
                                     "su repectivo audio de ruido!";
                        JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        listaArchivos = new String[listaAudiosVoz.size()];
                        for (int i = 0; i < listaAudiosVoz.size(); i++) {
                            listaArchivos[i] = listaAudiosVoz.get(i);
                        }
                        JL_ListaAudiosVoz.setListData(listaArchivos);
                        listaArchivos = new String[listaAudiosRuido.size()];
                        for (int i = 0; i < listaAudiosRuido.size(); i++) {
                            listaArchivos[i] = listaAudiosRuido.get(i);
                        }
                        JL_ListaAudiosRuido.setListData(listaArchivos);
                    }
                }
            } else {
                listaAudiosVoz = null;
                listaAudiosRuido = null;
                String[] lista = {""};
                JL_ListaAudiosVoz.setListData(lista);
                JL_ListaAudiosRuido.setListData(lista);
                String msj = "No hay archivos de audio .wav!";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_CargarActionPerformed

    private void JB_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_GuardarActionPerformed
        String mensaje = verificacionDatos();
        if (mensaje.equals("")) {
            if (fileChooserGuardar.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String rutaDirectorioEntrada = fileChooserAbrir.getCurrentDirectory().getAbsolutePath();
                String rutaDirectorioSalida = fileChooserGuardar.getSelectedFile().getAbsolutePath();
                JTF_DirectorioSalida.setText(rutaDirectorioSalida);
                double[] senalFinal;
                int coef = Integer.parseInt(JTF_Coeficientes.getText());
                double adaptacion = Double.parseDouble(JTF_ValorAdaptacion.getText());
                double alfa = Double.parseDouble(JTF_FiltroAlfa.getText());
                int TV = Integer.parseInt(JTF_SegmentosVentana.getText());
                double umb = Double.parseDouble(JTF_SegmentosUmbral.getText());
                int TV2 = Integer.parseInt(JTF_VentanaTamano.getText());
                int DS = Integer.parseInt(JTF_VentanaSolapamiento.getText());
                int M = Integer.parseInt(JCB_M.getSelectedItem().toString());
                int BC = Integer.parseInt(JCB_BC.getSelectedItem().toString());
                double men, may, max;
                for (int i = 0; i < listaAudiosVoz.size(); i++) {
                    Audio audioVoz = new Audio();
                    audioVoz.abrirAudio(new File(rutaDirectorioEntrada+"\\"+listaAudiosVoz.get(i)));
                    senalFinal = audioVoz.getDatos().getAmplitudes();
                    
                    if (!JRB_RuidoNinguno.isSelected()) {
                        double[] senalEntrada = Preprocesamiento.normalizar(senalFinal, 32768);
                        Audio audioRuido = new Audio();
                        audioRuido.abrirAudio(new File(rutaDirectorioEntrada+"\\"+listaAudiosRuido.get(i)));
                        double[] senalRuido = Preprocesamiento.normalizar(
                                              audioRuido.getDatos().getAmplitudes(), 32768);
                        LMS lms = new LMS(senalEntrada, senalRuido);
                        if (JRB_RuidoLMS.isSelected()) {
                            lms.LMS(adaptacion, coef, null);
                        } else {
                            lms.NLMS(adaptacion, coef, null);
                        }
                        senalFinal = lms.getSenalDeseada();
                        men = Utilitarios.getMenor(senalFinal);
                        may = Utilitarios.getMayor(senalFinal);
                        max = Utilitarios.getMaximo(may, men);
                        senalFinal = Preprocesamiento.normalizarInt(senalFinal, -max, max, -32768, 32767);
                    }
                    
                    senalFinal = Preprocesamiento.preEnfasis(senalFinal, alfa);
                    men = Utilitarios.getMenor(senalFinal);
                    may = Utilitarios.getMayor(senalFinal);
                    max = Utilitarios.getMaximo(may, men);
                    if (max < 32768) {
                        max = 32768;
                    }
                    senalFinal = Preprocesamiento.normalizarDouble(senalFinal, -max, max, -1, 1);
                    
                    if (!JRB_SegmentosNinguno.isSelected()) {
                        int[] puntos;
                        if (JRB_SegmentosEnergia.isSelected()) {
                            puntos = Preprocesamiento.eliminarSegmentosInutiles_Energia(TV, umb, senalFinal);
                        } else if (JRB_SegmentosRabiner1.isSelected()) {
                            puntos = Preprocesamiento.eliminarSegmentosInutiles_RabinerSambur(TV, senalFinal, 1);
                        } else {
                            puntos = Preprocesamiento.eliminarSegmentosInutiles_RabinerSambur(TV, senalFinal, 2);
                        }
                        senalFinal = Utilitarios.recortar(senalFinal, puntos[0], puntos[1]);
                    }
                    
                    int tipo = 0;
                    if (JRB_VentanaHamming.isSelected()) {
                        tipo = 1;
                    } else if (JRB_VentanaHanning.isSelected()) {
                        tipo = 2;
                    }
                    Ventanamiento ventanamiento = new Ventanamiento(senalFinal, TV2, DS, tipo);

                    double fs = audioVoz.getFormato().getSampleRate();
                    MFCC mfcc = new MFCC(ventanamiento.getMatriz(), TV2, M, BC, fs);
                    double[][] moMFCC = null;
                    if (JRB_MFCC13.isSelected()) {
                        moMFCC = mfcc.getResultMFCC(0, fs/2);
                    } else if (JRB_MFCC26.isSelected()) {
                        moMFCC = mfcc.getResultMFCCDelta(0, fs/2);
                    } else if (JRB_MFCC39.isSelected()) {
                        moMFCC = mfcc.getResultMFCCDeltaDelta(0, fs/2);
                    }

                    String[] nombreArchivo = listaAudiosVoz.get(i).split("_");
                    String usuario = nombreArchivo[0];
                    String palabra = nombreArchivo[1];
                    String vez = nombreArchivo[2].split("\\.")[0];
                    String rutaSalida = JTF_DirectorioSalida.getText()+"\\"+usuario+"_"+palabra+"_"+vez+".mfcc";
                    ArchivoMFCC archivoMFCC = new ArchivoMFCC(rutaSalida, usuario, palabra, 0, moMFCC);
                    archivoMFCC.guardarMO();
                }
                JOptionPane.showMessageDialog(this, "Audios procesados correctamente!");
            }
        } else {
            JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_GuardarActionPerformed

    private String verificacionDatos() {
        String msj = "";
        if (!JRB_RuidoNinguno.isSelected()) {
            try {
                Integer.parseInt(JTF_Coeficientes.getText());
            } catch(NumberFormatException ex) {
                msj += "-> El nro de coeficientes debe ser un numero entero\n";
            }
            try {
                Double.parseDouble(JTF_ValorAdaptacion.getText());
            } catch (NumberFormatException ex) {
                msj += "-> El paso de adaptacion debe ser un numero real\n";
            }
        }
        if (!JRB_SegmentosNinguno.isSelected()) {
            try {
                Integer.parseInt(JTF_SegmentosVentana.getText());
            } catch(NumberFormatException ex) {
                msj += "-> El tamaño de ventana para eliminar segmentos\n"+
                       "   debe ser un numero entero\n";
            }
            try {
                Double.parseDouble(JTF_SegmentosUmbral.getText());
            } catch (NumberFormatException ex) {
                msj += "-> El umbral debe ser un numero real\n";
            }
        }
        try {
            double alfa = Double.parseDouble(JTF_FiltroAlfa.getText());
            if (alfa < 0.8 && alfa > 1) {
                msj += "-> El valor del alfa debe ser un numero entre 0.8 y 1\n";
            }
        } catch(NumberFormatException ex) {
            msj += "-> El valor del alfa debe ser un numero entre 0.8 y 1\n";
        }
        try {
            Integer.parseInt(JTF_VentanaTamano.getText());
        } catch(NumberFormatException ex) {
            msj += "-> El tamaño de ventana para el ventaneamiento debe ser\n"+
                       "    un numero entero\n";
        }
        try {
            Integer.parseInt(JTF_VentanaSolapamiento.getText());
        } catch (NumberFormatException ex) {
            msj += "-> La distancia de solapamiento debe ser un numero entero\n";
        } 
        if (listaAudiosVoz == null || listaAudiosVoz.isEmpty()) {
            msj += "-> Cargue los archivos de auido";
        }
        return msj;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup JBG_EliminarRuido;
    private javax.swing.ButtonGroup JBG_EliminarSegmentos;
    private javax.swing.ButtonGroup JBG_MFCC;
    private javax.swing.ButtonGroup JBG_Ventaneamiento;
    private javax.swing.JButton JB_Cargar;
    private javax.swing.JButton JB_Guardar;
    private javax.swing.JButton JB_MenuPrincipal;
    private javax.swing.JComboBox<String> JCB_BC;
    private javax.swing.JComboBox<String> JCB_M;
    private javax.swing.JLabel JL_AudiosRuido;
    private javax.swing.JLabel JL_AudiosVoz;
    private javax.swing.JLabel JL_BC;
    private javax.swing.JLabel JL_Coeficientes;
    private javax.swing.JLabel JL_DirectorioEntrada;
    private javax.swing.JLabel JL_DirectorioSalida;
    private javax.swing.JLabel JL_EliminarRuido;
    private javax.swing.JLabel JL_EliminarSegmentos;
    private javax.swing.JLabel JL_FiltroAlfa;
    private javax.swing.JLabel JL_FiltroPreEnfasis;
    private javax.swing.JList<String> JL_ListaAudiosRuido;
    private javax.swing.JList<String> JL_ListaAudiosVoz;
    private javax.swing.JLabel JL_M;
    private javax.swing.JLabel JL_MFCC;
    private javax.swing.JLabel JL_SegmentosUmbral;
    private javax.swing.JLabel JL_SegmentosVentana;
    private javax.swing.JLabel JL_ValorAdaptacion;
    private javax.swing.JLabel JL_VentanaSolapamiento;
    private javax.swing.JLabel JL_VentanaTamano;
    private javax.swing.JLabel JL_Ventaneamiento;
    private javax.swing.JPanel JP_ArchivosAudio;
    private javax.swing.JPanel JP_ConfiguracionMFCC;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JRadioButton JRB_MFCC13;
    private javax.swing.JRadioButton JRB_MFCC26;
    private javax.swing.JRadioButton JRB_MFCC39;
    private javax.swing.JRadioButton JRB_RuidoLMS;
    private javax.swing.JRadioButton JRB_RuidoNLMS;
    private javax.swing.JRadioButton JRB_RuidoNinguno;
    private javax.swing.JRadioButton JRB_SegmentosEnergia;
    private javax.swing.JRadioButton JRB_SegmentosNinguno;
    private javax.swing.JRadioButton JRB_SegmentosRabiner1;
    private javax.swing.JRadioButton JRB_SegmentosRabiner2;
    private javax.swing.JRadioButton JRB_VentanaHamming;
    private javax.swing.JRadioButton JRB_VentanaHanning;
    private javax.swing.JRadioButton JRB_VentanaRectangular;
    private javax.swing.JScrollPane JSP_AudiosRuido;
    private javax.swing.JScrollPane JSP_AudiosVoz;
    private javax.swing.JTextField JTF_Coeficientes;
    private javax.swing.JTextField JTF_DirectorioEntrada;
    private javax.swing.JTextField JTF_DirectorioSalida;
    private javax.swing.JTextField JTF_FiltroAlfa;
    private javax.swing.JTextField JTF_SegmentosUmbral;
    private javax.swing.JTextField JTF_SegmentosVentana;
    private javax.swing.JTextField JTF_ValorAdaptacion;
    private javax.swing.JTextField JTF_VentanaSolapamiento;
    private javax.swing.JTextField JTF_VentanaTamano;
    // End of variables declaration//GEN-END:variables
}
