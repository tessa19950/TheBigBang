package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.BombArrowEntity;
import com.homebrewCult.TheBigBang.entities.GenesisBeamEntity;
import com.homebrewCult.TheBigBang.entities.model.BigBangArrowModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BombArrowRenderer extends EntityRenderer<BombArrowEntity> {

	public static final BigBangArrowModel MODEL = new BigBangArrowModel();
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/bomb_arrow.png");
	
	public BombArrowRenderer(EntityRendererManager manager) {
		super(manager);
	}
	
	public BombArrowRenderer(EntityRendererManager manager, float scaleIn) {
		super(manager);
	}

	@Override
	public void render(BombArrowEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		GlStateManager.pushMatrix();
		float scale = 0.15f;
		GlStateManager.translatef((float)entity.getPosX(), (float)entity.getPosY(), (float)entity.getPosZ());
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch), 0.0F, 0.0F, 1.0F);
		GlStateManager.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.translatef(0, -1.2F, 0.4F);
		GlStateManager.scalef(0.05625F, 0.05625F, 0.05625F);

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.bindEntityTexture(entity);
		//MODEL.render();

		GlStateManager.popMatrix();
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	public static class RenderFactory implements IRenderFactory<BombArrowEntity> {
		public EntityRenderer<? super BombArrowEntity> createRenderFor(EntityRendererManager manager) {
			return new BombArrowRenderer(manager);
		}
	}

	@Override
	public ResourceLocation getEntityTexture(BombArrowEntity entity) {
		return TEXTURE_LOCATION;
	}

	private void bindEntityTexture(BombArrowEntity entity) {

	}
}
