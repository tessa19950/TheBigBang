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

public class BlessingSerializer<T extends BlessingRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<BlessingRecipe>{

	private final int cookTimeFallback;
	private final BlessingSerializer.IFactory<T> factory;
	
	public BlessingSerializer(BlessingSerializer.IFactory<T> factoryIn, int cookTimeFallback) {
		this.factory = factoryIn;
		this.cookTimeFallback = cookTimeFallback;
	}
	
	@Override
	public BlessingRecipe read(ResourceLocation recipeId, JsonObject json) {
		TheBigBang.print("Loading recipe [" + recipeId + "]");
		try {
			String group = JSONUtils.getString(json, "group", "");
			JsonElement jsonelement = JSONUtils.isJsonArray(json, "ingredient") ?
					JSONUtils.getJsonArray(json, "ingredient") :
					JSONUtils.getJsonObject(json, "ingredient");
			Ingredient ingredient = Ingredient.deserialize(jsonelement);

			//Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
			if (!json.has("result"))
				throw new JsonSyntaxException("Missing result, expected to find a string or object");
			ItemStack itemstack;
			if (json.get("result").isJsonObject())
				itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
			else {
				String result = JSONUtils.getString(json, "result");
				ResourceLocation resourcelocation = new ResourceLocation(result);
				itemstack = new ItemStack(Registry.ITEM.getValue(resourcelocation).orElseThrow(()
						-> new IllegalStateException("Item: " + result + " does not exist")));
			}
			float exp = JSONUtils.getFloat(json, "experience", 0.0F);
			int cookingTime = JSONUtils.getInt(json, "cookingtime", this.cookTimeFallback);
			return this.factory.create(recipeId, group, ingredient, itemstack, exp, cookingTime);
		} catch (Exception e) {
			TheBigBang.print("ERROR: Failed to load recipe [" + recipeId + "]!");
			return null;
		}
	}

	@Override
	public BlessingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
		String name = buffer.readString(32767);
		Ingredient ingredient = Ingredient.read(buffer);
		ItemStack itemstack = buffer.readItemStack();
		float exp = buffer.readFloat();
		int cookingTime = buffer.readVarInt();
		return this.factory.create(recipeId, name, ingredient, itemstack, exp, cookingTime);
	}

	@Override
	public void write(PacketBuffer buffer, BlessingRecipe recipe) {
		buffer.writeString(recipe.getGroup());
		recipe.ingredient.write(buffer);
		buffer.writeItemStack(recipe.result);
		buffer.writeFloat(recipe.experience);
		buffer.writeVarInt(recipe.cookTime);
	}
	
	public interface IFactory<T extends BlessingRecipe> {
		T create(ResourceLocation resourceLocation, String group, Ingredient ingredient, ItemStack itemstack, float exp, int cookingTime);
	}
}
