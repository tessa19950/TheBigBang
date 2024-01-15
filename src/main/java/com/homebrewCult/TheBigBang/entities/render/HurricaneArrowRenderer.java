package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.GenesisBeamEntity;
import com.homebrewCult.TheBigBang.entities.HurricaneArrowEntity;
import com.homebrewCult.TheBigBang.entities.SnipingArrowEntity;
import com.homebrewCult.TheBigBang.entities.model.BigBangArrowModel;
import com.homebrewCult.TheBigBang.entities.model.GenesisBeamModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class HurricaneArrowRenderer extends EntityRenderer<HurricaneArrowEntity> {

	public static final BigBangArrowModel MODEL = new BigBangArrowModel();
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/hurricane_arrow.png");
	
	public HurricaneArrowRenderer(EntityRendererManager manager) {
		super(manager);
	}
	
	public HurricaneArrowRenderer(EntityRendererManager manager, float scaleIn) {
		super(manager);
	}

	@Override
	public void render(HurricaneArrowEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		GlStateManager.pushMatrix();
		float scale = 0.15f;
		GlStateManager.translatef((float)entity.getPosX(), (float)entity.getPosY(), (float)entity.getPosZ());
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch), 0.0F, 0.0F, 1.0F);
		GlStateManager.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
	    GlStateManager.translatef(0, -1.2F, 0.2F);
		GlStateManager.scalef(0.05625F, 0.05625F, 0.05625F);

	    GlStateManager.enableBlend();
	    //GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
	    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.bindEntityTexture(entity);
	    //MODEL.render();
	    GlStateManager.disableBlend();
	    GlStateManager.popMatrix();

		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	public static class RenderFactory implements IRenderFactory<HurricaneArrowEntity> {
		public EntityRenderer<? super HurricaneArrowEntity> createRenderFor(EntityRendererManager manager) {
			return new HurricaneArrowRenderer(manager);
		}
	}

	@Override
	public ResourceLocation getEntityTexture(HurricaneArrowEntity entity) {
		return TEXTURE_LOCATION;
	}

	private void bindEntityTexture(Entity entity) {

	}
}
