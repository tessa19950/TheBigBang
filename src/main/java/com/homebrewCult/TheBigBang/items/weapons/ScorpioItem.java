package com.homebrewCult.TheBigBang.items.weapons;

import com.google.common.collect.ImmutableList;
import com.homebrewCult.TheBigBang.TheBigBang;

import com.homebrewCult.TheBigBang.entities.DragonCrusherStabEntity;
import com.homebrewCult.TheBigBang.init.ModEffects;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.entity.player.CriticalHitEvent;

import java.util.List;
import java.util.function.Predicate;

public class ScorpioItem extends AxeItem implements IBigBangWeapon {

	public static final String DRAGON_CRUSHER_TIME_KEY = TheBigBang.getNamespacedKey("dragon_crusher_time");
	public int clientDragonCrusherTime;

	public ScorpioItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
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
		worldIn.playSound(player, player.getPosition(), ModSounds.DRAGON_CRUSHER_USE, SoundCategory.PLAYERS, 1, pitch);
		if(!worldIn.isRemote) {
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putInt(DRAGON_CRUSHER_TIME_KEY, player.ticksExisted);
			stack.setTag(nbt);
		} else {
			this.clientDragonCrusherTime = player.ticksExisted;
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
			if(!(user instanceof PlayerEntity))
				return;
			int delay = 14 - (2 * getEffectMultiplier((PlayerEntity) user, ModEffects.WARRIOR_EFFECT.get()));
			if(timer <= 41 && (timer % delay) == 1)
				startStabAttack(worldIn, (PlayerEntity)user, timer == 41);
		}
	}

	public void startStabAttack(World worldIn, PlayerEntity player, boolean lastStab) {
		player.swingArm(Hand.MAIN_HAND);
		if(!worldIn.isRemote) {
			Vec3d pos = player.getPositionVec();
			LivingEntity target = worldIn.getClosestEntityWithinAABB(LivingEntity.class, EntityPredicate.DEFAULT, player, pos.x, pos.y, pos.z, player.getBoundingBox().grow(4));
			DragonCrusherStabEntity stab = new DragonCrusherStabEntity(worldIn, player);
			Vec3d lookVec3d = player.getLook(1.0F);
			if (target != null) {
				lookVec3d = target.getPositionVec().subtract(player.getPositionVec()).normalize();
				this.stabEntity(player, target);
			} else if (!lastStab) {
				float x = worldIn.rand.nextFloat() - 0.5F;
				float y = worldIn.rand.nextFloat() - 0.5F;
				lookVec3d = lookVec3d.rotatePitch(x * 0.3F).rotateYaw(y * 0.3F);
			}
			Vector3f lookVec3f = new Vector3f(lookVec3d);
			stab.shoot(lookVec3f.getX(), lookVec3f.getY(), lookVec3f.getZ(), 0.1F, 0F);
			worldIn.addEntity(stab);
		}
	}

	public void stabEntity(PlayerEntity player, LivingEntity target) {
		float dmg = (float)player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue() + random.nextInt(3);
		boolean crit = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPassenger();
		CriticalHitEvent hitResult = ForgeHooks.getCriticalHit(player, target, crit, crit ? 1.5F : 1.0F);
		dmg *= hitResult != null ? hitResult.getDamageModifier() : 1;

		boolean success = target.attackEntityFrom(DamageSource.causePlayerDamage(player), dmg);
		if (success) {
			if (crit) {
				player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT,
						player.getSoundCategory(), 1.0F, 1.0F);
				player.onCriticalHit(target);
			}
			player.setLastAttackedEntity(target);
		}
	}

	@Override
	public UseAction getUseAction(ItemStack stack) { return UseAction.SPEAR; }
	
	@Override
	public int getUseDuration(ItemStack stack) { return 72000; }

	@Override
	public Predicate<ItemStack> getMagicAmmoPredicate() { return IBigBangWeapon.SUMMONING_ROCKS; }

	@Override
	public int getChargeDuration(PlayerEntity player) { return 60 - (15 * getEffectMultiplier(player, ModEffects.WARRIOR_EFFECT.get())); }

	@Override
	public IParticleData getChargingParticle() { return ModParticleTypes.SYMBOL_BLUE.get(); }

	@Override
	public IParticleData getChargedParticle() { return ModParticleTypes.GLOWLEAF_BLUE.get(); }

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL; }

	private static final List<Enchantment> VALID_ENCHANTMENTS = ImmutableList.of(
			Enchantments.FIRE_ASPECT, Enchantments.LOOTING, Enchantments.SHARPNESS,
			Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS, Enchantments.KNOCKBACK
	);

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(VALID_ENCHANTMENTS.contains(enchantment))
			return true;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
}
