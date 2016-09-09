package Fusers;

import java.math.BigInteger;

public class AveragingFuser extends Fuser {

	private final BigInteger THRESHOLD = new BigInteger("100"); 
	
	@Override
	protected void fuse() {
		//System.out.println("Averaging fusion for " + data);
		BigInteger sum = new BigInteger("0");
		for(int i : data){
			sum = sum.add(new BigInteger(Integer.toString(i)));
		}
		BigInteger average = sum.divide(new BigInteger(Integer.toString(data.size())));
		if(average.compareTo(THRESHOLD) > 0){
			System.out.println("State detected at Averaging " + average);
		}
	}

}
