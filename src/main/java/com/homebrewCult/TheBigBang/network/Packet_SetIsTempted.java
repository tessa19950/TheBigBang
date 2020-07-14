package com.homebrewCult.TheBigBang.network;

import com.homebrewCult.TheBigBang.entities.AbstractGolemEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public class Packet_SetIsTempted  {

	private final int golemID;
	private final boolean isTempted;
	
	public Packet_SetIsTempted(PacketBuffer buf) {	
		golemID = buf.readInt();
		isTempted = buf.readBoolean();
	}
	
	public Packet_SetIsTempted(int golemID, boolean isTempted) {
		this.golemID = golemID;
		this.isTempted = isTempted;
	}
	
	public static void encode(Packet_SetIsTempted msg, PacketBuffer buf) {
		buf.writeInt(msg.golemID);
		buf.writeBoolean(msg.isTempted);
	}
	
	public static Packet_SetIsTempted decode(PacketBuffer buf) {
		return new Packet_SetIsTempted(buf.readInt(), buf.readBoolean());	
	}
	
	public static void handle(Packet_SetIsTempted msg, Supplier<NetworkEvent.Context> ctx) {
		AbstractGolemEntity golemEntity = (AbstractGolemEntity) Minecraft.getInstance().player.world.getEntityByID(msg.golemID);
		golemEntity.setIsTempted(msg.isTempted);
		ctx.get().setPacketHandled(true);
	}
}
