package legacy.fusers;

import java.math.BigInteger;

public class MultiplicationFuser extends Fuser{
	
	private final BigInteger THRESHOLD = new BigInteger("100000"); 

	@Override
	protected void fuse() {
		//System.out.println("Multiplication fusion for " + data);
		BigInteger product = new BigInteger("1");
		for(int i : data){
			if(product.compareTo(THRESHOLD) > 0){
				System.out.println("State detected in Multiplication " + product);
				break;
			}
			product = product.multiply(new BigInteger(Integer.toString(i)));
		}
	}
}
