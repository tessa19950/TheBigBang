package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

public class GreenSnailEntity extends AbstractSnailEntity {	
	public GreenSnailEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super((EntityType<? extends AnimalEntity>) ModEntities.GREEN_SNAIL_ENTITY, worldIn);
	}

	@Override
	public GreenSnailEntity createChild(AgeableEntity ageable) {		
		return (GreenSnailEntity)ModEntities.GREEN_SNAIL_ENTITY.create(this.world);
	}
}
