package com.homebrewCult.TheBigBang.items;

import java.util.List;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class BigBangDiskItem extends MusicDiscItem {

	private String customDiscTooltip;
	
	public BigBangDiskItem(String tooltip, SoundEvent soundIn, Properties builder) {
		super(0, soundIn, builder);
		customDiscTooltip = tooltip;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent(customDiscTooltip));
		//super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
