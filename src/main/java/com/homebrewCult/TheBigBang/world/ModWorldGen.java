package com.homebrewCult.TheBigBang.world;

import com.google.common.collect.ImmutableList;
import com.homebrewCult.TheBigBang.config.BigBangConfig;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModWorldGen {
	
	public static void worldGenInit() {
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
		
		//Place Plateau Danger Sign Structures
		ImmutableList<Biome> dangerSignPlateauBiomes = ImmutableList.of(Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.FLOWER_FOREST, Biomes.FOREST,
		Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.PLAINS,
		Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TUNDRA, 
		Biomes.SUNFLOWER_PLAINS, Biomes.SWAMP, Biomes.SWAMP_HILLS, Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, Biomes.WOODED_HILLS);
		for(Biome biome : dangerSignPlateauBiomes) {
			biome.addFeature(Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModFeatures.DANGER_SIGN_PLATEAU, IFeatureConfig.NO_FEATURE_CONFIG, 
			Placement.CHANCE_HEIGHTMAP, new ChanceConfig(196)));
			
			biome.addFeature(Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(ModFeatures.DANGER_SIGN_CAVE, IFeatureConfig.NO_FEATURE_CONFIG, 
			Placement.CHANCE_HEIGHTMAP, new ChanceConfig(12)));
		}
		
		//Place Stone Danger Sign Structures
		ImmutableList<Biome> dangerSignStoneBiomes = ImmutableList.of(Biomes.GRAVELLY_MOUNTAINS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.MOUNTAINS, Biomes.SNOWY_MOUNTAINS,
		Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.TAIGA_MOUNTAINS, Biomes.WOODED_MOUNTAINS);
		for(Biome biome : dangerSignStoneBiomes) {
			biome.addFeature(Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModFeatures.DANGER_SIGN_STONE, IFeatureConfig.NO_FEATURE_CONFIG, 
			Placement.CHANCE_HEIGHTMAP, new ChanceConfig(196)));
			
			biome.addFeature(Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(ModFeatures.DANGER_SIGN_CAVE, IFeatureConfig.NO_FEATURE_CONFIG, 
			Placement.CHANCE_HEIGHTMAP, new ChanceConfig(12)));
		}
	}
}
