package se.stonepath.framework.verysimple.networking.server;


import se.stonepath.framework.verysimple.networking.SimpleStream;
import se.stonepath.framework.verysimple.networking.exception.ServerException;
import se.stonepath.framework.verysimple.networking.server.ServerMonitor.Status;

public class ConnectionListener implements Runnable{

	private ServerMonitor monitor;
	private Server server;
	public ConnectionListener(ServerMonitor monitor,Server server){
		this.monitor = monitor;
		this.server = server;
	}
	
	public void run() {
		while(monitor.getStatus() != Status.Aborted){
			
			try {
				SimpleStream client = server.acceptClient();
				
				
				
				Thread servantThread = new Thread(new ConnectionServant(client,monitor));
				servantThread.start();
				
				
				
				
			} catch (ServerException e) {
				//Probably stopped.
			} 
		}
	}

}
