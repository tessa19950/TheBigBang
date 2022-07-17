package com.homebrewCult.TheBigBang.entities.model; 

import com.homebrewCult.TheBigBang.entities.mob.AbstractDrakeEntity;
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
public class AbstractDrakeModel <T extends AbstractDrakeEntity> extends QuadrupedModel<T> {
	
	private float oscillationTimer = 0f;
	private float oscillationSpeed = .2f;
	
	private float tailStartMaxRot = .5f;
	private float tailEndMaxRot = .5f;
	private float tailEndTimeOffset = -.3f;
	
	private final RendererModel Body;
	private final RendererModel BackCervical_Bone;
	private final RendererModel BackLumbar_Bone;
	private final RendererModel BackPlate_Bone1;
	private final RendererModel BackPlate_Bone2;
	private final RendererModel BackPlate_Bone3;
	private final RendererModel BackPlate_Bone5;
	private final RendererModel BackPlate_Bone4;
	private final RendererModel BackPlateSmall_Bone1;
	private final RendererModel WingLeft_Bone;
	private final RendererModel WingRight_Bone;
	private final RendererModel NeckStart_Bone;
	private final RendererModel NeckEnd_Bone;
	private final RendererModel Head_Bone;
	private final RendererModel HeadHornLeft_Bone;
	private final RendererModel HeadHornLeftX_Bone;
	private final RendererModel HeadHornLeftY_Bone;
	private final RendererModel HeadHornRight_Bone;
	private final RendererModel HeadHornRightX_Bone2;
	private final RendererModel HeadHornRightY_Bone2;
	private final RendererModel Jaw_Bone;
	private final RendererModel EyeLeft_Bone;
	private final RendererModel EyeRight_Bone;
	private final RendererModel NoseHornX_Bone;
	private final RendererModel NoseHornY_Bone;
	private final RendererModel TailStart_Bone;
	private final RendererModel TailEnd_Bone;
	private final RendererModel BackPlateSmall_Bone3;
	private final RendererModel BackPlateSmall_Bone2;
	private final RendererModel LeftUpperLeg_Bone;
	private final RendererModel LeftLowerLeg_Bone;
	private final RendererModel LeftFoot_Bone;
	private final RendererModel RightUpperLeg_Bone;
	private final RendererModel RightLowerLeg_Bone;
	private final RendererModel RightFoot_Bone;
	private final RendererModel LeftUpperArm_Bone;
	private final RendererModel LeftLowerArm_Bone;
	private final RendererModel LeftHand_Bone;
	private final RendererModel RightUpperArm_Bone;
	private final RendererModel RightLowerArm_Bone;
	private final RendererModel RightHand_Bone;

	public AbstractDrakeModel() {
		super(6, 0.0f);
		
		textureWidth = 128;
		textureHeight = 128;

		Body = new RendererModel(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 47, 33, -5.0F, -30.0F, -6.0F, 10, 14, 14, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 29, 62, -6.0F, -23.0F, -9.0F, 12, 18, 18, 0.0F, false));

		BackCervical_Bone = new RendererModel(this);
		BackCervical_Bone.setRotationPoint(0.0F, -23.0F, 7.0F);
		setRotationAngle(BackCervical_Bone, -1.2217F, 0.0F, 0.0F);
		Body.addChild(BackCervical_Bone);
		BackCervical_Bone.cubeList.add(new ModelBox(BackCervical_Bone, 66, 75, -4.0F, -3.0F, -6.0F, 8, 8, 23, 0.0F, false));

		BackLumbar_Bone = new RendererModel(this);
		BackLumbar_Bone.setRotationPoint(0.0F, -23.0F, -7.0F);
		setRotationAngle(BackLumbar_Bone, 0.7854F, 0.0F, 0.0F);
		Body.addChild(BackLumbar_Bone);
		BackLumbar_Bone.cubeList.add(new ModelBox(BackLumbar_Bone, 0, 87, -4.0F, -3.5F, -6.0F, 8, 8, 12, 0.0F, false));

