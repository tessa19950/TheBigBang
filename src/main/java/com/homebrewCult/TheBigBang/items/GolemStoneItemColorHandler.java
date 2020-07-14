package com.homebrewCult.TheBigBang.items;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GrassColors;

public class GolemStoneItemColorHandler implements IItemColor {

	public static final IItemColor INSTANCE = new GolemStoneItemColorHandler();

	@Override
	public int getColor(ItemStack item, int color) {
		return GrassColors.get(0.5D, 1.0D);
	}
	
}
