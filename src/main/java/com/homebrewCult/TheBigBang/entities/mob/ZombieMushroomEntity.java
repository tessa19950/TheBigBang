package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class ZombieMushroomEntity extends AbstractMushroomEntity {	
	public ZombieMushroomEntity(EntityType<? extends AbstractMushroomEntity> type, World worldIn) {
		super(ModEntities.ZOMBIE_MUSHROOM_ENTITY, worldIn);
	}
}
