package com.homebrewCult.TheBigBang.world;

import com.google.common.collect.ImmutableList;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.config.BigBangConfig;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static net.minecraft.world.biome.Biomes.*;

public class ModWorldGen {

	// LISTS OF BIOMES THE ORES MORE FREQUENTLY APPEAR IN
	public static final ImmutableList<Biome> ADAMANTIUM_BIOMES = ImmutableList.of(
			DESERT, DESERT_HILLS, DESERT_LAKES
	);
	public static final ImmutableList<Biome> ORIHALCON_BIOMES = ImmutableList.of(
			OCEAN, COLD_OCEAN, DEEP_COLD_OCEAN,
			WARM_OCEAN, LUKEWARM_OCEAN, DEEP_WARM_OCEAN, DEEP_LUKEWARM_OCEAN,
			FROZEN_OCEAN, DEEP_FROZEN_OCEAN
	);
	public static final ImmutableList<Biome> MITHRIL_BIOMES = ImmutableList.of(
			PLAINS, FOREST, FLOWER_FOREST,
			BIRCH_FOREST, BIRCH_FOREST_HILLS, TALL_BIRCH_FOREST,
			DARK_FOREST, DARK_FOREST_HILLS
	);

	// LISTS OF BIOMES THAT THE VARIOUS DANGER SIGN TYPES APPEAR IN
	public static final ImmutableList<Biome> GRASSY_DANGER_SIGN_BIOMES = ImmutableList.of(
			BIRCH_FOREST, BIRCH_FOREST_HILLS, DARK_FOREST, DARK_FOREST_HILLS, FLOWER_FOREST, FOREST,
			GIANT_SPRUCE_TAIGA, GIANT_SPRUCE_TAIGA_HILLS, GIANT_TREE_TAIGA, GIANT_TREE_TAIGA_HILLS, JUNGLE,
			JUNGLE_EDGE, JUNGLE_HILLS, PLAINS, SAVANNA, SAVANNA_PLATEAU, SHATTERED_SAVANNA,
			SHATTERED_SAVANNA_PLATEAU, SNOWY_TAIGA, SNOWY_TAIGA_HILLS, SNOWY_TUNDRA, SUNFLOWER_PLAINS,
			SWAMP, SWAMP_HILLS, TAIGA, TAIGA_HILLS, TALL_BIRCH_FOREST, TALL_BIRCH_HILLS
	);
	public static final ImmutableList<Biome> STONE_DANGER_SIGN_BIOMES = ImmutableList.of(
			GRAVELLY_MOUNTAINS, MODIFIED_GRAVELLY_MOUNTAINS, MOUNTAIN_EDGE, MOUNTAINS, SNOWY_MOUNTAINS,
			SNOWY_TAIGA_MOUNTAINS, TAIGA_MOUNTAINS, WOODED_MOUNTAINS
	);
	public static final ImmutableList<Biome> DESERT_DANGER_SIGN_BIOMES = ImmutableList.of(
			DESERT, DESERT_HILLS, DESERT_LAKES
	);
	public static final ImmutableList<Biome> ICY_DANGER_SIGN_BIOMES = ImmutableList.of(
			ICE_SPIKES, SNOWY_BEACH, DEEP_FROZEN_OCEAN, FROZEN_OCEAN, FROZEN_RIVER
	);
	public static final List<Biome> CAVE_DANGER_SIGN_BIOMES = Biome.BIOMES.stream()
			.filter(biome -> !biome.getCategory().equals(Biome.Category.OCEAN)).collect(Collectors.toList());

