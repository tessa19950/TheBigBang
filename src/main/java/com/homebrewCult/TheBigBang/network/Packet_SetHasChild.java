package com.homebrewCult.TheBigBang.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

import com.homebrewCult.TheBigBang.entities.mob.AbstractMushroomEntity;

public class Packet_SetHasChild  {

	private final int momID;
	
	public Packet_SetHasChild(PacketBuffer buf) {	
		momID = buf.readInt();
	}
	
	public Packet_SetHasChild(int momID) {
		this.momID = momID;
	}
	
	public static void encode(Packet_SetHasChild msg, PacketBuffer buf) {
		buf.writeInt(msg.momID);
	}
	
	public static Packet_SetHasChild decode(PacketBuffer buf) {
		return new Packet_SetHasChild(buf.readInt());	
	}
	
	public static void handle(Packet_SetHasChild msg, Supplier<NetworkEvent.Context> ctx) {
		AbstractMushroomEntity momEntity = (AbstractMushroomEntity) Minecraft.getInstance().player.world.getEntityByID(msg.momID);
		momEntity.setHasChild();
		ctx.get().setPacketHandled(true);
	}
}
