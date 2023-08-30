package com.rentease.main;

import com.rentease.main.Login;
import com.rentease.main.Daftar;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import com.rentease.database.DatabaseConnection;
import java.sql.*;
import javax.swing.JLabel;

public class Daftar extends javax.swing.JFrame {

    private Point mouseDownCompCoords = null;

    public Daftar() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        addMouseListener(new MouseAdapter() {
            
        @Override
        public void mousePressed(MouseEvent e) {
            // Mendapatkan posisi awal saat mouse ditekan
            mouseDownCompCoords = e.getPoint();
        }
    });


    addMouseMotionListener(new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {    
            try {
                int x = getLocation().x + e.getX() - mouseDownCompCoords.x;
                int y = getLocation().y + e.getY() - mouseDownCompCoords.y;
                setLocation(x, y);
            } catch (Exception ex) {
            }
        }
    });
    
    form_Daftar1.getMasukLabel().addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            navigateToNextFrame();
        }
    });
    
    form_Daftar1.getDaftarButton().addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        JLabel notif = form_Daftar1.setNotif();
        try
        {
            Connection con = DatabaseConnection.getConnection();
            
            String username = form_Daftar1.getUsername();
            char[] passwordChar = form_Daftar1.getPassword();
            String password = new String(passwordChar);
            
            String email = form_Daftar1.getEmail();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                    notif.setText("                       Data tidak lengkap!");
                    notif.setForeground(Color.red);
            } else {
                String sql = "INSERT INTO `t_users` (`username`, `password`, `email`) VALUES ('" + username + "', '" + password + "', '" + email + "');";
                PreparedStatement insertStatement = con.prepareStatement(sql);
                int rowsInserted = insertStatement.executeUpdate();
                if (rowsInserted > 0) {
                    notif.setText("                        Berhasil Daftar!");
                    notif.setForeground(Color.green);
                }
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            notif.setText("     Terjadi Kesalahan: Data Sudah terdaftar");
            notif.setForeground(Color.red);
        } catch(SQLException er)
        {
            System.out.println(er.getMessage());
        }
    }
});
    
    }
    
    private void navigateToNextFrame() {
        Login nextFrame = new Login();
        nextFrame.setVisible(true);
        dispose();
    }


 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        form_Daftar1 = new com.rentease.form.Form_Daftar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(form_Daftar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(form_Daftar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  
    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Daftar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Daftar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rentease.form.Form_Daftar form_Daftar1;
    // End of variables declaration//GEN-END:variables
}
