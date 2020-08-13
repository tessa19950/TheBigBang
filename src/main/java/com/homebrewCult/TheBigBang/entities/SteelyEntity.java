package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SteelyEntity extends ThrowingStarEntity {

	public SteelyEntity(EntityType<? extends Entity> type, World worldIn) {
		super(ModEntities.STEELY, worldIn);
	}

	public SteelyEntity(World worldIn, double x, double y, double z) {
		super(ModEntities.STEELY, worldIn, x, y, z);
	}

	public SteelyEntity(World worldIn, LivingEntity shooter) {
		super(ModEntities.STEELY, worldIn, shooter);
	}
	
	public SteelyEntity(World worldIn) {
		super(ModEntities.STEELY, worldIn);
	}
	
	@Override
	Item getThrowingStarAmmoItem() {
		return ModItems.STEELY;
	}

	@Override
	Item getThrowingStarProjectileItem() {
		return ModItems.STEELY_PROJECTILE;
	}

	@Override
	EntityType<?> getThrowingStarEntityType() {
		return ModEntities.STEELY;
	}

}
