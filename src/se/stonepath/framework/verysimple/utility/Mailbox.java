package se.stonepath.framework.verysimple.utility;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Mailbox<T> {

	

	private ConcurrentLinkedQueue<T> queue;
	
	public Mailbox(){
		this.queue = new ConcurrentLinkedQueue<T>();
	}
	
	public synchronized void add(T object){
		queue.add(object);
		notifyAll();
	}
	
	public synchronized T poll() throws InterruptedException{
		while(queue.isEmpty())
			wait();
		
		return queue.poll();
	}
}
