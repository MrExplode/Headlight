package me.mrexplode.headlight;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Fixture extends JPanel {

    private static final long serialVersionUID = 8899901309750221010L;
    
    //constants
    public double rX;//center, not corner
    public double rY;
    public double rZ;
    
    public float panRange;
    public float tiltRange;
    public float zoomMin;
    public float zoomMax;
    
    public int panChannel;
    public int tiltChannel;
    public int zoomChannel;
    public int dimChannel;
    
    //non-constants
    /**
     * one meter scaled to the current canvas size on X axis
     */
    private double rMetricX;
    /**
     * one meter scaled to the current canvas size on Y axis
     */
    private double rMetricY;
    
    //dmx values
    private int panDMX;
    private int tiltDMX;
    private int dimDMX;
    private int zoomDMX;
    
    /**
     * size of the moving fixture's base plate, in CM
     */
    public int size;
    
    public Fixture(double rX, double rY, double rZ, int size, double rMetricX, double rMetricY) {
        this.setOpaque(false);
        this.rX = rX;
        this.rY = rY;
        this.rZ = rZ;
        this.size = size;
        this.rMetricX = rMetricX;
        this.rMetricY = rMetricY;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        System.out.println("paint: " + this.getX() + " " + this.getY() + " w: " + this.getWidth() + " h " + this.getHeight());
        g.setColor(Color.BLUE);
    }
    
    private double tX(double irlValue) {
        return rMetricX * irlValue;
    }
    
    private double tY(double irlValue) {
        return rMetricY * irlValue;
    }
    
    private int i(double d) {
        return (int) d;
    }

}
