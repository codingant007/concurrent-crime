package modern.fusers;

import java.math.BigInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AdditionFuser extends Fuser {

	private final BigInteger THRESHOLD = new BigInteger("10000"); 
	
	private BigInteger sum = BigInteger.ZERO;

	private ReentrantLock mutex = new ReentrantLock(true);

	@Override
	public void fuse(int data) {
		mutex.lock();
		try{
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
			if(sum.compareTo(THRESHOLD) > 0){
				System.out.println("State detected in Addition " + sum);
			}
			sum = BigInteger.ZERO;
		}
		finally{
			mutex.unlock();
		}
	}
}
