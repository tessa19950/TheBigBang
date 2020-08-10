package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class RydenItem extends BowItem {

	private static final String RYDEN_TIMER_KEY = TheBigBang.MODID + "ryden_timer";
	private static final String RYDEN_VELOCITY_KEY = TheBigBang.MODID + "ryden_velocity";
	
	public RydenItem(Properties builder) {
		super(builder);
	}
	
	/**
    * Called when the player stops using an Item (stops holding the right mouse button).
    */
	@Override
	public void onPlayerStoppedUsing(ItemStack bowStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bowStack) > 0;
			ItemStack itemstack = playerentity.findAmmo(bowStack);

			int i = this.getUseDuration(bowStack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bowStack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
			if (i < 0) return;
			
			if (!itemstack.isEmpty() || flag) {
				shootArrow(bowStack, worldIn, entityLiving, i, true);
				CompoundNBT nbt = bowStack.getOrCreateTag();
				nbt.putInt(RYDEN_TIMER_KEY, 10);
				nbt.putInt(RYDEN_VELOCITY_KEY, i);
				bowStack.setTag(nbt);
			}
		}
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged;
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		CompoundNBT nbt = stack.getOrCreateTag();
		if(nbt.contains(RYDEN_TIMER_KEY) && nbt.contains(RYDEN_VELOCITY_KEY)) {
			int timer = nbt.getInt(RYDEN_TIMER_KEY);
			if(timer > 0) {
				if(timer == 1) {
					shootArrow(stack, worldIn, (LivingEntity)entityIn, nbt.getInt(RYDEN_VELOCITY_KEY), false);
				}
				nbt.putInt(RYDEN_TIMER_KEY, timer - 1);
				stack.setTag(nbt);
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	public void shootArrow(ItemStack bowStack, World worldIn, LivingEntity entityLiving, int velocity, boolean consumeArrow) {
		PlayerEntity playerentity = (PlayerEntity)entityLiving;
		ItemStack itemstack = playerentity.findAmmo(bowStack);
		if (itemstack.isEmpty()) {
			itemstack = new ItemStack(Items.ARROW);
		}

		float f = getArrowVelocity(velocity);
		if (!((double)f < 0.1D)) {
			boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, bowStack, playerentity) || !consumeArrow);
			if (!worldIn.isRemote) {
				ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
				AbstractArrowEntity arrow1 = arrowitem.createArrow(worldIn, itemstack, playerentity);
				arrow1 = customeArrow(arrow1);
				arrow1.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
				if (f == 1.0F) {
					arrow1.setIsCritical(true);
				}

				int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bowStack);
				if (j > 0) {
					arrow1.setDamage(arrow1.getDamage() + (double)j * 0.5D + 0.5D);
				}
				
				int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bowStack);
				if (k > 0) {
					arrow1.setKnockbackStrength(k);
				}

				if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack) > 0) {
					arrow1.setFire(100);
				}
				
				bowStack.damageItem(1, playerentity, (p_220009_1_) -> {
					p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
				});
				if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
					arrow1.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
				}
				
				worldIn.addEntity(arrow1);
			}

			worldIn.playSound((PlayerEntity)null, playerentity.posX, playerentity.posY, playerentity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			if (!flag1 && !playerentity.abilities.isCreativeMode && consumeArrow) {
				itemstack.shrink(1);
				if (itemstack.isEmpty()) {
					playerentity.inventory.deleteStack(itemstack);
				}
			}

			playerentity.addStat(Stats.ITEM_USED.get(this));
		}
	}
}
