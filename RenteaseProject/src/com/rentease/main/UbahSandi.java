package com.rentease.main;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.*;
import com.rentease.database.DatabaseConnection;

public class UbahSandi extends javax.swing.JFrame {
    private Point mouseDownCompCoords = null;
    
    public UbahSandi() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        
        addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            // Mendapatkan posisi awal saat mouse ditekan
            mouseDownCompCoords = e.getPoint();
        }
    });

    // Tambahkan listener mouse dragged
    addMouseMotionListener(new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            // Menghitung perbedaan posisi mouse saat ini dengan posisi awal saat mouse ditekan
            try {
                int x = getLocation().x + e.getX() - mouseDownCompCoords.x;
                int y = getLocation().y + e.getY() - mouseDownCompCoords.y;
                setLocation(x, y);
            } catch (Exception ex) {
            }
        }
    });
    form_UbahSandi1.getKembaliLabel().addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            navigateToLogin();
        }
    });
    
    form_UbahSandi1.getUbahButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    Connection con = DatabaseConnection.getConnection();

                    String username = form_UbahSandi1.getUsername();
                    char[] passwordChar = form_UbahSandi1.getPassword();
                    String password = new String(passwordChar);
                    String email = form_UbahSandi1.getEmail();

                    JLabel notif = form_UbahSandi1.setNotif();

                    // Memeriksa apakah ada data yang kosong
                    if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                        notif.setText("                            Data tidak lengkap!");
                        notif.setForeground(Color.red);
                    } else {
                        String sql = "UPDATE t_users SET PASSWORD = ? WHERE username = ? AND email = ?";
                        PreparedStatement updateStatement = con.prepareStatement(sql);
                        updateStatement.setString(1, password);
                        updateStatement.setString(2, username);
                        updateStatement.setString(3, email);

                        int rowsUpdated = updateStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            notif.setText("                    Kata Sandi Berhasil di Ubah!");
                            notif.setForeground(Color.green);
                        } else {
                            notif.setText("        Email atau Nama Pengguna tidak sesuai");
                            notif.setForeground(Color.red);
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
    
    
    }
    private void navigateToLogin() {
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        form_UbahSandi1 = new com.rentease.form.Form_UbahSandi();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(form_UbahSandi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(form_UbahSandi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(UbahSandi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UbahSandi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UbahSandi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UbahSandi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new UbahSandi().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rentease.form.Form_UbahSandi form_UbahSandi1;
    // End of variables declaration//GEN-END:variables
}
