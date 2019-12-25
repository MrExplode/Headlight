package me.mrexplode.headlight;

import java.awt.EventQueue;

import ch.bildspur.artnet.ArtNetClient;

public class Main {
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });
        
        //artnet();
    }

    
    private static void artnet() {        
        ArtNetClient artnet = new ArtNetClient();
        artnet.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            artnet.stop();
        }));
        
        System.out.println("Starting artnet...");
        byte[] dmxData = new byte[512];
        
        int value = 0;
        long time = 0;
        boolean var1 = true;
        while (true) {
            if (System.currentTimeMillis() > time + 1) {
                time = System.currentTimeMillis();
                
                dmxData = artnet.readDmxData(0, 1);
                if (value < 255 && var1 == true) {
                    value++;
                } else {
                    var1 = false;
                    if (value > 0) {
                        value--;
                    } else {
                        var1 = true;
                    }
                }
                System.out.println("Broadcasting dmx value " + value);
                dmxData[2] = (byte) 255;
                dmxData[6] = (byte) value;
                artnet.broadcastDmx(0, 11, dmxData);
            }
        }
    }

}
