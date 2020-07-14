package com.homebrewCult.TheBigBang.util;

import java.util.Random;

public class MathUtility {
	
	//Including Max
	public static int randomIntegerInRange(int min, int max) {
		return randomIntegerInRange(min, max, false);
	}
	
	public static int randomIntegerInRange(int min, int max, boolean inclusive) {
		Random r = new Random();
		if(inclusive) {
			return r.nextInt((max - min) + 1) + min;
		} else {
			return r.nextInt((max - min)) + min;
		}
	}
	
	public static double randomDoubleInRange(double min, double max) {
		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}
}
