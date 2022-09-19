package com.homebrewCult.TheBigBang.particles;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SymbolParticle extends SpriteTexturedParticle {

    private IAnimatedSprite sprites;

    protected SymbolParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spritesIn) {
        super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        this.canCollide = false;
        this.maxAge = 60;
        this.particleScale = 0.1F;
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
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getBrightnessForRender(float partialTick) {
        return 15728880;
    }

    @OnlyIn(Dist.CLIENT)
    public static class SymbolFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite factorySprites;

        public SymbolFactory(IAnimatedSprite spritesIn) {
            this.factorySprites = spritesIn;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new SymbolParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.factorySprites);
        }
    }
}
