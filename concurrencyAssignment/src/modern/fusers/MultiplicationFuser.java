package modern.fusers;

import java.math.BigInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MultiplicationFuser extends Fuser {
	
	private BigInteger THRESHOLD = new BigInteger("100000");
	
	private BigInteger product = BigInteger.ONE;
	
	private ReentrantLock mutex = new ReentrantLock();
	
	@Override
	public void fuse(int data) {
		mutex.lock();
		try{
			product = product.multiply(new BigInteger(Integer.toString(data)));
		}
		finally{
			mutex.unlock();
		}
	}

	@Override
	protected void clearData() {
		mutex.lock();
		try{
			if(product.compareTo(THRESHOLD) > 0){
				System.out.println("State detected in Multiplication " + product);
			}
			product = BigInteger.ONE;
		}
		finally{
			mutex.unlock();
		}
	}
}
