package com.homebrewCult.TheBigBang.blocks;

import java.util.Random;

import com.homebrewCult.TheBigBang.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraftforge.common.IPlantable;

public class GrassyGolemStoneBlock extends Block implements IBlockColor {
	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	public GrassyGolemStoneBlock(Properties builder) {
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}

	@Override
	public int getColor(BlockState state, IEnviromentBlockReader biome, BlockPos pos, int p_getColor_4_) {
		return biome.getBiome(pos).getGrassColor(pos);
	}
	
	@Override
    public BlockRenderLayer getRenderLayer() {
    	return BlockRenderLayer.CUTOUT_MIPPED;
    }
	
	@Override
	public boolean isSolid(BlockState state) {
		return true;
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
		return true;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if(!worldIn.isRemote) {
			if(!worldIn.isAreaLoaded(pos, 2)) {
				return; 
			}
			if(!isValidGrassPos(state, worldIn, pos)) {
				worldIn.setBlockState(pos, ModBlocks.GOLEM_STONE.getDefaultState().with(FACING, worldIn.getBlockState(pos).get(FACING)));
			}
		}
	}
	
    private static boolean isValidGrassPos(BlockState state, IWorldReader worldIn, BlockPos pos) {
    	BlockPos upPos = pos.up();
    	BlockState upBlockState = worldIn.getBlockState(upPos);
    	if (upBlockState.getBlock() == Blocks.SNOW && upBlockState.get(SnowBlock.LAYERS) == 1) {
    		return true;
    	} else {
    		int i = LightEngine.func_215613_a(worldIn, state, pos, upBlockState, upPos, Direction.UP, upBlockState.getOpacity(worldIn, upPos));
    		return i < worldIn.getMaxLightLevel();
    	}
    }
	
	@Override
	public boolean ticksRandomly(BlockState state) {
		return true;
	}
}
