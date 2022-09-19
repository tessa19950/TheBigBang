package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

public class KandayoItem extends GarnierItem {

	public KandayoItem(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}
	
	@Override
	public float getMaxVelocity() {
		return 3f;
	}

	@Override
	public SoundEvent getThrowSound() {
		return ModSounds.LUCKY_SEVEN_USE;
	}
}
