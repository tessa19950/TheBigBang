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
public class AbstractSnailModel<T extends Entity> extends EntityModel<T> {

	private final ModelRenderer Body_Bone;
	private final ModelRenderer EyeStem2_Bone;
	private final ModelRenderer EyeStem1_Bone;
	private final ModelRenderer Tail_Bone;
	private final ModelRenderer Eye2_Bone;
	private final ModelRenderer Eye1_Bone;
	private final ModelRenderer Shell_Bone;

	public AbstractSnailModel() {
		super();
		textureWidth = 64;
		textureHeight = 64;

		Body_Bone = new ModelRenderer(this);
		Body_Bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body_Bone.setTextureOffset(10, 17).addBox(-2.0F, -9.0F, -4.5F, 4.0F, 3.0F, 3.0F, 0.0F, false);
		Body_Bone.setTextureOffset(1, 0).addBox(-2.5F, -6.0F, -5.0F, 5.0F, 6.0F, 11.0F, 0.0F, false);

		EyeStem2_Bone = new ModelRenderer(this);
		EyeStem2_Bone.setRotationPoint(1.5F, -8.5F, -4.0F);
		Body_Bone.addChild(EyeStem2_Bone);
		setRotationAngle(EyeStem2_Bone, 0.0F, 0.0F, 0.2618F);
		EyeStem2_Bone.setTextureOffset(0, 6).addBox(-0.5F, -2.0F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		EyeStem1_Bone = new ModelRenderer(this);
		EyeStem1_Bone.setRotationPoint(-1.5F, -8.5F, -4.0F);
		Body_Bone.addChild(EyeStem1_Bone);
		setRotationAngle(EyeStem1_Bone, 0.0F, 0.0F, -0.2618F);
		EyeStem1_Bone.setTextureOffset(0, 6).addBox(-0.5F, -2.0F, -0.4F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		Tail_Bone = new ModelRenderer(this);
		Tail_Bone.setRotationPoint(0.0F, -1.5F, 6.0F);
		Body_Bone.addChild(Tail_Bone);
		setRotationAngle(Tail_Bone, 0.2618F, 0.0F, 0.0F);
		Tail_Bone.setTextureOffset(9, 23).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		Eye2_Bone = new ModelRenderer(this);
		Eye2_Bone.setRotationPoint(2.0F, -11.5F, -4.0F);
		Body_Bone.addChild(Eye2_Bone);
		Eye2_Bone.setTextureOffset(0, 0).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		Eye1_Bone = new ModelRenderer(this);
		Eye1_Bone.setRotationPoint(-2.0F, -11.0F, -4.0F);
		Body_Bone.addChild(Eye1_Bone);
		Eye1_Bone.setTextureOffset(0, 0).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		Shell_Bone = new ModelRenderer(this);
		Shell_Bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		Shell_Bone.setTextureOffset(25, 7).addBox(-3.0F, -12.0F, -2.0F, 6.0F, 10.0F, 10.0F, 0.0F, false);
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);		
		
		if(this.isChild) {
	        GlStateManager.translatef(0.0F, 0.6F, 0.0F);
			GlStateManager.scalef(0.6F, 0.6F, 0.6F);
		}
		
		if(!valueInRange(entityIn.getMotion().x, -0.0001D, 0.0001D) || !valueInRange(entityIn.getMotion().z, -0.0001D, 0.0001D)) {
			float oscillationTimer = (entityIn.ticksExisted + partialTick) * 0.3f;
			this.Body_Bone.rotationPointZ = -0.1f + (float)(0.15f * Math.cos(oscillationTimer));
		}
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.Eye1_Bone.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Eye1_Bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.Eye2_Bone.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Eye2_Bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Shell_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
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