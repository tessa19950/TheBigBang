package com.homebrewCult.TheBigBang.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlockColorHandler implements IBlockColor {

	public static final IBlockColor INSTANCE = new BlockColorHandler();
	
	@Override
	public int getColor(BlockState state, IEnviromentBlockReader biome, BlockPos pos, int p_getColor_4_) {
		return biome.getBiome(pos).getGrassColor(pos);
	}
	
}
