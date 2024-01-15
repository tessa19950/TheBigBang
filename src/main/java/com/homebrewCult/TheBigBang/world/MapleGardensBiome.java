package com.homebrewCult.TheBigBang.world;

import com.homebrewCult.TheBigBang.init.ModFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class MapleGardensBiome extends Biome {

	public MapleGardensBiome() {
		super((new Biome.Builder())
				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_SAND_CONFIG)
				.precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST)
				.depth(0.1F).scale(0.4F).temperature(0.7F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent("forest"));

		/*
		this.addStructure(Feature.VILLAGE, new VillageConfig("village/taiga/town_centers", 6));
		this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
		this.addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);
		
		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addStructures(this);
		DefaultBiomeFeatures.addLakes(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addTaigaLargeFerns(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.func_222319_X(this);
		DefaultBiomeFeatures.addMushrooms(this);
		DefaultBiomeFeatures.addReedsAndPumpkins(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addBerryBushes(this);
		DefaultBiomeFeatures.addFreezeTopLayer(this);
		MapleGardensBiome.addMapleTrees(this);
		
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.SHEEP, 12, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.PIG, 10, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.CHICKEN, 10, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.COW, 8, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.WOLF, 8, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.RABBIT, 4, 2, 3));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.FOX, 8, 2, 4));
		this.addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
		 */
	}
	
	public static void addMapleTrees(Biome biomeIn) {
		//biomeIn.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModFeatures.MAPLE_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(10, 0.1F, 1)));
	}

	public int getGrassColor(BlockPos pos) {
		return 0xCC4320;
	}

	public int getFoliageColor(BlockPos pos) {
		return 0xCC4320;
	}
}
