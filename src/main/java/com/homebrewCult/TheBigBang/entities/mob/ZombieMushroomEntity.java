package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class ZombieMushroomEntity extends AbstractMushroomEntity {	
	public ZombieMushroomEntity(EntityType<? extends AbstractMushroomEntity> type, World worldIn) {
		super(ModEntities.ZOMBIE_MUSHROOM_ENTITY, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Override
	public boolean isEntityUndead() {
		return true;
	}
}
