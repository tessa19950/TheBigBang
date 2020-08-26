package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.crafting.RefiningRecipe;
import com.homebrewCult.TheBigBang.crafting.RefiningRecipeType;
import com.homebrewCult.TheBigBang.crafting.RefiningSerializer;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {
	
	public static final IRecipeType<? extends AbstractCookingRecipe> REFINING_RECIPE_TYPE = new RefiningRecipeType();
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, TheBigBang.MODID);
	public static final RegistryObject<RefiningSerializer<RefiningRecipe>> REFINING = RECIPES.register("refining", () -> new RefiningSerializer<>(RefiningRecipe::new, 1600));
	
}




