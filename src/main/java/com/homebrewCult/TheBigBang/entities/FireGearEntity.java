package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class FireGearEntity extends BigBangAreaEffectCloudEntity {

    public FireGearEntity(EntityType<?> type, World worldIn) { this(worldIn); }

    public FireGearEntity(World worldIn) { super(ModEntities.FIRE_GEAR, worldIn); }

    public FireGearEntity(World worldIn, double x, double y, double z) {
        super(ModEntities.FIRE_GEAR, worldIn);
        this.setPosition(x, y, z);
    }

    @Override
    void applyEffectToEntity(LivingEntity entity) {
        entity.setFire(10);
    }

    @Override
    void spawnAreaParticles(float radius) {
        if(!shouldSpawnParticle())
            return;
        double a = world.rand.nextFloat() * Math.PI * 2;
        double x1 = Math.sin(a) * (0.5 + world.rand.nextDouble()) * Math.max(radius - 1F, 0.5);
        double z1 = Math.cos(a) * (0.5 + world.rand.nextDouble()) * Math.max(radius - 1F, 0.5);
        world.addParticle(ModParticleTypes.FIRE_GEAR.get(), posX + x1, posY + 1, posZ + z1, 0, 0.01D, 0);
    }

    private boolean shouldSpawnParticle() {
        if(ticksExisted < 3)
            return true;
        if(ticksExisted <= 10)
            return ticksExisted % 2 == 0;
        if(ticksExisted <= 20)
            return ticksExisted % 4 == 0;
        else
            return ticksExisted % 6 == 0;
    }
}
