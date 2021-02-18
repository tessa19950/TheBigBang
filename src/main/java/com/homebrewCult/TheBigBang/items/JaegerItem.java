package com.homebrewCult.TheBigBang.items;

import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;

public class JaegerItem extends CrossbowItem
{
	public JaegerItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean isCrossbow(ItemStack stack) {
		return true;
	}

}
