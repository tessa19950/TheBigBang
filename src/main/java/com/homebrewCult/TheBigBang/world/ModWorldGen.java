package com.homebrewCult.TheBigBang.world;

import com.google.common.collect.ImmutableList;
import com.homebrewCult.TheBigBang.config.BigBangConfig;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.feature.structure.PillagerOutpostConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.CompletableFuture;

public class ModWorldGen {
	
	public static final ImmutableList<Biome> GRASSY_DANGER_SIGN_BIOMES =
	ImmutableList.of(Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.FLOWER_FOREST, Biomes.FOREST,
	Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.PLAINS,
	Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TUNDRA, 
	Biomes.SUNFLOWER_PLAINS, Biomes.SWAMP, Biomes.SWAMP_HILLS, Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS);
	
	public static final ImmutableList<Biome> STONE_DANGER_SIGN_BIOMES =
	ImmutableList.of(Biomes.GRAVELLY_MOUNTAINS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.MOUNTAINS, Biomes.SNOWY_MOUNTAINS,
	Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.TAIGA_MOUNTAINS, Biomes.WOODED_MOUNTAINS);
	
	public static final ImmutableList<Biome> SANDY_DANGER_SIGN_BIOMES =
	ImmutableList.of(Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.DESERT_LAKES);
	
	public static final ImmutableList<Biome> ICY_DANGER_SIGN_BIOMES =
	ImmutableList.of(Biomes.ICE_SPIKES, Biomes.SNOWY_BEACH, Biomes.DEEP_FROZEN_OCEAN, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER);
	
	public static final ImmutableList<Biome> CAVE_DANGER_SIGN_BIOMES =
	ImmutableList.of(Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.FLOWER_FOREST, Biomes.FOREST,
	Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.PLAINS,
	Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TUNDRA, 
	Biomes.SUNFLOWER_PLAINS, Biomes.SWAMP, Biomes.SWAMP_HILLS, Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, 
	Biomes.GRAVELLY_MOUNTAINS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.MOUNTAINS, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA_MOUNTAINS, 
	Biomes.TAIGA_MOUNTAINS, Biomes.WOODED_MOUNTAINS, Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.DESERT_LAKES);

	public static void worldGenInit() {
		CompletableFuture.runAsync(() -> {
			//Generate Ores
			for(Biome biome : ForgeRegistries.BIOMES) {
				//Add Adamantium
				biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.ADAMANTIUM_ORE.getDefaultState(), 5),
						Placement.COUNT_RANGE, new CountRangeConfig(BigBangConfig.adamantiumOreChance.get(), 62, 0, 256)));
				//Add Mithril
				biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.MITHRIL_ORE.getDefaultState(), 5),
						Placement.COUNT_RANGE, new CountRangeConfig(BigBangConfig.mithrilOreChance.get(), 62, 0, 256)));
				//Add Orihalcon
				biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.ORIHALCON_ORE.getDefaultState(), 5),
						Placement.COUNT_RANGE, new CountRangeConfig(BigBangConfig.orihalconOreChance.get(), 62, 0, 256)));
			}
			//Add Surface Danger Signs Structures
			addSurfaceStructures(GRASSY_DANGER_SIGN_BIOMES, ModFeatures.DANGER_SIGN_PLATEAU_STRUCTURE, 100);
			addSurfaceStructures(STONE_DANGER_SIGN_BIOMES, ModFeatures.DANGER_SIGN_STONE_STRUCTURE, 1000);
			addSurfaceStructures(SANDY_DANGER_SIGN_BIOMES, ModFeatures.DANGER_SIGN_DESERT_STRUCTURE, 100);
			addSurfaceStructures(ICY_DANGER_SIGN_BIOMES, ModFeatures.DANGER_SIGN_ICE_STRUCTURE, 100);

			//Add Cave Sign Features
			for(Biome biome : CAVE_DANGER_SIGN_BIOMES) {
				biome.addFeature(Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(ModFeatures.DANGER_SIGN_CAVE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.CHANCE_HEIGHTMAP, new ChanceConfig(1)));
			}
		});
	}

	private static void addSurfaceStructures(ImmutableList<Biome> biomes, Structure<PillagerOutpostConfig> structure, int count) {
		for(Biome biome : biomes) {
			biome.addStructure(structure, new PillagerOutpostConfig(100.0D));
			biome.addFeature(Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(structure,
					new PillagerOutpostConfig(100.0D), Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		}
	}
}
