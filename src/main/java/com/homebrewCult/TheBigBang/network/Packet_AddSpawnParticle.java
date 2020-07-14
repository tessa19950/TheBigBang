package com.homebrewCult.TheBigBang.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

import com.homebrewCult.TheBigBang.util.MathUtility;

public class Packet_AddSpawnParticle  {

	private final BlockPos pos;
	private static int count = 32;
	private static int spread = 1;
	
	public Packet_AddSpawnParticle(PacketBuffer buf) {	
		this.pos = buf.readBlockPos();
	}
	
	public Packet_AddSpawnParticle(BlockPos pos) {
		this.pos = pos;
	}
	
	public static void encode(Packet_AddSpawnParticle msg, PacketBuffer buf) {
		buf.writeBlockPos(msg.pos);
	}
	
	public static Packet_AddSpawnParticle decode(PacketBuffer buf) {
		return new Packet_AddSpawnParticle(buf.readBlockPos());	
	}
	
	public static void handle(Packet_AddSpawnParticle msg, Supplier<NetworkEvent.Context> ctx) {
		for(int i = 0; i < count; i++) {
			double x = msg.pos.getX() + MathUtility.randomDoubleInRange(-spread, spread);
			double y = msg.pos.getY() + MathUtility.randomDoubleInRange(-spread, spread);
			double z = msg.pos.getZ() + MathUtility.randomDoubleInRange(-spread, spread);
			Minecraft.getInstance().player.world.addParticle(ParticleTypes.POOF, x, y, z, 0, 0, 0);
		}
		ctx.get().setPacketHandled(true);
	}
}
