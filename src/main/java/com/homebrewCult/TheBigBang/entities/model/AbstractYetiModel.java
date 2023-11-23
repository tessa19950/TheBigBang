package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.mob.AbstractYetiEntity;
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
public class AbstractYetiModel <T extends AbstractYetiEntity> extends EntityModel<T> {

	private final ModelRenderer Hip_Bone;
	private final ModelRenderer Back_Bone;
	private final ModelRenderer Head_Bone;
	private final ModelRenderer cube_r1;
	private final ModelRenderer HornX_Bone;
	private final ModelRenderer HornY_Bone;
	private final ModelRenderer Jaw_Bone;
	private final ModelRenderer LeftUpArm_Bone;
	private final ModelRenderer LeftUpArm_Saddle;
	private final ModelRenderer Furstrip_Bone3;
	private final ModelRenderer LeftLowArm_Bone;
	private final ModelRenderer FurStrip_Bone2;
	private final ModelRenderer LeftHand_Bone;
	private final ModelRenderer FurStrip_Bone;
	private final ModelRenderer RightUpArm_Bone;
	private final ModelRenderer RightUpArm_Saddle;
	private final ModelRenderer Furstrip_Bone2;
	private final ModelRenderer RightLowArm_Bone2;
	private final ModelRenderer FurStrip_Bone3;
	private final ModelRenderer RightHand_Bone2;
	private final ModelRenderer FurStrip_Bone4;
	private final ModelRenderer LeftUpleg_Bone;
	private final ModelRenderer LeftLowLeg_Bone;
	private final ModelRenderer RightUpleg_Bone;
	private final ModelRenderer RightLowLeg_Bone;
	private final ModelRenderer Saddle;
	private final ModelRenderer Saddle_Support_Left_r1;

