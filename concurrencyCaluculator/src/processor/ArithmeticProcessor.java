package processor;

import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.SwingWorker;

import main.ConcurrentCaluculator;

public class ArithmeticProcessor extends SwingWorker<Integer,Integer>{
	private ConcurrentCaluculator caluculator;
	private String expression;
	ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

	public ArithmeticProcessor(ConcurrentCaluculator caluculator, String expr){
		this.caluculator = caluculator;
		this.expression = expr;
	}
	
	
	@Override
	protected Integer doInBackground() throws Exception {
		int result = ((Double)engine.eval(expression)).intValue();
		publish(result);
		return 0;
	}
	
	@Override
	protected void process(List<Integer> list){
		caluculator.setDisplayText(expression + "=" + list.get(0));
	}

}
