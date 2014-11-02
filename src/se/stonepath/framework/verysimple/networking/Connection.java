package se.stonepath.framework.verysimple.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import se.stonepath.framework.verysimple.networking.data.Packet;
import se.stonepath.framework.verysimple.networking.exception.ServerException;


public class Connection {
	
	public static Packet sendPacket(String host, int port, Packet packet) throws Exception {
		
		
		try {
			Socket socket = new Socket(host, port);
			SimpleStream simpleStream = new SimpleStream(socket.getInetAddress().toString(),socket.getInputStream(), socket.getOutputStream());
			
			
			simpleStream.sendPacket(packet);
			
			Packet respondPacket =  simpleStream.receivePacket();
			
			if(respondPacket.getContent()[0] instanceof Exception){
				socket.close();
				throw (Exception)respondPacket.getContent()[0];
			}
			
			simpleStream.close();
			socket.close();
			
			return respondPacket;
			
		} catch (UnknownHostException e) {
			throw new ServerException("Host not found (" + e.getMessage() + ")");
		} catch (IOException e) {
			throw new ServerException("Unable to reach host (" + e.getMessage() + ")");
		} 
	}
}
