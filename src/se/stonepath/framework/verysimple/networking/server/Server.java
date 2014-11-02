package se.stonepath.framework.verysimple.networking.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import se.stonepath.framework.verysimple.delegate.DelegateHandler;
import se.stonepath.framework.verysimple.networking.SimpleStream;
import se.stonepath.framework.verysimple.networking.exception.ServerException;
import se.stonepath.framework.verysimple.networking.server.ServerMonitor.Status;


public class Server {

	public final static int DEFAULT_PORT = 4143;
	
	
	private ServerSocket serverSocket;
	private ServerMonitor monitor;
	
	private int port;
	
	
	
	public Server(DelegateHandler delegateHandler){
		this(DEFAULT_PORT,delegateHandler);
	}
	
	public Server(int port,DelegateHandler delegateHandler){
		this.port = port;
		this.monitor = new ServerMonitor(delegateHandler);
		
	}
	

	
	
	public void initialize() throws ServerException{
		
		if(monitor.getStatus() != Status.Pending)
			throw new ServerException("not pending");
		
		try {
			serverSocket = new ServerSocket(port);
			monitor.setStatus(Status.Ready);
			
		} catch (IOException e) {
			throw new ServerException("init error");
		}
	}
	
	public void start() throws ServerException{
		if(monitor.getStatus() != Status.Ready)
			throw new ServerException("not ready");
		
		monitor.setStatus(Status.Running);
		
		Thread listenerThread = new Thread(new ConnectionListener(monitor, this));
		listenerThread.start();
		
		
	}
	
	public void stop() throws ServerException{
		monitor.setStatus(Status.Aborted);
		try {
			serverSocket.close();
		} catch (IOException e) {
			throw new ServerException("stop");
		}
	}
	
	public SimpleStream acceptClient() throws ServerException{
		
		if(monitor.getStatus() != Status.Running)
			throw new ServerException("Not running");
		
		try {
			
			Socket newSocket = serverSocket.accept();
			
			return new SimpleStream(newSocket.getInetAddress().toString(),newSocket.getInputStream(),newSocket.getOutputStream());
		} catch (IOException e) {
			throw new ServerException("server closed");
		}
		
		
	}
	
	
}
