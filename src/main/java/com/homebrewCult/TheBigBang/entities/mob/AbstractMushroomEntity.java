package com.homebrewCult.TheBigBang.entities.mob;

import javax.annotation.Nullable;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.network.BigBangPacketHandler;
import com.homebrewCult.TheBigBang.network.Packet_SetChildAttacker;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

public class AbstractMushroomEntity extends AnimalEntity implements IQuestEntity {
	
	public boolean isAngry = false;
	public boolean hasChild = false;
	public boolean isChildHurt = false;
	
	public AbstractMushroomEntity mom = null;
	public String mushroomType = null;
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.POISONOUS_POTATO, Items.ROTTEN_FLESH, Items.DEAD_BUSH);

	public AbstractMushroomEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
		this.mushroomType = type.getRegistryName().getPath();
	}
	
	@Override
	protected void registerGoals()
	{
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
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.getTrueSource() instanceof LivingEntity) {
			LivingEntity trueTarget = (LivingEntity)source.getTrueSource();
			setAttackTarget(trueTarget);
        	if(this.isChild() && this.hasMom()) {
        		this.getMom().setChildAttacker(trueTarget);
        		BigBangPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new Packet_SetChildAttacker(this.getMom().getEntityId(), trueTarget.getEntityId()));
        	}      	
        }
		return super.attackEntityFrom(source, amount);
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
	
	public boolean isBreedingItem(ItemStack stack) 
	{
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
	
	//Extra Family Information
	public boolean hasMom() {
		return(this.mom != null);
	}
	
	public AbstractMushroomEntity getMom() {
		return this.mom;
	}
	
	public void setMom (AbstractMushroomEntity newMom) {
		this.mom = newMom; 
	}
	
	public void setHasChild() {
		this.hasChild = true;
	}
	
	public void setChildAttacker(LivingEntity target) {
		this.setAttackTarget(target);
		this.isAngry = true;
		this.isChildHurt = true;
	}
	
	//Mushroom Tempt Goal
	static class AbstractMushroomTemptGoal extends TemptGoal {
		public AbstractMushroomTemptGoal(CreatureEntity creatureIn, double speedIn, boolean p_i47823_4_, Ingredient temptItemsIn) {
			super(creatureIn, speedIn, p_i47823_4_, temptItemsIn);
		}
		
		public boolean shouldExecute( ) {
			AbstractMushroomEntity mushroom = (AbstractMushroomEntity) creature;
			if(mushroom.isAngry) {
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
			if(mushroomAttacker.isAngry && !mushroomAttacker.isChild()) {
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
