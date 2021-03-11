package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ScorpioItem extends Item {

	public static final String DRAGON_CRUSHER_TIME_KEY = TheBigBang.MODID + "dragon_crusher_time";
	
	public ScorpioItem(Properties properties) {
		super(properties);
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity && worldIn.isRemote) {
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putInt(DRAGON_CRUSHER_TIME_KEY, entityLiving.ticksExisted);
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
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.SPEAR;
	}
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}
	
}
