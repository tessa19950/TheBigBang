package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.network.Packet_SetHasChild;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class BlueMushroomEntity extends AbstractMushroomEntity {	
	public BlueMushroomEntity(EntityType<? extends AbstractMushroomEntity> type, World worldIn) {
		super((EntityType<? extends AbstractMushroomEntity>) ModEntities.BLUE_MUSHROOM_ENTITY, worldIn);
	}
	
	@Override
	public BlueMushroomEntity createChild(AgeableEntity ageable) {
		BigBangPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new Packet_SetHasChild(this.getEntityId()));
		this.setHasChild();
		
		AbstractMushroomEntity newChild = (AbstractMushroomEntity)ModEntities.BLUE_MUSHROOM_ENTITY.create(this.world);
		newChild.setMom(this);
		return (BlueMushroomEntity)newChild;
	}
}
