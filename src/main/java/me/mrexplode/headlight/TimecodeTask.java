package me.mrexplode.headlight;

import java.net.SocketException;
import java.util.TimerTask;

import ch.bildspur.artnet.ArtNetException;
import ch.bildspur.artnet.ArtNetServer;
import ch.bildspur.artnet.packets.ArtTimePacket;

public class TimecodeTask extends TimerTask {
	
	private ArtTimePacket packet;
	private ArtNetServer server;
	private boolean playing = true;
	
	public TimecodeTask() {
		this.packet = new ArtTimePacket();
		this.server = new ArtNetServer();
	}
	
	public void init() throws SocketException, ArtNetException {
		server.start();
		packet.setTime(0, 0, 0, 0);
	}

	public void setTime(int hour, int min, int sec, int frame) {
		packet.setTime(hour, min, sec, frame);
	}
	
	@Override
	public void run() {
		if (playing) {
			packet.increment();
		}
		server.broadcastPacket(packet);
		System.out.println("time: " + packet.getHours() + "h " + packet.getMinutes() + "m " + packet.getSeconds() + "s " + packet.getFrames() + "f  " + packet.encoded);
	}

}
