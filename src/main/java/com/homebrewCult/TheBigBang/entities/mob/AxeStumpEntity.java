package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class AxeStumpEntity extends AbstractStumpEntity {	
	public AxeStumpEntity(EntityType<? extends AbstractStumpEntity> type, World worldIn) {
		super((EntityType<? extends AbstractStumpEntity>) ModEntities.AXE_STUMP_ENTITY, worldIn);
	}
	
	@Override
	public AxeStumpEntity createChild(AgeableEntity ageable) {
		return (AxeStumpEntity)ModEntities.AXE_STUMP_ENTITY.create(this.world);
	}
}
