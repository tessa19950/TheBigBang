package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class IlbiEntity extends ThrowingStarEntity {

	public IlbiEntity(EntityType<? extends Entity> type, World worldIn) {
		super(ModEntities.ILBI, worldIn);
	}

	public IlbiEntity(World worldIn, double x, double y, double z) {
		super(ModEntities.ILBI, worldIn, x, y, z);
	}

	public IlbiEntity(World worldIn, LivingEntity shooter) {
		super(ModEntities.ILBI, worldIn, shooter);
	}
	
	public IlbiEntity(World worldIn) {
		super(ModEntities.ILBI, worldIn);
	}
	
	@Override
	Item getThrowingStarAmmoItem() {
		return ModItems.ILBI;
	}

	@Override
	Item getThrowingStarProjectileItem() {
		return ModItems.ILBI_PROJECTILE;
	}

	@Override
	EntityType<?> getThrowingStarEntityType() {
		return ModEntities.ILBI;
	}

}
