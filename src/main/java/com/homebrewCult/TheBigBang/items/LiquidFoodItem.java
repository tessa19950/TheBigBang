package com.homebrewCult.TheBigBang.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;

public class LiquidFoodItem extends Item {

	public LiquidFoodItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}
}
