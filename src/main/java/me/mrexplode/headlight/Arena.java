package me.mrexplode.headlight;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Arena extends JPanel {

    private static final long serialVersionUID = 2122939378017918241L;
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillOval(0, 0, this.getWidth(), this.getHeight());
    }

}
