package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DarkStumpEntity extends AbstractStumpEntity {	
	public DarkStumpEntity(EntityType<? extends AbstractStumpEntity> type, World worldIn) {
		super(ModEntities.DARK_STUMP_ENTITY, worldIn);
	}
}
