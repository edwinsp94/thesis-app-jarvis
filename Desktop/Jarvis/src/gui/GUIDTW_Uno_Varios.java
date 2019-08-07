package gui;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import reconocedor.ArchivoMFCC;
import reconocedor.DTW;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class GUIDTW_Uno_Varios extends javax.swing.JFrame {

    private final GUIAnalisis guiAnalisis;
    private ArchivoMFCC archivoPrueba;
    private ArrayList<ArchivoMFCC> archivosEntrenamiento;
    private final JFileChooser fileChooser;
    
    public GUIDTW_Uno_Varios(GUIAnalisis guiAnalisis) {
        initComponents();
        this.guiAnalisis = guiAnalisis;
        this.archivoPrueba = null;
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
        JP_PatronPrueba = new javax.swing.JPanel();
        JP_PruebaInfo = new javax.swing.JPanel();
        JL_PruebaArchivo = new javax.swing.JLabel();
        JTF_PruebaArchivo = new javax.swing.JTextField();
        JL_PruebaUsuario = new javax.swing.JLabel();
        JTF_PruebaUsuario = new javax.swing.JTextField();
        JL_PruebaPalabra = new javax.swing.JLabel();
        JTF_PruebaPalabra = new javax.swing.JTextField();
        JL_PruebaCoeficientes = new javax.swing.JLabel();
        JTF_PruebaCoeficientes = new javax.swing.JTextField();
        JL_PruebaSegmentos = new javax.swing.JLabel();
        JTF_PruebaSegmentos = new javax.swing.JTextField();
        JB_PruebaAbrir = new javax.swing.JButton();
        JSP_PruebaDatos = new javax.swing.JScrollPane();
        JT_PruebaDatos = new javax.swing.JTable();
        JP_PatronesEntrenamiento = new javax.swing.JPanel();
        JL_Directorio = new javax.swing.JLabel();
        JTF_Directorio = new javax.swing.JTextField();
        JB_Cargar = new javax.swing.JButton();
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
        setTitle("DTW Uno vs. Varios");

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JP_PatronPrueba.setBackground(new java.awt.Color(255, 255, 255));
        JP_PatronPrueba.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "PATRON DE PRUEBA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JP_PruebaInfo.setBackground(new java.awt.Color(255, 255, 255));
        JP_PruebaInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_PruebaArchivo.setText("Archivo:");

        JTF_PruebaArchivo.setEditable(false);
        JTF_PruebaArchivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JL_PruebaUsuario.setText("Usuario:");

        JTF_PruebaUsuario.setEditable(false);
        JTF_PruebaUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JL_PruebaPalabra.setText("Palabra:");

        JTF_PruebaPalabra.setEditable(false);
        JTF_PruebaPalabra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JL_PruebaCoeficientes.setText("Nro. Coeficientes:");

        JTF_PruebaCoeficientes.setEditable(false);
        JTF_PruebaCoeficientes.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JL_PruebaSegmentos.setText("Nro. Segmentos:");

        JTF_PruebaSegmentos.setEditable(false);
        JTF_PruebaSegmentos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JB_PruebaAbrir.setText("Abrir");
        JB_PruebaAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_PruebaAbrirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_PruebaInfoLayout = new javax.swing.GroupLayout(JP_PruebaInfo);
        JP_PruebaInfo.setLayout(JP_PruebaInfoLayout);
        JP_PruebaInfoLayout.setHorizontalGroup(
            JP_PruebaInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PruebaInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_PruebaArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_PruebaArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_PruebaUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_PruebaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_PruebaPalabra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_PruebaPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_PruebaCoeficientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_PruebaCoeficientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_PruebaSegmentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_PruebaSegmentos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JB_PruebaAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_PruebaInfoLayout.setVerticalGroup(
            JP_PruebaInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_PruebaInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_PruebaInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_PruebaArchivo)
                    .addComponent(JTF_PruebaArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_PruebaUsuario)
                    .addComponent(JTF_PruebaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_PruebaPalabra)
                    .addComponent(JTF_PruebaPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_PruebaCoeficientes)
                    .addComponent(JTF_PruebaCoeficientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_PruebaSegmentos)
                    .addComponent(JTF_PruebaSegmentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_PruebaAbrir))
                .addContainerGap())
        );

        JT_PruebaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JSP_PruebaDatos.setViewportView(JT_PruebaDatos);

        javax.swing.GroupLayout JP_PatronPruebaLayout = new javax.swing.GroupLayout(JP_PatronPrueba);
        JP_PatronPrueba.setLayout(JP_PatronPruebaLayout);
        JP_PatronPruebaLayout.setHorizontalGroup(
            JP_PatronPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PatronPruebaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_PatronPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JP_PruebaInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JSP_PruebaDatos))
                .addContainerGap())
        );
        JP_PatronPruebaLayout.setVerticalGroup(
            JP_PatronPruebaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PatronPruebaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_PruebaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JSP_PruebaDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JP_PatronesEntrenamiento.setBackground(new java.awt.Color(255, 255, 255));
        JP_PatronesEntrenamiento.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "PATRONES DE ENTRENAMIENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JL_Directorio.setText("Directorio:");

        JB_Cargar.setText("Cargar");
        JB_Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CargarActionPerformed(evt);
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
                        .addComponent(JL_Directorio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Directorio, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JB_Cargar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JP_PatronesEntrenamientoLayout.setVerticalGroup(
            JP_PatronesEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PatronesEntrenamientoLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(JP_PatronesEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_Cargar)
                    .addComponent(JL_Directorio)
                    .addComponent(JTF_Directorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JSP_EntrenamientoPatrones)
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
                .addGap(26, 26, 26)
                .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                        .addComponent(JL_TipoDTW)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCB_TipoDTW, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                        .addComponent(JL_TipoDistancia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCB_TipoDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                        .addComponent(JL_Radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                        .addComponent(JL_DP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCB_DP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JB_Calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_MenuPrincipal))
                .addGap(24, 24, 24))
        );
        JP_ConfiguracionDTWLayout.setVerticalGroup(
            JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_TipoDistancia)
                    .addComponent(JCB_TipoDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_DP)
                    .addComponent(JCB_DP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Calcular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_Radio)
                        .addComponent(JTF_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JB_MenuPrincipal))
                    .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JL_TipoDTW)
                        .addComponent(JCB_TipoDTW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap()
                .addComponent(JSP_Resultados, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout JP_FondoLayout = new javax.swing.GroupLayout(JP_Fondo);
        JP_Fondo.setLayout(JP_FondoLayout);
        JP_FondoLayout.setHorizontalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JP_PatronPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JP_ConfiguracionDTW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JP_Resultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JP_PatronesEntrenamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_PatronPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JP_PatronesEntrenamiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addComponent(JP_ConfiguracionDTW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JP_Resultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void JB_PruebaAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_PruebaAbrirActionPerformed
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            if (ruta.endsWith(".mfcc") || ruta.endsWith(".MFCC")) {
                archivoPrueba = new ArchivoMFCC(ruta);
                archivoPrueba.abrirMO();
                JTF_PruebaArchivo.setText(archivoPrueba.getNombreArchivo());
                JTF_PruebaUsuario.setText(archivoPrueba.getUsuario());
                JTF_PruebaPalabra.setText(archivoPrueba.getPalabra());
                double[][] mfcc = archivoPrueba.getMo();
                JTF_PruebaCoeficientes.setText(String.valueOf(mfcc.length));
                JTF_PruebaSegmentos.setText(String.valueOf(mfcc[0].length));
                recargarTabla(mfcc, JT_PruebaDatos);
                JTA_Resultados.setText("");
            } else {
                String msj = "Archivo no soportado";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_PruebaAbrirActionPerformed

    private void JB_CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CargarActionPerformed
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            fileChooser.setCurrentDirectory(new File(ruta));
            String rutaDirectorio = fileChooser.getCurrentDirectory().getAbsolutePath();
            JTF_Directorio.setText(rutaDirectorio);
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
                String msj = "No hay archivos .mfcc";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_CargarActionPerformed

    private void JB_CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CalcularActionPerformed
        String msj = verificarDatos();
        if (msj.equals("")) {
            DTW dtw;
            int indMenor = -1;
            double menorDistancia = 1000000;
            String res = "============================\n"+
                          archivoPrueba.getNombreArchivo()+
                         "\n============================\n";
            for (int i = 0; i < archivosEntrenamiento.size(); i++) {
                dtw = new DTW(archivoPrueba.getMo(), 
                              archivosEntrenamiento.get(i).getMo(), 
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
                res += archivosEntrenamiento.get(i).getNombreArchivo() 
                       + " --> "+dtw.getDistanciaDTW()+"\n";
                if (dtw.getDistanciaDTW() < menorDistancia) {
                    menorDistancia = dtw.getDistanciaDTW();
                    indMenor = i;
                }
            }
            if (indMenor != -1) {
                res += "Parecido a: "+archivosEntrenamiento.get(indMenor).getNombreArchivo();
                if (!archivosEntrenamiento.get(indMenor).getUsuario().equals(JTF_PruebaUsuario.getText()) ||
                    !archivosEntrenamiento.get(indMenor).getPalabra().equals(JTF_PruebaPalabra.getText())) {
                    res += " (ERROR)";
                }
                res += "\n";
            }
            JTA_Resultados.setText(res);
            JOptionPane.showMessageDialog(this, "Proceso Finalizado!");
        } else {
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_CalcularActionPerformed

    private String verificarDatos() {
        String msj = "";
        if (archivoPrueba == null) {
            msj += "-> No ha abierto un patron prueba\n";
        } else {
            if (archivoPrueba.getMo() == null) {
                msj += "-> No hay datos del patron prueba\n";
            }
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
    
    private void recargarTabla(double[][] datos, JTable JT_MFCC) {
        int nf = datos.length;
        int nc = datos[0].length;
        String[][] data = new String[nf][nc];
        String[] col = new String[nc];
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                if (i == 0) {
                    col[j] = String.valueOf(j+1);
                }
                data[i][j] = String.valueOf(datos[i][j]);
            }
        }
        JT_MFCC.setModel(new javax.swing.table.DefaultTableModel(data, col) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Calcular;
    private javax.swing.JButton JB_Cargar;
    private javax.swing.JButton JB_MenuPrincipal;
    private javax.swing.JButton JB_PruebaAbrir;
    private javax.swing.JComboBox<String> JCB_DP;
    private javax.swing.JComboBox<String> JCB_TipoDTW;
    private javax.swing.JComboBox<String> JCB_TipoDistancia;
    private javax.swing.JLabel JL_DP;
    private javax.swing.JLabel JL_Directorio;
    private javax.swing.JList<String> JL_EntrenamientoPatrones;
    private javax.swing.JLabel JL_PruebaArchivo;
    private javax.swing.JLabel JL_PruebaCoeficientes;
    private javax.swing.JLabel JL_PruebaPalabra;
    private javax.swing.JLabel JL_PruebaSegmentos;
    private javax.swing.JLabel JL_PruebaUsuario;
    private javax.swing.JLabel JL_Radio;
    private javax.swing.JLabel JL_TipoDTW;
    private javax.swing.JLabel JL_TipoDistancia;
    private javax.swing.JPanel JP_ConfiguracionDTW;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JPanel JP_PatronPrueba;
    private javax.swing.JPanel JP_PatronesEntrenamiento;
    private javax.swing.JPanel JP_PruebaInfo;
    private javax.swing.JPanel JP_Resultados;
    private javax.swing.JScrollPane JSP_EntrenamientoPatrones;
    private javax.swing.JScrollPane JSP_PruebaDatos;
    private javax.swing.JScrollPane JSP_Resultados;
    private javax.swing.JTextArea JTA_Resultados;
    private javax.swing.JTextField JTF_Directorio;
    private javax.swing.JTextField JTF_PruebaArchivo;
    private javax.swing.JTextField JTF_PruebaCoeficientes;
    private javax.swing.JTextField JTF_PruebaPalabra;
    private javax.swing.JTextField JTF_PruebaSegmentos;
    private javax.swing.JTextField JTF_PruebaUsuario;
    private javax.swing.JTextField JTF_Radio;
    private javax.swing.JTable JT_PruebaDatos;
    // End of variables declaration//GEN-END:variables
}
