package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.network.Packet_SetIsTempted;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class AbstractGolemEntity extends AnimalEntity implements IQuestEntity {
	private boolean isAngry;
	private boolean isTempted;
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.DANDELION, Items.POPPY, Items.BLUE_ORCHID, Items.ALLIUM, 
	Items.AZURE_BLUET, Items.ORANGE_TULIP, Items.PINK_TULIP, Items.RED_TULIP, Items.WHITE_TULIP, 
	Items.OXEYE_DAISY, Items.CORNFLOWER, Items.LILAC, Items.LILY_OF_THE_VALLEY, Items.PEONY, Items.ROSE_BUSH);
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	
	public AbstractGolemEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.targetSelector.addGoal(1, new GolemTemptGoal(this, 1D, false, TEMPTATION_ITEMS, world));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		  this.goalSelector.addGoal(3, new AbstractGolemAttackGoal(this, 1D, false));
	      this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 1f));
	      this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50D);
	      this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	      this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.1F);
	      this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
	      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
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
	
	//GolemTempt Goal
	static class GolemTemptGoal extends TemptGoal {

		private World world;
		
		public GolemTemptGoal(CreatureEntity creatureIn, double speedIn, boolean scaredByPlayerMovement, Ingredient temptItemsIn, World world) {
			super(creatureIn, speedIn, scaredByPlayerMovement, temptItemsIn);
			this.world = world;
		}
		
		public boolean shouldExecute( ) {
			AbstractGolemEntity golem = (AbstractGolemEntity) world.getEntityByID(creature.getEntityId());
			
			if(golem.isAngry) {
				return false;
			} else {
				boolean shouldExecute = super.shouldExecute();
				if(golem.isTempted != shouldExecute) {
					TheBigBang.print("Setting isTempted to " + shouldExecute);
					golem.setIsTempted(shouldExecute);
					BigBangPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new Packet_SetIsTempted(golem.getEntityId(), shouldExecute));
				}	
				return shouldExecute;
			}
		}
	}
	
	//Golem Attack Goal
	static class AbstractGolemAttackGoal extends MeleeAttackGoal {
	    public AbstractGolemAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
			super(creature, speedIn, useLongMemory);
		}

		public boolean shouldExecute() {
			AbstractGolemEntity golemAttacker = (AbstractGolemEntity)attacker;
			if(golemAttacker.isAngry) {
				return super.shouldExecute();
			} else {
				return false;
			}
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return this.questEntityHandler;
	}
}
