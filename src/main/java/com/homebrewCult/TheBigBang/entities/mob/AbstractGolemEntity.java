package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.SnipingArrowEntity;
import com.homebrewCult.TheBigBang.entities.goals.GolemSmashGoal;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.network.Packet_SetIsTempted;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.*;

public class AbstractGolemEntity extends AnimalEntity implements IQuestEntity {

	private boolean isAngry;
	private boolean isTempted;
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.DANDELION, Items.POPPY, Items.BLUE_ORCHID, Items.ALLIUM, 
	Items.AZURE_BLUET, Items.ORANGE_TULIP, Items.PINK_TULIP, Items.RED_TULIP, Items.WHITE_TULIP, 
	Items.OXEYE_DAISY, Items.CORNFLOWER, Items.LILAC, Items.LILY_OF_THE_VALLEY, Items.PEONY, Items.ROSE_BUSH);
	private final QuestEntityHandler questEntityHandler = new QuestEntityHandler();

	private static final DataParameter<Integer> SHOCKWAVE_START_TICK = EntityDataManager.createKey(AbstractGolemEntity.class, DataSerializers.VARINT);

	public AbstractGolemEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
	      this.targetSelector.addGoal(1, new GolemTemptGoal(this, 1D, false, TEMPTATION_ITEMS, world));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		  this.goalSelector.addGoal(3, new GolemSmashGoal(this));
		  this.goalSelector.addGoal(4, new GolemAttackGoal(this, 1.2D, false));
	      this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 1f));
	      this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50D);
	      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1F);
	      this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(SHOCKWAVE_START_TICK, -1);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.GOLEM_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSounds.GOLEM_DAMAGE;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSounds.GOLEM_DIE;
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
	
	public boolean isBreedingItem(ItemStack stack) {
		if(!this.isAngry) {
			return TEMPTATION_ITEMS.test(stack);
		} else {
			return false;
		}
	}
	
	public Ingredient getTemptationItems() {
		if(!this.isAngry) {
			return TEMPTATION_ITEMS;
		} else {
			return null;
		}
	}

	public boolean isAngry() {
		return isAngry;
	}

	public int getShockwaveTick() {
		return this.dataManager.get(SHOCKWAVE_START_TICK);
	}

	public void setShockwaveTick(int tick) {
		this.dataManager.set(SHOCKWAVE_START_TICK, tick);
	}
	
	//GolemTempt Goal
	static class GolemTemptGoal extends TemptGoal {
		private AbstractGolemEntity golem;
		
		public GolemTemptGoal(AbstractGolemEntity golem, double speedIn, boolean scaredByPlayerMovement, Ingredient temptItemsIn, World world) {
			super(golem, speedIn, scaredByPlayerMovement, temptItemsIn);
			this.golem = golem;
		}
		
		public boolean shouldExecute( ) {
			if(golem.isAngry)
				return false;
			boolean shouldExecute = super.shouldExecute();
			if(golem.isTempted != shouldExecute) {
				golem.setIsTempted(shouldExecute);
				BigBangPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new Packet_SetIsTempted(golem.getEntityId(), shouldExecute));
			}
			return shouldExecute;
		}
	}
	
	//Golem Attack Goal
	static class GolemAttackGoal extends MeleeAttackGoal {
	    public GolemAttackGoal(AbstractGolemEntity golem, double speedIn, boolean useLongMemory) {
			super(golem, speedIn, useLongMemory);
		}

		@Override
		public boolean shouldExecute() {
			return ((AbstractGolemEntity) attacker).isAngry && super.shouldExecute();
		}
	}
	
	public void setIsTempted(boolean InTempted) {
		isTempted = InTempted;
	}
	
	public boolean getIsTempted() {
		return isTempted;
	}
	
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}
	
	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return this.questEntityHandler;
	}
}
