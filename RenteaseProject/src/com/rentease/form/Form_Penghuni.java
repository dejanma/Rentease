package com.rentease.form;

import com.rentease.database.DatabaseConnection;
import java.awt.Color;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import com.rentease.cell.TableActionCellRender;
import com.rentease.cell.TableActionCellEditor;
import com.rentease.cell.TableActionEvent;
import static com.rentease.database.Session.currentLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class Form_Penghuni extends javax.swing.JPanel {
    private String idPenghuni;
    
    public static boolean isNumeric(String input) {
    if (input == null || input.isEmpty()) {
        return false; // Handle null or empty input if needed
    }

    for (char c : input.toCharArray()) {
        if (!Character.isDigit(c)) {
            return false;
        }
    }
    return true;
}

    public String getIdPenghuni() {
        return idPenghuni;
    }
    public class CekDB
    {
        public void Check()
        {
            try
            {
                Connection con = DatabaseConnection.getConnection();
                Statement stm = con.createStatement();
              
                String sqlTabel = "SELECT t_penghuni.id_penghuni, t_penghuni.nama,  t_penghuni.nohp,  t_penghuni.jeniskelamin,  COALESCE ( t_kamar.nokamar, '-' ) AS nokamar,  COALESCE ( t_penyewaan.tgl_mulai, '-' ) AS tgl_mulai,  COALESCE ( t_penyewaan.tgl_selesai, '-' ) AS tgl_selesai FROM t_penghuni LEFT JOIN t_penyewaan ON  t_penyewaan.id_penghuni = t_penghuni.id_penghuni LEFT JOIN t_kamar ON  t_penyewaan.id_kamar = t_kamar.id_kamar WHERE t_penghuni.inputBy = '"+ currentLogin +"';";
                ResultSet rs = stm.executeQuery(sqlTabel);

                DefaultTableModel model =  (DefaultTableModel)tbl_penghuni.getModel();
                model.setRowCount(0);
                while (rs.next())
                {
                    model.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
                }

            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public Form_Penghuni() {
        initComponents();
        CekDB Check = new CekDB();
        Check.Check();
        tbl_penghuni.fixTable(jScrollPane1);
  
        int columnWidth = 70; 
        tbl_penghuni.setColumnWidth(7, columnWidth);
        
        TableActionEvent event = new TableActionEvent() {
            @Override
                public void onEdit(int row) {
                int selectedRow = tbl_penghuni.getSelectedRow();
                
                // Memeriksa apakah ada baris yang dipilih
                if (selectedRow != -1) {
                    setActive(true);
                    setRows(row);
                    // Mengambil nilai kolom yang sesuai dari baris yang dipilih
                    idPenghuni = tbl_penghuni.getValueAt(selectedRow, 0).toString();
                    
                    String nama = tbl_penghuni.getValueAt(selectedRow, 1).toString();
                    String kontak = tbl_penghuni.getValueAt(selectedRow, 2).toString();
                    String jenisKelamin = tbl_penghuni.getValueAt(selectedRow, 3).toString();

                    // Menetapkan nilai ke field teks dan radio button
                    tf_nama.setText(nama);
                    tf_kontak.setText(kontak);

                    if (jenisKelamin.equals("Laki-Laki")) {
                        rb_lakilaki.setSelected(true);
                        rb_perempuan.setSelected(false);
                    } else if (jenisKelamin.equals("Perempuan")) {
                        rb_lakilaki.setSelected(false);
                        rb_perempuan.setSelected(true);
                    }
                }
            }

            @Override
                public void onDelete(int row) {
                
                int selectedRow = tbl_penghuni.getSelectedRow();
                
                // Memeriksa apakah ada baris yang dipilih
                if (selectedRow != -1) {
                    // Mengambil nilai kolom yang sesuai dari baris yang dipilih
                    idPenghuni = tbl_penghuni.getValueAt(selectedRow, 0).toString();

                    try {
                        Connection con = DatabaseConnection.getConnection();
                        String sqlDelete = "DELETE FROM t_penghuni WHERE id_penghuni = ? AND inputBy = ?";
                        PreparedStatement deleteStatement = con.prepareStatement(sqlDelete);
                        deleteStatement.setString(1, idPenghuni);
                        deleteStatement.setString(2, currentLogin);
                        
                        // Get the id_kamar associated with the deleted id_penghuni
                        String sqlGetKamarId = "SELECT id_kamar FROM t_penyewaan WHERE id_penghuni = ?";
                        PreparedStatement getKamarIdStatement = con.prepareStatement(sqlGetKamarId);
                        getKamarIdStatement.setString(1, idPenghuni);
                        ResultSet resultSet = getKamarIdStatement.executeQuery();
                        int idKamar = -1;
                        
                        if (resultSet.next()) {
                            idKamar = resultSet.getInt("id_kamar");
                        }

                        int rowsDeleted = deleteStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            notif.setText("                             Data Berhasil dihapus!");
                            notif.setForeground(Color.green);
                            System.out.println("Data berhasil dihapus dari tabel t_penghuni");
                            String sqlUpdateKamar = "UPDATE t_kamar SET ketersediaan = 1 WHERE id_kamar = ?";
                            PreparedStatement updateKamarStatement = con.prepareStatement(sqlUpdateKamar);
                            updateKamarStatement.setInt(1, idKamar);
                            int rowsUpdated = updateKamarStatement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Status kamar telah diubah menjadi tersedia (1) di tabel t_kamar.");
                            } else {
                                System.out.println("Gagal mengubah status kamar di tabel t_kamar.");
                            }
                        } else {
                            System.out.println("Data tidak ditemukan atau tidak berhasil dihapus");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    CekDB Check = new CekDB();
                    Check.Check();
                }
                
            }

          
        };
       
        setBackground(new Color(0,0,0,0));
        tbl_penghuni.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        tbl_penghuni.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));
        
        search .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        
        
    }
        
    public String getNama()
    {
        return tf_nama.getText();
    }
    public String getKontak()
    {
        return tf_kontak.getText();
    }
    public String getJK()
    {
        String jeniskelamin = "";
        if (rb_lakilaki.isSelected())
        {
            jeniskelamin = "Laki-Laki";
        } else if (rb_perempuan.isSelected())
        {
            jeniskelamin = "Perempuan";
        }
        return jeniskelamin;
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
    int rows;
    public void setRows(int x) {
        this.rows = x;
    }
    public int getRows() {
        return rows;
    }


private void performSearch() {
    String searchTerm = search.getText().trim();
    if (searchTerm.isEmpty()) {
       CekDB Check = new CekDB();
        Check.Check();
    } else {
        try {
            String query = "SELECT t_penghuni.id_penghuni, t_penghuni.nama, t_penghuni.nohp, t_penghuni.jeniskelamin, COALESCE ( t_kamar.nokamar, '-' ) AS nokamar, COALESCE ( t_penyewaan.tgl_mulai, '-' ) AS tgl_mulai, COALESCE ( t_penyewaan.tgl_selesai, '-' ) AS tgl_selesai FROM t_penghuni LEFT JOIN t_penyewaan ON t_penyewaan.id_penghuni = t_penghuni.id_penghuni LEFT JOIN t_kamar ON t_penyewaan.id_kamar = t_kamar.id_kamar WHERE t_penghuni.inputBy = '" + currentLogin +"' AND (t_penghuni.nama LIKE '%" + searchTerm + "%' OR t_penghuni.nohp LIKE '%" + searchTerm + "%' OR t_penghuni.jeniskelamin LIKE '%" + searchTerm + "%')";
            Connection con = DatabaseConnection.getConnection();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(query);
          
            // Bersihkan data di tabel
            DefaultTableModel model =  (DefaultTableModel)tbl_penghuni.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {
                int id_penghuni = resultSet.getInt("id_penghuni");
                String nama = resultSet.getString("nama");
                String nohp = resultSet.getString("nohp");
                String jeniskelamin = resultSet.getString("jeniskelamin");
                String nokamar = resultSet.getString("nokamar");
                String tgl_mulai = resultSet.getString("tgl_mulai");
                String tgl_selesai = resultSet.getString("tgl_selesai");

                model.addRow(new Object[]{id_penghuni, nama, nohp, jeniskelamin, nokamar, tgl_mulai, tgl_selesai});
            }
        } catch (SQLException e) {
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
        tf_nama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        roundPanel4 = new com.rentease.swing.RoundPanel();
        tf_kontak = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rb_lakilaki = new javax.swing.JRadioButton();
        rb_perempuan = new javax.swing.JRadioButton();
        kButton1 = new com.k33ptoo.components.KButton();
        jLabel1 = new javax.swing.JLabel();
        roundPanel2 = new com.rentease.swing.RoundPanel();
        search = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_penghuni = new com.rentease.table.TableDark();
        notif = new javax.swing.JLabel();
        notif1 = new javax.swing.JLabel();

        roundPanel3.setBackground(new java.awt.Color(36, 37, 41));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kontak");

        roundPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tf_nama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_nama.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(255, 255, 255)));
        tf_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_namaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_nama)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama");

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tf_kontak.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tf_kontak.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(255, 255, 255)));
        tf_kontak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_kontakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_kontak)
                .addContainerGap())
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_kontak, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jenis Kelamin");

        buttonGroup1.add(rb_lakilaki);
        rb_lakilaki.setForeground(new java.awt.Color(255, 255, 255));
        rb_lakilaki.setText("Laki - Laki");

        buttonGroup1.add(rb_perempuan);
        rb_perempuan.setForeground(new java.awt.Color(255, 255, 255));
        rb_perempuan.setText("Perempuan");

        kButton1.setBackground(new java.awt.Color(0, 0, 0));
        kButton1.setForeground(new java.awt.Color(0, 0, 0));
        kButton1.setText("Simpan");
        kButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kButton1.setkAllowGradient(false);
        kButton1.setkBackGroundColor(new java.awt.Color(255, 243, 122));
        kButton1.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton1.setkHoverColor(new java.awt.Color(255, 255, 204));
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
        jLabel1.setText("Data Penghuni");

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

        tbl_penghuni.setBackground(new java.awt.Color(36, 37, 41));
        tbl_penghuni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nama", "Kontak", "Jenis Kelamin", "No Kamar", "Tanggal Masuk", "Tanggal Keluar", "Aksi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_penghuni.getTableHeader().setReorderingAllowed(false);
        tbl_penghuni.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbl_penghuniMouseMoved(evt);
            }
        });
        tbl_penghuni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tbl_penghuniFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_penghuni);
        if (tbl_penghuni.getColumnModel().getColumnCount() > 0) {
            tbl_penghuni.getColumnModel().getColumn(0).setMinWidth(0);
            tbl_penghuni.getColumnModel().getColumn(0).setMaxWidth(0);
            tbl_penghuni.getColumnModel().getColumn(5).setResizable(false);
            tbl_penghuni.getColumnModel().getColumn(6).setResizable(false);
            tbl_penghuni.getColumnModel().getColumn(7).setResizable(false);
        }

        notif.setForeground(new java.awt.Color(36, 37, 41));
        notif.setText("jLabel5");

        notif1.setForeground(new java.awt.Color(36, 37, 41));
        notif1.setText("jLabel5");

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(roundPanel3Layout.createSequentialGroup()
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roundPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6)
                                .addGroup(roundPanel3Layout.createSequentialGroup()
                                    .addComponent(rb_lakilaki)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rb_perempuan))
                                .addComponent(kButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                            .addComponent(notif)
                            .addGroup(roundPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(notif1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(14, 14, 14)
                        .addComponent(notif1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rb_lakilaki)
                            .addComponent(rb_perempuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(notif))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(124, 124, 124))
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

    private void tf_kontakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_kontakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_kontakActionPerformed

    private void tf_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_namaActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void kButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton1MouseClicked
        // TODO add your handling code here:
        String nama = getNama();
        String kontak = getKontak();
        String jeniskelamin = getJK();
        boolean conf = isNumeric(kontak);
        JLabel notiftxt1 = setNotif1();
        JLabel notiftxt = setNotif();
        notiftxt.setText("");
        if (nama.isEmpty() || kontak.isEmpty() || jeniskelamin.isEmpty()) {
            notiftxt.setText("                   Gagal Input Data: Data tidak lengkap!");
            notiftxt.setForeground(Color.red);
        } else {
            if(conf==false || kontak.length()>14) {
                notiftxt1.setText("      Gagal Input Data: Format Kontak Tidak Sesuai!");
                notiftxt1.setForeground(Color.red);
            } else if(getActive() && kontak.length()<15) {
                notiftxt1.setText("");
                Connection con = DatabaseConnection.getConnection();
                try {
                    String sql = "update t_penghuni "
                                    + "set nama='"+nama+"',"
                                    + "nohp='"+kontak+"',"
                                    + "jeniskelamin='"+jeniskelamin+"' "
                                + "where "
                                + "id_penghuni='"+tbl_penghuni.getValueAt(getRows(),0).toString()+"';";
                    PreparedStatement updateStatement = con.prepareStatement(sql);

                    int rowsUpdated = updateStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        notiftxt.setText("                                 Berhasil Update Data!");
                        notiftxt.setForeground(Color.green);
                        
                        tf_nama.setText("");
                        tf_kontak.setText("");
                        rb_lakilaki.setSelected(false);
                        rb_perempuan.setSelected(false);
                        setActive(false);

                        CekDB Check = new CekDB();
                        Check.Check();
                    }
                } catch (SQLException e) {
                    // Penanganan kesalahan saat mengakses database
                    e.printStackTrace();
                }

                CekDB Check = new CekDB();
                Check.Check();

            } else {
                notiftxt1.setText("");
                Connection con = DatabaseConnection.getConnection();
                try {
                    String sql = "INSERT INTO `t_penghuni` (`nama`, `nohp`, `jeniskelamin`, `inputBy`) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertStatement = con.prepareStatement(sql);
                    insertStatement.setString(1, nama);
                    insertStatement.setString(2, kontak);
                    insertStatement.setString(3, jeniskelamin);
                    insertStatement.setString(4, currentLogin);
                    int rowsInserted = insertStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        notiftxt.setText("                                Berhasil Input Data!");
                        notiftxt.setForeground(Color.green);
                        setActive(false);
                        setRows(0);
                        tf_nama.setText("");
                        tf_kontak.setText("");
                        rb_lakilaki.setSelected(false);
                        rb_perempuan.setSelected(false);
                        CekDB Check = new CekDB();
                        Check.Check();
                    }
                } catch (SQLException e) {
                    // Penanganan kesalahan saat mengakses database
                    e.printStackTrace();
                }

                CekDB Check = new CekDB();
                Check.Check();
            }
        }
    }//GEN-LAST:event_kButton1MouseClicked

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        performSearch();
    }//GEN-LAST:event_jLabel4MousePressed

    private void tbl_penghuniMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_penghuniMouseMoved

    }//GEN-LAST:event_tbl_penghuniMouseMoved

    private void tbl_penghuniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbl_penghuniFocusGained
 
    }//GEN-LAST:event_tbl_penghuniFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.k33ptoo.components.KButton kButton1;
    private javax.swing.JLabel notif;
    private javax.swing.JLabel notif1;
    private javax.swing.JRadioButton rb_lakilaki;
    private javax.swing.JRadioButton rb_perempuan;
    private com.rentease.swing.RoundPanel roundPanel1;
    private com.rentease.swing.RoundPanel roundPanel2;
    private com.rentease.swing.RoundPanel roundPanel3;
    private com.rentease.swing.RoundPanel roundPanel4;
    private javax.swing.JTextField search;
    private com.rentease.table.TableDark tbl_penghuni;
    private javax.swing.JTextField tf_kontak;
    private javax.swing.JTextField tf_nama;
    // End of variables declaration//GEN-END:variables
}
