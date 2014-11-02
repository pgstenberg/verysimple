package se.stonepath.framework.verysimple.networking.server;


import se.stonepath.framework.verysimple.networking.SimpleStream;
import se.stonepath.framework.verysimple.networking.data.Packet;
import se.stonepath.framework.verysimple.networking.exception.SimpleStreamException;

public class ConnectionServant implements Runnable{

	
	private SimpleStream stream;
	private ServerMonitor monitor;
	public ConnectionServant(SimpleStream stream,ServerMonitor monitor){
		this.stream = stream;
		this.monitor = monitor;
	}

	public void run() {
		
		try {
			Packet request = (Packet)stream.receivePacket();
			
			monitor.executeDelegate(request.getPacketKey(),stream,request.getContent());
			
		} catch (SimpleStreamException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
	}
}
