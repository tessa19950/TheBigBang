package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;

import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class ZardItem extends SwordItem implements IBigBangWeapon {

	public static final String MONSTER_MAGNET_TIME_KEY = TheBigBang.MODID + "monster_magnet_time";
	
	public ZardItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int timeLeft) {
		onSpellCharging(stack, player.world, player, timeLeft);
		super.onUsingTick(stack, player, timeLeft);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		trySpellAttack(stack, worldIn, entityLiving, timeLeft);
	}

	@Override
	public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) {
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
	public Predicate<ItemStack> getAmmoPredicate() { return IBigBangWeapon.MAGIC_ROCKS; }

	@Override
	public int getChargeDuration() { return 20; }

	@Override
	public IParticleData getChargingParticle() { return ModParticleTypes.SYMBOL_BLUE.get(); }

	@Override
	public IParticleData getChargedParticle() { return ModParticleTypes.GLOWLEAF_BLUE.get(); }

}
