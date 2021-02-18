package com.homebrewCult.TheBigBang.items;

import java.util.ArrayList;
import java.util.List;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.MathUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;

public class MagicodarItem extends Item {
	
	private static final String SPELL_TIMER_KEY = TheBigBang.MODID + "spell_timer";
	private static final String SPELL_TARGET_ID_KEY = TheBigBang.MODID + "spell_target_id"; 
	private static final int SPELL_RANGE = 24;
	private static final double SPELL_ANGLE_THRESHOLD = 30;
	
	public MagicodarItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean onEntitySwing(ItemStack stack, LivingEntity user) {	
		//Grab the timer information from the stack nbt
		int timer = 0;
		CompoundNBT nbt = stack.getOrCreateTag();
		if(nbt.contains(SPELL_TIMER_KEY)) {
			timer = nbt.getInt(SPELL_TIMER_KEY);
		} else {
			nbt.putInt(SPELL_TIMER_KEY, 0);
			stack.setTag(nbt);
		}

		if(timer <= 0) {
			World world = user.world;
			AxisAlignedBB AABB = new AxisAlignedBB(user.getPosition().add(-SPELL_RANGE, -SPELL_RANGE, -SPELL_RANGE), user.getPosition().add(SPELL_RANGE, SPELL_RANGE, SPELL_RANGE));
			
			//Instantiate a List and populate it with Entities within the AABB
			List<Entity> targets = new ArrayList<Entity>();
			targets.addAll(world.getEntitiesWithinAABB(MobEntity.class, AABB, (entityIn) -> {return true;}));
			
			//Find an entity within the aim cone and range
			Entity bestTarget = null;
			double bestDis = SPELL_RANGE;
			if(targets.size() > 0) {
				for(Entity t : targets) {
					double tDis = user.getPositionVec().distanceTo(t.getPositionVec());
					Vec3d tDir = t.getPositionVec().subtract(user.getPositionVec()).normalize();
					double tAngle = Math.acos(user.getLookVec().dotProduct(tDir)) / Math.PI * 180;
					if(tAngle < SPELL_ANGLE_THRESHOLD && tDis < bestDis) {
						bestTarget = t;
						bestDis = tDis;
					}
				}
			}	
			
			//If an entity is found, save it's information for the Spelltick to use
			if(bestTarget != null) {
				nbt.putInt(SPELL_TARGET_ID_KEY, bestTarget.getEntityId());
				nbt.putInt(SPELL_TIMER_KEY, 30);
				stack.setTag(nbt);
				stack.attemptDamageItem(1, world.rand, null);
			}
		}
		return super.onEntitySwing(stack, user);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);	
		spellTick(stack, worldIn);
	}
	
	public void spellTick(ItemStack stack, World worldIn) {
		CompoundNBT nbt = stack.getOrCreateTag();
		if(nbt.contains(SPELL_TIMER_KEY) && nbt.contains(SPELL_TARGET_ID_KEY)) {
			Entity target = worldIn.getEntityByID(nbt.getInt(SPELL_TARGET_ID_KEY));
			int timer = nbt.getInt(SPELL_TIMER_KEY);
			
			//Handle the spell if it's still in effect
			if(timer > 0) {
				if(worldIn.isRemote && target != null) { 
					if(timer == 30) {
						
					} else if(timer == 20) {
						worldIn.addParticle(ModParticleTypes.MAGIC_CLAW_LEFT.get(), target.posX, target.posY + 1, target.posZ, 0, 0, 0);
					} else if (timer == 10) {
						worldIn.addParticle(ModParticleTypes.MAGIC_CLAW_RIGHT.get(), target.posX, target.posY + 1, target.posZ, 0, 0, 0);
					} 
				} else if (target != null) {
					if(timer == 30) {
						worldIn.playSound(null, target.posX, target.posY, target.posZ, ModSounds.MAGIC_CLAW_USE, SoundCategory.PLAYERS, 1, 1 + (MathUtility.floatInRange(worldIn.rand, -0.2f, 0.2f)));
					} else if (timer == 20) {
						target.attackEntityFrom(DamageSource.MAGIC, 2);
					} else if (timer == 10) {
						target.attackEntityFrom(DamageSource.MAGIC, 2);
					} 
				}
				nbt.putInt(SPELL_TIMER_KEY, timer - 1);
				stack.setTag(nbt);
			}
		}
	}
}
