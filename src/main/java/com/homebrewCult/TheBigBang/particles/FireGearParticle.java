package com.homebrewCult.TheBigBang.particles;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FireGearParticle extends SpriteTexturedParticle {

    private final int type;
    private final boolean reverse;
    private IAnimatedSprite sprites;

    protected FireGearParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spritesIn) {
        super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        this.maxAge = 40 + worldIn.rand.nextInt(40);
        this.particleScale = 0.3F + worldIn.rand.nextFloat() * 1.2F;
        this.posY += (worldIn.rand.nextDouble() - 0.5D) * (2 - this.particleScale);
        this.setPosition(posX, posY, posZ);
        this.prevPosY = posY;

        this.sprites = spritesIn;
        this.motionX = xSpeed;
        this.motionY = ySpeed;
        this.motionZ = zSpeed;
        this.reverse = worldIn.rand.nextBoolean();
        this.type = (worldIn.rand.nextInt(4) * 4) + (reverse ? 3 : 0);
        setSprite(spritesIn.get(type, 15));
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.motionX = this.motionX * 0.9D;
        this.motionY = this.motionY * 0.9D;
        this.motionZ = this.motionZ * 0.9D;

        int frame = (int)Math.floor((float)this.age * 0.4F) % 4;
        this.setSprite(this.sprites.get(reverse ? type - frame : type + frame, 15));

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
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new FireGearParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.factorySprites);
        }
    }
}
