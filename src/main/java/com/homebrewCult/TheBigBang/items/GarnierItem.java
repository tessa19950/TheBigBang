package com.homebrewCult.TheBigBang.items;

import java.util.function.Predicate;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShootableItem;

public class GarnierItem extends ShootableItem {
	
	public static final Predicate<ItemStack> THROWING_STARS = (itemstack) -> {
		return itemstack.getItem() == ModItems.SUBI || itemstack.getItem() == ModItems.TOBI || itemstack.getItem() == ModItems.STEELY || itemstack.getItem() == ModItems.ILBI;
   	};
	
	public GarnierItem(Properties properties) {
		super(properties);
	}

	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return THROWING_STARS;
	}

}
