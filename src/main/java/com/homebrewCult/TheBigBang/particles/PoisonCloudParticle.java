package com.homebrewCult.TheBigBang.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PoisonCloudParticle extends SpriteTexturedParticle {

    private IAnimatedSprite sprites;

    protected PoisonCloudParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spritesIn) {
        super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        this.maxAge = 40 + worldIn.rand.nextInt(40);
        this.particleScale = 0.5F + worldIn.rand.nextFloat();
        this.sprites = spritesIn;
        this.motionX = xSpeed;
        this.motionY = ySpeed;
        this.motionZ = zSpeed;
        this.selectSpriteRandomly(sprites);
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
        return f >= particleScale ? 1 : (float) (1 - Math.pow(2, -10 * f)) * particleScale;
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
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new PoisonCloudParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.factorySprites);
        }
    }
}
