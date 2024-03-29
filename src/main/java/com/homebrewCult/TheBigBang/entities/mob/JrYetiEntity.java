package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class JrYetiEntity extends AbstractJrYetiEntity {	
	
	public JrYetiEntity(EntityType<? extends AbstractJrYetiEntity> type, World worldIn) {
		super(ModEntities.JRYETI_ENTITY, worldIn);
	}
	
	@Override
	public JrYetiEntity createChild(AgeableEntity ageable) {
		return ModEntities.JRYETI_ENTITY.create(this.world);
	}
}
