package com.homebrewCult.TheBigBang.util;

import java.util.Random;

public class MathUtility {
	
	//Including Max
	public static int randomIntegerInRange(Random random, int min, int max) {
		return randomIntegerInRange(random, min, max, false);
	}
	
	public static int randomIntegerInRange(Random random, int min, int max, boolean inclusive) {
		if(inclusive) {
			return random.nextInt((max - min) + 1) + min;
		} else {
			return random.nextInt((max - min)) + min;
		}
	}
	
	public static double randomDoubleInRange(Random random, double min, double max) {
		return min + (max - min) * random.nextDouble();
	}
}
