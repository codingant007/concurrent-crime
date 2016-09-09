package Fusers;

import java.util.List;

import main.App;

public abstract class Fuser {
	
	private List<Integer> data;

	public synchronized void submitData(List<Integer> data){
		this.data = data;
		notify();
	}
	
	public synchronized void fuseData(){
		if(data == null){
			try{ wait(); }catch(Exception ex){}
		}
		System.out.println("Data fused!");
		data = null;
	}
	
	protected abstract void fuse();
}
