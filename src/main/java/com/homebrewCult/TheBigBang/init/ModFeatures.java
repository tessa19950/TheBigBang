package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.world.CaveRoomConfig;
import com.homebrewCult.TheBigBang.world.danger_sign_features.DangerSignCaveFeature;
import com.homebrewCult.TheBigBang.world.danger_sign_structures.*;
import com.homebrewCult.TheBigBang.world.maple_gardens_features.MapleTreeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = TheBigBang.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures {

	public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, TheBigBang.MOD_ID);

	public static final RegistryObject<PlateauSignStructure> DANGER_SIGN_PLATEAU_STRUCTURE = FEATURES.register("danger_sign_plateau",
			() -> new PlateauSignStructure(NoFeatureConfig::deserialize));
	public static final RegistryObject<DesertSignStructure> DANGER_SIGN_DESERT_STRUCTURE = FEATURES.register("danger_sign_desert",
			() -> new DesertSignStructure(NoFeatureConfig::deserialize));
	public static final RegistryObject<StoneSignStructure> DANGER_SIGN_STONE_STRUCTURE = FEATURES.register("danger_sign_stone",
			() -> new StoneSignStructure(NoFeatureConfig::deserialize));
	public static final RegistryObject<IceSignStructure> DANGER_SIGN_ICE_STRUCTURE = FEATURES.register("danger_sign_ice",
			() -> new IceSignStructure(NoFeatureConfig::deserialize));
	public static final RegistryObject<DangerSignCaveFeature> DANGER_SIGN_CAVE = FEATURES.register("danger_sign_cave",
			() -> new DangerSignCaveFeature(NoFeatureConfig::deserialize));

	public static IStructurePieceType DANGER_SIGN_PLATEAU_PIECE = PlateauSignPieceType::new;
	public static IStructurePieceType DANGER_SIGN_DESERT_PIECE = DesertSignPieceType::new;
	public static IStructurePieceType DANGER_SIGN_STONE_PIECE = StoneSignPieceType::new;
	public static IStructurePieceType DANGER_SIGN_ICE_PIECE = IceSignPieceType::new;

	public static final DeferredRegister<Placement<?>> DECORATORS = new DeferredRegister<>(ForgeRegistries.DECORATORS, TheBigBang.MOD_ID);
	public static final RegistryObject<Placement<CaveRoomConfig>> CAVE_ROOM = DECORATORS.register("cave_room",
			() -> new CaveRoomConfig.CaveRoom(CaveRoomConfig::deserialize));

	public static final RegistryObject<MapleTreeFeature> MAPLE_TREE = FEATURES.register("maple_tree",
			() -> new MapleTreeFeature(NoFeatureConfig::deserialize, true));

	@SubscribeEvent
	public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
		Registry.register(Registry.STRUCTURE_PIECE, TheBigBang.getNamespacedKey("danger_sign_plateau"), DANGER_SIGN_PLATEAU_PIECE);
		Registry.register(Registry.STRUCTURE_PIECE, TheBigBang.getNamespacedKey("danger_sign_desert"), DANGER_SIGN_DESERT_PIECE);
		Registry.register(Registry.STRUCTURE_PIECE, TheBigBang.getNamespacedKey("danger_sign_stone"), DANGER_SIGN_STONE_PIECE);
		Registry.register(Registry.STRUCTURE_PIECE, TheBigBang.getNamespacedKey("danger_sign_ice"), DANGER_SIGN_ICE_PIECE);
	}
}
