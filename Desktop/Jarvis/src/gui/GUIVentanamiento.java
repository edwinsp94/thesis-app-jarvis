package gui;

import javax.swing.JOptionPane;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class GUIVentanamiento extends javax.swing.JDialog {

    private final GUIAnalisis gui_analisis;
    
    public GUIVentanamiento(GUIAnalisis parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.gui_analisis = parent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBG_TipoAlgoritmo = new javax.swing.ButtonGroup();
        JP_Fondo = new javax.swing.JPanel();
        JL_Tipo = new javax.swing.JLabel();
        JRB_Rectangular = new javax.swing.JRadioButton();
        JRB_Hamming = new javax.swing.JRadioButton();
        JRB_Hanning = new javax.swing.JRadioButton();
        JL_Ventana = new javax.swing.JLabel();
        JTF_Ventana = new javax.swing.JTextField();
        JL_Solapamiento = new javax.swing.JLabel();
        JTF_Solapamiento = new javax.swing.JTextField();
        JB_Aceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ventanamiento de la Señal");

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JL_Tipo.setText("Tipo de Algoritmo:");

        JRB_Rectangular.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_Rectangular);
        JRB_Rectangular.setSelected(true);
        JRB_Rectangular.setText("Rectangular");

        JRB_Hamming.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_Hamming);
        JRB_Hamming.setText("Hamming");

        JRB_Hanning.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_Hanning);
        JRB_Hanning.setText("Hanning");

        JL_Ventana.setText("Tamaño de Ventana:");

        JL_Solapamiento.setText("Distancia de Solapamiento:");

        JB_Aceptar.setText("Aceptar");
        JB_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_AceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_FondoLayout = new javax.swing.GroupLayout(JP_Fondo);
        JP_Fondo.setLayout(JP_FondoLayout);
        JP_FondoLayout.setHorizontalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JL_Tipo)
                            .addGroup(JP_FondoLayout.createSequentialGroup()
                                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(JP_FondoLayout.createSequentialGroup()
                                        .addComponent(JL_Solapamiento)
                                        .addGap(5, 5, 5))
                                    .addGroup(JP_FondoLayout.createSequentialGroup()
                                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(JL_Ventana)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JP_FondoLayout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(JRB_Hanning)
                                                    .addComponent(JRB_Hamming)
                                                    .addComponent(JRB_Rectangular))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JTF_Ventana, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JTF_Solapamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(JB_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 104, Short.MAX_VALUE))
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_FondoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(JL_Tipo)
                .addGap(18, 18, 18)
                .addComponent(JRB_Rectangular)
                .addGap(4, 4, 4)
                .addComponent(JRB_Hamming)
                .addGap(7, 7, 7)
                .addComponent(JRB_Hanning)
                .addGap(25, 25, 25)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Ventana)
                    .addComponent(JTF_Ventana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Solapamiento)
                    .addComponent(JTF_Solapamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(JB_Aceptar)
                .addContainerGap(33, Short.MAX_VALUE))
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

    private void JB_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_AceptarActionPerformed
        try {
            Integer.parseInt(JTF_Ventana.getText());
            try {
                Integer.parseInt(JTF_Solapamiento.getText());
                gui_analisis.respuestaGUI = new String[3];
                if (JRB_Rectangular.isSelected()) {
                    gui_analisis.respuestaGUI[0] = "Rectangular";
                } else if (JRB_Hamming.isSelected()) {
                    gui_analisis.respuestaGUI[0] = "Hamming";
                } else {
                    gui_analisis.respuestaGUI[0] = "Hanning";
                }
                gui_analisis.respuestaGUI[1] = JTF_Ventana.getText();
                gui_analisis.respuestaGUI[2] = JTF_Solapamiento.getText();
                gui_analisis.setVisible(true);
                this.dispose();
            } catch(NumberFormatException ex) {
                String msj = "La distancia de solapamiento debe ser un numero entero";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch(NumberFormatException ex) {
            String msj = "El tamaño de ventana debe ser un numero entero";
            JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JB_AceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup JBG_TipoAlgoritmo;
    private javax.swing.JButton JB_Aceptar;
    private javax.swing.JLabel JL_Solapamiento;
    private javax.swing.JLabel JL_Tipo;
    private javax.swing.JLabel JL_Ventana;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JRadioButton JRB_Hamming;
    private javax.swing.JRadioButton JRB_Hanning;
    private javax.swing.JRadioButton JRB_Rectangular;
    private javax.swing.JTextField JTF_Solapamiento;
    private javax.swing.JTextField JTF_Ventana;
    // End of variables declaration//GEN-END:variables
}
