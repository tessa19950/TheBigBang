package com.homebrewCult.TheBigBang.items.weapons;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableList;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.GenesisBeamEntity;
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
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LamaStaffItem extends TieredItem implements IBigBangWeapon {

	public static final String SPELL_TIME_KEY = TheBigBang.getNamespacedKey("spell_timer");
	private static final String SPELL_TARGET_ID_KEY = TheBigBang.getNamespacedKey("spell_target_id");
	private static final int SPELL_RANGE = 24;
	private static final double SPELL_ANGLE_THRESHOLD = 60;
	public int clientSpellTime;
	
	public LamaStaffItem(IItemTier tierIn, Item.Properties builder) {
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
		CompoundNBT nbt = itemstack.getOrCreateTag();
		int spellTime = 100;
		if(nbt.contains(SPELL_TIME_KEY)) {
			spellTime = playerIn.ticksExisted - nbt.getInt(SPELL_TIME_KEY);
			if(spellTime < 0) {
				nbt.remove(SPELL_TIME_KEY);
				itemstack.setTag(nbt);
			}
		}
		if(spellTime > 40) {
			playerIn.setActiveHand(handIn);
			return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
		}
		return new ActionResult<>(ActionResultType.FAIL, itemstack);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity user, int timeLeft) {
		if(user instanceof PlayerEntity)
			trySpellAttack(stack, worldIn, (PlayerEntity) user, timeLeft);
		super.onPlayerStoppedUsing(stack, worldIn, user, timeLeft);
	}

	@Override
	public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) {
		int maxTargets = 10 + (5 * getEffectMultiplier(player, ModEffects.MAGICIAN_EFFECT.get()));
		List<Entity> targets = getTargetsInCone(stack, worldIn, player, SPELL_RANGE, SPELL_ANGLE_THRESHOLD, maxTargets);
		//If entities are selected, save their ID's in NBT for the Spelltick to use
		if(targets.size() > 0) {
			int[] ids = new int[targets.size()];
			for(int i = 0; i < targets.size(); ++i) {
				worldIn.addParticle(ModParticleTypes.HOLY_HEXAGRAM.get(), targets.get(i).posX, targets.get(i).posY + 0.01D, targets.get(i).posZ, 0, 0, 0);
				ids[i] = targets.get(i).getEntityId();
			}
			if(!worldIn.isRemote) {
				CompoundNBT nbt = stack.getOrCreateTag();
				nbt.putIntArray(SPELL_TARGET_ID_KEY, ids);
				nbt.putInt(SPELL_TIME_KEY, player.ticksExisted);
				stack.setTag(nbt);
				stack.attemptDamageItem(1, worldIn.rand, null);
			} else {
				this.clientSpellTime = player.ticksExisted;
			}
			float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
			worldIn.playSound(player, player.getPosition(), ModSounds.GENESIS_USE, SoundCategory.PLAYERS, 1, pitch);
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		spellTick(stack, worldIn, entityIn);
	}
	
	public void spellTick(ItemStack stack, World worldIn, Entity user) {
		CompoundNBT nbt = stack.getOrCreateTag();
		if(!(user instanceof PlayerEntity))
			return;
		PlayerEntity player = (PlayerEntity) user;
		if(nbt.contains(SPELL_TIME_KEY) && nbt.contains(SPELL_TARGET_ID_KEY)) {
			int timer = player.ticksExisted -	nbt.getInt(SPELL_TIME_KEY) - 40;
			if(timer <= 60) {
				int[] idArray = nbt.getIntArray(SPELL_TARGET_ID_KEY);
				for(int i = 0; i < idArray.length; ++i) {
					ItemStack magicAmmo = findMagicAmmo(player, this);
					Entity entity = worldIn.getEntityByID(idArray[i]);
					if(!(entity instanceof LivingEntity))
						return;
					LivingEntity target = (LivingEntity) entity;
					if(magicAmmo != null && !magicAmmo.isEmpty()) {
						if (timer == i * 3) {
							consumeMagicAmmo(player, magicAmmo);
							spawnGenesisBeam(worldIn, target.getPositionVec());
							break;
						} else if (timer == i * 3 + 5) {
							int amount = 60 + random.nextInt(11);
							magicAttackEntityAsMob(player, target, amount);
							break;
						}
					}
				}
			}
		}
	}
	
	public void spawnGenesisBeam(World worldIn, Vec3d pos) {
		GenesisBeamEntity beamEntity = new GenesisBeamEntity(worldIn); 
		beamEntity.setPosition(pos.x, pos.y, pos.z);
		worldIn.addEntity(beamEntity);
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
		return SUMMONING_ROCKS;
	}

	@Override
	public int getChargeDuration(PlayerEntity player) { return 60 - (15 * getEffectMultiplier(player, ModEffects.MAGICIAN_EFFECT.get())); }

	@Override
	public IParticleData getChargingParticle() {
		return ModParticleTypes.SYMBOL_GOLD.get();
	}

	@Override
	public IParticleData getChargedParticle() {
		return ModParticleTypes.GLOWLEAF_GOLD.get();
	}

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL; }

	private static final List<Enchantment> VALID_ENCHANTMENTS = ImmutableList.of(
			Enchantments.FIRE_ASPECT, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS
	);

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(VALID_ENCHANTMENTS.contains(enchantment))
			return true;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
}
