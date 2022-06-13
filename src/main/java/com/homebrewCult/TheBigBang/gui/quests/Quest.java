package com.homebrewCult.TheBigBang.gui.quests;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.homebrewCult.TheBigBang.init.ModItems;

public enum Quest {
	BlankQuest("TITLE", 100,
			"QUEST_DESCRIPTION"),

	Polluted("POLLUTED!", 100,
			"Eliminate #kill_amount# #entity# for the Rememberer.",
			100),
	Polluted2("POLLUTED! Part 2", 1000,
			"Eliminate #kill_amount# #entity# for the Rememberer.",
			300),

	//EYE QUESTS
	AlexTheRunawayKid("Alex the Runaway Kid", 100,
			"On a lonely rooftop in Kerning City, sits a boy named Alex who ran away from home, he�s become exhausted from being on the run with no money. "
					+ "He wants to return home, but needs Evil Eye Tails and Red Ribbons to persuade his father not to punish him.",
			ImmutableMap.of(ModItems.EVIL_EYE_TAIL, 30, ModItems.PIGS_RIBBON, 5)),
	LazyLittleCalico("Lazy Little Calico", 100,
			"All Calico ever does is sleep, sleep, sleep, when he should be keeping an eye out on the crews bedrooms. "
					+ "Bartol thinks it would be funny to throw an Evil Eye Tail at Calico and see how he reacts. You think it might be a fun little prank..",
			ImmutableMap.of(ModItems.EVIL_EYE_TAIL, 10)),
	StrangeDish_EvilEye("Strange Dish", 100,
			"Tangyoon has been experimenting with a new dish recently, using an ingredient that no one has ever used before. "
					+ "He's been working with #item0#s and says that he could make something new and fresh if only you would bring him #item_amount0# #item0#s...",
			ImmutableMap.of(ModItems.EVIL_EYE_TAIL, 20)),
	StrangeDish_CurseEye("Strange Dish", 100,
			"Tangyoon has been experimenting with a new dish recently, using an ingredient that no one has ever used before. "
					+ "He's been working with #item0#s and says that he could make something new and fresh if only you would bring him #item_amount0# #item0#s...",
			ImmutableMap.of(ModItems.CURSE_EYE_TAIL, 20)),
	StrangeDish_ColdEye("Strange Dish", 100,
			"Tangyoon has been experimenting with a new dish recently, using an ingredient that no one has ever used before. "
					+ "He's been working with #item0#s and says that he could make something new and fresh if only you would bring him #item_amount0# #item0#s...",
			ImmutableMap.of(ModItems.COLD_EYE_TAIL, 20)),
	CutthroatMannysRequest("Cutthroat Manny's Request", 100,
			"A request from Nella. Cutthroat Manny needs to get #item_amount# #item#s so he can make a nice dinner of Evil Eye Stew. Now, that sounds delicious...in a gross, disgusting, putrid type of way!",
			ImmutableMap.of(ModItems.EVIL_EYE_TAIL, 64)),
	KerningCityBookStore("Kerning City Book Store", 1000,
			"Mr. Pickall, the book store owner of Kerning City needs 64 Evil Eye Tails. Surely he's up to something... Oh well.",
			ImmutableMap.of(ModItems.EVIL_EYE_TAIL, 64)),
	ArwensPaint("Arwen's Paint", 100,
			"Arwen was assigned to draw a mural for the Ellinia Magic School Festival, "
					+ "but she can't finish her mural, because she has run out of yellow and green paint. Find Evil and Curse Eye Tails, which can be used to make paint.",
			ImmutableMap.of(ModItems.EVIL_EYE_TAIL, 20, ModItems.CURSE_EYE_TAIL, 20)),

