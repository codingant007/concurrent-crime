package main;

import java.util.ArrayList;
import java.util.List;

import legacy.fusers.AdditionFuser;
import legacy.fusers.AveragingFuser;
import legacy.fusers.Fuser;
import legacy.fusers.MultiplicationFuser;
import legacy.processor.Buffer;
import legacy.processor.PreProcessor;
import legacy.threads.BufferReader;
import legacy.threads.BufferWriter;
import legacy.threads.Fusion;

public class LegacyConstructsApp {

	public static final int NO_OF_SENSORS = 10;
	
	public final static List<Fuser> fusers = new ArrayList<>();
	public final static PreProcessor preProcessor = new PreProcessor(fusers);
	
	
	public static void main(String[] args) {
		fusers.add(new AdditionFuser());
		fusers.add(new MultiplicationFuser());
		fusers.add(new AveragingFuser());
		
		// Start threads for each type of fusion
		for(Fuser fuser : fusers){
			new Thread(new Fusion(fuser)).start();
		}
		
		// Start threads for pushing and popping from the queue buffer
		// One per sensor
		for(int i=0;i<NO_OF_SENSORS;i++){
			Buffer buffer = new Buffer();
			
			new Thread(new BufferWriter(buffer)).start();
			new Thread(new BufferReader(buffer, preProcessor)).start();
		}
	}

}
