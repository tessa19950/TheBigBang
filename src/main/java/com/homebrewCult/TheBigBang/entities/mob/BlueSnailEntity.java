package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

public class BlueSnailEntity extends AbstractSnailEntity {	
	public BlueSnailEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(ModEntities.BLUE_SNAIL_ENTITY, worldIn);
	}
	
	@Override
	public BlueSnailEntity createChild(AgeableEntity ageable) {		
		return ModEntities.BLUE_SNAIL_ENTITY.create(this.world);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6D);
	}
}
