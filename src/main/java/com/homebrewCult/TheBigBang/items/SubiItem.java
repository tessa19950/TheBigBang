package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.entities.SubiEntity;
import com.homebrewCult.TheBigBang.entities.ThrowingStarEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SubiItem extends ThrowingStarItem {

	public SubiItem(Properties properties) {
		super(properties);
	}

	@Override
	public ThrowingStarEntity createThrowingStar(World worldIn, ItemStack stack, LivingEntity shooter) {
		return new SubiEntity(worldIn, shooter);
	}

}