	//OCTOPUS QUESTS
	MrsMingMingsWorry("Mrs. Ming Ming's Worry", 1000,
			"Mrs. Ming Ming from Henesys is preparing for the village festival. She needs food to prepare for the festival, and needs me to collect 5 Raw Porkchops, 60 Octopus Legs, and 1 Milk Bucket.",
			ImmutableMap.of(Items.PORKCHOP, 5, ModItems.OCTOPUS_LEG, 60, Items.MILK_BUCKET, 1)),
	MakingASparklingRockOctopus("Making a Sparkling Rock", 1000,
			"Sophia from the General Store in Perion is working on her dream to be an alchemist. To make the sparkling rock, she needs 50 Oak Leaves, 20 Octopus Legs and 1 Drake's Blood. Everything looks reasonable enough...wait, Drake's Blood?!",
			ImmutableMap.of(Items.OAK_LEAVES, 50, ModItems.OCTOPUS_LEG, 20, ModItems.DRAKES_BLOOD, 1)),
	KerningCitizenRequest("Kerning Citizen Request", 1000,
			"Andre from the Hair Salon wants some Seafood Soup. You should get 15 Octopus Legs and 1 Pure Water.",
			ImmutableMap.of(ModItems.OCTOPUS_LEG, 15, ModItems.PURE_WATER, 1)),
	HowToSurvive("How to Survive", 1000,
			"The Dark Lord has some simple training for you. Eliminate 10 Octopuses in this area. Remember: Never trust anyone and be ready for everything. It's the Dark Lord way.",
			10),
	BuildingANewHouseOctopus("Building a New House", 1000,
			"Ronnie needs more materials for his house, he wants 50 of each of the following: Octopus Legs, Curse Eye Tails and Charms of the Undead. These are probably available from the monsters around Dark Forest, and Caverns.",
			ImmutableMap.of(ModItems.OCTOPUS_LEG, 50, ModItems.CURSE_EYE_TAIL, 50, ModItems.UNDEAD_CHARM, 50)),
	TheDarkLordsTraining("The Dark Lord's Training", 1000,
			"The Dark Lord thinks you're a normal Steve, and intends to fix your supposed laziness by making you train. Investigate the surrounding area and hunt 40 Octopuses. Hey, a little extra training never hurt, right?",
			40),
	OctopusesAreHealthy("Octopus' are Healthy", 1000,
			"Gather the necessary ingredients to make a special tonic to energize Lady Syl. Hunt some Octopusses around this sign and gather some Octopus Legs.",
			ImmutableMap.of(ModItems.OCTOPUS_LEG, 20)),
	RolyPolys6sTools("Roly-Poly 6's Tools", 1000,
			"Roly-Poly 6 lost his tools to a bunch of monsters, I need those tools to fix things at Eos Tower. Find the octopus that took his stuff and take it out. It should be somewhere around here.",
			EnumQuestItem.RolyPolysTools),
	OctopusAreAliens("Octopus are Aliens?", 1000,
			"Dr. Kim, who has tirelessly studied life in space, wonders if the monsters from Omega Sector, which spread to the taiga forests are in fact aliens. Find an Octopus' Private Mail, so we can find out a more accurate answer.",
			EnumQuestItem.OctopusMail),
	VirusSampleResearchOctopus("Virus Sample Research", 1000,
			"The Dark Lord from Kerning City is looking into the likelihood of a virus spreading. You must hunt 30 Octopuses and gather 30 Orange Mushroom Caps and report back to him.",
			30, ImmutableMap.of(ModItems.ORANGE_MUSHROOM_CAP, 30)),

