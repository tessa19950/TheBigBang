package com.homebrewCult.TheBigBang.blocks;

import com.homebrewCult.TheBigBang.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class RopeFenceBlock extends HorizontalBlock {

    public static final BooleanProperty SIDE_LEFT = BooleanProperty.create("side_left");
    public static final BooleanProperty SIDE_RIGHT = BooleanProperty.create("side_right");
    public static final BooleanProperty CORNER_LEFT = BooleanProperty.create("corner_left");
    public static final BooleanProperty CORNER_RIGHT = BooleanProperty.create("corner_right");

    private final VoxelShape[] renderShapes;
    private final VoxelShape[] collisionShapes;

    public RopeFenceBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.getStateContainer().getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(SIDE_LEFT, false).with(SIDE_RIGHT, false).with(CORNER_LEFT, false).with(CORNER_RIGHT, false));
        this.renderShapes = this.makeShapes(16D);
        this.collisionShapes = this.makeShapes(24D);
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        BlockState state = getConnectionsForFenceState(worldIn, stateIn, currentPos, stateIn.get(HORIZONTAL_FACING));
        worldIn.setBlockState(currentPos, state, 2);
        return stateIn;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = getConnectionsForFenceState(context.getWorld(), getBlock().getDefaultState(), context.getPos(), context.getPlacementHorizontalFacing());
        return context.canPlace() ? state.with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing()) : null;
    }

    public static BlockState getConnectionsForFenceState(IWorld worldIn, BlockState fenceState, BlockPos fencePos, Direction fenceDirection) {
        BlockState leftNeighbor = worldIn.getBlockState(fencePos.offset(fenceDirection.rotateYCCW()));
        boolean left = leftNeighbor.isSolid() || leftNeighbor.getBlock().equals(ModBlocks.ROPE_FENCE) && leftNeighbor.get(HORIZONTAL_FACING) != fenceDirection.getOpposite();
        BlockState rightNeighbor = worldIn.getBlockState(fencePos.offset(fenceDirection.rotateY()));
        boolean right = rightNeighbor.isSolid() || rightNeighbor.getBlock().equals(ModBlocks.ROPE_FENCE) && rightNeighbor.get(HORIZONTAL_FACING) != fenceDirection.getOpposite();
        BlockState backNeighbor = worldIn.getBlockState(fencePos.offset(fenceDirection.getOpposite()));
        boolean cornerLeft = backNeighbor.getBlock().equals(ModBlocks.ROPE_FENCE) && backNeighbor.get(HORIZONTAL_FACING) == fenceDirection.rotateYCCW();
        boolean cornerRight = backNeighbor.getBlock().equals(ModBlocks.ROPE_FENCE) && backNeighbor.get(HORIZONTAL_FACING) == fenceDirection.rotateY();
        return fenceState.with(SIDE_LEFT, left).with(SIDE_RIGHT, right).with(CORNER_LEFT, cornerLeft).with(CORNER_RIGHT, cornerRight);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(new IProperty[]{HORIZONTAL_FACING, SIDE_LEFT, SIDE_RIGHT, CORNER_LEFT, CORNER_RIGHT});
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        int dir = state.get(HORIZONTAL_FACING).getHorizontalIndex();
        if (state.get(CORNER_LEFT)) {
            return renderShapes[20 + dir];
        } else if (state.get(CORNER_RIGHT)) {
            return renderShapes[16 + dir];
        } else if (state.get(SIDE_LEFT) && state.get(SIDE_RIGHT)) {
            return renderShapes[12 + dir];
        } else if (state.get(SIDE_LEFT)) {
            return renderShapes[4 + dir];
        } else if (state.get(SIDE_RIGHT)) {
            return renderShapes[8 + dir];
        } else {
            return renderShapes[0 + dir];
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        int dir = state.get(HORIZONTAL_FACING).getHorizontalIndex();
        if (state.get(CORNER_LEFT)) {
            return collisionShapes[20 + dir];
        } else if (state.get(CORNER_RIGHT)) {
            return collisionShapes[16 + dir];
        } else if (state.get(SIDE_LEFT) && state.get(SIDE_RIGHT)) {
            return collisionShapes[12 + dir];
        } else if (state.get(SIDE_LEFT)) {
            return collisionShapes[4 + dir];
        } else if (state.get(SIDE_RIGHT)) {
            return collisionShapes[8 + dir];
        } else {
            return collisionShapes[0 + dir];
        }
    }

    protected VoxelShape[] makeShapes(double height) {
        VoxelShape poleEast = Block.makeCuboidShape(14.0D, 0.0D, 6.5D, 16.0D, height, 9.5D);
        VoxelShape poleWest = Block.makeCuboidShape(0.0D, 0.0D, 6.5D, 2.0D, height, 9.5D);
        VoxelShape poleSouth = Block.makeCuboidShape(6.5D, 0.0D, 14.0D, 9.5D, height, 16.0D);
        VoxelShape poleNorth = Block.makeCuboidShape(6.5D, 0.0D, 0.0D, 9.5D, height, 2.0D);

        VoxelShape leftEast = Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, height, 9.5D);
        VoxelShape leftWest = Block.makeCuboidShape(0.0D, 0.0D, 6.5D, 2.0D, height, 16D);
        VoxelShape leftSouth = Block.makeCuboidShape(6.5D, 0.0D, 14.0D, 16D, height, 16.0D);
        VoxelShape leftNorth = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 9.5D, height, 2.0D);

        VoxelShape rightEast = Block.makeCuboidShape(14.0D, 0.0D, 6.5D, 16.0D, height, 16.0D);
        VoxelShape rightWest = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, height, 9.5D);
        VoxelShape rightSouth = Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 9.5D, height, 16.0D);
        VoxelShape rightNorth = Block.makeCuboidShape(6.5D, 0.0D, 0.0D, 16.0D, height, 2.0D);

        VoxelShape fullEast = Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, height, 16.0D);
        VoxelShape fullWest = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, height, 16.0D);
        VoxelShape fullSouth = Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, height, 16.0D);
        VoxelShape fullNorth = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, height, 2.0D);

        VoxelShape[] avoxelshape = new VoxelShape[]{poleSouth, poleWest, poleNorth, poleEast,
                leftSouth, leftWest, leftNorth, leftEast, rightSouth, rightWest, rightNorth, rightEast, fullSouth, fullWest, fullNorth, fullEast,
                VoxelShapes.or(fullSouth, fullWest), VoxelShapes.or(fullWest, fullNorth), VoxelShapes.or(fullNorth, fullEast), VoxelShapes.or(fullEast, fullSouth),
                VoxelShapes.or(fullEast, fullSouth), VoxelShapes.or(fullSouth, fullWest), VoxelShapes.or(fullWest, fullNorth), VoxelShapes.or(fullNorth, fullEast)};
        return avoxelshape;
    }
}
