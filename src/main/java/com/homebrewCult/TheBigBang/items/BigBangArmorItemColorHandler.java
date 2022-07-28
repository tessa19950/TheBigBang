package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.items.armor.BigBangArmorItem;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class BigBangArmorItemColorHandler implements IItemColor {

	public static final IItemColor INSTANCE = new BigBangArmorItemColorHandler();

	@Override
	public int getColor(ItemStack stack, int color) {
		if(stack.getItem() instanceof BigBangArmorItem && color != 0) {
			BigBangArmorItem item = (BigBangArmorItem) stack.getItem();
			CompoundNBT compoundnbt = stack.getChildTag("display");
			return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : item.getDefaultColor();
		} else {
			return 0xFFFFFF;
		}
	}
}
