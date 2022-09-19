package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.TheBigBang;

import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
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

	public static final String THREATEN_TIME_KEY = TheBigBang.MODID + "threaten_time";
	public int clientThreatenTime;

	public HeliosItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entity, int timeLeft) {
		if(entity instanceof PlayerEntity)
			onSpellCharging(stack, entity.world, (PlayerEntity) entity, timeLeft);
		super.onUsingTick(stack, entity, timeLeft);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		trySpellAttack(stack, worldIn, entityLiving, timeLeft);
	}

	@Override
	public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) {
		float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
		worldIn.playSound(player, player.getPosition(), ModSounds.THREATEN_USE, SoundCategory.PLAYERS, 1, pitch);
		List<Entity> targets = getTargetsInCone(stack, worldIn, player, 24, 60, 6);
		for(Entity t : targets) {
			if(t instanceof MobEntity) {
				EffectInstance effect = new EffectInstance(Effects.SLOWNESS, 60, 5, false, true);
				((MobEntity) t).addPotionEffect(effect);
				if(worldIn.isRemote)
					worldIn.addParticle(ModParticleTypes.HOLY_CIRCLE.get(), t.posX, t.posY + 0.01D, t.posZ, 0, 0.08D, 0);
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
	public int getChargeDuration() { return 40; }

	@Override
	public IParticleData getChargingParticle() { return ModParticleTypes.SYMBOL_GOLD.get(); }

	@Override
	public IParticleData getChargedParticle() { return ModParticleTypes.GLOWLEAF_GOLD.get(); }

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_EVOKER_CAST_SPELL; }
}
