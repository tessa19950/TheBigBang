package com.homebrewCult.TheBigBang.entities.model; 

import com.homebrewCult.TheBigBang.entities.mob.AbstractDrakeEntity;
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
public class AbstractDrakeModel <T extends AbstractDrakeEntity> extends EntityModel<T> {
	
	private float oscillationTimer = 0f;
	private float oscillationSpeed = .2f;
	
	private float tailStartMaxRot = .5f;
	private float tailEndMaxRot = .5f;
	private float tailEndTimeOffset = -.3f;

	private final ModelRenderer Body;
	private final ModelRenderer BackCervical_Bone;
	private final ModelRenderer BackLumbar_Bone;
	private final ModelRenderer BackPlate_Bone1;
	private final ModelRenderer BackPlate_Bone2;
	private final ModelRenderer BackPlate_Bone3;
	private final ModelRenderer BackPlate_Bone5;
	private final ModelRenderer BackPlate_Bone4;
	private final ModelRenderer BackPlateSmall_Bone1;
	private final ModelRenderer WingLeft_Bone;
	private final ModelRenderer WingRight_Bone;
	private final ModelRenderer NeckStart_Bone;
	private final ModelRenderer NeckEnd_Bone;
	private final ModelRenderer Head_Bone;
	private final ModelRenderer HeadHornLeft_Bone;
	private final ModelRenderer HeadHornLeftX_Bone;
	private final ModelRenderer HeadHornLeftY_Bone;
	private final ModelRenderer HeadHornRight_Bone;
	private final ModelRenderer HeadHornRightX_Bone2;
	private final ModelRenderer HeadHornRightY_Bone2;
	private final ModelRenderer Jaw_Bone;
	private final ModelRenderer EyeLeft_Bone;
	private final ModelRenderer EyeRight_Bone;
	private final ModelRenderer NoseHornX_Bone;
	private final ModelRenderer NoseHornY_Bone;
	private final ModelRenderer TailStart_Bone;
	private final ModelRenderer TailEnd_Bone;
	private final ModelRenderer BackPlateSmall_Bone3;
	private final ModelRenderer BackPlateSmall_Bone2;
	private final ModelRenderer LeftUpperLeg_Bone;
	private final ModelRenderer LeftLowerLeg_Bone;
	private final ModelRenderer LeftFoot_Bone;
	private final ModelRenderer RightUpperLeg_Bone;
	private final ModelRenderer RightLowerLeg_Bone;
	private final ModelRenderer RightFoot_Bone;
	private final ModelRenderer LeftUpperArm_Bone;
	private final ModelRenderer LeftLowerArm_Bone;
	private final ModelRenderer LeftHand_Bone;
	private final ModelRenderer RightUpperArm_Bone;
	private final ModelRenderer RightLowerArm_Bone;
	private final ModelRenderer RightHand_Bone;

