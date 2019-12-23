package me.mrexplode.headlight;

import java.awt.EventQueue;

import ch.bildspur.artnet.ArtNetClient;
import ch.bildspur.artnet.DmxUniverseConfig;

public class Main {
    
    public static void main(String[] args) {
        //EventQueue.invokeLater(() -> {
        //    MainGUI gui = new MainGUI();
        //    gui.setVisible(true);
        //});
        
        artnet();
    }

    
    private static void artnet() {        
        ArtNetClient artnet = new ArtNetClient();
        artnet.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            artnet.stop();
        }));
        
        System.out.println("Starting artnet...");
        byte[] dmxData = new byte[512];
        dmxData[9] = 0;
        
        long time = 0;
        boolean var1 = true;
        while (true) {
            if (System.currentTimeMillis() > time + 100) {
                time = System.currentTimeMillis();
                
                if (dmxData[9] < 255 && var1 == true) {
                    dmxData[9]++;
                } else {
                    var1 = false;
                    if (dmxData[9] > 0) {
                        dmxData[9]--;
                    } else {
                        var1 = true;
                    }
                }
                //System.out.println("Broadcasting dmx value " + dmxData[9]);
                
                DmxUniverseConfig conf = new DmxUniverseConfig();
                //artnet.broadcastDmx(0, 1, dmxData);
                artnet.readDmxData(0, 1);
            }
        }
    }

}
