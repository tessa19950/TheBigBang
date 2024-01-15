package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.blocks.DivineAltarBlock;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ManaRockEntity extends CreatureEntity {

    private boolean canInteract;
    public long punchCooldown;
    public static final DataParameter<Byte> STATUS = EntityDataManager.createKey(ManaRockEntity.class, DataSerializers.BYTE);
    private static final Predicate<Entity> IS_RIDEABLE_MINECART = (p_200617_0_) -> {
        return p_200617_0_ instanceof AbstractMinecartEntity && ((AbstractMinecartEntity)p_200617_0_).canBeRidden();
    };
    private static final List<ItemStack> EMPTY_EQUIPMENT = Collections.emptyList();

    public ManaRockEntity(EntityType<?> type, World worldIn) {
        super(ModEntities.MANA_ROCK, worldIn);
    }

    public ManaRockEntity(World worldIn) {
        super(ModEntities.MANA_ROCK, worldIn);
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setNoAI(true);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
    }

    public void recalculateSize() {
        double d0 = this.getPosX();
        double d1 = this.getPosY();
        double d2 = this.getPosZ();
        super.recalculateSize();
        this.setPosition(d0, d1, d2);
    }

    private boolean func_213814_A() {
        return !this.hasMarker() && !this.hasNoGravity();
    }

    /**
     * Returns whether the entity is in a server world
     */
    public boolean isServerWorld() {
        return super.isServerWorld() && this.func_213814_A();
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(STATUS, (byte)0);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putBoolean("Invisible", this.isInvisible());
        compound.putBoolean("Small", this.isSmall());
        if (this.hasMarker()) {
            compound.putBoolean("Marker", this.hasMarker());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setInvisible(compound.getBoolean("Invisible"));
        this.setSmall(compound.getBoolean("Small"));
        this.setMarker(compound.getBoolean("Marker"));
        this.noClip = !this.func_213814_A();
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed() {
        return false;
    }

    protected void collideWithEntity(Entity entityIn) {
    }

    protected void collideWithNearbyEntities() {
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox(), IS_RIDEABLE_MINECART);
        for (Entity entity : list) {
            if (this.getDistanceSq(entity) <= 0.2D) {
                entity.applyEntityCollision(this);
            }
        }
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!this.world.isRemote && !this.removed) {
            if (DamageSource.OUT_OF_WORLD.equals(source)) {
                this.remove();
                return false;
            } else if (!this.isInvulnerableTo(source) && !this.canInteract && !this.hasMarker()) {
                if (source.isExplosion()) {
                    this.func_213816_g(source);
                    this.remove();
                    return false;
                } else if (DamageSource.IN_FIRE.equals(source)) {
                    if (this.isBurning()) {
                        this.func_213817_e(source, 0.15F);
                    } else {
                        this.setFire(5);
                    }

                    return false;
                } else if (DamageSource.ON_FIRE.equals(source) && this.getHealth() > 0.5F) {
                    this.func_213817_e(source, 4.0F);
                    return false;
                } else {
                    boolean isArrow = source.getImmediateSource() instanceof AbstractArrowEntity;
                    boolean nonZeroDmg = isArrow && ((AbstractArrowEntity)source.getImmediateSource()).getDamage() > 0;
                    boolean isPlayer = "player".equals(source.getDamageType());
                    if (!isPlayer && !isArrow) {
                        return false;
                    } else if (source.getTrueSource() instanceof PlayerEntity && !((PlayerEntity)source.getTrueSource()).abilities.allowEdit) {
                        return false;
                    } else if (source.isCreativePlayer()) {
                        this.playBrokenSound();
                        this.playParticles();
                        this.remove();
                        return nonZeroDmg;
                    } else {
                        long i = this.world.getGameTime();
                        if (i - this.punchCooldown > 5L && !isArrow) {
                            this.world.setEntityState(this, (byte)32);
                            this.punchCooldown = i;
                        } else {
                            this.func_213815_f(source);
                            this.playParticles();
                            this.remove();
                        }

                        return true;
                    }
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 32) {
            if (this.world.isRemote) {
                this.world.playSound(this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.BLOCK_STONE_HIT, this.getSoundCategory(), 0.3F, 1.0F, false);
                this.punchCooldown = this.world.getGameTime();
            }
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() { return EMPTY_EQUIPMENT; }

    @Override
    public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn) { return ItemStack.EMPTY; }

    @Override
    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {}

    /**
     * Checks if the entity is in range to render.
     */
    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        double d0 = this.getBoundingBox().getAverageEdgeLength() * 4.0D;
        if (Double.isNaN(d0) || d0 == 0.0D) {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    private void playParticles() {
        if (this.world instanceof ServerWorld) {
            ((ServerWorld)this.world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.STRIPPED_SPRUCE_WOOD.getDefaultState()), this.getPosX(), this.getPosY() + (double)this.getHeight() / 1.5D, this.getPosZ(), 10, (double)(this.getWidth() / 4.0F), (double)(this.getHeight() / 4.0F), (double)(this.getWidth() / 4.0F), 0.05D);
        }

    }

    private void func_213817_e(DamageSource p_213817_1_, float p_213817_2_) {
        float f = this.getHealth();
        f = f - p_213817_2_;
        if (f <= 0.5F) {
            this.func_213816_g(p_213817_1_);
            this.remove();
        } else {
            this.setHealth(f);
        }

    }

    private void func_213815_f(DamageSource p_213815_1_) {
        Block.spawnAsEntity(this.world, new BlockPos(this), new ItemStack(Items.ARMOR_STAND));
        this.func_213816_g(p_213815_1_);
    }

    private void func_213816_g(DamageSource p_213816_1_) {
        this.playBrokenSound();
        this.spawnDrops(p_213816_1_);
    }

    private void playBrokenSound() {
        this.world.playSound((PlayerEntity)null, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.BLOCK_STONE_BREAK, this.getSoundCategory(), 1.0F, 1.0F);
    }

    protected float updateDistance(float p_110146_1_, float p_110146_2_) {
        this.prevRenderYawOffset = this.prevRotationYaw;
        this.renderYawOffset = this.rotationYaw;
        return 0.0F;
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.5F;
    }

    @Override
    public float getEyeHeight(Pose poseIn) {
        return super.getEyeHeight(poseIn);
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset() {
        return this.hasMarker() ? 0.0D : (double)0.1F;
    }

    public void travel(Vec3d pos) {
        if (this.func_213814_A()) {
            super.travel(pos);
        }
    }

    /**
     * Set the render yaw offset
     */
    public void setRenderYawOffset(float offset) {
        this.prevRenderYawOffset = this.prevRotationYaw = offset;
        this.prevRotationYawHead = this.rotationYawHead = offset;
    }

    @Override
    public HandSide getPrimaryHand() {
        return HandSide.RIGHT;
    }

    /**
     * Sets the head's yaw rotation of the entity.
     */
    public void setRotationYawHead(float rotation) {
        this.prevRenderYawOffset = this.prevRotationYaw = rotation;
        this.prevRotationYawHead = this.rotationYawHead = rotation;
    }

    /**
     * Clears potion metadata values if the entity has no potion effects. Otherwise, updates potion effect color,
     * ambience, and invisibility metadata values
     */
    protected void updatePotionMetadata() {
        this.setInvisible(this.canInteract);
    }

    public void setInvisible(boolean invisible) {
        this.canInteract = invisible;
        super.setInvisible(invisible);
    }

    /**
     * If Animal, checks if the age timer is negative
     */
    public boolean isChild() {
        return this.isSmall();
    }

    /**
     * Called by the /kill command.
     */
    public void onKillCommand() {
        this.remove();
    }

    public PushReaction getPushReaction() {
        return this.hasMarker() ? PushReaction.IGNORE : super.getPushReaction();
    }

    private void setSmall(boolean small) {
        this.dataManager.set(STATUS, this.setBit(this.dataManager.get(STATUS), 1, small));
    }

    public boolean isSmall() {
        return (this.dataManager.get(STATUS) & 1) != 0;
    }

    /**
     * Marker defines where if true, the size is 0 and will not be rendered or intractable.
     */
    private void setMarker(boolean marker) {
        this.dataManager.set(STATUS, this.setBit(this.dataManager.get(STATUS), 16, marker));
    }

    /**
     * Gets whether the armor stand has marker enabled. If true, the armor stand's bounding box is set to zero and cannot
     * be interacted with.
     */
    public boolean hasMarker() {
        return (this.dataManager.get(STATUS) & 16) != 0;
    }

    private byte setBit(byte p_184797_1_, int p_184797_2_, boolean p_184797_3_) {
        if (p_184797_3_) {
            p_184797_1_ = (byte)(p_184797_1_ | p_184797_2_);
        } else {
            p_184797_1_ = (byte)(p_184797_1_ & ~p_184797_2_);
        }

        return p_184797_1_;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith() {
        return super.canBeCollidedWith() && !this.hasMarker();
    }

    protected SoundEvent getFallSound(int heightIn) {
        return SoundEvents.BLOCK_STONE_FALL;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.BLOCK_STONE_HIT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_STONE_BREAK;
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    public void onStruckByLightning(LightningBoltEntity lightningBolt) {
    }

    /**
     * Returns false if the entity is an armor stand. Returns true for all other entity living bases.
     */
    public boolean canBeHitWithPotion() {
        return false;
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        if (STATUS.equals(key)) {
            this.recalculateSize();
            this.preventEntitySpawning = !this.hasMarker();
        }
        super.notifyDataManagerChange(key);
    }

    public boolean attackable() {
        return false;
    }

    public EntitySize getSize(Pose poseIn) {
        float f = this.hasMarker() ? 0.0F : (this.isChild() ? 0.5F : 1.0F);
        return this.getType().getSize().scale(f);
    }

}
