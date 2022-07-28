package com.homebrewCult.TheBigBang.items;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GrassColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemColorHandler implements IItemColor {

	public static final IItemColor INSTANCE = new ItemColorHandler();

	@Override
	public int getColor(ItemStack item, int color) {
		return GrassColors.get(0.5D, 1.0D);
	}
	
}