	//MUSHROOM QUESTS
	VirusSampleResearchMushroom("Virus Sample Research", 1000,
			"The Dark Lord from Kerning City is looking into the likelihood of a virus spreading. You must hunt 30 Orange Mushrooms and gather 30 Octopus Legs and report back to him.",
			30, ImmutableMap.of(ModItems.OCTOPUS_LEG, 30)),
	BiggsItemCollection("Biggs' Item Collection", 1000,
			"Biggs has a task for you. He wants you to get him 10 Orange Mushroom Caps and 30 Blue Snail Shells. He doesn't seem too convinced that you can do it, but it sounds like a piece of cake.",
			ImmutableMap.of(ModItems.ORANGE_MUSHROOM_CAP, 10, ModItems.BLUE_SNAIL_SHELL, 30)),
	BarisTest("Bari's Test", 1000,
			"Bari has been instructed by Mai to test your skills. He wants you to defeat the most powerful monster from Maple Island, an Orange Mushroom, and bring back 1 Orange Mushroom Cap.",
			1, ImmutableMap.of(ModItems.ORANGE_MUSHROOM_CAP, 1)),
	PiaAndTheBlueMushroom("Pia and the Blue Mushroom", 1000,
			"Pia from Henesys Park tells you that she ran into a bully on her way to Ellinia, and now she wants you to show the rude mushroom who's boss! Collect 10 Orange Mushroom Caps as proof. ",
			ImmutableMap.of(ModItems.BLUE_MUSHROOM_CAP, 10)),
	TheMushroomStudies("The Mushroom Studies", 1000,
			"I contacted Bruce, who has been studying the monsters in Henesys. His daughter has been missing for some time, and he needs help studying mushrooms for a clue on his daughter's whereabouts. I just can't say no to a worthy cause like this...",
			ImmutableMap.of(ModItems.ORANGE_MUSHROOM_CAP, 40)),
	BuildingANewHouseMushroom("Building a New House", 1000,
			"Ronnie from the forest of Ellinia needs you to gather up materials to build a house for newborn fairies. 50 of each of the following: Orange Mushroom Caps, Leaves and Red Snail Shells. You can get most of these from the monsters around the prairie.",
			ImmutableMap.of(ModItems.ORANGE_MUSHROOM_CAP, 50, ModItems.RED_SNAIL_SHELL, 50, Items.OAK_LEAVES, 50)),
	MushroomJuiceMixer("Mushroom Juice Mixer", 1000,
			"Camila has gotten into cooking lately, and said that she wants to make the Orange Mushroom Juice from Athena Pierce's recipe. She needs 15 Orange Mushroom Caps from the area you're in right now.",
			ImmutableMap.of(ModItems.ORANGE_MUSHROOM_CAP, 15)),
	MushroomSaladRecipe("Mushroom Salad Recipe", 1000,
			"Camila has gotten Mrs. Ming Ming's recipe. That really made her day... she could barely contain her excitement, but she's missing a few ingredients.",
			ImmutableMap.of(ModItems.ORANGE_MUSHROOM_CAP, 10)),
	SaladIngredients("Salad Ingredients", 1000,
			"Camila wants to make a Mushroom Salad for Maya who is sick, but she needs you to get the ingredients. Bring back 10 Orange Mushroom Caps and 10 Blue Mushroom Caps.",
			ImmutableMap.of(ModItems.ORANGE_MUSHROOM_CAP, 10, ModItems.BLUE_MUSHROOM_CAP, 10)),
	MakingHornyMushroomPie("Making Horny Mushroom Pie", 1000,
			"Camila asked you to get ingredients so she can make a yummy Horny Mushroom Pie for the townspeople. Might need a better name, though.... Collect Mushroom Caps from the area and collect Yeti's Horns by hunting Yetis in snowy or icy biomes.",
			ImmutableMap.of(ModItems.ORANGE_MUSHROOM_CAP, 20, ModItems.YETI_HORN, 5)),

	//GOLEM QUESTS
	DrBrucesResearch("Dr. Bruce's Research", 1000,
			"Dr. Bruce needs your help with his research on Golems, and named you his assistant. Your first task as assistant is to reduce the amount of Golems by hunting 40 of them.",
			40),
	CrackedRubble("Cracked Rubble", 1000,
			"Dr. Bruce needs 25 Stone Golem Rubbles for his research.",
			ImmutableMap.of(ModItems.STONE_GOLEM_RUBBLE, 25)),
	UnknownBlueMaterial("Unknown Blue Material", 1000,
			"Inside the cracked rubble, Dr. Bruce found a strange blue substance. He's curious if all Golems have this substance inside them, so he wants you to bring him 10 Dark Stone Golem Rubbles.",
			ImmutableMap.of(ModItems.DARK_STONE_GOLEM_RUBBLE, 10)),
	ANewHouseForBlackbull("A New House for Blackbull", 100,
			"Blackbull's cousins are all moving to Perion, so he needs to build a brand new house, from scratch! "
			+ "He needs the deed to his land, but it was stolen by from him, you must slay Golems in the area to gather materials and recover the Deed to the Land.",
			ImmutableMap.of(ModItems.STONE_GOLEM_RUBBLE, 20, Items.IRON_INGOT, 5, ModItems.LETTER, 1), EnumQuestItem.BlackbullsDeed),
	LastMaterialForRonnie("Last Material for Ronnie", 1000,
			"Ronnie reveals the last material he needs to build a house. He wants you to collect, 10 Dark Stone Golem Rubbles, 10 Spider Eyes and 1 Recipe for Medicine with Weird Vibes. The golems should have that last one.",
			ImmutableMap.of(ModItems.DARK_STONE_GOLEM_RUBBLE, 10, Items.SPIDER_EYE, 10, Items.DIAMOND, 1)),
	MayasLastCollection("Maya's Last Collection", 1000,
			"Maya requests 50 Dark Stone Golem Rubbles, 1 Diamond and 10 Bones. This is RIDICULOUS, it's too hard.",
			ImmutableMap.of(ModItems.DARK_STONE_GOLEM_RUBBLE, 50, Items.BONE, 10, Items.DIAMOND, 1)),
	EliminateTheGolems("Eliminate the Golems", 1000,
			"Muirhat says evil spirits have infiltrated the Minecraft world, but you can't give up. Eliminate 20 Stone, Dark Stone and Mixed Golems to stop these evil creatures from spreading their wickedness further.",
			40, ImmutableMap.of(ModItems.STONE_GOLEM_RUBBLE, 10, ModItems.DARK_STONE_GOLEM_RUBBLE, 10)),
	TheMixedGolemThreat("The Mixed Golem Threat", 1000,
			"Rina sneaks into Golem Temples under the full moon to collect primroses. It's quite bizarre, but she says the Mixed Golems have grown more dangerous and she'd like you to eliminate 60 of them for her.",
			60),
	TheMixedGolemMutation("The Mixed Golem Mutation", 1000,
			"Bruce thinks the vitality of the primroses gives Golems life. It sounds impossible, but could the flowers really have such power? Please eliminate more Mixed Golems and report back here with your findings.",
			80),
	CharmsForInfo("Charms for Info", 1000,
			"Bruce wants Golem information from Roca, the Cygnus informant. But Roca wants 30 Zombie Mushroom Charms, please bring those here, as well as 30 more Stone and Dark Stone Golem Rubbles. Why should I have to do that? Ugh, whatever.",
			ImmutableMap.of(ModItems.STONE_GOLEM_RUBBLE, 30, ModItems.DARK_STONE_GOLEM_RUBBLE, 30, ModItems.UNDEAD_CHARM, 30)),

