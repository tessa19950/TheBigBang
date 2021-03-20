package com.homebrewCult.TheBigBang.gui.quests;

import java.util.Random;
import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public enum Questline implements IStringSerializable { 		
	
	None("", new EntityType<?>[] {ModEntities.BUBBLING_ENTITY},
	new Quest[] {},
	new Biome[] {}),
	
	Octopuses("Octopuses", new EntityType<?>[] {ModEntities.OCTOPUS_ENTITY}, 
	new Quest[] {Quest.Polluted, Quest.Polluted2},
	new Biome[] {Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.BEACH}),
	
	Eyes("Eye Monsters", new EntityType<?>[] {ModEntities.EVIL_EYE_ENTITY, ModEntities.CURSE_EYE_ENTITY, ModEntities.COLD_EYE_ENTITY}, 
	new Quest[] {Quest.WhatPiaHasBorrowed, Quest.Polluted, Quest.LazyLittleCalico, Quest.ArwensPaint, Quest.StrangeDish_EvilEye, Quest.Polluted2},
	new Biome[] {Biomes.SWAMP, Biomes.SWAMP_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS}),
	
	Mushrooms("Mushrooms", new EntityType<?>[] {ModEntities.ORANGE_MUSHROOM_ENTITY, ModEntities.BLUE_MUSHROOM_ENTITY}, 
	new Quest[] {Quest.ANewHouseForBlackbull, Quest.Polluted, Quest.Polluted2},
	new Biome[] {Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, Biomes.MUSHROOM_FIELD_SHORE, Biomes.MUSHROOM_FIELDS}),
	
	ZombieMushrooms("Zombie Mushrooms", new EntityType<?>[] {ModEntities.ZOMBIE_MUSHROOM_ENTITY}, 
	new Quest[] {Quest.Polluted, Quest.Polluted2},
	new Biome[] {Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS}),
	
	Drakes("Drakes", new EntityType<?>[] {ModEntities.DRAKE_ENTITY, ModEntities.COPPER_DRAKE_ENTITY, ModEntities.DARK_DRAKE_ENTITY}, 
	new Quest[] {Quest.Polluted, Quest.Polluted2},
	new Biome[] {Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU}),
	
	Golems("Golems", new EntityType<?>[] {ModEntities.STONE_GOLEM_ENTITY, ModEntities.DARK_STONE_GOLEM_ENTITY, ModEntities.MIXED_GOLEM_ENTITY}, 
	new Quest[] {Quest.Polluted, Quest.Polluted2},
	new Biome[] {Biomes.GRAVELLY_MOUNTAINS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.MOUNTAINS, Biomes.TAIGA_MOUNTAINS, Biomes.WOODED_MOUNTAINS}),
	
	Snails("Snails", new EntityType<?>[] {ModEntities.GREEN_SNAIL_ENTITY, ModEntities.BLUE_SNAIL_ENTITY, ModEntities.RED_SNAIL_ENTITY}, 
	new Quest[] {Quest.Polluted, Quest.Polluted2},
	new Biome[] {Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS}),
	
	JrYetis("Jr. Yetis", new EntityType<?>[] {ModEntities.JRYETI_ENTITY, ModEntities.DARK_JRYETI_ENTITY}, 
	new Quest[] {Quest.Polluted, Quest.Polluted2},
	new Biome[] {Biomes.SNOWY_BEACH, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.SNOWY_TUNDRA}),
	
	Yetis("Yetis", new EntityType<?>[] {ModEntities.YETI_ENTITY, ModEntities.DARK_YETI_ENTITY}, 
	new Quest[] {Quest.Polluted, Quest.Polluted2},
	new Biome[] {Biomes.ICE_SPIKES, Biomes.DEEP_FROZEN_OCEAN, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER}),
	
	Stumps("Stumps", new EntityType<?>[] {ModEntities.STUMP_ENTITY, ModEntities.DARK_STUMP_ENTITY, ModEntities.AXE_STUMP_ENTITY, ModEntities.DARK_AXE_STUMP_ENTITY}, 
	new Quest[] {Quest.Polluted, Quest.Polluted2},
	new Biome[] {Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.DESERT_LAKES, Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_BADLANDS_PLATEAU, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.WOODED_BADLANDS_PLATEAU});
	
	private final String DISPLAY_NAME;
	private final EntityType<?>[] ENTITIES;
	private final Quest[] QUESTS;
	private final Biome[] BIOMES;
	
	private Questline(String displayName, EntityType<?>[] entities, Quest[] quests, Biome[] biomes) {
		DISPLAY_NAME = displayName;
		ENTITIES = entities;
		QUESTS = quests;
		BIOMES = biomes;
	}
	
	public EntityType<?>[] getEntityTypes() {
		return ENTITIES;
	}
	
	public EntityType<?> getRandomEntityType() {
		if(ENTITIES.length > 0) {
			Random rand = new Random();
			return ENTITIES[rand.nextInt(ENTITIES.length)];
		} else {
			return ModEntities.BUBBLING_ENTITY;
		}
	}
	
	public String getDisplayName() {
		return DISPLAY_NAME;
	}
	
	public Quest[] getQuests() {
		return QUESTS;
	}
	
	public Biome[] getBiomes() {
		return BIOMES;
	}
	
	public Quest getQuestByIndex(int index) {
		return QUESTS[index];
	}
	
	public int getIndexByQuest(Quest quest) {
		for(int i = 0; i < QUESTS.length; i++) {
			if(QUESTS[i] == quest) {
				return i;
			}
		}
		return -1;
	}
	
	public static Questline getQuestlineByIndex(int index) {
		Questline[] questlines = Questline.values();
		return questlines[index];
	}
	
	public static Questline getQuestlineByBiome(Biome biome) {
		Questline[] questlines = Questline.values();
		for(int i = 0; i < questlines.length; i++) {
			Biome[] biomes = questlines[i].getBiomes();
			for(int j = 0; j < biomes.length; j++) {
				if(biomes[j] == biome) {
					return questlines[i];
				}
			}
		}
		return Questline.None;
	}

	@Override
	public String getName() {
		return this.name().toLowerCase();
	}
	
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
