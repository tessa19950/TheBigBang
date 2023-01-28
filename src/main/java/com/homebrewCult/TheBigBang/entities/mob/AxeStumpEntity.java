package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class AxeStumpEntity extends AbstractStumpEntity {	
	public AxeStumpEntity(EntityType<? extends AbstractStumpEntity> type, World worldIn) {
		super(ModEntities.AXE_STUMP_ENTITY, worldIn);
	}
}
