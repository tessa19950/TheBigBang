package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DarkAxeStumpEntity extends AbstractStumpEntity {	
	public DarkAxeStumpEntity(EntityType<? extends AbstractStumpEntity> type, World worldIn) {
		super(ModEntities.DARK_AXE_STUMP_ENTITY, worldIn);
	}
}
