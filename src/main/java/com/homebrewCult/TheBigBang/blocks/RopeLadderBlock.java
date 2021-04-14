package com.homebrewCult.TheBigBang.blocks;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class RopeLadderBlock extends LadderBlock {
    public RopeLadderBlock(Properties builder) {
        super(builder);
    }

    private boolean canAttachTo(IBlockReader p_196471_1_, BlockPos p_196471_2_, Direction p_196471_3_) {
        return true;
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(handIn);
        BlockItemUseContext useContext = new BlockItemUseContext(new ItemUseContext(player, handIn, hit));
        if(stack.getItem().equals(ModItems.ROPE_LADDER)) {
            BlockPos downPos = pos.down();
            while(downPos.getY() >= 0) {
                BlockState downState = worldIn.getBlockState(downPos);
                if(downState.equals(state)) {
                    downPos = downPos.down();
                } else {
                    if (downState.isReplaceable(useContext)) {
                        worldIn.playSound(player, downPos, SoundEvents.BLOCK_LADDER_PLACE, SoundCategory.BLOCKS, 1, 1);
                        worldIn.setBlockState(downPos, state);
                        if (!player.isCreative())
                            stack.shrink(1);
                        return true;
                    } else {
                        break;
                    }
                }
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
