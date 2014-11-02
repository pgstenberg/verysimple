package se.stonepath.framework.verysimple.networking.data;

import java.io.Serializable;


public class Packet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5106307753679970665L;

	private String packetKey;
	private Object[] content;
	
	public Packet(String packetKey){
		this(packetKey,new EmptyContent());
	}
	
	public Packet(String packetKey,Object... content){
		this.packetKey = packetKey;
		this.content = content;
	}
	
	public String getPacketKey(){
		return packetKey;
	}
	
	public Object[] getContent(){
		return content;
	}
	
	
}
