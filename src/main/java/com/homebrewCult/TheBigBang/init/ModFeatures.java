package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.world.danger_sign_features.DangerSignCaveFeature;
import com.homebrewCult.TheBigBang.world.danger_sign_features.DangerSignDesertFeature;
import com.homebrewCult.TheBigBang.world.danger_sign_features.DangerSignIceFeature;
import com.homebrewCult.TheBigBang.world.danger_sign_features.DangerSignPlateauFeature;
import com.homebrewCult.TheBigBang.world.danger_sign_features.DangerSignStoneFeature;
import com.homebrewCult.TheBigBang.world.maple_gardens_features.MapleTreeFeature;

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
	@ObjectHolder("danger_sign_desert")
	public static final Feature<NoFeatureConfig> DANGER_SIGN_DESERT = null;
	@ObjectHolder("danger_sign_ice")
	public static final Feature<NoFeatureConfig> DANGER_SIGN_ICE = null;
	@ObjectHolder("danger_sign_cave")
	public static final Feature<NoFeatureConfig> DANGER_SIGN_CAVE = null;
	@ObjectHolder("maple_tree")
	public static final Feature<NoFeatureConfig> MAPLE_TREE = null;
	
	@SubscribeEvent
	public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().registerAll(
				new DangerSignPlateauFeature(NoFeatureConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_plateau"),
				new DangerSignStoneFeature(NoFeatureConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_stone"),
				new DangerSignDesertFeature(NoFeatureConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_desert"),
				new DangerSignIceFeature(NoFeatureConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_ice"),
				new DangerSignCaveFeature(NoFeatureConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_cave"),
				new MapleTreeFeature(NoFeatureConfig::deserialize, false).setRegistryName(TheBigBang.MODID, "maple_tree")
		);
	}
}
