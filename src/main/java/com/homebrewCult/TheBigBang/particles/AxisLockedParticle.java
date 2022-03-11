package com.homebrewCult.TheBigBang.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AxisLockedParticle extends SpriteTexturedParticle {

    protected IAnimatedSprite sprites;

    protected AxisLockedParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spritesIn) {
        super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
        this.motionX = xSpeed;
        this.motionY = ySpeed;
        this.motionZ = zSpeed;
        this.maxAge = 60;
        this.sprites = spritesIn;
        this.sprite = spritesIn.get(worldIn.getRandom());
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevParticleAngle = this.particleAngle;

        this.motionX = this.motionX * getMotionDrag();
        this.motionY = this.motionY * getMotionDrag();
        this.motionZ = this.motionZ * getMotionDrag();

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        }
    }

    public double getMotionDrag() {
        return 0.95D;
    }
    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getBrightnessForRender(float partialTick) {
        return 15728880;
    }

    protected Vec3d[] getVertexPositions(float f) {
        return new Vec3d[]{new Vec3d(1*f, 0, 1*f), new Vec3d(1*f, 0, -1*f), new Vec3d(-1*f, 0, -1*f), new Vec3d(-1*f, 0, 1*f)};
    }

    @Override
    public void renderParticle(BufferBuilder buffer, ActiveRenderInfo entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        float f = this.getScale(partialTicks);
        float f1 = this.getMinU();
        float f2 = this.getMaxU();
        float f3 = this.getMinV();
        float f4 = this.getMaxV();
        float f5 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosX, this.posX) - interpPosX);
        float f6 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosY, this.posY) - interpPosY);
        float f7 = (float)(MathHelper.lerp((double)partialTicks, this.prevPosZ, this.posZ) - interpPosZ);
        int i = this.getBrightnessForRender(partialTicks);
        int j = i >> 16 & '\uffff';
        int k = i & '\uffff';

        Vec3d[] avec3d = getVertexPositions(f);
        if (this.particleAngle != 0.0F) {
            float f8 = MathHelper.lerp(partialTicks, this.prevParticleAngle, this.particleAngle);
            float f9 = MathHelper.cos(f8 * 0.5F);
            float f10 = (float)((double)MathHelper.sin(f8 * 0.5F) * 0);
            float f11 = (float)((double)MathHelper.sin(f8 * 0.5F) * 1);
            float f12 = (float)((double)MathHelper.sin(f8 * 0.5F) * 0);

            Vec3d vec3d = new Vec3d((double)f10, (double)f11, (double)f12);
            for(int l = 0; l < 4; ++l) {
                avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale((double)(f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale((double)(2.0F * f9)));
            }
        }
        buffer.pos((double)f5 + avec3d[0].x, (double)f6 + avec3d[0].y, (double)f7 + avec3d[0].z).tex((double)f2, (double)f4).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)f5 + avec3d[1].x, (double)f6 + avec3d[1].y, (double)f7 + avec3d[1].z).tex((double)f2, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)f5 + avec3d[2].x, (double)f6 + avec3d[2].y, (double)f7 + avec3d[2].z).tex((double)f1, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos((double)f5 + avec3d[3].x, (double)f6 + avec3d[3].y, (double)f7 + avec3d[3].z).tex((double)f1, (double)f4).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
    }
}
