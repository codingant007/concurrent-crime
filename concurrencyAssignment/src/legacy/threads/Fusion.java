package legacy.threads;

import legacy.fusers.Fuser;

public class Fusion implements Runnable {
	private Fuser fuser;
	
	public Fusion(Fuser fuser){
		this.fuser = fuser;
	}
	
	@Override
	public void run() {
		while(true){
			//System.out.println("Fusion Thread: fuse data...");
			try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}  
			fuser.fuseData();
		}
	}
}
