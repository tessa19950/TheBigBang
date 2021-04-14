package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class DragonCrusherStabEntity extends AbstractArrowEntity {

    public DragonCrusherStabEntity(EntityType<?> type, World worldIn) {
        super(ModEntities.DRAGON_CRUSHER_STAB, worldIn);
    }

    public DragonCrusherStabEntity(World worldIn) {
        super(ModEntities.DRAGON_CRUSHER_STAB, worldIn);
    }

    public DragonCrusherStabEntity(World worldIn, double x, double y, double z) {
        super(ModEntities.DRAGON_CRUSHER_STAB, x, y, z, worldIn);
    }

    public DragonCrusherStabEntity(World worldIn, LivingEntity shooter) {
        super(ModEntities.DRAGON_CRUSHER_STAB, shooter, worldIn);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.ticksExisted > 30)
            this.remove();
    }

    @Override
    public void onCollideWithPlayer(PlayerEntity entityIn) { }

    @Override
    public boolean canBeCollidedWith() { return false; }

    @Override
    protected void doBlockCollisions() { }

    @Override
    public void applyEntityCollision(Entity entityIn) { }

    @Override
    protected void onHit(RayTraceResult raytraceResultIn) { }

    @Override
    protected void arrowHit(LivingEntity living) { }

    @Override
    protected ItemStack getArrowStack() { return ItemStack.EMPTY; }

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
}
