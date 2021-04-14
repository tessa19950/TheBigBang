package com.homebrewCult.TheBigBang.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;

public class KandayoItem extends GarnierItem {

	public KandayoItem(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}
	
	@Override
	public float getMaxVelocity() {
		return 3f;
	}
}
