package com.homebrewCult.TheBigBang.entities.mob;
import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class YetiEntity extends AbstractYetiEntity {	
	public YetiEntity(EntityType<? extends AbstractYetiEntity> type, World worldIn) {
		super((EntityType<? extends AbstractYetiEntity>) ModEntities.YETI_ENTITY, worldIn);
	}
}
