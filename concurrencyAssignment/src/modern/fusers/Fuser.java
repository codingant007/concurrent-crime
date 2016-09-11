package modern.fusers;

public abstract class Fuser {

	public abstract void fuse(int data);

	public void sendFusionComplete(){
		clearData();
	}

	protected abstract void clearData();
}
