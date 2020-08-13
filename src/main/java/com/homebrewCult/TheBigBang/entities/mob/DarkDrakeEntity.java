package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DarkDrakeEntity extends AbstractDrakeEntity {
	public DarkDrakeEntity(EntityType<? extends AbstractDrakeEntity> type, World worldIn) {
		super((EntityType<? extends AbstractDrakeEntity>) ModEntities.DARK_DRAKE_ENTITY, worldIn);
	}

}
