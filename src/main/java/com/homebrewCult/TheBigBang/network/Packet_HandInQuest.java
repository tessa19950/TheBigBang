package com.homebrewCult.TheBigBang.network;

import com.homebrewCult.TheBigBang.blocks.DangerSignTile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

public class Packet_HandInQuest {

	private final BlockPos pos;
	private final int questInt;
	
	public Packet_HandInQuest(PacketBuffer buf) {	
		pos = buf.readBlockPos();
		questInt = buf.readInt();
	}
	
	public Packet_HandInQuest(BlockPos pos, int quest) {
		this.pos = pos;
		this.questInt = quest;
	}
	
	public static void encode(Packet_HandInQuest msg, PacketBuffer buf) {
		buf.writeBlockPos(msg.pos);
		buf.writeInt(msg.questInt);
	}
	
	public static Packet_HandInQuest decode(PacketBuffer buf) {
		return new Packet_HandInQuest(buf.readBlockPos(), buf.readInt());	
	}
	
	public static void handle(Packet_HandInQuest msg, Supplier<NetworkEvent.Context> ctx) {
		DangerSignTile te = (DangerSignTile)ctx.get().getSender().getServerWorld().getTileEntity(msg.pos);
		te.container.takeQuestItems(msg.questInt);
		te.container.takeKillCount(msg.questInt);
		te.container.giveRewards(msg.questInt);
		te.setQuestCompleted(msg.questInt);
		ctx.get().setPacketHandled(true);
	}
}
