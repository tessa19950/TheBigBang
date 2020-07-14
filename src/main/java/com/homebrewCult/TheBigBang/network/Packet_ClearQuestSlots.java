package com.homebrewCult.TheBigBang.network;

import com.homebrewCult.TheBigBang.blocks.DangerSignTile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public class Packet_ClearQuestSlots {

	private final BlockPos pos;
	
	public Packet_ClearQuestSlots(PacketBuffer buf) {	
		pos = buf.readBlockPos();
	}
	
	public Packet_ClearQuestSlots(BlockPos pos) {
		this.pos = pos;
	}
	
	public static void encode(Packet_ClearQuestSlots msg, PacketBuffer buf) {
		buf.writeBlockPos(msg.pos);
	}
	
	public static Packet_ClearQuestSlots decode(PacketBuffer buf) {
		return new Packet_ClearQuestSlots(buf.readBlockPos());	
	}
	
	public static void handle(Packet_ClearQuestSlots msg, Supplier<NetworkEvent.Context> ctx) {
		DangerSignTile te = (DangerSignTile)ctx.get().getSender().getServerWorld().getTileEntity(msg.pos);
		te.container.clearQuestInventory();
		ctx.get().setPacketHandled(true);
	}
}
