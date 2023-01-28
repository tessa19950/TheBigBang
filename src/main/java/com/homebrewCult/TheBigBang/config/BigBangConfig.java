package com.homebrewCult.TheBigBang.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BigBangConfig {

	public static ForgeConfigSpec.BooleanValue USE_DAMAGE_NUMBERS_CONFIG;

	public static ForgeConfigSpec.IntValue ADAMANTIUM_ORE_CHANCE_CONFIG;
	public static ForgeConfigSpec.IntValue MITHRIL_ORE_CHANCE_CONFIG;
	public static ForgeConfigSpec.IntValue ORIHALCON_ORE_CHANCE_CONFIG;
	public static ForgeConfigSpec.BooleanValue SURFACE_DANGER_SIGN_CONFIG;
	public static ForgeConfigSpec.IntValue UNDERGROUND_DANGER_SIGN_FREQUENCY_CONFIG;
	
	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		client.comment("The Big Bang - Client Configuration Settings");
		USE_DAMAGE_NUMBERS_CONFIG = client
				.comment("Spawn damage numbers over mobs heads whenever a player deals damage to them.")
				.define("Particles.Damage Numbers", true);

		server.comment("The Big Bang - Server Configuration Settings");
		UNDERGROUND_DANGER_SIGN_FREQUENCY_CONFIG = server
				.comment("Percentage chance of an underground Danger Sign being generated.")
				.defineInRange("World Generation.Underground Danger Sign Frequency", 95, 0, 100);
		SURFACE_DANGER_SIGN_CONFIG = server
				.comment("Whether or not surface Danger Signs should be generated.")
				.define("World Generation.Surface Danger Sign Frequency", true);
		ORIHALCON_ORE_CHANCE_CONFIG = server
				.comment("Maximum amount of Orihalcon ore veins per chunk.")
				.defineInRange("World Generation.Orihalcon Ore Chance", 10, 0, 100);
		MITHRIL_ORE_CHANCE_CONFIG = server
				.comment("Maximum amount of Mithril ore veins per chunk.")
				.defineInRange("World Generation.Mithril Ore Chance", 10, 0, 100);
		ADAMANTIUM_ORE_CHANCE_CONFIG = server
				.comment("Maximum amount of Adamantium ore veins per chunk.")
				.defineInRange("World Generation.Adamantium Ore Chance", 10, 0, 100);
	}
}
