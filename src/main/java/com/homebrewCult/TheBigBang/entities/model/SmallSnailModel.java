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
public class SmallSnailModel<T extends Entity> extends QuadrupedModel<T> {
	
	private float oscillationTimer = 0f;
	
	private final RendererModel bb_main;
	private final RendererModel Body_Bone;
	private final RendererModel Eye1_Bone;
	private final RendererModel Eye2_Bone;
	private final RendererModel Tail_Bone;
	private final RendererModel EyeStem1_Bone;
	private final RendererModel EyeStem2_Bone;

	public SmallSnailModel() {
		super(6,0);
		
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -3.0F, -10.0F, -2.0F, 6, 8, 8, 0.0F, false));

		Body_Bone = new RendererModel(this);
		Body_Bone.setRotationPoint(0.0F, 23.0F, 0.0F);
		Body_Bone.cubeList.add(new ModelBox(Body_Bone, 0, 16, -2.5F, -1.0F, -4.0F, 5, 2, 8, 0.0F, false));

		Eye1_Bone = new RendererModel(this);
		Eye1_Bone.setRotationPoint(-2.0F, -6.5F, -4.5F);
		Body_Bone.addChild(Eye1_Bone);
		Eye1_Bone.cubeList.add(new ModelBox(Eye1_Bone, 20, 0, -1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F, false));

		Eye2_Bone = new RendererModel(this);
		Eye2_Bone.setRotationPoint(2.0F, -6.5F, -4.5F);
		Body_Bone.addChild(Eye2_Bone);
		Eye2_Bone.cubeList.add(new ModelBox(Eye2_Bone, 20, 0, -1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F, false));

		Tail_Bone = new RendererModel(this);
		Tail_Bone.setRotationPoint(0.0F, -0.5F, 4.0F);
		setRotationAngle(Tail_Bone, 0.2618F, 0.0F, 0.0F);
		Body_Bone.addChild(Tail_Bone);
		Tail_Bone.cubeList.add(new ModelBox(Tail_Bone, 18, 18, -2.0F, -1.0F, -1.0F, 4, 2, 4, 0.0F, false));

		EyeStem1_Bone = new RendererModel(this);
		EyeStem1_Bone.setRotationPoint(-1.5F, -0.5F, -3.0F);
		setRotationAngle(EyeStem1_Bone, 0.3491F, 0.0F, -0.0873F);
		Body_Bone.addChild(EyeStem1_Bone);
		EyeStem1_Bone.cubeList.add(new ModelBox(EyeStem1_Bone, 0, 0, -0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F, false));

		EyeStem2_Bone = new RendererModel(this);
		EyeStem2_Bone.setRotationPoint(1.5F, -0.5F, -3.0F);
		setRotationAngle(EyeStem2_Bone, 0.3491F, 0.0F, 0.0873F);
		Body_Bone.addChild(EyeStem2_Bone);
		EyeStem2_Bone.cubeList.add(new ModelBox(EyeStem2_Bone, 4, 0, -0.5F, -5.0F, -0.5F, 1, 5, 1, 0.0F, false));
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);		
		
		if(this.isChild) {
	        GlStateManager.translatef(0.0F, 0.6F, 0.0F);
			GlStateManager.scalef(0.6F, 0.6F, 0.6F);
		}
		
		if(!valueInRange(entityIn.getMotion().x, -0.0001D, 0.0001D) || !valueInRange(entityIn.getMotion().z, -0.0001D, 0.0001D)) {
			oscillationTimer = (entityIn.ticksExisted + partialTick) * 0.2f;
			this.Body_Bone.rotateAngleY = (float)(0.3f * Math.cos(oscillationTimer));
		}
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch, float scaleFactor) {
		this.Eye1_Bone.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Eye1_Bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		      
		this.Eye2_Bone.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Eye2_Bone.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
		Body_Bone.render(f5);
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