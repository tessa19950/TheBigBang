package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class SnailShellEntity extends ProjectileItemEntity {

    public SnailShellEntity(EntityType<? extends Entity> type, World worldIn) {
        super(ModEntities.SNAIL_SHELL, worldIn);
    }

    public SnailShellEntity(World worldIn, LivingEntity shooter) {
        super(ModEntities.SNAIL_SHELL, shooter, worldIn);
    }

    public SnailShellEntity(World worldIn, LivingEntity shooter, ItemStack shellStack) {
        super(ModEntities.SNAIL_SHELL, shooter, worldIn);
        this.setItem(shellStack);
    }

    public SnailShellEntity(World worldIn) {
        super(ModEntities.SNAIL_SHELL, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return getFallbackItem();
    }

    protected Item getFallbackItem() {
        ItemStack itemStack = this.func_213882_k();
        return itemStack.isEmpty() ? ModItems.GREEN_SNAIL_SHELL : itemStack.getItem();
    }

    @OnlyIn(Dist.CLIENT)
    private IParticleData func_213887_n() {
        ItemStack itemstack = this.func_213882_k();
        return itemstack.isEmpty() ? ParticleTypes.POOF : new ItemParticleData(ParticleTypes.ITEM, itemstack);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            IParticleData iparticledata = this.func_213887_n();
            for(int i = 0; i < 8; ++i)
                this.world.addParticle(iparticledata, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), getShellDamage());
        }
        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    private int getShellDamage() {
        Item item = getFallbackItem();
        if(item.equals(ModItems.RED_SNAIL_SHELL))
            return 5;
        if(item.equals(ModItems.BLUE_SNAIL_SHELL))
            return 3;
        return 1;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public ItemStack getItem() { return new ItemStack(getFallbackItem()); }
}
