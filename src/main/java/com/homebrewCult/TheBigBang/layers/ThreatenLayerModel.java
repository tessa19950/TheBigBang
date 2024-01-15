package com.homebrewCult.TheBigBang.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class ThreatenLayerModel extends Model {

	private final ModelRenderer tail;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftLowerArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightLowerArm;
	private final ModelRenderer leftEye;
	private final ModelRenderer rightEye;

	public ThreatenLayerModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 64;
		textureHeight = 64;
		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.0F, 7.0F);
		tail.setTextureOffset(0, 38).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 16.0F, 10.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -14.0F, 0.0F);
		tail.addChild(body);
		body.setTextureOffset(0, 0).addBox(-8.0F, -24.0F, -8.0F, 16.0F, 24.0F, 14.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(3.0F, -9.0F, -1.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, 0.0F, 0.0F, 0.7854F);


		leftLowerArm = new ModelRenderer(this);
		leftLowerArm.setRotationPoint(5.5F, 0.5F, -0.5F);
		leftArm.addChild(leftLowerArm);
		setRotationAngle(leftLowerArm, 0.0F, 0.0F, -1.5708F);
		leftLowerArm.setTextureOffset(44, 38).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-3.0F, -9.0F, -1.0F);
		body.addChild(rightArm);


		rightLowerArm = new ModelRenderer(this);
		rightLowerArm.setRotationPoint(-5.5F, 0.5F, -0.5F);
		rightArm.addChild(rightLowerArm);
		setRotationAngle(rightLowerArm, 0.0F, 0.0F, 1.5708F);
		rightLowerArm.setTextureOffset(44, 38).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		leftEye = new ModelRenderer(this);
		leftEye.setRotationPoint(4.0F, -18.0F, -8.1F);
		body.addChild(leftEye);
		leftEye.setTextureOffset(0, 0).addBox(-3.5F, -2.0F, 0.0F, 7.0F, 4.0F, 0.0F, 0.0F, false);

		rightEye = new ModelRenderer(this);
		rightEye.setRotationPoint(-4.0F, -18.0F, -8.1F);
		body.addChild(rightEye);
		rightEye.setTextureOffset(0, 4).addBox(-3.5F, -2.0F, 0.0F, 7.0F, 4.0F, 0.0F, 0.0F, false);
	}
	
	public void render(float time) {
		float p = (float)Math.PI/2;
		setRotationAngle(tail, -1 + MathHelper.sin(MathHelper.clamp(time/10F, 0, p)) * 0.75f, 0, 0);
		setRotationAngle(body, MathHelper.sin(MathHelper.clamp(time/15F, 0, p)) * 0.75f, 0, 0);
		setRotationAngle(leftArm, 0, 0, 2 - MathHelper.sin(MathHelper.clamp(time/10, 0, p)) * 2.8f);
		setRotationAngle(rightArm, 0, 0, -leftArm.rotateAngleZ);
		//tail.render(1);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		tail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