	public AbstractYetiModel() {
		textureWidth = 128;
		textureHeight = 128;

		Hip_Bone = new ModelRenderer(this);
		Hip_Bone.setRotationPoint(0.0F, 16.0F, 10.0F);
		setRotationAngle(Hip_Bone, -0.2618F, 0.0F, 0.0F);
		Hip_Bone.setTextureOffset(80, 100).addBox(-7.0F, -17.0F, -6.0F, 14.0F, 18.0F, 10.0F, 0.0F, false);

		Back_Bone = new ModelRenderer(this);
		Back_Bone.setRotationPoint(0.0F, -18.0F, 0.0F);
		Hip_Bone.addChild(Back_Bone);
		setRotationAngle(Back_Bone, 0.5236F, 0.0F, 0.0F);
		Back_Bone.setTextureOffset(0, 66).addBox(-13.0F, -21.0F, -10.0F, 26.0F, 24.0F, 14.0F, 0.0F, false);

		Head_Bone = new ModelRenderer(this);
		Head_Bone.setRotationPoint(0.0F, -10.0F, -5.0F);
		Back_Bone.addChild(Head_Bone);
		Head_Bone.setTextureOffset(0, 0).addBox(-9.0F, -12.0F, -23.0F, 18.0F, 14.0F, 22.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Head_Bone.addChild(cube_r1);
		setRotationAngle(cube_r1, -1.5708F, 0.0F, -1.5708F);
		cube_r1.setTextureOffset(32, 104).addBox(10.0F, -9.0F, 0.0F, 6.0F, 24.0F, 0.0F, 0.0F, false);

		HornX_Bone = new ModelRenderer(this);
		HornX_Bone.setRotationPoint(0.0F, -15.5F, -15.5F);
		Head_Bone.addChild(HornX_Bone);
		setRotationAngle(HornX_Bone, 0.0F, -0.7854F, 0.0F);
		HornX_Bone.setTextureOffset(109, 46).addBox(0.0F, -3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 0.0F, false);

		HornY_Bone = new ModelRenderer(this);
		HornY_Bone.setRotationPoint(0.0F, -15.5F, -15.5F);
		Head_Bone.addChild(HornY_Bone);
		setRotationAngle(HornY_Bone, 0.0F, 0.7854F, 0.0F);
		HornY_Bone.setTextureOffset(109, 46).addBox(0.0F, -3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 0.0F, false);

		Jaw_Bone = new ModelRenderer(this);
		Jaw_Bone.setRotationPoint(0.0F, -2.0F, -3.0F);
		Head_Bone.addChild(Jaw_Bone);
		Jaw_Bone.setTextureOffset(0, 34).addBox(-10.0F, 0.0F, -21.0F, 20.0F, 12.0F, 20.0F, 0.0F, false);

		LeftUpArm_Bone = new ModelRenderer(this);
		LeftUpArm_Bone.setRotationPoint(13.0F, -15.0F, -3.0F);
		Back_Bone.addChild(LeftUpArm_Bone);
		setRotationAngle(LeftUpArm_Bone, -0.1745F, 0.0F, -0.0873F);
		LeftUpArm_Bone.setTextureOffset(0, 104).addBox(0.0F, -4.0F, -4.0F, 8.0F, 16.0F, 8.0F, 0.0F, false);

		LeftUpArm_Saddle = new ModelRenderer(this);
		LeftUpArm_Saddle.setRotationPoint(-13.0F, 41.0F, -7.0F);
		LeftUpArm_Bone.addChild(LeftUpArm_Saddle);
		LeftUpArm_Saddle.setTextureOffset(48, 107).addBox(13.0F, -45.0F, 3.0F, 8.0F, 13.0F, 8.0F, 0.2F, false);

		Furstrip_Bone3 = new ModelRenderer(this);
		Furstrip_Bone3.setRotationPoint(8.0F, 3.0F, 4.0F);
		LeftUpArm_Bone.addChild(Furstrip_Bone3);
		setRotationAngle(Furstrip_Bone3, 0.0F, -0.7854F, 0.0F);
		Furstrip_Bone3.setTextureOffset(113, 21).addBox(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, 0.0F, false);

		LeftLowArm_Bone = new ModelRenderer(this);
		LeftLowArm_Bone.setRotationPoint(4.0F, 11.0F, 0.0F);
		LeftUpArm_Bone.addChild(LeftLowArm_Bone);
		setRotationAngle(LeftLowArm_Bone, -0.5236F, 0.0F, 0.0F);
		LeftLowArm_Bone.setTextureOffset(80, 72).addBox(-6.0F, -1.0F, -6.0F, 12.0F, 16.0F, 12.0F, 0.0F, false);

		FurStrip_Bone2 = new ModelRenderer(this);
		FurStrip_Bone2.setRotationPoint(6.0F, 5.0F, 6.0F);
		LeftLowArm_Bone.addChild(FurStrip_Bone2);
		setRotationAngle(FurStrip_Bone2, 0.0F, -0.7854F, 0.0F);
		FurStrip_Bone2.setTextureOffset(113, 21).addBox(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, 0.0F, false);

		LeftHand_Bone = new ModelRenderer(this);
		LeftHand_Bone.setRotationPoint(0.0F, 11.0F, 0.0F);
		LeftLowArm_Bone.addChild(LeftHand_Bone);
		setRotationAngle(LeftHand_Bone, 0.4363F, -0.1745F, 0.0F);
		LeftHand_Bone.setTextureOffset(64, 20).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		LeftHand_Bone.setTextureOffset(66, 52).addBox(-7.0F, 13.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);

		FurStrip_Bone = new ModelRenderer(this);
		FurStrip_Bone.setRotationPoint(8.0F, 6.0F, 8.0F);
		LeftHand_Bone.addChild(FurStrip_Bone);
		setRotationAngle(FurStrip_Bone, 0.0F, -0.7854F, 0.0F);
		FurStrip_Bone.setTextureOffset(113, 21).addBox(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, 0.0F, false);

		RightUpArm_Bone = new ModelRenderer(this);
		RightUpArm_Bone.setRotationPoint(-13.0F, -15.0F, -3.0F);
		Back_Bone.addChild(RightUpArm_Bone);
		setRotationAngle(RightUpArm_Bone, -0.1745F, 0.0F, 0.0873F);
		RightUpArm_Bone.setTextureOffset(0, 104).addBox(-8.0F, -4.0F, -4.0F, 8.0F, 16.0F, 8.0F, 0.0F, true);

		RightUpArm_Saddle = new ModelRenderer(this);
		RightUpArm_Saddle.setRotationPoint(-21.0F, 41.0F, -7.0F);
		RightUpArm_Bone.addChild(RightUpArm_Saddle);
		RightUpArm_Saddle.setTextureOffset(48, 107).addBox(13.0F, -45.0F, 3.0F, 8.0F, 13.0F, 8.0F, 0.2F, true);

		Furstrip_Bone2 = new ModelRenderer(this);
		Furstrip_Bone2.setRotationPoint(-8.0F, 3.0F, 4.0F);
		RightUpArm_Bone.addChild(Furstrip_Bone2);
		setRotationAngle(Furstrip_Bone2, 0.0F, -2.3562F, 0.0F);
		Furstrip_Bone2.setTextureOffset(113, 21).addBox(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, 0.0F, false);

		RightLowArm_Bone2 = new ModelRenderer(this);
		RightLowArm_Bone2.setRotationPoint(-4.0F, 11.0F, 0.0F);
		RightUpArm_Bone.addChild(RightLowArm_Bone2);
		setRotationAngle(RightLowArm_Bone2, -0.5236F, 0.0F, 0.0F);
		RightLowArm_Bone2.setTextureOffset(80, 72).addBox(-6.0F, -1.0F, -6.0F, 12.0F, 16.0F, 12.0F, 0.0F, false);

		FurStrip_Bone3 = new ModelRenderer(this);
		FurStrip_Bone3.setRotationPoint(-6.0F, 5.0F, 6.0F);
		RightLowArm_Bone2.addChild(FurStrip_Bone3);
		setRotationAngle(FurStrip_Bone3, 0.0F, -2.3562F, 0.0F);
		FurStrip_Bone3.setTextureOffset(113, 21).addBox(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, 0.0F, false);

		RightHand_Bone2 = new ModelRenderer(this);
		RightHand_Bone2.setRotationPoint(0.0F, 11.0F, 0.0F);
		RightLowArm_Bone2.addChild(RightHand_Bone2);
		setRotationAngle(RightHand_Bone2, 0.4363F, 0.1745F, 0.0F);
		RightHand_Bone2.setTextureOffset(64, 20).addBox(-8.0F, 0.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		RightHand_Bone2.setTextureOffset(66, 52).addBox(-7.0F, 13.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);

		FurStrip_Bone4 = new ModelRenderer(this);
		FurStrip_Bone4.setRotationPoint(-8.0F, 6.0F, 8.0F);
		RightHand_Bone2.addChild(FurStrip_Bone4);
		setRotationAngle(FurStrip_Bone4, 0.0F, -2.3562F, 0.0F);
		FurStrip_Bone4.setTextureOffset(113, 21).addBox(0.0F, -6.0F, 0.0F, 4.0F, 12.0F, 0.0F, 0.0F, false);

		LeftUpleg_Bone = new ModelRenderer(this);
		LeftUpleg_Bone.setRotationPoint(5.0F, 14.0F, 7.0F);
		setRotationAngle(LeftUpleg_Bone, -0.5236F, 0.0F, 0.0F);
		LeftUpleg_Bone.setTextureOffset(0, 35).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);

		LeftLowLeg_Bone = new ModelRenderer(this);
		LeftLowLeg_Bone.setRotationPoint(0.0F, 6.0F, 0.0F);
		LeftUpleg_Bone.addChild(LeftLowLeg_Bone);
		setRotationAngle(LeftLowLeg_Bone, 0.5236F, 0.0F, 0.0F);
		LeftLowLeg_Bone.setTextureOffset(0, 1).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.2F, false);
		LeftLowLeg_Bone.setTextureOffset(0, 46).addBox(-2.5F, 2.0F, -2.5F, 5.0F, 3.0F, 5.0F, 0.0F, false);

		RightUpleg_Bone = new ModelRenderer(this);
		RightUpleg_Bone.setRotationPoint(-5.0F, 14.0F, 7.0F);
		setRotationAngle(RightUpleg_Bone, -0.5236F, 0.0F, 0.0F);
		RightUpleg_Bone.setTextureOffset(0, 35).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);

