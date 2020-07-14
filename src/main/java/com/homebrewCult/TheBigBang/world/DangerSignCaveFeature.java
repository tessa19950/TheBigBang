package com.homebrewCult.TheBigBang.world;

import java.util.Random;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;

public class DangerSignCaveFeature extends AbstractDangerSignFeature {
	
	public static final BlockIgnoreStructureProcessor BEDROCK = new BlockIgnoreStructureProcessor(ImmutableList.of(Blocks.BEDROCK));
	
	public DangerSignCaveFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		BlockPos placePos = new BlockPos(pos.getX(), rand.nextInt(pos.getY() - 8), pos.getZ());	
		return super.place(worldIn, generator, rand, placePos, config, DangerSignCaveFeature.BEDROCK);
	}
	
	@Override
	public String getTemplateName() {
		return "cave_sign";
	}
	
	@Override
	public Vec3i getTemplateOffset(int index) {
		switch(index) {
		case 0: return new Vec3i(-5,-3,-5);
		case 1: return new Vec3i(-5,-3,-10);
		case 2: return new Vec3i(-5,-3,-5);
		case 3: return new Vec3i(-5,-3,-10);
		default: return new Vec3i(0,0,0);
		}
	}
	
	@Override
	public Direction getTemplateDirection(int index) {
		switch(index) {
		case 0: return Direction.SOUTH;
		case 1: return Direction.EAST;
		case 2: return Direction.NORTH;
		case 3: return Direction.WEST;
		default: return Direction.NORTH;
		}
	}
}
