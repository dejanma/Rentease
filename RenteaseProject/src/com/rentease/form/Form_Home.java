package com.rentease.form;

import com.rentease.database.DatabaseConnection;
import static com.rentease.database.Session.currentLogin;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class Form_Home extends javax.swing.JPanel {
    public Form_Home() {
        initComponents();
        setBackground(new Color(0,0,0,0));

        try {
            Connection con = DatabaseConnection.getConnection();

            int totalRow = countRows(con, "t_kamar");
            int tersedia = tersedia(con,"t_kamar");
            double totalUang = totalUang(con,"t_penyewaan","totalbiaya");
            
            DecimalFormat dec = new DecimalFormat("#,###");
            String formatNum = dec.format(totalUang);

            ttl_kamar.setText(tersedia+" / "+totalRow);
            ttl_pendptan.setText("Rp. "+formatNum);

        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        username.setText(currentLogin);
    }
    
    public static int countRows(Connection con, String tabel) throws SQLException {
        int totalRows = 0;
        Statement stm = null;
        ResultSet res = null;
        
        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT COUNT(*) AS total FROM " + tabel + " WHERE t_kamar.inputBy = '"+currentLogin+ "'");
            
            if (res.next()) {
                totalRows = res.getInt("total");
            }
        } finally {
            if (res != null) {
                res.close();
            } if (stm != null) {
                stm.close();
            }
        }
        return totalRows;
    }
    
    public static int tersedia(Connection con, String tabel) throws SQLException {
        int tersedia = 0;
        Statement stm = null;
        ResultSet res = null;
        
        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT COUNT(*) AS total FROM " + tabel + " WHERE t_kamar.inputBy = '"+currentLogin+ "' AND t_kamar.ketersediaan = '1'");
            
            if (res.next()) {
                tersedia = res.getInt("total");
            }
        } finally {
            if (res != null) {
                res.close();
            } if (stm != null) {
                stm.close();
            }
        }
        return tersedia;
    }
    
    public static double totalUang(Connection con, String tabel, String columnName) throws SQLException {
        double totalUang = 0;
        Statement stm = null;
        ResultSet res = null;
        
        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT SUM("+columnName+") AS total FROM " + tabel + " WHERE t_penyewaan.inputBy = '"+currentLogin+ "'");
            
            if (res.next()) {
                totalUang = res.getInt("total");
            }
        } finally {
            if (res != null) {
                res.close();
            } if (stm != null) {
                stm.close();
            }
        }
        return totalUang;
    }


    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.rentease.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        blurPanel7 = new com.rentease.swing.BlurPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ttl_pendptan = new javax.swing.JLabel();
        imageAvatar1 = new com.rentease.swing.ImageAvatar();
        username = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        blurPanel8 = new com.rentease.swing.BlurPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ttl_kamar = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        roundPanel1.setBackground(new java.awt.Color(36, 37, 41));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Home");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kelola Kosan");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Lebih Mudah dan Efisien");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rentease/icon/Asset 44x.png"))); // NOI18N

        blurPanel7.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rentease/icon/Money Bag_3.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Pendapatan");

        ttl_pendptan.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        ttl_pendptan.setForeground(new java.awt.Color(255, 255, 255));
        ttl_pendptan.setText("9 / 11");

        javax.swing.GroupLayout blurPanel7Layout = new javax.swing.GroupLayout(blurPanel7);
        blurPanel7.setLayout(blurPanel7Layout);
        blurPanel7Layout.setHorizontalGroup(
            blurPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurPanel7Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(blurPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ttl_pendptan)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        blurPanel7Layout.setVerticalGroup(
            blurPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(24, 24, 24)
                .addComponent(ttl_pendptan)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rentease/icon/avatar.jpg"))); // NOI18N

        username.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("Admin");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Selamat Datang");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Sistem Manajemen Kosan untuk Pemilik Kosan yang Efektif dan Efisien");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Maksimalkan Pengelolaan Kosan Anda dengan Fitur Terbaik dan Praktis.");

        blurPanel8.setPreferredSize(new java.awt.Dimension(200, 200));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rentease/icon/Open Door_2.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Jumlah Kamar");

        ttl_kamar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        ttl_kamar.setForeground(new java.awt.Color(255, 255, 255));
        ttl_kamar.setText("9 / 11");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("tersedia");

        javax.swing.GroupLayout blurPanel8Layout = new javax.swing.GroupLayout(blurPanel8);
        blurPanel8.setLayout(blurPanel8Layout);
        blurPanel8Layout.setHorizontalGroup(
            blurPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(blurPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(ttl_kamar)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        blurPanel8Layout.setVerticalGroup(
            blurPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blurPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(24, 24, 24)
                .addComponent(ttl_kamar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(blurPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(blurPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                                        .addGap(191, 191, 191)
                                        .addComponent(jLabel2))))
                            .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(username, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(145, 145, 145)
                        .addComponent(jLabel4))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(username)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(57, 57, 57)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(blurPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(blurPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(128, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rentease.swing.BlurPanel blurPanel7;
    private com.rentease.swing.BlurPanel blurPanel8;
    private com.rentease.swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private com.rentease.swing.RoundPanel roundPanel1;
    private javax.swing.JLabel ttl_kamar;
    private javax.swing.JLabel ttl_pendptan;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
