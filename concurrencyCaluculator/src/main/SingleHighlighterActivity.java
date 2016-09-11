package main;

public class SingleHighlighterActivity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConcurrentCaluculator caluculator = new ConcurrentCaluculator(1);
		
		caluculator.startHighlighter();
		
		while(true){
			caluculator.highlightNext();
			try {Thread.sleep(1000);} catch (InterruptedException e){}
		}
	}

}
