package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class SubiEntity extends ThrowingStarEntity {

	public SubiEntity(EntityType<? extends Entity> type, World worldIn) {
		super(ModEntities.SUBI, worldIn);
	}

	public SubiEntity(World worldIn, double x, double y, double z) {
		super(ModEntities.SUBI, worldIn, x, y, z);
	}

	public SubiEntity(World worldIn, LivingEntity shooter) {
		super(ModEntities.SUBI, worldIn, shooter);
	}
	
	public SubiEntity(World worldIn) {
		super(ModEntities.SUBI, worldIn);
	}

	@Override
	Item getThrowingStarAmmoItem() {
		return ModItems.SUBI;
	}

	@Override
	Item getThrowingStarProjectileItem() {
		return ModItems.SUBI_PROJECTILE;
	}

	@Override
	EntityType<?> getThrowingStarEntityType() {
		return ModEntities.SUBI;
	}

}
