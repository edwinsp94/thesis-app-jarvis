package gui;

import javax.swing.JOptionPane;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class GUIEliminarRuido extends javax.swing.JDialog {

    private final GUIAnalisis gui_analisis;
    
    public GUIEliminarRuido(GUIAnalisis parent, boolean modal) {
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
        JRB_LMS = new javax.swing.JRadioButton();
        JRB_NLMS = new javax.swing.JRadioButton();
        JRB_Ninguno = new javax.swing.JRadioButton();
        JL_Coeficientes = new javax.swing.JLabel();
        JTF_Coeficientes = new javax.swing.JTextField();
        JL_Valor = new javax.swing.JLabel();
        JTF_ValorAdaptacion = new javax.swing.JTextField();
        JB_Aceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Eliminar Ruido");

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JL_Tipo.setText("Tipo de Algoritmo:");

        JRB_LMS.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_LMS);
        JRB_LMS.setText("LMS");

        JRB_NLMS.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_NLMS);
        JRB_NLMS.setText("NLMS");

        JRB_Ninguno.setBackground(new java.awt.Color(255, 255, 255));
        JBG_TipoAlgoritmo.add(JRB_Ninguno);
        JRB_Ninguno.setSelected(true);
        JRB_Ninguno.setText("Ninguno");

        JL_Coeficientes.setText("Nro. Coeficientes:");

        JL_Valor.setText("Paso de Adaptacion:");

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
                        .addGap(85, 85, 85)
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JP_FondoLayout.createSequentialGroup()
                                .addComponent(JL_Valor)
                                .addGap(5, 5, 5))
                            .addGroup(JP_FondoLayout.createSequentialGroup()
                                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JL_Coeficientes)
                                    .addGroup(JP_FondoLayout.createSequentialGroup()
                                        .addComponent(JL_Tipo)
                                        .addGap(22, 22, 22)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JTF_Coeficientes)
                            .addComponent(JTF_ValorAdaptacion, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JRB_NLMS)
                            .addComponent(JRB_LMS)
                            .addComponent(JRB_Ninguno))))
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_FondoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JB_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JP_FondoLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(JL_Tipo)
                .addGap(18, 18, 18)
                .addComponent(JRB_LMS)
                .addGap(7, 7, 7)
                .addComponent(JRB_NLMS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JRB_Ninguno)
                .addGap(29, 29, 29)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Coeficientes)
                    .addComponent(JTF_Coeficientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Valor)
                    .addComponent(JTF_ValorAdaptacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(JB_Aceptar)
                .addGap(25, 25, 25))
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
                Integer.parseInt(JTF_Coeficientes.getText());
                try {
                    Double.parseDouble(JTF_ValorAdaptacion.getText());
                    gui_analisis.respuestaGUI = new String[3];
                    if (JRB_LMS.isSelected()) {
                        gui_analisis.respuestaGUI[0] = "LMS";
                    } else if (JRB_NLMS.isSelected()) {
                        gui_analisis.respuestaGUI[0] = "NLMS";
                    }
                    gui_analisis.respuestaGUI[1] = JTF_Coeficientes.getText();
                    gui_analisis.respuestaGUI[2] = JTF_ValorAdaptacion.getText();
                    gui_analisis.setVisible(true);
                    this.dispose();
                } catch(NumberFormatException ex) {
                    String msj = "El paso de adaptacion debe ser un numero real";
                    JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException ex) {
                String msj = "El nro de coeficientes debe ser un numero entero";
                JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JB_AceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup JBG_TipoAlgoritmo;
    private javax.swing.JButton JB_Aceptar;
    private javax.swing.JLabel JL_Coeficientes;
    private javax.swing.JLabel JL_Tipo;
    private javax.swing.JLabel JL_Valor;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JRadioButton JRB_LMS;
    private javax.swing.JRadioButton JRB_NLMS;
    private javax.swing.JRadioButton JRB_Ninguno;
    private javax.swing.JTextField JTF_Coeficientes;
    private javax.swing.JTextField JTF_ValorAdaptacion;
    // End of variables declaration//GEN-END:variables
}
