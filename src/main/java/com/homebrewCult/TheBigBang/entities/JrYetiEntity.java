package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class JrYetiEntity extends AbstractJrYetiEntity {	
	
	public JrYetiEntity(EntityType<? extends AbstractJrYetiEntity> type, World worldIn) {
		super((EntityType<? extends AbstractJrYetiEntity>) ModEntities.JRYETI_ENTITY, worldIn);
	}
	
	@Override
	public JrYetiEntity createChild(AgeableEntity ageable) {
		return (JrYetiEntity)ModEntities.JRYETI_ENTITY.create(this.world);
	}
}
