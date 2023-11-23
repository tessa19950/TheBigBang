package com.homebrewCult.TheBigBang.entities.model; 

import com.homebrewCult.TheBigBang.entities.mob.AbstractGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

@OnlyIn(Dist.CLIENT)
public class AbstractGolemModel <T extends AbstractGolemEntity> extends EntityModel<T> {
	
	private float oscillationTimer = 0f;
	private float oscillationSpeed = .05f;

	private final ModelRenderer LeftLowerLeg_Bone;
	private final ModelRenderer LeftUpperLeg_Bone;
	private final ModelRenderer RightLowerLeg_Bone;
	private final ModelRenderer RightUpperLeg_Bone;
	private final ModelRenderer Hip_Bone;
	private final ModelRenderer Torso_Bone;
	private final ModelRenderer Head_Bone;
	private final ModelRenderer Flower1_Bone;
	private final ModelRenderer Flower1_Bone2;
	private final ModelRenderer LeftUpperArm_Bone;
	private final ModelRenderer LeftLowerArm_Bone;
	private final ModelRenderer RightUpperArm_Bone;
	private final ModelRenderer RightLowerArm_Bone;

	public AbstractGolemModel() {
		super();
		textureWidth = 128;
		textureHeight = 128;

		LeftLowerLeg_Bone = new ModelRenderer(this);
		LeftLowerLeg_Bone.setRotationPoint(8.0F, 11.5F, -3.0F);
		LeftLowerLeg_Bone.setTextureOffset(92, 104).addBox(-4.0F, 2.5F, -5.0F, 8.0F, 10.0F, 10.0F, 0.0F, false);
		LeftLowerLeg_Bone.setTextureOffset(92, 104).addBox(-4.0F, 2.5F, -5.0F, 8.0F, 10.0F, 10.0F, 0.0F, false);
		LeftLowerLeg_Bone.setTextureOffset(92, 104).addBox(-4.0F, 2.5F, -5.0F, 8.0F, 10.0F, 10.0F, 0.0F, false);
		LeftLowerLeg_Bone.setTextureOffset(115, 97).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		LeftUpperLeg_Bone = new ModelRenderer(this);
		LeftUpperLeg_Bone.setRotationPoint(10.0F, 2.0F, 0.0F);
		setRotationAngle(LeftUpperLeg_Bone, -0.3491F, 0.0F, 0.0F);
		LeftUpperLeg_Bone.setTextureOffset(19, 114).addBox(-6.0F, 4.0F, -5.0F, 8.0F, 4.0F, 10.0F, 0.0F, false);
		LeftUpperLeg_Bone.setTextureOffset(0, 105).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 8.0F, 10.0F, 0.0F, false);

		RightLowerLeg_Bone = new ModelRenderer(this);
		RightLowerLeg_Bone.setRotationPoint(-8.0F, 11.5F, -3.0F);
		RightLowerLeg_Bone.setTextureOffset(0, 30).addBox(-4.0F, 2.5F, -5.0F, 8.0F, 10.0F, 10.0F, 0.0F, false);
		RightLowerLeg_Bone.setTextureOffset(102, 97).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		RightUpperLeg_Bone = new ModelRenderer(this);
		RightUpperLeg_Bone.setRotationPoint(-10.0F, 2.0F, 0.0F);
		setRotationAngle(RightUpperLeg_Bone, -0.3491F, 0.0F, 0.0F);
		RightUpperLeg_Bone.setTextureOffset(63, 115).addBox(-2.0F, 5.0F, -5.0F, 8.0F, 3.0F, 10.0F, 0.0F, false);
		RightUpperLeg_Bone.setTextureOffset(46, 104).addBox(-2.0F, -4.0F, -5.0F, 3.0F, 9.0F, 10.0F, 0.0F, false);

		Hip_Bone = new ModelRenderer(this);
		Hip_Bone.setRotationPoint(0.0F, 4.5F, 0.0F);
		setRotationAngle(Hip_Bone, 0.1745F, 0.0F, 0.0F);
		Hip_Bone.setTextureOffset(80, 65).addBox(-7.0F, -5.5F, -5.0F, 14.0F, 5.0F, 10.0F, 0.0F, false);

