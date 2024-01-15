package com.homebrewCult.TheBigBang.entities.model;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class SmallSnailModel<T extends Entity> extends EntityModel<T> {
	
	private float oscillationTimer = 0f;

	private final ModelRenderer Body_Bone;
	private final ModelRenderer Eye1_Bone;
	private final ModelRenderer Eye2_Bone;
	private final ModelRenderer Tail_Bone;
	private final ModelRenderer EyeStem1_Bone;
	private final ModelRenderer EyeStem2_Bone;
	private final ModelRenderer bb_main;

	public SmallSnailModel() {
		Body_Bone = new ModelRenderer(this);
		Body_Bone.setRotationPoint(0.0F, 23.0F, 0.0F);
		Body_Bone.setTextureOffset(0, 16).addBox(-2.5F, -1.0F, -4.0F, 5.0F, 2.0F, 8.0F, 0.0F, false);

		Eye1_Bone = new ModelRenderer(this);
		Eye1_Bone.setRotationPoint(-1.5F, -6.0F, -5.0F);
		Body_Bone.addChild(Eye1_Bone);
		Eye1_Bone.setTextureOffset(20, 0).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		Eye2_Bone = new ModelRenderer(this);
		Eye2_Bone.setRotationPoint(1.5F, -6.0F, -5.0F);
		Body_Bone.addChild(Eye2_Bone);
		Eye2_Bone.setTextureOffset(20, 0).addBox(-1.0F, -2.0F, -1.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		Tail_Bone = new ModelRenderer(this);
		Tail_Bone.setRotationPoint(0.0F, -0.5F, 4.0F);
		Body_Bone.addChild(Tail_Bone);
		setRotationAngle(Tail_Bone, 0.2618F, 0.0F, 0.0F);
		Tail_Bone.setTextureOffset(18, 18).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		EyeStem1_Bone = new ModelRenderer(this);
		EyeStem1_Bone.setRotationPoint(-1.5F, -0.5F, -3.0F);
		Body_Bone.addChild(EyeStem1_Bone);
		setRotationAngle(EyeStem1_Bone, 0.3491F, 0.0F, -0.0873F);
		EyeStem1_Bone.setTextureOffset(0, 0).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		EyeStem2_Bone = new ModelRenderer(this);
		EyeStem2_Bone.setRotationPoint(1.5F, -0.5F, -3.0F);
		Body_Bone.addChild(EyeStem2_Bone);
		setRotationAngle(EyeStem2_Bone, 0.3491F, 0.0F, 0.0873F);
		EyeStem2_Bone.setTextureOffset(4, 0).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 0).addBox(-3.0F, -10.0F, -2.0F, 6.0F, 8.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.Eye1_Bone.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Eye1_Bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.Eye2_Bone.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Eye2_Bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);

		if(this.isChild) {
			GlStateManager.translatef(0.0F, 0.6F, 0.0F);
			GlStateManager.scalef(0.6F, 0.6F, 0.6F);
		}

		if(!valueInRange(entityIn.getMotion().x, -0.0001D, 0.0001D) || !valueInRange(entityIn.getMotion().z, -0.0001D, 0.0001D)) {
			oscillationTimer = (ageInTicks) * 0.2f;
			this.Body_Bone.rotateAngleY = (float)(0.3f * Math.cos(oscillationTimer));
		}

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	private boolean valueInRange(double inValue, double min, double max)
	{
		if(inValue > min && inValue < max) {
			return true;
		} else {
			return false;
		}
	}
}