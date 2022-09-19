package com.homebrewCult.TheBigBang.entities;

import com.google.common.collect.Maps;
import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class BigBangAreaEffectCloudEntity extends AreaEffectCloudEntity {

    private final Map<Entity, Integer> reapplicationDelayMap = Maps.newHashMap();
    protected int duration = 400;
    protected int waitTime = 10;
    protected int reapplicationDelay = 20;
    protected float radiusPerTick;
    protected LivingEntity owner;
    protected UUID ownerUniqueId;

    public BigBangAreaEffectCloudEntity(EntityType<? extends AreaEffectCloudEntity> entityType, World worldIn, float radius) {
        super(entityType, worldIn);
        setRadius(radius);
        radiusPerTick = -(getRadius() / duration);
    }

    @Override
    public void tick() {
        float f = this.getRadius();
        if (this.world.isRemote) {
            spawnAreaParticles(f - 0.5F);
        } else {
            if (this.ticksExisted >= this.waitTime + this.duration) {
                this.remove();
                return;
            }

            if (this.radiusPerTick != 0.0F) {
                f += this.radiusPerTick;
                if (f < 0.5F) {
                    this.remove();
                    return;
                }
                this.setRadius(f);
            }

            if (this.ticksExisted % 5 == 0) {
                Iterator<Map.Entry<Entity, Integer>> iterator = this.reapplicationDelayMap.entrySet().iterator();
                while(iterator.hasNext()) {
                    Map.Entry<Entity, Integer> entry = iterator.next();
                    if (this.ticksExisted >= entry.getValue()) {
                        iterator.remove();
                    }
                }

                List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox());
                if (!list.isEmpty()) {
                    for(LivingEntity e : list) {
                        boolean isAffected = true;
                        if(e instanceof TameableEntity) {
                            isAffected = ((TameableEntity)e).getOwnerId() != ownerUniqueId;
                        } else if (e instanceof PlayerEntity) {
                            isAffected = e.getUniqueID() != ownerUniqueId;
                        }

                        if (!this.reapplicationDelayMap.containsKey(e) && e.canBeHitWithPotion() && isAffected) {
                            double d0 = e.posX - this.posX;
                            double d1 = e.posZ - this.posZ;
                            double d2 = d0 * d0 + d1 * d1;
                            if (d2 <= (double)(f * f)) {
                                this.reapplicationDelayMap.put(e, this.ticksExisted + this.reapplicationDelay);
                                applyEffectToEntity(e);
                            }
                        }
                    }
                }
            }
        }
    }

    abstract void applyEffectToEntity(LivingEntity entity);

    abstract void spawnAreaParticles(float radius);

    protected void readAdditional(CompoundNBT compound) {
        this.ticksExisted = compound.getInt("Age");
        this.duration = compound.getInt("Duration");
        this.waitTime = compound.getInt("WaitTime");
        this.reapplicationDelay = compound.getInt("ReapplicationDelay");
        this.radiusPerTick = compound.getFloat("RadiusPerTick");
        this.setRadius(compound.getFloat("Radius"));
        this.ownerUniqueId = compound.getUniqueId("OwnerUUID");
    }

    protected void writeAdditional(CompoundNBT compound) {
        compound.putInt("Age", this.ticksExisted);
        compound.putInt("Duration", this.duration);
        compound.putInt("WaitTime", this.waitTime);
        compound.putInt("ReapplicationDelay", this.reapplicationDelay);
        compound.putFloat("RadiusPerTick", this.radiusPerTick);
        compound.putFloat("Radius", this.getRadius());
        compound.putString("Particle", this.getParticleData().getParameters());
        if (this.ownerUniqueId != null) {
            compound.putUniqueId("OwnerUUID", this.ownerUniqueId);
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public EntitySize getSize(Pose poseIn) {
        return EntitySize.flexible(this.getRadius() * this.getType().getSize().width, this.getType().getSize().height);
    }

    @Override
    public void setOwner(@Nullable LivingEntity owner) {
        this.owner = owner;
        this.ownerUniqueId = owner == null ? null : owner.getUniqueID();
        super.setOwner(owner);
    }
}
