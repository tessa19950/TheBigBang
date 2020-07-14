package com.homebrewCult.TheBigBang.entities;

import java.util.ArrayList;
import net.minecraft.item.Item;

public interface IQuestEntity {
	
	ArrayList<Item> getQuestItems();
	
	default void addQuestItem(Item itemIn) {
		getQuestItems().add(itemIn);
	}
	
	default void removeQuestItem(Item itemIn) {
		getQuestItems().remove(itemIn);
	}
}