	public static void worldGenInit() {
		CompletableFuture.runAsync(() -> {
			Integer adamantiumConfig = BigBangConfig.ADAMANTIUM_ORE_CHANCE_CONFIG.get();
			Integer mithrilConfig = BigBangConfig.MITHRIL_ORE_CHANCE_CONFIG.get();
			Integer orihalconConfig = BigBangConfig.ORIHALCON_ORE_CHANCE_CONFIG.get();
			TheBigBang.print("Your world has been blessed with Adamantium, Mithril and Orihalcon!");
			Biome.BIOMES.forEach(b -> {
				if(adamantiumConfig > 0)
					addOre(b, ModBlocks.ADAMANTIUM_ORE.getDefaultState(), 5, adamantiumConfig, 128);
				if(mithrilConfig > 0)
					addOre(b, ModBlocks.MITHRIL_ORE.getDefaultState(), 5, mithrilConfig, 128);
				if(orihalconConfig > 0)
					addOre(b, ModBlocks.ORIHALCON_ORE.getDefaultState(), 5, orihalconConfig, 128);
			});
			if(adamantiumConfig > 0)
				ADAMANTIUM_BIOMES.forEach(b -> addOre(b, ModBlocks.ADAMANTIUM_ORE.getDefaultState(), 9, adamantiumConfig * 3, 128));
			if(mithrilConfig > 0)
				MITHRIL_BIOMES.forEach(b -> addOre(b, ModBlocks.MITHRIL_ORE.getDefaultState(), 9, mithrilConfig * 3, 128));
			if(orihalconConfig > 0)
				ORIHALCON_BIOMES.forEach(b -> addOre(b, ModBlocks.ORIHALCON_ORE.getDefaultState(), 9, orihalconConfig * 3, 128));

			TheBigBang.print("Generating surface danger sign structures.");
			Boolean surfaceChanceConfig = BigBangConfig.SURFACE_DANGER_SIGN_CONFIG.get();
			if(surfaceChanceConfig) {
				addSurfaceStructures(GRASSY_DANGER_SIGN_BIOMES, ModFeatures.DANGER_SIGN_PLATEAU_STRUCTURE.get(), 1);
				addSurfaceStructures(STONE_DANGER_SIGN_BIOMES, ModFeatures.DANGER_SIGN_STONE_STRUCTURE.get(), 1);
				addSurfaceStructures(DESERT_DANGER_SIGN_BIOMES, ModFeatures.DANGER_SIGN_DESERT_STRUCTURE.get(), 1);
				addSurfaceStructures(ICY_DANGER_SIGN_BIOMES, ModFeatures.DANGER_SIGN_ICE_STRUCTURE.get(), 1);
			}

			TheBigBang.print("Generating cave danger sign features.");
			Integer caveChanceConfig = BigBangConfig.UNDERGROUND_DANGER_SIGN_FREQUENCY_CONFIG.get();
			if(caveChanceConfig > 0) {
				int caveChance = (int) MathHelper.lerp((float)caveChanceConfig / 100.0F, 100, 1);
				addUndergroundStructures(CAVE_DANGER_SIGN_BIOMES, ModFeatures.DANGER_SIGN_CAVE.get(), caveChance);
			}
		});
	}

	private static void addOre(Biome biome, BlockState ore, int size, int count, int maximum) {
		OreFeatureConfig config = new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ore, size);
		ConfiguredPlacement<CountRangeConfig> placement = Placement.COUNT_RANGE.configure(new CountRangeConfig(count, 0, 0, maximum));
		biome.addFeature(
				Decoration.UNDERGROUND_ORES,
				Feature.ORE.withConfiguration(config).withPlacement(placement)
		);
	}

	private static void addSurfaceStructures(List<Biome> biomes, Structure<NoFeatureConfig> structure, int chance) {
		ChanceConfig chanceConfig = new ChanceConfig(chance);
		for(Biome biome : biomes) {
			biome.addStructure(structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			biome.addFeature(
					Decoration.SURFACE_STRUCTURES,
					structure.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
			);
		}
		//Biome.createDecoratedFeature(structure, IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHANCE_HEIGHTMAP, chanceConfig)
	}

	private static void addUndergroundStructures(List<Biome> biomes, Feature<NoFeatureConfig> feature, int chance) {
		CaveRoomConfig chanceConfig = new CaveRoomConfig(chance);
		for(Biome biome : biomes) {
			biome.addFeature(
					Decoration.UNDERGROUND_DECORATION,
					feature.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
			);
		}
		//Biome.createDecoratedFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, ModFeatures.CAVE_ROOM.get(), chanceConfig)
	}
}
