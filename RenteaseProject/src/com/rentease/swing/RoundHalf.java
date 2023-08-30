package com.rentease.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundHalf extends JPanel {

    private int roundness = 15; // Jumlah bulatan sudut

    public RoundHalf() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        int width = getWidth();
        int height = getHeight();
        g2d.fillRoundRect(0, 0, width - roundness, height, roundness, roundness);
        g2d.fillRect(width - roundness, height - roundness, roundness, roundness);

        super.paintChildren(g);
    }
}
