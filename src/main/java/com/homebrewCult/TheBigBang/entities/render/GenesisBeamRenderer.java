package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.GenesisBeamEntity;
import com.homebrewCult.TheBigBang.entities.model.GenesisBeamModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GenesisBeamRenderer extends EntityRenderer<GenesisBeamEntity> {

	public static final GenesisBeamModel MODEL = new GenesisBeamModel();
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/genesis_beam_entity.png");
	
	public GenesisBeamRenderer(EntityRendererManager manager) {
		super(manager);
	}
	
	public GenesisBeamRenderer(EntityRendererManager manager, float scaleIn) {
		super(manager);
	}
	
	@Override
	public void doRender(GenesisBeamEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		float scale = 0.15f;
		float animTime = (entity.ticksExisted + partialTicks) / (GenesisBeamEntity.MAX_AGE);  
		float doubleAnimTime = MathHelper.clamp(animTime * 2f, 0f, 1f);
		float heightOffset = (24f * scale) + (Math.abs(doubleAnimTime - 1f) * 4f);
		GlStateManager.translatef((float)x, (float)y + heightOffset, (float)z);
		GlStateManager.scalef(scale - (animTime * scale), MathHelper.lerp(doubleAnimTime, 0, scale), scale - (animTime * scale));

	    GlStateManager.rotatef((entity.ticksExisted + partialTicks), 0.0F, 1.0F, 0.0F);
	    GlStateManager.enableBlend();
	    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
	    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.75F);
	    
	    this.bindEntityTexture(entity);
	    MODEL.renderer();
	    GlStateManager.disableBlend();
	    
	    GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	public static class RenderFactory implements IRenderFactory<GenesisBeamEntity> {
		public EntityRenderer<? super GenesisBeamEntity> createRenderFor(EntityRendererManager manager) {
			return new GenesisBeamRenderer(manager);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(GenesisBeamEntity entity) {
		return TEXTURE_LOCATION;
	}
}
