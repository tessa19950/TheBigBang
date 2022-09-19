package com.homebrewCult.TheBigBang.entities.model; 

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.AbstractGolemEntity;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

@OnlyIn(Dist.CLIENT)
public class AbstractGolemModel <T extends AbstractGolemEntity> extends QuadrupedModel<T> {
	
	private float oscillationTimer = 0f;
	private float oscillationSpeed = .05f;
	
	private final RendererModel LeftLowerLeg_Bone;
	private final RendererModel LeftUpperLeg_Bone;
	private final RendererModel RightLowerLeg_Bone;
	private final RendererModel RightUpperLeg_Bone;
	private final RendererModel Hip_Bone;
	private final RendererModel Torso_Bone;
	private final RendererModel Head_Bone;
	private final RendererModel Flower1_Bone;
	private final RendererModel Flower1_Bone2;
	private final RendererModel LeftUpperArm_Bone;
	private final RendererModel LeftLowerArm_Bone;
	private final RendererModel RightUpperArm_Bone;
	private final RendererModel RightLowerArm_Bone;

	public AbstractGolemModel() {
		super(6, 0f);
		
		textureWidth = 128;
		textureHeight = 128;

		LeftLowerLeg_Bone = new RendererModel(this);
		LeftLowerLeg_Bone.setRotationPoint(8.0F, 11.5F, -3.0F);
		LeftLowerLeg_Bone.cubeList.add(new ModelBox(LeftLowerLeg_Bone, 92, 104, -4.0F, 2.5F, -5.0F, 8, 10, 10, 0.0F, false));
		LeftLowerLeg_Bone.cubeList.add(new ModelBox(LeftLowerLeg_Bone, 92, 104, -4.0F, 2.5F, -5.0F, 8, 10, 10, 0.0F, false));
		LeftLowerLeg_Bone.cubeList.add(new ModelBox(LeftLowerLeg_Bone, 92, 104, -4.0F, 2.5F, -5.0F, 8, 10, 10, 0.0F, false));
		LeftLowerLeg_Bone.cubeList.add(new ModelBox(LeftLowerLeg_Bone, 115, 97, -1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F, false));

		LeftUpperLeg_Bone = new RendererModel(this);
		LeftUpperLeg_Bone.setRotationPoint(10.0F, 2.0F, 0.0F);
		setRotationAngle(LeftUpperLeg_Bone, -0.3491F, 0.0F, 0.0F);
		LeftUpperLeg_Bone.cubeList.add(new ModelBox(LeftUpperLeg_Bone, 19, 114, -6.0F, 4.0F, -5.0F, 8, 4, 10, 0.0F, false));
		LeftUpperLeg_Bone.cubeList.add(new ModelBox(LeftUpperLeg_Bone, 0, 105, -2.0F, -4.0F, -5.0F, 4, 8, 10, 0.0F, false));

		RightLowerLeg_Bone = new RendererModel(this);
		RightLowerLeg_Bone.setRotationPoint(-8.0F, 11.5F, -3.0F);
		RightLowerLeg_Bone.cubeList.add(new ModelBox(RightLowerLeg_Bone, 0, 30, -4.0F, 2.5F, -5.0F, 8, 10, 10, 0.0F, false));
		RightLowerLeg_Bone.cubeList.add(new ModelBox(RightLowerLeg_Bone, 102, 97, -1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F, false));

		RightUpperLeg_Bone = new RendererModel(this);
		RightUpperLeg_Bone.setRotationPoint(-10.0F, 2.0F, 0.0F);
		setRotationAngle(RightUpperLeg_Bone, -0.3491F, 0.0F, 0.0F);
		RightUpperLeg_Bone.cubeList.add(new ModelBox(RightUpperLeg_Bone, 63, 115, -2.0F, 5.0F, -5.0F, 8, 3, 10, 0.0F, false));
		RightUpperLeg_Bone.cubeList.add(new ModelBox(RightUpperLeg_Bone, 46, 104, -2.0F, -4.0F, -5.0F, 3, 9, 10, 0.0F, false));

		Hip_Bone = new RendererModel(this);
		Hip_Bone.setRotationPoint(0.0F, 4.5F, 0.0F);
		setRotationAngle(Hip_Bone, 0.1745F, 0.0F, 0.0F);
		Hip_Bone.cubeList.add(new ModelBox(Hip_Bone, 80, 65, -7.0F, -5.5F, -5.0F, 14, 5, 10, 0.0F, false));

		Torso_Bone = new RendererModel(this);
		Torso_Bone.setRotationPoint(0.0F, -5.0F, 0.0F);
		setRotationAngle(Torso_Bone, 0.0F, 0.0F, 0.0175F);
		Hip_Bone.addChild(Torso_Bone);
		Torso_Bone.cubeList.add(new ModelBox(Torso_Bone, 107, 81, -2.5F, -6.5F, -2.5F, 5, 5, 5, 0.0F, false));
		Torso_Bone.cubeList.add(new ModelBox(Torso_Bone, 0, 0, -12.0F, -18.5F, -6.0F, 24, 11, 12, 0.0F, false));

		Head_Bone = new RendererModel(this);
		Head_Bone.setRotationPoint(0.0F, -20.5F, 0.0F);
		Torso_Bone.addChild(Head_Bone);
		Head_Bone.cubeList.add(new ModelBox(Head_Bone, 73, 0, -6.0F, -2.0F, -5.0F, 12, 4, 10, 0.0F, false));
		Head_Bone.cubeList.add(new ModelBox(Head_Bone, 73, 15, -5.5F, -1.0F, -4.5F, 11, 3, 9, 0.0F, false));
		Head_Bone.cubeList.add(new ModelBox(Head_Bone, 40, 27, -6.0F, -5.0F, 0.0F, 12, 3, 0, 0.0F, false));

		Flower1_Bone = new RendererModel(this);
		Flower1_Bone.setRotationPoint(-3.0F, -2.0F, 0.0F);
		setRotationAngle(Flower1_Bone, 0.0F, -0.7854F, 0.0F);
		Head_Bone.addChild(Flower1_Bone);
		Flower1_Bone.cubeList.add(new ModelBox(Flower1_Bone, 64, 24, -2.0F, -6.0F, 0.0F, 4, 6, 0, 0.0F, false));

		Flower1_Bone2 = new RendererModel(this);
		Flower1_Bone2.setRotationPoint(-3.0F, -2.0F, 0.0F);
		setRotationAngle(Flower1_Bone2, 0.0F, 0.7854F, 0.0F);
		Head_Bone.addChild(Flower1_Bone2);
		Flower1_Bone2.cubeList.add(new ModelBox(Flower1_Bone2, 64, 24, -2.0F, -6.0F, 0.0F, 4, 6, 0, 0.0F, false));

		LeftUpperArm_Bone = new RendererModel(this);
		LeftUpperArm_Bone.setRotationPoint(13.0F, -11.5F, 1.0F);
		setRotationAngle(LeftUpperArm_Bone, -0.0873F, 0.0F, 0.0F);
		Torso_Bone.addChild(LeftUpperArm_Bone);
		LeftUpperArm_Bone.cubeList.add(new ModelBox(LeftUpperArm_Bone, 53, 35, 0.0F, -13.0F, -7.0F, 14, 17, 12, 0.0F, false));

		LeftLowerArm_Bone = new RendererModel(this);
		LeftLowerArm_Bone.setRotationPoint(4.0F, 7.5F, -1.0F);
		setRotationAngle(LeftLowerArm_Bone, -0.1745F, 0.0F, -0.0873F);
		LeftUpperArm_Bone.addChild(LeftLowerArm_Bone);
		LeftLowerArm_Bone.cubeList.add(new ModelBox(LeftLowerArm_Bone, 68, 65, 0.0F, -1.5F, -1.0F, 4, 4, 3, 0.0F, false));
		LeftLowerArm_Bone.cubeList.add(new ModelBox(LeftLowerArm_Bone, 62, 81, -3.0F, 3.5F, -4.0F, 12, 24, 8, 0.0F, false));

		RightUpperArm_Bone = new RendererModel(this);
		RightUpperArm_Bone.setRotationPoint(-27.0F, -11.5F, 1.0F);
		setRotationAngle(RightUpperArm_Bone, -0.0873F, 0.0F, 0.0F);
		Torso_Bone.addChild(RightUpperArm_Bone);
		RightUpperArm_Bone.cubeList.add(new ModelBox(RightUpperArm_Bone, 2, 51, 1.0F, -13.0F, -7.0F, 13, 17, 12, 0.0F, false));

		RightLowerArm_Bone = new RendererModel(this);
		RightLowerArm_Bone.setRotationPoint(6.0F, 7.5F, -1.0F);
		setRotationAngle(RightLowerArm_Bone, -0.1745F, 0.0F, 0.0873F);
		RightUpperArm_Bone.addChild(RightLowerArm_Bone);
		RightLowerArm_Bone.cubeList.add(new ModelBox(RightLowerArm_Bone, 53, 65, 0.0F, -1.5F, -1.0F, 4, 4, 3, 0.0F, false));
		RightLowerArm_Bone.cubeList.add(new ModelBox(RightLowerArm_Bone, 18, 81, -3.0F, 3.5F, -4.0F, 10, 24, 8, 0.0F, false));
	}

