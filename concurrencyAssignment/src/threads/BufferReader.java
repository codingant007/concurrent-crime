package threads;

import processor.Buffer;
import processor.PreProcessor;

public class BufferReader implements Runnable{
	
	private Buffer buffer;
	private PreProcessor preProcessor;
	
	public BufferReader(Buffer buffer, PreProcessor preProcessor){
		this.buffer = buffer;
		this.preProcessor = preProcessor;
	}

	@Override
	public void run() {
		while(true){
			//System.out.println("BufferReader Thread: buffer.pick...");
			try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);} 
			String head = buffer.pick();
			//System.out.println("BufferReader Thread: preProcessor.submitData...");
			try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);} 
			preProcessor.submitData(head);
		}
	}
	
	
}
