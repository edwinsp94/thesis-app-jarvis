package gui;

import javax.swing.JOptionPane;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class GUIEliminarSegmentos extends javax.swing.JDialog {
    
    private final GUIAnalisis gui_analisis;
    
    public GUIEliminarSegmentos(GUIAnalisis parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.gui_analisis = parent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBG_TipoAlgoritmo = new javax.swing.ButtonGroup();
        JP_Fondo = new javax.swing.JPanel();
        JL_TipoAlgoritmo = new javax.swing.JLabel();
        JRB_Energia = new javax.swing.JRadioButton();
        JRB_Rabiner1 = new javax.swing.JRadioButton();
        JRB_Rabiner2 = new javax.swing.JRadioButton();
        JRB_Ninguno = new javax.swing.JRadioButton();
        JL_TamVentana = new javax.swing.JLabel();
        JTF_TamVentana = new javax.swing.JTextField();
        JL_Umbral = new javax.swing.JLabel();
        JTF_Umbral = new javax.swing.JTextField();
        JB_Aceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Eliminar Segmentos Inutiles");

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JL_TipoAlgoritmo.setText("Tipo de Algoritmo:");

        JRB_Energia.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_Energia);
        JRB_Energia.setText("Energia");
        JRB_Energia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_EnergiaActionPerformed(evt);
            }
        });

        JRB_Rabiner1.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_Rabiner1);
        JRB_Rabiner1.setText("Rabiner & Sambur 1");
        JRB_Rabiner1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_Rabiner1ActionPerformed(evt);
            }
        });

        JRB_Rabiner2.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_Rabiner2);
        JRB_Rabiner2.setText("Rabiner & Sambur 2");
        JRB_Rabiner2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JRB_Rabiner2ActionPerformed(evt);
            }
        });

        JRB_Ninguno.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_Ninguno);
        JRB_Ninguno.setSelected(true);
        JRB_Ninguno.setText("Ninguno");

        JL_TamVentana.setText("Tamaño de Ventana: ");

        JTF_TamVentana.setToolTipText("");

        JL_Umbral.setText("Umbral:");

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
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_FondoLayout.createSequentialGroup()
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JL_TipoAlgoritmo)
                            .addGroup(JP_FondoLayout.createSequentialGroup()
                                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JL_TamVentana)
                                    .addComponent(JL_Umbral))
                                .addGap(18, 18, 18)
                                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JTF_TamVentana)
                                    .addComponent(JTF_Umbral, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JP_FondoLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JRB_Rabiner1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JRB_Rabiner2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JRB_Ninguno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JRB_Energia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_FondoLayout.createSequentialGroup()
                        .addComponent(JB_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))))
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(JL_TipoAlgoritmo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JRB_Energia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JRB_Rabiner1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JRB_Rabiner2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JRB_Ninguno)
                .addGap(15, 15, 15)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_TamVentana)
                    .addComponent(JTF_TamVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Umbral)
                    .addComponent(JTF_Umbral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(JB_Aceptar)
                .addContainerGap(34, Short.MAX_VALUE))
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
        if (JRB_Ninguno.isSelected()) {
            gui_analisis.respuestaGUI = null;
            gui_analisis.setVisible(true);
            this.dispose();
        } else {
            try {
                Integer.parseInt(JTF_TamVentana.getText());
                try {
                    Double.parseDouble(JTF_Umbral.getText());
                    if (JRB_Energia.isSelected()) {
                        gui_analisis.respuestaGUI = new String[3];
                        gui_analisis.respuestaGUI[0] = "Energia";
                        gui_analisis.respuestaGUI[1] = JTF_TamVentana.getText();
                        gui_analisis.respuestaGUI[2] = JTF_Umbral.getText();
                    } else if (JRB_Rabiner1.isSelected()) {
                        gui_analisis.respuestaGUI = new String[2];
                        gui_analisis.respuestaGUI[0] = "Rabiner1";
                        gui_analisis.respuestaGUI[1] = JTF_TamVentana.getText();
                    } else if (JRB_Rabiner2.isSelected()) {
                        gui_analisis.respuestaGUI = new String[2];
                        gui_analisis.respuestaGUI[0] = "Rabiner2";
                        gui_analisis.respuestaGUI[1] = JTF_TamVentana.getText();
                    }
                    gui_analisis.setVisible(true);
                    this.dispose();
                } catch(NumberFormatException ex) {
                    String msj = "El umbral debe ser un numero real";
                    JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException ex) {
                String msj = "El tamaño ventana debe ser un numero entero";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_AceptarActionPerformed

    private void JRB_EnergiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_EnergiaActionPerformed
        JTF_Umbral.setText("");
        JTF_Umbral.setEnabled(true);
    }//GEN-LAST:event_JRB_EnergiaActionPerformed

    private void JRB_Rabiner1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_Rabiner1ActionPerformed
        JTF_Umbral.setText("0");
        JTF_Umbral.setEnabled(false);
    }//GEN-LAST:event_JRB_Rabiner1ActionPerformed

    private void JRB_Rabiner2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JRB_Rabiner2ActionPerformed
        JTF_Umbral.setText("0");
        JTF_Umbral.setEnabled(false);
    }//GEN-LAST:event_JRB_Rabiner2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup JBG_TipoAlgoritmo;
    private javax.swing.JButton JB_Aceptar;
    private javax.swing.JLabel JL_TamVentana;
    private javax.swing.JLabel JL_TipoAlgoritmo;
    private javax.swing.JLabel JL_Umbral;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JRadioButton JRB_Energia;
    private javax.swing.JRadioButton JRB_Ninguno;
    private javax.swing.JRadioButton JRB_Rabiner1;
    private javax.swing.JRadioButton JRB_Rabiner2;
    private javax.swing.JTextField JTF_TamVentana;
    private javax.swing.JTextField JTF_Umbral;
    // End of variables declaration//GEN-END:variables
}
