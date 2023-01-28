package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.crafting.BlessingRecipe;
import com.homebrewCult.TheBigBang.crafting.BlessingRecipeType;
import com.homebrewCult.TheBigBang.crafting.BlessingSerializer;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeTypes {
	
	public static final IRecipeType<BlessingRecipe> BLESSING = Registry.register(Registry.RECIPE_TYPE,
			new ResourceLocation("blessing"),
			new BlessingRecipeType()
	);
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, TheBigBang.MOD_ID);
	public static final RegistryObject<BlessingSerializer<BlessingRecipe>> BLESSING_SERIALIZER = RECIPES.register(
			"blessing", () -> new BlessingSerializer<>(BlessingRecipe::new, 200)
	);
}




