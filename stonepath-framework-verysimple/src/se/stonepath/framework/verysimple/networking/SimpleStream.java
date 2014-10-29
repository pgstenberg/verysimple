package se.stonepath.framework.verysimple.networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import se.stonepath.framework.verysimple.networking.data.Packet;
import se.stonepath.framework.verysimple.networking.exception.SimpleStreamException;

public class SimpleStream {

	private InputStream input;
	private OutputStream output;

	private String identifier;
	
	public SimpleStream(String identifier,InputStream input,OutputStream output){
		this.identifier = identifier;
		this.input = input;
		this.output = output;
	}
	
	public String getIdentifier(){
		return identifier;
	}
	
	public void close() throws SimpleStreamException{
		
		try {
			output.close();
			input.close();
		} catch (IOException e) {
			throw new SimpleStreamException("Unable to close streams.");
		}
	}
	
	public Packet receivePacket() throws SimpleStreamException{
		try {
			ObjectInputStream objectInput = new ObjectInputStream(input);
			return (Packet)objectInput.readObject();
		} catch (IOException e) {
			throw new SimpleStreamException("Client disconnected");
		} catch (ClassNotFoundException e) {
			throw new SimpleStreamException("Not able to convert object");
		}
	}
	public void sendPacket(Packet packet) throws SimpleStreamException {
		try {
			ObjectOutputStream objectOuput = new ObjectOutputStream(output);
			objectOuput.writeObject(packet);
			objectOuput.flush();
		} catch (IOException e) {
			throw new SimpleStreamException("Client disconnected");
		}
	}
	
	
}
