package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AbstractYetiEntity extends AnimalEntity implements IQuestEntity {
	private boolean isAngry;
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.COD, Items.SALMON, Items.PUFFERFISH, Items.TROPICAL_FISH);
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	private static final DataParameter<Boolean> SADDLED;
	
	public AbstractYetiEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.targetSelector.addGoal(1, new TemptGoal(this, 1D, false, TEMPTATION_ITEMS));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 1f));
	      this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30D);
	      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.2F);
	      this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if (worldIn.getRandom().nextInt(100) == 0) {
			setSaddled(true);
			PepeEntity pepe = (PepeEntity) ModEntities.PEPE_ENTITY.create(this.world);
			pepe.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
			pepe.onInitialSpawn(worldIn, difficultyIn, reason, (ILivingEntityData)null, (CompoundNBT)null);
			worldIn.addEntity(pepe);
			pepe.startRiding(this);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public boolean processInteract(PlayerEntity player, Hand hand) {
		if (!super.processInteract(player, hand)) {
			ItemStack itemstack = player.getHeldItem(hand);
			if (itemstack.getItem() == Items.NAME_TAG) {
				itemstack.interactWithEntity(player, this, hand);
				return true;
			} else if (this.getSaddled() && !this.isBeingRidden()) {
				if (!this.world.isRemote) {
					player.startRiding(this);
				}
				return true;
			} else if (itemstack.getItem() == Items.SADDLE) {
				setSaddled(true);
				itemstack.shrink(1);
				//itemstack.interactWithEntity(player, this, hand);
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public double getMountedYOffset() {
		return 2.2D;
	}

	@Override
	public void updatePassenger(Entity passenger) {
		if (this.isPassenger(passenger)) {
			double z = -1.5F;
			double y = this.getMountedYOffset() + passenger.getYOffset();
			Vec3d vec3d = (new Vec3d(z, 0.0D, 0.0D)).rotateYaw(-this.renderYawOffset * 0.017453292F - 1.5707964F);
			passenger.setPosition(this.posX + vec3d.x, this.posY + y, this.posZ + vec3d.z);
		}
	}

	@Nullable
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	}

	public boolean canBeSteered() {
		return this.getControllingPassenger() instanceof LivingEntity;
	}

	public boolean getSaddled() {
		return (Boolean)this.dataManager.get(SADDLED);
	}

	public void setSaddled(boolean saddled) {
		if (saddled) {
			this.dataManager.set(SADDLED, true);
		} else {
			this.dataManager.set(SADDLED, false);
		}

	}

	public void travel(Vec3d direction) {
		if (this.isAlive()) {
			if (this.isBeingRidden() && this.canBeSteered() && this.getSaddled()) {
				LivingEntity livingentity = (LivingEntity)this.getControllingPassenger();
				this.stepHeight = 1.0F;
				this.rotationYaw = livingentity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = livingentity.rotationPitch * 0.5F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.renderYawOffset = this.rotationYaw;
				this.rotationYawHead = this.renderYawOffset;
				float f = livingentity.moveStrafing * 0.5F;
				float f1 = livingentity.moveForward;
				if (f1 <= 0.0F) {
					f1 *= 0.25F;
				}

				if (this.canPassengerSteer()) {
					this.setAIMoveSpeed((float)this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					super.travel(new Vec3d((double)f, direction.y, (double)f1));
				} else if (livingentity instanceof PlayerEntity) {
					this.setMotion(Vec3d.ZERO);
				}

				this.prevLimbSwingAmount = this.limbSwingAmount;
				double d2 = this.posX - this.prevPosX;
				double d3 = this.posZ - this.prevPosZ;
				float f4 = MathHelper.sqrt(d2 * d2 + d3 * d3) * 4.0F;
				if (f4 > 1.0F) {
					f4 = 1.0F;
				}

				this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4F;
				this.limbSwing += this.limbSwingAmount * 0.001f;
			} else {
				super.travel(direction);
			}
		}
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(SADDLED, false);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("Saddle", this.getSaddled());
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setSaddled(compound.getBoolean("Saddle"));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.YETI_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSounds.YETI_DAMAGE;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSounds.YETI_DIE;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.getTrueSource() instanceof LivingEntity) {
			LivingEntity trueTarget = (LivingEntity)source.getTrueSource();
			setAttackTarget(trueTarget);
        	this.isAngry = true;    	
        }
		return super.attackEntityFrom(source, amount);
	}
	
	public boolean isBreedingItem(ItemStack stack) 
	{
		if(!this.isAngry) {
			return TEMPTATION_ITEMS.test(stack);
		} else {
			return false;
		}
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}
	
	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return this.questEntityHandler;
	}

	static {
		SADDLED = EntityDataManager.createKey(AbstractYetiEntity.class, DataSerializers.BOOLEAN);
	}
}
