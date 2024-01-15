package com.homebrewCult.TheBigBang.items.weapons;

import com.google.common.collect.ImmutableList;
import com.homebrewCult.TheBigBang.TheBigBang;

import com.homebrewCult.TheBigBang.init.ModEffects;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class HeliosItem extends AxeItem implements IBigBangWeapon {

	public static final String THREATEN_TIME_KEY = TheBigBang.getNamespacedKey("threaten_time");
	public int clientThreatenTime;

	public HeliosItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entity, int timeLeft) {
		if(entity instanceof PlayerEntity && !entity.isSneaking())
			onSpellCharging(stack, entity.world, (PlayerEntity) entity, timeLeft);
		super.onUsingTick(stack, entity, timeLeft);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity user, int timeLeft) {
		if(user instanceof PlayerEntity)
			trySpellAttack(stack, worldIn, (PlayerEntity) user, timeLeft);
	}

	@Override
	public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) {
		float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
		worldIn.playSound(player, player.getPosition(), ModSounds.THREATEN_USE, SoundCategory.PLAYERS, 1, pitch);
		List<Entity> targets = getTargetsInCone(stack, worldIn, player, 24, 60, 6);
		for(Entity t : targets) {
			if(t instanceof MobEntity) {
				int amp = getEffectMultiplier(player, ModEffects.WARRIOR_EFFECT.get());
				EffectInstance slowness = new EffectInstance(Effects.SLOWNESS, 200, amp * 3, false, true);
				EffectInstance weakness = new EffectInstance(Effects.WEAKNESS, 200, amp * 1, false, true);
				((MobEntity) t).addPotionEffect(slowness);
				((MobEntity) t).addPotionEffect(weakness);
				if(worldIn.isRemote)
					worldIn.addParticle(ModParticleTypes.HOLY_CIRCLE.get(), t.getPosX(), t.getPosY() + 0.01D, t.getPosZ(), 0, 0.08D, 0);
			}
		}
		if(!worldIn.isRemote) {
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putInt(THREATEN_TIME_KEY, player.ticksExisted);
			stack.setTag(nbt);
		} else {
			this.clientThreatenTime = player.ticksExisted;
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
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public Predicate<ItemStack> getMagicAmmoPredicate() { return IBigBangWeapon.SUMMONING_ROCKS; }

	@Override
	public int getChargeDuration(PlayerEntity player) { return 40 - (10 * getEffectMultiplier(player, ModEffects.WARRIOR_EFFECT.get())); }

	@Override
	public IParticleData getChargingParticle() { return ModParticleTypes.SYMBOL_GOLD.get(); }

	@Override
	public IParticleData getChargedParticle() { return ModParticleTypes.GLOWLEAF_GOLD.get(); }

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_EVOKER_CAST_SPELL; }

	private static final List<Enchantment> VALID_ENCHANTMENTS = ImmutableList.of(
			Enchantments.FIRE_ASPECT, Enchantments.LOOTING, Enchantments.SHARPNESS,
			Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.KNOCKBACK
	);

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(VALID_ENCHANTMENTS.contains(enchantment))
			return true;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
}
