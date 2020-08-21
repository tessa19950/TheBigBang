package com.homebrewCult.TheBigBang.crafting;

import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModRecipeTypes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RefiningRecipe extends AbstractCookingRecipe implements IRecipe<IInventory> {
	
	public final IRecipeType<?> type;
	public final ResourceLocation id;
	public final String group;
	public final Ingredient ingredient;
	public final ItemStack result;
	public final float experience;
	public final int cookTime;
	
	public RefiningRecipe(ResourceLocation idIn, String groupIn, Ingredient ingredientIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
		super(ModRecipeTypes.REFINING_RECIPE_TYPE, idIn, groupIn, ingredientIn, resultIn, experienceIn, cookTimeIn);
		this.type = ModRecipeTypes.REFINING_RECIPE_TYPE;
		this.id = idIn;
		this.group = groupIn;
		this.ingredient = ingredientIn;
		this.result = resultIn;
		this.experience = experienceIn;
		this.cookTime = cookTimeIn;
	}

	public ItemStack getIcon() {
		return new ItemStack(ModBlocks.MONSTER_FURNACE);
	}

	public IRecipeSerializer<?> getSerializer() {
		return ModRecipeTypes.REFINING.get();
	}

	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return this.ingredient.test(inv.getStackInSlot(0));
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		return this.result.copy();
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
	    nonnulllist.add(this.ingredient);
	    return nonnulllist;
	}

	public float getExperience() {
		return this.experience;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return this.result;
	}

	@Override
	public String getGroup() {
		return this.group;
	}
	
	public int getCookTime() {
		return this.cookTime;
	}
	
	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public IRecipeType<?> getType() {
		return this.type;
	}
}
