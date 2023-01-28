package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.TheBigBang;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Vaulter2000Item extends BigBangBowItem {

	public Vaulter2000Item(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}

	@Override
	public AbstractArrowEntity customizedArrow(AbstractArrowEntity arrow, ItemStack item) {
		if(arrow.getShooter() instanceof LivingEntity)
			return new HurricaneArrowEntity(arrow.world, (LivingEntity) arrow.getShooter());
		return arrow;
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entityLiving, int count) {
		int useTime = this.getUseDuration(stack) - count;
		int firstUse = 60 - (15 * getEffectMultiplier(entityLiving, ModEffects.BOWMAN_EFFECT.get()));
		boolean flag = useTime >= firstUse && useTime % 5 == 0;
		if (entityLiving instanceof PlayerEntity && flag) {
			PlayerEntity player = (PlayerEntity)entityLiving;
			boolean creative = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = player.findAmmo(stack);
			if(!hasMagicAmmo(player, this) && !creative) {
				if(useTime % 40 == 0)
					playMissingAmmoEffect(player, player.world);
			} else if(!itemstack.isEmpty() || creative) {
				boolean freeArrow = useTime % getConsumptionRate(player) != 0;
				shootArrow(stack, entityLiving.world, entityLiving, 20, freeArrow);
				if(!freeArrow)
					consumeMagicAmmo(player, findMagicAmmo(player, this));
			}
		}
		super.onUsingTick(stack, entityLiving, count);
	}

	// When the usetime of the item reaches this value, another magic ammo is consumed.
	// Without the bowman effect that's once every 20 ticks (30 with Tier I, 40 with Tier II)
	// We shoot an arrow every 5 ticks, so that's 1 magic ammo per 4(No Effect)/6(Tier I)/8(Tier II) arrows fired.
	private int getConsumptionRate(PlayerEntity player) {
		EffectInstance effect = player.getActivePotionEffect(ModEffects.BOWMAN_EFFECT.get());
		if(effect != null)
			return 20 + (10 * (effect.getAmplifier() + 1));
		return 20;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		stack.damageItem(2, entityLiving, (p) -> p.sendBreakAnimation(entityLiving.getActiveHand()));
	}

	@Override
	public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
		return (amount > 1) ? amount : 0;
	}

	@Override
	protected SoundEvent getShootSound() {
		return ModSounds.HURRICANE_USE;
	}

	@Override
	public Predicate<ItemStack> getMagicAmmoPredicate() {
		return SUMMONING_ROCKS;
	}
}
