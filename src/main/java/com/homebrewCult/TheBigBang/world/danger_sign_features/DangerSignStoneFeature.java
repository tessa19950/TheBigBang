package com.homebrewCult.TheBigBang.world.danger_sign_features;

import java.util.Random;
import java.util.function.Function;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DangerSignStoneFeature extends AbstractDangerSignFeature {
	
	public DangerSignStoneFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		//Find the nearest non-air and non-water block 
		BlockPos placePos = pos;
		boolean foundSurface = false;
		for(int i = 0; !foundSurface; i++) {
			if(i < 16) {
				Block b = worldIn.getBlockState(pos.down(i)).getBlock();
				if(b == Blocks.AIR || b == Blocks.WATER || b == Blocks.ACACIA_LEAVES || b == Blocks.BIRCH_LEAVES || 
				b == Blocks.DARK_OAK_LEAVES || b == Blocks.JUNGLE_LEAVES || b == Blocks.OAK_LEAVES || b == Blocks.SPRUCE_LEAVES) {
					placePos = pos.down(i);
				} else {
					foundSurface = true;
				}
			}
		}
		return super.place(worldIn, generator, rand, placePos, config);	
	}
	
	@Override
	public String getTemplateName() {
		return "stone_sign";
	}
	
	@Override
	public Vec3i getTemplateOffset(int index) {
		switch(index) {
			case 0: return new Vec3i(-3,-4,-2);
			case 1: return new Vec3i(-3,-4,-2);
			case 2: return new Vec3i(-2,-4,-3);
			case 3: return new Vec3i(-2,-4,-3);
			default: return new Vec3i(0,0,0);
		}
	}
	
	@Override
	public Direction getTemplateDirection(int index) {
		switch(index) {
			case 0: return Direction.SOUTH;
			case 1: return Direction.NORTH;
			case 2: return Direction.EAST;
			case 3: return Direction.WEST;
			default: return Direction.NORTH;
		}
	}
}
