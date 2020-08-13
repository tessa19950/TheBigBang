package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EvilEyeEntity extends AbstractEyeEntity {	
	public EvilEyeEntity(EntityType<? extends AbstractEyeEntity> type, World worldIn) {
		super((EntityType<? extends AbstractEyeEntity>) ModEntities.EVIL_EYE_ENTITY, worldIn);
	}
}




