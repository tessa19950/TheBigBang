package com.homebrewCult.TheBigBang;

import com.homebrewCult.TheBigBang.init.*;
import com.homebrewCult.TheBigBang.listeners.EffectsListener;
import com.homebrewCult.TheBigBang.listeners.BigBangListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.homebrewCult.TheBigBang.config.BigBangConfigSetup;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.util.ClientProxy;
import com.homebrewCult.TheBigBang.util.IProxy;
import com.homebrewCult.TheBigBang.util.ServerProxy;
import com.homebrewCult.TheBigBang.world.ModWorldGen;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(TheBigBang.MOD_ID)
public final class TheBigBang {
	
	public static final String MOD_ID = "thebigbang";
	private static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
	
	public TheBigBang() {
		print("Constructing the Big Bang.");
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, BigBangConfigSetup.SERVER_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, BigBangConfigSetup.CLIENT_CONFIG);
		BigBangConfigSetup.LoadConfig(BigBangConfigSetup.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("thebigbang-client.toml").toString());
		BigBangConfigSetup.LoadConfig(BigBangConfigSetup.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("thebigbang-server.toml").toString());
		
		final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		eventBus.addListener(this::onCommonSetup);
		ModEffects.EFFECTS.register(eventBus);
		ModParticleTypes.PARTICLE_TYPES.register(eventBus);
		ModRecipeTypes.RECIPES.register(eventBus);
		ModFeatures.FEATURES.register(eventBus);
		ModFeatures.DECORATORS.register(eventBus);
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new BigBangListener());
		MinecraftForge.EVENT_BUS.register(new EffectsListener());
	}

	public void onCommonSetup(final FMLCommonSetupEvent event) {
		proxy.Init();
		BigBangPacketHandler.packetHandlerInit();
		ModWorldGen.worldGenInit();
	}
	
	public static void print (String message) {
		LOGGER.info("[The Big Bang Info] " + message);
		LOGGER.debug("[The Big Bang Info] " + message);
	}

	public static String getNamespacedKey(String key) {
		return MOD_ID + ":" + key;
	}
}
