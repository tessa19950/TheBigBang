package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class GreenBubblingEntity extends AbstractBubblingEntity {
	public GreenBubblingEntity(EntityType<? extends AbstractBubblingEntity> type, World worldIn) {
		super((EntityType<? extends AbstractBubblingEntity>) ModEntities.GREEN_BUBBLING_ENTITY, worldIn);
	}
}
