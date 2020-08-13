package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class TobiEntity extends ThrowingStarEntity {

	public TobiEntity(EntityType<? extends Entity> type, World worldIn) {
		super(ModEntities.TOBI, worldIn);
	}

	public TobiEntity(World worldIn, double x, double y, double z) {
		super(ModEntities.TOBI, worldIn, x, y, z);
	}

	public TobiEntity(World worldIn, LivingEntity shooter) {
		super(ModEntities.TOBI, worldIn, shooter);
	}
	
	public TobiEntity(World worldIn) {
		super(ModEntities.TOBI, worldIn);
	}
	
	@Override
	Item getThrowingStarAmmoItem() {
		return ModItems.TOBI;
	}

	@Override
	Item getThrowingStarProjectileItem() {
		return ModItems.TOBI_PROJECTILE;
	}

	@Override
	EntityType<?> getThrowingStarEntityType() {
		return ModEntities.TOBI;
	}

}
