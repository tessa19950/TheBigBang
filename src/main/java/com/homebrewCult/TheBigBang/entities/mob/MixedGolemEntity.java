package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class MixedGolemEntity extends AbstractGolemEntity {
	public MixedGolemEntity(EntityType<? extends AbstractGolemEntity> type, World worldIn) {
		super((EntityType<? extends AbstractGolemEntity>) ModEntities.MIXED_GOLEM_ENTITY, worldIn);
	}

}
