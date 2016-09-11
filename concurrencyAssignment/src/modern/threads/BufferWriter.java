package modern.threads;

import java.util.concurrent.BlockingQueue;

import util.RandomBinaryGenerator;

public class BufferWriter implements Runnable {

	private BlockingQueue<String> buffer;
	
	public BufferWriter(BlockingQueue<String> buffer){
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while(true){
			try{
				try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);} 
				buffer.put(RandomBinaryGenerator.getRandom8BittBinary());
			}
			catch(InterruptedException ie){
				System.out.println("Interrupted while execution: " + ie);
			}
		}
	}

	
}
