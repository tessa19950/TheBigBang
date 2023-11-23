package com.homebrewCult.TheBigBang.entities.mob;

import java.util.EnumSet;

import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AbstractDrakeEntity extends MonsterEntity implements IQuestEntity {
	
	private static final SoundEvent[] HURT_SOUNDS = new SoundEvent[] {ModSounds.DRAKE_DAMAGE, ModSounds.COPPER_DRAKE_DAMAGE, ModSounds.DARK_DRAKE_DAMAGE, ModSounds.RED_DRAKE_DAMAGE, ModSounds.ICE_DRAKE_DAMAGE};
	private static final SoundEvent[] DEATH_SOUNDS = new SoundEvent[] {ModSounds.DRAKE_DIE, ModSounds.COPPER_DRAKE_DIE, ModSounds.DARK_DRAKE_DIE, ModSounds.RED_DRAKE_DIE, ModSounds.ICE_DRAKE_DIE};
	private static final DataParameter<Integer> FIREBALL_START_TICK = EntityDataManager.createKey(AbstractDrakeEntity.class, DataSerializers.VARINT);
	public static final int FIREBALL_DURATION = 3 * 20;
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();

	public AbstractDrakeEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		if(this instanceof DarkDrakeEntity || this instanceof RedDrakeEntity)
			this.goalSelector.addGoal(2, new DrakeFireballGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1D, false));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 1f));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if(reason.equals(SpawnReason.SPAWNER) && world.isRemote)
			spawnPoofParticles(this, rand);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(FIREBALL_START_TICK, -1);
	}

	public int getFireballTick() {
		return this.dataManager.get(FIREBALL_START_TICK);
	}

	public void setFireballTick(int tick) {
		this.dataManager.set(FIREBALL_START_TICK, tick);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.DRAKE_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return HURT_SOUNDS[rand.nextInt(HURT_SOUNDS.length)];
	}

	@Override
	protected SoundEvent getDeathSound() {
		return DEATH_SOUNDS[rand.nextInt(DEATH_SOUNDS.length)];
	}

	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return this.questEntityHandler;
	}

	private static class DrakeFireballGoal extends Goal {

		public static final int FIREBALL_DURATION = 2 * 20;
		public static final int FIREBALL_COOLDOWN = 8 * 20;
		private final AbstractDrakeEntity drake;

		public DrakeFireballGoal(AbstractDrakeEntity drake) {
			super();
			this.drake = drake;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean shouldExecute() {
			if(drake.ticksExisted - drake.getFireballTick() > FIREBALL_COOLDOWN && drake.world.rand.nextDouble() < 0.032)
				return drake.getAttackTarget() != null;
			return false;
		}

		@Override
		public void startExecuting() {
			super.startExecuting();
			if(drake.getAttackTarget() != null)
				drake.setFireballTick(drake.ticksExisted);
		}

		@Override
		public void tick() {
			super.tick();
			LivingEntity target = drake.getAttackTarget();
			if(target == null)
				return;
			drake.lookAt(EntityAnchorArgument.Type.EYES, target.getEyePosition(0));
			if(drake.ticksExisted - drake.getFireballTick() == FIREBALL_DURATION) {
				double dirX = target.getPosX() - drake.getPosX();
				double dirY = target.getBoundingBox().minY + (double)(target.getHeight() / 2.0F) - (drake.getPosY() + (double)(drake.getHeight() / 2.0F));
				double dirZ = target.getPosZ() - drake.getPosZ();
				double x = dirX + drake.getRNG().nextGaussian() * 0.2D;
				double y = dirY + drake.getRNG().nextGaussian() * 0.2D;
				double z = dirZ + drake.getRNG().nextGaussian() * 0.2D;
				SmallFireballEntity fireball = new SmallFireballEntity(drake.world, drake, x, y, z);
				double length = MathHelper.sqrt(dirX * dirX + dirY * dirY + dirZ * dirZ) * 2.0D;
				fireball.setPosition(drake.getPosX() + dirX / length * 2.5D, drake.getPosY() + 0.75D, drake.getPosZ() + dirZ / length * 2.5D);
				drake.world.addEntity(fireball);
			}
		}

		@Override
		public boolean shouldContinueExecuting() {
			return drake.ticksExisted - drake.getFireballTick() <= FIREBALL_DURATION;
		}
	}
}
