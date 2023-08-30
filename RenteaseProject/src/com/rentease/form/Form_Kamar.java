package com.rentease.form;

import com.rentease.cell.TableActionCellEditor;
import com.rentease.cell.TableActionCellRender;
import com.rentease.cell.TableActionEvent;
import com.rentease.database.DatabaseConnection;
import static com.rentease.database.Session.currentLogin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class Form_Kamar extends javax.swing.JPanel {
    private String idKamar;
    public String getIdKamar() {
        return idKamar;
    }
    public class CekDB
    {
        public void Check()
        {
            try
            {
                Connection con = DatabaseConnection.getConnection();
                Statement stm = con.createStatement();                

                // table kamar
                String sqlTabel = "SELECT t_kamar.id_kamar, t_kamar.nokamar, t_kamar.hargasewa, t_kamar.ketersediaan FROM t_kamar WHERE t_kamar.inputBy = '" + currentLogin + "'";
                ResultSet rs = stm.executeQuery(sqlTabel);

                DefaultTableModel model =  (DefaultTableModel)tbl_kamar.getModel();
                model.setRowCount(0);
                while (rs.next()) {
                    String ketersediaan = rs.getInt("ketersediaan") == 1 ? "Kosong" : "Terisi";
                    model.addRow(new String[]{rs.getString("id_kamar"), rs.getString("nokamar"), rs.getString("hargasewa"), ketersediaan});
                }

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public Form_Kamar() {
        initComponents();
        CekDB Check = new CekDB();
        Check.Check();
        
        setBackground(new Color(0,0,0,0));
        int columnWidth = 70; 
        tbl_kamar.setColumnWidth(4, columnWidth);
        tbl_kamar.fixTable(jScrollPane2);
        
        TableActionEvent event = new TableActionEvent() {
            @Override
                public void onEdit(int row) {
                    int selectedRow = tbl_kamar.getSelectedRow();
                
                    // Memeriksa apakah ada baris yang dipilih
                    if (selectedRow != -1) {
                        setActive(true);
                        // Mengambil nilai kolom yang sesuai dari baris yang dipilih
                        idKamar = tbl_kamar.getValueAt(selectedRow, 0).toString();

                        String nokamar = tbl_kamar.getValueAt(selectedRow, 1).toString();
                        String harga = tbl_kamar.getValueAt(selectedRow, 2).toString();

                        // Menetapkan nilai ke field teks dan radio button
                        tf_nokamar.setText(nokamar);
                        tf_harga.setText(harga);
                    }
                }

            @Override
            public void onDelete(int row) {
                int selectedRow = tbl_kamar.getSelectedRow();
                
                // Memeriksa apakah ada baris yang dipilih
                if (selectedRow != -1) {
                    // Mengambil nilai kolom yang sesuai dari baris yang dipilih
                    idKamar = tbl_kamar.getValueAt(selectedRow, 0).toString();

                    try {
                        Connection con = DatabaseConnection.getConnection();
                        String sqlDelete = "DELETE FROM t_kamar WHERE id_kamar = ? AND inputBy = ?";
                        PreparedStatement deleteStatement = con.prepareStatement(sqlDelete);
                        deleteStatement.setString(1, idKamar);
                        deleteStatement.setString(2, currentLogin);
                        
                        JLabel notiftxt = setNotif();

                        int rowsDeleted = deleteStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Data berhasil dihapus");
                            notiftxt.setText("                             Data Berhasil dihapus!");
                            notiftxt.setForeground(Color.green);
                        } else {
                            System.out.println("Data tidak ditemukan atau tidak berhasil dihapus");
                            notiftxt.setText("Data tidak ditemukan atau tidak berhasil dihapus");
                            notiftxt.setForeground(Color.red);
                        }
                    } catch (SQLException e) {
                        // Tangani eksepsi
                        e.printStackTrace();
                    }
                }
                CekDB Check = new CekDB();
                Check.Check();
            }

          
        };
       
        setBackground(new Color(0,0,0,0));
        tbl_kamar.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        tbl_kamar.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
        
        search .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        
    }
    
    public String getNokamar()
    {
        return tf_nokamar.getText();
    }
    public String getHarga()
    {
        return tf_harga.getText();
    }
    public javax.swing.JLabel setNotif() {
        return notif;
    }
    public javax.swing.JLabel setNotif1() {
        return notif1;
    }
    boolean active = false;
    public void setActive(boolean x) {
        this.active = x;
    }
    public boolean getActive() {
        return active;
    }
    public static String isNokamarExisted(Connection con, String tabel, String tesNokamar) throws SQLException {
        Statement stm = null;
        ResultSet res = null;
        
        try {
            stm = con.createStatement();
            res = stm.executeQuery("SELECT nokamar AS tes FROM " + tabel + " WHERE t_kamar.inputBy = '"+currentLogin+ "' AND t_kamar.nokamar = '"+tesNokamar+ "'");
            
            if (res.next()) {
                tesNokamar = res.getString("tes");
                return tesNokamar;
            }
            
        } finally {
            if (res != null) {
                res.close();
            } if (stm != null) {
                stm.close();
            }
        }
        return "0";
    }
    
    private void performSearch() {
        String searchTerm = search.getText().trim();
        if (searchTerm.isEmpty()) {
            CekDB Check = new CekDB();
            Check.Check();
        } else {
            try {
                Connection con = DatabaseConnection.getConnection();
                Statement stm = con.createStatement();

                // table kamar
                String sqlTabel = "SELECT t_kamar.id_kamar, t_kamar.nokamar, t_kamar.hargasewa, t_kamar.ketersediaan FROM t_kamar WHERE t_kamar.inputBy = '" + currentLogin + "' AND (t_kamar.nokamar LIKE '%" + searchTerm + "%' OR t_kamar.hargasewa LIKE '%" + searchTerm + "%' OR t_kamar.ketersediaan LIKE '%" + searchTerm + "%');";
                ResultSet rs = stm.executeQuery(sqlTabel);

                DefaultTableModel model = (DefaultTableModel) tbl_kamar.getModel();
                model.setRowCount(0);
                while (rs.next()) {
                    int ketersediaanValue = rs.getInt("ketersediaan");
                    String ketersediaan = (ketersediaanValue == 1) ? "Kosong" : "Terisi";
                    model.addRow(new Object[]{rs.getString("id_kamar"), rs.getString("nokamar"), rs.getString("hargasewa"), ketersediaan});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        roundPanel3 = new com.rentease.swing.RoundPanel();
        jLabel2 = new javax.swing.JLabel();
        roundPanel1 = new com.rentease.swing.RoundPanel();
        tf_nokamar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        roundPanel4 = new com.rentease.swing.RoundPanel();
        tf_harga = new javax.swing.JTextField();
        kButton1 = new com.k33ptoo.components.KButton();
        jLabel1 = new javax.swing.JLabel();
        roundPanel2 = new com.rentease.swing.RoundPanel();
        search = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_kamar = new com.rentease.table.TableDark();
        notif1 = new javax.swing.JLabel();
        notif = new javax.swing.JLabel();

        roundPanel3.setBackground(new java.awt.Color(36, 37, 41));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Harga Kamar");

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tf_nokamar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_nokamar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(255, 255, 255)));
        tf_nokamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nokamarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_nokamar)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_nokamar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nomor Kamar");

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tf_harga.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_harga.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(255, 255, 255)));
        tf_harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_hargaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_harga)
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tf_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        kButton1.setBackground(new java.awt.Color(0, 0, 0));
        kButton1.setForeground(new java.awt.Color(0, 0, 0));
        kButton1.setText("Simpan");
        kButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton1.setkAllowGradient(false);
        kButton1.setkBackGroundColor(new java.awt.Color(255, 243, 122));
        kButton1.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton1.setkHoverColor(new java.awt.Color(255, 255, 153));
        kButton1.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        kButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton1MouseClicked(evt);
            }
        });
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Data Kamar");

        roundPanel2.setBackground(new java.awt.Color(36, 37, 41));

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

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        tbl_kamar.setBackground(new java.awt.Color(36, 37, 41));
        tbl_kamar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Kamar", "No Kamar", "Harga (Per Bulan)", "Status", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_kamar.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbl_kamar);
        if (tbl_kamar.getColumnModel().getColumnCount() > 0) {
            tbl_kamar.getColumnModel().getColumn(0).setMinWidth(0);
            tbl_kamar.getColumnModel().getColumn(0).setMaxWidth(0);
            tbl_kamar.getColumnModel().getColumn(1).setResizable(false);
            tbl_kamar.getColumnModel().getColumn(1).setPreferredWidth(20);
            tbl_kamar.getColumnModel().getColumn(2).setResizable(false);
            tbl_kamar.getColumnModel().getColumn(3).setResizable(false);
            tbl_kamar.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(945, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(kButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(notif1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(notif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(notif1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(notif, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton1ActionPerformed

    private void tf_nokamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nokamarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nokamarActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void kButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton1MouseClicked
        // TODO add your handling code here:
        String nokamar = getNokamar();
        String harga = getHarga();
        int hargaDouble = 0;

        // Memeriksa apakah nokamar dan harga kosong
        if (nokamar.isEmpty() || harga.isEmpty()) {
            JLabel notiftxt = setNotif();
            JLabel notiftxt1 = setNotif1();
            notiftxt1.setText("");
            notiftxt.setText("                   Gagal Input Data: Data tidak lengkap!");
            notiftxt.setForeground(Color.red);
        } else {
            Connection con = DatabaseConnection.getConnection();
            try {
                hargaDouble = (int) Double.parseDouble(harga);
            } catch (NumberFormatException ex) {
                JLabel notiftxt = setNotif();
                JLabel notiftxt1 = setNotif1();
                notiftxt.setText("");
                notiftxt1.setText("                           Harga harus berupa angka!");
                notiftxt1.setForeground(Color.red);
                return;
            }
            try {
                String checkSql = "SELECT id_kamar FROM t_kamar WHERE id_kamar = ?";
                PreparedStatement checkStatement = con.prepareStatement(checkSql);
                checkStatement.setString(1, idKamar);
                ResultSet checkResult = checkStatement.executeQuery();

                if (checkResult.next() && getActive()) {
                    // Jika nokamar sudah ada, lakukan UPDATE
                    String updateSql = "UPDATE t_kamar SET nokamar = ?, hargasewa = ?, inputBy = ? WHERE id_kamar = ?";
                    PreparedStatement updateStatement = con.prepareStatement(updateSql);
                    updateStatement.setString(1, nokamar);
                    updateStatement.setDouble(2, hargaDouble);
                    updateStatement.setString(3, currentLogin);
                    updateStatement.setString(4, idKamar);

                    int rowsUpdated = updateStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        
                        JLabel notiftxt = setNotif();
                        notiftxt.setText("                                Berhasil Update Data!");
                        notiftxt.setForeground(Color.green);
                        
                        tf_harga.setText("");
                        tf_nokamar.setText("");
                        setActive(false);
                        
                        CekDB Check = new CekDB();
                        Check.Check();
                    } else {
                        JLabel notiftxt = setNotif();
                        notiftxt.setText("                                Gagal Update Data!");
                        notiftxt.setForeground(Color.red);
                    }
                } else {
                    // Jika nokamar belum ada, lakukan INSERT
                    if(isNokamarExisted(con,"t_kamar",nokamar).equals(nokamar)) {
                        JLabel notiftxt = setNotif();
                        notiftxt.setText("                            Nomor Kamar sudah ada!");
                        notiftxt.setForeground(Color.red);
                    } else {
                        String insertSql = "INSERT INTO t_kamar (nokamar, hargasewa, ketersediaan, inputBy) VALUES (?, ?, '1', ?)";
                        PreparedStatement insertStatement = con.prepareStatement(insertSql);
                        insertStatement.setString(1, nokamar);
                        insertStatement.setDouble(2, hargaDouble);
                        insertStatement.setString(3, currentLogin);

                        int rowsInserted = insertStatement.executeUpdate();

                        if (rowsInserted > 0) {
                            JLabel notiftxt = setNotif();
                            notiftxt.setText("                                Berhasil Input Data!");
                            notiftxt.setForeground(Color.green);

                            CekDB Check = new CekDB();
                            Check.Check();
                        }
                        tf_nokamar.setText("");
                        tf_harga.setText("");
                    }                    
                }
            } catch (SQLException ex) {
                // Tangani exception jika terjadi kesalahan dalam koneksi atau query SQL
                JLabel notiftxt = setNotif();
                notiftxt.setText("Terjadi kesalahan dalam koneksi atau query SQL");
                notiftxt.setForeground(Color.red);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_kButton1MouseClicked

    private void tf_hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_hargaActionPerformed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        performSearch();
    }//GEN-LAST:event_jLabel4MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private com.k33ptoo.components.KButton kButton1;
    private javax.swing.JLabel notif;
    private javax.swing.JLabel notif1;
    private com.rentease.swing.RoundPanel roundPanel1;
    private com.rentease.swing.RoundPanel roundPanel2;
    private com.rentease.swing.RoundPanel roundPanel3;
    private com.rentease.swing.RoundPanel roundPanel4;
    private javax.swing.JTextField search;
    private com.rentease.table.TableDark tbl_kamar;
    private javax.swing.JTextField tf_harga;
    private javax.swing.JTextField tf_nokamar;
    // End of variables declaration//GEN-END:variables
}
