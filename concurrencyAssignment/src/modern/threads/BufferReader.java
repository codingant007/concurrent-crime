package modern.threads;

import java.util.concurrent.BlockingQueue;

import modern.processor.PreProcessor;

public class BufferReader implements Runnable {

	private BlockingQueue<String> buffer;
	private PreProcessor preProcessor;
	
	public BufferReader(BlockingQueue<String> buffer, PreProcessor preProcessor){
		this.buffer = buffer;
		this.preProcessor = preProcessor;
	}
	
	@Override
	public void run() {
		while(true){
			String head = null;
			try{
				head = buffer.take();
			}
			catch(InterruptedException ie){
				System.out.println("Interrupted while execution: " + ie);
			}
			try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);} 
			preProcessor.submitData(head);
		}
	}
}