		BackPlate_Bone1 = new RendererModel(this);
		BackPlate_Bone1.setRotationPoint(0.0F, -30.0F, 1.0F);
		Body.addChild(BackPlate_Bone1);
		BackPlate_Bone1.cubeList.add(new ModelBox(BackPlate_Bone1, 82, 35, 0.0F, -6.0F, -3.0F, 0, 6, 5, 0.0F, false));

		BackPlate_Bone2 = new RendererModel(this);
		BackPlate_Bone2.setRotationPoint(0.0F, -29.0F, -5.0F);
		setRotationAngle(BackPlate_Bone2, 0.4363F, 0.0F, 0.0F);
		Body.addChild(BackPlate_Bone2);
		BackPlate_Bone2.cubeList.add(new ModelBox(BackPlate_Bone2, 82, 35, 0.0F, -6.0F, -2.5F, 0, 6, 5, 0.0F, false));

		BackPlate_Bone3 = new RendererModel(this);
		BackPlate_Bone3.setRotationPoint(0.0F, -29.0F, 7.0F);
		setRotationAngle(BackPlate_Bone3, -0.4363F, 0.0F, 0.0F);
		Body.addChild(BackPlate_Bone3);
		BackPlate_Bone3.cubeList.add(new ModelBox(BackPlate_Bone3, 82, 35, 0.0F, -5.5F, -3.0F, 0, 6, 5, 0.0F, false));

		BackPlate_Bone5 = new RendererModel(this);
		BackPlate_Bone5.setRotationPoint(0.0F, -24.0F, -9.0F);
		setRotationAngle(BackPlate_Bone5, 0.7854F, 0.0F, 0.0F);
		Body.addChild(BackPlate_Bone5);
		BackPlate_Bone5.cubeList.add(new ModelBox(BackPlate_Bone5, 82, 35, 0.0F, -6.0F, -2.0F, 0, 6, 5, 0.0F, false));

		BackPlate_Bone4 = new RendererModel(this);
		BackPlate_Bone4.setRotationPoint(0.0F, -23.0F, 10.0F);
		setRotationAngle(BackPlate_Bone4, -1.0472F, 0.0F, 0.0F);
		Body.addChild(BackPlate_Bone4);
		BackPlate_Bone4.cubeList.add(new ModelBox(BackPlate_Bone4, 82, 35, 0.0F, -6.0F, -4.0F, 0, 6, 5, 0.0F, false));

		BackPlateSmall_Bone1 = new RendererModel(this);
		BackPlateSmall_Bone1.setRotationPoint(0.0F, -19.0F, 12.0F);
		setRotationAngle(BackPlateSmall_Bone1, -1.2217F, 0.0F, 0.0F);
		Body.addChild(BackPlateSmall_Bone1);
		BackPlateSmall_Bone1.cubeList.add(new ModelBox(BackPlateSmall_Bone1, 92, 37, 0.0F, -3.0F, -2.0F, 0, 4, 5, 0.0F, false));

		WingLeft_Bone = new RendererModel(this);
		WingLeft_Bone.setRotationPoint(5.0F, -27.0F, 1.0F);
		setRotationAngle(WingLeft_Bone, -0.2618F, 0.2618F, 0.0F);
		Body.addChild(WingLeft_Bone);
		WingLeft_Bone.cubeList.add(new ModelBox(WingLeft_Bone, 96, 31, 0.0F, -9.0F, 0.0F, 0, 9, 16, 0.0F, false));

		WingRight_Bone = new RendererModel(this);
		WingRight_Bone.setRotationPoint(-4.9581F, -27.0F, 1.9587F);
		setRotationAngle(WingRight_Bone, -0.2618F, -0.2618F, 0.0F);
		Body.addChild(WingRight_Bone);
		WingRight_Bone.cubeList.add(new ModelBox(WingRight_Bone, 96, 31, 0.0F, -9.0F, 0.0F, 0, 9, 16, 0.0F, false));