	//ZOMBIE MUSHROOM QUESTS
	ZombieMushroomSignal("Zombie Mushroom Signal", 100,
			"The Rememberer needs help figuring out what drove the mushrooms into a rage. He suggests that someone should study the Zombie Mushrooms and wants you to gather some Charms of the Undead.",
			30, ImmutableMap.of(ModItems.UNDEAD_CHARM, 10)),
	TheOutbreak("The Outbreak", 100,
			"A virus is spreading and we might have accidentally infected you a little. Collect 20 Unread Mushroom Charms and 1 Vaccine Recipe from monsters in the area, so we can cure you, or you will become a zombie and you won't get any special powers. ",
			ImmutableMap.of(ModItems.UNDEAD_CHARM, 20, ModItems.LETTER, 1), EnumQuestItem.VaccineRecipe),
	RowensCursedMushrooms("Rowen's Cursed Mushrooms", 100,
			"Rowen, assistant to the head magician of Ellinia, Grendel the Really Old tells you that Grendel is concerned about the Zombie Mushrooms destroying the forest. Take them down for me and bring back 50 Undead Mushroom Charms.",
			50, ImmutableMap.of(ModItems.UNDEAD_CHARM, 50)),
	MysteryOfTheHospital("Mystery of the Hospital", 100,
			"In Naora Hospital lives a ghost! She's unwilling to believe that she is dead, and needs 35 Charms of the Undead in order to live again and requests that you find them. You wonder if her plan will really work...",
			ImmutableMap.of(ModItems.UNDEAD_CHARM, 35)),
	MakingSparklingRockUndeadMushroom("Making a Sparkling Rock", 100,
			"Manji and Sophia, need materials for a special stone. They need you to gather 30x Undead Mushroom Charms and 1 Drake's Blood. Where am I supposed to get that? This won't be easy.",
			ImmutableMap.of(ModItems.UNDEAD_CHARM, 30, ModItems.DRAKES_BLOOD, 1)),
	ShawnTheExcavator("Shawn the Excavator", 100,
			"An announcement posted by the Relic Excavation Association: A bloodthirsty band of zombie mushrooms has appeared and are stirring up a ruckus! Any brave adventurer who eliminates these undead beasts will be rewarded.",
			50),
	HowToAvoidTheStink("How to Avoid the Stink", 100,
			"The rememberer needs to make a gas mask to reach a place with extremely foul odor. Please bring the necessary materials. Looking at those materials you can only assume the mask itself would be very stinky.",
			ImmutableMap.of(ModItems.UNDEAD_CHARM, 30, Items.LEATHER, 15, Items.SPIDER_EYE, 5)),
	SpiritDiviner("Spirit Diviner", 100,
			"Unleash the spirits of Undead monsters by eliminating 70 Zombie Mushrooms and sending them back to nirvana. Doing so will aquire you the title of Spirit Diviner.",
			70),
	GasMaskAlternative("Gas Mask Alternative", 100,
			"The rememberer needs an alternative to the gas mask and thinks the smell wouldn't matter if he didn't breathe at all. He needs a few items to brew an ancient potion of not-breathing.\"",
			ImmutableMap.of(ModItems.UNDEAD_CHARM, 30, ModItems.LETTER, 1, Items.NETHER_WART, 10), EnumQuestItem.NotBreathingPotionRecipe),
	ZombieMushroomThreat("Zombie Mushroom Threat", 100,
			"Luvar tells you the Zombie Mushrooms have changed in a very stange way, but he can't fight them himself. That's pretty weak, but you really should give the guy a break. He wants 50 Charms of the Undead so that he can study the change in the Mushrooms.",
			ImmutableMap.of(ModItems.UNDEAD_CHARM, 50)),

