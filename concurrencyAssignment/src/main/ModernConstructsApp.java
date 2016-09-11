package main;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import modern.fusers.AdditionFuser;
import modern.fusers.AveragingFuser;
import modern.fusers.Fuser;
import modern.fusers.MultiplicationFuser;
import modern.processor.PreProcessor;
import modern.threads.BufferReader;
import modern.threads.BufferWriter;

public class ModernConstructsApp {

	public final static int NO_OF_SENSORS = 10;
	
	private final static ArrayList<Fuser> fusers = new ArrayList<>();
	private static ExecutorService executorService = Executors.newFixedThreadPool(30);
	public final static PreProcessor preProcessor = new PreProcessor(fusers,executorService);
	
	public static void main(String[] args) {
		fusers.add(new AdditionFuser());
		fusers.add(new MultiplicationFuser());
		fusers.add(new AveragingFuser());
		
		for(int i=0; i<NO_OF_SENSORS; i++){
			BlockingQueue<String> buffer = new ArrayBlockingQueue<>(100);
			executorService.execute(new BufferWriter(buffer));
			executorService.execute(new BufferReader(buffer,preProcessor));
		}
	}

}
