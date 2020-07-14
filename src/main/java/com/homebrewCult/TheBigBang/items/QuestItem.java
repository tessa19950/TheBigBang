package com.homebrewCult.TheBigBang.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class QuestItem extends Item {

	public ITextComponent questItemName;
	
	public QuestItem(Properties properties) {
		this(properties, "null");
	}
	
	public QuestItem(Properties properties, String questItemNameIn) {
		super(properties);
		questItemName = new StringTextComponent(questItemNameIn);
	}
	
	public void setQuestItemName(String questItemNameIn) {
		questItemName = new StringTextComponent(questItemNameIn);
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		return questItemName;
	}
}
