package com.homebrewCult.TheBigBang.items.model;
// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class LegolierArmorModel extends BigBangArmorModel {	
	public final RendererModel hatFeatherPivot;

	public static final LegolierArmorModel INSTANCE = new LegolierArmorModel();

	public LegolierArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead.setRotationPoint(0.0F, -6.0F, 0.5F);
		bipedHead.addChild(armorHead);
		setRotationAngle(armorHead, -10.0F, 0.0F, 0.0F);
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 0, -4.5F, -4.0F, -5.0F, 9, 5, 9, 0.0F, false));
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 28, -6.0F, -3.0F, -6.5F, 12, 4, 12, 0.0F, false));

		hatFeatherPivot = new RendererModel(this);
		hatFeatherPivot.setRotationPoint(4.5F, -2.0F, 0.5F);
		armorHead.addChild(hatFeatherPivot);
		setRotationAngle(hatFeatherPivot, 0.0F, 0.0F, 10.0F);
		hatFeatherPivot.cubeList.add(new ModelBox(hatFeatherPivot, 70, 31, 0.0F, -5.0F, -1.5F, 0, 6, 3, 0.0F, false));

		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.cubeList.add(new ModelBox(armorTorso, 96, 8, -5.0F, -0.5F, -3.0F, 10, 14, 6, 0.0F, false));

		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.cubeList.add(new ModelBox(armorWaist, 100, 28, -4.5F, 8.0F, -2.5F, 9, 4, 5, 0.0F, false));

		armorLeftArm.setRotationPoint(1.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 87, 45, -2.5F, -2.5F, -2.5F, 5, 12, 5, 0.0F, false));

		armorRightArm.setRotationPoint(-1.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		setRotationAngle(armorRightArm, 0.0F, 180.0F, 0.0F);
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 87, 45, -2.5F, -2.5F, -2.5F, 5, 12, 5, 0.0F, false));

		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 108, 40, 1.5F, -0.5F, -2.5F, 5, 9, 5, 0.0F, false));

		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 64, 55, 1.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 64, 47, 1.0F, 8.0F, -3.0F, 6, 2, 6, 0.0F, false));

		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 108, 40, -6.5F, -0.5F, -2.5F, 5, 9, 5, 0.0F, false));

		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 64, 55, -6.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 64, 47, -7.0F, 8.0F, -3.0F, 6, 2, 6, 0.0F, false));
		//To here
	}
}