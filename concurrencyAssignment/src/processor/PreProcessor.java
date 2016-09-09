package processor;

import java.util.ArrayList;
import java.util.List;

import util.BinaryStringParser;

import Fusers.Fuser;

import main.App;

public class PreProcessor {

	private List<String> data = new ArrayList<>();
	
	private List<Fuser> fusers;
	
	public PreProcessor(List<Fuser> fusers){
		this.fusers = fusers;
	}
	
	public synchronized void submitData(String s){
		data.add(s);
		if(data.size() < App.NO_OF_SENSORS){
			try{ wait(); }catch(Exception ex){}
		}
		else{
			//System.out.println("prepro -> " + data);
			submitProcessedData();
			notifyAll();
		}
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