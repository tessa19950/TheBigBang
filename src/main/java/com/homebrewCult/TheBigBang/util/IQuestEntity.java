package com.homebrewCult.TheBigBang.util;

import com.homebrewCult.TheBigBang.gui.quests.QuestItems;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

public interface IQuestEntity {
	QuestEntityHandler getQuestEntityHandler();

	default void spawnPoofParticles(Entity entity, Random rand) {
		for(int i = 0; i < 16; i++) {
			double x = entity.posX + rand.nextGaussian();
			double y = entity.posY + 1 + rand.nextGaussian();
			double z = entity.posZ + rand.nextGaussian();
			entity.world.addParticle(ParticleTypes.POOF, x, y, z, 0, 0, 0);
		}
	}

	default void onQuestEntityDeath(LivingEntity entity, DamageSource cause) {
		if(entity.isServerWorld()) {
			Random rand = new Random();
			for(QuestItems item : getQuestEntityHandler().getQuestItems()) {
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
