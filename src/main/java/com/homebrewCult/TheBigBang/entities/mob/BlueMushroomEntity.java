package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class BlueMushroomEntity extends AbstractMushroomEntity {	
	public BlueMushroomEntity(EntityType<? extends AbstractMushroomEntity> type, World worldIn) {
		super(ModEntities.BLUE_MUSHROOM_ENTITY, worldIn);
	}
	
	@Override
	public BlueMushroomEntity createChild(AgeableEntity ageable) {
		getDataManager().set(HAS_CHILD, true);
		BlueMushroomEntity newChild = ModEntities.BLUE_MUSHROOM_ENTITY.create(this.world);
		if(newChild != null)
			newChild.setMom(this);
		return newChild;
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}
}
