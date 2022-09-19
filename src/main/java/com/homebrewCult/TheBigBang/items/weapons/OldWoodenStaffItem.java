package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.entities.BigBangAreaEffectCloudEntity;
import com.homebrewCult.TheBigBang.entities.FireGearEntity;
import com.homebrewCult.TheBigBang.entities.PoisonMistEntity;
import com.homebrewCult.TheBigBang.init.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class OldWoodenStaffItem extends TieredItem implements IBigBangWeapon {

	public OldWoodenStaffItem(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
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
		EffectInstance effect = player.getActivePotionEffect(ModEffects.MAGICIAN_EFFECT.get());
		float radius = 4.0F;
		if(effect != null)
			radius += (1.0 * (effect.getAmplifier() + 1));

		BigBangAreaEffectCloudEntity aoeEntity = new FireGearEntity(worldIn, player.posX, player.posY, player.posZ, radius);
		if(player.getHeldItem(Hand.OFF_HAND).getItem().equals(Items.POISONOUS_POTATO))
			aoeEntity = new PoisonMistEntity(worldIn, player.posX, player.posY, player.posZ, radius);
		aoeEntity.setPosition(player.posX, player.posY, player.posZ);
		aoeEntity.setOwner(player);
		worldIn.addEntity(aoeEntity);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		spellTick(stack, worldIn, entityIn);
	}

	public void spellTick(ItemStack stack, World worldIn, Entity user) {

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
	public int getChargeDuration() {
		return 5;
	}

	@Override
	public IParticleData getChargingParticle() { return null; }

	@Override
	public IParticleData getChargedParticle() { return null; }

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_EVOKER_CAST_SPELL; }
}
