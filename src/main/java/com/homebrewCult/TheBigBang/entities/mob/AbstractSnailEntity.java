package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
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

public class AbstractSnailEntity extends AnimalEntity implements IQuestEntity {
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.APPLE, Items.BEETROOT, Items.POTATO, Items.CARROT, Items.SWEET_BERRIES, Items.MELON_SLICE,																Items.VINE, Items.KELP);
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	
	protected AbstractSnailEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals()
	{
	      this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.goalSelector.addGoal(1, new PanicGoal(this, 1D));
	      this.goalSelector.addGoal(2, new BreedGoal(this, 1D));
	      this.goalSelector.addGoal(3, new FollowParentGoal(this, 1D));
	      this.goalSelector.addGoal(4, new TemptGoal(this, 1D, false, TEMPTATION_ITEMS));
	      this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1D));
	      this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 1F));
	      this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerAttributes() {
    super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2f);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.SNAIL_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) 
	{
		return ModSounds.SNAIL_DAMAGE;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return ModSounds.SNAIL_DIE;
	}
	
	public boolean isBreedingItem(ItemStack stack) 
	{
		return TEMPTATION_ITEMS.test(stack);
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
