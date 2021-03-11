package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.BombArrowEntity;
import com.homebrewCult.TheBigBang.entities.GenesisBeamEntity;
import com.homebrewCult.TheBigBang.entities.model.BigBangArrowModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BombArrowRenderer extends EntityRenderer<BombArrowEntity> {

	public static final BigBangArrowModel MODEL = new BigBangArrowModel();
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/bomb_arrow.png");
	
	public BombArrowRenderer(EntityRendererManager manager) {
		super(manager);
	}
	
	public BombArrowRenderer(EntityRendererManager manager, float scaleIn) {
		super(manager);
	}
	
	@Override
	public void doRender(BombArrowEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		float scale = 0.15f;
		GlStateManager.translatef((float)x, (float)y, (float)z);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch), 0.0F, 0.0F, 1.0F);  
		GlStateManager.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
	    GlStateManager.translatef(0, -1.2F, 0.4F);		
		GlStateManager.scalef(0.05625F, 0.05625F, 0.05625F);
		
	    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.bindEntityTexture(entity);
	    MODEL.render();
	    
	    GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	public static class RenderFactory implements IRenderFactory<BombArrowEntity> {
		public EntityRenderer<? super BombArrowEntity> createRenderFor(EntityRendererManager manager) {
			return new BombArrowRenderer(manager);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(BombArrowEntity entity) {
		return TEXTURE_LOCATION;
	}
}
