package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.init.ModItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

public class LiquidFoodItem extends Item {

	public LiquidFoodItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		boolean flag = stack.getItem() == ModItems.PURE_WATER || stack.getItem() == ModItems.CIDER || stack.getItem() == ModItems.ICE_CREAM_POP || stack.getItem() == ModItems.VERY_SPECIAL_SUNDAE;
		if(!worldIn.isRemote && flag) {
			PlayerEntity player = (PlayerEntity) entityLiving;
			player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
		}
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}
