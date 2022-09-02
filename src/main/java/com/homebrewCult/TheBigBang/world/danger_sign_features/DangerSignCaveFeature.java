package com.homebrewCult.TheBigBang.world.danger_sign_features;

import java.util.Random;
import java.util.function.Function;
import com.google.common.collect.ImmutableList;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.gui.quests.Questline;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;

public class DangerSignCaveFeature extends AbstractDangerSignFeature {
	
	public static final BlockIgnoreStructureProcessor BEDROCK_AND_STUCTURE_BLOCK = new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.BEDROCK, Blocks.STRUCTURE_BLOCK));
	
	public DangerSignCaveFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		BlockPos placePos = new BlockPos(pos.getX(), Math.max(rand.nextInt(pos.getY() - 8), 16), pos.getZ());
		return super.place(worldIn, generator, rand, placePos, config, DangerSignCaveFeature.BEDROCK_AND_STUCTURE_BLOCK);
	}
	
	@Override
	public String getTemplateName() { return TheBigBang.MODID + ":cave_sign"; }
	
	@Override
	public Vec3i getTemplateOffset(int index) {
		switch(index) {
		case 0: case 2:
				return new Vec3i(-5,-3,-5);
		case 1: case 3:
				return new Vec3i(-5,-3,-10);
			default: return new Vec3i(0,0,0);
		}
	}
	
	@Override
	public Direction getTemplateDirection(int index) {
		switch(index) {
		case 0: return Direction.SOUTH;
		case 1: return Direction.EAST;
		case 3: return Direction.WEST;
		default: return Direction.NORTH;
		}
	}
	
	@Override
	public Questline getTemplateQuestline(Biome biome) {
		int r = (int)(Math.random() * 3);
		switch(r) {
		case 0: return Questline.ZombieMushrooms;
		case 1: return Questline.Eyes;
		case 2: default: return Questline.Drakes;
		}
	}
}
