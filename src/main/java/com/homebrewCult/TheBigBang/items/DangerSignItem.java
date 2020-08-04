package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.blocks.DangerSignBlock;
import com.homebrewCult.TheBigBang.gui.quests.Questline;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.util.DangerSignPart;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;

public class DangerSignItem extends BlockItem {

	public DangerSignItem(Block blockIn, Properties builder) {
		super(blockIn, builder);
	}
	
	@Override
	public ActionResultType tryPlace(BlockItemUseContext context) {
		if(isValidDangerSignLocation(context)) {
			return super.tryPlace(context);
		} else {
			return ActionResultType.FAIL;
		}
	}
	
	@Override
	protected boolean placeBlock(BlockItemUseContext context, BlockState state) {	
		placeDangerSignParts(context);
		Questline questline = Questline.getQuestlineByBiome(context.getWorld().getBiome(context.getPos()));
		return super.placeBlock(context, state.with(DangerSignBlock.QUESTLINE, questline));
	}
	
	private void placeDangerSignParts(BlockItemUseContext context) {
		Direction facing = context.getPlacementHorizontalFacing().getOpposite();
		BlockState state = ModBlocks.DANGER_SIGN.getDefaultState().with(DangerSignBlock.FACING, facing);
		context.getWorld().setBlockState(context.getPos().up(), state.with(DangerSignBlock.PART, DangerSignPart.TOPLEFT));
		context.getWorld().setBlockState(context.getPos().offset(facing.rotateYCCW()), state.with(DangerSignBlock.PART, DangerSignPart.BOTTOMRIGHT));
		context.getWorld().setBlockState(context.getPos().offset(facing.rotateYCCW()).up(), state.with(DangerSignBlock.PART, DangerSignPart.TOPRIGHT));
	}
	
	private static boolean isValidDangerSignLocation(BlockItemUseContext context) {
		Direction facing = context.getPlacementHorizontalFacing();
		boolean flag1 = (context.getPos().getY() < 255);
		boolean flag2 = (context.getWorld().getBlockState(context.getPos().up()).isReplaceable(context));
		boolean flag3 = (context.getWorld().getBlockState(context.getPos().offset(facing.rotateY())).isReplaceable(context));
		boolean flag4 = (context.getWorld().getBlockState(context.getPos().offset(facing.rotateY()).up()).isReplaceable(context));
		return (flag1 && flag2 && flag3 && flag4); 
	}
}
