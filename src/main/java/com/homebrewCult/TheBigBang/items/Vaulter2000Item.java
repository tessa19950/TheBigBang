package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.HurricaneArrowEntity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class Vaulter2000Item extends BowItem {

	private final IItemTier tier;
	public Vaulter2000Item(IItemTier tierIn, Item.Properties builder) {
		super(builder);
		this.tier = tierIn;
	}
	
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged;
	}
	
	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entityLiving, int count) {
		int useTime = this.getUseDuration(stack) - count;
		boolean flag = useTime > 60 && useTime % 5 == 1;
		if (entityLiving instanceof PlayerEntity && flag) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			boolean creative = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = playerentity.findAmmo(stack);
			
			if (!itemstack.isEmpty() || creative) {
				shootArrow(stack, entityLiving.world, entityLiving, 20, true);
			}
		}
		super.onUsingTick(stack, entityLiving, count);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		;
	}
	
	public void shootArrow(ItemStack bowStack, World worldIn, LivingEntity entityLiving, int velocity, boolean consumeArrow) {
		PlayerEntity playerentity = (PlayerEntity)entityLiving;
		ItemStack itemstack = playerentity.findAmmo(bowStack);
		if (itemstack.isEmpty()) {
			itemstack = new ItemStack(Items.ARROW);
		}

		float f = getArrowVelocity(velocity);
		if (f > 0.1F) {
			boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, bowStack, playerentity) || !consumeArrow);
			if (!worldIn.isRemote) {
				HurricaneArrowEntity arrow1 = new HurricaneArrowEntity(worldIn, playerentity);
				arrow1 = (HurricaneArrowEntity) customeArrow(arrow1);
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

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return this.tier.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
	}

	@Override
	public int getItemEnchantability() {
		return this.tier.getEnchantability();
	}
}
