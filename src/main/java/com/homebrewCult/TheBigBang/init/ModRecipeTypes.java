package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.crafting.BlessingRecipe;
import com.homebrewCult.TheBigBang.crafting.BlessingRecipeType;
import com.homebrewCult.TheBigBang.crafting.BlessingSerializer;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class ModRecipeTypes {
	
	public static final IRecipeType<BlessingRecipe> BLESSING_RECIPE = IRecipeType.register("blessing_recipe");
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, TheBigBang.MODID);
	public static final RegistryObject<BlessingSerializer<BlessingRecipe>> BLESSING_SERIALIZER = RECIPES.register("blessing", () -> new BlessingSerializer<>(BlessingRecipe::new, 1600));

}




