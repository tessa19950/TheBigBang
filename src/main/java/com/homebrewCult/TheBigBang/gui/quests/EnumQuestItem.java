package com.homebrewCult.TheBigBang.gui.quests;

import net.minecraft.util.IStringSerializable;

public enum EnumQuestItem implements IStringSerializable {

	None("Untitled Quest Item", 1),
	BlackbullsDeed("Blackbull's Deed of the Land", 1),
	AncientScroll("Ancient Scroll", 1),
	AntiqueScroll("Antique Scroll", 1),
	RolyPolysTools("Roly Poly's Tools", 1),
	OctopusMail("Octopus' Private Mail", 1),
	VaccineRecipe("Vaccine Recipe", 1),
	NotBreathingPotionRecipe("Recipe for Potion of Not-Breating", 1),
	PackagedFrog("Packaged Experimental Frog", 1),
	LostBook("Lost Book", 1),
	MissingReport("Missing Research Report", 1),
	ManjisDiary("Manji's Old Diary", 1),
	TranslationGuide("Horrible Handwritings Translation Guide", 1),
	JrYetiKeychain("Jr. Yeti Keychain", 1),
	LucasLetter("Letter for Chief Lucas", 1),
	SnailLegend("Legend of Mano the Snail", 1);
	
	private final String questItemName;
	private final float dropPercentage;
	
	EnumQuestItem(String questItemNameIn, int dropPercentageIn) {
		this.questItemName = questItemNameIn;
		this.dropPercentage = dropPercentageIn;
	}

	public static EnumQuestItem getQuestItemByIndex(int index) {
		EnumQuestItem[] q = EnumQuestItem.values();
		return q[index];
	}
	
	public String getQuestItemName() {
		return this.questItemName;
	}
	
	public float getDropPercentage() {
		return this.dropPercentage;
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
