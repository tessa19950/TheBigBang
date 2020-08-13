package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class AbstractJrYetiEntity extends AnimalEntity implements IQuestEntity {
	
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	
	public AbstractJrYetiEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.goalSelector.addGoal(1, new PanicGoal(this, 0.3D));
	      this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
	      this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 0.3F));
	      this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.JR_YETI_DAMAGE;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.JR_YETI_DIE;
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}

	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return questEntityHandler;
	}

}
