package com.homebrewCult.TheBigBang.items;

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
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class ZardItem extends SwordItem implements IBigBangWeapon {

	public static final String MONSTER_MAGNET_TIME_KEY = TheBigBang.MODID + "monster_magnet_time";
	public int clientMonsterMagnetTime;

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

	@Override
	public void onSpellCharging(ItemStack weaponStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		int chargeTime = weaponStack.getUseDuration() - timeLeft;
		if(chargeTime == getChargeDuration() && entityLiving instanceof PlayerEntity) {
			float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
			worldIn.playSound((PlayerEntity) entityLiving, entityLiving.getPosition(), SoundEvents.ENTITY_EVOKER_CAST_SPELL, SoundCategory.PLAYERS, 0.5F, pitch);
		}
		double t = chargeTime + entityLiving.ticksExisted;
		double y = Math.sin(t * 0.05D) * 0.5D;
		double y1 = entityLiving.posY + 1.5D + y;
		double x = Math.sin(t * 0.2D) * (1D - Math.abs(y * 0.8F));
		double x1 = entityLiving.posX + x;
		double x2 = (entityLiving.getMotion().x * 2F) + (x * 0.03F);
		double z = Math.cos(t * 0.2D) * (1D - Math.abs(y * 0.8F));
		double z1 = entityLiving.posZ + z;
		double z2 = (entityLiving.getMotion().z * 2F) + (z * 0.03F);
		worldIn.addParticle(this.getChargingParticle(), x1, y1, z1, x2, 0D, z2);
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

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_EVOKER_CAST_SPELL; }

}
