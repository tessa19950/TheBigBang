package com.homebrewCult.TheBigBang.items.weapons;

import java.util.function.Predicate;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.StealEntity;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class GephardItem extends SwordItem implements IBigBangWeapon {

	public static final String SPELL_TIME_KEY = TheBigBang.MODID + "spell_timer";
	private static final String SPELL_TARGET_ID_KEY = TheBigBang.MODID + "spell_target_id";
	private static final int SPELL_RANGE = 16;
	private static final double SPELL_ANGLE_THRESHOLD = 60;
	public int clientSpellTime;

	public GephardItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(tier, attackDamageIn, attackSpeedIn, builder);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entity, int timeLeft) {
		if(entity instanceof PlayerEntity)
			onSpellCharging(stack, entity.world, (PlayerEntity) entity, timeLeft);
		super.onUsingTick(stack, entity, timeLeft);
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
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity user, int timeLeft) {
		trySpellAttack(stack, worldIn, user, timeLeft);
		super.onPlayerStoppedUsing(stack, worldIn, user, timeLeft);
	}

	@Override
	public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) {
		Entity target = getBestTargetInCone(stack, worldIn, player, SPELL_RANGE, SPELL_ANGLE_THRESHOLD);
		if(target != null) {
			int id = target.getEntityId();
			if(!worldIn.isRemote) {
				StealEntity stealEntity = new StealEntity(worldIn, player, target);
				stealEntity.setPosition(player.posX, player.posY, player.posZ);
				worldIn.addEntity(stealEntity);

				CompoundNBT nbt = stack.getOrCreateTag();
				nbt.putInt(SPELL_TARGET_ID_KEY, id);
				nbt.putInt(SPELL_TIME_KEY, player.ticksExisted);
				stack.setTag(nbt);
				stack.attemptDamageItem(1, worldIn.rand, null);
			} else {
				this.clientSpellTime = player.ticksExisted;
			}
			float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
			worldIn.playSound(player, player.getPosition(), ModSounds.STEAL_USE, SoundCategory.PLAYERS, 1, pitch);
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
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged;
	}

	@Override
	public Predicate<ItemStack> getMagicAmmoPredicate() {
		return SUMMONING_ROCKS;
	}

	@Override
	public int getChargeDuration() {
		return 20;
	}

	@Override
	public IParticleData getChargingParticle() {
		return ModParticleTypes.SYMBOL_BLUE.get();
	}

	@Override
	public IParticleData getChargedParticle() { return null; }

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL; }
}
