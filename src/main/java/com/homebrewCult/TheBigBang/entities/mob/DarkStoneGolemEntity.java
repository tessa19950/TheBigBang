package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DarkStoneGolemEntity extends AbstractGolemEntity {
	public DarkStoneGolemEntity(EntityType<? extends AbstractGolemEntity> type, World worldIn) {
		super((EntityType<? extends AbstractGolemEntity>) ModEntities.DARK_STONE_GOLEM_ENTITY, worldIn);
	}

}
