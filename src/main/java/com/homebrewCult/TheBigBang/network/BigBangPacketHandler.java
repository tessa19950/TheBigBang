package com.homebrewCult.TheBigBang.network;

import com.homebrewCult.TheBigBang.TheBigBang;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class BigBangPacketHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(TheBigBang.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int HandInQuest_ID = 960300;
	private static int ClearQuestSlots_ID = 960301;
	private static int ShowInputSlots_ID = 960302;
	private static int HideInputSlots_ID = 960303;

	
	public static void packetHandlerInit() {
		INSTANCE.registerMessage(HandInQuest_ID++, Packet_HandInQuest.class, Packet_HandInQuest::encode, Packet_HandInQuest::decode, Packet_HandInQuest::handle);
		INSTANCE.registerMessage(ClearQuestSlots_ID++, Packet_ClearQuestSlots.class, Packet_ClearQuestSlots::encode, Packet_ClearQuestSlots::decode, Packet_ClearQuestSlots::handle);
		INSTANCE.registerMessage(ShowInputSlots_ID++, Packet_ShowInputSlots.class, Packet_ShowInputSlots::encode, Packet_ShowInputSlots::decode, Packet_ShowInputSlots::handle);
		INSTANCE.registerMessage(HideInputSlots_ID++, Packet_HideInputSlots.class, Packet_HideInputSlots::encode, Packet_HideInputSlots::decode, Packet_HideInputSlots::handle);
	}
}
