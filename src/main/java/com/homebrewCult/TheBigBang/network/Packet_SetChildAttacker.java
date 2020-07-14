package com.homebrewCult.TheBigBang.network;

import com.homebrewCult.TheBigBang.entities.AbstractMushroomEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public class Packet_SetChildAttacker  {

	private final int momID;
	private final int childAttackerID;
	
	public Packet_SetChildAttacker(PacketBuffer buf) {	
		momID = buf.readInt();
		childAttackerID = buf.readInt();
	}
	
	public Packet_SetChildAttacker(int momID, int childAttackerID) {
		this.momID = momID;
		this.childAttackerID = childAttackerID;
	}
	
	public static void encode(Packet_SetChildAttacker msg, PacketBuffer buf) {
		buf.writeInt(msg.momID);
		buf.writeInt(msg.childAttackerID);
	}
	
	public static Packet_SetChildAttacker decode(PacketBuffer buf) {
		return new Packet_SetChildAttacker(buf.readInt(), buf.readInt());	
	}
	
	public static void handle(Packet_SetChildAttacker msg, Supplier<NetworkEvent.Context> ctx) {
		AbstractMushroomEntity momEntity = (AbstractMushroomEntity) Minecraft.getInstance().player.world.getEntityByID(msg.momID);
		LivingEntity childAttackerEntity = (LivingEntity) Minecraft.getInstance().player.world.getEntityByID(msg.childAttackerID);
		momEntity.setChildAttacker(childAttackerEntity);
		ctx.get().setPacketHandled(true);
	}
}