		Torso_Bone = new ModelRenderer(this);
		Torso_Bone.setRotationPoint(0.0F, -5.0F, 0.0F);
		Hip_Bone.addChild(Torso_Bone);
		setRotationAngle(Torso_Bone, 0.0F, 0.0F, 0.0175F);
		Torso_Bone.setTextureOffset(107, 81).addBox(-2.5F, -6.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
		Torso_Bone.setTextureOffset(0, 0).addBox(-12.0F, -18.5F, -6.0F, 24.0F, 11.0F, 12.0F, 0.0F, false);

		Head_Bone = new ModelRenderer(this);
		Head_Bone.setRotationPoint(0.0F, -20.5F, 0.0F);
		Torso_Bone.addChild(Head_Bone);
		Head_Bone.setTextureOffset(73, 0).addBox(-6.0F, -2.0F, -5.0F, 12.0F, 4.0F, 10.0F, 0.0F, false);
		Head_Bone.setTextureOffset(73, 15).addBox(-5.5F, -1.0F, -4.5F, 11.0F, 3.0F, 9.0F, 0.0F, false);
		Head_Bone.setTextureOffset(40, 27).addBox(-6.0F, -5.0F, 0.0F, 12.0F, 3.0F, 0.0F, 0.0F, false);

		Flower1_Bone = new ModelRenderer(this);
		Flower1_Bone.setRotationPoint(-3.0F, -2.0F, 0.0F);
		Head_Bone.addChild(Flower1_Bone);
		setRotationAngle(Flower1_Bone, 0.0F, -0.7854F, 0.0F);
		Flower1_Bone.setTextureOffset(64, 24).addBox(-2.0F, -6.0F, 0.0F, 4.0F, 6.0F, 0.0F, 0.0F, false);

		Flower1_Bone2 = new ModelRenderer(this);
		Flower1_Bone2.setRotationPoint(-3.0F, -2.0F, 0.0F);
		Head_Bone.addChild(Flower1_Bone2);
		setRotationAngle(Flower1_Bone2, 0.0F, 0.7854F, 0.0F);
		Flower1_Bone2.setTextureOffset(64, 24).addBox(-2.0F, -6.0F, 0.0F, 4.0F, 6.0F, 0.0F, 0.0F, false);

		LeftUpperArm_Bone = new ModelRenderer(this);
		LeftUpperArm_Bone.setRotationPoint(13.0F, -11.5F, 1.0F);
		Torso_Bone.addChild(LeftUpperArm_Bone);
		setRotationAngle(LeftUpperArm_Bone, -0.0873F, 0.0F, 0.0F);
		LeftUpperArm_Bone.setTextureOffset(53, 35).addBox(0.0F, -13.0F, -7.0F, 14.0F, 17.0F, 12.0F, 0.0F, false);

		LeftLowerArm_Bone = new ModelRenderer(this);
		LeftLowerArm_Bone.setRotationPoint(4.0F, 7.5F, -1.0F);
		LeftUpperArm_Bone.addChild(LeftLowerArm_Bone);
		setRotationAngle(LeftLowerArm_Bone, -0.1745F, 0.0F, -0.0873F);
		LeftLowerArm_Bone.setTextureOffset(68, 65).addBox(0.0F, -1.5F, -1.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
		LeftLowerArm_Bone.setTextureOffset(62, 81).addBox(-3.0F, 3.5F, -4.0F, 12.0F, 24.0F, 8.0F, 0.0F, false);

		RightUpperArm_Bone = new ModelRenderer(this);
		RightUpperArm_Bone.setRotationPoint(-27.0F, -11.5F, 1.0F);
		Torso_Bone.addChild(RightUpperArm_Bone);
		setRotationAngle(RightUpperArm_Bone, -0.0873F, 0.0F, 0.0F);
		RightUpperArm_Bone.setTextureOffset(2, 51).addBox(1.0F, -13.0F, -7.0F, 13.0F, 17.0F, 12.0F, 0.0F, false);

		RightLowerArm_Bone = new ModelRenderer(this);
		RightLowerArm_Bone.setRotationPoint(6.0F, 7.5F, -1.0F);
		RightUpperArm_Bone.addChild(RightLowerArm_Bone);
		setRotationAngle(RightLowerArm_Bone, -0.1745F, 0.0F, 0.0873F);
		RightLowerArm_Bone.setTextureOffset(53, 65).addBox(0.0F, -1.5F, -1.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
		RightLowerArm_Bone.setTextureOffset(18, 81).addBox(-3.0F, 3.5F, -4.0F, 10.0F, 24.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		LeftLowerLeg_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftUpperLeg_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		RightLowerLeg_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		RightUpperLeg_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Hip_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	@Override
	public void setLivingAnimations(T golem, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(golem, limbSwing, limbSwingAmount, partialTick);
		oscillationTimer = (golem.ticksExisted + partialTick) * oscillationSpeed;
	}

	@Override
	public void setRotationAngles(T golem, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//Legwork
		float leftLegSwing = MathHelper.cos(limbSwing * 0.6f);
		float rightLegSwing = MathHelper.cos(limbSwing * 0.6f + (float)Math.PI);

		int t = golem.getShockwaveTick();
		if(golem.isTempted() && limbSwingAmount < 0.01f) { //Golem is tempted and will crouch down
        	//Move Hip Offset into Squat Position
	        this.Hip_Bone.rotationPointY = 0.3f;
	        this.Hip_Bone.rotationPointZ = 0.3f;
	        //Move Leg Rotations into Squat Positions
	        this.LeftUpperLeg_Bone.rotateAngleX = -1f;
	        this.RightUpperLeg_Bone.rotateAngleX = 1f;
	        //Move Leg Offsets into Squat Positions
	        this.LeftUpperLeg_Bone.rotationPointY = 0.3f;
	        this.RightUpperLeg_Bone.rotationPointY = 0.3f;
	        this.LeftUpperLeg_Bone.rotationPointZ = 0.2f;
	        this.RightUpperLeg_Bone.rotationPointZ = 0.2f;
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
	        this.Hip_Bone.rotationPointY = -0.1f;
	        this.Hip_Bone.rotationPointZ = 0;
	        //Animate Leg Rotations for Walking
	        this.LeftUpperLeg_Bone.rotateAngleX = MathHelper.clamp(-0.5f + leftLegSwing * 3F * limbSwingAmount, -1.4f, 1.4f);
	        this.RightUpperLeg_Bone.rotateAngleX = MathHelper.clamp(-0.5f + rightLegSwing * 3F * limbSwingAmount,  -1.4f, 1.4f);
	        //Move Leg Offsets into Original Positions
	        this.LeftUpperLeg_Bone.rotationPointY = 0f;
	        this.RightUpperLeg_Bone.rotationPointY = 0f;
	        this.LeftUpperLeg_Bone.rotationPointZ = 0f;
	        this.RightUpperLeg_Bone.rotationPointZ = 0f;
		}
        
        //Footwork
        float leftFootRaise = MathHelper.cos((limbSwing * 0.6f) + (float)Math.PI - 1.8f);
        float rightFootRaise = MathHelper.cos((limbSwing * 0.6f) - 1.8f);
        this.LeftLowerLeg_Bone.rotationPointZ = MathHelper.clamp(0 + leftLegSwing * 2f * limbSwingAmount, -0.6f, 0.6f);
		this.RightLowerLeg_Bone.rotationPointZ = MathHelper.clamp(0 + rightLegSwing * 2f * limbSwingAmount, -0.6f, 0.6f);
        this.LeftLowerLeg_Bone.rotationPointY = MathHelper.clamp(Math.min(leftFootRaise, 0) * 0.6f * limbSwingAmount, -0.6f, 0.6f);
        this.RightLowerLeg_Bone.rotationPointY = MathHelper.clamp(Math.min(rightFootRaise, 0) * 0.6f * limbSwingAmount, -0.6f, 0.6f);
        
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
		this.Torso_Bone.rotationPointY = (float)Math.cos(oscillationTimer) * 0.05f;
		this.LeftUpperArm_Bone.rotationPointY = (float)Math.cos(oscillationTimer) * 0.05f;
		this.RightUpperArm_Bone.rotationPointY = (float)Math.cos(oscillationTimer) * 0.05f;
		
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}