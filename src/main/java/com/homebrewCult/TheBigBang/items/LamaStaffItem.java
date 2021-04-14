package com.homebrewCult.TheBigBang.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.GenesisBeamEntity;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.MathUtility;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LamaStaffItem extends TieredItem implements IBigBangWeapon {

	public static final String SPELL_TIME_KEY = TheBigBang.MODID + "spell_timer";
	private static final String SPELL_TARGET_ID_KEY = TheBigBang.MODID + "spell_target_id"; 
	private static final int SPELL_RANGE = 24;
	private static final double SPELL_ANGLE_THRESHOLD = 30;
	
	public LamaStaffItem(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int timeLeft) {
		onSpellCharging(stack, player.world, player, timeLeft);
		super.onUsingTick(stack, player, timeLeft);
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		CompoundNBT nbt = itemstack.getOrCreateTag();
		int spellTime = 100;
		if(nbt.contains(SPELL_TIME_KEY))
			spellTime = playerIn.ticksExisted - nbt.getInt(SPELL_TIME_KEY);
		if(spellTime > 40) {
			playerIn.setActiveHand(handIn);
			return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
		}
		return new ActionResult<>(ActionResultType.FAIL, itemstack);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity user, int timeLeft) {
		trySpellAttack(stack, worldIn, user, timeLeft);
	}

	@Override
	public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) {
		List<Entity> targets = getTargetsInCone(stack, worldIn, player, SPELL_RANGE, SPELL_ANGLE_THRESHOLD, 20);
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
			}
		}
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);	
		//remove the spell time key from nbt data when the item first ticks
		if(entityIn.ticksExisted == 0) {
			CompoundNBT nbt = stack.getOrCreateTag();
			nbt.remove(SPELL_TIME_KEY);
			stack.setTag(nbt);
		}
		spellTick(stack, worldIn, entityIn);
	}
	
	public void spellTick(ItemStack stack, World worldIn, Entity user) {
		CompoundNBT nbt = stack.getOrCreateTag();
		if(nbt.contains(SPELL_TIME_KEY) && nbt.contains(SPELL_TARGET_ID_KEY)) {			
			int timer = user.ticksExisted -	nbt.getInt(SPELL_TIME_KEY) - 40;
			//handle the spell if it's still in effect, 20 ticks delay is to make this sync with the angel which is rendered on the client
			if(timer >= 0 && timer <= 40) {
				ArrayList<Entity> targets = new ArrayList<Entity>();
				for(int id : nbt.getIntArray(SPELL_TARGET_ID_KEY)) {
					if(worldIn.getEntityByID(id) != null)
						targets.add(worldIn.getEntityByID(id));
				}
				if(targets.size() > 0) {
					for(int i = 0; i < targets.size(); ++i) {
						if (timer == i * 3) {
							spawnGenesisBeam(stack, worldIn, targets.get(i).getPositionVec());
							worldIn.playSound(null, targets.get(i).posX, targets.get(i).posY, targets.get(i).posZ, ModSounds.MAGIC_CLAW_USE, SoundCategory.PLAYERS, 1, 1 + (MathUtility.floatInRange(worldIn.rand, -0.2f, 0.2f)));
						} else if (timer == i * 3 + 5) {
							targets.get(i).attackEntityFrom(DamageSource.MAGIC, 100);
						} 
					}
				}
			}
		}
	}
	
	public void spawnGenesisBeam(ItemStack stack, World worldIn, Vec3d pos) {
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
	public Predicate<ItemStack> getAmmoPredicate() {
		return SUMMONING_ROCKS;
	}

	@Override
	public int getChargeDuration() {
		return 60;
	}

	@Override
	public IParticleData getChargingParticle() {
		return ModParticleTypes.SYMBOL_GOLD.get();
	}

	@Override
	public IParticleData getChargedParticle() {
		return ModParticleTypes.GLOWLEAF_GOLD.get();
	}
}
