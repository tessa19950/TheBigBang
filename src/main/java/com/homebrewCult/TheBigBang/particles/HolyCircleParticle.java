package com.homebrewCult.TheBigBang.particles;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HolyCircleParticle extends AxisLockedParticle {
	
	protected HolyCircleParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spritesIn) {
		super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, spritesIn);
		this.particleScale = 0;
	}
	
	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}
	
	public int getBrightnessForRender(float partialTick) {
		return 15728880;
	}

	@Override
	public void tick() {
		super.tick();
		if(!isExpired) {
			this.selectSpriteWithAge(this.sprites);
			this.move(this.motionX, this.motionY, this.motionZ);
			this.particleAngle = this.particleAngle + 0.1f;
			if (this.age > 50) {
				this.particleAlpha = (((float) this.age - 50F) / 10F) * -1 + 1;
			}
			float newScale = (float) (Math.sin((float) this.age / 10f));
			if (newScale > this.particleScale) {
				this.particleScale = newScale;
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class HolyCircleFactory implements IParticleFactory<BasicParticleType> {		
		private final IAnimatedSprite factorySprites;
		
		public HolyCircleFactory(IAnimatedSprite spritesIn) {
			this.factorySprites = spritesIn;
		}
		
		@Override
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new HolyCircleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.factorySprites);
		}	
	}
}
