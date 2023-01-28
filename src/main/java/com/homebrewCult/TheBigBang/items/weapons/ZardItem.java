package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.TheBigBang;

import com.homebrewCult.TheBigBang.init.ModEffects;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class ZardItem extends SwordItem implements IBigBangWeapon {

	public static final String MONSTER_MAGNET_TIME_KEY = TheBigBang.getNamespacedKey("monster_magnet_time");
	public int clientMonsterMagnetTime;

	public ZardItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
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
		worldIn.playSound(player, player.getPosition(), ModSounds.MONSTER_MAGNET_USE, SoundCategory.PLAYERS, 1, pitch);
		if(!worldIn.isRemote) {
			Entity target = getBestTargetInCone(stack, worldIn, player, 24, 30);
			if(target instanceof MobEntity) {
				Vec3d dir = target.getPositionVec().subtract(player.getPositionVec()).normalize();
				float strength = target.getDistance(player) * 0.18F;
				((MobEntity)target).knockBack(player, strength, dir.x, dir.z);
			}
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putInt(MONSTER_MAGNET_TIME_KEY, player.ticksExisted);
			stack.setTag(nbt);
		} else {
			this.clientMonsterMagnetTime = player.ticksExisted;
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
	public UseAction getUseAction(ItemStack stack) { return UseAction.SPEAR; }

	@Override
	public int getUseDuration(ItemStack stack) { return 72000; }

	@Override
	public Predicate<ItemStack> getMagicAmmoPredicate() { return IBigBangWeapon.MAGIC_ROCKS; }

	@Override
	public int getChargeDuration(PlayerEntity player) { return 20 - (5 * getEffectMultiplier(player, ModEffects.WARRIOR_EFFECT.get())); }

	@Override
	public IParticleData getChargingParticle() { return ModParticleTypes.SYMBOL_BLUE.get(); }

	@Override
	public IParticleData getChargedParticle() { return ModParticleTypes.GLOWLEAF_BLUE.get(); }

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_EVOKER_CAST_SPELL; }

}
