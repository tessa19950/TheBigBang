package com.homebrewCult.TheBigBang.items.model;
// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class NightshiftArmorModel extends BigBangArmorModel {
	public final RendererModel armorHead;
	public final RendererModel armorTorso;
	public final RendererModel armorWaist;
	public final RendererModel armorLeftArm;
	public final RendererModel armorRightArm;
	public final RendererModel armorLeftLeg;
	public final RendererModel armorLeftBoot;
	public final RendererModel armorRightLeg;
	public final RendererModel armorRightBoot;

	public NightshiftArmorModel() {	
		super();
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new RendererModel(this);
		armorHead.setRotationPoint(0.0F, -8.5F, 0.0F);
		bipedHead.addChild(armorHead);
		setRotationAngle(armorHead, -5.0F, 0.0F, 0.0F);
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 6, -5.0F, -1.0F, -4.5F, 10, 4, 10, 0.0F, false));
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 0, -2.5F, -2.0F, -0.5F, 5, 1, 5, 0.0F, false));

		armorTorso = new RendererModel(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 20, -5.5F, -0.5F, -3.5F, 11, 6, 7, 0.0F, false));
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 33, -5.0F, 5.5F, -3.0F, 10, 5, 6, 0.0F, false));

		armorWaist = new RendererModel(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.cubeList.add(new ModelBox(armorWaist, 64, 44, -4.5F, 8.5F, -2.5F, 9, 4, 5, 0.0F, false));

		armorLeftArm = new RendererModel(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 108, 0, -1.5F, 3.0F, -2.5F, 5, 6, 5, 0.0F, false));

		armorRightArm = new RendererModel(this);
		armorRightArm.setRotationPoint(-1.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		setRotationAngle(armorRightArm, 0.0F, 180.0F, 0.0F);
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 108, 0, -2.5F, 3.0F, -2.5F, 5, 6, 5, 0.0F, false));

		armorLeftLeg = new RendererModel(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 106, 16, 2.0F, -0.5F, -3.0F, 5, 5, 6, 0.0F, false));
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 108, 38, 1.5F, 4.5F, -2.5F, 5, 4, 5, 0.0F, false));

		armorLeftBoot = new RendererModel(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 90, 53, 1.5F, 9.0F, -2.5F, 5, 3, 5, 0.0F, false));
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 104, 56, 1.0F, 8.5F, -3.0F, 6, 2, 6, 0.0F, false));

		armorRightLeg = new RendererModel(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 106, 27, -7.0F, -0.5F, -3.0F, 5, 5, 6, 0.0F, false));
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 108, 38, -6.5F, 4.5F, -2.5F, 5, 4, 5, 0.0F, false));

		armorRightBoot = new RendererModel(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 90, 53, -6.5F, 9.0F, -2.5F, 5, 3, 5, 0.0F, false));
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 72, 56, -7.0F, 8.5F, -3.0F, 6, 2, 6, 0.0F, false));
		//To here
	}
}