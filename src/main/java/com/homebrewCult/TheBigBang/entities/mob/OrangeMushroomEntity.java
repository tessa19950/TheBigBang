package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.network.Packet_SetHasChild;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class OrangeMushroomEntity extends AbstractMushroomEntity {	
	public OrangeMushroomEntity(EntityType<? extends AbstractMushroomEntity> type, World worldIn) {
		super((EntityType<? extends AbstractMushroomEntity>) ModEntities.ORANGE_MUSHROOM_ENTITY, worldIn);
	}
	
	@Override
	public OrangeMushroomEntity createChild(AgeableEntity ageable) {		
		BigBangPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new Packet_SetHasChild(this.getEntityId()));
		setHasChild();
		
		AbstractMushroomEntity newChild = (AbstractMushroomEntity)ModEntities.ORANGE_MUSHROOM_ENTITY.create(this.world);
		newChild.setMom(this);
		return (OrangeMushroomEntity)newChild;
	}
}
