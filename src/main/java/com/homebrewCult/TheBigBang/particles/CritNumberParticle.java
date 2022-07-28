package com.homebrewCult.TheBigBang.particles;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CritNumberParticle extends DmgNumberParticle {

    protected CritNumberParticle(World worldIn, double x, double y, double z, double numberX, double numberY, double numberZ, IAnimatedSprite spritesIn) {
        super(worldIn, x, y, z, numberX, numberY, numberZ, spritesIn);
    }

    @OnlyIn(Dist.CLIENT)
    public static class ParticleFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite factorySprites;

        public ParticleFactory(IAnimatedSprite spritesIn) {
            this.factorySprites = spritesIn;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double number, double zSpeed) {
            return new CritNumberParticle(worldIn, x, y, z, xSpeed, number, zSpeed, this.factorySprites);
        }
    }
}
