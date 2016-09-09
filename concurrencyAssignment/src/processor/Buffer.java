package processor;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Buffer {
	
	private Queue<String> queue = new LinkedList<>();
	
	public synchronized void push(String s){
		try{
			queue.add(s);
		}
		catch(Exception e){
			try{ wait(); } catch(InterruptedException ie){}
			queue.add(s);
		}
		notify();
	}
	
	public synchronized String pick(){
		String head;
		try{
			System.out.println("buffer -> " + queue);
			head = queue.remove();
		}
		catch(NoSuchElementException e){
			try{ wait(); } catch(InterruptedException ie){}
			head = queue.remove();
		}
		notify();
		return head;
	}
}
