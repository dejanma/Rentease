package com.rentease.form;

import com.rentease.database.DatabaseConnection;
import static com.rentease.database.Session.currentLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


public class Form_Riwayat extends javax.swing.JPanel {
    public Form_Riwayat() {
        initComponents();
    
            try {
                String sqlTabel = "SELECT t_penyewaan.tgl_transaksi, COALESCE(t_penghuni.nama, '-') AS nama, COALESCE(t_kamar.nokamar, '-') AS no_kamar, durasi AS durasi, t_penyewaan.tgl_mulai, t_penyewaan.tgl_selesai, t_penyewaan.totalbiaya FROM t_penyewaan LEFT JOIN t_penghuni ON t_penyewaan.id_penghuni = t_penghuni.id_penghuni LEFT JOIN t_kamar ON t_penyewaan.id_kamar = t_kamar.id_kamar JOIN t_users ON t_penyewaan.inputBy = t_users.username WHERE t_users.username = '" + currentLogin + "';";
                Connection con = DatabaseConnection.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sqlTabel);

                DefaultTableModel model =  (DefaultTableModel)tbl_transaksi.getModel();
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }    
            
            search .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
    }

    public class CekDB
    {
        public void Check()
        {
            try {
                String sqlTabel = "SELECT t_penyewaan.tgl_transaksi, COALESCE(t_penghuni.nama, '-') AS nama, COALESCE(t_kamar.nokamar, '-') AS no_kamar, durasi AS durasi, t_penyewaan.tgl_mulai, t_penyewaan.tgl_selesai, t_penyewaan.totalbiaya FROM t_penyewaan LEFT JOIN t_penghuni ON t_penyewaan.id_penghuni = t_penghuni.id_penghuni LEFT JOIN t_kamar ON t_penyewaan.id_kamar = t_kamar.id_kamar JOIN t_users ON t_penyewaan.inputBy = t_users.username WHERE t_users.username = '" + currentLogin + "';";
                Connection con = DatabaseConnection.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sqlTabel);

                DefaultTableModel model =  (DefaultTableModel)tbl_transaksi.getModel();
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }    
        }
    }
    
    private void performSearch() {
        String searchTerm = search.getText().trim();
            System.out.println(searchTerm);
            System.out.println(currentLogin);
            
        if (searchTerm.isEmpty()) {
           CekDB Check = new CekDB();
            Check.Check();
        } else {
            try {
                String query = "SELECT t_penyewaan.tgl_transaksi, COALESCE(t_penghuni.nama, '-') AS nama, COALESCE(t_kamar.nokamar, '-') AS no_kamar, durasi AS durasi, t_penyewaan.tgl_mulai, t_penyewaan.tgl_selesai, t_penyewaan.totalbiaya FROM t_penyewaan LEFT JOIN t_penghuni ON t_penyewaan.id_penghuni = t_penghuni.id_penghuni LEFT JOIN t_kamar ON t_penyewaan.id_kamar = t_kamar.id_kamar WHERE t_penyewaan.inputBy = '" + currentLogin +"' AND (t_penghuni.nama LIKE '%" + searchTerm + "%' OR t_kamar.nokamar LIKE '%" + searchTerm + "%' OR t_penyewaan.totalbiaya LIKE '%" + searchTerm + "%' OR t_penyewaan.durasi LIKE '%" + searchTerm + "%')";
                Connection con = DatabaseConnection.getConnection();
                Statement stm = con.createStatement();
                ResultSet resultSet = stm.executeQuery(query);

                // Bersihkan data di tabel
                DefaultTableModel model =  (DefaultTableModel)tbl_transaksi.getModel();
                model.setRowCount(0);

                while (resultSet.next()) {
                     model.addRow(new String[]{resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)});
                }
            } catch (SQLException e) {
            }
        }
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundPanel1 = new com.rentease.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel3 = new com.rentease.swing.RoundPanel();
        search = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_transaksi = new com.rentease.table.TableDark();

        setOpaque(false);

        roundPanel1.setBackground(new java.awt.Color(36, 37, 41));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Riwayat Transaksi");

        roundPanel3.setBackground(new java.awt.Color(36, 37, 41));

        search.setBackground(new java.awt.Color(36, 37, 41));
        search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        search.setCaretColor(new java.awt.Color(255, 255, 255));
        search.setName("Search"); // NOI18N
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rentease/icon/Search_1.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        tbl_transaksi.setBackground(new java.awt.Color(36, 37, 41));
        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tanggal Transaksi", "Nama", "Kamar", "Durasi (bulan)", "Tanggal Mulai", "Tanggal Selesai", "Total Biaya"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_transaksi.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbl_transaksi);

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1099, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(367, 367, 367)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(91, 91, 91)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_searchActionPerformed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        performSearch();
    }//GEN-LAST:event_jLabel4MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private com.rentease.swing.RoundPanel roundPanel1;
    private com.rentease.swing.RoundPanel roundPanel3;
    private javax.swing.JTextField search;
    private com.rentease.table.TableDark tbl_transaksi;
    // End of variables declaration//GEN-END:variables
}
