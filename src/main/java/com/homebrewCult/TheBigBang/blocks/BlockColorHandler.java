package com.homebrewCult.TheBigBang.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILightReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.level.ColorResolver;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class BlockColorHandler implements IBlockColor {

	public static final IBlockColor INSTANCE = new BlockColorHandler();
	public static final ColorResolver COLOR_RESOLVER = new BigBangGrassColorResolver();

	@Override
	public int getColor(BlockState blockState, @Nullable ILightReader iLightReader, @Nullable BlockPos blockPos, int i) {
		if(iLightReader != null && blockPos != null)
			 iLightReader.getBlockColor(blockPos, COLOR_RESOLVER);
		return 0;
	}

	private static class BigBangGrassColorResolver implements ColorResolver {
		@Override
		public int getColor(Biome biome, double v, double v1) {
			return biome.getGrassColor(v, v1);
		}
	}
}
