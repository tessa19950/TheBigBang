package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class OrangeMushroomEntity extends AbstractMushroomEntity {	
	public OrangeMushroomEntity(EntityType<? extends AbstractMushroomEntity> type, World worldIn) {
		super(ModEntities.ORANGE_MUSHROOM_ENTITY, worldIn);
	}
	
	@Override
	public OrangeMushroomEntity createChild(AgeableEntity ageable) {
		getDataManager().set(HAS_CHILD, true);
		OrangeMushroomEntity newChild = ModEntities.ORANGE_MUSHROOM_ENTITY.create(this.world);
		if(newChild != null)
			newChild.setMom(this);
		return newChild;
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}
}
