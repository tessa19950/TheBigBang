package com.homebrewCult.TheBigBang.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GrassColors;

public class GolemStoneItem extends BlockItem implements IItemColor {

	public GolemStoneItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}

	@Override
	public int getColor(ItemStack stack, int p_getColor_2_) {
		return GrassColors.get(0.5D, 1.0D);
	}
	
}
