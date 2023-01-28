package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class MixedGolemEntity extends AbstractGolemEntity {
	public MixedGolemEntity(EntityType<? extends AbstractGolemEntity> type, World worldIn) {
		super(ModEntities.MIXED_GOLEM_ENTITY, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D);
	}
}
