package com.homebrewCult.TheBigBang.entities.mob;
import com.homebrewCult.TheBigBang.init.ModEntities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class YetiEntity extends AbstractYetiEntity {	
	public YetiEntity(EntityType<? extends AbstractYetiEntity> type, World worldIn) {
		super(ModEntities.YETI_ENTITY, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
	}
}
