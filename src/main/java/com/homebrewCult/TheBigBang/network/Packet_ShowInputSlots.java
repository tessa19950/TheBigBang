package com.homebrewCult.TheBigBang.network;

import com.homebrewCult.TheBigBang.blocks.DangerSignTile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public class Packet_ShowInputSlots {

	private final BlockPos pos;
	private final int count;
	
	public Packet_ShowInputSlots(PacketBuffer buf) {	
		pos = buf.readBlockPos();
		count = buf.readInt();
	}
	
	public Packet_ShowInputSlots(BlockPos pos, int count) {
		this.pos = pos;
		this.count = count;
	}
	
	public static void encode(Packet_ShowInputSlots msg, PacketBuffer buf) {
		buf.writeBlockPos(msg.pos);
		buf.writeInt(msg.count);
	}
	
	public static Packet_ShowInputSlots decode(PacketBuffer buf) {
		return new Packet_ShowInputSlots(buf.readBlockPos(), buf.readInt());	
	}
	
	public static void handle(Packet_ShowInputSlots msg, Supplier<NetworkEvent.Context> ctx) {
		DangerSignTile te = (DangerSignTile)ctx.get().getSender().getServerWorld().getTileEntity(msg.pos);
		te.container.showInputSlots(msg.count);
		ctx.get().setPacketHandled(true);
	}
}
