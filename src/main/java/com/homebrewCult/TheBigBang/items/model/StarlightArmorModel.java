package com.homebrewCult.TheBigBang.items.model;
// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;

public class StarlightArmorModel extends BigBangArmorModel {
	public final RendererModel armorHead;
	public final RendererModel hatrim_right_pivot;
	public final RendererModel hatrim_left_pivot;
	public final RendererModel hattip1_pivot;
	public final RendererModel hattip2_pivot;
	public final RendererModel hattip3_pivot;
	public final RendererModel armorTorso;
	public final RendererModel tabardTorso_pivot;
	public final RendererModel armorWaist;
	public final RendererModel armorLeftArm;
	public final RendererModel armorRightArm;
	public final RendererModel armorLeftLeg;
	public final RendererModel armorLeftBoot;
	public final RendererModel armorRightLeg;
	public final RendererModel armorRightBoot;
	public final RendererModel tabardLegs_front;
	public final RendererModel tabardLegs_back;

	public static final StarlightArmorModel INSTANCE = new StarlightArmorModel();

	public StarlightArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new RendererModel(this);
		armorHead.setRotationPoint(0.0F, -8.0F, 0.0F);
		bipedHead.addChild(armorHead);
		setRotationAngle(armorHead, -10.0F, 0.0F, 0.0F);
		

		hatrim_right_pivot = new RendererModel(this);
		hatrim_right_pivot.setRotationPoint(0.0F, -1.0F, 0.0F);
		armorHead.addChild(hatrim_right_pivot);
		setRotationAngle(hatrim_right_pivot, 0.0F, 0.0F, -10.0F);
		hatrim_right_pivot.cubeList.add(new ModelBox(hatrim_right_pivot, 68, 46, -7.8F, 1.0F, -8.0F, 8, 2, 16, 0.0F, false));

		hatrim_left_pivot = new RendererModel(this);
		hatrim_left_pivot.setRotationPoint(0.0F, -1.0F, 0.0F);
		armorHead.addChild(hatrim_left_pivot);
		setRotationAngle(hatrim_left_pivot, 0.0F, 180.0F, 10.0F);
		hatrim_left_pivot.cubeList.add(new ModelBox(hatrim_left_pivot, 68, 46, -7.8F, 1.0F, -8.0F, 8, 2, 16, 0.0F, false));

		hattip1_pivot = new RendererModel(this);
		hattip1_pivot.setRotationPoint(0.0F, 2.0F, 0.0F);
		armorHead.addChild(hattip1_pivot);
		setRotationAngle(hattip1_pivot, 0.0F, 0.0F, 5.0F);
		hattip1_pivot.cubeList.add(new ModelBox(hattip1_pivot, 92, 21, -4.5F, -5.0F, -4.5F, 9, 5, 9, 0.0F, false));

		hattip2_pivot = new RendererModel(this);
		hattip2_pivot.setRotationPoint(0.0F, -5.0F, 0.0F);
		hattip1_pivot.addChild(hattip2_pivot);
		setRotationAngle(hattip2_pivot, 0.0F, 0.0F, -15.0F);
		hattip2_pivot.cubeList.add(new ModelBox(hattip2_pivot, 80, 35, -3.0F, -4.0F, -3.0F, 6, 5, 6, 0.0F, false));

		hattip3_pivot = new RendererModel(this);
		hattip3_pivot.setRotationPoint(0.0F, -4.0F, 0.0F);
		hattip2_pivot.addChild(hattip3_pivot);
		setRotationAngle(hattip3_pivot, 0.0F, 0.0F, 20.0F);
		hattip3_pivot.cubeList.add(new ModelBox(hattip3_pivot, 94, 12, -1.5F, -4.0F, -1.5F, 3, 5, 3, 0.0F, false));

		armorTorso = new RendererModel(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.cubeList.add(new ModelBox(armorTorso, 108, 0, -4.0F, 1.0F, -3.5F, 8, 8, 1, 0.0F, false));
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 11, -4.5F, -0.5F, -2.5F, 9, 9, 5, 0.0F, false));
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 0, -4.5F, -1.0F, -4.5F, 9, 2, 9, 0.0F, false));

		tabardTorso_pivot = new RendererModel(this);
		tabardTorso_pivot.setRotationPoint(0.0F, 0.0F, 3.0F);
		armorTorso.addChild(tabardTorso_pivot);
		setRotationAngle(tabardTorso_pivot, 0.0F, 180.0F, 0.0F);
		tabardTorso_pivot.cubeList.add(new ModelBox(tabardTorso_pivot, 108, 0, -4.0F, 1.0F, -0.5F, 8, 8, 1, 0.0F, false));

		armorWaist = new RendererModel(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.cubeList.add(new ModelBox(armorWaist, 64, 25, -4.5F, 8.5F, -2.5F, 9, 4, 5, 0.0F, false));

		tabardLegs_back = new RendererModel(this);
		tabardLegs_back.setRotationPoint(0.0F, 10.0F, 3.0F);
		armorWaist.addChild(tabardLegs_back);
		setRotationAngle(tabardLegs_back, 0.0F, 180.0F, 0.0F);
		tabardLegs_back.cubeList.add(new ModelBox(tabardLegs_back, 108, 8, -4.5F, 0.0F, -0.5F, 9, 12, 1, 0.0F, false));

		tabardLegs_front = new RendererModel(this);
		tabardLegs_front.setRotationPoint(0.0F, 10.0F, -3.0F);
		armorWaist.addChild(tabardLegs_front);
		tabardLegs_front.cubeList.add(new ModelBox(tabardLegs_front, 108, 8, -4.5F, 0.0F, -0.5F, 9, 12, 1, 0.0F, false));

		armorLeftArm = new RendererModel(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 64, 41, -1.5F, -2.5F, -2.5F, 5, 13, 5, 0.0F, false));

		armorRightArm = new RendererModel(this);
		armorRightArm.setRotationPoint(-1.5F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		setRotationAngle(armorRightArm, 0.0F, 180.0F, 0.0F);
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 64, 41, -3.0F, -2.5F, -2.5F, 5, 13, 5, 0.0F, false));

		armorLeftLeg = new RendererModel(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 104, 36, 1.5F, -0.5F, -3.0F, 6, 11, 6, 0.0F, false));

		armorLeftBoot = new RendererModel(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 108, 53, 1.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));

		armorRightLeg = new RendererModel(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 104, 36, -7.5F, -0.5F, -3.0F, 6, 11, 6, 0.0F, false));

		armorRightBoot = new RendererModel(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 108, 53, -6.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));
		//To here
	}
	
	@Override
	public void setRotationAngles(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		this.tabardLegs_front.rotateAngleX = Math.min(this.bipedLeftLeg.rotateAngleX, this.bipedRightLeg.rotateAngleX);
		this.tabardLegs_back.rotateAngleX = Math.min(this.bipedLeftLeg.rotateAngleX, this.bipedRightLeg.rotateAngleX);
	}
}