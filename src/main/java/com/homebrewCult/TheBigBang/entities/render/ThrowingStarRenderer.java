package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.entities.GenesisBeamEntity;
import com.homebrewCult.TheBigBang.entities.ThrowingStarEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ThrowingStarRenderer extends SpriteRenderer<ThrowingStarEntity> {

	private final ItemRenderer itemRenderer;
	private final float scale;
	
	public ThrowingStarRenderer(EntityRendererManager manager, ItemRenderer itemRenderer) {
		super(manager, itemRenderer);
	      this.itemRenderer = itemRenderer;
	      this.scale = 1f;
	}
	
	public ThrowingStarRenderer(EntityRendererManager manager, ItemRenderer itemRenderer, float scaleIn) {
		super(manager, itemRenderer);
	      this.itemRenderer = itemRenderer;
	      this.scale = scaleIn;
	}

	public void render(ThrowingStarEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		GlStateManager.pushMatrix();

		GlStateManager.enableRescaleNormal();
		GlStateManager.scalef(this.scale, this.scale, this.scale);
		GlStateManager.translatef((float)entity.getPosX(), (float)entity.getPosY(), (float)entity.getPosZ());
		GlStateManager.rotatef(45.0f, 0.0f, 0.0f, 1.0f);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) + 90.0F, 0.5f, 0.5f, 0.0F);
		GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch) * -1f, 0.0F, 0.0F, 1.0F);

		this.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
		this.itemRenderer.renderItem(((IRendersAsItem)entity).getItem(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, 0, matrixStackIn, bufferIn);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();

		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	public static class RenderFactory implements IRenderFactory<ThrowingStarEntity> {
		public EntityRenderer<? super ThrowingStarEntity> createRenderFor(EntityRendererManager manager) {
			return new ThrowingStarRenderer(manager, Minecraft.getInstance().getItemRenderer());
		}
	}

	private void bindTexture(ResourceLocation texture) {

	}

}
