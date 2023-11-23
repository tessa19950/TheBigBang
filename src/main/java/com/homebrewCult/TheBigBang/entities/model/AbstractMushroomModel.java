package com.homebrewCult.TheBigBang.entities.model;
import com.homebrewCult.TheBigBang.entities.mob.AbstractMushroomEntity;
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
public class AbstractMushroomModel<T extends Entity> extends EntityModel<T> {
	
	private float oscillationTimer = 0f;

	private final ModelRenderer BodyBottom_Bone;
	private final ModelRenderer BodyMiddle_Bone;
	private final ModelRenderer BodyTop_Bone;
	private final ModelRenderer Head_Bone;
	private final ModelRenderer Cap_Bone;
	private final ModelRenderer Charm_Bone;

	public AbstractMushroomModel() {
		super();
		textureWidth = 128;
		textureHeight = 128;

		BodyBottom_Bone = new ModelRenderer(this);
		BodyBottom_Bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		BodyBottom_Bone.setTextureOffset(0, 60).addBox(-6.0F, -2.0F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);

		BodyMiddle_Bone = new ModelRenderer(this);
		BodyMiddle_Bone.setRotationPoint(0.0F, -3.0F, 0.0F);
		BodyBottom_Bone.addChild(BodyMiddle_Bone);
		BodyMiddle_Bone.setTextureOffset(0, 36).addBox(-8.0F, -6.0F, -8.0F, 16.0F, 8.0F, 16.0F, 0.0F, false);

		BodyTop_Bone = new ModelRenderer(this);
		BodyTop_Bone.setRotationPoint(0.0F, -7.0F, 0.5F);
		BodyMiddle_Bone.addChild(BodyTop_Bone);
		setRotationAngle(BodyTop_Bone, -0.0873F, 0.0F, 0.0F);
		BodyTop_Bone.setTextureOffset(3, 19).addBox(-7.0F, -1.0F, -7.0F, 14.0F, 3.0F, 14.0F, 0.0F, false);

		Head_Bone = new ModelRenderer(this);
		Head_Bone.setRotationPoint(0.0F, -2.0F, 1.0F);
		BodyTop_Bone.addChild(Head_Bone);
		Head_Bone.setTextureOffset(0, 0).addBox(-6.0F, -5.0F, -6.5F, 12.0F, 7.0F, 12.0F, 0.0F, false);

		Cap_Bone = new ModelRenderer(this);
		Cap_Bone.setRotationPoint(0.0F, -4.0F, -0.5F);
		Head_Bone.addChild(Cap_Bone);
		setRotationAngle(Cap_Bone, -0.2618F, 0.0F, 0.0F);
		Cap_Bone.setTextureOffset(56, 51).addBox(-9.0F, 1.0F, -9.0F, 18.0F, 1.0F, 18.0F, 0.0F, false);
		Cap_Bone.setTextureOffset(48, 0).addBox(-10.0F, -2.0F, -10.0F, 20.0F, 4.0F, 20.0F, 0.0F, false);
		Cap_Bone.setTextureOffset(72, 24).addBox(-7.0F, -3.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		Cap_Bone.setTextureOffset(88, 39).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 2.0F, 10.0F, 0.0F, false);
		Cap_Bone.setTextureOffset(108, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		Charm_Bone = new ModelRenderer(this);
		Charm_Bone.setRotationPoint(0.0F, 1.0F, -10.0F);
		Cap_Bone.addChild(Charm_Bone);
		setRotationAngle(Charm_Bone, -0.1745F, 0.0F, 0.0F);
		Charm_Bone.setTextureOffset(108, 5).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);		
		
		AbstractMushroomEntity mushroomIn = (AbstractMushroomEntity) entityIn;
		if(mushroomIn.isMushmom()) {
			double growth = 1D;
	        GlStateManager.translatef(0.0F, (float) growth * -1.6F, 0.0F);
			GlStateManager.scalef((float) (1 + growth), (float) (1 + growth), (float) (1 + growth));
		}
		if(this.isChild) {
	        GlStateManager.translatef(0.0F, 0.6F, 0.0F);
			GlStateManager.scalef(0.6F, 0.6F, 0.6F);
		}
		
		if(valueInRange(entityIn.getMotion().x, -0.0001D, 0.0001D) && valueInRange(entityIn.getMotion().z, -0.0001D, 0.0001D)) {
			oscillationTimer = (entityIn.ticksExisted + partialTick) * 0.1f;
			//Body Bounce
			this.BodyMiddle_Bone.rotationPointY = -0.05f + (float)Math.abs(0.1f * Math.cos(oscillationTimer));
			this.Head_Bone.rotationPointY = -0.05f + (float)Math.abs(0.05f * Math.cos(oscillationTimer));
			
			//Reset other Bones
			this.BodyBottom_Bone.rotationPointY = 0f;
			this.BodyBottom_Bone.rotationPointZ = 0f;
			this.BodyBottom_Bone.rotateAngleX = 0f;
			this.BodyTop_Bone.rotationPointZ = 0f;
			this.BodyTop_Bone.rotateAngleX = 0f;
			this.Head_Bone.rotationPointZ = 0f;
			this.Head_Bone.rotateAngleX = 0f;
		} else {
			oscillationTimer = (entityIn.ticksExisted + partialTick) * 0.4f;
			//Body Bounce
			this.BodyBottom_Bone.rotationPointY = 0f + (float)(Math.min(0, 0.2f * Math.cos(oscillationTimer)));
			this.BodyMiddle_Bone.rotationPointY = 0f + (float)(0.05f * Math.cos(oscillationTimer));
			this.Head_Bone.rotationPointY = 0.03f + (float)(0.05f * Math.cos(oscillationTimer));
			
			//Body, Body Top & Head, Back n Forth Motion 
			this.BodyTop_Bone.rotationPointZ = -0.03f + (float)(0.05f * Math.cos(oscillationTimer));
			this.Head_Bone.rotationPointZ = -0.03f + (float)(0.05f * Math.cos(oscillationTimer));
			//Body, Body Top & Head, Rotating Motion
			this.BodyBottom_Bone.rotateAngleX = 0f + (float)(0.1f * Math.cos(oscillationTimer));
			this.BodyTop_Bone.rotateAngleX = 0f + (float)(0.1f * Math.cos(oscillationTimer));
			this.Head_Bone.rotateAngleX = 0f + (float)(0.1f * Math.cos(oscillationTimer));
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		BodyBottom_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
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