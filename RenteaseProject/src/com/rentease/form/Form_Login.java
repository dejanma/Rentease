package com.rentease.form;

public class Form_Login extends javax.swing.JPanel {
    public Form_Login() {
        initComponents();   
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        roundPanel4 = new com.rentease.swing.RoundPanel();
        jLabel9 = new javax.swing.JLabel();
        roundPanel1 = new com.rentease.swing.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        roundPanel2 = new com.rentease.swing.RoundPanel();
        fr_username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        roundPanel3 = new com.rentease.swing.RoundPanel();
        fr_password = new javax.swing.JPasswordField();
        masukB = new com.k33ptoo.components.KButton();
        ubahL = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        daftarL = new javax.swing.JLabel();
        notif = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        setBackground(new java.awt.Color(36, 37, 41));
        setOpaque(false);

        roundPanel4.setBackground(new java.awt.Color(36, 37, 41));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rentease/icon/login.png"))); // NOI18N

        roundPanel1.setBackground(new java.awt.Color(55, 56, 63));

        jLabel1.setBackground(new java.awt.Color(36, 37, 41));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Selamat Datang");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama Pengguna");

        roundPanel2.setBackground(new java.awt.Color(36, 37, 41));

        fr_username.setBackground(new java.awt.Color(36, 37, 41));
        fr_username.setForeground(new java.awt.Color(255, 255, 255));
        fr_username.setBorder(null);
        fr_username.setCaretColor(new java.awt.Color(255, 255, 255));
        fr_username.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(fr_username, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fr_username, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kata Sandi");

        roundPanel3.setBackground(new java.awt.Color(36, 37, 41));

        fr_password.setBackground(new java.awt.Color(36, 37, 41));
        fr_password.setForeground(new java.awt.Color(255, 255, 255));
        fr_password.setBorder(null);
        fr_password.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel3Layout = new javax.swing.GroupLayout(roundPanel3);
        roundPanel3.setLayout(roundPanel3Layout);
        roundPanel3Layout.setHorizontalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(fr_password, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel3Layout.setVerticalGroup(
            roundPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fr_password, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        masukB.setBackground(new java.awt.Color(36, 37, 41));
        masukB.setForeground(new java.awt.Color(0, 0, 0));
        masukB.setText("Masuk");
        masukB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        masukB.setkAllowGradient(false);
        masukB.setkBackGroundColor(new java.awt.Color(255, 242, 122));
        masukB.setkBorderRadius(20);
        masukB.setkForeGround(new java.awt.Color(0, 0, 0));
        masukB.setkHoverForeGround(new java.awt.Color(0, 0, 0));
        masukB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masukBActionPerformed(evt);
            }
        });

        ubahL.setForeground(new java.awt.Color(255, 242, 122));
        ubahL.setText("Lupa Sandi?");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Belum memiliki akun ?");

        daftarL.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        daftarL.setForeground(new java.awt.Color(255, 242, 122));
        daftarL.setText("Mendaftar");
        daftarL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        daftarL.setName(""); // NOI18N
        daftarL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                daftarLMousePressed(evt);
            }
        });

        notif.setBackground(new java.awt.Color(55, 56, 63));
        notif.setForeground(new java.awt.Color(55, 56, 63));
        notif.setText("Nama Penggguna atau Kata Sandi salah!");

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(roundPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ubahL)
                        .addComponent(masukB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(notif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(daftarL)
                .addGap(100, 100, 100))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(53, 53, 53)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roundPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ubahL)
                .addGap(13, 13, 13)
                .addComponent(notif)
                .addGap(18, 18, 18)
                .addComponent(masukB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(daftarL))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/rentease/icon/Close Window_1.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(roundPanel4);
        roundPanel4.setLayout(roundPanel4Layout);
        roundPanel4Layout.setHorizontalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        roundPanel4Layout.setVerticalGroup(
            roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(roundPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(96, 96, 96)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void masukBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masukBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_masukBActionPerformed

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel7MousePressed

    private void daftarLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftarLMousePressed
    
      

    }//GEN-LAST:event_daftarLMousePressed
    public javax.swing.JLabel getDaftarLabel() {
        return daftarL;
    }
    public javax.swing.JLabel getUbahLabel() {
        return ubahL;
    }
    public javax.swing.JButton getMasukButton() {
        return masukB;
    }
    public javax.swing.JLabel setNotif() {
        return notif;
    }
    public String getUsername()
    {
        return fr_username.getText();
    }
    public char[] getPassword()
    {
        return fr_password.getPassword();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel daftarL;
    private javax.swing.JPasswordField fr_password;
    private javax.swing.JTextField fr_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.k33ptoo.components.KButton masukB;
    private javax.swing.JLabel notif;
    private com.rentease.swing.RoundPanel roundPanel1;
    private com.rentease.swing.RoundPanel roundPanel2;
    private com.rentease.swing.RoundPanel roundPanel3;
    private com.rentease.swing.RoundPanel roundPanel4;
    private javax.swing.JLabel ubahL;
    // End of variables declaration//GEN-END:variables
}
