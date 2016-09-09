package Fusers;

import java.util.List;

public abstract class Fuser {
	
	protected List<Integer> data;

	public synchronized void submitData(List<Integer> data){
		this.data = data;
		notify();
	}
	
	public synchronized void fuseData(){
		if(data == null){
			try{ wait(); }catch(Exception ex){}
		}
		fuse();
		data = null;
	}
	
	protected abstract void fuse();
}