	public AbstractDrakeModel() {
		super();
		textureWidth = 128;
		textureHeight = 128;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.setTextureOffset(47, 33).addBox(-5.0F, -30.0F, -6.0F, 10.0F, 14.0F, 14.0F, 0.0F, false);
		Body.setTextureOffset(29, 62).addBox(-6.0F, -23.0F, -9.0F, 12.0F, 18.0F, 18.0F, 0.0F, false);

		BackCervical_Bone = new ModelRenderer(this);
		BackCervical_Bone.setRotationPoint(0.0F, -23.0F, 7.0F);
		Body.addChild(BackCervical_Bone);
		setRotationAngle(BackCervical_Bone, -1.2217F, 0.0F, 0.0F);
		BackCervical_Bone.setTextureOffset(66, 75).addBox(-4.0F, -3.0F, -6.0F, 8.0F, 8.0F, 23.0F, 0.0F, false);

		BackLumbar_Bone = new ModelRenderer(this);
		BackLumbar_Bone.setRotationPoint(0.0F, -23.0F, -7.0F);
		Body.addChild(BackLumbar_Bone);
		setRotationAngle(BackLumbar_Bone, 0.7854F, 0.0F, 0.0F);
		BackLumbar_Bone.setTextureOffset(0, 87).addBox(-4.0F, -3.5F, -6.0F, 8.0F, 8.0F, 12.0F, 0.0F, false);

		BackPlate_Bone1 = new ModelRenderer(this);
		BackPlate_Bone1.setRotationPoint(0.0F, -30.0F, 1.0F);
		Body.addChild(BackPlate_Bone1);
		BackPlate_Bone1.setTextureOffset(82, 35).addBox(0.0F, -6.0F, -3.0F, 0.0F, 6.0F, 5.0F, 0.0F, false);

		BackPlate_Bone2 = new ModelRenderer(this);
		BackPlate_Bone2.setRotationPoint(0.0F, -29.0F, -5.0F);
		Body.addChild(BackPlate_Bone2);
		setRotationAngle(BackPlate_Bone2, 0.4363F, 0.0F, 0.0F);
		BackPlate_Bone2.setTextureOffset(82, 35).addBox(0.0F, -6.0F, -2.5F, 0.0F, 6.0F, 5.0F, 0.0F, false);

		BackPlate_Bone3 = new ModelRenderer(this);
		BackPlate_Bone3.setRotationPoint(0.0F, -29.0F, 7.0F);
		Body.addChild(BackPlate_Bone3);
		setRotationAngle(BackPlate_Bone3, -0.4363F, 0.0F, 0.0F);
		BackPlate_Bone3.setTextureOffset(82, 35).addBox(0.0F, -5.5F, -3.0F, 0.0F, 6.0F, 5.0F, 0.0F, false);

		BackPlate_Bone5 = new ModelRenderer(this);
		BackPlate_Bone5.setRotationPoint(0.0F, -24.0F, -9.0F);
		Body.addChild(BackPlate_Bone5);
		setRotationAngle(BackPlate_Bone5, 0.7854F, 0.0F, 0.0F);
		BackPlate_Bone5.setTextureOffset(82, 35).addBox(0.0F, -6.0F, -2.0F, 0.0F, 6.0F, 5.0F, 0.0F, false);

		BackPlate_Bone4 = new ModelRenderer(this);
		BackPlate_Bone4.setRotationPoint(0.0F, -23.0F, 10.0F);
		Body.addChild(BackPlate_Bone4);
		setRotationAngle(BackPlate_Bone4, -1.0472F, 0.0F, 0.0F);
		BackPlate_Bone4.setTextureOffset(82, 35).addBox(0.0F, -6.0F, -4.0F, 0.0F, 6.0F, 5.0F, 0.0F, false);

		BackPlateSmall_Bone1 = new ModelRenderer(this);
		BackPlateSmall_Bone1.setRotationPoint(0.0F, -19.0F, 12.0F);
		Body.addChild(BackPlateSmall_Bone1);
		setRotationAngle(BackPlateSmall_Bone1, -1.2217F, 0.0F, 0.0F);
		BackPlateSmall_Bone1.setTextureOffset(92, 37).addBox(0.0F, -3.0F, -2.0F, 0.0F, 4.0F, 5.0F, 0.0F, false);

		WingLeft_Bone = new ModelRenderer(this);
		WingLeft_Bone.setRotationPoint(5.0F, -27.0F, 1.0F);
		Body.addChild(WingLeft_Bone);
		setRotationAngle(WingLeft_Bone, -0.2618F, 0.2618F, 0.0F);
		WingLeft_Bone.setTextureOffset(96, 31).addBox(0.0F, -9.0F, 0.0F, 0.0F, 9.0F, 16.0F, 0.0F, false);

		WingRight_Bone = new ModelRenderer(this);
		WingRight_Bone.setRotationPoint(-4.9581F, -27.0F, 1.9587F);
		Body.addChild(WingRight_Bone);
		setRotationAngle(WingRight_Bone, -0.2618F, -0.2618F, 0.0F);
		WingRight_Bone.setTextureOffset(96, 31).addBox(0.0F, -9.0F, 0.0F, 0.0F, 9.0F, 16.0F, 0.0F, false);

		NeckStart_Bone = new ModelRenderer(this);
		NeckStart_Bone.setRotationPoint(0.0F, 14.0F, -10.0F);
		setRotationAngle(NeckStart_Bone, -0.6109F, 0.0F, 0.0F);
		NeckStart_Bone.setTextureOffset(0, 39).addBox(-5.0F, -11.0F, -10.0F, 10.0F, 13.0F, 13.0F, 0.0F, false);

		NeckEnd_Bone = new ModelRenderer(this);
		NeckEnd_Bone.setRotationPoint(0.0F, -3.6865F, -11.253F);
		NeckStart_Bone.addChild(NeckEnd_Bone);
		setRotationAngle(NeckEnd_Bone, -0.1745F, 0.0F, 0.0F);
		NeckEnd_Bone.setTextureOffset(0, 20).addBox(-3.0F, -5.3135F, -7.747F, 6.0F, 8.0F, 10.0F, 0.0F, false);

		Head_Bone = new ModelRenderer(this);
		Head_Bone.setRotationPoint(0.0F, -1.3572F, -7.766F);
		NeckEnd_Bone.addChild(Head_Bone);
		setRotationAngle(Head_Bone, 1.0472F, 0.0F, 0.0F);
		Head_Bone.setTextureOffset(0, 0).addBox(-4.0F, -4.4116F, -10.381F, 8.0F, 6.0F, 14.0F, 0.0F, false);

		HeadHornLeft_Bone = new ModelRenderer(this);
		HeadHornLeft_Bone.setRotationPoint(3.0F, -4.9563F, 3.019F);
		Head_Bone.addChild(HeadHornLeft_Bone);
		setRotationAngle(HeadHornLeft_Bone, -0.7854F, 0.0F, 0.0F);


		HeadHornLeftX_Bone = new ModelRenderer(this);
		HeadHornLeftX_Bone.setRotationPoint(0.0F, -1.0F, 0.0F);
		HeadHornLeft_Bone.addChild(HeadHornLeftX_Bone);
		setRotationAngle(HeadHornLeftX_Bone, 0.0F, -0.7854F, 0.0F);
		HeadHornLeftX_Bone.setTextureOffset(65, 5).addBox(0.0F, -3.0F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, false);

		HeadHornLeftY_Bone = new ModelRenderer(this);
		HeadHornLeftY_Bone.setRotationPoint(0.0F, -1.0F, 0.0F);
		HeadHornLeft_Bone.addChild(HeadHornLeftY_Bone);
		setRotationAngle(HeadHornLeftY_Bone, 0.0F, 0.7854F, 0.0F);
		HeadHornLeftY_Bone.setTextureOffset(65, 5).addBox(0.0F, -3.0F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, false);

		HeadHornRight_Bone = new ModelRenderer(this);
		HeadHornRight_Bone.setRotationPoint(-3.0F, -4.9563F, 3.019F);
		Head_Bone.addChild(HeadHornRight_Bone);
		setRotationAngle(HeadHornRight_Bone, -0.7854F, 0.0F, 0.0F);


		HeadHornRightX_Bone2 = new ModelRenderer(this);
		HeadHornRightX_Bone2.setRotationPoint(0.0F, -1.0F, 0.0F);
		HeadHornRight_Bone.addChild(HeadHornRightX_Bone2);
		setRotationAngle(HeadHornRightX_Bone2, 0.0F, -0.7854F, 0.0F);
		HeadHornRightX_Bone2.setTextureOffset(65, 5).addBox(0.0F, -3.0F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, false);

		HeadHornRightY_Bone2 = new ModelRenderer(this);
		HeadHornRightY_Bone2.setRotationPoint(0.0F, -1.0F, 0.0F);
		HeadHornRight_Bone.addChild(HeadHornRightY_Bone2);
		setRotationAngle(HeadHornRightY_Bone2, 0.0F, 0.7854F, 0.0F);
		HeadHornRightY_Bone2.setTextureOffset(65, 5).addBox(0.0F, -3.0F, -1.5F, 0.0F, 5.0F, 3.0F, 0.0F, false);

		Jaw_Bone = new ModelRenderer(this);
		Jaw_Bone.setRotationPoint(0.0F, 2.5884F, 1.619F);
		Head_Bone.addChild(Jaw_Bone);
		setRotationAngle(Jaw_Bone, -0.1745F, 0.0F, 0.0F);
		Jaw_Bone.setTextureOffset(34, 17).addBox(-3.5F, -2.1428F, -11.234F, 7.0F, 4.0F, 12.0F, 0.0F, false);

		EyeLeft_Bone = new ModelRenderer(this);
		EyeLeft_Bone.setRotationPoint(1.5F, -5.0981F, 1.366F);
		Head_Bone.addChild(EyeLeft_Bone);
		setRotationAngle(EyeLeft_Bone, -0.3491F, 0.0F, 0.0F);
		EyeLeft_Bone.setTextureOffset(40, 4).addBox(-2.0F, -1.8582F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		EyeRight_Bone = new ModelRenderer(this);
		EyeRight_Bone.setRotationPoint(-2.0F, -5.0981F, 0.866F);
		Head_Bone.addChild(EyeRight_Bone);
		setRotationAngle(EyeRight_Bone, -0.3491F, 0.0F, 0.0F);
		EyeRight_Bone.setTextureOffset(31, 1).addBox(-1.5F, -0.8582F, -3.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		NoseHornX_Bone = new ModelRenderer(this);
		NoseHornX_Bone.setRotationPoint(0.0F, -6.0981F, -6.634F);
		Head_Bone.addChild(NoseHornX_Bone);
		setRotationAngle(NoseHornX_Bone, 0.0F, -0.7854F, 0.0F);
		NoseHornX_Bone.setTextureOffset(57, 4).addBox(0.0F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);

		NoseHornY_Bone = new ModelRenderer(this);
		NoseHornY_Bone.setRotationPoint(0.0F, -6.0981F, -6.634F);
		Head_Bone.addChild(NoseHornY_Bone);
		setRotationAngle(NoseHornY_Bone, 0.0F, 0.7854F, 0.0F);
		NoseHornY_Bone.setTextureOffset(57, 4).addBox(0.0F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);

		TailStart_Bone = new ModelRenderer(this);
		TailStart_Bone.setRotationPoint(0.0F, 13.0F, 14.0F);
		setRotationAngle(TailStart_Bone, 0.4363F, 0.0F, 0.0F);
		TailStart_Bone.setTextureOffset(92, 110).addBox(-3.0F, -3.5031F, -0.8533F, 6.0F, 6.0F, 12.0F, 0.0F, false);

		TailEnd_Bone = new ModelRenderer(this);
		TailEnd_Bone.setRotationPoint(0.0F, -1.0F, 11.0F);
		TailStart_Bone.addChild(TailEnd_Bone);
		setRotationAngle(TailEnd_Bone, 0.2618F, 0.0F, 0.0F);
		TailEnd_Bone.setTextureOffset(74, 107).addBox(-2.0F, -1.5F, -2.0F, 4.0F, 4.0F, 11.0F, 0.0F, false);

		BackPlateSmall_Bone3 = new ModelRenderer(this);
		BackPlateSmall_Bone3.setRotationPoint(0.0F, -1.0507F, 3.9569F);
		TailEnd_Bone.addChild(BackPlateSmall_Bone3);
		BackPlateSmall_Bone3.setTextureOffset(92, 37).addBox(0.0F, -3.9493F, -1.9569F, 0.0F, 4.0F, 5.0F, 0.0F, false);

		BackPlateSmall_Bone2 = new ModelRenderer(this);
		BackPlateSmall_Bone2.setRotationPoint(0.0F, -3.1964F, 7.7675F);
		TailStart_Bone.addChild(BackPlateSmall_Bone2);
		BackPlateSmall_Bone2.setTextureOffset(92, 37).addBox(0.0F, -3.8036F, -2.7675F, 0.0F, 4.0F, 5.0F, 0.0F, false);

		LeftUpperLeg_Bone = new ModelRenderer(this);
		LeftUpperLeg_Bone.setRotationPoint(6.0F, 13.0F, 3.0F);
		setRotationAngle(LeftUpperLeg_Bone, -1.2217F, 0.0F, 0.0F);
		LeftUpperLeg_Bone.setTextureOffset(33, 105).addBox(0.0F, -3.0F, -5.0F, 4.0F, 12.0F, 8.0F, 0.0F, false);

		LeftLowerLeg_Bone = new ModelRenderer(this);
		LeftLowerLeg_Bone.setRotationPoint(2.0F, 7.0F, 2.0F);
		LeftUpperLeg_Bone.addChild(LeftLowerLeg_Bone);
		setRotationAngle(LeftLowerLeg_Bone, 0.6981F, 0.0F, 0.0F);
		LeftLowerLeg_Bone.setTextureOffset(47, 115).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);

		LeftFoot_Bone = new ModelRenderer(this);
		LeftFoot_Bone.setRotationPoint(0.0F, 0.5113F, 8.5572F);
		LeftLowerLeg_Bone.addChild(LeftFoot_Bone);
		setRotationAngle(LeftFoot_Bone, -0.8727F, 0.0F, 0.0F);
		LeftFoot_Bone.setTextureOffset(62, 107).addBox(-3.5F, -0.5113F, 0.4428F, 7.0F, 10.0F, 0.0F, 0.0F, false);

		RightUpperLeg_Bone = new ModelRenderer(this);
		RightUpperLeg_Bone.setRotationPoint(-6.0F, 13.0F, 3.0F);
		setRotationAngle(RightUpperLeg_Bone, -1.2217F, 0.0F, 0.0F);
		RightUpperLeg_Bone.setTextureOffset(33, 105).addBox(-4.0F, -3.0F, -5.0F, 4.0F, 12.0F, 8.0F, 0.0F, false);

		RightLowerLeg_Bone = new ModelRenderer(this);
		RightLowerLeg_Bone.setRotationPoint(-2.0F, 7.0F, 2.0F);
		RightUpperLeg_Bone.addChild(RightLowerLeg_Bone);
		setRotationAngle(RightLowerLeg_Bone, 0.6981F, 0.0F, 0.0F);
		RightLowerLeg_Bone.setTextureOffset(47, 115).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);

		RightFoot_Bone = new ModelRenderer(this);
		RightFoot_Bone.setRotationPoint(0.0F, 0.5113F, 8.5572F);
		RightLowerLeg_Bone.addChild(RightFoot_Bone);
		setRotationAngle(RightFoot_Bone, -0.8727F, 0.0F, 0.0F);
		RightFoot_Bone.setTextureOffset(62, 107).addBox(-3.5F, -0.5113F, 0.4428F, 7.0F, 10.0F, 0.0F, 0.0F, false);

		LeftUpperArm_Bone = new ModelRenderer(this);
		LeftUpperArm_Bone.setRotationPoint(5.0F, 9.0F, -10.0F);
		setRotationAngle(LeftUpperArm_Bone, 0.6981F, 0.0F, 0.0F);
		LeftUpperArm_Bone.setTextureOffset(0, 115).addBox(-1.0F, -2.0F, -10.0F, 3.0F, 3.0F, 10.0F, 0.0F, false);

		LeftLowerArm_Bone = new ModelRenderer(this);
		LeftLowerArm_Bone.setRotationPoint(0.5F, -0.5F, -9.0F);
		LeftUpperArm_Bone.addChild(LeftLowerArm_Bone);
		setRotationAngle(LeftLowerArm_Bone, -1.309F, 0.0F, 0.0F);
		LeftLowerArm_Bone.setTextureOffset(4, 119).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		LeftHand_Bone = new ModelRenderer(this);
		LeftHand_Bone.setRotationPoint(0.0F, 0.0F, -6.0F);
		LeftLowerArm_Bone.addChild(LeftHand_Bone);
		setRotationAngle(LeftHand_Bone, 0.9599F, 0.0F, 0.0F);
		LeftHand_Bone.setTextureOffset(0, 108).addBox(-2.0F, -3.5F, -1.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		LeftHand_Bone.setTextureOffset(11, 108).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		RightUpperArm_Bone = new ModelRenderer(this);
		RightUpperArm_Bone.setRotationPoint(-6.0F, 9.0F, -10.0F);
		setRotationAngle(RightUpperArm_Bone, 0.6981F, 0.0F, 0.0F);
		RightUpperArm_Bone.setTextureOffset(0, 115).addBox(-1.0F, -2.0F, -10.0F, 3.0F, 3.0F, 10.0F, 0.0F, false);

		RightLowerArm_Bone = new ModelRenderer(this);
		RightLowerArm_Bone.setRotationPoint(0.5F, -0.5F, -9.0F);
		RightUpperArm_Bone.addChild(RightLowerArm_Bone);
		setRotationAngle(RightLowerArm_Bone, -1.309F, 0.0F, 0.0F);
		RightLowerArm_Bone.setTextureOffset(4, 119).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		RightHand_Bone = new ModelRenderer(this);
		RightHand_Bone.setRotationPoint(0.0F, 0.0F, -6.0F);
		RightLowerArm_Bone.addChild(RightHand_Bone);
		setRotationAngle(RightHand_Bone, 0.9599F, 0.0F, 0.0F);
		RightHand_Bone.setTextureOffset(0, 108).addBox(-2.0F, -3.5F, -1.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		RightHand_Bone.setTextureOffset(11, 108).addBox(-2.0F, -3.5F, -2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		NeckStart_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		TailStart_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftUpperLeg_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		RightUpperLeg_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		LeftUpperArm_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		RightUpperArm_Bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setLivingAnimations(T entity, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTick);
		oscillationTimer = (entity.ticksExisted + partialTick) * oscillationSpeed;
	}

	public void setRotationAngles(T drake, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.EyeLeft_Bone.rotateAngleX = -0.2f + (headPitch) * ((float) Math.PI / 180F);
		this.EyeLeft_Bone.rotateAngleY = (netHeadYaw / 4) * ((float) Math.PI / 180F);
		this.EyeRight_Bone.rotateAngleX = -0.2f + (headPitch) * ((float) Math.PI / 180F);
		this.EyeRight_Bone.rotateAngleY = (netHeadYaw / 4) * ((float) Math.PI / 180F);

		this.Head_Bone.rotateAngleX = 1f + (headPitch * ((float) Math.PI / 180F));
		this.Head_Bone.rotateAngleY = (netHeadYaw * 0.1f) * ((float) Math.PI / 180F);
		this.Head_Bone.rotateAngleZ = (netHeadYaw * 0.4f) * ((float) Math.PI / 180F);
		this.Jaw_Bone.rotateAngleX = -0.17F;

		this.NeckStart_Bone.rotateAngleY = (netHeadYaw * 0.1f) * ((float) Math.PI / 180F);
		this.NeckEnd_Bone.rotateAngleY = (netHeadYaw * 0.7f) * ((float) Math.PI / 180F);

		int t = drake.getFireballTick();
		if(t != -1 && ageInTicks - t < AbstractDrakeEntity.FIREBALL_DURATION) {
			float attackTimer = (ageInTicks - t)/AbstractDrakeEntity.FIREBALL_DURATION;
			if(attackTimer < 0.5F) {
				this.NeckStart_Bone.rotateAngleX = -0.6F - attackTimer;
				this.NeckEnd_Bone.rotateAngleX = -0.2F - attackTimer;
			} else {
				float f = MathHelper.clamp((attackTimer - 0.5F) * 4F, 0, 1);
				this.NeckStart_Bone.rotateAngleX = -1.1F + f;
				this.NeckEnd_Bone.rotateAngleX = -0.7F + f;
				this.Head_Bone.rotateAngleX = -f;
				this.Jaw_Bone.rotateAngleX = f;
			}
		} else {
			this.NeckStart_Bone.rotateAngleX = -0.6F;
			this.NeckEnd_Bone.rotateAngleX = -0.2F;
		}

		this.TailStart_Bone.rotateAngleY = (float)Math.cos(oscillationTimer) * tailStartMaxRot;
		this.TailEnd_Bone.rotateAngleY = (float)Math.cos(oscillationTimer + tailEndTimeOffset) * tailEndMaxRot;
		
        this.LeftUpperLeg_Bone.rotateAngleX = -1.4f + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.RightUpperLeg_Bone.rotateAngleX = -1.4f + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.LeftLowerLeg_Bone.rotateAngleX = 1f + MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.RightLowerLeg_Bone.rotateAngleX = 1f + MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}