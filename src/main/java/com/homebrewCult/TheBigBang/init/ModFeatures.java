package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.world.danger_sign_features.*;

import com.homebrewCult.TheBigBang.world.danger_sign_structures.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.PillagerOutpostConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.CountConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TheBigBang.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TheBigBang.MODID)
public class ModFeatures {

	@ObjectHolder("danger_sign_plateau")
	public static final Structure<PillagerOutpostConfig> DANGER_SIGN_PLATEAU_STRUCTURE = null;
	public static IStructurePieceType DANGER_SIGN_PLATEAU_PIECE;
	@ObjectHolder("danger_sign_desert")
	public static final Structure<PillagerOutpostConfig> DANGER_SIGN_DESERT_STRUCTURE = null;
	public static IStructurePieceType DANGER_SIGN_DESERT_PIECE;
	@ObjectHolder("danger_sign_stone")
	public static final Structure<PillagerOutpostConfig> DANGER_SIGN_STONE_STRUCTURE = null;
	public static IStructurePieceType DANGER_SIGN_STONE_PIECE;
	@ObjectHolder("danger_sign_ice")
	public static final Structure<PillagerOutpostConfig> DANGER_SIGN_ICE_STRUCTURE = null;
	public static IStructurePieceType DANGER_SIGN_ICE_PIECE;

	@ObjectHolder("danger_sign_cave")
	public static final Feature<NoFeatureConfig> DANGER_SIGN_CAVE = null;

	//@ObjectHolder("maple_tree")
	//public static final Feature<NoFeatureConfig> MAPLE_TREE = null;

	@SubscribeEvent
	public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
		DANGER_SIGN_PLATEAU_PIECE = Registry.register(Registry.STRUCTURE_PIECE, TheBigBang.MODID + ":danger_sign_plateau", PlateauSignPieceType::new);
		DANGER_SIGN_DESERT_PIECE = Registry.register(Registry.STRUCTURE_PIECE, TheBigBang.MODID + ":danger_sign_desert", DesertSignPieceType::new);
		DANGER_SIGN_STONE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, TheBigBang.MODID + ":danger_sign_stone", StoneSignPieceType::new);
		DANGER_SIGN_ICE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, TheBigBang.MODID + ":danger_sign_ice", IceSignPieceType::new);

		event.getRegistry().registerAll(
				new PlateauSignStructure(PillagerOutpostConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_plateau"),
				new DesertSignStructure(PillagerOutpostConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_desert"),
				new StoneSignStructure(PillagerOutpostConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_stone"),
				new IceSignStructure(PillagerOutpostConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_ice"),

				new DangerSignCaveFeature(NoFeatureConfig::deserialize).setRegistryName(TheBigBang.MODID, "danger_sign_cave")
				//new MapleTreeFeature(NoFeatureConfig::deserialize, false).setRegistryName(TheBigBang.MODID, "maple_tree")
		);
	}
}
