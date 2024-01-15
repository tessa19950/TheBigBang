package com.homebrewCult.TheBigBang.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class HyperBodyLayerModel extends Model {
	private final ModelRenderer coffin_front;
	private final ModelRenderer coffin_back;

	public HyperBodyLayerModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 128;
		textureHeight = 64;

		coffin_front = new ModelRenderer(this);
		coffin_front.setRotationPoint(1.0F, 24.0F, 0.0F);
		coffin_front.setTextureOffset(0, 0).addBox(-10.0F, -33.0F, -5.0F, 18.0F, 15.0F, 5.0F, 0.0F, false);
		coffin_front.setTextureOffset(120, 0).addBox(-11.5F, -31.0F, -6.0F, 3.0F, 13.0F, 1.0F, 0.0F, false);
		coffin_front.setTextureOffset(111, 0).addBox(8.0F, -20.5F, -6.0F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		coffin_front.setTextureOffset(103, 0).addBox(8.0F, -25.0F, -10.0F, 1.0F, 6.0F, 3.0F, 0.0F, false);
		coffin_front.setTextureOffset(93, 0).addBox(8.0F, -23.5F, -7.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		coffin_front.setTextureOffset(0, 40).addBox(-12.0F, -18.0F, -7.0F, 20.0F, 5.0F, 7.0F, 0.0F, false);
		coffin_front.setTextureOffset(0, 52).addBox(-10.0F, -13.0F, -7.0F, 20.0F, 5.0F, 7.0F, 0.0F, true);
		coffin_front.setTextureOffset(55, 38).addBox(-10.0F, -8.0F, -5.0F, 18.0F, 8.0F, 5.0F, 0.0F, false);

		coffin_back = new ModelRenderer(this);
		coffin_back.setRotationPoint(1.0F, 24.0F, 0.0F);
		coffin_back.setTextureOffset(0, 20).addBox(-10.0F, -33.0F, 0.0F, 18.0F, 15.0F, 5.0F, 0.0F, false);
		coffin_back.setTextureOffset(48, 2).addBox(-12.0F, -18.0F, 0.0F, 20.0F, 5.0F, 5.0F, 0.0F, true);
		coffin_back.setTextureOffset(48, 14).addBox(-10.0F, -13.0F, 0.0F, 20.0F, 5.0F, 5.0F, 0.0F, false);
		coffin_back.setTextureOffset(55, 25).addBox(-10.0F, -8.0F, 0.0F, 18.0F, 8.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {

	}

	public void render(float hyperBodyTime) {
		float f1 = 0;
		if(hyperBodyTime < 10) {
			f1 = MathHelper.sin((float)Math.PI * 1.5f);
		} else if (hyperBodyTime > 20) {
			f1 = (float)Math.PI * 2f;
		} else {
			float f = MathHelper.clamp(hyperBodyTime - 10, 0f, 30f) * 0.4f;
			f1 = MathHelper.sin(((float)Math.PI * 1.5f) + f);
		}
		coffin_front.rotationPointZ = MathHelper.clamp(f1 * 12f, -100f, 0f);
		coffin_back.rotationPointZ = MathHelper.clamp(-f1 * 12f, 0f, 100f);
		//coffin_front.render(1);
		//coffin_back.render(1);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
