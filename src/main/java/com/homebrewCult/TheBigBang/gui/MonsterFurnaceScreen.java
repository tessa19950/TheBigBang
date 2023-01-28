package com.homebrewCult.TheBigBang.gui;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.inventory.DivineAltarContainer;
import net.minecraft.client.gui.recipebook.FurnaceRecipeGui;
import net.minecraft.client.gui.screen.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.Optional;

public class MonsterFurnaceScreen extends AbstractFurnaceScreen<DivineAltarContainer> {

	public static final ResourceLocation MONSTER_FURNACE = new ResourceLocation(TheBigBang.MOD_ID, "textures/gui/monster_furnace_gui.png"); 
	
	public MonsterFurnaceScreen(DivineAltarContainer container, PlayerInventory playerInventory, ITextComponent titleIn) {
		super(container, new FurnaceRecipeGui(), playerInventory, titleIn, MONSTER_FURNACE);
	}

	@Override
	public void init() {
		super.init();
		//Get the ImageButton widget (there should be only 1, the recipe book button) and delete that shit.
		Optional<Widget> optionalWidget = this.buttons.stream().filter(widget -> widget instanceof ImageButton).findFirst();
		optionalWidget.ifPresent(widget -> {
			widget.visible = false;
			widget.active = false;
			buttons.remove(widget);
		});
	}
}
