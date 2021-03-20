package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.blocks.RopeFenceBlock;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;

import javax.annotation.Nullable;

public class RopeFenceItem extends BlockItem {
    public RopeFenceItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }
}
