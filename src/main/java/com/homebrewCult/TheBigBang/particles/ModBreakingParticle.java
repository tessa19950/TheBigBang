package com.homebrewCult.TheBigBang.particles;

import com.homebrewCult.TheBigBang.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBreakingParticle extends SpriteTexturedParticle {
	
	private final float uvX;
	private final float uvY;
	
	public ModBreakingParticle(World worldIn, double posX, double posY, double posZ, double xSpeed, double ySpeed, double zSpeed, ItemStack itemStack) {
	      this(worldIn, posX, posY, posZ, itemStack);
	      this.motionX *= (double)0.1F;
	      this.motionY *= (double)0.1F;
	      this.motionZ *= (double)0.1F;
	      this.motionX += xSpeed;
	      this.motionY += ySpeed;
	      this.motionZ += zSpeed;
	}

	public ModBreakingParticle(World worldIn, double posX, double posY, double posZ, ItemStack itemStack) {
	      super(worldIn, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
	      this.setSprite(Minecraft.getInstance().getItemRenderer().getModelWithOverrides(itemStack, worldIn, (LivingEntity)null).getParticleTexture());
	      this.particleGravity = 1.0F;
	      this.particleScale /= 2.0F;
	      this.uvX = this.rand.nextFloat() * 3.0F;
	      this.uvY = this.rand.nextFloat() * 3.0F;
	}

	protected float getMinU() {
		return this.sprite.getInterpolatedU((double)((this.uvX + 1.0F) / 4.0F * 16.0F));
	}

	protected float getMaxU() {
		return this.sprite.getInterpolatedU((double)(this.uvX / 4.0F * 16.0F));
	}

	protected float getMinV() {
		return this.sprite.getInterpolatedV((double)(this.uvY / 4.0F * 16.0F));
	}

	protected float getMaxV() {
		return this.sprite.getInterpolatedV((double)((this.uvY + 1.0F) / 4.0F * 16.0F));
	}

	public IParticleRenderType getRenderType() {
		return IParticleRenderType.TERRAIN_SHEET;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class BlueSlimeFactory implements IParticleFactory<BasicParticleType> {
        
        public BlueSlimeFactory(IAnimatedSprite spriteSet) {}
		
		@Override
		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			ModBreakingParticle particle = new ModBreakingParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, new ItemStack(ModItems.BLUE_SLIME_BALL));
			return particle;
		}
	}
}


