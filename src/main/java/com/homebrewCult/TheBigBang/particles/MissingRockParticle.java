package com.homebrewCult.TheBigBang.particles;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MissingRockParticle extends SpriteTexturedParticle {
    public MissingRockParticle(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite sprites) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.maxAge = 20;
        this.particleScale = Minecraft.getInstance().gameSettings.thirdPersonView == 0 ? 0.1F : 0.25F;
        this.motionX = xSpeed;
        this.motionY = ySpeed;
        this.motionZ = zSpeed;
        this.sprite = sprites.get(world.getRandom());
        this.particleAlpha = 0.5F;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.move(this.motionX * 0.9, this.motionY * 0.9, this.motionZ * 0.9);
            this.particleAlpha = 0.5F - ((float)this.age / (float)this.maxAge / 2F);
        }
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
            return new MissingRockParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.factorySprites);
        }
    }
}
