package com.homebrewCult.TheBigBang.entities.model;
import com.homebrewCult.TheBigBang.entities.mob.RibbonPigEntity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class RibbonPigModel<T extends Entity> extends EntityModel<T> {

	private final ModelRenderer body;
	private final ModelRenderer ribbon;
	private final ModelRenderer head;
	private final ModelRenderer leg0;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;

	private float oscillationTimer = 0f;
	private float oscillationSpeed = .2f;
	private float ribbonMaxRot = .3f;
	
	public RibbonPigModel() {
		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 11.0F, 2.0F);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.setTextureOffset(28, 8).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 16.0F, 8.0F, 0.0F, true);

		ribbon = new ModelRenderer(this);
		ribbon.setRotationPoint(0.0F, -2.5F, 1.0F);
		body.addChild(ribbon);
		ribbon.setTextureOffset(32, 4).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 12.0F, -6.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
		head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, 0.0F, true);

		leg0 = new ModelRenderer(this);
		leg0.setRotationPoint(3.0F, 18.0F, 7.0F);
		leg0.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-3.0F, 18.0F, 7.0F);
		leg1.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(3.0F, 18.0F, -5.0F);
		leg2.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(-3.0F, 18.0F, -5.0F);
		leg3.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		oscillationTimer = (ageInTicks) * oscillationSpeed;
		RibbonPigEntity e = (RibbonPigEntity) entityIn;
		if (e.getSaddled()) {
			this.ribbon.rotateAngleX = (float)Math.PI;
		} else {
			this.ribbon.rotateAngleX = -0.5f + ((float)Math.cos(oscillationTimer) * ribbonMaxRot);
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg0.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}