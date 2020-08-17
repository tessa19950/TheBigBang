package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.entities.IlbiEntity;
import com.homebrewCult.TheBigBang.entities.ThrowingStarEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class IlbiItem extends ThrowingStarItem {

	public IlbiItem(Properties properties) {
		super(properties);
	}

	@Override
	ThrowingStarEntity createThrowingStar(World worldIn, ItemStack stack, LivingEntity shooter) {
		return new IlbiEntity(worldIn, shooter);
	}
}
