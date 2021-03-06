/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import civitas.CivitasJuego ;
import civitas.Diario;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
/**
 *
 * @author ivan
 */
public class CivitasView extends javax.swing.JFrame {
    
    private CivitasJuego juego ;
    private JugadorPanel jugadorPanel ;
    private CasillaPanel casillaPanel ;

    /**
     * Creates new form CivitasView
     */
    public CivitasView() {
        initComponents();
        jugadorPanel = new JugadorPanel() ;
        casillaPanel = new CasillaPanel() ;
        contenedorVistaJugador.add(jugadorPanel) ;
        contenedorCasilla.add(casillaPanel) ;
        
        repaint() ;
        revalidate() ;
    }
    
    public void pausa(){
        Jugar.setAction(new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                synchronized (Jugar) {
                    Jugar.notify();
                }
            }
        });
        
        this.getContentPane().add(Jugar, BorderLayout.CENTER);
        this.Jugar.setText("Jugar");
        
        synchronized(Jugar) {
        try {
          Jugar.wait();
        } catch (InterruptedException ex) {
          System.out.println("Interrupted");
        }
}
    }
    
    public void actualizarVista(){
        jugadorPanel.setJugador(juego.getJugadorActual());
        casillaPanel.setCasilla(juego.getCasillaActual());
        jugadorPanel.setVisible(true);
        casillaPanel.setVisible(true);
        labelRanking.setVisible(false);
        areaRanking.setVisible(false);
        Jugar.setVisible(true);
        if (juego.finalDelJuego()){
            areaRanking.setText(juego.ranking().toString()) ;
            labelRanking.setVisible(true);
            areaRanking.setVisible(true);
        }
        repaint() ;
        revalidate() ;
    }
    
    public void mostrarSiguienteOperacion(OperacionesJuego op){
        campoEstado.setText(op.toString()) ;
        campoEstado.setVisible(true);
        actualizarVista() ;
    }
    
    public void mostrarEventos(){
        DiarioDialog diarioD = new DiarioDialog(this) ;
    }
    
    public void setCivitasJuego(CivitasJuego partida){
        juego = partida ;
        setVisible(true) ;
    }
    
    Respuestas comprar(){
        int opcion = JOptionPane.showConfirmDialog(null,"¿Quieres comprar la calle"
                + " actual?","Compra",YES_NO_OPTION) ;
        System.out.println(opcion);
        return (Respuestas.values()[opcion]) ;
    }
    
    SalidasCarcel salirCarcel(){
        String[] opciones = {"Pagando","Tirando"} ;
        
        int respuesta = JOptionPane.showOptionDialog(null, "¿Como quieres salir de la cárcel?",
                "Salir de la cárcel", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, opciones,opciones[0]) ;
        
        return (SalidasCarcel.values()[respuesta]) ;
    }
    
    GestionDialog gestionarD= new GestionDialog(this) ;
    
    int getGestion(){
        return gestionarD.getGestion();
    }
    
    int getPropiedad(){
        return gestionarD.getPropiedad();
    }
    
    public void gestionar(){
        gestionarD.gestionar(juego.getJugadorActual());
        gestionarD.pack();
        gestionarD.repaint();
        gestionarD.revalidate();
        gestionarD.setVisible(true) ;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        contenedorCasilla = new javax.swing.JPanel();
        contenedorVistaJugador = new javax.swing.JPanel();
        campoEstado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaRanking = new javax.swing.JTextArea();
        labelRanking = new javax.swing.JLabel();
        Jugar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(151, 213, 239));

        titulo.setBackground(new java.awt.Color(0, 0, 255));
        titulo.setFont(new java.awt.Font("FreeSans", 0, 36)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 0, 255));
        titulo.setText("<html><font color=chartreuse> CivitasView </font></html>");
        titulo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(254, 254, 254), 2, true));
        titulo.setEnabled(false);

        campoEstado.setText("???");
        campoEstado.setEnabled(false);

        jLabel1.setText("<html><font color = black>Operación :</font></html>");
        jLabel1.setEnabled(false);

        areaRanking.setColumns(20);
        areaRanking.setRows(5);
        areaRanking.setBorder(null);
        areaRanking.setEnabled(false);
        jScrollPane1.setViewportView(areaRanking);

        labelRanking.setText("Ranking :");
        labelRanking.setEnabled(false);

        Jugar.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        Jugar.setForeground(new java.awt.Color(0, 102, 255));
        Jugar.setText("jButton1");

        jLabel2.setText("<html><font color = black> Pulse \"Jugar\" para continuar </font></html>");
        jLabel2.setEnabled(false);

        jLabel3.setText("<html><font color = black>Miguel Muñoz, Iván Valero | PDOO (Grupo C3) 2019 </font></html>");
        jLabel3.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelRanking)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 391, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(116, 116, 116)
                                        .addComponent(Jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(214, 214, 214)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(contenedorCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(contenedorVistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(contenedorCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(labelRanking)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CivitasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CivitasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Jugar;
    private javax.swing.JTextArea areaRanking;
    private javax.swing.JTextField campoEstado;
    private javax.swing.JPanel contenedorCasilla;
    private javax.swing.JPanel contenedorVistaJugador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelRanking;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
