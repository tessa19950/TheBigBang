package com.homebrewCult.TheBigBang.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

import java.util.Random;

public interface IQuestEntity {
	QuestEntityHandler getQuestEntityHandler();

	default void spawnPoofParticles(Entity entity, World world, Random rand) {
		for(int i = 0; i < 16; i++) {
			double x = entity.posX + rand.nextGaussian();
			double y = entity.posY + 1 + rand.nextGaussian();
			double z = entity.posZ + rand.nextGaussian();
			world.addParticle(ParticleTypes.POOF, x, y, z, 0, 0, 0);
		}
	}
}
