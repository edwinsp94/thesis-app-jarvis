package gui;

/**
 *
 * @author EDWIN JONATAN SANCHEZ PEDRO
 */

public class GUIDTW_Escoger extends javax.swing.JDialog {
    
    private final GUIAnalisis guiAnalisis;

    public GUIDTW_Escoger(GUIAnalisis parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.guiAnalisis = parent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JBG_Tipos = new javax.swing.ButtonGroup();
        JP_Fondo = new javax.swing.JPanel();
        JL_Tipo = new javax.swing.JLabel();
        JRB_Tipo1 = new javax.swing.JRadioButton();
        JRB_Tipo2 = new javax.swing.JRadioButton();
        JRB_Tipo3 = new javax.swing.JRadioButton();
        JB_Aceptar = new javax.swing.JButton();
        JB_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Escoger Analisis DTW");

        JP_Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JL_Tipo.setText("Escoga el Tipo de Analisis:");

        JRB_Tipo1.setBackground(new java.awt.Color(255, 255, 255));
        JBG_Tipos.add(JRB_Tipo1);
        JRB_Tipo1.setText("Uno vs. Uno");

        JRB_Tipo2.setBackground(new java.awt.Color(255, 255, 255));
        JBG_Tipos.add(JRB_Tipo2);
        JRB_Tipo2.setText("Uno vs. Varios");

        JRB_Tipo3.setBackground(new java.awt.Color(255, 255, 255));
        JBG_Tipos.add(JRB_Tipo3);
        JRB_Tipo3.setText("Varios vs. Varios");

        JB_Aceptar.setText("Aceptar");
        JB_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_AceptarActionPerformed(evt);
            }
        });

        JB_Cancelar.setText("Cancelar");
        JB_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_FondoLayout = new javax.swing.GroupLayout(JP_Fondo);
        JP_Fondo.setLayout(JP_FondoLayout);
        JP_FondoLayout.setHorizontalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JRB_Tipo3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JRB_Tipo2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JRB_Tipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JP_FondoLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JL_Tipo)
                            .addGroup(JP_FondoLayout.createSequentialGroup()
                                .addComponent(JB_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                .addComponent(JB_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(71, 71, 71))
        );
        JP_FondoLayout.setVerticalGroup(
            JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_FondoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(JL_Tipo)
                .addGap(27, 27, 27)
                .addComponent(JRB_Tipo1)
                .addGap(18, 18, 18)
                .addComponent(JRB_Tipo2)
                .addGap(18, 18, 18)
                .addComponent(JRB_Tipo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(JP_FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_Aceptar)
                    .addComponent(JB_Cancelar))
                .addGap(36, 36, 36))
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
        if (JRB_Tipo1.isSelected()) {
            this.dispose();
            guiAnalisis.setVisible(false);
            new GUIDTW_Uno_Uno(guiAnalisis).setVisible(true);
        } else if (JRB_Tipo2.isSelected()) {
            this.dispose();
            guiAnalisis.setVisible(false);
            new GUIDTW_Uno_Varios(guiAnalisis).setVisible(true);
        } else if (JRB_Tipo3.isSelected()) {
            this.dispose();
            guiAnalisis.setVisible(false);
            new GUIDTW_Varios_Varios(guiAnalisis).setVisible(true);
        }
    }//GEN-LAST:event_JB_AceptarActionPerformed

    private void JB_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CancelarActionPerformed
        guiAnalisis.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JB_CancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup JBG_Tipos;
    private javax.swing.JButton JB_Aceptar;
    private javax.swing.JButton JB_Cancelar;
    private javax.swing.JLabel JL_Tipo;
    private javax.swing.JPanel JP_Fondo;
    private javax.swing.JRadioButton JRB_Tipo1;
    private javax.swing.JRadioButton JRB_Tipo2;
    private javax.swing.JRadioButton JRB_Tipo3;
    // End of variables declaration//GEN-END:variables
}
