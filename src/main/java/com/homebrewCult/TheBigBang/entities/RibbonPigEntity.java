package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.world.World;

public class RibbonPigEntity extends PigEntity {	
	public RibbonPigEntity(EntityType<? extends PigEntity> type, World worldIn) {
		super((EntityType<? extends PigEntity>) ModEntities.RIBBON_PIG_ENTITY, worldIn);
	}
}
