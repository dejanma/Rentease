package com.rentease.component;

import com.rentease.event.EventMenu;
import com.rentease.swing.ButtonMenu;
import com.rentease.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    private EventMenu event;

    public Menu() {
        initComponents();
        setOpaque(false);
        ScrollBarCustom sb = new ScrollBarCustom();
        sb.setForeground(new Color(130, 130, 130, 100));
     
        panelMenu.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));
    }

    public void initMenu(EventMenu event) {
        this.event = event;
        addMenu(new ImageIcon(getClass().getResource("/com/rentease/icon/logo1.png")), "", 0);
        addEmpty();
        addMenu(new ImageIcon(getClass().getResource("/com/rentease/icon/2.png")), "Kamar", 2);
        addMenu(new ImageIcon(getClass().getResource("/com/rentease/icon/3.png")), "Penghuni", 3);
        addMenu(new ImageIcon(getClass().getResource("/com/rentease/icon/Yandex Money_3.png")), "Transaksi", 1);
        addMenu(new ImageIcon(getClass().getResource("/com/rentease/icon/4.png")), "Riwayat", 4);
        addEmpty();
        addMenu(new ImageIcon(getClass().getResource("/com/rentease/icon/logout.png")), "Keluar", 5);
    }

    private void addEmpty() {
        panelMenu.add(new JLabel(), "push");
    }

    private void addMenu(Icon icon, String text, int index) {
        ButtonMenu menu = new ButtonMenu();
        menu.setIcon(icon);
        menu.setText("  " + text);
        panelMenu.add(menu);
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.selected(index);
                setSelected(menu);
            }
        });
    }

    private void setSelected(ButtonMenu menu) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof ButtonMenu) {
                ButtonMenu b = (ButtonMenu) com;
                b.setSelected(false);
            }
        }
        menu.setSelected(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new javax.swing.JPanel();

        panelMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(46, 47, 51)));
        panelMenu.setOpaque(false);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
