package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.*;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AbstractMushroomEntity extends AnimalEntity implements IQuestEntity {

	protected static final DataParameter<Boolean> IS_ANGRY = EntityDataManager.createKey(AbstractMushroomEntity.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HAS_CHILD = EntityDataManager.createKey(AbstractMushroomEntity.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> MOM_ID = EntityDataManager.createKey(AbstractMushroomEntity.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean> IS_MUSHMOM = EntityDataManager.createKey(AbstractMushroomEntity.class, DataSerializers.BOOLEAN);

	public String mushroomType;
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.POISONOUS_POTATO, Items.ROTTEN_FLESH, Items.DEAD_BUSH);

	public AbstractMushroomEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.mushroomType = type.getRegistryName().getPath();
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1D));
		this.goalSelector.addGoal(2, new MushroomTemptGoal(this, 1D, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(3, new MushroomFollowParentGoal(this, 1D));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.goalSelector.addGoal(5, new MushroomAttackGoal(this, 1D, false));
		this.goalSelector.addGoal(6, new PanicGoal(this, 1.2D));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1D));
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 1f));
		this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3f);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(IS_ANGRY, false);
		this.dataManager.register(HAS_CHILD, false);
		this.dataManager.register(MOM_ID, -1);
		this.dataManager.register(IS_MUSHMOM, false);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public void onAddedToWorld() {
		if(world.isRemote && this.getTags().contains("spawner"))
			spawnPoofParticles(this, rand);
		super.onAddedToWorld();
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.getTrueSource() instanceof LivingEntity) {
			LivingEntity trueTarget = (LivingEntity)source.getTrueSource();
        	if(this.isChild() && this.hasMom()) {
				this.getMom().onChildAttacked(trueTarget);
			} else {
				setAttackTarget(trueTarget);
				getDataManager().set(IS_ANGRY, true);
			}
        }
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.MUSHROOM_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.MUSHROOM_DAMAGE;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.MUSHROOM_DIE;
	}
	
	@Nullable
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}
	
	public boolean isBreedingItem(ItemStack stack) {
		if(!getDataManager().get(IS_ANGRY))
			return TEMPTATION_ITEMS.test(stack);
		return false;
	}

	public Ingredient getTemptationItems() {
		if(!getDataManager().get(IS_ANGRY))
			return TEMPTATION_ITEMS;
		return null;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		if(isMushmom())
			return new EntitySize(1.8F, 2.4F, false);
		return super.getSize(poseIn);
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return super.getBoundingBox();
	}

	public boolean isAngry() { return getDataManager().get(IS_ANGRY); }

	public boolean isMushmom() { return getDataManager().get(IS_MUSHMOM); }

	public boolean hasMom() { return getMom() != null; }

	public boolean hasChild() { return getDataManager().get(HAS_CHILD); }

	public AbstractMushroomEntity getMom() {
		int id = getDataManager().get(MOM_ID);
		if(id >= 0) {
			Entity mom = world.getEntityByID(id);
			if(mom instanceof AbstractMushroomEntity)
				return (AbstractMushroomEntity) mom;
		}
		return null;
	}

	public void setMom(AbstractMushroomEntity mom) {
		getDataManager().set(MOM_ID, mom.getEntityId());
	}
	
	public void onChildAttacked(LivingEntity attacker) {
		this.setAttackTarget(attacker);
		getDataManager().set(IS_ANGRY, true);
		if(!isMushmom()) {
			getDataManager().set(IS_MUSHMOM, true);
			IAttributeInstance attribute = this.getAttribute(SharedMonsterAttributes.MAX_HEALTH);
			if (attribute.getBaseValue() != 80.0D) {
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
				this.setHealth(80.0F);
			}
			spawnPoofParticles(this, rand);
			jump();
			this.recalculateSize();
		}
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("ismushmom"))
			getDataManager().set(IS_MUSHMOM, true);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		if(isMushmom())
			compound.putBoolean("ismushmom", true);
	}

	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return this.questEntityHandler;
	}

	static class MushroomTemptGoal extends TemptGoal {
		public MushroomTemptGoal(CreatureEntity creatureIn, double speedIn, boolean p_i47823_4_, Ingredient temptItemsIn) {
			super(creatureIn, speedIn, p_i47823_4_, temptItemsIn);
		}
		
		public boolean shouldExecute( ) {
			AbstractMushroomEntity mushroom = (AbstractMushroomEntity) creature;
			if(mushroom.getDataManager().get(IS_ANGRY)) {
				return false;
			} else {
				return super.shouldExecute();
			}
		}
	}

	static class MushroomAttackGoal extends MeleeAttackGoal {
	    public MushroomAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}

		public boolean shouldExecute() {
			AbstractMushroomEntity mushroomAttacker = (AbstractMushroomEntity)attacker;
			if(mushroomAttacker.getDataManager().get(IS_ANGRY) && !mushroomAttacker.isChild()) {
				return super.shouldExecute();
			} else {
				return false;
			}
		}
	}

	static class MushroomFollowParentGoal extends Goal {
		private final AbstractMushroomEntity child;
		private AnimalEntity parent;
		private final double moveSpeed;
		private int delayCounter;

		public MushroomFollowParentGoal(AbstractMushroomEntity animal, double speed) {
			this.child = animal;
			this.moveSpeed = speed;
		}

		public boolean shouldExecute() {
			if (this.child.getGrowingAge() >= 0)
				return false;
			AnimalEntity newParent = null;
			double bestDis = Double.MAX_VALUE;
			for(AnimalEntity a : child.world.getEntitiesWithinAABB(child.getClass(), child.getBoundingBox().grow(8.0D, 4.0D, 8.0D))) {
				if (a.getGrowingAge() < 0)
					continue;
				double dis = this.child.getDistanceSq(a);
				if (dis < bestDis) {
					bestDis = dis;
					newParent = a;
				}
			}
			if(newParent != null && bestDis < 9.0D) {
				this.parent = newParent;
				return true;
			}
			return false;
		}

		public boolean shouldContinueExecuting() {
			if (child.getGrowingAge() >= 0 || !parent.isAlive())
				return false;
			double d0 = child.getDistanceSq(parent);
			return !(d0 < 9.0D) && !(d0 > 256.0D);
		}

		public void startExecuting() { delayCounter = 0; }

		public void resetTask() { this.parent = null; }

		public void tick() {
			if (--this.delayCounter <= 0 && parent != null) {
				delayCounter = 10;
				child.getNavigator().tryMoveToEntityLiving(parent, moveSpeed);
				if(parent instanceof AbstractMushroomEntity)
					child.setMom((AbstractMushroomEntity) parent);
			}
		}
	}
}
