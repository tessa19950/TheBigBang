package com.homebrewCult.TheBigBang.util;

import java.util.ArrayList;

import com.homebrewCult.TheBigBang.gui.quests.QuestItems;

public class QuestEntityHandler {

	private ArrayList<QuestItems> questItems = new ArrayList<QuestItems>();

	public ArrayList<QuestItems> getQuestItems() { return questItems; }

	public void addQuestItem(QuestItems questItemIn) {
		questItems.add(questItemIn);
	}
	
	public void removeQuestItem(QuestItems questItemIn) {
		questItems.remove(questItemIn);
	}
}
