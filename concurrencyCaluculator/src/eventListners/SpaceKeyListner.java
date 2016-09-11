package eventListners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.ConcurrentCaluculator;

public class SpaceKeyListner implements KeyListener{
	private ConcurrentCaluculator caluculator;
	
	public SpaceKeyListner(ConcurrentCaluculator caluculator) {
		this.caluculator = caluculator;
	}

	    @Override
	    public void keyTyped(KeyEvent ke) {
	        if(ke.getKeyCode() == KeyEvent.VK_SPACE){
	        }
	    }

	    @Override
	    public void keyPressed(KeyEvent ke) {
	        if(ke.getKeyCode() == KeyEvent.VK_SPACE){
	        	char c = caluculator.getHighlightedButtonText(1).charAt(0);
	        	System.out.println(c);
	        	caluculator.updateText(c);
	        }
	    }

	    @Override
	    public void keyReleased(KeyEvent ke) {
	        if(ke.getKeyCode() == KeyEvent.VK_SPACE){
	        }
	    }
	

}
