package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class RydenItem extends BigBangBowItem {

	private static final String RYDEN_TIMER_KEY = TheBigBang.getNamespacedKey("ryden_timer");
	private static final String RYDEN_VELOCITY_KEY = TheBigBang.getNamespacedKey("ryden_velocity");
	
	public RydenItem(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}

	@Override
	public AbstractArrowEntity customizedArrow(AbstractArrowEntity arrow, ItemStack item) {
		if(item.getItem().equals(ModItems.BLESSED_MAGIC_ROCK))
			arrow.setDamage(arrow.getDamage() * 2);
		return arrow;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack bowStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)entityLiving;
			boolean flag = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bowStack) > 0;
			ItemStack itemstack = player.findAmmo(bowStack);

			int i = this.getUseDuration(bowStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bowStack, worldIn, player, i, !itemstack.isEmpty() || flag);
			if (i < 0) return;
			
			if (!itemstack.isEmpty() || flag) {
				shootArrow(bowStack, worldIn, entityLiving, i, false);
				if(tryConsumeMagicAmmo(player)) {
					CompoundNBT nbt = bowStack.getOrCreateTag();
					nbt.putInt(RYDEN_TIMER_KEY, 10);
					nbt.putInt(RYDEN_VELOCITY_KEY, i);
					bowStack.setTag(nbt);
				}
			}
		}
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		CompoundNBT nbt = stack.getOrCreateTag();
		if(nbt.contains(RYDEN_TIMER_KEY) && nbt.contains(RYDEN_VELOCITY_KEY)) {
			int timer = nbt.getInt(RYDEN_TIMER_KEY);
			if(timer > 0) {
				if(timer == 1)
					shootArrow(stack, worldIn, (PlayerEntity)entityIn, nbt.getInt(RYDEN_VELOCITY_KEY), true);
				nbt.putInt(RYDEN_TIMER_KEY, timer - 1);
				stack.setTag(nbt);
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
}
