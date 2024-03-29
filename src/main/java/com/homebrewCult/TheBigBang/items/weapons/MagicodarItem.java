package com.homebrewCult.TheBigBang.items.weapons;

import java.util.Random;
import java.util.function.Predicate;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModEffects;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class MagicodarItem extends TieredItem implements IBigBangWeapon {

	private static final String SPELL_TIME_KEY = TheBigBang.getNamespacedKey("spell_timer");

	private int clientTeleportTime = 0;
	private static final Vec3i[] TELEPORT_TEST_OFFSETS = new Vec3i[]{ new Vec3i(0,0,0), new Vec3i(0,1,0), new Vec3i(0,-1,0),
			new Vec3i(1,0,0), new Vec3i(-1,0,0), new Vec3i(0,0,1), new Vec3i(0,0,-1)};

	public MagicodarItem(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}

	public int getTeleportTime() { return clientTeleportTime; }

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity entity, int timeLeft) {
		if(entity instanceof PlayerEntity) {
			onSpellCharging(stack, entity.world, (PlayerEntity) entity, timeLeft);
			super.onUsingTick(stack, entity, timeLeft);
			int chargeTime = stack.getUseDuration() - timeLeft;
			if (chargeTime > getChargeDuration((PlayerEntity) entity) && chargeTime % 3 == 1) {
				BlockPos pos = getAimPosition(entity);
				spawnTeleportParticles(entity, new Vec3d(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D), 1, 0.5);
			}
		}
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
		if(user instanceof PlayerEntity)
			trySpellAttack(stack, worldIn, (PlayerEntity) user, timeLeft);
		super.onPlayerStoppedUsing(stack, worldIn, user, timeLeft);
	}

	@Override
	public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) {
		BlockPos pos = getAimPosition(player);
		for(Vec3i offset : TELEPORT_TEST_OFFSETS) {
			BlockState aimedState = worldIn.getBlockState(pos.add(offset));
			BlockState underState = worldIn.getBlockState(pos.add(offset).down());
			if(!aimedState.isSolid() && underState.getBlockHardness(worldIn, pos) > 0) {
				spawnTeleportParticles(player, player.getPositionVec().add(0,1D,0), 32, 1);
				player.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, player.rotationYaw, player.rotationPitch);
				spawnTeleportParticles(player, player.getPositionVec().add(0,1D,0), 32, 1);
				worldIn.playSound(null, player.getPosition(), ModSounds.TELEPORT_USE, SoundCategory.PLAYERS, 1, 1);
				player.fallDistance = 0;
				break;
			}
		}

		if(!worldIn.isRemote) {
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.putInt(SPELL_TIME_KEY, player.ticksExisted);
			stack.setTag(nbt);
		} else {
			this.clientTeleportTime = player.ticksExisted;
		}
	}

	private void spawnTeleportParticles(LivingEntity player, Vec3d pos, double amount, double radius) {
		for(int i = 0; i < amount; i++) {
			Random rand = player.world.getRandom();
			double y0 = rand.nextDouble() - 0.5D;
			double y1 = pos.getY() + y0 * radius;
			double x = pos.getX() + (rand.nextDouble() - 0.5D) * radius * (1-Math.abs(y0));
			double z = pos.getZ() + (rand.nextDouble() - 0.5D) * radius * (1-Math.abs(y0));
			double s2 = 0.4 * y0;
			player.world.addParticle(ModParticleTypes.TELEPORT_LINE.get(), x, y1, z, 0, s2, 0);
		}
	}

	private BlockPos getAimPosition(LivingEntity player) {
		double distance = 8.0D + (4.0 * getEffectMultiplier(player, ModEffects.MAGICIAN_EFFECT.get()));
		float yaw = player.rotationYaw * ((float)Math.PI / 180F);
		float pitch = player.rotationPitch * ((float)Math.PI / 180F);
		double x = player.posX - Math.sin(yaw) * distance * Math.cos(-pitch);
		double z = player.posZ + Math.cos(yaw) * distance * Math.cos(-pitch);
		double y = player.posY + Math.sin(-pitch) * distance;
		return new BlockPos((int)Math.floor(x),(int)Math.round(y) + 1,(int)Math.floor(z));
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
	public IParticleData getChargingParticle() { return null; }

	@Override
	public IParticleData getChargedParticle() { return null; }

	@Override
	public SoundEvent getChargedSound() { return SoundEvents.ENTITY_EVOKER_CAST_SPELL; }
}
