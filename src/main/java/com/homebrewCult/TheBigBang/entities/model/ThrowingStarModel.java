package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.TheBigBang;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

//Made with Blockbench 3.6.5
//Exported for Minecraft version 1.14
//Paste this class into your mod and generate all required imports

public class ThrowingStarModel extends Model {
	
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/item/steely_projectile.png");
	private final ModelRenderer modelRenderer;

	public ThrowingStarModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 16;
		textureHeight = 16;

		modelRenderer = new ModelRenderer(this);
		modelRenderer.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(modelRenderer, 0.7854F, 0.0F, 0.0F);
		modelRenderer.setTextureOffset(-16, -16).addBox(0.0F, -8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		modelRenderer.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
