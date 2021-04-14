package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;

import com.homebrewCult.TheBigBang.entities.DragonCrusherStabEntity;
import com.homebrewCult.TheBigBang.entities.GenesisBeamEntity;
import com.homebrewCult.TheBigBang.entities.SnipingArrowEntity;
import com.homebrewCult.TheBigBang.init.ModItemTier;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.MathUtility;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

public class ScorpioItem extends AxeItem implements IBigBangWeapon {

	public static final String DRAGON_CRUSHER_TIME_KEY = TheBigBang.MODID + "dragon_crusher_time";
	
	public ScorpioItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
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
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putInt(DRAGON_CRUSHER_TIME_KEY, player.ticksExisted);
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
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		//remove the spell time key from nbt data when the item first ticks
		if(entityIn.ticksExisted == 0) {
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.remove(DRAGON_CRUSHER_TIME_KEY);
			stack.setTag(nbt);
		}
		spellTick(stack, worldIn, entityIn);
	}

	public void spellTick(ItemStack stack, World worldIn, Entity user) {
		CompoundNBT nbt = stack.getOrCreateTag();
		if(nbt.contains(DRAGON_CRUSHER_TIME_KEY)) {
			int timer = user.ticksExisted -	nbt.getInt(DRAGON_CRUSHER_TIME_KEY);
			if(timer <= 41 && timer % 10 == 1 && user instanceof PlayerEntity) {
				startStabAttack(worldIn, (PlayerEntity) user, timer >= 41);
			}
		}
	}

	public void startStabAttack(World worldIn, PlayerEntity player, boolean lastStab) {
		player.swingArm(Hand.MAIN_HAND);
		if(!worldIn.isRemote) {
			Vec3d pos = player.getPositionVec();
			LivingEntity target = worldIn.getClosestEntityWithinAABB(LivingEntity.class, EntityPredicate.DEFAULT, player, pos.x, pos.y, pos.z, player.getBoundingBox().grow(4));
			IProjectile stab = new DragonCrusherStabEntity(worldIn, player);
			Vec3d lookVec3d = player.getLook(1.0F);
			if (target != null) {
				lookVec3d = target.getPositionVec().subtract(player.getPositionVec()).normalize();
				player.attackTargetEntityWithCurrentItem(target);
			} else if (!lastStab) {
				float x = worldIn.rand.nextFloat() - 0.5F;
				float y = worldIn.rand.nextFloat() - 0.5F;
				lookVec3d = lookVec3d.rotatePitch(x * 0.3F).rotateYaw(y * 0.3F);
			}
			Vector3f lookVec3f = new Vector3f(lookVec3d);
			stab.shoot((double) lookVec3f.getX(), (double) lookVec3f.getY(), (double) lookVec3f.getZ(), 0.1F, 0F);
			worldIn.addEntity((DragonCrusherStabEntity) stab);
		}
	}

	@Override
	public UseAction getUseAction(ItemStack stack) { return UseAction.SPEAR; }
	
	@Override
	public int getUseDuration(ItemStack stack) { return 72000; }

	@Override
	public Predicate<ItemStack> getAmmoPredicate() { return IBigBangWeapon.SUMMONING_ROCKS; }

	@Override
	public int getChargeDuration() { return 60; }

	@Override
	public IParticleData getChargingParticle() { return ModParticleTypes.SYMBOL_BLUE.get(); }

	@Override
	public IParticleData getChargedParticle() { return ModParticleTypes.GLOWLEAF_BLUE.get(); }
}
