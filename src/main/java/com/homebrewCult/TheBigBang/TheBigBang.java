package com.homebrewCult.TheBigBang;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.Logger;
import com.homebrewCult.TheBigBang.config.BigBangConfigSetup;
import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModRecipeTypes;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.util.ClientProxy;
import com.homebrewCult.TheBigBang.util.IProxy;
import com.homebrewCult.TheBigBang.util.ServerProxy;
import com.homebrewCult.TheBigBang.world.ModWorldGen;

import org.apache.logging.log4j.LogManager;

@Mod(TheBigBang.MODID)
public final class TheBigBang {
	
	public static final String MODID = "thebigbang";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
	
	public TheBigBang() {
		LOGGER.debug("Constructing the Big Bang.");
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, BigBangConfigSetup.SERVER_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, BigBangConfigSetup.CLIENT_CONFIG);
		BigBangConfigSetup.LoadConfig(BigBangConfigSetup.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("thebigbang-client.toml").toString());
		BigBangConfigSetup.LoadConfig(BigBangConfigSetup.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("thebigbang-server.toml").toString());
		
		ModRecipeTypes.RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModParticleTypes.particleInit();
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		proxy.Init();
		BigBangPacketHandler.packetHandlerInit();
		ModWorldGen.worldGenInit();
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		ModEntities.registerEntityRenders();
	}
	
	public static void print (String message) {
		System.out.print("[The Big Bang Info] " + message + "\n");
	}
}
