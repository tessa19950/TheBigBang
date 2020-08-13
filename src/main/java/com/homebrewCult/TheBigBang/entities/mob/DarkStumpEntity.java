package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DarkStumpEntity extends AbstractStumpEntity {	
	public DarkStumpEntity(EntityType<? extends AbstractStumpEntity> type, World worldIn) {
		super((EntityType<? extends AbstractStumpEntity>) ModEntities.DARK_STUMP_ENTITY, worldIn);
	}
	
	@Override
	public DarkStumpEntity createChild(AgeableEntity ageable) {
		return (DarkStumpEntity)ModEntities.DARK_STUMP_ENTITY.create(this.world);
	}
}
