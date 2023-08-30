package com.rentease.form;

import com.rentease.database.DatabaseConnection;
import static com.rentease.database.Session.currentLogin;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;


public class Form_Transaksi extends javax.swing.JPanel {
    
        public class Item {
        private int id;
        private String name;

        public Item(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    public Form_Transaksi() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        
        Connection con = DatabaseConnection.getConnection();
        try {
//            String sqlNama = "SELECT id_penghuni, nama FROM t_penghuni WHERE t_penghuni.inputBy = '"+ currentLogin + "';";
            String sqlNama = "SELECT id_penghuni, nama FROM t_penghuni WHERE inputBy = '"+ currentLogin + "' AND NOT EXISTS (SELECT 1 FROM t_penyewaan WHERE t_penyewaan.id_penghuni = t_penghuni.id_penghuni);";
            Statement sNama = con.createStatement();
            ResultSet rsNama = sNama.executeQuery(sqlNama);
            
            cb_nama.removeAllItems();
            cb_nama.addItem("Cari Nama");
            
//            debug
//            System.out.println("---------------");
//            System.out.println("data penghuni:");

            DefaultComboBoxModel model_nama = new DefaultComboBoxModel();
            while (rsNama.next()) {
                int id_penghuni = rsNama.getInt("id_penghuni");
                String nama = rsNama.getString("nama");
                
//                System.out.println("id:" + id_penghuni + " | nama: " + nama);
                model_nama.addElement(new Item(id_penghuni, nama));
            }
            cb_nama.setModel(model_nama);
//            debug
//            System.out.println("---------------");
//            System.out.println("data kamar:");

            // -----------------------------------------------------------------------
            
            String sqlKamar = "SELECT id_kamar, nokamar FROM t_kamar WHERE KETERSEDIAAN = 1 AND t_kamar.inputBy = '"+ currentLogin + "';";
            Statement sKamar = con.createStatement();
            ResultSet rsKamar = sKamar.executeQuery(sqlKamar);

            cb_kamar.removeAllItems();
            cb_kamar.addItem("Cari Kamar");

            DefaultComboBoxModel model_kamar = new DefaultComboBoxModel();
            while (rsKamar.next()) {
                int id_kamar = rsKamar.getInt("id_kamar");
                String kamar = rsKamar.getString("nokamar");
                
//                System.out.println("id:" + id_kamar + " | noKamar: " + kamar);
                model_kamar.addElement(new Item(id_kamar, kamar));
            }
            cb_kamar.setModel(model_kamar);
            
            sNama.close();
            sKamar.close();
            
            rsNama.close();
            rsKamar.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
    }
    
    public javax.swing.JLabel setNotif() {
        return notif;
    }
            
    public static boolean isLamaTinggalInt(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.rentease.datechooser.DateChooser();
        dateChooser2 = new com.rentease.datechooser.DateChooser();
        roundPanel1 = new com.rentease.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        roundPanel4 = new com.rentease.swing.RoundPanel();
        cb_nama = new com.rentease.combobox.Combobox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        kButton1 = new com.k33ptoo.components.KButton();
        kButton2 = new com.k33ptoo.components.KButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        roundPanel5 = new com.rentease.swing.RoundPanel();
        cb_kamar = new com.rentease.combobox.Combobox();
        roundPanel6 = new com.rentease.swing.RoundPanel();
        jd_tgl_masuk = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        roundPanel7 = new com.rentease.swing.RoundPanel();
        tf_lamatinggal = new javax.swing.JTextField();
        roundPanel8 = new com.rentease.swing.RoundPanel();
        tf_tgl_transaksi = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        roundPanel9 = new com.rentease.swing.RoundPanel();
        tf_tgl_keluar = new javax.swing.JTextField();
        notif = new javax.swing.JLabel();

        dateChooser1.setForeground(new java.awt.Color(102, 102, 102));
        dateChooser1.setDateFormat("yyyy-MM-dd");
        dateChooser1.setTextRefernce(jd_tgl_masuk);

        dateChooser2.setForeground(new java.awt.Color(102, 102, 102));
        dateChooser2.setDateFormat("yyyy-MM-dd");
        dateChooser2.setTextRefernce(tf_tgl_transaksi);

        roundPanel1.setBackground(new java.awt.Color(36, 37, 41));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transaksi");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama");

        roundPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(cb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cb_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("No Kamar");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tanggal Masuk");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Lama Tinggal");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tanggal Transaksi");

        kButton1.setBackground(new java.awt.Color(0, 0, 0));
        kButton1.setForeground(new java.awt.Color(0, 0, 0));
        kButton1.setText("Ulang");
        kButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButton1.setkAllowGradient(false);
        kButton1.setkBackGroundColor(new java.awt.Color(255, 0, 0));
        kButton1.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton1.setkHoverForeGround(new java.awt.Color(255, 102, 102));
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

        kButton2.setBackground(new java.awt.Color(0, 0, 0));
        kButton2.setForeground(new java.awt.Color(0, 0, 0));
        kButton2.setText("Simpan");
        kButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        kButton2.setkAllowGradient(false);
        kButton2.setkBackGroundColor(new java.awt.Color(255, 243, 122));
        kButton2.setkForeGround(new java.awt.Color(0, 0, 0));
        kButton2.setkHoverColor(new java.awt.Color(255, 255, 204));
        kButton2.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        kButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kButton2MouseClicked(evt);
            }
        });
        kButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tanggal Keluar");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("(otomatis)");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("(bulan)");

        roundPanel5.setBackground(new java.awt.Color(255, 255, 255));

        cb_kamar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6" }));

        javax.swing.GroupLayout roundPanel5Layout = new javax.swing.GroupLayout(roundPanel5);
        roundPanel5.setLayout(roundPanel5Layout);
        roundPanel5Layout.setHorizontalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(cb_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        roundPanel5Layout.setVerticalGroup(
            roundPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cb_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jd_tgl_masuk.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jd_tgl_masuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jd_tgl_masukActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rentease/icon/Date To_3.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jd_tgl_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jd_tgl_masuk, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addGroup(roundPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        roundPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tf_lamatinggal.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        tf_lamatinggal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_lamatinggalFocusLost(evt);
            }
        });

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(tf_lamatinggal, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_lamatinggal, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tf_tgl_transaksi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rentease/icon/Date To_3.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(tf_tgl_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel8Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_tgl_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        roundPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tf_tgl_keluar.setEditable(false);
        tf_tgl_keluar.setBackground(new java.awt.Color(255, 255, 255));
        tf_tgl_keluar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout roundPanel9Layout = new javax.swing.GroupLayout(roundPanel9);
        roundPanel9.setLayout(roundPanel9Layout);
        roundPanel9Layout.setHorizontalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(tf_tgl_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        roundPanel9Layout.setVerticalGroup(
            roundPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_tgl_keluar, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap(242, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(roundPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(roundPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel9))
                        .addComponent(roundPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)))
                        .addContainerGap(368, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(kButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                        .addComponent(notif, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(368, 368, 368))))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(54, 54, 54)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roundPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(roundPanel1Layout.createSequentialGroup()
                                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(50, 50, 50)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roundPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(notif, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton1ActionPerformed

    private void kButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton2ActionPerformed

    private void jd_tgl_masukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jd_tgl_masukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jd_tgl_masukActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dateChooser1.showPopup();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dateChooser2.showPopup();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void kButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton2MouseClicked
        // TODO add your handling code here:
        
        Connection con = DatabaseConnection.getConnection();

        // get ID Penghuni
        Item scNama = (Item) cb_nama.getSelectedItem();
        int id_penghuni = scNama.getId();
        
        // get ID Kamar
        Item scKamar = (Item) cb_kamar.getSelectedItem();
        int id_kamar = scKamar.getId();
        
        // calendar
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String lama = tf_lamatinggal.getText();
        boolean isLamaTinggalInt = isLamaTinggalInt(lama);

        if(lama.equals("")) {
            notif.setText("Gagal Input Data: Data tidak lengkap!!");
            notif.setForeground(Color.red);
        } else if(isLamaTinggalInt==false){
            notif.setText("Gagal Input Data: Format Durasi Tidak Sesuai!");
            notif.setForeground(Color.red);
        } else {
            int lamaTinggal = Integer.parseInt(lama);
            String tglM = jd_tgl_masuk.getText();
            Date tgl_masuk = null;
            Calendar calTambah = Calendar.getInstance();

                try {
                    tgl_masuk = sdf.parse(tglM);
                    calTambah.setTime(tgl_masuk);
                } catch (ParseException ex) {
                    Logger.getLogger(Form_Transaksi.class.getName()).log(Level.SEVERE, null, ex);
                }
            calTambah.add(Calendar.MONTH, lamaTinggal);

            // tgl transaksi
            String tglTrx = tf_tgl_transaksi.getText();

            tf_tgl_keluar.setText(sdf.format(calTambah.getTime()));


            // hargasewa
            String sqlCekHarga = "SELECT hargasewa FROM t_kamar WHERE id_kamar = ?";
            PreparedStatement check;
            int hargaSewa = 0;
                try {
                    check = con.prepareStatement(sqlCekHarga);
                    check.setInt(1, id_kamar);
                    ResultSet resultSet = check.executeQuery();
                    if(resultSet.next()) {
                        hargaSewa = resultSet.getInt("hargasewa");
                    } else {
                        System.out.println("No Data for ID Kamar: " + id_kamar);
                    }

    //                resultSet.close();
    //                check.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Form_Transaksi.class.getName()).log(Level.SEVERE, null, ex);
                }

            int totalBiaya = hargaSewa * lamaTinggal;

            // debug
    //        System.out.println("ID Nama : " + id_penghuni);
    //        System.out.println("ID Nama : " + id_kamar);
    //        System.out.println("Tanggal Masuk: " + sdf.format(tgl_masuk.getTime()));
    //        System.out.println("Lama Tinggal: " + lamaTinggal);
    //        System.out.println("Tanggal Keluar: " + sdf.format(calTambah.getTime()));
    //        System.out.println("Tanggal Transaksi: " + tglTrx);
    //        System.out.println("Total Biaya: "  + totalBiaya);
    //        System.out.println("---------------------- " + currentLogin);
    //        
            String sql = "INSERT INTO `t_penyewaan` (`id_penghuni`, `id_kamar`, `durasi`, `tgl_mulai`, `tgl_selesai`, `totalbiaya`, `tgl_transaksi`, `inputBy`) VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";

            PreparedStatement insertStatement;
            PreparedStatement updateStatement;
                try {
                    insertStatement = con.prepareStatement(sql);
                    insertStatement.setInt(1, id_penghuni);
                    insertStatement.setInt(2, id_kamar);
                    insertStatement.setInt(3, lamaTinggal);
                    insertStatement.setString(4, sdf.format(tgl_masuk.getTime()));
                    insertStatement.setString(5, sdf.format(calTambah.getTime()));
                    insertStatement.setInt(6, totalBiaya);
                    insertStatement.setString(7, tglTrx);
                    insertStatement.setString(8, currentLogin);

                    insertStatement.executeUpdate();

                    String sqlKetersediaan = "UPDATE `t_kamar` SET `ketersediaan` = '0' WHERE `id_kamar` = ?;";
                    updateStatement = con.prepareStatement(sqlKetersediaan);
                    updateStatement.setInt(1, id_kamar);
                    updateStatement.executeUpdate();

                  notif.setText("                                                                                Berhasil Input Data!");
                    notif.setForeground(Color.green);
                    // debug
                    System.out.println("Berhasil Input!");
                } catch (SQLException ex) {
                    Logger.getLogger(Form_Transaksi.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Error Input Data" + ex);
                }
        }

    }//GEN-LAST:event_kButton2MouseClicked

    private void tf_lamatinggalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_lamatinggalFocusLost
        // TODO add your handling code here:
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            int lamaTinggal = Integer.parseInt(tf_lamatinggal.getText());
            String tglM = jd_tgl_masuk.getText();
            Date tgl_masuk = null;
            Calendar calTambah = Calendar.getInstance();

                try {
                    tgl_masuk = sdf.parse(tglM);
                    calTambah.setTime(tgl_masuk);
                } catch (ParseException ex) {
                    Logger.getLogger(Form_Transaksi.class.getName()).log(Level.SEVERE, null, ex);
                }
            calTambah.add(Calendar.MONTH, lamaTinggal);

            // tgl transaksi
            String tglTrx = tf_tgl_transaksi.getText();

            tf_tgl_keluar.setText(sdf.format(calTambah.getTime()));
        } catch (Exception err) {
            System.out.println(err);
        }
    }//GEN-LAST:event_tf_lamatinggalFocusLost

    private void kButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kButton1MouseClicked
        // TODO add your handling code here:
        jd_tgl_masuk.setText("");
        tf_lamatinggal.setText("");
        tf_tgl_keluar.setText("");
    }//GEN-LAST:event_kButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rentease.combobox.Combobox cb_kamar;
    private com.rentease.combobox.Combobox cb_nama;
    private com.rentease.datechooser.DateChooser dateChooser1;
    private com.rentease.datechooser.DateChooser dateChooser2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jd_tgl_masuk;
    private com.k33ptoo.components.KButton kButton1;
    private com.k33ptoo.components.KButton kButton2;
    private javax.swing.JLabel notif;
    private com.rentease.swing.RoundPanel roundPanel1;
    private com.rentease.swing.RoundPanel roundPanel4;
    private com.rentease.swing.RoundPanel roundPanel5;
    private com.rentease.swing.RoundPanel roundPanel6;
    private com.rentease.swing.RoundPanel roundPanel7;
    private com.rentease.swing.RoundPanel roundPanel8;
    private com.rentease.swing.RoundPanel roundPanel9;
    private javax.swing.JTextField tf_lamatinggal;
    private javax.swing.JTextField tf_tgl_keluar;
    private javax.swing.JTextField tf_tgl_transaksi;
    // End of variables declaration//GEN-END:variables
}
