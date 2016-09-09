package util;

import java.util.Random;

public class RandomBinaryGenerator {
	
	public static int getRandomInteger(int min, int max){
		Random rn = new Random();
		return min + rn.nextInt(max-min+1);
	}
	
	public static String getRandom8BittBinary(){
		// 00000000 to 11111111
		// ie 0 to 511
		return Integer.toBinaryString(getRandomInteger(0,511));
	}
	


}
