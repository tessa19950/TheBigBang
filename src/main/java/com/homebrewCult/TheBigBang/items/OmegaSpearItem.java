package com.homebrewCult.TheBigBang.items;

import com.google.common.collect.Sets;
import com.homebrewCult.TheBigBang.TheBigBang;

import com.homebrewCult.TheBigBang.init.ModItemTier;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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
		}
	}

	@Override
	public void onSpellCharging(ItemStack weaponStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		int chargeTime = weaponStack.getUseDuration() - timeLeft;
		if(chargeTime > getChargeDuration()) {
			for(int x = 0; x < 64; ++x) {
				double x1 = x % 32;
				double z1 = x > 32 ? 8 : -8;
				x1 = (x1 / 4F) + entityLiving.posX - 4;
				z1 = (z1 / 4F) + entityLiving.posZ - 4;
				worldIn.addParticle(this.getChargedParticle(), x1, entityLiving.posY, z1, 0, 0, 0);
			}
			for(int z = 0; z < 64; ++z) {
				double z1 = z % 32;
				double x1 = z > 32 ? 8 : -8;
				x1 = (x1 / 4F) + entityLiving.posX - 4;
				z1 = (z1 / 4F) + entityLiving.posZ - 4;
				worldIn.addParticle(this.getChargedParticle(), x1, entityLiving.posY, z1, 0, 0, 0);
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
	
}
