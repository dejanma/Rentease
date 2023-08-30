package com.rentease.main;

import com.rentease.event.EventMenu;
import com.rentease.form.Form_Home;
import com.rentease.form.Form_Kamar;
import com.rentease.form.Form_Penghuni;
import com.rentease.form.Form_Riwayat;
import com.rentease.form.Form_Transaksi;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;

public class Dashboard extends javax.swing.JFrame {
private static Dashboard main;
private String session;

    public Dashboard() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        this.session = session;
        
     header1.initMoving(Dashboard.this);
     main = this;
     titleBar1.initJFram(this);
   
        setBackground(new Color(0, 0, 0, 0));
        EventMenu event = new EventMenu() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    showForm(new Form_Home());
                } else if (index == 1) {
                    showForm(new Form_Transaksi());
                } else if (index == 2) {
                    System.out.println(session);
                    showForm(new Form_Kamar());
                }else if (index == 3) {
                    showForm(new Form_Penghuni());
                } else if (index == 4) {
                    showForm(new Form_Riwayat());
                } else if (index == 5) {
                    Login login = new Login();
                    login.setVisible(true);
                    dispose()
;                }
                else {
                    
                }
            }
        };
        menu1.initMenu(event);
        
        showForm(new Form_Transaksi());
        showForm(new Form_Kamar());
        showForm(new Form_Penghuni());
        showForm(new Form_Riwayat());
        showForm(new Form_Home());     
    }

    public String getSession() {
        return session;
    }
    
    private void showForm(Component com) {
        body.removeAll();
        body.add(com);
        body.revalidate();
        body.repaint();
    }
    
   public static Dashboard getMain() {
        return main;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        componentMoverUtil1 = new com.k33ptoo.utils.ComponentMoverUtil();
        roundPanel1 = new com.rentease.swing.RoundPanel();
        body = new javax.swing.JPanel();
        menu1 = new com.rentease.component.Menu();
        header1 = new com.rentease.component.Header();
        titleBar1 = new com.rentease.component.TitleBar();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        roundPanel1.setBackground(new java.awt.Color(36, 37, 41));

        body.setOpaque(false);
        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout header1Layout = new javax.swing.GroupLayout(header1);
        header1.setLayout(header1Layout);
        header1Layout.setHorizontalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        header1Layout.setVerticalGroup(
            header1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(header1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addComponent(header1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addComponent(header1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
          JFrame frame = new JFrame();
      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dashboard panel = new Dashboard();
                panel.setVisible(true);
            }
        });
      
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private com.k33ptoo.utils.ComponentMoverUtil componentMoverUtil1;
    private com.rentease.component.Header header1;
    private javax.swing.JLabel jLabel1;
    private com.rentease.component.Menu menu1;
    private com.rentease.swing.RoundPanel roundPanel1;
    private com.rentease.component.TitleBar titleBar1;
    // End of variables declaration//GEN-END:variables

    private static class Connection {

        public Connection() {
        }
    }
}
