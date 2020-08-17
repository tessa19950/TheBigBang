package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.entities.ThrowingStarEntity;
import com.homebrewCult.TheBigBang.entities.TobiEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TobiItem extends ThrowingStarItem {

	public TobiItem(Properties properties) {
		super(properties);
	}

	@Override
	ThrowingStarEntity createThrowingStar(World worldIn, ItemStack stack, LivingEntity shooter) {
		return new TobiEntity(worldIn, shooter);
	}

}
