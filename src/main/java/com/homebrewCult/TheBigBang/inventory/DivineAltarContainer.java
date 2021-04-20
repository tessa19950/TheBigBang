package com.homebrewCult.TheBigBang.inventory;

import com.homebrewCult.TheBigBang.blocks.DivineAltarTile;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModRecipeTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DivineAltarContainer extends AbstractFurnaceContainer {

	public DivineAltarContainer(int id, PlayerInventory playerInventory, World world, BlockPos pos) {
		super(ModBlocks.DIVINE_ALTAR_CONTAINER, ModRecipeTypes.BLESSING_RECIPE, id, playerInventory);
	}
	
	public DivineAltarContainer(int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray furnaceData) {
		super(ModBlocks.DIVINE_ALTAR_CONTAINER, ModRecipeTypes.BLESSING_RECIPE, id, playerInventoryIn, furnaceInventoryIn, furnaceData);
	}
	
	@Override
	protected boolean isFuel(ItemStack stack) {
		return DivineAltarTile.isFuel(stack);
	}
	
}
