package com.homebrewCult.TheBigBang.particles;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GlowleafParticle extends SpriteTexturedParticle {

    private IAnimatedSprite sprites;

    protected GlowleafParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spritesIn) {
        super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = spritesIn;
        this.maxAge = 80;
        this.age = worldIn.rand.nextInt(40);
        this.particleScale = 0.2F;
        this.particleGravity = 0.05F;
        this.motionX = xSpeed;
        this.motionY = ySpeed;
        this.motionZ = zSpeed;
        this.selectSpriteWithAge(sprites);
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.motionX = this.motionX * 0.99D;
        this.motionY -= 0.04D * (double)this.particleGravity;
        this.motionZ = this.motionZ * 0.99D;

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else if (!onGround) {
            int frame = (int)Math.floor((float)this.age * 0.3F) % 16;
            this.setSprite(this.sprites.get(frame, 16));
            this.move(this.motionX, this.motionY, this.motionZ);
        }
        this.particleAlpha = MathHelper.cos((float)this.age / (float)this.maxAge * (float)Math.PI / 2F);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getBrightnessForRender(float partialTick) {
        return 15728880;
    }

    @OnlyIn(Dist.CLIENT)
    public static class GlowleafFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite factorySprites;

        public GlowleafFactory(IAnimatedSprite spritesIn) {
            this.factorySprites = spritesIn;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new GlowleafParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.factorySprites);
        }
    }
}
