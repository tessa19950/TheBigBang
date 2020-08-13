package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class RedDrakeEntity extends AbstractDrakeEntity {
	public RedDrakeEntity(EntityType<? extends AbstractDrakeEntity> type, World worldIn) {
		super((EntityType<? extends AbstractDrakeEntity>) ModEntities.RED_DRAKE_ENTITY, worldIn);
	}
}
