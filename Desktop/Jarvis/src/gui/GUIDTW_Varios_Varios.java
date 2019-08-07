package gui;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import reconocedor.ArchivoMFCC;
import reconocedor.DTW;
import reconocedor.TomaDecision;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class GUIDTW_Varios_Varios extends javax.swing.JFrame {

    private final GUIAnalisis guiAnalisis;
    private ArrayList<ArchivoMFCC> archivosPrueba;
    private ArrayList<ArchivoMFCC> archivosEntrenamiento;
    private final JFileChooser fileChooser;
    
    public GUIDTW_Varios_Varios(GUIAnalisis guiAnalisis) {
        initComponents();
        this.guiAnalisis = guiAnalisis;
        this.archivosPrueba = null;
        this.archivosEntrenamiento = null;
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.fileChooser.setFileFilter(
        new FileNameExtensionFilter("Todos los Archivos *.mfcc", "mfcc", "MFCC"));
        this.fileChooser.setMultiSelectionEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JP_Fondo = new javax.swing.JPanel();
        JP_PatronesPrueba = new javax.swing.JPanel();
        JL_PruebaDirectorio = new javax.swing.JLabel();
        JTF_PruebaDirectorio = new javax.swing.JTextField();
        JL_PruebaCantidad = new javax.swing.JLabel();
        JTF_PruebaCantidad = new javax.swing.JTextField();
        JB_PruebaCargar = new javax.swing.JButton();
        JSP_PruebaPatrones = new javax.swing.JScrollPane();
        JL_PruebaPatrones = new javax.swing.JList<>();
        JP_PatronesEntrenamiento = new javax.swing.JPanel();
        JL_EntrenamientoDirectorio = new javax.swing.JLabel();
        JTF_EntrenamientoDirectorio = new javax.swing.JTextField();
        JL_EntrenamientoCantidad = new javax.swing.JLabel();
        JTF_EntrenamientoCantidad = new javax.swing.JTextField();
        JB_EntrenamientoCargar = new javax.swing.JButton();
        JSP_EntrenamientoPatrones = new javax.swing.JScrollPane();
        JL_EntrenamientoPatrones = new javax.swing.JList<>();
        JP_ConfiguracionDTW = new javax.swing.JPanel();
        JL_TipoDistancia = new javax.swing.JLabel();
        JCB_TipoDistancia = new javax.swing.JComboBox<>();
        JL_TipoDTW = new javax.swing.JLabel();
        JCB_TipoDTW = new javax.swing.JComboBox<>();
        JL_DP = new javax.swing.JLabel();
        JCB_DP = new javax.swing.JComboBox<>();
        JL_Radio = new javax.swing.JLabel();
        JTF_Radio = new javax.swing.JTextField();
        JB_Calcular = new javax.swing.JButton();
        JB_MenuPrincipal = new javax.swing.JButton();
        JP_Resultados = new javax.swing.JPanel();
        JSP_Resultados = new javax.swing.JScrollPane();
        JTA_Resultados = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("DTW Varios vs. Varios");

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JP_PatronesPrueba.setBackground(new java.awt.Color(255, 255, 255));
        JP_PatronesPrueba.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "PATRONES DE PRUEBA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JL_PruebaDirectorio.setText("Directorio:");

        JL_PruebaCantidad.setText("Cantidad:");

        JTF_PruebaCantidad.setEditable(false);

        JB_PruebaCargar.setText("Cargar");
        JB_PruebaCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_PruebaCargarActionPerformed(evt);
            }
        });

        JSP_PruebaPatrones.setViewportView(JL_PruebaPatrones);

        javax.swing.GroupLayout JP_PatronesPruebaLayout = new javax.swing.GroupLayout(JP_PatronesPrueba);
        JP_PatronesPrueba.setLayout(JP_PatronesPruebaLayout);
        JP_PatronesPruebaLayout.setHorizontalGroup(
            JP_PatronesPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PatronesPruebaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_PatronesPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JSP_PruebaPatrones)
                    .addGroup(JP_PatronesPruebaLayout.createSequentialGroup()
                        .addGroup(JP_PatronesPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JL_PruebaDirectorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JL_PruebaCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(JP_PatronesPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_PatronesPruebaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTF_PruebaCantidad)
                                .addGap(18, 18, 18)
                                .addComponent(JB_PruebaCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JP_PatronesPruebaLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(JTF_PruebaDirectorio)))))
                .addContainerGap())
        );
        JP_PatronesPruebaLayout.setVerticalGroup(
            JP_PatronesPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PatronesPruebaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_PatronesPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_PruebaDirectorio)
                    .addComponent(JTF_PruebaDirectorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_PatronesPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_PruebaCantidad)
                    .addComponent(JTF_PruebaCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_PruebaCargar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JSP_PruebaPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JP_PatronesEntrenamiento.setBackground(new java.awt.Color(255, 255, 255));
        JP_PatronesEntrenamiento.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "PATRONES DE ENTRENAMIENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JL_EntrenamientoDirectorio.setText("Directorio:");

        JL_EntrenamientoCantidad.setText("Cantidad:");

        JTF_EntrenamientoCantidad.setEditable(false);

        JB_EntrenamientoCargar.setText("Cargar");
        JB_EntrenamientoCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_EntrenamientoCargarActionPerformed(evt);
            }
        });

        JSP_EntrenamientoPatrones.setViewportView(JL_EntrenamientoPatrones);

        javax.swing.GroupLayout JP_PatronesEntrenamientoLayout = new javax.swing.GroupLayout(JP_PatronesEntrenamiento);
        JP_PatronesEntrenamiento.setLayout(JP_PatronesEntrenamientoLayout);
        JP_PatronesEntrenamientoLayout.setHorizontalGroup(
            JP_PatronesEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PatronesEntrenamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_PatronesEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JSP_EntrenamientoPatrones)
                    .addGroup(JP_PatronesEntrenamientoLayout.createSequentialGroup()
                        .addGroup(JP_PatronesEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JL_EntrenamientoDirectorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JL_EntrenamientoCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(JP_PatronesEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JP_PatronesEntrenamientoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JTF_EntrenamientoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JB_EntrenamientoCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JP_PatronesEntrenamientoLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(JTF_EntrenamientoDirectorio)))))
                .addContainerGap())
        );
        JP_PatronesEntrenamientoLayout.setVerticalGroup(
            JP_PatronesEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PatronesEntrenamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_PatronesEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_EntrenamientoDirectorio)
                    .addComponent(JTF_EntrenamientoDirectorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_PatronesEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_EntrenamientoCantidad)
                    .addComponent(JTF_EntrenamientoCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_EntrenamientoCargar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JSP_EntrenamientoPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JP_ConfiguracionDTW.setBackground(new java.awt.Color(255, 255, 255));
        JP_ConfiguracionDTW.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "CONFIGURACION DTW", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JL_TipoDistancia.setText("Tipo de Distancia:");

        JCB_TipoDistancia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MSE", "Euclidiana" }));

        JL_TipoDTW.setText("Tipo DTW:");

        JCB_TipoDTW.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asimetrico", "Simetrico" }));

        JL_DP.setText("DP:");

        JCB_DP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1/2", "1", "2" }));

        JL_Radio.setText("Radio:");

        JB_Calcular.setText("Calcular");
        JB_Calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CalcularActionPerformed(evt);
            }
        });

        JB_MenuPrincipal.setText("Menu Principal");
        JB_MenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_MenuPrincipalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_ConfiguracionDTWLayout = new javax.swing.GroupLayout(JP_ConfiguracionDTW);
        JP_ConfiguracionDTW.setLayout(JP_ConfiguracionDTWLayout);
        JP_ConfiguracionDTWLayout.setHorizontalGroup(
            JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                        .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                                .addComponent(JL_TipoDistancia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCB_TipoDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                                .addComponent(JL_TipoDTW)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCB_TipoDTW, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                                .addComponent(JL_Radio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTF_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                                .addComponent(JL_DP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCB_DP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(JB_Calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JB_MenuPrincipal)))
                .addGap(24, 24, 24))
        );
        JP_ConfiguracionDTWLayout.setVerticalGroup(
            JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                        .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JL_TipoDistancia)
                            .addComponent(JCB_TipoDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JL_TipoDTW)
                            .addComponent(JCB_TipoDTW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                        .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JL_DP)
                            .addComponent(JCB_DP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JL_Radio)
                            .addComponent(JTF_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_MenuPrincipal)
                    .addComponent(JB_Calcular))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JP_Resultados.setBackground(new java.awt.Color(255, 255, 255));
        JP_Resultados.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "RESULTADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JTA_Resultados.setColumns(20);
        JTA_Resultados.setRows(5);
        JSP_Resultados.setViewportView(JTA_Resultados);

        javax.swing.GroupLayout JP_ResultadosLayout = new javax.swing.GroupLayout(JP_Resultados);
        JP_Resultados.setLayout(JP_ResultadosLayout);
        JP_ResultadosLayout.setHorizontalGroup(
            JP_ResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSP_Resultados)
                .addContainerGap())
        );
        JP_ResultadosLayout.setVerticalGroup(
            JP_ResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ResultadosLayout.createSequentialGroup()
                .addComponent(JSP_Resultados)
                .addContainerGap())
        );

        javax.swing.GroupLayout JP_FondoLayout = new javax.swing.GroupLayout(JP_Fondo);
        JP_Fondo.setLayout(JP_FondoLayout);
        JP_FondoLayout.setHorizontalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(JP_PatronesPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JP_ConfiguracionDTW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_Resultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JP_PatronesEntrenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addComponent(JP_ConfiguracionDTW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JP_Resultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(JP_PatronesPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JP_PatronesEntrenamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
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

    private void JB_PruebaCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_PruebaCargarActionPerformed
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            fileChooser.setCurrentDirectory(new File(ruta));
            String rutaDirectorio = fileChooser.getCurrentDirectory().getAbsolutePath();
            JTF_PruebaDirectorio.setText(rutaDirectorio);
            JTA_Resultados.setText("");
            String[] listaArchivos = fileChooser.getCurrentDirectory().list();
            ArrayList<String> listaArchivosMFCC = new ArrayList<>();
            for (String archivo : listaArchivos) {
                if (archivo.endsWith(".mfcc") || archivo.endsWith(".MFCC")) {
                    listaArchivosMFCC.add(archivo);
                }
            }
            if (listaArchivosMFCC.size() > 0) {
                listaArchivos = new String[listaArchivosMFCC.size()];
                for (int i = 0; i < listaArchivosMFCC.size(); i++) {
                    listaArchivos[i] = listaArchivosMFCC.get(i);
                }
                JL_PruebaPatrones.setListData(listaArchivos);
                JTF_PruebaCantidad.setText(""+listaArchivos.length);
                archivosPrueba = new ArrayList<>();
                for (String archivo : listaArchivos) {
                    ArchivoMFCC archivoMFCC = new ArchivoMFCC(rutaDirectorio+"\\"+archivo);
                    archivoMFCC.abrirMO();
                    archivosPrueba.add(archivoMFCC);
                }
            } else {
                archivosPrueba = null;
                String[] lista = {""};
                JL_PruebaPatrones.setListData(lista);
                JTF_PruebaCantidad.setText("0");
                String msj = "No hay archivos .mfcc";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_PruebaCargarActionPerformed

    private void JB_EntrenamientoCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_EntrenamientoCargarActionPerformed
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            fileChooser.setCurrentDirectory(new File(ruta));
            String rutaDirectorio = fileChooser.getCurrentDirectory().getAbsolutePath();
            JTF_EntrenamientoDirectorio.setText(rutaDirectorio);
            JTA_Resultados.setText("");
            String[] listaArchivos = fileChooser.getCurrentDirectory().list();
            ArrayList<String> listaArchivosMFCC = new ArrayList<>();
            for (String archivo : listaArchivos) {
                if (archivo.endsWith(".mfcc") || archivo.endsWith(".MFCC")) {
                    listaArchivosMFCC.add(archivo);
                }
            }
            if (listaArchivosMFCC.size() > 0) {
                listaArchivos = new String[listaArchivosMFCC.size()];
                for (int i = 0; i < listaArchivosMFCC.size(); i++) {
                    listaArchivos[i] = listaArchivosMFCC.get(i);
                }
                JL_EntrenamientoPatrones.setListData(listaArchivos);
                JTF_EntrenamientoCantidad.setText(""+listaArchivos.length);
                archivosEntrenamiento = new ArrayList<>();
                for (String archivo : listaArchivos) {
                    ArchivoMFCC archivoMFCC = new ArchivoMFCC(rutaDirectorio+"\\"+archivo);
                    archivoMFCC.abrirMO();
                    archivosEntrenamiento.add(archivoMFCC);
                }
            } else {
                archivosEntrenamiento = null;
                String[] lista = {""};
                JL_EntrenamientoPatrones.setListData(lista);
                JTF_EntrenamientoCantidad.setText("0");
                String msj = "No hay archivos .mfcc";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_EntrenamientoCargarActionPerformed

    private void JB_CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CalcularActionPerformed
        String msj = verificarDatos();
        if (msj.equals("")) {
            ArchivoMFCC pruebaMFCC;
            ArchivoMFCC entrenaMFCC;
            ArchivoMFCC parecidoMFCC;
            DTW dtw;
            int indMenor;
            double menorDistancia;
            String res = "";
            int aciertos = 0;
            int desaciertos = 0;
            int identificacion_correcta = 0;
            int no_identificacion = 0;
            int falsa_identificacion = 0;
            
            TomaDecision.obtenerUmbralesParaDecision(archivosEntrenamiento);
            
            for (int i = 0; i < archivosPrueba.size(); i++) {
                pruebaMFCC = archivosPrueba.get(i);
                indMenor = -1;
                menorDistancia = 1000000;
                res += "============================\n";
                res += ""+pruebaMFCC.getNombreArchivo()+"\n";
                res += "============================\n";
                for (int j = 0; j < archivosEntrenamiento.size(); j++) {
                    entrenaMFCC = archivosEntrenamiento.get(j);
                    dtw = new DTW(pruebaMFCC.getMo(), entrenaMFCC.getMo(), 
                                  Integer.parseInt(JTF_Radio.getText()), 
                                  JCB_TipoDistancia.getSelectedIndex());
                    if (JCB_TipoDTW.getSelectedItem().toString().equals("Asimetrico")) {
                        switch(JCB_DP.getSelectedItem().toString()) {
                            case "0":
                                dtw.dtwAsimetricoP0();
                                break;
                            case "1/2":
                                dtw.dtwAsimetricoP12();
                                break;
                            case "1":
                                dtw.dtwAsimetricoP1();
                                break;
                            case "2":
                                dtw.dtwAsimetricoP2();
                                break;
                        }
                    } else {
                        switch(JCB_DP.getSelectedItem().toString()) {
                            case "0":
                                dtw.dtwSimetricoP0();
                                break;
                            case "1/2":
                                dtw.dtwSimetricoP12();
                                break;
                            case "1":
                                dtw.dtwSimetricoP1();
                                break;
                            case "2":
                                dtw.dtwSimetricoP2();
                                break;
                        }
                    }
                    res += entrenaMFCC.getNombreArchivo()+" --> "+dtw.getDistanciaDTW()+"\n";     
                    if (dtw.getDistanciaDTW() < menorDistancia) {
                        menorDistancia = dtw.getDistanciaDTW();
                        indMenor = j;
                    }
                }
                if (indMenor != -1) {
                    parecidoMFCC = archivosEntrenamiento.get(indMenor);
                    res += "Parecido a: "+parecidoMFCC.getNombreArchivo();
                    if (//!parecidoMFCC.getUsuario().equals(pruebaMFCC.getUsuario()) ||
                        !parecidoMFCC.getPalabra().equals(pruebaMFCC.getPalabra())) {
                        res += " (ERROR)";
                        desaciertos++;
                    } else {
                        aciertos++;
                    }
                    if (menorDistancia > parecidoMFCC.getUmbral()) {//0.51
                        no_identificacion++;
                        res += " (NO IDENTIFICACION)";
                    } else {
                        if (menorDistancia < 0.31) {
                            identificacion_correcta++;
                        } else {
                            falsa_identificacion++;
                            res += " (FALSA IDENTIFICACION)";
                        }
                    }
                    res += "\n\n";
                }
            }
            res += "Aciertos: "+aciertos+"\n";
            res += "Desaciertos: "+desaciertos+"\n\n";
            res += "Identificacion Correcta: "+identificacion_correcta+"\n";
            res += "Falsa Identificacion: "+falsa_identificacion+"\n";
            res += "No Identificacion: "+no_identificacion+"\n";
            JTA_Resultados.setText(res);
            JOptionPane.showMessageDialog(this, "Proceso Finalizado!");
        } else {
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_CalcularActionPerformed

    private String verificarDatos() {
        String msj = "";
        if (archivosPrueba == null || archivosPrueba.isEmpty()) {
            msj += "-> No ha cargado los patrones de prueba\n";
        }
        if (archivosEntrenamiento == null || archivosEntrenamiento.isEmpty()) {
            msj += "-> No ha cargado los patrones de entrenamiento\n";
        }
        if (JTF_Radio.getText() == null || JTF_Radio.getText().equals("")) {
            msj += "-> Ingrese el valor del radio";
        } else {
            try {
                Integer.parseInt(JTF_Radio.getText());
            } catch(NumberFormatException ex) {
                msj += "-> El radio debe ser un numero entero";
            }
        }
        return msj;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Calcular;
    private javax.swing.JButton JB_EntrenamientoCargar;
    private javax.swing.JButton JB_MenuPrincipal;
    private javax.swing.JButton JB_PruebaCargar;
    private javax.swing.JComboBox<String> JCB_DP;
    private javax.swing.JComboBox<String> JCB_TipoDTW;
    private javax.swing.JComboBox<String> JCB_TipoDistancia;
    private javax.swing.JLabel JL_DP;
    private javax.swing.JLabel JL_EntrenamientoCantidad;
    private javax.swing.JLabel JL_EntrenamientoDirectorio;
    private javax.swing.JList<String> JL_EntrenamientoPatrones;
    private javax.swing.JLabel JL_PruebaCantidad;
    private javax.swing.JLabel JL_PruebaDirectorio;
    private javax.swing.JList<String> JL_PruebaPatrones;
    private javax.swing.JLabel JL_Radio;
    private javax.swing.JLabel JL_TipoDTW;
    private javax.swing.JLabel JL_TipoDistancia;
    private javax.swing.JPanel JP_ConfiguracionDTW;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JPanel JP_PatronesEntrenamiento;
    private javax.swing.JPanel JP_PatronesPrueba;
    private javax.swing.JPanel JP_Resultados;
    private javax.swing.JScrollPane JSP_EntrenamientoPatrones;
    private javax.swing.JScrollPane JSP_PruebaPatrones;
    private javax.swing.JScrollPane JSP_Resultados;
    private javax.swing.JTextArea JTA_Resultados;
    private javax.swing.JTextField JTF_EntrenamientoCantidad;
    private javax.swing.JTextField JTF_EntrenamientoDirectorio;
    private javax.swing.JTextField JTF_PruebaCantidad;
    private javax.swing.JTextField JTF_PruebaDirectorio;
    private javax.swing.JTextField JTF_Radio;
    // End of variables declaration//GEN-END:variables
}
