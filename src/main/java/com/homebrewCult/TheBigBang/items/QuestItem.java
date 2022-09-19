package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.gui.quests.QuestItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class QuestItem extends Item {
	
	public QuestItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) {
		if(validQuestItem(stack)) {
			QuestItems q = QuestItems.getQuestItemByIndex(stack.getTag().getInt("quest_item"));
			return new StringTextComponent(q.getQuestItemName());
		} else {
			return new StringTextComponent("NULL");
		}
	}
	
	private boolean validQuestItem(ItemStack stack) {
		if(stack.hasTag()) {
			if(stack.getTag().contains("quest_item")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
