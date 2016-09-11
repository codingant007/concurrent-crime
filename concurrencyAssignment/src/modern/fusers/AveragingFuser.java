package modern.fusers;

import java.math.BigInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AveragingFuser extends Fuser {

	private final BigInteger THRESHOLD = new BigInteger("100");
	private BigInteger sum = BigInteger.ZERO;
	private int count = 0;
	
	private ReentrantLock mutex = new ReentrantLock();

	@Override
	public void fuse(int data) {
		mutex.lock();
		try{
			count++;
			sum = sum.add(new BigInteger(Integer.toString(data)));
		}
		finally{
			mutex.unlock();
		}
	}

	@Override
	protected void clearData() {
		mutex.lock();
		try{
			BigInteger average = sum.divide(new BigInteger(Integer.toString(count)));
			if(average.compareTo(THRESHOLD) > 0){
				System.out.println("State detected at Average " + average);
			}
			count = 0;
			sum = BigInteger.ZERO;
		}
		catch(ArithmeticException ex){}
		finally{
			mutex.unlock();
		}
	}	
}
