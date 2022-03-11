package com.homebrewCult.TheBigBang.particles;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FlashJumpParticle extends AxisLockedParticle {

    protected FlashJumpParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spritesIn) {
        super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, spritesIn);
        this.particleScale = 1;
        this.particleAngle = (float)Math.atan2(xSpeed, zSpeed) + (90F * (float)Math.PI / 180F); //* 180F / (float)Math.PI;
        this.prevParticleAngle = this.particleAngle;
    }

    @Override
    protected Vec3d[] getVertexPositions(float f) {
        return new Vec3d[]{new Vec3d(0, -1*f, 1*f), new Vec3d(0, 1*f, 1*f), new Vec3d(0, 1*f, -1*f), new Vec3d(0, -1*f, -1*f)};
    }

    @Override
    public double getMotionDrag() {
        return 0.75D;
    }

    @Override
    public void tick() {
        super.tick();
        if(!isExpired) {
            this.move(this.motionX, this.motionY, this.motionZ);
            this.particleAlpha = ((float) this.age / (float) this.maxAge) * -1 + 1;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class FlashJumpFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite factorySprites;

        public FlashJumpFactory(IAnimatedSprite spritesIn) {
            this.factorySprites = spritesIn;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new FlashJumpParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.factorySprites);
        }
    }
}
