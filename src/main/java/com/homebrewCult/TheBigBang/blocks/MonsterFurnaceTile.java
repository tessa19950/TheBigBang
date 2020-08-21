package com.homebrewCult.TheBigBang.blocks;

import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModRecipeTypes;
import com.homebrewCult.TheBigBang.inventory.MonsterFurnaceContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class MonsterFurnaceTile extends AbstractFurnaceTileEntity {

	public MonsterFurnaceContainer container;
	
	public MonsterFurnaceTile() {
		super(ModBlocks.MONSTER_FURNACE_TILE, ModRecipeTypes.REFINING_RECIPE_TYPE);
	}
	
	public MonsterFurnaceTile(TileEntityType<?> tileTypeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn) {
		super(tileTypeIn, recipeTypeIn);
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new StringTextComponent("Monster Furnace");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory playerInventory) {
		return new MonsterFurnaceContainer(id, playerInventory, this, this.furnaceData);
	}

}
