package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DarkJrYetiEntity extends AbstractJrYetiEntity {	
	
	public DarkJrYetiEntity(EntityType<? extends AbstractJrYetiEntity> type, World worldIn) {
		super((EntityType<? extends AbstractJrYetiEntity>) ModEntities.DARK_JRYETI_ENTITY, worldIn);
	}
	
	@Override
	public DarkJrYetiEntity createChild(AgeableEntity ageable) {
		return (DarkJrYetiEntity)ModEntities.DARK_JRYETI_ENTITY.create(this.world);
	}
}
