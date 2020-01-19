package me.mrexplode.headlight;

import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ch.bildspur.artnet.ArtNetClient;
import ch.bildspur.artnet.ArtNetException;
import ch.bildspur.artnet.ArtNetServer;
import ch.bildspur.artnet.packets.ArtNetPacket;
import ch.bildspur.artnet.packets.ArtTimePacket;

public class Main {
    
    public static void main(String[] args) throws IOException, ArtNetException, InterruptedException {
        //new ConsoleStarter("headlight").start();
        /*
        EventQueue.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
        });*/
        
        ArtNetServer server = new ArtNetServer();
        server.start();
        
        ArtTimePacket timePacket = new ArtTimePacket();
        timePacket.setHours(0);
        timePacket.setMinutes(0);
        timePacket.setSeconds(0);
        timePacket.setFrames(0);
        timePacket.setFrameType(1);
        byte[] data = timePacket.getData();
        System.out.println("hour: " + timePacket.getHours());
        System.out.println("Minutes: " + timePacket.getMinutes());
        System.out.println("Seconds: " + timePacket.getSeconds());
        System.out.println("Frames: " + timePacket.getFrames());
        
        
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        TimecodeTask timecodeTask = new TimecodeTask();
        timecodeTask.init();
        executor.scheduleAtFixedRate(timecodeTask, 0, 40, TimeUnit.MILLISECONDS);
        
        /*
        long time = System.currentTimeMillis();
        long incTime = 0;
        long time3 = 0;
        boolean running = true;
        
        
        double val = 0;
        while (running) {
        	//increment packet time
        	//val = System.nanoTime() / 40000000;
        	if (System.nanoTime() >= incTime + 40000000) {
        		incTime = System.nanoTime();
        		timePacket.increment();
        	}
        	
        	if (System.currentTimeMillis() > time3 + 40) {
        		server.broadcastPacket(timePacket);
        	}
            
        	//logger
            if (System.currentTimeMillis() > time3 + 1000) {
                time3 = System.currentTimeMillis();
                System.out.println("time: " + timePacket.getHours() + "h " + timePacket.getMinutes() + "m " + timePacket.getSeconds() + "s " + timePacket.getFrames() + "f  " + timePacket.encoded);
            }
            
            //2 min shutdown
            if (System.currentTimeMillis() > time + 1000 * 120) {
                running = false;
            }
        }
        */
        server.stop();
        //artnet();
    }
    
    public static void send(ArtNetPacket packet, ArtNetServer server) {
    	server.broadcastPacket(packet);
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
