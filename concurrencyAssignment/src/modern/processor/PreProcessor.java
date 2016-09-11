package modern.processor;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

import util.BinaryStringParser;

import main.ModernConstructsApp;
import modern.fusers.Fuser;
import modern.threads.SendFusionCompleteSignal;
import modern.threads.SubmitForFusion;

public class PreProcessor {

	private int counter = 0;
	private BlockingQueue<Integer> data = new ArrayBlockingQueue<>(30);
	
	private List<Fuser> fusers;
	private ExecutorService executorService;
	
	private ReentrantLock updateDataLock = new ReentrantLock(true);
	
	private CountDownLatch countDownLatch = new CountDownLatch(ModernConstructsApp.NO_OF_SENSORS);
	
	public PreProcessor(List<Fuser> fusers, ExecutorService executorService){
		this.fusers = fusers;
		this.executorService = executorService;
	}
	
	public void submitData(String s){
		updateData(s);
		for(Fuser fuser : fusers){
			executorService.execute(new SubmitForFusion(fuser, BinaryStringParser.getIntegerValue(s)));
		}
		countDownLatch.countDown();
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {e.printStackTrace();}
		sendSignal();
	}
	
	public void updateData(String s){
		updateDataLock.lock();
		try{
			data.add(Integer.parseInt(s, 2));
			counter++;
		}finally { updateDataLock.unlock();}
	}
	
	public void sendSignal(){
		updateDataLock.lock();
		try{
			if(counter == 10){
				System.out.println(data);
				for(Fuser fuser : fusers){
					executorService.execute(new SendFusionCompleteSignal(fuser));
				}
				data.clear();
				counter = 0;
			}
		} finally {updateDataLock.unlock();}
	}
	
	
	/*
	public void submitData(String s){
		updateData(s);
		for(Fuser fuser : fusers){
			executorService.execute(new SubmitForFusion(fuser, BinaryStringParser.getIntegerValue(s)));
		}
		if(counter < ModernConstructsApp.NO_OF_SENSORS){
			try {dataCountSemaphore.acquire();} catch(InterruptedException ie){ie.printStackTrace();}
		}
		else{
			System.out.println("data -> " + data + " count -> " + counter);
			for(Fuser fuser : fusers){
				executorService.execute(new SendFusionCompleteSignal(fuser));
			}
			clearData();
			System.out.println("lockscountbfr " + dataCountSemaphore.availablePermits());
			dataCountSemaphore.release(ModernConstructsApp.NO_OF_SENSORS-1);
			System.out.println("lockscountaft -> " + dataCountSemaphore.availablePermits());
		}
	}
	*/
	
	/*
	private void clearData(){
		updateDataLock.lock();
		try{
			counter = 0;
			data.clear();
		}
		finally {updateDataLock.unlock();}
	}
	
	private void updateData(String s){
		updateDataLock.lock();
		try{
			counter++;
			data.put(s);
		}catch(InterruptedException ie){}
		finally{
			updateDataLock.unlock();
		}
	}*/
}
