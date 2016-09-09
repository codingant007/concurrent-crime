package main;

import java.util.ArrayList;
import java.util.List;

import processor.Buffer;
import processor.PreProcessor;

import Fusers.AdditionFuser;
import Fusers.AveragingFuser;
import Fusers.Fuser;
import Fusers.MultiplicationFuser;
import threads.BufferReader;
import threads.BufferWriter;
import threads.Fusion;


public class App {

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
