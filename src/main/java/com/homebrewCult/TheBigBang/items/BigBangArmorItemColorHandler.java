package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.items.armor.BigBangArmorItem;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class BigBangArmorItemColorHandler implements IItemColor {

	public static final IItemColor INSTANCE = new BigBangArmorItemColorHandler();

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
}
