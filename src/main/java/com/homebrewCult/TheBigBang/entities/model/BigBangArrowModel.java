package com.homebrewCult.TheBigBang.entities.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BigBangArrowModel extends EntityModel<Entity> {

	private final ModelRenderer bb_main;
	private final ModelRenderer back_r1;
	private final ModelRenderer plane1_r1;

	public BigBangArrowModel() {
		super();
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		back_r1 = new ModelRenderer(this);
		back_r1.setRotationPoint(0.0F, 0.0F, 8.0F);
		bb_main.addChild(back_r1);
		setRotationAngle(back_r1, 0.0F, 0.0F, 0.7854F);
		back_r1.setTextureOffset(0, 0).addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 0.0F, 0.0F, false);
		back_r1.setTextureOffset(0, 0).addBox(0.0F, -3.5F, -23.0F, 0.0F, 7.0F, 32.0F, 0.0F, false);

		plane1_r1 = new ModelRenderer(this);
		plane1_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(plane1_r1);
		setRotationAngle(plane1_r1, 0.0F, 0.0F, -0.7854F);
		plane1_r1.setTextureOffset(0, 0).addBox(0.0F, -3.5F, -15.0F, 0.0F, 7.0F, 32.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
