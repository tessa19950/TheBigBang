package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class HeliosItem extends AxeItem {

	public static final String THREATEN_TIME_KEY = TheBigBang.MODID + "threaten_time";
	
	public HeliosItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity && worldIn.isRemote) {
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putInt(THREATEN_TIME_KEY, entityLiving.ticksExisted);
			stack.setTag(nbt);
		}
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (itemstack.getDamage() >= itemstack.getMaxDamage()) {
			return new ActionResult<>(ActionResultType.FAIL, itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
		}
	}
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

}
