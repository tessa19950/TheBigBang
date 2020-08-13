package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class CopperDrakeEntity extends AbstractDrakeEntity {
	public CopperDrakeEntity(EntityType<? extends AbstractDrakeEntity> type, World worldIn) {
		super((EntityType<? extends AbstractDrakeEntity>) ModEntities.COPPER_DRAKE_ENTITY, worldIn);
	}

}