	//DRAKE QUESTS
	WhatPiaHasBorrowed("What Pia has Borrowed", 100,
			"Rina from Henesys tells you that she took Pia's red cape, because Pia didn't return the items she borrowed. "
					+ "Rina says that if she gets her items back, the cape is Pia's again. Go find 1 Diamond, 1 Antique Scroll, and 30 Drake's Skull.",
			ImmutableMap.of(ModItems.DRAKES_SKULL, 30, Items.DIAMOND, 1, ModItems.LETTER, 1), EnumQuestItem.AntiqueScroll),
	BuildingMoreHouses("Building more Houses", 100,
			"Ronnie asks to gather the necessary materials to make more homes for newborn fairies in Ellinia. He needs you to collect 50 Drake Skulls.",
			ImmutableMap.of(ModItems.DRAKES_SKULL, 50)),
	JanesChallenge("Jane's Challenge", 100,
			"Jane wants to make some nice elixirs, so she needs you to gather 3 vials of Drake's Blood, 1 Orange Mushroom Cap and 30 Packaged Experimental Frogs, which she lost a while ago to some Drakes in the area.",
			ImmutableMap.of(ModItems.DRAKES_BLOOD, 3, ModItems.ORANGE_MUSHROOM_CAP, 1, ModItems.LETTER, 30), EnumQuestItem.PackagedFrog),
	JanesLastExperiment("Jane's Last Experiment", 100,
			"For this last experiment, Jane wants to make a mysterious scroll. For that, she needs 5 Dragon's Skins, 3 Ancient Tree Saps and 1 Magic Rock. The sap is held by Stumps, but Jane doesn't know how where to find a Magic Rock.",
			ImmutableMap.of(ModItems.DRAGON_SKIN, 5, ModItems.SAP_OF_ANCIENT_TREE, 3, ModItems.BLESSED_MAGIC_ROCK, 1)),
	AClueToTheSecretBook("A clue to the Secret Book", 100,
			"Mr. Wetbottom from Sleepywood's hotel is in a pickle. He lost his favorite book to his runaway son Ronnie. Ronnie is known for watching the Drakes, and Mr. Wetbottom believes the Drakes have stolen the book from Ronnie. ",
			ImmutableMap.of(ModItems.LETTER, 1), EnumQuestItem.LostBook),
	TransportingDrakeSkulls("Transporting Drake Skulls", 100,
			"Dr. Betty needs 50 Drake Skulls to be delivered to her carefully for fossil research. Fossils huh...? But Drake Skulls aren't fossils, right...?",
			ImmutableMap.of(ModItems.DRAKES_SKULL, 50)),
	FossilResearchProgress("Fossil Research Progress", 100,
			"Dr. Betty finished her research on Drake Skulls. That was quick. But she was expecting another report from Dr. Winston in Perion, however it seems to have been lost on the way to her. Please hunt the Drakes in the area and find the Missing Research Report.",
			ImmutableMap.of(ModItems.LETTER, 1), EnumQuestItem.MissingReport),
	SealUpACriticalDanger("Seal up a Critical Danger", 100,
			"The Insignificant Being  has been feeling a strange vibe permeating deep inside the dungeon. You need to gather 33 Drake Skulls to be used in a spell to seal up the curse.",
			ImmutableMap.of(ModItems.DRAKES_SKULL, 33)),
	MuirhatsWorry("Muirhat's Worry", 100,
			"The Minecraft world has already been infused with evil spirits. But you refuse to give up like this. Eliminate 50 Drakes, 50 Copper Drakes and 50 Dark Drakes to stop these evil creatures from further spreading their evilness.",
			150),
	ManjisTale("Manji's Tale", 100,
			"The Maple Administrator wants to collect stories from the very first adventurers of the Minecraft world. Please find him one Drake's Blood and Manji's Old Diary so the Administrator can record this ancient tale.",
			ImmutableMap.of(ModItems.DRAKES_BLOOD, 1, ModItems.LETTER, 1), EnumQuestItem.ManjisDiary),

