package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.world.DangerSignCaveFeature;
import com.homebrewCult.TheBigBang.world.DangerSignPlateauFeature;
import com.homebrewCult.TheBigBang.world.DangerSignStoneFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TheBigBang.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TheBigBang.MODID)
public class ModFeatures {
	
	@ObjectHolder("danger_sign_plateau")
	public static final Feature<NoFeatureConfig> DANGER_SIGN_PLATEAU = null;
	@ObjectHolder("danger_sign_stone")
	public static final Feature<NoFeatureConfig> DANGER_SIGN_STONE = null;
	@ObjectHolder("danger_sign_cave")
	public static final Feature<NoFeatureConfig> DANGER_SIGN_CAVE = null;
	
	@SubscribeEvent
	public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().registerAll(
				new DangerSignPlateauFeature(NoFeatureConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_plateau"),
				new DangerSignStoneFeature(NoFeatureConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_stone"),
				new DangerSignCaveFeature(NoFeatureConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_cave")
		);
	}
}
