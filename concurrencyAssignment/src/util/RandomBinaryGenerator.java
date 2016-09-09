package util;

public class RandomBinaryGenerator {
	
	private static int i = 0;
	
	public static String getRandomBinary(){
		i++;
		return "" + i;
	}

}