	@Override
	public void render(AbstractGolemEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		LeftUpperLeg_Bone.render(f5);
		RightUpperLeg_Bone.render(f5);
		LeftLowerLeg_Bone.render(f5);
		RightLowerLeg_Bone.render(f5);
		Hip_Bone.render(f5);
	}
	
	@Override
	public void setLivingAnimations(T golem, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(golem, limbSwing, limbSwingAmount, partialTick);
		oscillationTimer = (golem.ticksExisted + partialTick) * oscillationSpeed;
	}
	
	@Override
	public void setRotationAngles(T golem, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		super.setRotationAngles(golem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

		//Legwork
		float leftLegSwing = MathHelper.cos(limbSwing * 0.6f);
		float rightLegSwing = MathHelper.cos(limbSwing * 0.6f + (float)Math.PI);

		int t = golem.getShockwaveTick();
		if(golem.isTempted() && limbSwingAmount < 0.01f) { //Golem is tempted and will crouch down
        	//Move Hip Offset into Squat Position
	        this.Hip_Bone.offsetY = 0.3f;
	        this.Hip_Bone.offsetZ = 0.3f;
	        //Move Leg Rotations into Squat Positions
	        this.LeftUpperLeg_Bone.rotateAngleX = -1f;
	        this.RightUpperLeg_Bone.rotateAngleX = 1f;
	        //Move Leg Offsets into Squat Positions
	        this.LeftUpperLeg_Bone.offsetY = 0.3f;
	        this.RightUpperLeg_Bone.offsetY = 0.3f;
	        this.LeftUpperLeg_Bone.offsetZ = 0.2f;
	        this.RightUpperLeg_Bone.offsetZ = 0.2f;
		} else if(t != -1 && ageInTicks - t <= AbstractGolemEntity.SHOCKWAVE_DURATION) { //Golem doing smash attack
			float attackTime = ageInTicks - golem.getShockwaveTick();
			float swingPct = MathHelper.clamp(attackTime / 40F, 0, 1);
			if(swingPct < 0.85F) {
				float upSwing = (float) -Math.sin(swingPct * 0.85F * Math.PI);
				this.LeftUpperArm_Bone.rotateAngleX = upSwing * 3F;
				this.RightUpperArm_Bone.rotateAngleX = upSwing * 3F;
				this.Hip_Bone.rotateAngleX = 0.4F + upSwing;
			} else {
				float downSwing = (swingPct - 0.85F) * 10F;
				this.LeftUpperArm_Bone.rotateAngleX = MathHelper.lerp(downSwing, -3, -2.3F);
				this.RightUpperArm_Bone.rotateAngleX = MathHelper.lerp(downSwing, -3, -2.3F);
				this.LeftLowerArm_Bone.rotateAngleX = MathHelper.lerp(downSwing, 1, -0.1F);
				this.RightLowerArm_Bone.rotateAngleX = MathHelper.lerp(downSwing, 1, -0.1F);
				this.Hip_Bone.rotateAngleX = 0.4F + MathHelper.lerp(downSwing, -1, 0.5F);
			}
		} else { //Golem is moving normally
			//Move Hip Offset into Original Position
			this.Hip_Bone.rotateAngleX = 0;
	        this.Hip_Bone.offsetY = -0.1f;
	        this.Hip_Bone.offsetZ = 0;
	        //Animate Leg Rotations for Walking
	        this.LeftUpperLeg_Bone.rotateAngleX = MathHelper.clamp(-0.5f + leftLegSwing * 3F * limbSwingAmount, -1.4f, 1.4f);
	        this.RightUpperLeg_Bone.rotateAngleX = MathHelper.clamp(-0.5f + rightLegSwing * 3F * limbSwingAmount,  -1.4f, 1.4f);
	        //Move Leg Offsets into Original Positions
	        this.LeftUpperLeg_Bone.offsetY = 0f;
	        this.RightUpperLeg_Bone.offsetY = 0f;
	        this.LeftUpperLeg_Bone.offsetZ = 0f;
	        this.RightUpperLeg_Bone.offsetZ = 0f;
		}
        
        //Footwork
        float leftFootRaise = MathHelper.cos((limbSwing * 0.6f) + (float)Math.PI - 1.8f);
        float rightFootRaise = MathHelper.cos((limbSwing * 0.6f) - 1.8f);
        this.LeftLowerLeg_Bone.offsetZ = MathHelper.clamp(0 + leftLegSwing * 2f * limbSwingAmount, -0.6f, 0.6f);
		this.RightLowerLeg_Bone.offsetZ = MathHelper.clamp(0 + rightLegSwing * 2f * limbSwingAmount, -0.6f, 0.6f);
        this.LeftLowerLeg_Bone.offsetY = MathHelper.clamp(Math.min(leftFootRaise, 0) * 0.6f * limbSwingAmount, -0.6f, 0.6f);
        this.RightLowerLeg_Bone.offsetY = MathHelper.clamp(Math.min(rightFootRaise, 0) * 0.6f * limbSwingAmount, -0.6f, 0.6f);
        
        //Looking at Player
		this.Torso_Bone.rotateAngleX = headPitch * (float)Math.PI / 180F * 0.6f;
	    this.Head_Bone.rotateAngleY = netHeadYaw * 0.8f * ((float)Math.PI / 180F);
		if(ageInTicks - t > AbstractGolemEntity.SHOCKWAVE_DURATION) {
			this.LeftUpperArm_Bone.rotateAngleX = (headPitch * -1) * (float) Math.PI / 180 * 0.6f;
			this.RightUpperArm_Bone.rotateAngleX = (headPitch * -1) * (float) Math.PI / 180 * 0.6f;
			this.LeftLowerArm_Bone.rotateAngleX = (headPitch * -1) * (float) Math.PI / 180 * 0.6f;
			this.RightLowerArm_Bone.rotateAngleX = (headPitch * -1) * (float) Math.PI / 180 * 0.6f;
		}

		//Breathing
		this.Torso_Bone.offsetY = (float)Math.cos(oscillationTimer) * 0.05f;
		this.LeftUpperArm_Bone.offsetY = (float)Math.cos(oscillationTimer) * 0.05f;
		this.RightUpperArm_Bone.offsetY = (float)Math.cos(oscillationTimer) * 0.05f;
		
	}
	
	public void setRotationAngle(RendererModel renderer, float x, float y, float z) {
		renderer.rotateAngleX = x;
		renderer.rotateAngleY = y;
		renderer.rotateAngleZ = z;
	}
}