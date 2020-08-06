package com.homebrewCult.TheBigBang.util;

import java.util.Random;

public class MathUtility {
	
	//Including Max
	public static int intInRange(Random random, int min, int max) {
		return intInRange(random, min, max, false);
	}
	
	public static int intInRange(Random random, int min, int max, boolean inclusive) {
		if(inclusive) {
			return random.nextInt((max - min) + 1) + min;
		} else {
			return random.nextInt((max - min)) + min;
		}
	}
	
	public static double doubleInRange(Random random, double min, double max) {
		return min + (max - min) * random.nextDouble();
	}
	
	public static float floatInRange(Random random, float min, float max) {
		return min + (max - min) * random.nextFloat();
	}
}
