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

public class HolyHexagramParticle extends AxisLockedParticle {
	
	protected HolyHexagramParticle(World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite sprites) {
		super(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, sprites);
		this.particleScale = 0;
	}

	@Override
	public void tick() {
		super.tick();
		if(!isExpired) {
			this.move(this.motionX, this.motionY, this.motionZ);
			this.particleAngle = this.particleAngle + 0.1f;
			this.particleAlpha = ((float) this.age / (float) this.maxAge) * -1 + 1;
			float newScale = (float) (Math.sin((float) this.age / 10f)) * 0.5f;
			if(newScale > this.particleScale) {
				this.particleScale = newScale;
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class HolyHexagramFactory implements IParticleFactory<BasicParticleType> {		
		private final IAnimatedSprite factorySprites;
		
		public HolyHexagramFactory(IAnimatedSprite spritesIn) {
			this.factorySprites = spritesIn;
		}
		
		@Override
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new HolyHexagramParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.factorySprites);
		}	
	}
}
