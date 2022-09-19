package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.entities.BombArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class NishadaItem extends BigBangCrossbowItem {


	public NishadaItem(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}

	@Override
	AbstractArrowEntity getUniqueArrow(World worldIn, LivingEntity shooter) {
		return new BombArrowEntity(worldIn, shooter);
	}
}
