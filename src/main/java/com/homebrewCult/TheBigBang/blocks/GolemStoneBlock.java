package com.homebrewCult.TheBigBang.blocks;

import java.util.Random;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

public class GolemStoneBlock extends Block {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	public GolemStoneBlock(Properties builder) {
		super(builder);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 1))
			return;
		if (worldIn.getLight(pos.up()) >= 9) {
			for(int i = 0; i < 4; i++) {
				BlockPos otherBlockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				Block otherBlock = worldIn.getBlockState(otherBlockpos).getBlock();
				if(otherBlock == Blocks.GRASS_BLOCK || otherBlock == ModBlocks.GRASSY_GOLEM_STONE || otherBlock == ModBlocks.GRASSY_DARK_GOLEM_STONE) {
					if(canSpreadTo(state, worldIn, pos)) {
						worldIn.setBlockState(pos, ModBlocks.GRASSY_GOLEM_STONE.getDefaultState().with(FACING, worldIn.getBlockState(pos).get(FACING)));
					}
				}
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
	
    private static boolean canSpreadTo(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return isValidGrassPos(state, worldIn, pos) && !worldIn.getFluidState(blockpos).isTagged(FluidTags.WATER);
     }
	
	@Override
	public boolean ticksRandomly(BlockState state) {
		return true;
	}
}
