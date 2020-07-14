package com.homebrewCult.TheBigBang.gui.quests;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.util.IStringSerializable;

public enum Questline implements IStringSerializable { 		
	Octopuses(ModEntities.OCTOPUS_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.Polluted2}),
	EvilEyes(ModEntities.EVIL_EYE_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.LazyLittleCalico, Quest.ArwensPaint, Quest.StrangeDish_EvilEye, Quest.Polluted2}),
	CurseEyes(ModEntities.CURSE_EYE_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.ArwensPaint, Quest.StrangeDish_CurseEye, Quest.Polluted2}),
	ColdEyes(ModEntities.COLD_EYE_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.StrangeDish_ColdEye, Quest.Polluted2}),
	OrangeMushrooms(ModEntities.ORANGE_MUSHROOM_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.Polluted2}),
	BlueMushrooms(ModEntities.BLUE_MUSHROOM_ENTITY, 
	new Quest[] {Quest.ANewHouseForBlackbull, Quest.Polluted, Quest.Polluted2}),
	ZombieMushrooms(ModEntities.ZOMBIE_MUSHROOM_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.Polluted2}),
	GreenSnails(ModEntities.GREEN_SNAIL_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.Polluted2}),
	BlueSnails(ModEntities.BLUE_SNAIL_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.Polluted2}),
	RedSnails(ModEntities.RED_SNAIL_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.Polluted2}),
	JrYetis(ModEntities.JRYETI_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.Polluted2}),
	DarkJrYetis(ModEntities.DARK_JRYETI_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.Polluted2}),
	Stumps(ModEntities.STUMP_ENTITY, 
	new Quest[] {Quest.Polluted, Quest.Polluted2});
	
	private final EntityType<?> QUESTLINE_ENTITY;
	private final Quest[] QUESTS;
	
	private Questline(EntityType<?> questlineEntity, Quest[] quests) {
		QUESTLINE_ENTITY = questlineEntity;
		QUESTS = quests;
	}
	
	public EntityType<?> getEntityType() {
		return QUESTLINE_ENTITY;
	}
	
	public Quest[] getQuests() {
		return QUESTS;
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
		switch (index) {
		case 1:
			return EvilEyes;
		case 2:
			return CurseEyes;
		case 3:
			return ColdEyes;
		case 4:
			return OrangeMushrooms;
		case 5:
			return BlueMushrooms;
		case 6:
			return ZombieMushrooms;
		case 7:
			return GreenSnails;
		case 8:
			return BlueSnails;
		case 9:
			return RedSnails;
		case 10:
			return JrYetis;
		case 11:
			return DarkJrYetis;
		case 12:
			return Stumps;
		default: case 0:
			return Octopuses;
		}
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
