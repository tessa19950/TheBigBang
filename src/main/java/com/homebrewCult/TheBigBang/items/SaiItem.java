package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class SaiItem extends SwordItem implements IBigBangWeapon {

	public SaiItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int timeLeft) {
		onSpellCharging(stack, player.world, player, timeLeft);
		super.onUsingTick(stack, player, timeLeft);
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
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.SPEAR;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) { return slotChanged; }

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity user, int timeLeft) {
		trySpellAttack(stack, worldIn, user, timeLeft);
		super.onPlayerStoppedUsing(stack, worldIn, user, timeLeft);
	}

	@Override
	public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) {
		Vec3d dir = new Vec3d(player.getLookVec().x, 0.75F, player.getLookVec().getZ());
		player.addVelocity(dir.x, dir.y, dir.z);
		spawnFlashJumpParticle(worldIn, player, ModParticleTypes.FLASH_JUMP_SYMBOLS.get(), dir.mul(0.2D, 0, 0.2D));
		spawnFlashJumpParticle(worldIn, player, ModParticleTypes.FLASH_JUMP_RINGS.get(), dir.mul(0.05D, 0, 0.05D));
		spawnFlashJumpParticle(worldIn, player, ModParticleTypes.FLASH_JUMP_DASHES.get(), dir.mul(-0.05D, 0, -0.05D));
		worldIn.playSound(null, player.getPosition(), ModSounds.FLASH_JUMP_USE, SoundCategory.PLAYERS, 1, 1);
	}

	private void spawnFlashJumpParticle(World worldIn, PlayerEntity player, BasicParticleType particle, Vec3d motion) {
		worldIn.addParticle(particle, player.posX, player.posY + 1D, player.posZ, motion.x, motion.y, motion.z);
	}

	@Override
	public Predicate<ItemStack> getAmmoPredicate() {
		return MAGIC_ROCKS;
	}

	@Override
	public int getChargeDuration() {
		return 5;
	}

	@Override
	public IParticleData getChargingParticle() {
		return null;
	}

	@Override
	public IParticleData getChargedParticle() {
		return null;
	}

	@Override
	public SoundEvent getChargedSound() {
		return ModSounds.SPELL_CHARGED;
	}
}