		NeckStart_Bone = new RendererModel(this);
		NeckStart_Bone.setRotationPoint(0.0F, 14.0F, -10.0F);
		setRotationAngle(NeckStart_Bone, -0.6109F, 0.0F, 0.0F);
		NeckStart_Bone.cubeList.add(new ModelBox(NeckStart_Bone, 0, 39, -5.0F, -11.0F, -10.0F, 10, 13, 13, 0.0F, false));

		NeckEnd_Bone = new RendererModel(this);
		NeckEnd_Bone.setRotationPoint(0.0F, -3.6865F, -11.253F);
		setRotationAngle(NeckEnd_Bone, -0.1745F, 0.0F, 0.0F);
		NeckStart_Bone.addChild(NeckEnd_Bone);
		NeckEnd_Bone.cubeList.add(new ModelBox(NeckEnd_Bone, 0, 20, -3.0F, -5.3135F, -7.747F, 6, 8, 10, 0.0F, false));

		Head_Bone = new RendererModel(this);
		Head_Bone.setRotationPoint(0.0F, -1.3572F, -7.766F);
		setRotationAngle(Head_Bone, 1.0472F, 0.0F, 0.0F);
		NeckEnd_Bone.addChild(Head_Bone);
		Head_Bone.cubeList.add(new ModelBox(Head_Bone, 0, 0, -4.0F, -4.4116F, -10.381F, 8, 6, 14, 0.0F, false));

		HeadHornLeft_Bone = new RendererModel(this);
		HeadHornLeft_Bone.setRotationPoint(3.0F, -4.9563F, 3.019F);
		setRotationAngle(HeadHornLeft_Bone, -0.7854F, 0.0F, 0.0F);
		Head_Bone.addChild(HeadHornLeft_Bone);

		HeadHornLeftX_Bone = new RendererModel(this);
		HeadHornLeftX_Bone.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotationAngle(HeadHornLeftX_Bone, 0.0F, -0.7854F, 0.0F);
		HeadHornLeft_Bone.addChild(HeadHornLeftX_Bone);
		HeadHornLeftX_Bone.cubeList.add(new ModelBox(HeadHornLeftX_Bone, 65, 5, 0.0F, -3.0F, -1.5F, 0, 5, 3, 0.0F, false));

		HeadHornLeftY_Bone = new RendererModel(this);
		HeadHornLeftY_Bone.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotationAngle(HeadHornLeftY_Bone, 0.0F, 0.7854F, 0.0F);
		HeadHornLeft_Bone.addChild(HeadHornLeftY_Bone);
		HeadHornLeftY_Bone.cubeList.add(new ModelBox(HeadHornLeftY_Bone, 65, 5, 0.0F, -3.0F, -1.5F, 0, 5, 3, 0.0F, false));

		HeadHornRight_Bone = new RendererModel(this);
		HeadHornRight_Bone.setRotationPoint(-3.0F, -4.9563F, 3.019F);
		setRotationAngle(HeadHornRight_Bone, -0.7854F, 0.0F, 0.0F);
		Head_Bone.addChild(HeadHornRight_Bone);

		HeadHornRightX_Bone2 = new RendererModel(this);
		HeadHornRightX_Bone2.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotationAngle(HeadHornRightX_Bone2, 0.0F, -0.7854F, 0.0F);
		HeadHornRight_Bone.addChild(HeadHornRightX_Bone2);
		HeadHornRightX_Bone2.cubeList.add(new ModelBox(HeadHornRightX_Bone2, 65, 5, 0.0F, -3.0F, -1.5F, 0, 5, 3, 0.0F, false));

		HeadHornRightY_Bone2 = new RendererModel(this);
		HeadHornRightY_Bone2.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotationAngle(HeadHornRightY_Bone2, 0.0F, 0.7854F, 0.0F);
		HeadHornRight_Bone.addChild(HeadHornRightY_Bone2);
		HeadHornRightY_Bone2.cubeList.add(new ModelBox(HeadHornRightY_Bone2, 65, 5, 0.0F, -3.0F, -1.5F, 0, 5, 3, 0.0F, false));

