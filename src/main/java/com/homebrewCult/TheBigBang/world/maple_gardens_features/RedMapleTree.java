package com.homebrewCult.TheBigBang.world.maple_gardens_features;

import java.util.Random;
import com.homebrewCult.TheBigBang.init.ModBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class RedMapleTree extends Tree {
	@Override
	protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
		return new MapleTreeFeature(NoFeatureConfig::deserialize, false, 5, Blocks.OAK_LOG.getDefaultState(), ModBlocks.RED_MAPLE_LEAVES.getDefaultState(), false);
	}
}
