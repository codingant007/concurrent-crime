package threads;

import processor.Buffer;
import util.RandomBinaryGenerator;

public class BufferWriter implements Runnable{

	private Buffer buffer;
	
	public BufferWriter(Buffer buffer){
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while(true){
			//System.out.println("BufferWriter Thread: buffer.push...");
			try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);} 
			buffer.push(RandomBinaryGenerator.getRandom8BittBinary());
		}
	}
	

}
