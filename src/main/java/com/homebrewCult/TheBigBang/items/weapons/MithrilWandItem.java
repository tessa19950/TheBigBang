package com.homebrewCult.TheBigBang.items.weapons;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableList;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModEffects;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.MathUtility;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class MithrilWandItem extends TieredItem implements IBigBangWeapon {

	private static final String SPELL_TIME_KEY = TheBigBang.getNamespacedKey("spell_timer");
	private static final String SPELL_TARGET_ID_KEY = TheBigBang.getNamespacedKey("spell_target_id");
	private static final int SPELL_RANGE = 24;
	private static final double SPELL_ANGLE_THRESHOLD = 30;
	
	public MithrilWandItem(IItemTier tierIn, Item.Properties builder) { super(tierIn, builder); }

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entity, int timeLeft) {
		if(entity instanceof PlayerEntity)
			onSpellCharging(stack, entity.world, (PlayerEntity) entity, timeLeft);
		super.onUsingTick(stack, entity, timeLeft);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity user, int timeLeft) {
		if(user instanceof PlayerEntity)
			trySpellAttack(stack, worldIn, (PlayerEntity) user, timeLeft);
		super.onPlayerStoppedUsing(stack, worldIn, user, timeLeft);
	}

	@Override
	public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) {
		Entity bestTarget = getBestTargetInCone(stack, worldIn, player, SPELL_RANGE, SPELL_ANGLE_THRESHOLD);
		if(bestTarget != null && !worldIn.isRemote) {
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putInt(SPELL_TARGET_ID_KEY, bestTarget.getEntityId());
			nbt.putInt(SPELL_TIME_KEY, player.ticksExisted);
			stack.setTag(nbt);
		}
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		CompoundNBT nbt = itemstack.getOrCreateTag();
		int spellTime = 100;
		if(nbt.contains(SPELL_TIME_KEY)) {
			spellTime = playerIn.ticksExisted - nbt.getInt(SPELL_TIME_KEY);
			if(spellTime < 0) {
				nbt.remove(SPELL_TIME_KEY);
				itemstack.setTag(nbt);
			}
		}
		if(spellTime > 20) {
			playerIn.setActiveHand(handIn);
			return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
		}
		return new ActionResult<>(ActionResultType.FAIL, itemstack);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);	
		spellTick(stack, worldIn, entityIn);
	}
	
	public void spellTick(ItemStack stack, World worldIn, Entity userEntity) {
		if(!(userEntity instanceof LivingEntity))
			return;
		LivingEntity user = (LivingEntity) userEntity;
		CompoundNBT nbt = stack.getOrCreateTag();
		if(nbt.contains(SPELL_TIME_KEY) && nbt.contains(SPELL_TARGET_ID_KEY)) {
			Entity targetEntity = worldIn.getEntityByID(nbt.getInt(SPELL_TARGET_ID_KEY));
			if(!(targetEntity instanceof LivingEntity))
				return;
			LivingEntity target = (LivingEntity) targetEntity;
			int timer = user.ticksExisted - nbt.getInt(SPELL_TIME_KEY);
			//Handle the spell if it's still in effect
			if(timer > 0) {
				if(worldIn.isRemote) {
					if(timer == 10) {
						worldIn.addParticle(ModParticleTypes.MAGIC_CLAW_LEFT.get(), target.posX, target.posY + 1, target.posZ, 0, 0, 0);
					} else if (timer == 20) {
						worldIn.addParticle(ModParticleTypes.MAGIC_CLAW_RIGHT.get(), target.posX, target.posY + 1, target.posZ, 0, 0, 0);
					} 
				} else {
					if(timer == 1) {
						worldIn.playSound(null, target.posX, target.posY, target.posZ, ModSounds.MAGIC_CLAW_USE, SoundCategory.PLAYERS, 1, 1 + (MathUtility.floatInRange(worldIn.rand, -0.2f, 0.2f)));
					} else if (timer == 10) {
						int damage = 4 + random.nextInt(4);
						magicAttackEntityAsMob(user, target, damage);
					} else if (timer == 20) {
						int damage = 4 + random.nextInt(4);
						magicAttackEntityAsMob(user, target, damage);
					} 
				}
			}
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
	public Predicate<ItemStack> getMagicAmmoPredicate() {
		return MAGIC_ROCKS;
	}

	@Override
	public int getChargeDuration(PlayerEntity player) { return 8 - (2 * getEffectMultiplier(player, ModEffects.MAGICIAN_EFFECT.get())); }

	@Override
	public IParticleData getChargingParticle() {
		return ModParticleTypes.SYMBOL_BLUE.get();
	}

	@Override
	public IParticleData getChargedParticle() {
		return ModParticleTypes.GLOWLEAF_BLUE.get();
	}

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_EVOKER_CAST_SPELL; }

	private static final List<Enchantment> VALID_ENCHANTMENTS = ImmutableList.of(
			Enchantments.FIRE_ASPECT, Enchantments.SHARPNESS,
			Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS
	);

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(VALID_ENCHANTMENTS.contains(enchantment))
			return true;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
}