	//YETI QUESTS
	ReawakeningTheGladius("Reawakening the Gladius", 100,
			"Chrishrama made an Ancient Gladius a long time ago. To reawaken it he needs 1 Piece of Ice, 1 Ancient Scroll, and 1 Magma Cream, but you've never heard those things... "
					+ "He recommends hunting in the area for the scroll and in cold biomes for the Piece of Ice.",
			ImmutableMap.of(ModItems.PIECE_OF_ICE, 1, ModItems.LETTER, 1, Items.MAGMA_CREAM, 1), EnumQuestItem.AncientScroll),
	SnowfieldGiant("Snowfield Giant", 100,
			"Jade from El Nath was attacked by a huge Yeti while skiing. What could have attacked her? Please collect evidence to discover something about the creature.",
			ImmutableMap.of(ModItems.YETI_HORN, 10)),
	SonOfScadurTheHunter("Son of Scadur the Hunter", 100,
			"Scadur is investigating a statue in search of his son. It has someones name on it, but he can't read the terribly written inscription without the Horrible Handwritings Translation Guide. Oh and don't forget to fetch 64 Yeti's horns, either.",
			ImmutableMap.of(ModItems.YETI_HORN, 64, ModItems.LETTER, 1), EnumQuestItem.TranslationGuide),
	BlakesBiggestFan("Blake's Biggest Fan", 100,
			"Enin is ecstatic about Blake, but now she believes he doesn't like Yetis, so she wants you to eliminate 30 of them to honor him. What a strange girl.",
			30),
	MarchOfTheYetiArmy("March of the Yeti Army", 100,
			"Dr. Kim needs your help, the Yetis are angered! You understand where they are coming from, but you're just going to ignore that. He asks you to defeat 50 of them. ",
			50),
	YetiRides("Yeti Rides", 100,
			"Alcaster tells you about the master/servant relationship between Yeti and Pepe, and how they recently discovered Yeti and Pepe are actually friends! Awww. Hunt them and collect Yeti Horns and a Pepe's beak to learn more about them.",
			ImmutableMap.of(ModItems.YETI_HORN, 30, ModItems.PEPE_BEAK, 1)),
	ASymbioticRelationship("A Symbiotic Relationship", 100,
			"Alcaster has been studying the symbiotic nature of the Yetis and Pepes, a guide and a means to travel. He's schemed a plan to ride Yeti, inspired by Ribbon Pigs, and needs some more materials for his research.",
			ImmutableMap.of(ModItems.YETI_HORN, 50, ModItems.PIGS_RIBBON, 1)),

	//Jr Yeti Skins
	GordonTheCraftsman("Gordon the Craftsman", 100,
			"Gordon says, being a great craftsman requires patience and stamina. He wants to test you by having you gather up some Jr. Yeti Skins, the primary material for shoemaking.",
			ImmutableMap.of(ModItems.JRYETI_SKIN, 30)),
	BeastTamer("Beast Tamer", 100,
			"A beggar from El Nath requests you collect 10 Jr. Yeti Skins for him. He would be very thankful. I wonder who he is...?",
			ImmutableMap.of(ModItems.JRYETI_SKIN, 10)),
	OneMoreWish("One More Wish", 100,
			"The beggar asks you to grant him one more wish and reveals his identity, he used to be a Hunter like Scadur. He wants you to collect Jr. Yeti Skins and one Yeti's Horn so he can craft himself a spear to hunt with.",
			ImmutableMap.of(ModItems.JRYETI_SKIN, 30)),
	GordonsTuition("Gordon's Tuition", 100,
			"Gordon from El Nath wants to test your skills by having you bring him 50 pieces of Jr. Yeti Skin. It's a test that can also be served as a tuition. Haha, actually I'm just writing this request so I can make more shoes. ",
			ImmutableMap.of(ModItems.JRYETI_SKIN, 50)),
	BettysResearch("Betty's Research", 100,
			"Betty's laboratory is planning to gather information and research how organisms in snowy areas adapt to their environment. Please eliminate some creatures in your area and bring back some samples for us. ",
			25, ImmutableMap.of(ModItems.JRYETI_SKIN, 10, ModItems.PEPE_BEAK, 1)),
	JrYetiKeychain("Jr. Yeti Keychain", 100,
			"Enin is a huge fan of Blake and spent tons of time finding the perfect gift for him, and now she's lost it to some Jr. Yetis. But she refuses to give up. She asks you recover her Jr. Yeti Keychain so she can gift it to her beloved superstar.",
			ImmutableMap.of(ModItems.LETTER, 1), EnumQuestItem.JrYetiKeychain),
	YetiResearchProgress("Yeti Research Progress", 100,
			"From our results it seems Pepes have evolved from simple sea birds to bovenbearing jerks. Meanwhile, the Yeti are currently in the process of evolving into creatures composed entirely of hair. The relationship between Yetis and Jr. Yetis remains unclear...",
			100),

