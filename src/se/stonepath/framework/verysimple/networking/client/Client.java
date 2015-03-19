package se.stonepath.framework.verysimple.networking.client;

import java.io.Serializable;

import se.stonepath.framework.verysimple.networking.Connection;
import se.stonepath.framework.verysimple.networking.data.Packet;



public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String host;
	private int port;
	
	public Client(String host,int port){
		this.host = host;
		this.port = port;
	}
	
	
	
	public int ping()  throws Exception{
		long startTime = System.currentTimeMillis();
		Connection.sendPacket(host,port,new Packet("ping"));
		long endTime = System.currentTimeMillis();
		
		return (int)(endTime - startTime);
	}
	
	public Packet sendPacket(Packet packet) throws Exception{
		return Connection.sendPacket(host, port, packet);
	}
	
}
