package com.homebrewCult.TheBigBang.items.armor;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class BigBangArmorItem extends DyeableArmorItem implements IItemColor, IDyeableArmorItem {

	private String name;
	protected int defaultColor;
	
	public BigBangArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, int defaultColorIn, Properties builder) {
		super(materialIn, slot, builder);
		this.name = materialIn.getName();
		this.defaultColor = defaultColorIn;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String layer){
		if(layer == null) {
			return TheBigBang.MODID + ":textures/models/armor/" + name + "_overlay.png";
		} else {
			return TheBigBang.MODID + ":textures/models/armor/" + name + ".png";
		}
	}
	
	public int getDefaultColor() {
		return this.defaultColor;
	}
	
	@Override
	public boolean hasColor(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("display");
		return compoundnbt != null && compoundnbt.contains("color", 99);
   	}
	
	@Override
	public int getColor(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("display");
		return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : defaultColor;
	}
	
	@Override
	public int getColor(ItemStack stack, int index) {
		if(index == 0) {
			return 0xFFFFFF;
		} else {
			BigBangArmorItem item = (BigBangArmorItem) stack.getItem(); 
			if(item != null) {
				CompoundNBT compoundnbt = stack.getChildTag("display");
				return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : item.getDefaultColor();
			} else {
				return 0xFFFFFF;
			}
		}
	}

	@Override
	public void removeColor(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("display");
		if (compoundnbt != null && compoundnbt.contains("color")) {
			compoundnbt.remove("color");
		}
	}
	
	@Override
	public void setColor(ItemStack stack, int color) {
		stack.getOrCreateChildTag("display").putInt("color", color);
	}
}
