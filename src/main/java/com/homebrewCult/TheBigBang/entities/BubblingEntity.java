package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;

import net.minecraft.entity.EntityType;
import net.minecraft.particles.IParticleData;
import net.minecraft.world.World;

public class BubblingEntity extends AbstractBubblingEntity {
	public BubblingEntity(EntityType<? extends AbstractBubblingEntity> type, World worldIn) {
		super((EntityType<? extends AbstractBubblingEntity>) ModEntities.BUBBLING_ENTITY, worldIn);
	}
	
	@Override
	public IParticleData getSquishParticle() {
		return ModParticleTypes.ITEM_BLUE_SLIME.get();
	}
}
