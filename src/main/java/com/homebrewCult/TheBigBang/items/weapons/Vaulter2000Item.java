package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.entities.HurricaneArrowEntity;
import com.homebrewCult.TheBigBang.init.ModEffects;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class Vaulter2000Item extends BigBangBowItem {

	public Vaulter2000Item(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}

	@Override
	public AbstractArrowEntity customizedArrow(AbstractArrowEntity arrow, ItemStack item) {
		if(arrow.getShooter() instanceof LivingEntity)
			new HurricaneArrowEntity(arrow.world, (LivingEntity) arrow.getShooter());
		return arrow;
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entityLiving, int count) {
		int useTime = this.getUseDuration(stack) - count;
		boolean flag = useTime >= 60 && useTime % 5 == 0;
		if (entityLiving instanceof PlayerEntity && flag) {
			PlayerEntity player = (PlayerEntity)entityLiving;
			boolean creative = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = player.findAmmo(stack);
			if(itemstack.isEmpty() && !creative)
				player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BOAT_PADDLE_WATER, SoundCategory.PLAYERS, 1.0F, 1.0F);
			else if(!hasMagicAmmo(player, this) && !creative) {
				player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS, 1.0F, 0.8F);
			} else {
				shootArrow(stack, entityLiving.world, entityLiving, 20, true);
				if(useTime % getConsumptionRate(player) == 0)
					consumeMagicAmmo(player, findMagicAmmo(player, this));
			}
		}
		super.onUsingTick(stack, entityLiving, count);
	}

	private int getConsumptionRate(PlayerEntity player) {
		EffectInstance effect = player.getActivePotionEffect(ModEffects.BOWMAN_EFFECT.get());
		if(effect != null)
			return 20 * effect.getAmplifier();
		return 20;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {

	}

	@Override
	protected SoundEvent getShootSound() {
		return ModSounds.HURRICANE_USE;
	}
}
