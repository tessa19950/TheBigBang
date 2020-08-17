package com.homebrewCult.TheBigBang.items;

public class KandayoItem extends GarnierItem {

	public KandayoItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public float getMaxVelocity() {
		return 3f;
	}
}
