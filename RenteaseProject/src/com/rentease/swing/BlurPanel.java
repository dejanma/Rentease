package com.rentease.swing;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class BlurPanel extends JPanel {

    public BlurPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Set alpha composite to create a transparent effect
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f)); // Adjust the alpha value (0.0f - 1.0f) for the desired transparency

        // Apply anti-aliasing and fill the rounded rectangle
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        g2d.dispose();
    }
}
