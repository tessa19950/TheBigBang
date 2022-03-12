package com.homebrewCult.TheBigBang.entities;

import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class PoisonMistEntity extends BigBangAreaEffectCloudEntity {

    public PoisonMistEntity(EntityType<?> type, World worldIn) { this(worldIn); }

    public PoisonMistEntity(World worldIn) { super(ModEntities.POISON_MIST, worldIn); }

    public PoisonMistEntity(World worldIn, double x, double y, double z) {
        super(ModEntities.POISON_MIST, worldIn);
        this.setPosition(x, y, z);
    }

    @Override
    void applyEffectToEntity(LivingEntity entity) {
        EffectInstance effect = new EffectInstance(Effects.POISON, 100);
        entity.addPotionEffect(effect);
    }

    @Override
    void spawnAreaParticles(float radius) {
        if(!shouldSpawnParticle())
            return;
        double a = world.rand.nextFloat() * Math.PI * 2;
        double x1 = Math.sin(a) * (0.5 + world.rand.nextDouble()) * Math.max(radius - 1F, 0.5);
        double z1 = Math.cos(a) * (0.5 + world.rand.nextDouble()) * Math.max(radius - 1F, 0.5);
        double x2 = x1 * 0.005;
        double z2 = z1 * 0.005;
        world.addParticle(ModParticleTypes.POISON_CLOUD.get(), posX + x1, posY + 0.75F, posZ + z1, x2, -0.001D, z2);
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
