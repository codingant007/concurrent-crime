package modern.threads;

import modern.fusers.Fuser;

public class SendFusionCompleteSignal implements Runnable {

	private Fuser fuser;
	
	public SendFusionCompleteSignal(Fuser fuser){
		this.fuser = fuser;
	}
	
	@Override
	public void run() {
		fuser.sendFusionComplete();
	}
	
}
