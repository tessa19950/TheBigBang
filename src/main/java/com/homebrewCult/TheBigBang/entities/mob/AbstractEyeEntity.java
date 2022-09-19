package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AbstractEyeEntity extends MonsterEntity implements IQuestEntity {
	
	public boolean isAngry = false;
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();

	public AbstractEyeEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<LivingEntity>(this, LivingEntity.class, true));
	      this.goalSelector.addGoal(2, new AbstractEyeAttackGoal(this, 1.0D, false));
	      this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 1f));
	      this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20D);
	      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.4F);
	      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if(reason.equals(SpawnReason.SPAWNER) && world.isRemote)
			spawnPoofParticles(this, world, rand);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
        LivingEntity currentTarget = this.getAttackTarget();
        if (currentTarget == null && source.getTrueSource() instanceof LivingEntity) {
           currentTarget = (LivingEntity)source.getTrueSource();
        }
        isAngry = true;
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.EYE_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSounds.EYE_DAMAGE;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSounds.EYE_DIE;
	}
	
	static class AbstractEyeAttackGoal extends MeleeAttackGoal {
		
	    public AbstractEyeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}

		public boolean shouldExecute() {
			AbstractEyeEntity eyeAttacker = (AbstractEyeEntity)attacker;
			LivingEntity eyeTarget = eyeAttacker.getAttackTarget();
			if(eyeTarget != null) {
				if(eyeTarget.getClass() != eyeAttacker.getClass() && eyeAttacker.isAngry) {
					return super.shouldExecute();
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return this.questEntityHandler;
	}
}
