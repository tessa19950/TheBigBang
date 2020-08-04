package com.homebrewCult.TheBigBang.gui.quests;

import net.minecraft.util.IStringSerializable;

public enum EnumQuestItem implements IStringSerializable {

	None("Untitled Quest Item", 30),
	BlackbullsDeedOfTheLand("Blackbull's Deed of the Land", 30),
	AncientScroll("Ancient Scroll", 30),
	AntiqueScroll("Antique Scroll", 30);
	
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