	//SNAIL QUESTS
	BasicTraining("Basic Training", 100,
			"Shiba recommends you train by hunting an easy monster, Snails. Search for them in the area, they should be easy to find if you're not too slow. ",
			15),
	MaisTraining("Mai's Training", 100,
			"You're now under the tutelage of famous swordmaster, Mai, from Amherst. She wants you to hunt snails and mushrooms and bring back 30 Green Snail Shells, 30 Blue Snail Shells and 10 Orange Mushroom Caps.",
			10, ImmutableMap.of(ModItems.GREEN_SNAIL_SHELL, 30, ModItems.BLUE_SNAIL_SHELL, 30, ModItems.ORANGE_MUSHROOM_CAP, 10)),
	HelpHuntTheSnails("Help Hunt the Snails", 100,
			"Sam from Maple Island wants you to take care of some snails. Slay 10 snails so Sam can play carelessly in the otherwise dangerous fields.",
			10),
	MariasLetter("Maria's Letter", 100,
			"Maria is relieved you're hunting the wild snails. But she's still looking for a lost letter she sent to Chief Lucas from Amherst. She asks you hunt snails in the area to recover the letter that was intercepted by the snails.",
			10, ImmutableMap.of(ModItems.LETTER, 1), EnumQuestItem.LucasLetter),
	HelpOutYoona("Help out Yoona", 100,
			"Yoona is trapped somewhere in the forest and needs to return home, but she's afraid of the snails in the area. Please eliminate 30 of them so she can get out.",
			30),
	MaisSecondTraining("Mai's Second Training", 100,
			"Mai thinks you have talent and is turning it up a notch. This time, hunt Stumps and Red Snails for her, but don't underestimate those sneaky creatures, they're stronger than other snails and move faster then Stumps.",
			5, ImmutableMap.of(ModItems.RED_SNAIL_SHELL, 5, Items.STICK, 5)),
	MayasFirstCollection("Maya's First Collection", 100,
			"A request from Maya of Henesys, who has been collecting various items. She wants 50 of each Green, Blue and Red Snail Shells. Maybe... well at least it looks like I can get these from the monsters around this sign.",
			10, ImmutableMap.of(ModItems.GREEN_SNAIL_SHELL, 50, ModItems.BLUE_SNAIL_SHELL, 50, ModItems.RED_SNAIL_SHELL, 50)),
	TheOldSnail("The Old Snail", 100,
			"Pia in Henesys has interest in a strange book about a Snail Shell that makes wishes come true. Mrs. Ming Ming's great-grandfather Alloy is the author of the book, but it somehow was lost to the snails. Eliminate them to find the book.",
			10, ImmutableMap.of(ModItems.LETTER, 1), EnumQuestItem.SnailLegend),
	ARainbowShell("A Rainbow Shell", 100,
			"After getting his hands on the book about Mano. Pia wants to try his hand at creating a special shell of his own. He requires 10 of each Green, Blue and Red Snail Shells to research crafting a magical item like that.",
			10, ImmutableMap.of(ModItems.GREEN_SNAIL_SHELL, 10, ModItems.BLUE_SNAIL_SHELL, 10, ModItems.RED_SNAIL_SHELL, 10)),
	BobTheSnail("Bob the Snail", 100,
			"Bruce is looking into a snail who's age is incalculable. He needs you to help him further study this remarkable creature. Bring him 25 Green and Red Snail Shells, so the professional monster researcher may one day tell Bob's age.",
			10, ImmutableMap.of(ModItems.GREEN_SNAIL_SHELL, 25, ModItems.RED_SNAIL_SHELL, 25));
	
