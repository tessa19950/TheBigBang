package com.homebrewCult.TheBigBang.entities.mob;

import javax.annotation.Nullable;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class AbstractMushroomEntity extends AnimalEntity implements IQuestEntity {

	protected static final DataParameter<Boolean> IS_ANGRY = EntityDataManager.createKey(AbstractMushroomEntity.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> HAS_CHILD = EntityDataManager.createKey(AbstractMushroomEntity.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> IS_CHILD_HURT = EntityDataManager.createKey(AbstractMushroomEntity.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Integer> MOM_ID = EntityDataManager.createKey(AbstractMushroomEntity.class, DataSerializers.VARINT);

	public String mushroomType = null;
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
		this.goalSelector.addGoal(2, new AbstractMushroomTemptGoal(this, 1D, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(3, new FollowParentGoal(this, 1D));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.goalSelector.addGoal(5, new AbstractMushroomAttackGoal(this, 1D, false));
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
		this.dataManager.register(IS_CHILD_HURT, false);
		this.dataManager.register(MOM_ID, -1);
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
		if (source.getTrueSource() instanceof LivingEntity) {
			LivingEntity trueTarget = (LivingEntity)source.getTrueSource();
			setAttackTarget(trueTarget);
			getDataManager().set(IS_ANGRY, true);
        	if(this.isChild() && this.hasMom())
        		this.getMom().setChildAttacker(trueTarget);
        }
		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.MUSHROOM_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSounds.MUSHROOM_DAMAGE;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSounds.MUSHROOM_DIE;
	}
	
	@Nullable
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}
	
	public boolean isBreedingItem(ItemStack stack) {
		if(!getDataManager().get(IS_ANGRY)) {
			return TEMPTATION_ITEMS.test(stack);
		} else {
			return false;
		}
	}
	
	public Ingredient getTemptationItems() {
		if(!getDataManager().get(IS_ANGRY)) {
			return TEMPTATION_ITEMS;
		} else {
			return null;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		if(getDataManager().get(HAS_CHILD) && getDataManager().get(IS_CHILD_HURT))
			return super.getBoundingBox().grow(1);
		return super.getBoundingBox();
	}

	//Extra Family Information
	public boolean hasMom() { return getMom() != null; }

	public boolean hasChild() { return getDataManager().get(HAS_CHILD); }

	public boolean isChildHurt() { return getDataManager().get(IS_CHILD_HURT); }

	public boolean isAngry() { return getDataManager().get(IS_ANGRY); }
	
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
	
	public void setChildAttacker(LivingEntity target) {
		this.setAttackTarget(target);
		getDataManager().set(IS_ANGRY, true);
		getDataManager().set(IS_CHILD_HURT, true);
	}
	
	//Mushroom Tempt Goal
	static class AbstractMushroomTemptGoal extends TemptGoal {
		public AbstractMushroomTemptGoal(CreatureEntity creatureIn, double speedIn, boolean p_i47823_4_, Ingredient temptItemsIn) {
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
	
	//Mushroom Attack Goal
	static class AbstractMushroomAttackGoal extends MeleeAttackGoal {
		
	    public AbstractMushroomAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
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