		Jaw_Bone = new RendererModel(this);
		Jaw_Bone.setRotationPoint(0.0F, 2.5884F, 1.619F);
		setRotationAngle(Jaw_Bone, -0.17F, 0.0F, 0.0F);
		Head_Bone.addChild(Jaw_Bone);
		Jaw_Bone.cubeList.add(new ModelBox(Jaw_Bone, 34, 17, -3.5F, -2.1428F, -11.234F, 7, 4, 12, 0.0F, false));

		EyeLeft_Bone = new RendererModel(this);
		EyeLeft_Bone.setRotationPoint(1.5F, -5.0981F, 1.366F);
		setRotationAngle(EyeLeft_Bone, -0.3491F, 0.0F, 0.0F);
		Head_Bone.addChild(EyeLeft_Bone);
		EyeLeft_Bone.cubeList.add(new ModelBox(EyeLeft_Bone, 40, 4, -2.0F, -1.8582F, -4.0F, 4, 4, 4, 0.0F, false));

		EyeRight_Bone = new RendererModel(this);
		EyeRight_Bone.setRotationPoint(-2.0F, -5.0981F, 0.866F);
		setRotationAngle(EyeRight_Bone, -0.3491F, 0.0F, 0.0F);
		Head_Bone.addChild(EyeRight_Bone);
		EyeRight_Bone.cubeList.add(new ModelBox(EyeRight_Bone, 31, 1, -1.5F, -0.8582F, -3.5F, 3, 3, 3, 0.0F, false));

		NoseHornX_Bone = new RendererModel(this);
		NoseHornX_Bone.setRotationPoint(0.0F, -6.0981F, -6.634F);
		setRotationAngle(NoseHornX_Bone, 0.0F, -0.7854F, 0.0F);
		Head_Bone.addChild(NoseHornX_Bone);
		NoseHornX_Bone.cubeList.add(new ModelBox(NoseHornX_Bone, 57, 4, 0.0F, -2.0F, -2.0F, 0, 4, 4, 0.0F, false));

		NoseHornY_Bone = new RendererModel(this);
		NoseHornY_Bone.setRotationPoint(0.0F, -6.0981F, -6.634F);
		setRotationAngle(NoseHornY_Bone, 0.0F, 0.7854F, 0.0F);
		Head_Bone.addChild(NoseHornY_Bone);
		NoseHornY_Bone.cubeList.add(new ModelBox(NoseHornY_Bone, 57, 4, 0.0F, -2.0F, -2.0F, 0, 4, 4, 0.0F, false));

		TailStart_Bone = new RendererModel(this);
		TailStart_Bone.setRotationPoint(0.0F, 13.0F, 14.0F);
		setRotationAngle(TailStart_Bone, 0.4363F, 0.0F, 0.0F);
		TailStart_Bone.cubeList.add(new ModelBox(TailStart_Bone, 92, 110, -3.0F, -3.5031F, -0.8533F, 6, 6, 12, 0.0F, false));

		TailEnd_Bone = new RendererModel(this);
		TailEnd_Bone.setRotationPoint(0.0F, -1.0F, 11.0F);
		setRotationAngle(TailEnd_Bone, 0.2618F, 0.0F, 0.0F);
		TailStart_Bone.addChild(TailEnd_Bone);
		TailEnd_Bone.cubeList.add(new ModelBox(TailEnd_Bone, 74, 107, -2.0F, -1.5F, -2.0F, 4, 4, 11, 0.0F, false));

		BackPlateSmall_Bone3 = new RendererModel(this);
		BackPlateSmall_Bone3.setRotationPoint(0.0F, -1.0507F, 3.9569F);
		TailEnd_Bone.addChild(BackPlateSmall_Bone3);
		BackPlateSmall_Bone3.cubeList.add(new ModelBox(BackPlateSmall_Bone3, 92, 37, 0.0F, -3.9493F, -1.9569F, 0, 4, 5, 0.0F, false));

