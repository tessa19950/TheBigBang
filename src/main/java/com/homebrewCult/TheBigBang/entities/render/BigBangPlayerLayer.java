package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.GenesisBeamEntity;
import com.homebrewCult.TheBigBang.entities.model.GenesisAngelModel;
import com.homebrewCult.TheBigBang.entities.model.HyperBodyModel;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.items.LamaStaffItem;
import com.homebrewCult.TheBigBang.items.OmegaSpearItem;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BigBangPlayerLayer<T extends LivingEntity> extends LayerRenderer<T, PlayerModel<T>> {

	public static final ResourceLocation HYPER_BODY_TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/hyper_body.png");
	public static final ResourceLocation HYPER_BODY_FADE_0_TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/hyper_body_fade_0.png");
	public static final ResourceLocation HYPER_BODY_FADE_1_TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/hyper_body_fade_1.png");
	public static final ResourceLocation HYPER_BODY_FADE_2_TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/hyper_body_fade_2.png");
	public static final ResourceLocation HYPER_BODY_FADE_3_TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/hyper_body_fade_3.png");
	public static final ResourceLocation GENESIS_ANGEL_TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/genesis_angel.png");
	public static final ResourceLocation GENESIS_ANGEL_BRIGHT_TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/genesis_angel_bright.png");
	private static final HyperBodyModel HYPER_BODY_MODEL = new HyperBodyModel();
	private static final GenesisAngelModel GENESIS_MODEL = new GenesisAngelModel();
	
	public BigBangPlayerLayer(IEntityRenderer<T, PlayerModel<T>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		PlayerEntity player = (PlayerEntity)entityIn;
		if(!player.getHeldItemMainhand().hasTag())
			return;
		if(player.getHeldItemMainhand().getItem().equals(ModItems.LAMA_STAFF)) {
			CompoundNBT nbt = player.getHeldItemMainhand().getTag();
			if(nbt.contains(LamaStaffItem.SPELL_TIME_KEY)) {
				this.renderGenesisAngel(player, nbt, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleFactor);
			}
		}
		if(player.getHeldItemMainhand().getItem().equals(ModItems.OMEGA_SPEAR)) {
			CompoundNBT nbt = player.getHeldItemMainhand().getTag();
			if(nbt.contains(OmegaSpearItem.HYPER_BODY_TIME_KEY)) {
				this.renderHyperBody(player, nbt, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleFactor);
			}
		}
	}

	public void renderGenesisAngel(PlayerEntity player, CompoundNBT nbt, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		int genesisTime = player.ticksExisted - nbt.getInt(LamaStaffItem.SPELL_TIME_KEY);
		if(genesisTime > 0 && genesisTime < 45) {
			GlStateManager.pushMatrix();
			if(genesisTime > 30) {
				this.bindTexture(GENESIS_ANGEL_BRIGHT_TEXTURE_LOCATION);
			} else {
				this.bindTexture(GENESIS_ANGEL_TEXTURE_LOCATION);
			}
			float heightOffset = -1.5f + (genesisTime / 45F) * -1.5f;
			GlStateManager.translatef(0f, heightOffset, 0.2f);
			float scale = 0.0625F;
			GlStateManager.scalef(scale, scale, scale);
			int j = 15728880 % 65536;
			int k = 15728880 / 65536;
			GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float)j, (float)k);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, MathHelper.sin((genesisTime / 45F) * (float)Math.PI)); 
			GENESIS_MODEL.render(genesisTime + partialTicks);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
		}
	}
	
	public void renderHyperBody(PlayerEntity player, CompoundNBT nbt, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		int hyperBodyTime = player.ticksExisted - nbt.getInt(OmegaSpearItem.HYPER_BODY_TIME_KEY);
		if(hyperBodyTime < 30) {
			GlStateManager.pushMatrix();
			if(hyperBodyTime > 28) {
				this.bindTexture(HYPER_BODY_FADE_3_TEXTURE_LOCATION);
			} else if(hyperBodyTime > 26) {
				this.bindTexture(HYPER_BODY_FADE_2_TEXTURE_LOCATION);
			} else if(hyperBodyTime > 24) {
				this.bindTexture(HYPER_BODY_FADE_1_TEXTURE_LOCATION);
			} else if(hyperBodyTime > 20) {
				this.bindTexture(HYPER_BODY_FADE_0_TEXTURE_LOCATION);
			} else {
				this.bindTexture(HYPER_BODY_TEXTURE_LOCATION);
			}
			GlStateManager.translatef(0f, 0f, 0f);
			float scale = 0.0625F;
			float width = MathHelper.clamp((float)hyperBodyTime + partialTicks, 0f, 5f) / 5f;
			GlStateManager.scalef(scale * width, scale, scale);
			GlStateManager.enableBlend();
			if(hyperBodyTime > 22) {
				GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
				GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F - ((hyperBodyTime - 20) * 0.1F));
			}   
			HYPER_BODY_MODEL.render(hyperBodyTime + partialTicks);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
			if(hyperBodyTime == 12) {
				for(int x = 0; x < 3; ++x) {
					for(int y = 0; y < 3; ++y) {
						for(int z = 0; z < 3; ++z) {
							double p0 = player.posX + (x * 0.1d);
							double p1 = player.posY + (y * 0.8d);
							double p2 = player.posZ + (z * 0.1d);
							double s0 = (player.world.rand.nextDouble() - 0.5d) * 0.5d;
							double s1 = (player.world.rand.nextDouble() - 0.5d) * 0.5d;
							double s2 = (player.world.rand.nextDouble() - 0.5d) * 0.5d;
							player.world.addParticle(ParticleTypes.POOF, p0, p1, p2, s0, s1, s2);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
