package com.homebrewCult.TheBigBang.entities.mob;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import com.homebrewCult.TheBigBang.util.QuestEntityHandler;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Objects;

public class AbstractJrYetiEntity extends TameableEntity implements IQuestEntity {
	
	private QuestEntityHandler questEntityHandler = new QuestEntityHandler();
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.SALMON, Items.TROPICAL_FISH, Items.COD, Items.PUFFERFISH);
	
	public AbstractJrYetiEntity(EntityType<? extends TameableEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		  this.sitGoal = new SitGoal(this);
	      this.goalSelector.addGoal(0, new SwimGoal(this));
		  this.goalSelector.addGoal(1, this.sitGoal);
	      this.goalSelector.addGoal(2, new PanicGoal(this, 0.3D));
		  this.goalSelector.addGoal(3, new TemptGoal(this, 0.3D, false, TEMPTATION_ITEMS));
		  this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 0.3D, 10.0F, 2.0F));
	      this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.3D));
	      this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 0.3F));
	      this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if(reason.equals(SpawnReason.SPAWNER) && world.isRemote)
			spawnPoofParticles(this, world, rand);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		Food food = itemstack.getItem().getFood();
		if (this.getOwner() != null) {
			boolean isFishOrMeat = food != null && (isFish(itemstack) || food.isMeat());
			if (isFishOrMeat && getHealth() < 20.0F) {
				if (!player.abilities.isCreativeMode)
					itemstack.shrink(1);
				this.heal(food.getHealing());
				return true;
			}
			if (this.isOwner(player) && !this.world.isRemote) {
				this.sitGoal.setSitting(!this.isSitting());
				this.navigator.clearPath();
			}
			if (itemstack.getItem().equals(ModItems.BLESSED_SUMMONING_ROCK)) {
				itemstack.shrink(1);
				transformIntoYeti((PlayerEntity) this.getOwner());
			}
		} else if (isFish(itemstack)) {
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

	private void transformIntoYeti(PlayerEntity owner) {
		boolean isDark = this instanceof DarkJrYetiEntity;
		AbstractYetiEntity yeti = isDark ? new DarkYetiEntity(ModEntities.DARK_YETI_ENTITY, world) : new YetiEntity(ModEntities.YETI_ENTITY, world);
		yeti.onInitialSpawn(world, world.getDifficultyForLocation(getPosition()), SpawnReason.CONVERSION, null, getPersistentData());
		yeti.setTamedBy(owner);
		yeti.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
		yeti.setCustomName(getCustomName());
		world.addEntity(yeti);
		remove();
	}

	private boolean isFish(ItemStack stack) {
		if(!stack.isFood())
			return false;
		return TEMPTATION_ITEMS.test(stack);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.JR_YETI_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.JR_YETI_DAMAGE;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.JR_YETI_DIE;
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}

	@Override
	public QuestEntityHandler getQuestEntityHandler() {
		return questEntityHandler;
	}

}
