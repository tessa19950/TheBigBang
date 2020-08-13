package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class StumpEntity extends AbstractStumpEntity {	
	public StumpEntity(EntityType<? extends AbstractStumpEntity> type, World worldIn) {
		super((EntityType<? extends AbstractStumpEntity>) ModEntities.STUMP_ENTITY, worldIn);
	}
	
	@Override
	public StumpEntity createChild(AgeableEntity ageable) {
		return (StumpEntity)ModEntities.STUMP_ENTITY.create(this.world);
	}
}
