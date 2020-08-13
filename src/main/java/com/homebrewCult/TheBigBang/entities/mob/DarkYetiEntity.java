package com.homebrewCult.TheBigBang.entities.mob;
import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DarkYetiEntity extends AbstractYetiEntity {	
	public DarkYetiEntity(EntityType<? extends AbstractYetiEntity> type, World worldIn) {
		super((EntityType<? extends AbstractYetiEntity>) ModEntities.DARK_YETI_ENTITY, worldIn);
	}
	
}
