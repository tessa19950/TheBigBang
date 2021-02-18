package com.homebrewCult.TheBigBang.items;

import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;

public class NishadaItem extends CrossbowItem {

	public NishadaItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean isCrossbow(ItemStack stack) {
		return true;
	}
}