		BackPlateSmall_Bone2 = new RendererModel(this);
		BackPlateSmall_Bone2.setRotationPoint(0.0F, -3.1964F, 7.7675F);
		TailStart_Bone.addChild(BackPlateSmall_Bone2);
		BackPlateSmall_Bone2.cubeList.add(new ModelBox(BackPlateSmall_Bone2, 92, 37, 0.0F, -3.8036F, -2.7675F, 0, 4, 5, 0.0F, false));

		LeftUpperLeg_Bone = new RendererModel(this);
		LeftUpperLeg_Bone.setRotationPoint(6.0F, 13.0F, 3.0F);
		setRotationAngle(LeftUpperLeg_Bone, -1.2217F, 0.0F, 0.0F);
		LeftUpperLeg_Bone.cubeList.add(new ModelBox(LeftUpperLeg_Bone, 33, 105, 0.0F, -3.0F, -5.0F, 4, 12, 8, 0.0F, false));

		LeftLowerLeg_Bone = new RendererModel(this);
		LeftLowerLeg_Bone.setRotationPoint(2.0F, 7.0F, 2.0F);
		setRotationAngle(LeftLowerLeg_Bone, 0.6981F, 0.0F, 0.0F);
		LeftUpperLeg_Bone.addChild(LeftLowerLeg_Bone);
		LeftLowerLeg_Bone.cubeList.add(new ModelBox(LeftLowerLeg_Bone, 47, 115, -1.0F, -1.0F, -1.0F, 2, 2, 10, 0.0F, false));

		LeftFoot_Bone = new RendererModel(this);
		LeftFoot_Bone.setRotationPoint(0.0F, 0.5113F, 8.5572F);
		setRotationAngle(LeftFoot_Bone, -0.8727F, 0.0F, 0.0F);
		LeftLowerLeg_Bone.addChild(LeftFoot_Bone);
		LeftFoot_Bone.cubeList.add(new ModelBox(LeftFoot_Bone, 62, 107, -3.5F, -0.5113F, 0.4428F, 7, 10, 0, 0.0F, false));

		RightUpperLeg_Bone = new RendererModel(this);
		RightUpperLeg_Bone.setRotationPoint(-6.0F, 13.0F, 3.0F);
		setRotationAngle(RightUpperLeg_Bone, -1.2217F, 0.0F, 0.0F);
		RightUpperLeg_Bone.cubeList.add(new ModelBox(RightUpperLeg_Bone, 33, 105, -4.0F, -3.0F, -5.0F, 4, 12, 8, 0.0F, false));

		RightLowerLeg_Bone = new RendererModel(this);
		RightLowerLeg_Bone.setRotationPoint(-2.0F, 7.0F, 2.0F);
		setRotationAngle(RightLowerLeg_Bone, 0.6981F, 0.0F, 0.0F);
		RightUpperLeg_Bone.addChild(RightLowerLeg_Bone);
		RightLowerLeg_Bone.cubeList.add(new ModelBox(RightLowerLeg_Bone, 47, 115, -1.0F, -1.0F, -1.0F, 2, 2, 10, 0.0F, false));

		RightFoot_Bone = new RendererModel(this);
		RightFoot_Bone.setRotationPoint(0.0F, 0.5113F, 8.5572F);
		setRotationAngle(RightFoot_Bone, -0.8727F, 0.0F, 0.0F);
		RightLowerLeg_Bone.addChild(RightFoot_Bone);
		RightFoot_Bone.cubeList.add(new ModelBox(RightFoot_Bone, 62, 107, -3.5F, -0.5113F, 0.4428F, 7, 10, 0, 0.0F, false));

