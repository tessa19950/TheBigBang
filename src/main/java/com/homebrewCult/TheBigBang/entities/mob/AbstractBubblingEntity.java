package com.homebrewCult.TheBigBang.entities.mob;

import java.util.EnumSet;

import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AbstractBubblingEntity extends TameableEntity implements IQuestEntity {
	public float squishAmount;
	public float squishFactor;
	public float prevSquishFactor;
	private boolean wasOnGround;
	public int timeUntilNextSlimeball = this.rand.nextInt(12000) + 12000;
	private final QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.BONE,
			Items.WOODEN_SWORD, Items.STONE_SWORD, Items.IRON_SWORD, Items.GOLDEN_SWORD, Items.DIAMOND_SWORD);
	
	public AbstractBubblingEntity(EntityType<? extends TameableEntity> type, World worldIn) {
		super(type, worldIn);
	    this.moveController = new MoveHelperController(this);
	}
	
	@Override
	protected void registerGoals() {
		this.sitGoal = new SitGoal(this);
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, this.sitGoal);
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1D, false));
		this.goalSelector.addGoal(3, new BubblingTemptGoal(this, 0.2D, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 0.3D, 4.0F, 2.0F, false));
		this.goalSelector.addGoal(5, new FaceRandomGoal(this));
	    this.goalSelector.addGoal(6, new HopGoal(this));
	    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10,
				true, false, (target) -> Math.abs(target.getPosY() - this.getPosY()) <= 4.0D));
		super.registerGoals();
	}
	
	@Override
	protected void registerAttributes() {
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3D);
	      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3F);
		  this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if(reason.equals(SpawnReason.SPAWNER) && world.isRemote)
			spawnPoofParticles(this, rand);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		if (this.getOwner() != null) {
			if (TEMPTATION_ITEMS.test(itemstack) && getHealth() < 20.0F) {
				if (!player.abilities.isCreativeMode)
					itemstack.shrink(1);
				this.heal(3.0F);
				return true;
			}
			if (this.isOwner(player) && !this.world.isRemote) {
				this.sitGoal.setSitting(!this.isSitting());
				this.navigator.clearPath();
			}
		} else if (TEMPTATION_ITEMS.test(itemstack)) {
			if (!player.abilities.isCreativeMode)
				itemstack.shrink(1);
			if (!this.world.isRemote)
				tryTame(player);
			return true;
		}
		return super.processInteract(player, hand);
	}

	private void tryTame(PlayerEntity player) {
		if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
			this.setTamedBy(player);
			this.navigator.clearPath();
			this.setAttackTarget(null);
			this.sitGoal.setSitting(true);
			this.setHealth(20.0F);
			this.playTameEffect(true);
			this.world.setEntityState(this, (byte) 7);
		} else {
			this.playTameEffect(false);
			this.world.setEntityState(this, (byte) 6);
		}
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (!this.world.isRemote && this.isAlive() && --this.timeUntilNextSlimeball <= 0) {
			this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
			this.entityDropItem(Items.SLIME_BALL);
			this.timeUntilNextSlimeball = this.rand.nextInt(6000) + 6000;
		}
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("slimelaytime"))
			this.timeUntilNextSlimeball = compound.getInt("slimelaytime");
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("slimelaytime", this.timeUntilNextSlimeball);
	}

	public void tick() {
		if (!this.world.isRemote && this.world.getDifficulty() == Difficulty.PEACEFUL)
			this.remove();
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
	            double d0 = this.getPosX() + (double)f2;
	            double d1 = this.getPosZ() + (double)f3;
	            world.addParticle(iparticledata, d0, this.getBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D);
	        }
	        this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
	        this.squishAmount = -0.5F;
		} else if (!this.onGround && this.wasOnGround) {
			this.squishAmount = 1.0F;
		}
		this.wasOnGround = this.onGround;
		this.squishAmount *= 0.6F;
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

	protected boolean canDamagePlayer() {
		return getOwner() != null && this.isServerWorld();
	}

	@Nullable
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}

	static class HopGoal extends Goal {
		private final AbstractBubblingEntity bubbling;

		public HopGoal(AbstractBubblingEntity bubblingIn) {
			this.bubbling = bubblingIn;
			this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}

		public boolean shouldExecute() {
			return !this.bubbling.isPassenger();
		}

		public void tick() {
			((BubblingEntity.MoveHelperController)this.bubbling.getMoveHelper()).setSpeed(1.0D);
		}
	}

	static class BubblingTemptGoal extends TemptGoal {
		private final AbstractBubblingEntity bubbling;

		public BubblingTemptGoal(AbstractBubblingEntity bubblingIn, double speedIn, boolean useLongMemory, Ingredient temptationItems) {
			super(bubblingIn, speedIn, useLongMemory, temptationItems);
			this.bubbling = bubblingIn;
			this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public void tick() {
			bubbling.faceEntity(this.closestPlayer, 10.0F, 10.0F);
			((BubblingEntity.MoveHelperController)bubbling.getMoveHelper()).setDirection(bubbling.rotationYaw, bubbling.canDamagePlayer());
		}
	}

	/*
	static class BubblingFollowOwnerGoal extends FollowOwnerGoal {
		private final AbstractBubblingEntity bubbling;

		public BubblingFollowOwnerGoal(AbstractBubblingEntity bubblingIn, double speedIn, float minDistIn, float maxDistIn) {
			super(bubblingIn, speedIn, minDistIn, maxDistIn, false);
			this.bubbling = bubblingIn;
			this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public void tick() {
			LivingEntity owner = bubbling.getOwner();
			if(owner == null)
				return;
			bubbling.getLookController().setLookPositionWithEntity(owner, 10.0F, (float) bubbling.getVerticalFaceSpeed());
			if (!bubbling.isSitting() && !(bubbling.getDistanceSq(owner) < 144.0D)) {
				int i = MathHelper.floor(owner.getPosX()) - 2;
				int j = MathHelper.floor(owner.getPosZ()) - 2;
				int k = MathHelper.floor(owner.getBoundingBox().minY);
				for (int l = 0; l <= 4; ++l) {
					for (int i1 = 0; i1 <= 4; ++i1) {
						if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.canTeleportToBlock(new BlockPos(i + l, k - 1, j + i1))) {
							bubbling.faceEntity(owner, 10.0F, 10.0F);
							((BubblingEntity.MoveHelperController)bubbling.getMoveHelper()).setDirection(bubbling.rotationYaw, bubbling.canDamagePlayer());
							return;
						}
					}
				}
			}
		}
	}
	 */

	static class FaceRandomGoal extends Goal {
	      private final AbstractBubblingEntity bubbling;
	      private float chosenDegrees;
	      private int nextRandomizeTime;

	      public FaceRandomGoal(AbstractBubblingEntity bubblingIn) {
	         this.bubbling = bubblingIn;
	         this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
	      }

	      public boolean shouldExecute() {
	         return bubbling.getAttackTarget() == null && !bubbling.isSitting() &&
					 (bubbling.onGround || bubbling.isInWater() || bubbling.isInLava() || bubbling.isPotionActive(Effects.LEVITATION))
				 && bubbling.getMoveHelper() instanceof AbstractBubblingEntity.MoveHelperController;
	      }

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
	    				if (this.isAggressive)
	    					this.jumpDelay /= 3;
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
