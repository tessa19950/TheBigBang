package com.homebrewCult.TheBigBang.particles;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModAnimatedParticle extends SpriteTexturedParticle {

	private final IAnimatedSprite SPRITE_SET;
	
	protected ModAnimatedParticle(World worldIn, double x, double y, double z, IAnimatedSprite spriteSetIn) {
		super(worldIn, x, y, z);
		this.maxAge = 6;
		this.particleScale = 2;
		SPRITE_SET = spriteSetIn;
		this.setSprite(sprite);
		this.selectSpriteWithAge(SPRITE_SET);
	}
	
	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_LIT;
	}
	
	public int getBrightnessForRender(float partialTick) {
		return 15728880;
	}
	
	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.age++ >= this.maxAge) {
			this.setExpired();
		} else {
			this.selectSpriteWithAge(this.SPRITE_SET);
		}
	}
	
	protected float getMinU() { return 0f; }

	protected float getMaxU() { return 32f; }

	protected float getMinV() { return 0f; }

	protected float getMaxV() { return 32f; }
	
	@OnlyIn(Dist.CLIENT) 
	public static class MagicClawFactory implements IParticleFactory<BasicParticleType> {		
		private final IAnimatedSprite SPRITE_SET;
		
		public MagicClawFactory(IAnimatedSprite spriteSetIn) {
			this.SPRITE_SET = spriteSetIn;
		}
		
		@Override
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new ModAnimatedParticle(worldIn, x, y, z, SPRITE_SET);
		}	
	}
}
