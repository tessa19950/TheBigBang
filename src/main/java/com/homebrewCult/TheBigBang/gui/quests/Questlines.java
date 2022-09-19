package com.homebrewCult.TheBigBang.gui.quests;

import java.util.Random;
import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public enum Questlines implements IStringSerializable {
	
	None("", new EntityType<?>[] {ModEntities.BUBBLING_ENTITY},
	new Quests[] {},
	new Biome[] {}),
	
	Octopuses("Octopuses", new EntityType<?>[] {ModEntities.OCTOPUS_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.MrsMingMingsWorry, Quests.MakingASparklingRockOctopus, Quests.KerningCitizenRequest, Quests.HowToSurvive, Quests.BuildingANewHouseOctopus,
			Quests.Polluted2, Quests.TheDarkLordsTraining, Quests.OctopusesAreHealthy, Quests.RolyPolys6sTools, Quests.OctopusAreAliens, Quests.VirusSampleResearchOctopus},
	new Biome[] {Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.BEACH}),
	
	Eyes("Eye Monsters", new EntityType<?>[] {ModEntities.EVIL_EYE_ENTITY, ModEntities.CURSE_EYE_ENTITY, ModEntities.COLD_EYE_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.StrangeDish_EvilEye, Quests.WhatPiaHasBorrowed, Quests.LazyLittleCalico, Quests.ArwensPaint, Quests.StrangeDish_CurseEye,
			Quests.Polluted2, Quests.AlexTheRunawayKid,  Quests.CutthroatMannysRequest, Quests.StrangeDish_ColdEye, Quests.KerningCityBookStore},
	new Biome[] {Biomes.SWAMP, Biomes.SWAMP_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS}),
	
	Mushrooms("Mushrooms", new EntityType<?>[] {ModEntities.ORANGE_MUSHROOM_ENTITY, ModEntities.BLUE_MUSHROOM_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.BiggsItemCollection, Quests.BarisTest, Quests.PiaAndTheBlueMushroom, Quests.TheMushroomStudies,
			Quests.Polluted2, Quests.BuildingANewHouseMushroom, Quests.MushroomJuiceMixer, Quests.MushroomSaladRecipe, Quests.SaladIngredients, Quests.MakingHornyMushroomPie},
	new Biome[] {Biomes.PLAINS, Biomes.FOREST, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, Biomes.MUSHROOM_FIELD_SHORE, Biomes.MUSHROOM_FIELDS}),

	ZombieMushrooms("Zombie Mushrooms", new EntityType<?>[] {ModEntities.ZOMBIE_MUSHROOM_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.ZombieMushroomSignal, Quests.TheOutbreak, Quests.RowensCursedMushrooms, Quests.MysteryOfTheHospital, Quests.MakingSparklingRockUndeadMushroom,
			Quests.Polluted2, Quests.ShawnTheExcavator, Quests.HowToAvoidTheStink, Quests.SpiritDiviner, Quests.GasMaskAlternative, Quests.ZombieMushroomThreat},
	new Biome[] {Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS}),
	
	Drakes("Drakes", new EntityType<?>[] {ModEntities.DRAKE_ENTITY, ModEntities.COPPER_DRAKE_ENTITY, ModEntities.DARK_DRAKE_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.WhatPiaHasBorrowed, Quests.BuildingMoreHouses, Quests.JanesChallenge, Quests.JanesLastExperiment, Quests.AClueToTheSecretBook,
			Quests.Polluted2, Quests.TransportingDrakeSkulls, Quests.FossilResearchProgress, Quests.SealUpACriticalDanger, Quests.MuirhatsWorry, Quests.ManjisTale},
	new Biome[] {Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU}),
	
	Golems("Golems", new EntityType<?>[] {ModEntities.STONE_GOLEM_ENTITY, ModEntities.DARK_STONE_GOLEM_ENTITY, ModEntities.MIXED_GOLEM_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.DrBrucesResearch, Quests.UnknownBlueMaterial, Quests.ANewHouseForBlackbull, Quests.LastMaterialForRonnie, Quests.MayasLastCollection,
			Quests.Polluted2, Quests.CrackedRubble, Quests.EliminateTheGolems, Quests.TheMixedGolemThreat, Quests.TheMixedGolemMutation, Quests.CharmsForInfo},
	new Biome[] {Biomes.GRAVELLY_MOUNTAINS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.MOUNTAINS, Biomes.TAIGA_MOUNTAINS, Biomes.WOODED_MOUNTAINS}),
	
	Snails("Snails", new EntityType<?>[] {ModEntities.GREEN_SNAIL_ENTITY, ModEntities.BLUE_SNAIL_ENTITY, ModEntities.RED_SNAIL_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.BasicTraining, Quests.MaisTrainingSnail, Quests.HelpHuntTheSnails, Quests.MariasLetter, Quests.HelpOutYoona,
			Quests.Polluted2, Quests.MaisSecondTraining, Quests.MayasFirstCollection, Quests.TheOldSnail, Quests.ARainbowShell, Quests.BobTheSnail},
	new Biome[] {Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS}),
	
	JrYetis("Jr. Yetis", new EntityType<?>[] {ModEntities.JRYETI_ENTITY, ModEntities.DARK_JRYETI_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.GordonTheCraftsman, Quests.BeastTamer, Quests.OneMoreWish, Quests.GordonsTuition,
			Quests.Polluted2, Quests.BettysResearch, Quests.JrYetiKeychain, Quests.YetiResearchProgress},
	new Biome[] {Biomes.SNOWY_BEACH, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.SNOWY_TUNDRA}),
	
	Yetis("Yetis", new EntityType<?>[] {ModEntities.YETI_ENTITY, ModEntities.DARK_YETI_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.ReawakeningTheGladius, Quests.SnowfieldGiant, Quests.SonOfScadurTheHunter, Quests.BlakesBiggestFan,
			Quests.Polluted2, Quests.MarchOfTheYetiArmy, Quests.YetiRides, Quests.ASymbioticRelationship},
	new Biome[] {Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.ICE_SPIKES, Biomes.DEEP_FROZEN_OCEAN, Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER}),
	
	Stumps("Stumps", new EntityType<?>[] {ModEntities.STUMP_ENTITY, ModEntities.DARK_STUMP_ENTITY, ModEntities.AXE_STUMP_ENTITY, ModEntities.DARK_AXE_STUMP_ENTITY}, 
	new Quests[] {Quests.Polluted, Quests.MaisTrainingStump, Quests.FixingBlackbullsHouse, Quests.WarriorsFirstTraining, Quests.StumpHorrorStory, Quests.ImBored, Quests.NeedToFindMyDaughter,
			Quests.Polluted2, Quests.DrBettysResearchOnPlants, Quests.GettingArconsBlood, Quests.WarriorsSecondTraining, Quests.NeedHelpOnMyHomework, Quests.WinstonsFossilDigUp, Quests.SophiasSnack},
	new Biome[] {Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.DESERT_LAKES, Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_BADLANDS_PLATEAU, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.WOODED_BADLANDS_PLATEAU});
	
	private final String DISPLAY_NAME;
	private final EntityType<?>[] ENTITIES;
	private final Quests[] QUESTS;
	private final Biome[] BIOMES;
	
	Questlines(String displayName, EntityType<?>[] entities, Quests[] quests, Biome[] biomes) {
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
	
	public Quests[] getQuests() {
		return QUESTS;
	}
	
	public Biome[] getBiomes() {
		return BIOMES;
	}
	
	public Quests getQuestByIndex(int index) {
		return QUESTS[index];
	}
	
	public int getIndexByQuest(Quests quest) {
		for(int i = 0; i < QUESTS.length; i++) {
			if(QUESTS[i] == quest) {
				return i;
			}
		}
		return -1;
	}
	
	public static Questlines getQuestlineByIndex(int index) {
		Questlines[] questlines = Questlines.values();
		return questlines[index];
	}
	
	public static Questlines getQuestlineByBiome(Biome biome) {
		Questlines[] questlines = Questlines.values();
		for(int i = 0; i < questlines.length; i++) {
			Biome[] biomes = questlines[i].getBiomes();
			for(int j = 0; j < biomes.length; j++) {
				if(biomes[j] == biome) {
					return questlines[i];
				}
			}
		}
		return Questlines.None;
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
