package se.stonepath.framework.verysimple.networking.server;

import java.util.HashMap;

import se.stonepath.framework.verysimple.delegate.DelegateHandler;
import se.stonepath.framework.verysimple.networking.SimpleStream;

public class ServerMonitor {

	public static enum Status{Pending,Ready,Running,Aborted};
	
	private Status status;
	private HashMap<String,Object> resources;
	private DelegateHandler delegateHandler;
	
	public ServerMonitor(DelegateHandler delegateHandler){
		this.status = Status.Pending;
		this.resources = new HashMap<String, Object>();
		this.delegateHandler = delegateHandler;
	}
	
	public synchronized Status getStatus(){
		return status;
	}
	public synchronized void setStatus(Status newStatus){
		this.status = newStatus;
	}
	
	
	public synchronized void addResource(String resourceKey,Object resource){
		resources.put(resourceKey, resource);
	}
	
	
	public synchronized Object getResource(String resourceKey){
		return resources.get(resourceKey);
	}
	
	public synchronized void executeDelegate(String packetKey,SimpleStream stream, Object[] content) throws Exception{
		delegateHandler.callDelegate(packetKey,stream,content);
	}
	public synchronized void executeDelegate(String packetKey,SimpleStream stream) throws Exception{
		delegateHandler.callDelegate(packetKey,stream);
	}
}
