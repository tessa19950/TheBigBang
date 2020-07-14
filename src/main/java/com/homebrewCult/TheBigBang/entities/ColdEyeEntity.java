package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class ColdEyeEntity extends AbstractEyeEntity {	
	public ColdEyeEntity(EntityType<? extends AbstractEyeEntity> type, World worldIn) 
	{
		super((EntityType<? extends AbstractEyeEntity>) ModEntities.COLD_EYE_ENTITY, worldIn);
	}
}

