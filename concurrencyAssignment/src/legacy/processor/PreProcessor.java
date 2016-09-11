package legacy.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import util.BinaryStringParser;


import legacy.fusers.Fuser;
import legacy.threads.MergeSortTask;
import main.LegacyConstructsApp;

public class PreProcessor {

	private List<String> data = new ArrayList<>();
	
	private List<Fuser> fusers;
	
	public PreProcessor(List<Fuser> fusers){
		this.fusers = fusers;
	}
	
	public synchronized void submitData(String s){
		data.add(s);
		if(data.size() < LegacyConstructsApp.NO_OF_SENSORS){
			try{ wait(); }catch(Exception ex){}
		}
		else{
			//System.out.println("prepro -> " + data);
			performMergeSort();
			submitProcessedData();
			notifyAll();
		}
	}
	
	private void performMergeSort(){
		List<Integer> list = new ArrayList<>();
		for(String s : data){
			list.add(Integer.parseInt(s,2));
		}
		MergeSortTask task = new MergeSortTask(list);
		ForkJoinPool fjp = new ForkJoinPool(5);
		List<Integer> sorted = fjp.invoke(task);
		System.out.println("Sorted -> " + sorted);
	}
	
	private void submitProcessedData(){
		List<Integer> processedData = processData();
		System.out.println(processedData);
		for(Fuser fuser : fusers){
			fuser.submitData(processedData);
		}
		data.clear();
	}
	
	private List<Integer> processData(){
		List<Integer> processedData = new ArrayList<>();
		for(String s : data){
			processedData.add(BinaryStringParser.getIntegerValue(s));
		}
		return processedData;
	}
}