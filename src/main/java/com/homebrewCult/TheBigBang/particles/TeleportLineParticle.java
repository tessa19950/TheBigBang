package com.homebrewCult.TheBigBang.particles;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TeleportLineParticle extends AxisLockedParticle {

    private IAnimatedSprite sprites;

    protected TeleportLineParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spritesIn) {
        super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, spritesIn);
        this.maxAge = 30;
        this.particleScale = 0.5F + worldIn.rand.nextFloat() * 0.5F;
        this.particleAngle = worldIn.rand.nextFloat() * 180F;
        this.prevParticleAngle = this.particleAngle;

        int i = 0;
        if(ySpeed != 0) {
            if(Math.abs(ySpeed) > 0.1)
                i = 1;
            else if(ySpeed < 0)
                i = 2;
            else if (ySpeed > 0)
                i = 3;
        }
        this.sprite = spritesIn.get(i, 3);
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.motionX = this.motionX * 0.9D;
        this.motionY = this.motionY * 0.9D;
        this.motionZ = this.motionZ * 0.9D;

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.move(this.motionX, this.motionY, this.motionZ);
            this.particleAlpha = 1 - ((float)this.age / (float)this.maxAge);
        }
    }

    @Override
    protected Vec3d[] getVertexPositions(float f) {
        float x = (float)age/(float)maxAge;
        float height = (float) (x == 1F ? 1F : 1F - Math.pow(2F, -10F * x));
        return new Vec3d[]{new Vec3d(0, -height*f, 1*f), new Vec3d(0, height*f, 1*f),
                new Vec3d(0, height*f, -1*f), new Vec3d(0, -height*f, -1*f)};
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getBrightnessForRender(float partialTick) {
        return 15728880;
    }

    @OnlyIn(Dist.CLIENT)
    public static class TeleportLineFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite factorySprites;

        public TeleportLineFactory(IAnimatedSprite spritesIn) {
            this.factorySprites = spritesIn;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new TeleportLineParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.factorySprites);
        }
    }
}
