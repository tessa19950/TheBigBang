package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.mob.AbstractJrYetiEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class AbstractJrYetiModel<T extends AbstractJrYetiEntity> extends EntityModel<T> {
	
	private float oscillationTimer = 0f;

	private final ModelRenderer Body_Bone;
	private final ModelRenderer TopFur_Bone;
	private final ModelRenderer SideFur_Bone2;
	private final ModelRenderer SideFur_Bone1;
	private final ModelRenderer FeetFur_Bone4;
	private final ModelRenderer FeetFur_Bone3;
	private final ModelRenderer FeetFur_Bone2;
	private final ModelRenderer FeetFur_Bone1;

	public AbstractJrYetiModel() {
		super();
		textureWidth = 64;
		textureHeight = 64;

		Body_Bone = new ModelRenderer(this);
		Body_Bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body_Bone.setTextureOffset(0, 0).addBox(-7.0F, -12.0F, -7.0F, 14.0F, 12.0F, 14.0F, 0.0F, false);
		Body_Bone.setTextureOffset(0, 29).addBox(-6.0F, -14.0F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);

		TopFur_Bone = new ModelRenderer(this);
		TopFur_Bone.setRotationPoint(0.0F, -14.0F, -1.0F);
		Body_Bone.addChild(TopFur_Bone);
		TopFur_Bone.setTextureOffset(42, 0).addBox(-3.0F, -3.0F, 1.0F, 6.0F, 3.0F, 0.0F, 0.0F, false);

		SideFur_Bone2 = new ModelRenderer(this);
		SideFur_Bone2.setRotationPoint(-7.0F, -8.0F, 0.0F);
		Body_Bone.addChild(SideFur_Bone2);
		setRotationAngle(SideFur_Bone2, -0.1745F, 1.5708F, 0.0F);
		SideFur_Bone2.setTextureOffset(42, 3).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, 0.0F, false);

		SideFur_Bone1 = new ModelRenderer(this);
		SideFur_Bone1.setRotationPoint(7.0F, -8.0F, 0.0F);
		Body_Bone.addChild(SideFur_Bone1);
		setRotationAngle(SideFur_Bone1, -0.1745F, -1.5708F, 0.0F);
		SideFur_Bone1.setTextureOffset(42, 3).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, 0.0F, false);

		FeetFur_Bone4 = new ModelRenderer(this);
		FeetFur_Bone4.setRotationPoint(7.0F, -3.0F, 0.0F);
		Body_Bone.addChild(FeetFur_Bone4);
		setRotationAngle(FeetFur_Bone4, -0.3491F, -1.5708F, 0.0F);
		FeetFur_Bone4.setTextureOffset(0, 26).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 3.0F, 0.0F, 0.0F, false);

		FeetFur_Bone3 = new ModelRenderer(this);
		FeetFur_Bone3.setRotationPoint(0.0F, -3.0F, 7.0F);
		Body_Bone.addChild(FeetFur_Bone3);
		setRotationAngle(FeetFur_Bone3, -0.3491F, 3.1416F, 0.0F);
		FeetFur_Bone3.setTextureOffset(0, 26).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 3.0F, 0.0F, 0.0F, false);

		FeetFur_Bone2 = new ModelRenderer(this);
		FeetFur_Bone2.setRotationPoint(-7.0F, -3.0F, 0.0F);
		Body_Bone.addChild(FeetFur_Bone2);
		setRotationAngle(FeetFur_Bone2, -0.3491F, 1.5708F, 0.0F);
		FeetFur_Bone2.setTextureOffset(0, 26).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 3.0F, 0.0F, 0.0F, false);

		FeetFur_Bone1 = new ModelRenderer(this);
		FeetFur_Bone1.setRotationPoint(0.0F, -3.0F, -7.0F);
		Body_Bone.addChild(FeetFur_Bone1);
		setRotationAngle(FeetFur_Bone1, -0.3491F, 0.0F, 0.0F);
		FeetFur_Bone1.setTextureOffset(0, 26).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 3.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void setLivingAnimations(T jrYeti, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(jrYeti, limbSwing, limbSwingAmount, partialTick);

		ModelRenderer[] furStrips = {this.FeetFur_Bone1, this.FeetFur_Bone2, this.FeetFur_Bone3, this.FeetFur_Bone4, this.SideFur_Bone1, this.SideFur_Bone2};
		if(jrYeti.isSitting()) {
			for (ModelRenderer fur : furStrips) {
				fur.rotateAngleX = (fur == this.SideFur_Bone1 || fur == this.SideFur_Bone2) ? -0.2f : -1.5F;
			}
			this.Body_Bone.rotationPointY = 0.15F;
		} else {
			if (valueInRange(jrYeti.getMotion().x, -0.0001D, 0.0001D) && valueInRange(jrYeti.getMotion().z, -0.0001D, 0.0001D)) {
				oscillationTimer = (jrYeti.ticksExisted + partialTick) * 0.05f;
				for (ModelRenderer fur : furStrips)
					fur.rotateAngleX = -0.2f - (float) Math.abs(0.3f * Math.cos(oscillationTimer));
				this.Body_Bone.rotationPointY = 0.05f - (float) Math.abs(0.05f * Math.cos(oscillationTimer));
			} else {
				oscillationTimer = (jrYeti.ticksExisted + partialTick) * 0.4f;
				for (ModelRenderer fur : furStrips)
					fur.rotateAngleX = -0.2f - (float) Math.abs(0.9f * Math.cos(oscillationTimer));
				this.Body_Bone.rotationPointY = 0.05f - (float) Math.abs(0.1f * Math.cos(oscillationTimer));
			}
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	private boolean valueInRange(double inValue, double min, double max) {
		return inValue > min && inValue < max;
	}
}