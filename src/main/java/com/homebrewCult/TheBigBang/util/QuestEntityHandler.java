package com.homebrewCult.TheBigBang.util;

import java.util.ArrayList;
import java.util.Random;
import com.homebrewCult.TheBigBang.gui.quests.EnumQuestItem;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;

public class QuestEntityHandler {

	private ArrayList<EnumQuestItem> questItems = new ArrayList<EnumQuestItem>();
	
	public void addQuestItem(EnumQuestItem questItemIn) {
		questItems.add(questItemIn);
	}
	
	public void removeQuestItem(EnumQuestItem questItemIn) {
		questItems.remove(questItemIn);
	}
	
	public void onQuestEntityDeath(LivingEntity entity, DamageSource cause) {
		if(entity.isServerWorld()) {
			Random rand = new Random();
			for(EnumQuestItem item : this.questItems) {
				if(rand.nextFloat() < item.getDropPercentage() / 100) {
					ItemStack stack = new ItemStack(ModItems.LETTER);
					CompoundNBT tag = stack.getOrCreateTag();
					tag.putInt("quest_item", item.ordinal());
					stack.setTag(tag);
					entity.getEntityWorld().addEntity(new ItemEntity(entity.world, entity.posX, entity.posY, entity.posZ, stack));
				}
			}
		}
	}
}
