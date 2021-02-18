package com.homebrewCult.TheBigBang.items;

import java.util.ArrayList;
import java.util.List;

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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LamaStaffItem extends Item {

	public static final String SPELL_TIME_KEY = TheBigBang.MODID + "spell_timer";
	private static final String SPELL_TARGET_ID_KEY = TheBigBang.MODID + "spell_target_id"; 
	private static final int SPELL_RANGE = 24;
	private static final double SPELL_ANGLE_THRESHOLD = 30;
	
	public LamaStaffItem(Properties properties) {
		super(properties);
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
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		TheBigBang.LOGGER.debug("item finish use");
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity user, int timeLeft) {
		//Instantiate a List and populate it with Entities within the AABB
		AxisAlignedBB AABB = new AxisAlignedBB(user.getPosition().add(-SPELL_RANGE, -SPELL_RANGE, -SPELL_RANGE), user.getPosition().add(SPELL_RANGE, SPELL_RANGE, SPELL_RANGE));
		List<Entity> targets = new ArrayList<Entity>();
		targets.addAll(worldIn.getEntitiesWithinAABB(MobEntity.class, AABB, (entityIn) -> {return true;}));
		if(targets.size() > 0) {		
			CompoundNBT nbt = stack.getOrCreateTag();	
			//Find an entity within the aim cone and range
			List<Entity> selectedTargets = new ArrayList<Entity>();
			int selectedCount = 0;
			for(Entity t : targets) {
				++selectedCount;
				if(selectedCount > 20) {
					break;
				}
				double tDis = user.getPositionVec().distanceTo(t.getPositionVec());
				Vec3d tDir = t.getPositionVec().subtract(user.getPositionVec()).normalize();
				double tAngle = Math.acos(user.getLookVec().dotProduct(tDir)) / Math.PI * 180;
				if(tAngle < SPELL_ANGLE_THRESHOLD && tDis < SPELL_RANGE) {
					selectedTargets.add(t);
					worldIn.addParticle(ModParticleTypes.HOLY_HEXAGRAM.get(), t.posX, t.posY + 0.01D, t.posZ, 0, 0, 0);
				}
			}	
			
			//If an entity is found, save it's information for the Spelltick to use
			if(selectedTargets.size() > 0) {
				int[] ids = new int[selectedTargets.size()];
				for(int i = 0; i < selectedTargets.size(); ++i) {
					ids[i] = selectedTargets.get(i).getEntityId();
				}
				if(!worldIn.isRemote) {
					nbt.putIntArray(SPELL_TARGET_ID_KEY, ids);
					nbt.putInt(SPELL_TIME_KEY, user.ticksExisted);
					stack.setTag(nbt);
					stack.attemptDamageItem(1, worldIn.rand, null);
				}
			}
		}
		super.onPlayerStoppedUsing(stack, worldIn, user, timeLeft);
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
}
