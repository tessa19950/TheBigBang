package com.homebrewCult.TheBigBang.network;

import com.homebrewCult.TheBigBang.blocks.DangerSignTile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public class Packet_HideInputSlots {

	private final BlockPos pos;
	
	public Packet_HideInputSlots(PacketBuffer buf) {	
		pos = buf.readBlockPos();
	}
	
	public Packet_HideInputSlots(BlockPos pos) {
		this.pos = pos;
	}
	
	public static void encode(Packet_HideInputSlots msg, PacketBuffer buf) {
		buf.writeBlockPos(msg.pos);
	}
	
	public static Packet_HideInputSlots decode(PacketBuffer buf) {
		return new Packet_HideInputSlots(buf.readBlockPos());	
	}
	
	public static void handle(Packet_HideInputSlots msg, Supplier<NetworkEvent.Context> ctx) {
		DangerSignTile te = (DangerSignTile)ctx.get().getSender().getServerWorld().getTileEntity(msg.pos);
		te.container.hideInputSlots();
		ctx.get().setPacketHandled(true);
	}
}
