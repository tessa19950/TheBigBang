package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DrakeEntity extends AbstractDrakeEntity {
	public DrakeEntity(EntityType<? extends AbstractDrakeEntity> type, World worldIn) {
		super((EntityType<? extends AbstractDrakeEntity>) ModEntities.DRAKE_ENTITY, worldIn);
	}

}
