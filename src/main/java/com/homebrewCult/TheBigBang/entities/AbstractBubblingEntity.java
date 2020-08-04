package com.homebrewCult.TheBigBang.entities;

import java.util.EnumSet;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class AbstractBubblingEntity extends MonsterEntity implements IQuestEntity {
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;
	private boolean wasOnGround;
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	
	public AbstractBubblingEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	    this.moveController = new AbstractBubblingEntity.MoveHelperController(this);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1D, false));
		this.goalSelector.addGoal(3, new AbstractBubblingEntity.FaceRandomGoal(this));
	    this.goalSelector.addGoal(5, new AbstractBubblingEntity.HopGoal(this));
	    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213811_1_) -> {
	       return Math.abs(p_213811_1_.posY - this.posY) <= 4.0D;
	    }));
		super.registerGoals();
	}
	
	@Override
	protected void registerAttributes() {
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3D);
	      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.3F);
	      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
	}
	
	public void tick() {
		if (!this.world.isRemote && this.world.getDifficulty() == Difficulty.PEACEFUL) {
			this.remove(); //Forge: Kill entity with notification to caps/subclasses.
		}

		this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
		this.prevSquishFactor = this.squishFactor;
		super.tick();
		if (this.onGround && !this.wasOnGround) {
			int i = this.getSlimeSize();

	        for(int j = 0; j < i * 8; ++j) {
	            float f = this.rand.nextFloat() * ((float)Math.PI * 2F);
	            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
	            float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
	            float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
	            World world = this.world;
	            IParticleData iparticledata = this.getSquishParticle();
	            double d0 = this.posX + (double)f2;
	            double d1 = this.posZ + (double)f3;
	            world.addParticle(iparticledata, d0, this.getBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D);
	        }
	        
	        this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
	        this.squishAmount = -0.5F;
		} else if (!this.onGround && this.wasOnGround) {
			this.squishAmount = 1.0F;
		}
		this.wasOnGround = this.onGround;
	}
	
	public int getSlimeSize() {
		return 1;
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_SLIME_HURT_SMALL;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SLIME_DEATH_SMALL;
	}

	protected SoundEvent getSquishSound() {
		return SoundEvents.ENTITY_SLIME_SQUISH_SMALL;
	}
	
	public IParticleData getSquishParticle() {
		return ParticleTypes.ITEM_SLIME;
	}
	
	protected int getJumpDelay() {
		return this.rand.nextInt(10) + 5;
	}
	
	static class HopGoal extends Goal {
		private final AbstractBubblingEntity bubbling;

		public HopGoal(AbstractBubblingEntity bubblingIn) {
			this.bubbling = bubblingIn;
			this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute() {
			return !this.bubbling.isPassenger();
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			((BubblingEntity.MoveHelperController)this.bubbling.getMoveHelper()).setSpeed(1.0D);
		}
	}
	
	static class FaceRandomGoal extends Goal {
	      private final AbstractBubblingEntity bubbling;
	      private float chosenDegrees;
	      private int nextRandomizeTime;

	      public FaceRandomGoal(AbstractBubblingEntity bubblingIn) {
	         this.bubbling = bubblingIn;
	         this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
	      }

	      /**
	       * Returns whether the EntityAIBase should begin execution.
	       */
	      public boolean shouldExecute() {
	         return this.bubbling.getAttackTarget() == null && (this.bubbling.onGround || this.bubbling.isInWater() || this.bubbling.isInLava() || this.bubbling.isPotionActive(Effects.LEVITATION)) && this.bubbling.getMoveHelper() instanceof AbstractBubblingEntity.MoveHelperController;
	      }

	      /**
	       * Keep ticking a continuous task that has already been started
	       */
	      public void tick() {
	         if (--this.nextRandomizeTime <= 0) {
	            this.nextRandomizeTime = 40 + this.bubbling.getRNG().nextInt(60);
	            this.chosenDegrees = (float)this.bubbling.getRNG().nextInt(360);
	         }

	         ((AbstractBubblingEntity.MoveHelperController)this.bubbling.getMoveHelper()).setDirection(this.chosenDegrees, false);
	      }
	   }
	
	static class MoveHelperController extends MovementController {
		private float yRot;
	    private int jumpDelay;
	    private final AbstractBubblingEntity bubbling;
	    private boolean isAggressive;

	    public MoveHelperController(AbstractBubblingEntity bubblingIn) {
	    	super(bubblingIn);
	    	this.bubbling = bubblingIn;
	    	this.yRot = 180.0F * bubblingIn.rotationYaw / (float)Math.PI;
	    }

	    public void setDirection(float yRotIn, boolean aggressive) {
	    	this.yRot = yRotIn;
	    	this.isAggressive = aggressive;
	    }

	    public void setSpeed(double speedIn) {
	    	this.speed = speedIn;
	    	this.action = MovementController.Action.MOVE_TO;
	    }

	    public void tick() {
	    	this.mob.rotationYaw = this.limitAngle(this.mob.rotationYaw, this.yRot, 90.0F);
	    	this.mob.rotationYawHead = this.mob.rotationYaw;
	    	this.mob.renderYawOffset = this.mob.rotationYaw;
	    	if (this.action != MovementController.Action.MOVE_TO) {
	    		this.mob.setMoveForward(0.0F);
	    	} else {
	    		this.action = MovementController.Action.WAIT;
	    		if (this.mob.onGround) {
	    			this.mob.setAIMoveSpeed((float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
	    			if (this.jumpDelay-- <= 0) {
	    				this.jumpDelay = this.bubbling.getJumpDelay();
	    				if (this.isAggressive) {
	    					this.jumpDelay /= 3;
	    				}

	    				this.bubbling.getJumpController().setJumping();
	    				this.bubbling.playSound(SoundEvents.ENTITY_SLIME_JUMP_SMALL, 0.4F, ((this.bubbling.getRNG().nextFloat() - this.bubbling.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
	    			} else {
	    				this.bubbling.moveStrafing = 0.0F;
	    				this.bubbling.moveForward = 0.0F;
	    				this.mob.setAIMoveSpeed(0.0F);
	    			}
	    		} else {
	    			this.mob.setAIMoveSpeed((float)(this.speed * this.mob.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()));
	    		}

	    	}
	    }
	}

	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return this.questEntityHandler;
	}
}
