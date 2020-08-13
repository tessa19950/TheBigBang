package com.homebrewCult.TheBigBang.entities.mob;

import java.util.Random;

import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.MathUtility;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class AbstractDrakeEntity extends MonsterEntity implements IQuestEntity {
	
	private static final SoundEvent[] HURT_SOUNDS = new SoundEvent[] {ModSounds.DRAKE_DAMAGE, ModSounds.COPPER_DRAKE_DAMAGE, ModSounds.DARK_DRAKE_DAMAGE, ModSounds.RED_DRAKE_DAMAGE, ModSounds.ICE_DRAKE_DAMAGE};
	private static final SoundEvent[] DEATH_SOUNDS = new SoundEvent[] {ModSounds.DRAKE_DIE, ModSounds.COPPER_DRAKE_DIE, ModSounds.DARK_DRAKE_DIE, ModSounds.RED_DRAKE_DIE, ModSounds.ICE_DRAKE_DIE};
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	
	public AbstractDrakeEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1D, false));
	      this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 1f));
	      this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
	      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.3F);
	      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		Random r = new Random();
		return HURT_SOUNDS[MathUtility.intInRange(r, 0, HURT_SOUNDS.length)];
	}

	@Override
	protected SoundEvent getDeathSound() {
		Random r = new Random();
		return DEATH_SOUNDS[MathUtility.intInRange(r, 0, DEATH_SOUNDS.length)];
	}
	
	@Override
	public void onDeath(DamageSource cause) {
		this.questEntityHandler.onQuestEntityDeath(this, cause);
		super.onDeath(cause);
	}

	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return this.questEntityHandler;
	}
}
