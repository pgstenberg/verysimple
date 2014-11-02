package se.stonepath.framework.verysimple.delegate;

import java.util.HashMap;

import se.stonepath.framework.verysimple.networking.SimpleStream;

public class DelegateHandler {
	
	private HashMap<String,Delegate> delegates;
	public DelegateHandler(){
		this.delegates = new HashMap<String, Delegate>();
		
		addDelegate("ping", new PingDelegate());
	}
	
	
	public void addDelegate(String key,Delegate delegate){
		delegates.put(key, delegate);
	}
	
	public void callDelegate(String key,SimpleStream stream, Object... params) throws Exception{
		if(delegates.containsKey(key))
			delegates.get(key).execute(stream,params);
		else
			throw new Exception("Delegate with key " + key + " was not found!");
	}
}
