package modern.threads;

import modern.fusers.Fuser;

public class SubmitForFusion implements Runnable {

	private Fuser fuser;
	private int data;
	
	public SubmitForFusion(Fuser fuser, int data){
		this.fuser = fuser;
		this.data = data;
	}
	
	@Override
	public void run() {
		fuser.fuse(data);
	}
	
}
