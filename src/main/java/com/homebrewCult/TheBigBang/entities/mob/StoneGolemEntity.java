package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class StoneGolemEntity extends AbstractGolemEntity {
	public StoneGolemEntity(EntityType<? extends AbstractGolemEntity> type, World worldIn) {
		super((EntityType<? extends AbstractGolemEntity>) ModEntities.STONE_GOLEM_ENTITY, worldIn);
	}
}
