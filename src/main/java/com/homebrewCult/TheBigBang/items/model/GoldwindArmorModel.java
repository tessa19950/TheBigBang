package com.homebrewCult.TheBigBang.items.model;
// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;

public class GoldwindArmorModel extends BigBangArmorModel {
	public final RendererModel armorHead;
	public final RendererModel armorTorso;
	public final RendererModel armorWaist;
	public final RendererModel armorLeftArm;
	public final RendererModel armorRightArm;
	public final RendererModel armorLeftLeg;
	public final RendererModel armorLeftBoot;
	public final RendererModel armorRightLeg;
	public final RendererModel armorRightBoot;
	public final RendererModel goldwind_overhang_back;
	public final RendererModel goldwind_overhang_front;

	public GoldwindArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new RendererModel(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 0, -4.5F, -9.5F, -4.5F, 9, 5, 9, 0.0F, false));

		armorTorso = new RendererModel(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 14, -5.0F, -0.5F, -3.0F, 10, 9, 6, 0.0F, false));

		armorWaist = new RendererModel(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.cubeList.add(new ModelBox(armorWaist, 64, 29, -4.5F, 8.0F, -2.5F, 9, 4, 5, 0.0F, false));

		goldwind_overhang_front = new RendererModel(this);
		goldwind_overhang_front.setRotationPoint(-0.5F, 10.0F, -2.5F);
		armorWaist.addChild(goldwind_overhang_front);
		goldwind_overhang_front.cubeList.add(new ModelBox(goldwind_overhang_front, 64, 38, -5.0F, 0.0F, -0.5F, 10, 8, 5, 0.0F, false));

		goldwind_overhang_back = new RendererModel(this);
		goldwind_overhang_back.setRotationPoint(-0.5F, 10.0F, 2.5F);
		armorWaist.addChild(goldwind_overhang_back);
		goldwind_overhang_back.cubeList.add(new ModelBox(goldwind_overhang_back, 64, 51, -5.0F, 0.0F, -4.5F, 10, 8, 5, 0.0F, false));

		armorLeftArm = new RendererModel(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 108, 0, -1.5F, 3.5F, -2.5F, 5, 5, 5, 0.0F, false));
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 110, 10, 1.0F, 3.0F, -3.0F, 3, 6, 6, 0.0F, false));

		armorRightArm = new RendererModel(this);
		armorRightArm.setRotationPoint(-1.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		setRotationAngle(armorRightArm, 0.0F, 180.0F, 0.0F);
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 108, 0, -2.5F, 3.5F, -2.5F, 5, 5, 5, 0.0F, false));
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 110, 10, 0.0F, 3.0F, -3.0F, 3, 6, 6, 0.0F, false));

		armorLeftLeg = new RendererModel(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 108, 43, 1.5F, -0.5F, -2.5F, 5, 6, 5, 0.0F, false));

		armorLeftBoot = new RendererModel(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 108, 54, 1.5F, 7.5F, -2.5F, 5, 5, 5, 0.0F, false));
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 98, 59, 2.5F, 9.5F, -4.5F, 3, 3, 2, 0.0F, false));
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 104, 56, 2.5F, 10.5F, -4.5F, 3, 0, 2, 0.0F, false));

		armorRightLeg = new RendererModel(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 108, 43, -6.5F, -0.5F, -2.5F, 5, 6, 5, 0.0F, false));

		armorRightBoot = new RendererModel(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 108, 54, -6.5F, 7.5F, -2.5F, 5, 5, 5, 0.0F, false));
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 98, 59, -5.5F, 9.5F, -4.5F, 3, 3, 2, 0.0F, false));
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 104, 56, -5.5F, 10.5F, -4.5F, 3, 0, 2, 0.0F, false));
		//To here
	}
	
	@Override
	public void setRotationAngles(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		this.goldwind_overhang_front.rotateAngleX = Math.min(this.bipedLeftLeg.rotateAngleX, this.bipedRightLeg.rotateAngleX);
		this.goldwind_overhang_back.rotateAngleX = Math.max(this.bipedLeftLeg.rotateAngleX, this.bipedRightLeg.rotateAngleX);
	}
}