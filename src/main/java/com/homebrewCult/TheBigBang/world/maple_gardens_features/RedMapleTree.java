package com.homebrewCult.TheBigBang.world.maple_gardens_features;

import java.util.Random;
import com.homebrewCult.TheBigBang.init.ModBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;

public class RedMapleTree extends Tree {

	public RedMapleTree() {

	}

	@Nullable
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean b) {
		return null;
	}
}
