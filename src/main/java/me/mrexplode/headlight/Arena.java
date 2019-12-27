package me.mrexplode.headlight;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class Arena extends JPanel {

    private static final long serialVersionUID = 2122939378017918241L;
    
    public double rMetricX = 0;
    public double rMetricY = 0;
    
    private double IWidth;
    private double ILength;
    
    private List<Fixture> fixtures;
    
    public Arena() {
        this(10, 10);
    }
    
    public Arena(double width, double length) {
        this.IWidth = width;
        this.ILength = length;
        this.setBackground(Color.WHITE);
        this.fixtures = new ArrayList<Fixture>();
    }
    
    public void addFixture(Fixture f) {
        fixtures.add(f);
        this.add(f);
        f.setVisible(true);
        this.repaint();
    }

    public double getIWidth() {
        return this.IWidth;
    }
    
    public void setWidth(double width) {
        this.IWidth = width;
        this.repaint();
    }
    
    public double getILength() {
        return this.ILength;
    }
    
    public void setLength(double length) {
        this.ILength = length;
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        
        double segment1 = this.getWidth() / IWidth;
        rMetricX = segment1;
        for (int i = 0; i < IWidth; i++) {
            g.drawLine((int) (i * segment1), 0, (int) (i * segment1), this.getHeight());
        }
        
        double segment2 = this.getHeight() / ILength;
        rMetricY = segment2;
        for (int i = 0; i < ILength; i++) {
            g.drawLine(0, (int) (i * segment2), this.getWidth(), (int) (i * segment2));
        }
    }

}
