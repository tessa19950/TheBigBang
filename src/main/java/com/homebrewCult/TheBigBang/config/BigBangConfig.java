package com.homebrewCult.TheBigBang.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class BigBangConfig {

	public static ForgeConfigSpec.IntValue adamantiumOreChance;
	public static ForgeConfigSpec.IntValue mithrilOreChance;
	public static ForgeConfigSpec.IntValue orihalconOreChance;
	public static ForgeConfigSpec.IntValue dangerSignFrequency;
	
	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		server.comment("The Big Bang Configuration Settings");
		
		adamantiumOreChance = server
		.comment("Maximum amount of Adamantium ore veins per chunk.")
		.defineInRange("worldGen.adamantiumOreChance", 10, 1, 1000);
		mithrilOreChance = server
		.comment("Maximum amount of Mithril ore veins per chunk.")
		.defineInRange("worldGen.mithrilOreChance", 10, 1, 1000);
		orihalconOreChance = server
		.comment("Maximum amount of Orihalcon ore veins per chunk.")
		.defineInRange("worldGen.orihalconOreChance", 10, 1, 1000);
		dangerSignFrequency = server
		.comment("Percentage chance of a Danger Sign being generated in any given biome.")
		.defineInRange("worldGen.dangerSignFrequency", 33, 1, 100);
	}
}
