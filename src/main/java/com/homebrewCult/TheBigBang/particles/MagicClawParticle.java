package com.homebrewCult.TheBigBang.particles;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MagicClawParticle extends SpriteTexturedParticle {

	private final IAnimatedSprite sprites;
	
	protected MagicClawParticle(World worldIn, double x, double y, double z, IAnimatedSprite spritesIn) {
		super(worldIn, x, y, z);
		this.maxAge = 7;
		this.particleScale = 1;
		this.sprites = spritesIn;
		this.sprite = spritesIn.get(0, this.maxAge);
	}

	@Override
	public void renderParticle(IVertexBuilder vertexBuilder, ActiveRenderInfo renderInfo, float partialTicks) {
		if(this.age == 1) {
			Vec3d camPos = renderInfo.getRenderViewEntity().getEyePosition(partialTicks);
			Vec3d camDir = camPos.subtract(new Vec3d(this.posX, this.posY, this.posZ)).normalize();
			this.setPosition(this.posX + camDir.x, this.posY + camDir.y, this.posZ + camDir.z);
		}
		super.renderParticle(vertexBuilder, renderInfo, partialTicks);
	}
	
	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
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
			this.selectSpriteWithAge(this.sprites);
		}
	}
	
	@OnlyIn(Dist.CLIENT) 
	public static class MagicClawFactory implements IParticleFactory<BasicParticleType> {		
		private final IAnimatedSprite factorySprites;
		
		public MagicClawFactory(IAnimatedSprite spritesIn) {
			this.factorySprites = spritesIn;
		}
		
		@Override
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new MagicClawParticle(worldIn, x, y, z, this.factorySprites);
		}	
	}
}
