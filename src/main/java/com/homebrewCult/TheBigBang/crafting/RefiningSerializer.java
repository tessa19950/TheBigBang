package com.homebrewCult.TheBigBang.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class RefiningSerializer<T extends RefiningRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<RefiningRecipe>{

	private final int cookingTimeFallback;
	private final RefiningSerializer.IFactory<T> factory;
	
	public RefiningSerializer(RefiningSerializer.IFactory<T> factoryIn, int cookingTimeIn) {
		this.factory = factoryIn;
		this.cookingTimeFallback = cookingTimeIn;
	}
	
	@Override
	public RefiningRecipe read(ResourceLocation recipeId, JsonObject json) {
		String group = JSONUtils.getString(json, "group", "");
		JsonElement jsonelement = (JsonElement)(JSONUtils.isJsonArray(json, "ingredient") ? JSONUtils.getJsonArray(json, "ingredient") : JSONUtils.getJsonObject(json, "ingredient"));
		Ingredient ingredient = Ingredient.deserialize(jsonelement);
		
		//Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
		if (!json.has("result")) throw new JsonSyntaxException("Missing result, expected to find a string or object");
		ItemStack itemstack;
		if (json.get("result").isJsonObject()) itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
		else {
			String result = JSONUtils.getString(json, "result");
			ResourceLocation resourcelocation = new ResourceLocation(result);
			itemstack = new ItemStack(Registry.ITEM.getValue(resourcelocation).orElseThrow(() -> {
				return new IllegalStateException("Item: " + result + " does not exist");
			}));
		}
		float exp = JSONUtils.getFloat(json, "experience", 0.0F);
		int cookingTime = JSONUtils.getInt(json, "cookingtime", this.cookingTimeFallback);
		return this.factory.create(recipeId, group, ingredient, itemstack, exp, cookingTime);
	}

	@Override
	public RefiningRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		String name = buffer.readString(32767);
		Ingredient ingredient = Ingredient.read(buffer);
		ItemStack itemstack = buffer.readItemStack();
		float exp = buffer.readFloat();
		int cookingTime = buffer.readVarInt();
		return this.factory.create(recipeId, name, ingredient, itemstack, exp, cookingTime);
	}

	@Override
	public void write(PacketBuffer buffer, RefiningRecipe recipe) {
		buffer.writeString(recipe.getGroup());
		recipe.ingredient.write(buffer);
		buffer.writeItemStack(recipe.result);
		buffer.writeFloat(recipe.experience);
		buffer.writeVarInt(recipe.cookTime);
	}
	
	public interface IFactory<T extends RefiningRecipe> {
		T create(ResourceLocation resourceLocation, String group, Ingredient ingredient, ItemStack itemstack, float exp, int cookingTime);
	}
}
