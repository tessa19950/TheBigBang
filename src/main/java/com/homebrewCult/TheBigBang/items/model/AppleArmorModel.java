package com.homebrewCult.TheBigBang.items.model;
// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.LivingEntity;

public class AppleArmorModel extends BipedModel<LivingEntity> {
	public final RendererModel armorHead;
	public final RendererModel armorTorso;
	public final RendererModel armorWaist;
	public final RendererModel armorRightArm;
	public final RendererModel armorLeftArm;
	public final RendererModel armorRightLeg;
	public final RendererModel armorRightBoot;
	public final RendererModel armorLeftLeg;
	public final RendererModel armorLeftBoot;

	public static final AppleArmorModel INSTANCE = new AppleArmorModel();

	public AppleArmorModel() {	
		super(1f, 0.0F, 128, 64);
		
		//Paste the Blockbench exported file from here
		armorHead = new RendererModel(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 0, -5.5F, -9.5F, -5.5F, 11, 10, 11, 0.0F, false));
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 48, -4.5F, -18.5F, 0.0F, 9, 9, 0, 0.0F, false));

		armorTorso = new RendererModel(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 22, -4.5F, 0.0F, -3.0F, 9, 10, 6, 0.0F, false));

		armorWaist = new RendererModel(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.cubeList.add(new ModelBox(armorWaist, 64, 38, -4.5F, 10.0F, -3.0F, 9, 3, 6, 0.0F, false));

		armorLeftArm = new RendererModel(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 108, 0, -1.5F, -2.5F, -2.5F, 5, 13, 5, 0.0F, false));

		armorRightArm = new RendererModel(this);
		armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 108, 0, -3.5F, -2.5F, -2.5F, 5, 13, 5, 0.0F, false));

		armorLeftLeg = new RendererModel(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 108, 22, 1.5F, -0.5F, -2.5F, 5, 12, 5, 0.0F, false));

		armorLeftBoot = new RendererModel(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);

		armorRightLeg = new RendererModel(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 108, 22, -6.5F, -0.5F, -2.5F, 5, 12, 5, 0.0F, false));

		armorRightBoot = new RendererModel(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		//To here
	}

	@Override
	public void render(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}