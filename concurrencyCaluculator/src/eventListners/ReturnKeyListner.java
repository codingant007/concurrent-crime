/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventListners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.ConcurrentCaluculator;

/**
 *
 * @author ant
 */
public class ReturnKeyListner implements KeyListener{

	private ConcurrentCaluculator caluculator;
	
	public ReturnKeyListner(ConcurrentCaluculator caluculator) {
		this.caluculator = caluculator;
	}
	
    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
        	char c = caluculator.getHighlightedButtonText(0).charAt(0);
        	System.out.println(c);
        	caluculator.updateText(c);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
        }
    }
}
