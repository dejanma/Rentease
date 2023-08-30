package com.rentease.component;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class ControlPanel extends JPanel {

    private JButton exitButton;
    private JButton minimizeButton;

    private int x;
    private int y;
    private int diameter;

    public ControlPanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, (getHeight() - 14) / 2)); // Ubah nilai 14 sesuai ukuran lingkaran

        // Lingkaran merah (Exit)
        exitButton = createButton();
        exitButton.setBackground(new Color(255, 0, 0));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kode untuk aksi tombol exit
                System.exit(0);
            }
        });
        add(exitButton);

        // Lingkaran kuning (Minimize)
        minimizeButton = createButton();
        minimizeButton.setBackground(new Color(255, 255, 0));
        minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kode untuk aksi tombol minimize
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ControlPanel.this);
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        });
        add(minimizeButton);

        // Lingkaran hijau (Maximize)
        
    

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ControlPanel.this);
                frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        });
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(14, 14)); // Ubah ukuran sesuai preferensi Anda
        button.setUI(new BasicButtonUI());
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs.create();

        diameter = Math.min(exitButton.getWidth(), exitButton.getHeight());
        int y = (getHeight() - diameter) / 2;

        // Lingkaran merah (Exit)
        g2d.setColor(exitButton.getBackground());
        g2d.fill(new Ellipse2D.Double(exitButton.getX(), y, diameter, diameter));

        // Lingkaran kuning (Minimize)
        g2d.setColor(minimizeButton.getBackground());
        g2d.fill(new Ellipse2D.Double(minimizeButton.getX(), y, diameter, diameter));

        // Lingkaran hijau (Maximize)
        
        g2d.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        if (super.contains(x, y)) {
            int centerY = (getHeight() - diameter) / 2 + diameter / 2;
            int radius = diameter / 2;
            int exitButtonX = exitButton.getX() + (exitButton.getWidth() - diameter) / 2;
            int minimizeButtonX = minimizeButton.getX() + (minimizeButton.getWidth() - diameter) / 2;
          

            if (isInsideCircle(x, y, exitButtonX, centerY, radius)) {
                return true;
            }
            if (isInsideCircle(x, y, minimizeButtonX, centerY, radius)) {
                return true;
            }
       
        }
        return false;
    }

    private boolean isInsideCircle(int x, int y, int circleX, int circleY, int radius) {
        int dx = x - circleX;
        int dy = y - circleY;
        return dx * dx + dy * dy <= radius * radius;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Control Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 100);
                frame.setUndecorated(true);

                ControlPanel panel = new ControlPanel();
                panel.setBackground(Color.WHITE);

                frame.add(panel);
                frame.setVisible(true);
            }
        });
    }
}
