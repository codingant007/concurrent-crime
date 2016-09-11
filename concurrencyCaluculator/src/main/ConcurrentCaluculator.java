package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import processor.ArithmeticProcessor;

import eventListners.ReturnKeyListner;
import eventListners.SpaceKeyListner;


public class ConcurrentCaluculator {
    // Outermost frame - GridLayout 3x1
    private JFrame mainFrame;
    // Inner components
    private JLabel display;
    private JPanel numberGrid;  // GridLayout 4x3
    private JPanel symbolGrid;  // GridLayout5x1
    
    private List<JButton> numbers = new ArrayList<>(); // Inside numberGrid
    private List<JButton> symbols = new ArrayList<>(); // Inside symbolGrid
    private List<JButton> buttons = new ArrayList<>(); // numbers + symbols
    
    private List<Character> displayText = new ArrayList<>();
    
    private int highlighterCount;
    private List<Integer> highlightedIndices = new ArrayList<>();
    
    
    public ConcurrentCaluculator(int c){
        this.highlighterCount = c;
        for(int i=0;i<highlighterCount;i++){
        	highlightedIndices.add(0);
        }
    	initGUI();
    	addListener(c);
    }
    
    synchronized public String getHighlightedButtonText(int highlighterNumber){
    	if(highlighterNumber == 0){
    		return buttons.get(highlightedIndices.get(0)).getText();
    	}
    	else{
    		return symbols.get(highlightedIndices.get(1)).getText();
    	}
    }
    
    synchronized public void updateText(char c){
    	displayText.add(c);
    	String expr = getStringRepresentation(displayText);
    	display.setText(expr);
    	if(c == '='){
    		getResult(expr.substring(0, expr.length()-1));
    	}
    }
    
    synchronized public void setDisplayText(String s){
    	display.setText(s);
    }
    
    private void getResult(String expr) {
    	ArithmeticProcessor processor = new ArithmeticProcessor(this,expr);
		processor.execute();
    }
    
    private String getStringRepresentation(List<Character> list)
    {    
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }
    
    synchronized public void startHighlighter(){
    	highlightButton(buttons.get(0));
    }
    
    synchronized public void startHighlighters(){
    	highlightButton(numbers.get(0));
    	highlightButton(symbols.get(0));
    }
    
    synchronized public void highlightNext(){
    	int currentIndex = highlightedIndices.get(0);
    	int nextIndex = currentIndex + 1;
    	if(currentIndex == buttons.size()-1){
    		nextIndex = 0;
    	}
    	unHighlightButton(buttons.get(currentIndex));
    	highlightButton(buttons.get(nextIndex));
    	highlightedIndices.set(0, nextIndex);
    }
    
    synchronized public void highlightNext(int highlighterNumber){
    	int currentIndex = highlightedIndices.get(highlighterNumber);
    	List<JButton> list;
    	if(highlighterNumber == 0){
    		list = numbers;
    	}
    	else{
    		list = symbols;
    	}
    	int nextIndex = currentIndex + 1;
    	if(currentIndex == list.size()-1){
    		nextIndex = 0;
    	}
    	unHighlightButton(list.get(currentIndex));
    	highlightButton(list.get(nextIndex));
    	highlightedIndices.set(highlighterNumber, nextIndex);
    }
    
    private void unHighlightButton(JButton button){
    	button.setBackground(null);
    }
    
    private void highlightButton(JButton button){
        button.setBackground(Color.RED);
    }
    
    private void initGUI(){
        prepareOuterFrame();
        
        display = new JLabel("");
        
        initButtons();
        prepareNumberGrid();
        prepareSymbolGrid();
        
        mainFrame.add(display);
        mainFrame.add(numberGrid);
        mainFrame.add(symbolGrid);
        mainFrame.setVisible(true);
        
        makeNonFocusable();
    }
    
    private void addListener(int highlighterCount){
    	if(highlighterCount == 1){
    		mainFrame.addKeyListener(new ReturnKeyListner(this));
    	}
    	else{
    		mainFrame.addKeyListener(new ReturnKeyListner(this));
    		mainFrame.addKeyListener(new SpaceKeyListner(this));
    	}
    }
    
    private void prepareOuterFrame(){
        mainFrame = new JFrame("Test Frame");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3,1));
    }
    
    private void prepareNumberGrid(){
        numberGrid = new JPanel(new GridLayout(4, 3));
        for(JButton button : numbers){
            numberGrid.add(button);
        }
    }
    
    private void prepareSymbolGrid(){
        symbolGrid = new JPanel(new GridLayout(5, 1));
        for(JButton button : symbols){
            symbolGrid.add(button);
        }
    }
    
    private void initButtons(){
        for(int i=0;i<10;i++){
            numbers.add(new JButton(Integer.toString(i)));
        }
        symbols.add(new JButton("+"));
        symbols.add(new JButton("-"));
        symbols.add(new JButton("*"));
        symbols.add(new JButton("/"));
        symbols.add(new JButton("="));
        
        buttons.addAll(numbers);
        buttons.addAll(symbols);
    }
    
    private void makeNonFocusable(){
        for(JButton button : numbers){
            button.setFocusable(false);
        }
        for(JButton button : symbols){
            button.setFocusable(false);
        }
        display.setFocusable(false);
        numberGrid.setFocusable(false);
        symbolGrid.setFocusable(false);
    }
}
