package gui;

import conexion.Cliente;
import conexion.Servidor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import reconocedor.Audio;
import reconocedor.Datos;
import reconocedor.ThreadGrabar;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class GUINuevoAudio extends javax.swing.JFrame {

    private final GUIAnalisis guiAnalisis;
    private final int tipoGrabacion;
    private final JFileChooser fileChooser;
    private Audio audioVoz;
    private Audio audioRuido;
    private ThreadGrabar threadGrabar;
    private TargetDataLine targetDataLine;
    private Servidor servidor;
    private Cliente cliente;
    private Timer timer;
    private int segundos;
    
    public GUINuevoAudio(GUIAnalisis parent, boolean modal, int tipoGrabacion) {
        initComponents();
        this.guiAnalisis = parent;
        this.tipoGrabacion = tipoGrabacion;
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        this.fileChooser.addChoosableFileFilter(
        new FileNameExtensionFilter("Todos los Archivos *.wav", "wav", "WAV"));
        this.audioVoz = null;
        this.audioRuido = null;
        this.threadGrabar = null;
        this.targetDataLine = null;
        this.servidor = null;
        this.cliente = null;
        this.timer = null;
        String host;
        switch (this.tipoGrabacion) {
            case 1:
                JCB_Frecuencia.setEnabled(true);
                JCB_Tipo.setEnabled(true);
                JB_EmpezarAudio.setEnabled(false);
                JRB_ServidorEncendido.setEnabled(true);
                JRB_ServidorApagado.setEnabled(true);
                JTF_IpServidor.setEnabled(false);
                JB_EncenderCliente.setEnabled(false);
                JB_ApagarCliente.setEnabled(false);
                host = Servidor.getIpNombrePC();
                if (host == null) {
                    host = "";
                }
                JL_NombreServidor.setText(host);
                JL_NombreCliente.setText(host);
                break;
            case 2:
                JCB_Frecuencia.setEnabled(false);
                JCB_Tipo.setEnabled(false);
                JB_EmpezarAudio.setEnabled(false);
                JRB_ServidorEncendido.setEnabled(false);
                JRB_ServidorApagado.setEnabled(false);
                JTF_IpServidor.setEnabled(true);
                JB_EncenderCliente.setEnabled(true);
                JB_ApagarCliente.setEnabled(false);
                host = Servidor.getIpNombrePC();
                if (host == null) {
                    host = "";
                }
                JL_NombreCliente.setText(host);
                break;
            case 3:
                JCB_Frecuencia.setEnabled(true);
                JCB_Tipo.setEnabled(true);
                JB_EmpezarAudio.setEnabled(true);
                JRB_ServidorEncendido.setEnabled(false);
                JRB_ServidorApagado.setEnabled(false);
                JTF_IpServidor.setEnabled(false);
                JB_EncenderCliente.setEnabled(false);
                JB_ApagarCliente.setEnabled(false);
                break;
        }
        JB_PararAudio.setEnabled(false);
        JB_ReproducirVoz.setEnabled(false);
        JB_GuardarVoz.setEnabled(false);
        JB_ReproducirRuido.setEnabled(false);
        JB_GuardarRuido.setEnabled(false);
        JB_BuscarDispositivos.setEnabled(false);
        JL_ListaDispositivos.setEnabled(false);
        JB_Conectar.setEnabled(false);
        JB_Desconectar.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBG_Servidor = new javax.swing.ButtonGroup();
        JP_Fondo = new javax.swing.JPanel();
        JP_Formato = new javax.swing.JPanel();
        JL_Frecuencia = new javax.swing.JLabel();
        JCB_Frecuencia = new javax.swing.JComboBox<>();
        JL_Bits = new javax.swing.JLabel();
        JL_Canales = new javax.swing.JLabel();
        JL_Tipo = new javax.swing.JLabel();
        JCB_Tipo = new javax.swing.JComboBox<>();
        JTF_Bits = new javax.swing.JTextField();
        JTF_Canales = new javax.swing.JTextField();
        JP_Grabacion = new javax.swing.JPanel();
        JB_EmpezarAudio = new javax.swing.JButton();
        JB_PararAudio = new javax.swing.JButton();
        JL_Tiempo = new javax.swing.JLabel();
        JP_Voz = new javax.swing.JPanel();
        JB_ReproducirVoz = new javax.swing.JButton();
        JB_GuardarVoz = new javax.swing.JButton();
        JP_Ruido = new javax.swing.JPanel();
        JB_ReproducirRuido = new javax.swing.JButton();
        JB_GuardarRuido = new javax.swing.JButton();
        JP_ConfiguracionMicrofono = new javax.swing.JPanel();
        JL_Servidor = new javax.swing.JLabel();
        JL_NombreServidor = new javax.swing.JLabel();
        JRB_ServidorEncendido = new javax.swing.JRadioButton();
        JRB_ServidorApagado = new javax.swing.JRadioButton();
        JL_Cliente = new javax.swing.JLabel();
        JL_NombreCliente = new javax.swing.JLabel();
        JL_IpServidor = new javax.swing.JLabel();
        JTF_IpServidor = new javax.swing.JTextField();
        JB_EncenderCliente = new javax.swing.JButton();
        JB_ApagarCliente = new javax.swing.JButton();
        JB_BuscarDispositivos = new javax.swing.JButton();
        JSP_ListaDispositivos = new javax.swing.JScrollPane();
        JL_ListaDispositivos = new javax.swing.JList<>();
        JL_ConectadoCon = new javax.swing.JLabel();
        JL_OtroUsuario = new javax.swing.JLabel();
        JB_Conectar = new javax.swing.JButton();
        JB_Desconectar = new javax.swing.JButton();
        JB_MenuPrincipal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Nuevo Audio");

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JP_Formato.setBackground(new java.awt.Color(255, 255, 255));
        JP_Formato.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "CONFIGURACION DEL FORMATO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JL_Frecuencia.setText("Frecuencia de Muestreo:");

        JCB_Frecuencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8000", "11025", "16000", "22050", "44100" }));
        JCB_Frecuencia.setSelectedIndex(2);

        JL_Bits.setText("Nro. Bits por Muestra:");

        JL_Canales.setText("Nro. Canales:");

        JL_Tipo.setText("Tipo de Codificacion:");

        JCB_Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Big Endian", "Little Endian" }));
        JCB_Tipo.setSelectedIndex(1);

        JTF_Bits.setEditable(false);
        JTF_Bits.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTF_Bits.setText("16");

        JTF_Canales.setEditable(false);
        JTF_Canales.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        JTF_Canales.setText("1");

        javax.swing.GroupLayout JP_FormatoLayout = new javax.swing.GroupLayout(JP_Formato);
        JP_Formato.setLayout(JP_FormatoLayout);
        JP_FormatoLayout.setHorizontalGroup(
            JP_FormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FormatoLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(JP_FormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JL_Frecuencia)
                    .addComponent(JL_Tipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JP_FormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JCB_Tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JCB_Frecuencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(JP_FormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_FormatoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JL_Canales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Canales, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JP_FormatoLayout.createSequentialGroup()
                        .addComponent(JL_Bits)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Bits)))
                .addGap(34, 34, 34))
        );
        JP_FormatoLayout.setVerticalGroup(
            JP_FormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FormatoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_FormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_FormatoLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(JP_FormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JL_Canales, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTF_Canales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JP_FormatoLayout.createSequentialGroup()
                        .addGroup(JP_FormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JL_Frecuencia)
                            .addComponent(JCB_Frecuencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JP_FormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JCB_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTF_Bits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JL_Bits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JP_Grabacion.setBackground(new java.awt.Color(255, 255, 255));
        JP_Grabacion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "CONTROLES DE GRABACION", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JB_EmpezarAudio.setText("Empezar");
        JB_EmpezarAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_EmpezarAudioActionPerformed(evt);
            }
        });

        JB_PararAudio.setText("Parar");
        JB_PararAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_PararAudioActionPerformed(evt);
            }
        });

        JL_Tiempo.setBackground(new java.awt.Color(255, 255, 255));
        JL_Tiempo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JL_Tiempo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        JL_Tiempo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout JP_GrabacionLayout = new javax.swing.GroupLayout(JP_Grabacion);
        JP_Grabacion.setLayout(JP_GrabacionLayout);
        JP_GrabacionLayout.setHorizontalGroup(
            JP_GrabacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_GrabacionLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(JB_EmpezarAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JL_Tiempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(JB_PararAudio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        JP_GrabacionLayout.setVerticalGroup(
            JP_GrabacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_GrabacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_GrabacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_GrabacionLayout.createSequentialGroup()
                        .addGroup(JP_GrabacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JB_EmpezarAudio)
                            .addComponent(JB_PararAudio))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JL_Tiempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        JP_Voz.setBackground(new java.awt.Color(255, 255, 255));
        JP_Voz.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "AUDIO DE VOZ CON RUIDO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JB_ReproducirVoz.setText("Reproducir");
        JB_ReproducirVoz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_ReproducirVozActionPerformed(evt);
            }
        });

        JB_GuardarVoz.setText("Guardar");
        JB_GuardarVoz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_GuardarVozActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_VozLayout = new javax.swing.GroupLayout(JP_Voz);
        JP_Voz.setLayout(JP_VozLayout);
        JP_VozLayout.setHorizontalGroup(
            JP_VozLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_VozLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(JB_ReproducirVoz, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(JB_GuardarVoz, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JP_VozLayout.setVerticalGroup(
            JP_VozLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_VozLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_VozLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_ReproducirVoz)
                    .addComponent(JB_GuardarVoz))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JP_Ruido.setBackground(new java.awt.Color(255, 255, 255));
        JP_Ruido.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "AUDIO DEL RUIDO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JB_ReproducirRuido.setText("Reproducir");
        JB_ReproducirRuido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_ReproducirRuidoActionPerformed(evt);
            }
        });

        JB_GuardarRuido.setText("Guardar");
        JB_GuardarRuido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_GuardarRuidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_RuidoLayout = new javax.swing.GroupLayout(JP_Ruido);
        JP_Ruido.setLayout(JP_RuidoLayout);
        JP_RuidoLayout.setHorizontalGroup(
            JP_RuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_RuidoLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(JB_ReproducirRuido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(JB_GuardarRuido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );
        JP_RuidoLayout.setVerticalGroup(
            JP_RuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_RuidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_RuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_ReproducirRuido)
                    .addComponent(JB_GuardarRuido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JP_ConfiguracionMicrofono.setBackground(new java.awt.Color(255, 255, 255));
        JP_ConfiguracionMicrofono.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "CONFIGURACION DEL MICROFONO EXTERNO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        JL_Servidor.setText("Servidor:");

        JL_NombreServidor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JL_NombreServidor.setText("...");

        JRB_ServidorEncendido.setBackground(new java.awt.Color(255, 255, 255));
        JBG_Servidor.add(JRB_ServidorEncendido);
        JRB_ServidorEncendido.setText("Encendido");
        JRB_ServidorEncendido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_ServidorEncendidoActionPerformed(evt);
            }
        });

        JRB_ServidorApagado.setBackground(new java.awt.Color(255, 255, 255));
        JBG_Servidor.add(JRB_ServidorApagado);
        JRB_ServidorApagado.setSelected(true);
        JRB_ServidorApagado.setText("Apagado");
        JRB_ServidorApagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_ServidorApagadoActionPerformed(evt);
            }
        });

        JL_Cliente.setText("Cliente:");

        JL_NombreCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JL_NombreCliente.setText("...");

        JL_IpServidor.setText("IP Servidor:");

        JB_EncenderCliente.setText("Encender");
        JB_EncenderCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_EncenderClienteActionPerformed(evt);
            }
        });

        JB_ApagarCliente.setText("Apagar");
        JB_ApagarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_ApagarClienteActionPerformed(evt);
            }
        });

        JB_BuscarDispositivos.setText("Buscar");
        JB_BuscarDispositivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_BuscarDispositivosActionPerformed(evt);
            }
        });

        JSP_ListaDispositivos.setViewportView(JL_ListaDispositivos);

        JL_ConectadoCon.setText("Conectado con:");

        JL_OtroUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JL_OtroUsuario.setText("...");

        JB_Conectar.setText("Conectar");
        JB_Conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_ConectarActionPerformed(evt);
            }
        });

        JB_Desconectar.setText("Desconectar");
        JB_Desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_DesconectarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_ConfiguracionMicrofonoLayout = new javax.swing.GroupLayout(JP_ConfiguracionMicrofono);
        JP_ConfiguracionMicrofono.setLayout(JP_ConfiguracionMicrofonoLayout);
        JP_ConfiguracionMicrofonoLayout.setHorizontalGroup(
            JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JSP_ListaDispositivos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                        .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                .addComponent(JL_Servidor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JL_NombreServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                .addComponent(JL_Cliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JL_NombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(JRB_ServidorEncendido)
                                .addGap(18, 18, 18)
                                .addComponent(JRB_ServidorApagado, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                        .addComponent(JL_IpServidor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JTF_IpServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                        .addComponent(JB_EncenderCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(JB_ApagarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                        .addComponent(JL_ConectadoCon)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(JL_OtroUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addComponent(JB_BuscarDispositivos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                                        .addComponent(JB_Conectar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(JB_Desconectar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JP_ConfiguracionMicrofonoLayout.setVerticalGroup(
            JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_ConfiguracionMicrofonoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_Servidor)
                    .addComponent(JL_NombreServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JRB_ServidorEncendido)
                    .addComponent(JRB_ServidorApagado))
                .addGap(15, 15, 15)
                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Cliente)
                    .addComponent(JL_NombreCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_IpServidor)
                    .addComponent(JTF_IpServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_EncenderCliente)
                    .addComponent(JB_ApagarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JB_BuscarDispositivos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JSP_ListaDispositivos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JL_ConectadoCon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JL_OtroUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(JP_ConfiguracionMicrofonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_Conectar)
                    .addComponent(JB_Desconectar))
                .addContainerGap(14, Short.MAX_VALUE))
        );

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
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JP_Formato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JP_Grabacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JP_Voz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JP_Ruido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(JB_MenuPrincipal)))
                .addGap(18, 18, 18)
                .addComponent(JP_ConfiguracionMicrofono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addComponent(JP_Formato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JP_Grabacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JP_Voz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(JP_Ruido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(JB_MenuPrincipal))
                    .addComponent(JP_ConfiguracionMicrofono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void empezarAnimacionParaGrabacion() {
        JL_Tiempo.setFont(new Font("Tahoma", 1, 12));
        JL_Tiempo.setForeground(new Color(204, 0, 0));
        JL_Tiempo.setText("");
        segundos = 0;
        timer = new Timer(1000, (ActionEvent ae) -> {
            segundos++;
            if (segundos == 4) {
                JL_Tiempo.setForeground(new Color(51, 204, 0));
                JL_Tiempo.setText("HABLE!");
                timer.stop();
                empezarAudio();
            } else {
                JL_Tiempo.setText("00:0"+segundos);
            }
        });
        timer.start();
    }
    
    private void empezarAudio() {
        float fs = Float.parseFloat(JCB_Frecuencia.getSelectedItem().toString());
        int nBits = Integer.parseInt(JTF_Bits.getText());
        int nCanales = Integer.parseInt(JTF_Canales.getText());
        boolean tipo = JCB_Tipo.getSelectedItem().toString().equals("Big Endian");
        audioVoz = new Audio();
        audioVoz.setFormato(fs, nBits, nCanales, true, tipo);
        if (tipoGrabacion == 1) {
            String formato = fs+"_"+nBits+"_"+nCanales+"_"+tipo;
            cliente.enviarInformacion("empezar@"+cliente.getOtroUsuario()+"@"+formato);
        }
        //Devuelve el controlador del microfono
        targetDataLine = audioVoz.grabarAudio();
        if (targetDataLine != null) {
            //Crea un hilo que capturara los datos del microfono
            threadGrabar = new ThreadGrabar(targetDataLine);
            threadGrabar.start();
        }
        JB_EmpezarAudio.setEnabled(false);
        JB_PararAudio.setEnabled(true);
        JB_ReproducirVoz.setEnabled(false);
        JB_GuardarVoz.setEnabled(false);
        JB_Desconectar.setEnabled(false);
        JB_ReproducirRuido.setEnabled(false);
        JB_GuardarRuido.setEnabled(false);
    }
    
    private void JB_EmpezarAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_EmpezarAudioActionPerformed
        empezarAnimacionParaGrabacion();
    }//GEN-LAST:event_JB_EmpezarAudioActionPerformed

    private void JB_PararAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_PararAudioActionPerformed
        if (targetDataLine != null && threadGrabar != null) {
            JL_Tiempo.setText("");
            if (tipoGrabacion == 1) {
                cliente.enviarInformacion("parar@"+cliente.getOtroUsuario());
            }
            //Parar el hilo que esta capturando los datos del microfono
            threadGrabar.setStopGrabar(true);
            //Guardar los datos en bytes del ByteArrayOutputStream
            audioVoz.setDatos(new Datos(threadGrabar.getByteArrayOutputStream().toByteArray(),
                                        audioVoz.getFormato().isBigEndian()));
            threadGrabar = null;
            JB_EmpezarAudio.setEnabled(true);
            JB_PararAudio.setEnabled(false);
            JB_ReproducirVoz.setEnabled(true);
            JB_GuardarVoz.setEnabled(true);
            if (tipoGrabacion == 1) {
                JB_Desconectar.setEnabled(true);
            }
        }
    }//GEN-LAST:event_JB_PararAudioActionPerformed

    private void JB_ReproducirVozActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_ReproducirVozActionPerformed
        if (audioVoz != null && audioVoz.getDatos() != null && 
            audioVoz.getDatos().getBits() != null) {
            audioVoz.reproducirAudio();
        }
    }//GEN-LAST:event_JB_ReproducirVozActionPerformed

    private void JB_GuardarVozActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_GuardarVozActionPerformed
        if (audioVoz != null && audioVoz.getDatos() != null &&
            audioVoz.getDatos().getBits() != null) {
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String nombreArchivo = fileChooser.getSelectedFile().getAbsolutePath();
                if (!nombreArchivo.endsWith(".wav")) {
                    nombreArchivo += ".wav";
                }
                audioVoz.guardarAudio(nombreArchivo);
            }
        }
    }//GEN-LAST:event_JB_GuardarVozActionPerformed

    private void JB_ReproducirRuidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_ReproducirRuidoActionPerformed
        if (audioRuido != null && audioRuido.getDatos() != null &&
            audioRuido.getDatos().getBits() != null) {
            audioRuido.reproducirAudio();
        }
    }//GEN-LAST:event_JB_ReproducirRuidoActionPerformed

    private void JB_GuardarRuidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_GuardarRuidoActionPerformed
        if (audioRuido != null && audioRuido.getDatos() != null &&
            audioRuido.getDatos().getBits() != null) {
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String nombreArchivo = fileChooser.getSelectedFile().getAbsolutePath();
                if (!nombreArchivo.endsWith(".wav")) {
                    nombreArchivo += ".wav";
                }
                audioRuido.guardarAudio(nombreArchivo);
            }
        }
    }//GEN-LAST:event_JB_GuardarRuidoActionPerformed

    private void JB_MenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_MenuPrincipalActionPerformed
        switch (tipoGrabacion) {
            case 1:
                if (JRB_ServidorApagado.isSelected()) {
                    guiAnalisis.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Apague el servidor!");
                }
                break;
            case 2:
                if (cliente == null) {
                    guiAnalisis.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Apague el cliente!");
                }
                break;
            default:
                guiAnalisis.setVisible(true);
                this.dispose();
                break;
        }
    }//GEN-LAST:event_JB_MenuPrincipalActionPerformed

    private void JRB_ServidorEncendidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_ServidorEncendidoActionPerformed
        if (servidor == null) {
            servidor = new Servidor();
            servidor.encender(100);
            JB_EncenderCliente.setEnabled(true);
            JTF_IpServidor.setText(JL_NombreServidor.getText().split("/")[1]);
        }
    }//GEN-LAST:event_JRB_ServidorEncendidoActionPerformed

    private void JRB_ServidorApagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_ServidorApagadoActionPerformed
        if (servidor != null) {
            servidor.apagar();
            servidor = null;
            JB_EncenderCliente.setEnabled(false);
            JTF_IpServidor.setText("");
        }
    }//GEN-LAST:event_JRB_ServidorApagadoActionPerformed

    private void JB_EncenderClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_EncenderClienteActionPerformed
        String ip = JTF_IpServidor.getText();
        if (ip != null && !ip.isEmpty()) {
            String usuario = JOptionPane.showInputDialog("Nombre del Cliente: ", 
                             JL_NombreCliente.getText().split("/")[0]);
            if (usuario != null && !usuario.isEmpty()) {
                cliente = new Cliente(usuario, this);
                if (cliente.inicializarConexion(ip)) {
                    cliente.start();
                } else {
                    cliente = null;
                    String msj = "No se pudo crear el cliente!";
                    JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String msj = "Ingrese nombre del cliente!";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            String msj = "Ingrese IP Servidor!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_EncenderClienteActionPerformed

    private void JB_ApagarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_ApagarClienteActionPerformed
        if (cliente != null) {
            cliente.enviarInformacion("apagar");
            cliente = null;
            if (tipoGrabacion == 1) {
                JRB_ServidorEncendido.setEnabled(true);
                JRB_ServidorApagado.setEnabled(true);
                JB_BuscarDispositivos.setEnabled(false);
                JL_ListaDispositivos.setListData(new String[]{});
                JL_ListaDispositivos.setEnabled(false);
                JB_Conectar.setEnabled(false);
            } else if (tipoGrabacion == 2) {
                JTF_IpServidor.setEnabled(true);
            }
            JB_EncenderCliente.setEnabled(true);
            JB_ApagarCliente.setEnabled(false);
        }
    }//GEN-LAST:event_JB_ApagarClienteActionPerformed

    private void JB_BuscarDispositivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_BuscarDispositivosActionPerformed
        cliente.enviarInformacion("getVisibles");
    }//GEN-LAST:event_JB_BuscarDispositivosActionPerformed

    private void JB_ConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_ConectarActionPerformed
        String user = JL_ListaDispositivos.getSelectedValue();
        if (user != null) {
            cliente.enviarInformacion("online@"+user);
        }
    }//GEN-LAST:event_JB_ConectarActionPerformed

    private void JB_DesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_DesconectarActionPerformed
        cliente.enviarInformacion("salir@"+cliente.getOtroUsuario()+"@"+cliente.getUsuario());
        cliente.enviarInformacion("activo@" + true);
        cliente.setOtroUsuario(null);
        JB_ApagarCliente.setEnabled(true);
        JB_BuscarDispositivos.setEnabled(true);
        JL_ListaDispositivos.setListData(new String[]{});
        JL_ListaDispositivos.setEnabled(true);
        JB_Conectar.setEnabled(false);
        JB_Desconectar.setEnabled(false);
        JL_OtroUsuario.setText("...");
        JB_EmpezarAudio.setEnabled(false);
        JB_ReproducirVoz.setEnabled(false);
        JB_GuardarVoz.setEnabled(false);
        JB_ReproducirRuido.setEnabled(false);
        JB_GuardarRuido.setEnabled(false);
    }//GEN-LAST:event_JB_DesconectarActionPerformed

    public void apagoServidor() {
        JOptionPane.showMessageDialog(this, "El servidor ah sido apagado!");
        cliente = null;
        JB_EncenderCliente.setEnabled(true);
        JB_ApagarCliente.setEnabled(false);
    }
    
    public void malIngreso() {
        String msj = "El usuario, ya existe!";
        JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        cliente = null;
        if (tipoGrabacion == 2){
            JTF_IpServidor.setEnabled(true);
        }
        JB_EncenderCliente.setEnabled(true);
        JB_ApagarCliente.setEnabled(false);
    }
    
    public void buenIngreso() {
        String[] nomCliente = JL_NombreCliente.getText().split("/");
        JL_NombreCliente.setText(cliente.getUsuario()+"/"+nomCliente[1]);
        if (tipoGrabacion == 1) {
            JRB_ServidorEncendido.setEnabled(false);
            JRB_ServidorApagado.setEnabled(false);
            JB_BuscarDispositivos.setEnabled(true);
            JL_ListaDispositivos.setEnabled(true);
        } else if (tipoGrabacion == 2) {
            JTF_IpServidor.setEnabled(false);
        }
        JB_EncenderCliente.setEnabled(false);
        JB_ApagarCliente.setEnabled(true);
    }
    
    public void cargarListaUsuarios(String[] clientes) {
        if (clientes != null) {
            JL_ListaDispositivos.setListData(clientes);
            JB_Conectar.setEnabled(true);
        } else {
            JL_ListaDispositivos.setListData(new String[]{});
            JB_Conectar.setEnabled(false);
        }
    }
    
    public void solicitud(String otroUsuario) {
        if (JOptionPane.showConfirmDialog(this, otroUsuario+" te invito!") == JOptionPane.OK_OPTION) {
            cliente.setOtroUsuario(otroUsuario);
            cliente.enviarInformacion("activo@"+false);
            cliente.enviarInformacion("aceptar@"+cliente.getOtroUsuario()+"@"+cliente.getUsuario());
            JB_ApagarCliente.setEnabled(false);
            JL_OtroUsuario.setText(otroUsuario);
        } else {
            cliente.setOtroUsuario(null);
            cliente.enviarInformacion("rechazar@"+otroUsuario+"@"+cliente.getUsuario());
            JB_ApagarCliente.setEnabled(true);
            JL_OtroUsuario.setText("...");
        }
    }
    
    public void aceptado(String otroUsuario) {
        JOptionPane.showMessageDialog(this, otroUsuario+" acepto tu solicitud!");
        cliente.enviarInformacion("activo@"+false);
        JB_ApagarCliente.setEnabled(false);
        JB_BuscarDispositivos.setEnabled(false);
        JL_ListaDispositivos.setEnabled(false);
        JB_Conectar.setEnabled(false);
        JB_Desconectar.setEnabled(true);
        JL_OtroUsuario.setText(otroUsuario);
        JB_EmpezarAudio.setEnabled(true);
    }
    
    public void rechazado(String otroUsuario) {
        JOptionPane.showMessageDialog(this, otroUsuario+" rechazo tu solicitud!");
        JB_ApagarCliente.setEnabled(true);
        JB_BuscarDispositivos.setEnabled(true);
        JL_ListaDispositivos.setEnabled(true);
        JB_Conectar.setEnabled(true);
        JB_Desconectar.setEnabled(false);
        JL_OtroUsuario.setText("...");
        JB_EmpezarAudio.setEnabled(false);
    }
    
    public void salio(String otroUsuario) {
        JOptionPane.showMessageDialog(this, otroUsuario+" se salio!");
        cliente.enviarInformacion("activo@"+true);
        JB_ApagarCliente.setEnabled(true);
        JL_OtroUsuario.setText("...");
    }
    
    public void empezarGrabar(String[] formato) {
        float fs = Float.parseFloat(formato[0]+"f");
        int nBits = Integer.parseInt(formato[1]);
        int nCanales = Integer.parseInt(formato[2]);
        boolean tipo = Boolean.parseBoolean(formato[3]);
        audioRuido = new Audio();
        audioRuido.setFormato(fs, nBits, nCanales, true, tipo);
        JCB_Frecuencia.setSelectedItem(formato[0].split("\\.")[0]);
        if (tipo) {
            JCB_Tipo.setSelectedItem("Big Endian");
        } else {
            JCB_Tipo.setSelectedItem("Little Endian");
        }
        //Devuelve el controlador del microfono
        targetDataLine = audioRuido.grabarAudio();
        if (targetDataLine != null) {
            //Crea un hilo que capturara los datos del microfono
            threadGrabar = new ThreadGrabar(targetDataLine);
            threadGrabar.start();
        }
    }
    
    public void pararGrabar() {
        if (targetDataLine != null && threadGrabar != null) {
            //Parar el hilo que esta capturando los datos del microfono
            threadGrabar.setStopGrabar(true);
            //Guardar los datos en bytes del ByteArrayOutputStream
            audioRuido.setDatos(new Datos(threadGrabar.getByteArrayOutputStream().toByteArray(),
                                          audioRuido.getFormato().isBigEndian()));
            threadGrabar = null;
        }
        if (audioRuido != null && audioRuido.getDatos() != null &&
            audioRuido.getDatos().getBits() != null) {
            File dir = new File("C:\\xampp\\htdocs\\audios");
            dir.mkdir();
            audioRuido.guardarAudio("C:\\xampp\\htdocs\\audios\\ruido.wav");
            String ipServidor = JL_NombreCliente.getText().split("/")[1];
            String URL = "http://"+ipServidor+"/audios/ruido.wav";
            cliente.enviarInformacion("audio@"+cliente.getOtroUsuario()+"@"+URL);
        } else {
            cliente.enviarInformacion("audio@"+cliente.getOtroUsuario()+"@"+"error");
        }
    }
    
    public void audioRuido(String urlAudio) {
        if (urlAudio.equals("error")) {
            audioRuido = null;
            JB_ReproducirRuido.setEnabled(false);
            JB_GuardarRuido.setEnabled(false);
            String msj = "No se pudo grabar ruido!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            File archivoRuido = crearArchivo(urlAudio);
            if (archivoRuido != null) {
                Audio audio = new Audio();
                audio.abrirAudio(archivoRuido);
                audioRuido = new Audio();
                audioRuido.setFormato(audio.getFormato().getSampleRate(), 
                                      audio.getFormato().getSampleSizeInBits(), 
                                      audio.getFormato().getChannels(), true, 
                                      audio.getFormato().isBigEndian());
                audioRuido.setDatos(new Datos(audio.getDatos().getBits(), 
                                              audioRuido.getFormato().isBigEndian()));
                JB_ReproducirRuido.setEnabled(true);
                JB_GuardarRuido.setEnabled(true);
            } else {
                audioRuido = null;
                JB_ReproducirRuido.setEnabled(false);
                JB_GuardarRuido.setEnabled(false);
            }
        }
    }
    
    private File crearArchivo(String urlAudio) {
        File archivo = null;
        try {
            URL url = new URL(urlAudio);
            try {
                URLConnection connection = url.openConnection();
                if (((HttpURLConnection) connection).getResponseCode() == 200) {
                    InputStream in = connection.getInputStream();
                    archivo = new File("ruido.wav");
                    FileOutputStream fos = new FileOutputStream(archivo);
                    byte[] buf = new byte[1024];
                    int len;
		    while ((len = in.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    in.close();
                    fos.flush();
                    fos.close();
                } else {
                    String msj = "No se encuentra el audio del ruido!";
                    JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                String msj = "No se pude abrir la conexion!";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (MalformedURLException ex) {
            String msj = "No se pudo construir la URL!";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return archivo;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup JBG_Servidor;
    private javax.swing.JButton JB_ApagarCliente;
    private javax.swing.JButton JB_BuscarDispositivos;
    private javax.swing.JButton JB_Conectar;
    private javax.swing.JButton JB_Desconectar;
    private javax.swing.JButton JB_EmpezarAudio;
    private javax.swing.JButton JB_EncenderCliente;
    private javax.swing.JButton JB_GuardarRuido;
    private javax.swing.JButton JB_GuardarVoz;
    private javax.swing.JButton JB_MenuPrincipal;
    private javax.swing.JButton JB_PararAudio;
    private javax.swing.JButton JB_ReproducirRuido;
    private javax.swing.JButton JB_ReproducirVoz;
    private javax.swing.JComboBox<String> JCB_Frecuencia;
    private javax.swing.JComboBox<String> JCB_Tipo;
    private javax.swing.JLabel JL_Bits;
    private javax.swing.JLabel JL_Canales;
    private javax.swing.JLabel JL_Cliente;
    private javax.swing.JLabel JL_ConectadoCon;
    private javax.swing.JLabel JL_Frecuencia;
    private javax.swing.JLabel JL_IpServidor;
    private javax.swing.JList<String> JL_ListaDispositivos;
    private javax.swing.JLabel JL_NombreCliente;
    private javax.swing.JLabel JL_NombreServidor;
    private javax.swing.JLabel JL_OtroUsuario;
    private javax.swing.JLabel JL_Servidor;
    private javax.swing.JLabel JL_Tiempo;
    private javax.swing.JLabel JL_Tipo;
    private javax.swing.JPanel JP_ConfiguracionMicrofono;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JPanel JP_Formato;
    private javax.swing.JPanel JP_Grabacion;
    private javax.swing.JPanel JP_Ruido;
    private javax.swing.JPanel JP_Voz;
    private javax.swing.JRadioButton JRB_ServidorApagado;
    private javax.swing.JRadioButton JRB_ServidorEncendido;
    private javax.swing.JScrollPane JSP_ListaDispositivos;
    private javax.swing.JTextField JTF_Bits;
    private javax.swing.JTextField JTF_Canales;
    private javax.swing.JTextField JTF_IpServidor;
    // End of variables declaration//GEN-END:variables
}
