package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class FireGolemEntity extends AbstractGolemEntity {
	public FireGolemEntity(EntityType<? extends AbstractGolemEntity> type, World worldIn) {
		super((EntityType<? extends AbstractGolemEntity>) ModEntities.FIRE_GOLEM_ENTITY, worldIn);
	}
}