	private final String TITLE;
	private final int EXP_REWARD;
	private final String DESCRIPTION;
	private final int REQUIRED_KILLS;
	private final Map<Item, Integer> REQUIRED_ITEMS;
	private final EnumQuestItem QUEST_ITEM;
	
	private Quest(String title,  int expReward, String description) {
		this(title, expReward, description, 0, ImmutableMap.of(), EnumQuestItem.None);
	}
	
	private Quest(String title,  int expReward, String description, int requiredKills) {
		this(title, expReward, description, requiredKills, ImmutableMap.of(), EnumQuestItem.None);
	}
	
	private Quest(String title,  int expReward, String description, Map<Item, Integer> requiredItems) {
		this(title, expReward, description, 0, requiredItems, EnumQuestItem.None);
	}
	
	private Quest(String title,  int expReward, String description, EnumQuestItem questItem) {
		this(title, expReward, description, 0, ImmutableMap.of(), questItem);
	}
	
	private Quest(String title,  int expReward, String description, int requiredKills, Map<Item, Integer> requiredItems) {
		this(title, expReward, description, requiredKills, requiredItems, EnumQuestItem.None);
	}
	
	private Quest(String title,  int expReward, String description, int requiredKills, EnumQuestItem questItem) {
		this(title, expReward, description, requiredKills, ImmutableMap.of(), questItem);
	}
	
	private Quest(String title,  int expReward, String description, Map<Item, Integer> requiredItems, EnumQuestItem questItem) {
		this(title, expReward, description, 0, requiredItems, questItem);
	}
	
	private Quest(String title,  int expReward, String description, int requiredKills, Map<Item, Integer> requiredItems, EnumQuestItem questItem) {
		TITLE = title;
		EXP_REWARD = expReward;
		DESCRIPTION = description;
		REQUIRED_KILLS = requiredKills;
		REQUIRED_ITEMS = requiredItems;
		QUEST_ITEM = questItem;
	}
	
	public String getTitle() {
		return TITLE;
	}
	
	public int getExpReward() {
		return EXP_REWARD;
	}
	
	public String getDescription() {
		String format = DESCRIPTION;
		for(int i = 0; i < REQUIRED_ITEMS.size(); i++) {
			String key = "#item" + i + "#";
			String key2 = "#item_amount" + i + "#";
			if(format.contains(key)) {
				Item item = getRequiredItemByIndex(i);
				String name = item.getDisplayName(new ItemStack(item)).getString();
				format = format.replaceAll(key, name);
			}
			if(format.contains(key2)) {
				String amount = "" + getRequiredItemAmountByIndex(i);
				format = format.replaceAll(key2, amount);
			}
		}
		if(REQUIRED_KILLS > 0) {
			String key3 = "#kill_amount#";
			if(format.contains(key3)) {
				String amount = "" + getRequiredKills();
				format = format.replaceAll(key3, amount);
			}
		}	
		return format;
	}
	
	public int getRequiredKills() {
		return REQUIRED_KILLS;
	}
	
	public Map<Item, Integer> getRequiredItems() {
		return REQUIRED_ITEMS;
	}
	
	public int[] getRequiredItemAmounts() {
		int i = 0;
		int[] itemAmounts = new int[REQUIRED_ITEMS.size()];
		for(Map.Entry<Item, Integer> entry : REQUIRED_ITEMS.entrySet()) {
			itemAmounts[i] = entry.getValue();
			i++;
		}
		return itemAmounts;
	}
	
	public Item getRequiredItemByIndex(int index) {
		int i = 0;
		for(Map.Entry<Item, Integer> entry : REQUIRED_ITEMS.entrySet()) {
			if(i == index) {
				return entry.getKey();
			}
			i++;
		}
		return Items.AIR;
	}
	
	public int getRequiredItemAmountByIndex(int index) {
		int i = 0;
		for(Map.Entry<Item, Integer> entry : REQUIRED_ITEMS.entrySet()) {
			if(i == index) {
				return entry.getValue();
			}
			i++;
		}
		return 0;
	}
	
	public EnumQuestItem getRequiredQuestItem() {
		return this.QUEST_ITEM;
	}
	
	public boolean hasQuestItem() {
		return(this.QUEST_ITEM != EnumQuestItem.None);
	}
}
