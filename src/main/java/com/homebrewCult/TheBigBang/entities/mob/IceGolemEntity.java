package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class IceGolemEntity extends AbstractGolemEntity {
	public IceGolemEntity(EntityType<? extends AbstractGolemEntity> type, World worldIn) {
		super((EntityType<? extends AbstractGolemEntity>) ModEntities.ICE_GOLEM_ENTITY, worldIn);
	}
}
