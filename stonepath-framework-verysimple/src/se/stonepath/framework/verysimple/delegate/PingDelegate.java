package se.stonepath.framework.verysimple.delegate;


import se.stonepath.framework.verysimple.networking.SimpleStream;
import se.stonepath.framework.verysimple.networking.data.Packet;
import se.stonepath.framework.verysimple.networking.exception.SimpleStreamException;




public class PingDelegate implements Delegate{

	
	public void execute(SimpleStream stream,Object... params) throws SimpleStreamException {
			stream.sendPacket(new Packet("ping"));

		
	}

}
