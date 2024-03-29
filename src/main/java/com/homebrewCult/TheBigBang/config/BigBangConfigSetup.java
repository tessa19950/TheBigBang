package com.homebrewCult.TheBigBang.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BigBangConfigSetup {

	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SERVER_CONFIG;
	
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec CLIENT_CONFIG;
	
	static {
		BigBangConfig.init(SERVER_BUILDER, CLIENT_BUILDER);
		
		SERVER_CONFIG = SERVER_BUILDER.build();
		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}
	
	public static void LoadConfig(ForgeConfigSpec config, String path) {
		TheBigBang.print("Loading config: " + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		TheBigBang.print("Built config: " + path);
		file.load();
		TheBigBang.print("Loaded config: " + path);
		config.setConfig(file);
	}
	
}
