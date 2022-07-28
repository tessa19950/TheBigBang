package com.homebrewCult.TheBigBang.particles;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DmgNumberParticle extends SpriteTexturedParticle {

    protected DmgNumberParticle(World worldIn, double x, double y, double z, double numberX, double numberY, double numberZ, IAnimatedSprite spritesIn) {
        super(worldIn, x, y, z, numberX, numberY, numberZ);
        this.maxAge = 30;
        this.particleScale = 0.3F;
        this.setPosition(posX, posY, posZ);

        this.motionX = 0.0D;
        this.motionY = 0.01D;
        this.motionZ = 0.0D;

        setSprite(spritesIn.get((int)Math.floor(numberX), 10));
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.move(this.motionX, this.motionY, this.motionZ);
            this.particleAlpha = 1 - ((float)this.age / (float)this.maxAge);
        }
    }

    @Override
    public float getScale(float partialTicks) {
        float f = ((float)age + partialTicks) * 0.1F;
        double c1 = 1.7015D;
        double c3 = c1 + 1;
        return f > 1 ? particleScale : (float) (1 + c3 * Math.pow(f - 1, 3) + c1 * Math.pow(f - 1, 2)) * particleScale;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getBrightnessForRender(float partialTick) {
        return 15728880;
    }

    @OnlyIn(Dist.CLIENT)
    public static class ParticleFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite factorySprites;

        public ParticleFactory(IAnimatedSprite spritesIn) {
            this.factorySprites = spritesIn;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double number, double zSpeed) {
            return new DmgNumberParticle(worldIn, x, y, z, xSpeed, number, zSpeed, this.factorySprites);
        }
    }
}
