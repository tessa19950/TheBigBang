package com.homebrewCult.TheBigBang.blocks;

import com.homebrewCult.TheBigBang.gui.quests.Questline;
import com.homebrewCult.TheBigBang.util.DangerSignPart;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DangerSignBlock extends ContainerBlock {

	protected static final VoxelShape SHAPE_VERTICAL = Block.makeCuboidShape(0.0D, 0.0D, 5.0D, 16.0D, 16.0D, 11.0D);
	protected static final VoxelShape SHAPE_HORIZONTAL = Block.makeCuboidShape(5.0D, 0.0D, 0.0D, 11.0D, 16.0D, 16.0D);
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final EnumProperty<DangerSignPart> PART = EnumProperty.create("part", DangerSignPart.class);
	public static final EnumProperty<Questline> QUESTLINE = EnumProperty.create("questline", Questline.class);
	
	public DangerSignBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(PART, DangerSignPart.BASE).with(QUESTLINE, Questline.None));
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
        	case WEST: case EAST:
				return SHAPE_HORIZONTAL;
			default: case NORTH:
            return SHAPE_VERTICAL;
	    }
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
		builder.add(PART);
		builder.add(QUESTLINE);
	}

	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isRemote) {
			return true;
		} else {
			TileEntity tileEntity = worldIn.getTileEntity(getBasePartPosition(state, pos));
			if (tileEntity instanceof DangerSignTile) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider)tileEntity, tileEntity.getPos());
			} 
			return true;
      	}
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		for (BlockPos partPos : getOtherPartPositions(state, pos)) {
			worldIn.removeBlock(partPos, false);
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	private static BlockPos getBasePartPosition(BlockState state, BlockPos pos) {
		switch (state.get(PART)) {
		case BASE:
			return pos;
		case TOPLEFT:
			return pos.down();
		case BOTTOMRIGHT:
			return pos.offset(state.get(FACING).rotateY());
		default: case TOPRIGHT:
			return pos.offset(state.get(FACING).rotateY()).down();
		}
	}
	
	private static BlockPos[] getOtherPartPositions(BlockState state, BlockPos pos) {
		BlockPos[] positions = new BlockPos[3];
		switch (state.get(PART)) {
		case BASE:
			positions[0] = pos.up();
			positions[1] = pos.offset(state.get(FACING).rotateYCCW());
			positions[2] = pos.offset(state.get(FACING).rotateYCCW()).up();
			break;
		case TOPLEFT:
			positions[0] = pos.down();
			positions[1] = pos.offset(state.get(FACING).rotateYCCW());
			positions[2] = pos.offset(state.get(FACING).rotateYCCW()).down();
			break;
		case BOTTOMRIGHT:
			positions[0] = pos.up();
			positions[1] = pos.offset(state.get(FACING).rotateY());
			positions[2] = pos.offset(state.get(FACING).rotateY()).up();
			break;
		default: case TOPRIGHT:
			positions[0] = pos.down();
			positions[1] = pos.offset(state.get(FACING).rotateY());
			positions[2] = pos.offset(state.get(FACING).rotateY()).down();
			break;
		}
		return positions;
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new DangerSignTile();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	@Override
    public BlockRenderLayer getRenderLayer() {
    	return BlockRenderLayer.CUTOUT;
    }
}
