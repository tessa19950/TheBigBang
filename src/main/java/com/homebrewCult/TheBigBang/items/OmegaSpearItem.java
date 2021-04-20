package com.homebrewCult.TheBigBang.items;

import com.google.common.collect.Sets;
import com.homebrewCult.TheBigBang.TheBigBang;

import com.homebrewCult.TheBigBang.init.ModItemTier;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class OmegaSpearItem extends ToolItem implements IBigBangWeapon{

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.HAY_BLOCK, Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.OAK_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.SPRUCE_LEAVES);
	public static final String HYPER_BODY_TIME_KEY = TheBigBang.MODID + "hyper_body_time";
	public int clientHyperBodyTime;

	public OmegaSpearItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
		super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder);
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
		worldIn.playSound(player, player.getPosition(), ModSounds.HYPER_BODY_USE, SoundCategory.PLAYERS, 1, pitch);
		if(!worldIn.isRemote) {
			double range = 8;
			AxisAlignedBB AABB = new AxisAlignedBB(player.getPosition().add(-range, -range, -range), player.getPosition().add(range, range, range));
			List<Entity> targets = new ArrayList<Entity>();
			targets.addAll(worldIn.getEntitiesWithinAABB(PlayerEntity.class, AABB, (entityIn) -> {
				return true;
			}));
			for(Entity t : targets) {
				if(t instanceof PlayerEntity) {
					worldIn.addParticle(ModParticleTypes.HOLY_HEXAGRAM.get(), t.posX, t.posY + 0.01D, t.posZ, 0, 0.5D, 0);
					EffectInstance effect = new EffectInstance(Effects.RESISTANCE, 30 * 20, 1, false, true);
					((PlayerEntity) t).addPotionEffect(effect);
				}
			}
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putInt(HYPER_BODY_TIME_KEY, player.ticksExisted);
			stack.setTag(nbt);
		} else {
			this.clientHyperBodyTime = player.ticksExisted;
		}
	}

	@Override
	public void onSpellCharging(ItemStack weaponStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		int chargeTime = weaponStack.getUseDuration() - timeLeft;
		if(chargeTime == getChargeDuration() && entityLiving instanceof PlayerEntity) {
			float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
			worldIn.playSound((PlayerEntity) entityLiving, entityLiving.getPosition(), SoundEvents.ENTITY_EVOKER_CAST_SPELL, SoundCategory.PLAYERS, 0.5F, pitch);
		}
		if(chargeTime > getChargeDuration()) {
			for(int i = 0; i < 2; ++i) {
				double time = i == 0 ? (timeLeft * 1.5D) : (timeLeft * 1.5D) + 64;
				if (time % 64 > 32) {
					boolean flag = time % 128 > 64;
					double xOffset = flag ? (-time % 32) / 4F : (time % 32) / 4F;
					xOffset += flag ? 4 : -4;
					double zOffset = flag ? 4 : -4;
					worldIn.addParticle(this.getChargedParticle(), entityLiving.posX + xOffset, entityLiving.posY + 1D, entityLiving.posZ + zOffset, 0, 0, 0);
				} else {
					boolean flag = time % 128 > 64;
					double zOffset = flag ? (time % 32) / 4F : (-time % 32) / 4F;
					zOffset += flag ? -4 : 4;
					double xOffset = flag ? 4 : -4;
					worldIn.addParticle(this.getChargedParticle(), entityLiving.posX + xOffset, entityLiving.posY + 1D, entityLiving.posZ + zOffset, 0, 0, 0);
				}
			}
		} else {
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
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(isSelected && stack.hasTag()) {
			CompoundNBT nbt = stack.getTag();
			int spellTime = 0;
			if(nbt.contains(HYPER_BODY_TIME_KEY))
				spellTime = entityIn.ticksExisted - nbt.getInt(HYPER_BODY_TIME_KEY);
			if(spellTime < -10) {
				nbt.remove(HYPER_BODY_TIME_KEY);
				stack.setTag(nbt);
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		CompoundNBT nbt = itemstack.getOrCreateTag();
		int spellTime = 100;
		if(nbt.contains(HYPER_BODY_TIME_KEY)) {
			spellTime = playerIn.ticksExisted - nbt.getInt(HYPER_BODY_TIME_KEY);
		}
		if(spellTime > 30) {
			playerIn.setActiveHand(handIn);
			return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
		}
		return new ActionResult<>(ActionResultType.FAIL, itemstack);
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
	public Predicate<ItemStack> getAmmoPredicate() { return IBigBangWeapon.SUMMONING_ROCKS; }

	@Override
	public int getChargeDuration() { return 40; }

	@Override
	public IParticleData getChargingParticle() { return ModParticleTypes.SYMBOL_GOLD.get(); }

	@Override
	public IParticleData getChargedParticle() { return ModParticleTypes.GLOWLEAF_GOLD.get(); }

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL; }
	
}
