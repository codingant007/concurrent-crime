package Fusers;

import java.math.BigInteger;

public class AdditionFuser extends Fuser {
	
	private final BigInteger THRESHOLD = new BigInteger("10000"); 
	
	@Override
	protected void fuse() {
		//System.out.println("Addition fusion for " + data);
		BigInteger sum = new BigInteger("0");
		for(int i : data){
			if(sum.compareTo(THRESHOLD) > 0){
				System.out.println("State detected in Addition " + sum);
				break;
			}
			sum = sum.add(new BigInteger(Integer.toString(i)));
		}
	}

}
