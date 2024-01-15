package com.homebrewCult.TheBigBang.layers;

import com.homebrewCult.TheBigBang.TheBigBang;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class HurricaneLayerModel extends Model {
	private final ModelRenderer body;
	private final ModelRenderer wing1;
	private final ModelRenderer wing2;
	private final ModelRenderer wing3;
	private final ModelRenderer wing4;
	private final ModelRenderer wing5;
	private final ModelRenderer wing6;
	private final ModelRenderer[] wings;

	public HurricaneLayerModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 1.0F, -6.0F);
		body.setTextureOffset(0, 0).addBox(-11.0F, -11.0F, -5.0F, 22.0F, 22.0F, 10.0F, 0.0F, false);

		wing1 = new ModelRenderer(this);
		wing1.setRotationPoint(0.0F, -10.0F, -4.0F);
		wing1.setTextureOffset(0, 21).addBox(0.0F, -24.0F, -5.0F, 0.0F, 26.0F, 17.0F, 0.0F, false);

		wing2 = new ModelRenderer(this);
		wing2.setRotationPoint(11.0F, -7.0F, -4.0F);
		setRotationAngle(wing2, 0.0F, 0.0F, 1.0472F);
		wing2.setTextureOffset(0, 21).addBox(0.0F, -24.0F, -5.0F, 0.0F, 26.0F, 17.0F, 0.0F, false);

		wing3 = new ModelRenderer(this);
		wing3.setRotationPoint(11.0F, 9.0F, -4.0F);
		setRotationAngle(wing3, 0.0F, 0.0F, 2.0944F);
		wing3.setTextureOffset(0, 21).addBox(0.0F, -24.0F, -5.0F, 0.0F, 26.0F, 17.0F, 0.0F, false);

		wing4 = new ModelRenderer(this);
		wing4.setRotationPoint(0.0F, 12.0F, -4.0F);
		setRotationAngle(wing4, 0.0F, 0.0F, -3.1416F);
		wing4.setTextureOffset(0, 21).addBox(0.0F, -24.0F, -5.0F, 0.0F, 26.0F, 17.0F, 0.0F, false);

		wing5 = new ModelRenderer(this);
		wing5.setRotationPoint(-11.0F, 9.0F, -4.0F);
		setRotationAngle(wing5, 0.0F, 0.0F, -2.0944F);
		wing5.setTextureOffset(0, 21).addBox(0.0F, -24.0F, -5.0F, 0.0F, 26.0F, 17.0F, 0.0F, false);

		wing6 = new ModelRenderer(this);
		wing6.setRotationPoint(-11.0F, -7.0F, -4.0F);
		setRotationAngle(wing6, 0.0F, 0.0F, -1.0472F);
		wing6.setTextureOffset(0, 21).addBox(0.0F, -24.0F, -5.0F, 0.0F, 26.0F, 17.0F, 0.0F, false);

		wings = new ModelRenderer[] {wing1, wing2, wing3, wing4, wing5, wing6};
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {

	}

	public void render(float pullTime) {
		float pullPct = MathHelper.clamp((pullTime - 1) / 2, 0, 1);
		TheBigBang.print("Pct = " + pullPct);
		body.rotateAngleZ = MathHelper.clamp(pullTime - 1, 0, 72000) * MathHelper.clamp(pullTime - 1, 0, 72000);
		for(int i = 0; i < 6; ++i) {
			float y = (float) Math.sin(pullTime * 8 + 2 + (i * 0.1f)) * -0.25f;
			float z = (float) Math.sin(pullTime * 8 + (i * 0.1f)) * 0.25f;
			this.setRotationAngle(wings[i], 0, y, (i * 1.047f) + z);
		}
		//body.render(1);
		//wing1.render(1);
		//wing2.render(1);
		//wing3.render(1);
		//wing4.render(1);
		//wing5.render(1);
		//wing6.render(1);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
