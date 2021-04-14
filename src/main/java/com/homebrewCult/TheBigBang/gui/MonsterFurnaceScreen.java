package com.homebrewCult.TheBigBang.gui;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.inventory.DivineAltarContainer;
import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MonsterFurnaceScreen extends AbstractFurnaceScreen<DivineAltarContainer> {

	public static final ResourceLocation MONSTER_FURNACE = new ResourceLocation(TheBigBang.MODID, "textures/gui/monster_furnace_gui.png"); 
	
	public MonsterFurnaceScreen(DivineAltarContainer container, PlayerInventory playerInventory, ITextComponent titleIn) {
		super(container, new FurnaceRecipeGui(), playerInventory, titleIn, MONSTER_FURNACE);
	}
}