		LeftUpperArm_Bone = new RendererModel(this);
		LeftUpperArm_Bone.setRotationPoint(5.0F, 9.0F, -10.0F);
		setRotationAngle(LeftUpperArm_Bone, 0.6981F, 0.0F, 0.0F);
		LeftUpperArm_Bone.cubeList.add(new ModelBox(LeftUpperArm_Bone, 0, 115, -1.0F, -2.0F, -10.0F, 3, 3, 10, 0.0F, false));

		LeftLowerArm_Bone = new RendererModel(this);
		LeftLowerArm_Bone.setRotationPoint(0.5F, -0.5F, -9.0F);
		setRotationAngle(LeftLowerArm_Bone, -1.309F, 0.0F, 0.0F);
		LeftUpperArm_Bone.addChild(LeftLowerArm_Bone);
		LeftLowerArm_Bone.cubeList.add(new ModelBox(LeftLowerArm_Bone, 4, 119, -1.0F, -1.0F, -6.0F, 2, 2, 6, 0.0F, false));

		LeftHand_Bone = new RendererModel(this);
		LeftHand_Bone.setRotationPoint(0.0F, 0.0F, -6.0F);
		setRotationAngle(LeftHand_Bone, 0.9599F, 0.0F, 0.0F);
		LeftLowerArm_Bone.addChild(LeftHand_Bone);
		LeftHand_Bone.cubeList.add(new ModelBox(LeftHand_Bone, 0, 108, -2.0F, -3.5F, -1.0F, 4, 4, 1, 0.0F, false));
		LeftHand_Bone.cubeList.add(new ModelBox(LeftHand_Bone, 11, 108, -2.0F, -3.5F, -2.0F, 4, 4, 1, 0.0F, false));

		RightUpperArm_Bone = new RendererModel(this);
		RightUpperArm_Bone.setRotationPoint(-6.0F, 9.0F, -10.0F);
		setRotationAngle(RightUpperArm_Bone, 0.6981F, 0.0F, 0.0F);
		RightUpperArm_Bone.cubeList.add(new ModelBox(RightUpperArm_Bone, 0, 115, -1.0F, -2.0F, -10.0F, 3, 3, 10, 0.0F, false));

		RightLowerArm_Bone = new RendererModel(this);
		RightLowerArm_Bone.setRotationPoint(0.5F, -0.5F, -9.0F);
		setRotationAngle(RightLowerArm_Bone, -1.309F, 0.0F, 0.0F);
		RightUpperArm_Bone.addChild(RightLowerArm_Bone);
		RightLowerArm_Bone.cubeList.add(new ModelBox(RightLowerArm_Bone, 4, 119, -1.0F, -1.0F, -6.0F, 2, 2, 6, 0.0F, false));

		RightHand_Bone = new RendererModel(this);
		RightHand_Bone.setRotationPoint(0.0F, 0.0F, -6.0F);
		setRotationAngle(RightHand_Bone, 0.9599F, 0.0F, 0.0F);
		RightLowerArm_Bone.addChild(RightHand_Bone);
		RightHand_Bone.cubeList.add(new ModelBox(RightHand_Bone, 0, 108, -2.0F, -3.5F, -1.0F, 4, 4, 1, 0.0F, false));
		RightHand_Bone.cubeList.add(new ModelBox(RightHand_Bone, 11, 108, -2.0F, -3.5F, -2.0F, 4, 4, 1, 0.0F, false));
	}

	@Override
	public void render(AbstractDrakeEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Body.render(f5);
		NeckStart_Bone.render(f5);
		TailStart_Bone.render(f5);
		LeftUpperLeg_Bone.render(f5);
		RightUpperLeg_Bone.render(f5);
		LeftUpperArm_Bone.render(f5);
		RightUpperArm_Bone.render(f5);
	}
	
	@Override
	public void setLivingAnimations(T drake, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(drake, limbSwing, limbSwingAmount, partialTick);
		oscillationTimer = (drake.ticksExisted + partialTick) * oscillationSpeed;
	}
	
	@Override
	public void setRotationAngles(T drake, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		super.setRotationAngles(drake, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

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
	
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}