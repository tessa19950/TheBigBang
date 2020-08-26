package com.homebrewCult.TheBigBang.inventory;

import com.homebrewCult.TheBigBang.blocks.MonsterFurnaceTile;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModRecipeTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MonsterFurnaceContainer extends AbstractFurnaceContainer {

	public MonsterFurnaceContainer(int id, PlayerInventory playerInventory, World world, BlockPos pos) {
		super(ModBlocks.MONSTER_FURNACE_CONTAINER, ModRecipeTypes.REFINING_RECIPE_TYPE, id, playerInventory);
	}
	
	public MonsterFurnaceContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray furnaceData) {
		super(ModBlocks.MONSTER_FURNACE_CONTAINER, ModRecipeTypes.REFINING_RECIPE_TYPE, id, playerInventoryIn, furnaceInventoryIn, furnaceData);
	}
	
	@Override
	protected boolean isFuel(ItemStack stack) {
		return MonsterFurnaceTile.isFuel(stack);
	}
	
}