		RightLowLeg_Bone = new ModelRenderer(this);
		RightLowLeg_Bone.setRotationPoint(0.0F, 6.0F, 0.0F);
		RightUpleg_Bone.addChild(RightLowLeg_Bone);
		setRotationAngle(RightLowLeg_Bone, 0.5236F, 0.0F, 0.0F);
		RightLowLeg_Bone.setTextureOffset(0, 1).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.2F, false);
		RightLowLeg_Bone.setTextureOffset(0, 46).addBox(-2.5F, 2.0F, -2.5F, 5.0F, 3.0F, 5.0F, 0.0F, false);

		Saddle = new ModelRenderer(this);
		Saddle.setRotationPoint(0.0F, 24.0F, 0.0F);
		Saddle.setTextureOffset(64, 0).addBox(-8.0F, -42.0F, 14.0F, 16.0F, 4.0F, 16.0F, 0.0F, false);

		Saddle_Support_Left_r1 = new ModelRenderer(this);
		Saddle_Support_Left_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Saddle.addChild(Saddle_Support_Left_r1);
		setRotationAngle(Saddle_Support_Left_r1, 0.0F, 1.5708F, 0.0F);
		Saddle_Support_Left_r1.setTextureOffset(58, 0).addBox(-27.0F, -38.0F, 5.0F, 11.0F, 12.0F, 0.0F, 0.0F, false);
		Saddle_Support_Left_r1.setTextureOffset(58, 0).addBox(-27.0F, -38.0F, -5.0F, 11.0F, 12.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Hip_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftUpleg_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		RightUpleg_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Saddle.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head_Bone.rotateAngleX = Math.min(headPitch * ((float)Math.PI / 180F), 0);
	    this.Head_Bone.rotateAngleY = MathHelper.clamp(netHeadYaw * ((float)Math.PI / 180F), -0.2f, 0.2f);

	    boolean flag = entityIn.getSaddled();
	    this.Saddle.showModel = flag;
	    this.LeftUpArm_Saddle.showModel = flag;
	    this.RightUpArm_Saddle.showModel = flag;

		if(entityIn.isSitting()) {
			this.Hip_Bone.rotationPointY = 0.4F;
			this.LeftUpleg_Bone.rotationPointY = 0.4F;
			this.RightUpleg_Bone.rotationPointY = 0.4F;
			this.LeftUpleg_Bone.rotateAngleX = -1.6F;
			this.RightUpleg_Bone.rotateAngleX = -1.6F;
			this.LeftLowLeg_Bone.rotateAngleX = 0.3F;
			this.RightLowLeg_Bone.rotateAngleX = 0.3F;
			this.LeftUpArm_Bone.rotateAngleX = 0.2F;
			this.RightUpArm_Bone.rotateAngleX = 0.2F;
			this.LeftLowArm_Bone.rotateAngleX = -1.3F;
			this.RightLowArm_Bone2.rotateAngleX = -1.3F;
		} else {
			this.Hip_Bone.rotationPointY = 0F;
			this.LeftUpleg_Bone.rotationPointY = 0F;
			this.RightUpleg_Bone.rotationPointY = 0F;
			//LegWork
			float leftLegSwing = MathHelper.cos(limbSwing * 0.6f);
			float rightLegSwing = MathHelper.cos(limbSwing * 0.6f + (float) Math.PI);
			this.LeftUpleg_Bone.rotateAngleX = MathHelper.clamp(-0.4f + leftLegSwing * 3F * limbSwingAmount, -1.4f, 1.4f);
			this.RightUpleg_Bone.rotateAngleX = MathHelper.clamp(-0.4f + rightLegSwing * 3F * limbSwingAmount, -1.4f, 1.4f);
			this.LeftLowLeg_Bone.rotateAngleX = MathHelper.clamp(0.4f + leftLegSwing * 1F * limbSwingAmount, -1.4f, 1.4f);
			this.RightLowLeg_Bone.rotateAngleX = MathHelper.clamp(0.4f + rightLegSwing * 1F * limbSwingAmount, -1.4f, 1.4f);
			//ArmWork
			float leftArmSwing = MathHelper.cos(limbSwing * 0.6f);
			float rightArmSwing = MathHelper.cos(limbSwing * 0.6f + (float) Math.PI);
			this.LeftUpArm_Bone.rotateAngleX = MathHelper.clamp(-0.4f + leftArmSwing * 2F * limbSwingAmount, -1.4f, 1.4f);
			this.RightUpArm_Bone.rotateAngleX = MathHelper.clamp(-0.4f + rightArmSwing * 2F * limbSwingAmount, -1.4f, 1.4f);
			this.LeftLowArm_Bone.rotateAngleX = MathHelper.clamp(-0.3f + leftLegSwing * -2F * limbSwingAmount, -1.4f, 1.4f);
			this.RightLowArm_Bone2.rotateAngleX = MathHelper.clamp(-0.3f + rightLegSwing * -2F * limbSwingAmount, -1.4f, 1.4f);
		}
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}