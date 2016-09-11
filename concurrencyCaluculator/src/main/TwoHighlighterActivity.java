package main;

public class TwoHighlighterActivity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConcurrentCaluculator caluculator = new ConcurrentCaluculator(2);
		
		caluculator.startHighlighters();
		
		while(true){
			caluculator.highlightNext(0);
			caluculator.highlightNext(1);
			try {Thread.sleep(2000);} catch (InterruptedException e){}
		}
	}

}
