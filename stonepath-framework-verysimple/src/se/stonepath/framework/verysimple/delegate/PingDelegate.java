package se.stonepath.framework.verysimple.delegate;


import se.stonepath.framework.verysimple.networking.SimpleStream;
import se.stonepath.framework.verysimple.networking.data.Packet;
import se.stonepath.framework.verysimple.networking.exception.SimpleStreamException;




public class PingDelegate implements Delegate{

	
	public void execute(SimpleStream stream,Object... params) {
		
		try {
			
			
			stream.sendPacket(new Packet("ping"));
		} catch (SimpleStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
