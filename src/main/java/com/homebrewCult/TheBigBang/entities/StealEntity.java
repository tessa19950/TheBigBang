package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class StealEntity extends Entity {

    public static final int MAX_AGE = 40;
    private Entity summoner;
    private Entity target;
    public boolean hasHit;
    public static final DataParameter<Integer> TARGET;
    public static final DataParameter<Integer> SUMMONER;

    static {
        TARGET = EntityDataManager.createKey(StealEntity.class, DataSerializers.VARINT);
        SUMMONER = EntityDataManager.createKey(StealEntity.class, DataSerializers.VARINT);
    }

    public StealEntity(EntityType<?> type, World worldIn) {
        super(ModEntities.STEAL, worldIn);
    }

    public StealEntity(World worldIn) {
        super(ModEntities.STEAL, worldIn);
    }

    public StealEntity(World worldIn, PlayerEntity summoner, Entity target) {
        super(ModEntities.STEAL, worldIn);
        this.summoner = summoner;
        this.target = target;
        setSummoner(summoner.getEntityId());
        setTarget(target.getEntityId());
    }

    @Override
    public void tick() {
        super.tick();
        if(summoner == null)
            summoner = world.getEntityByID(getSummoner());
        if(target == null)
            target = world.getEntityByID(getTarget());
        if(summoner != null && target != null) {
            Vec3d vec3d = this.getMotion();
            double px = getPosX() + vec3d.x;
            double py = getPosY() + vec3d.y;
            double pz = getPosZ() + vec3d.z;
            setPosition(px, py, pz);

            if(!hasHit) {
                this.setMotion(target.getPositionVec().subtract(getPositionVec()).normalize().scale(2));
                this.setRotation(summoner.getYaw(0), summoner.getPitch(0));
                if (getDistanceSq(target) < 5 && summoner instanceof PlayerEntity) {
                    target.attackEntityFrom(DamageSource.GENERIC, 5);
                    target.addVelocity(getMotion().x/2D, getMotion().y/2D, getMotion().z/2D);
                    lootEntity((PlayerEntity) summoner, target);
                    hasHit = true;
                }
            } else {
                this.setMotion(vec3d.scale(0.65));
            }
            this.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
        }

        if(ticksExisted > MAX_AGE)
            this.remove();
    }

    public void lootEntity(PlayerEntity summoner, Entity entity) {
        if(entity.world.isRemote)
            return;

        Set<String> tags = entity.getTags();
        for(String tag : tags) {
            if(tag.equals("looted"))
                return;
        }

        LootContext.Builder lootContext = (new LootContext.Builder((ServerWorld)entity.world)).withRandom(summoner.getRNG()).withLuck(summoner.getLuck());
        lootContext = lootContext.withParameter(LootParameters.THIS_ENTITY, summoner)
                .withParameter(LootParameters.POSITION, new BlockPos(summoner)).withParameter(LootParameters.DAMAGE_SOURCE, DamageSource.GENERIC);
        LootTable lootTable = Objects.requireNonNull(entity.world.getServer()).getLootTableManager().getLootTableFromLocation(entity.getType().getLootTable());
        List<ItemStack> items = lootTable.generate(lootContext.build(LootParameterSets.ENTITY));
        if (items.size() > 0) {
            entity.getEntityWorld().addEntity(new ItemEntity(entity.world, entity.getPosX(), entity.getPosY() + entity.getHeight(), entity.getPosZ(), items.get(0)));
        }
        entity.addTag("looted");
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public float getBrightness() {
        return 1.0F;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBrightnessForRender() {
        return 15728880;
    }

    protected void registerData() {
        this.dataManager.register(SUMMONER, 0);
        this.dataManager.register(TARGET, 0);
    }

    public int getSummoner() {
        return dataManager.get(SUMMONER);
    }

    public void setSummoner(int id) {
        dataManager.set(SUMMONER, id);
    }

    public int getTarget() {
        return dataManager.get(TARGET);
    }

    public void setTarget(int id) {
        dataManager.set(TARGET, id);
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) { }

    @Override
    protected void readAdditional(CompoundNBT compound) { }
}
