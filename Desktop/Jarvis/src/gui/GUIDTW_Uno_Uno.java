package gui;

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

public class GUIDTW_Uno_Uno extends javax.swing.JFrame {

    private final GUIAnalisis guiAnalisis;
    private ArchivoMFCC archivoPrueba;
    private ArchivoMFCC archivoEntrenamiento;
    private final JFileChooser fileChooserAbrir;
    private final JFileChooser fileChooserGuardar;
    private double[][] mFusion;
    
    public GUIDTW_Uno_Uno(GUIAnalisis guiAnalisis) {
        initComponents();
        this.guiAnalisis = guiAnalisis;
        this.archivoPrueba = null;
        this.archivoEntrenamiento = null;
        this.fileChooserAbrir = new JFileChooser();
        this.fileChooserAbrir.setFileFilter(
        new FileNameExtensionFilter("Todos los Archivos *.mfcc", "mfcc", "MFCC"));
        this.fileChooserGuardar = new JFileChooser();
        this.fileChooserGuardar.addChoosableFileFilter(
        new FileNameExtensionFilter("Todos los Archivos *.mfcc", "mfcc", "MFCC"));
        this.mFusion = null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JP_Fondo = new javax.swing.JPanel();
        JTP_PatronesEntrada = new javax.swing.JTabbedPane();
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
        JP_PatronEntrenamiento = new javax.swing.JPanel();
        JP_EntrenamientoInfo = new javax.swing.JPanel();
        JL_EntrenamientoArchvio = new javax.swing.JLabel();
        JTF_EntrenamientoArchivo = new javax.swing.JTextField();
        JL_EntrenamientoUsuario = new javax.swing.JLabel();
        JTF_EntrenamientoUsuario = new javax.swing.JTextField();
        JL_EntrenamientoPalabra = new javax.swing.JLabel();
        JTF_EntrenamientoPalabra = new javax.swing.JTextField();
        JL_EntrenamientoCoeficientes = new javax.swing.JLabel();
        JTF_EntrenamientoCoeficientes = new javax.swing.JTextField();
        JL_EntrenamientoSegmentos = new javax.swing.JLabel();
        JTF_EntrenamientoSegmentos = new javax.swing.JTextField();
        JB_EntrenamientoAbrir = new javax.swing.JButton();
        JSP_EntrenamientoDatos = new javax.swing.JScrollPane();
        JT_EntrenamientoDatos = new javax.swing.JTable();
        JP_ConfiguracionDTW = new javax.swing.JPanel();
        JL_TipoDistancia = new javax.swing.JLabel();
        JCB_TipoDistancia = new javax.swing.JComboBox<>();
        JL_TipoDTW = new javax.swing.JLabel();
        JCB_TipoDTW = new javax.swing.JComboBox<>();
        JL_DP = new javax.swing.JLabel();
        JCB_DP = new javax.swing.JComboBox<>();
        JL_Radio = new javax.swing.JLabel();
        JTF_Radio = new javax.swing.JTextField();
        JL_Resultado = new javax.swing.JLabel();
        JTF_Resultado = new javax.swing.JTextField();
        JB_Calcular = new javax.swing.JButton();
        JB_MenuPrincipal = new javax.swing.JButton();
        JTP_Resultados = new javax.swing.JTabbedPane();
        JP_MatrizDistancias = new javax.swing.JPanel();
        JSP_MatrizDistancias = new javax.swing.JScrollPane();
        JT_MatrizDistancias = new javax.swing.JTable();
        JP_MatrizPesos = new javax.swing.JPanel();
        JSP_MatrizPesos = new javax.swing.JScrollPane();
        JT_MatrizPesos = new javax.swing.JTable();
        JP_Camino = new javax.swing.JPanel();
        JSP_Camino = new javax.swing.JScrollPane();
        JT_Camino = new javax.swing.JTable();
        JP_Fusion = new javax.swing.JPanel();
        JSP_Fusion = new javax.swing.JScrollPane();
        JT_Fusion = new javax.swing.JTable();
        JB_FusionGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("DTW Uno vs. Uno");

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JTP_PatronesEntrada.setBackground(new java.awt.Color(255, 255, 255));
        JTP_PatronesEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "PATRONES DE ENTRADA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JP_PatronPrueba.setBackground(new java.awt.Color(255, 255, 255));

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
                .addGap(2, 2, 2)
                .addComponent(JTF_PruebaPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_PruebaCoeficientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_PruebaCoeficientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_PruebaSegmentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_PruebaSegmentos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addComponent(JP_PruebaInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JSP_PruebaDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JTP_PatronesEntrada.addTab("Patron de Prueba", JP_PatronPrueba);

        JP_PatronEntrenamiento.setBackground(new java.awt.Color(255, 255, 255));

        JP_EntrenamientoInfo.setBackground(new java.awt.Color(255, 255, 255));
        JP_EntrenamientoInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JL_EntrenamientoArchvio.setText("Archivo:");

        JTF_EntrenamientoArchivo.setEditable(false);
        JTF_EntrenamientoArchivo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JL_EntrenamientoUsuario.setText("Usuario:");

        JTF_EntrenamientoUsuario.setEditable(false);
        JTF_EntrenamientoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JL_EntrenamientoPalabra.setText("Palabra:");

        JTF_EntrenamientoPalabra.setEditable(false);
        JTF_EntrenamientoPalabra.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JL_EntrenamientoCoeficientes.setText("Nro. Coeficientes:");

        JTF_EntrenamientoCoeficientes.setEditable(false);
        JTF_EntrenamientoCoeficientes.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JL_EntrenamientoSegmentos.setText("Nro. Segmentos:");

        JTF_EntrenamientoSegmentos.setEditable(false);
        JTF_EntrenamientoSegmentos.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        JB_EntrenamientoAbrir.setText("Abrir");
        JB_EntrenamientoAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_EntrenamientoAbrirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_EntrenamientoInfoLayout = new javax.swing.GroupLayout(JP_EntrenamientoInfo);
        JP_EntrenamientoInfo.setLayout(JP_EntrenamientoInfoLayout);
        JP_EntrenamientoInfoLayout.setHorizontalGroup(
            JP_EntrenamientoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_EntrenamientoInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_EntrenamientoArchvio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_EntrenamientoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_EntrenamientoUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_EntrenamientoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_EntrenamientoPalabra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_EntrenamientoPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_EntrenamientoCoeficientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_EntrenamientoCoeficientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_EntrenamientoSegmentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_EntrenamientoSegmentos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(JB_EntrenamientoAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JP_EntrenamientoInfoLayout.setVerticalGroup(
            JP_EntrenamientoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_EntrenamientoInfoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JP_EntrenamientoInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_EntrenamientoArchvio)
                    .addComponent(JTF_EntrenamientoArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_EntrenamientoUsuario)
                    .addComponent(JTF_EntrenamientoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_EntrenamientoPalabra)
                    .addComponent(JTF_EntrenamientoPalabra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_EntrenamientoCoeficientes)
                    .addComponent(JTF_EntrenamientoCoeficientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_EntrenamientoSegmentos)
                    .addComponent(JTF_EntrenamientoSegmentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_EntrenamientoAbrir))
                .addContainerGap())
        );

        JT_EntrenamientoDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JSP_EntrenamientoDatos.setViewportView(JT_EntrenamientoDatos);

        javax.swing.GroupLayout JP_PatronEntrenamientoLayout = new javax.swing.GroupLayout(JP_PatronEntrenamiento);
        JP_PatronEntrenamiento.setLayout(JP_PatronEntrenamientoLayout);
        JP_PatronEntrenamientoLayout.setHorizontalGroup(
            JP_PatronEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PatronEntrenamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_PatronEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JP_EntrenamientoInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JSP_EntrenamientoDatos))
                .addContainerGap())
        );
        JP_PatronEntrenamientoLayout.setVerticalGroup(
            JP_PatronEntrenamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_PatronEntrenamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JP_EntrenamientoInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JSP_EntrenamientoDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JTP_PatronesEntrada.addTab("Patron de Entrenamiento", JP_PatronEntrenamiento);

        JP_ConfiguracionDTW.setBackground(new java.awt.Color(255, 255, 255));
        JP_ConfiguracionDTW.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "CONFIGURACION DTW", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JL_TipoDistancia.setText("Tipo de Distancia:");

        JCB_TipoDistancia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MSE", "Euclidiana" }));

        JL_TipoDTW.setText("Tipo DTW:");

        JCB_TipoDTW.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asimetrico", "Simetrico" }));

        JL_DP.setText("DP:");

        JCB_DP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1/2", "1", "2" }));

        JL_Radio.setText("Radio:");

        JL_Resultado.setText("Resultado:");

        JTF_Resultado.setEditable(false);
        JTF_Resultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
                .addContainerGap()
                .addComponent(JL_TipoDistancia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCB_TipoDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_TipoDTW)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCB_TipoDTW, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_DP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCB_DP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JL_Radio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_Resultado, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTF_Resultado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JB_Calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JB_MenuPrincipal)
                .addContainerGap())
        );
        JP_ConfiguracionDTWLayout.setVerticalGroup(
            JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ConfiguracionDTWLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_ConfiguracionDTWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_TipoDistancia)
                    .addComponent(JL_TipoDTW)
                    .addComponent(JCB_TipoDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCB_TipoDTW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_DP)
                    .addComponent(JCB_DP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JL_Radio)
                    .addComponent(JTF_Radio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_Calcular)
                    .addComponent(JB_MenuPrincipal)
                    .addComponent(JL_Resultado)
                    .addComponent(JTF_Resultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Resultados.setBackground(new java.awt.Color(255, 255, 255));
        JTP_Resultados.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "RESULTADOS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JP_MatrizDistancias.setBackground(new java.awt.Color(255, 255, 255));

        JT_MatrizDistancias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JSP_MatrizDistancias.setViewportView(JT_MatrizDistancias);

        javax.swing.GroupLayout JP_MatrizDistanciasLayout = new javax.swing.GroupLayout(JP_MatrizDistancias);
        JP_MatrizDistancias.setLayout(JP_MatrizDistanciasLayout);
        JP_MatrizDistanciasLayout.setHorizontalGroup(
            JP_MatrizDistanciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MatrizDistanciasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSP_MatrizDistancias, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_MatrizDistanciasLayout.setVerticalGroup(
            JP_MatrizDistanciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MatrizDistanciasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSP_MatrizDistancias, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTP_Resultados.addTab("Matriz de Distancias", JP_MatrizDistancias);

        JP_MatrizPesos.setBackground(new java.awt.Color(255, 255, 255));

        JT_MatrizPesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JSP_MatrizPesos.setViewportView(JT_MatrizPesos);

        javax.swing.GroupLayout JP_MatrizPesosLayout = new javax.swing.GroupLayout(JP_MatrizPesos);
        JP_MatrizPesos.setLayout(JP_MatrizPesosLayout);
        JP_MatrizPesosLayout.setHorizontalGroup(
            JP_MatrizPesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MatrizPesosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSP_MatrizPesos, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_MatrizPesosLayout.setVerticalGroup(
            JP_MatrizPesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_MatrizPesosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSP_MatrizPesos, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        JTP_Resultados.addTab("Matriz de Pesos", JP_MatrizPesos);

        JP_Camino.setBackground(new java.awt.Color(255, 255, 255));

        JT_Camino.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JSP_Camino.setViewportView(JT_Camino);

        javax.swing.GroupLayout JP_CaminoLayout = new javax.swing.GroupLayout(JP_Camino);
        JP_Camino.setLayout(JP_CaminoLayout);
        JP_CaminoLayout.setHorizontalGroup(
            JP_CaminoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_CaminoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSP_Camino, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                .addContainerGap())
        );
        JP_CaminoLayout.setVerticalGroup(
            JP_CaminoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_CaminoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSP_Camino, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        JTP_Resultados.addTab("Mejor Camino", JP_Camino);

        JP_Fusion.setBackground(new java.awt.Color(255, 255, 255));

        JT_Fusion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        JSP_Fusion.setViewportView(JT_Fusion);

        JB_FusionGuardar.setText("Guardar");
        JB_FusionGuardar.setEnabled(false);
        JB_FusionGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_FusionGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_FusionLayout = new javax.swing.GroupLayout(JP_Fusion);
        JP_Fusion.setLayout(JP_FusionLayout);
        JP_FusionLayout.setHorizontalGroup(
            JP_FusionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FusionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSP_Fusion, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(JP_FusionLayout.createSequentialGroup()
                .addGap(422, 422, 422)
                .addComponent(JB_FusionGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_FusionLayout.setVerticalGroup(
            JP_FusionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FusionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSP_Fusion, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JB_FusionGuardar)
                .addContainerGap())
        );

        JTP_Resultados.addTab("Fusion de Audios", JP_Fusion);

        javax.swing.GroupLayout JP_FondoLayout = new javax.swing.GroupLayout(JP_Fondo);
        JP_Fondo.setLayout(JP_FondoLayout);
        JP_FondoLayout.setHorizontalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTP_Resultados, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JTP_PatronesEntrada)
                    .addComponent(JP_ConfiguracionDTW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JTP_PatronesEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JP_ConfiguracionDTW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTP_Resultados, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if (fileChooserAbrir.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooserAbrir.getSelectedFile().getAbsolutePath();
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
                recargarTabla(null, JT_MatrizDistancias);
                recargarTabla(null, JT_MatrizPesos);
                recargarTabla(null, JT_Camino);
                mFusion = null;
                recargarTabla(mFusion, JT_Fusion);
                JB_FusionGuardar.setEnabled(false);
                JTF_Resultado.setText("");
            } else {
                String msj = "Archivo no soportado";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_PruebaAbrirActionPerformed

    private void JB_EntrenamientoAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_EntrenamientoAbrirActionPerformed
        if (fileChooserAbrir.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooserAbrir.getSelectedFile().getAbsolutePath();
            if (ruta.endsWith(".mfcc") || ruta.endsWith(".MFCC")) {
                archivoEntrenamiento = new ArchivoMFCC(ruta);
                archivoEntrenamiento.abrirMO();
                JTF_EntrenamientoArchivo.setText(archivoEntrenamiento.getNombreArchivo());
                JTF_EntrenamientoUsuario.setText(archivoEntrenamiento.getUsuario());
                JTF_EntrenamientoPalabra.setText(archivoEntrenamiento.getPalabra());
                double[][] mfcc = archivoEntrenamiento.getMo();
                JTF_EntrenamientoCoeficientes.setText(String.valueOf(mfcc.length));
                JTF_EntrenamientoSegmentos.setText(String.valueOf(mfcc[0].length));
                recargarTabla(mfcc, JT_EntrenamientoDatos);
                recargarTabla(null, JT_MatrizDistancias);
                recargarTabla(null, JT_MatrizPesos);
                recargarTabla(null, JT_Camino);
                mFusion = null;
                recargarTabla(mFusion, JT_Fusion);
                JB_FusionGuardar.setEnabled(false);
                JTF_Resultado.setText("");
            } else {
                String msj = "Archivo no soportado";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_EntrenamientoAbrirActionPerformed

    private void JB_CalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CalcularActionPerformed
        String msj = verificarDatos();
        if (msj.equals("")) {
            DTW dtw = new DTW(archivoPrueba.getMo(), archivoEntrenamiento.getMo(), 
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
            recargarTabla(dtw.getmDistancia(), JT_MatrizDistancias);
            recargarTabla(dtw.getmPesos(), JT_MatrizPesos);
            recargarTabla(dtw.getmCamino(), JT_Camino);
            if (archivoPrueba.getUsuario().equals(archivoEntrenamiento.getUsuario()) && 
                archivoPrueba.getPalabra().equals(archivoEntrenamiento.getPalabra())) {
                mFusion = dtw.fusionarAudios2();
                JB_FusionGuardar.setEnabled(true);
            } else {
                mFusion = null;
                JB_FusionGuardar.setEnabled(false);
            }
            recargarTabla(mFusion, JT_Fusion);
            JTF_Resultado.setText(""+dtw.getDistanciaDTW());
        } else {
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_CalcularActionPerformed

    private void JB_FusionGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_FusionGuardarActionPerformed
        if (mFusion != null) {
            String usuario = archivoPrueba.getUsuario();
            String palabra = archivoPrueba.getPalabra();
            if (fileChooserGuardar.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String ruta = fileChooserGuardar.getSelectedFile().getAbsolutePath();
                if (!ruta.endsWith(".mfcc")) {
                    ruta += ".mfcc";
                }
                ArchivoMFCC archivoMFCC = new ArchivoMFCC(ruta, usuario, palabra, 0, mFusion);
                archivoMFCC.guardarMO();
                String msj = "Guardado Exitoso!";
                JOptionPane.showMessageDialog(this, msj);
            }
        }
    }//GEN-LAST:event_JB_FusionGuardarActionPerformed

    private String verificarDatos() {
        String msj = "";
        if (archivoPrueba == null) {
            msj += "-> No ha abierto un patron prueba\n";
        } else {
            if (archivoPrueba.getMo() == null) {
                msj += "-> No hay datos del patron prueba\n";
            }
        }
        if (archivoEntrenamiento == null) {
            msj += "-> No ha abierto un patron entrenamiento\n";
        } else {
            if (archivoEntrenamiento.getMo() == null) {
                msj += "-> No hay datos del patron entrenamiento\n";
            }
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
        if (datos != null) {
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
        } else {
            JT_MFCC.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {}, new String[] {}
            ));
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_Calcular;
    private javax.swing.JButton JB_EntrenamientoAbrir;
    private javax.swing.JButton JB_FusionGuardar;
    private javax.swing.JButton JB_MenuPrincipal;
    private javax.swing.JButton JB_PruebaAbrir;
    private javax.swing.JComboBox<String> JCB_DP;
    private javax.swing.JComboBox<String> JCB_TipoDTW;
    private javax.swing.JComboBox<String> JCB_TipoDistancia;
    private javax.swing.JLabel JL_DP;
    private javax.swing.JLabel JL_EntrenamientoArchvio;
    private javax.swing.JLabel JL_EntrenamientoCoeficientes;
    private javax.swing.JLabel JL_EntrenamientoPalabra;
    private javax.swing.JLabel JL_EntrenamientoSegmentos;
    private javax.swing.JLabel JL_EntrenamientoUsuario;
    private javax.swing.JLabel JL_PruebaArchivo;
    private javax.swing.JLabel JL_PruebaCoeficientes;
    private javax.swing.JLabel JL_PruebaPalabra;
    private javax.swing.JLabel JL_PruebaSegmentos;
    private javax.swing.JLabel JL_PruebaUsuario;
    private javax.swing.JLabel JL_Radio;
    private javax.swing.JLabel JL_Resultado;
    private javax.swing.JLabel JL_TipoDTW;
    private javax.swing.JLabel JL_TipoDistancia;
    private javax.swing.JPanel JP_Camino;
    private javax.swing.JPanel JP_ConfiguracionDTW;
    private javax.swing.JPanel JP_EntrenamientoInfo;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JPanel JP_Fusion;
    private javax.swing.JPanel JP_MatrizDistancias;
    private javax.swing.JPanel JP_MatrizPesos;
    private javax.swing.JPanel JP_PatronEntrenamiento;
    private javax.swing.JPanel JP_PatronPrueba;
    private javax.swing.JPanel JP_PruebaInfo;
    private javax.swing.JScrollPane JSP_Camino;
    private javax.swing.JScrollPane JSP_EntrenamientoDatos;
    private javax.swing.JScrollPane JSP_Fusion;
    private javax.swing.JScrollPane JSP_MatrizDistancias;
    private javax.swing.JScrollPane JSP_MatrizPesos;
    private javax.swing.JScrollPane JSP_PruebaDatos;
    private javax.swing.JTextField JTF_EntrenamientoArchivo;
    private javax.swing.JTextField JTF_EntrenamientoCoeficientes;
    private javax.swing.JTextField JTF_EntrenamientoPalabra;
    private javax.swing.JTextField JTF_EntrenamientoSegmentos;
    private javax.swing.JTextField JTF_EntrenamientoUsuario;
    private javax.swing.JTextField JTF_PruebaArchivo;
    private javax.swing.JTextField JTF_PruebaCoeficientes;
    private javax.swing.JTextField JTF_PruebaPalabra;
    private javax.swing.JTextField JTF_PruebaSegmentos;
    private javax.swing.JTextField JTF_PruebaUsuario;
    private javax.swing.JTextField JTF_Radio;
    private javax.swing.JTextField JTF_Resultado;
    private javax.swing.JTabbedPane JTP_PatronesEntrada;
    private javax.swing.JTabbedPane JTP_Resultados;
    private javax.swing.JTable JT_Camino;
    private javax.swing.JTable JT_EntrenamientoDatos;
    private javax.swing.JTable JT_Fusion;
    private javax.swing.JTable JT_MatrizDistancias;
    private javax.swing.JTable JT_MatrizPesos;
    private javax.swing.JTable JT_PruebaDatos;
    // End of variables declaration//GEN-END:variables
}
