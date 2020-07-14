package com.homebrewCult.TheBigBang.entities.model;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class AbstractSnailModel<T extends Entity> extends QuadrupedModel<T> {
	
	private float oscillationTimer = 0f;
	
	private final RendererModel Body_Bone;
	private final RendererModel EyeStem2_Bone;
	private final RendererModel EyeStem1_Bone;
	private final RendererModel Tail_Bone;
	private final RendererModel Eye2_Bone;
	private final RendererModel Eye1_Bone;
	private final RendererModel Shell_Bone;

	public AbstractSnailModel() {
		super(6,0);
		
		textureWidth = 64;
		textureHeight = 64;

		Body_Bone = new RendererModel(this);
		Body_Bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body_Bone.cubeList.add(new ModelBox(Body_Bone, 10, 17, -2.0F, -9.0F, -4.5F, 4, 3, 3, 0.0F, false));
		Body_Bone.cubeList.add(new ModelBox(Body_Bone, 1, 0, -2.5F, -6.0F, -5.0F, 5, 6, 11, 0.0F, false));

		EyeStem2_Bone = new RendererModel(this);
		EyeStem2_Bone.setRotationPoint(1.5F, -8.5F, -4.0F);
		setRotationAngle(EyeStem2_Bone, 0.0F, 0.0F, 0.2618F);
		Body_Bone.addChild(EyeStem2_Bone);
		EyeStem2_Bone.cubeList.add(new ModelBox(EyeStem2_Bone, 0, 6, -0.5F, -2.0F, -0.4F, 1, 2, 1, 0.0F, false));

		EyeStem1_Bone = new RendererModel(this);
		EyeStem1_Bone.setRotationPoint(-1.5F, -8.5F, -4.0F);
		setRotationAngle(EyeStem1_Bone, 0.0F, 0.0F, -0.2618F);
		Body_Bone.addChild(EyeStem1_Bone);
		EyeStem1_Bone.cubeList.add(new ModelBox(EyeStem1_Bone, 0, 6, -0.5F, -2.0F, -0.4F, 1, 2, 1, 0.0F, false));

		Tail_Bone = new RendererModel(this);
		Tail_Bone.setRotationPoint(0.0F, -1.5F, 6.0F);
		setRotationAngle(Tail_Bone, 0.2618F, 0.0F, 0.0F);
		Body_Bone.addChild(Tail_Bone);
		Tail_Bone.cubeList.add(new ModelBox(Tail_Bone, 9, 23, -2.0F, -1.0F, -1.0F, 4, 2, 4, 0.0F, false));

		Eye2_Bone = new RendererModel(this);
		Eye2_Bone.setRotationPoint(2.0F, -11.5F, -4.0F);
		Body_Bone.addChild(Eye2_Bone);
		Eye2_Bone.cubeList.add(new ModelBox(Eye2_Bone, 0, 0, -1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F, false));

		Eye1_Bone = new RendererModel(this);
		Eye1_Bone.setRotationPoint(-2.0F, -11.0F, -4.0F);
		Body_Bone.addChild(Eye1_Bone);
		Eye1_Bone.cubeList.add(new ModelBox(Eye1_Bone, 0, 0, -1.5F, -2.0F, -1.5F, 3, 3, 3, 0.0F, false));

		Shell_Bone = new RendererModel(this);
		Shell_Bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		Shell_Bone.setTextureOffset(25, 7);
		Shell_Bone.addBox(-3.0F, -12.0F, -2.0F, 6, 10, 10, 1);
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);		
		
		if(this.isChild) {
	        GlStateManager.translatef(0.0F, 0.6F, 0.0F);
			GlStateManager.scalef(0.6F, 0.6F, 0.6F);
		}
		
		if(!valueInRange(entityIn.getMotion().x, -0.0001D, 0.0001D) || !valueInRange(entityIn.getMotion().z, -0.0001D, 0.0001D)) {
			this.oscillationTimer = (entityIn.ticksExisted + partialTick) * 0.3f;
			this.Body_Bone.offsetZ = -0.1f + (float)(0.15f * Math.cos(oscillationTimer));
		}
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		this.Eye1_Bone.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Eye1_Bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		      
		this.Eye2_Bone.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Eye2_Bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.Body_Bone.render(f5);
		this.Shell_Bone.render(f5);
	}
	
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
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