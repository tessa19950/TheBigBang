package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.entities.SteelyEntity;
import com.homebrewCult.TheBigBang.entities.ThrowingStarEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SteelyItem extends ThrowingStarItem {

	public SteelyItem(Properties properties) {
		super(properties);
	}

	@Override
	ThrowingStarEntity createThrowingStar(World worldIn, ItemStack stack, LivingEntity shooter) {
		return new SteelyEntity(worldIn, shooter);
	}

}
