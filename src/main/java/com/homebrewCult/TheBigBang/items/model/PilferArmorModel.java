package com.homebrewCult.TheBigBang.items.model;
// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class PilferArmorModel extends BigBangArmorModel {
	public final RendererModel armorHead;
	public final RendererModel pilfer_tip_pivot;
	public final RendererModel armorTorso;
	public final RendererModel armorWaist;
	public final RendererModel armorLeftArm;
	public final RendererModel armorRightArm;
	public final RendererModel armorLeftLeg;
	public final RendererModel armorLeftBoot;
	public final RendererModel armorRightLeg;
	public final RendererModel armorRightBoot;

	public PilferArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new RendererModel(this);
		armorHead.setRotationPoint(0.0F, -5.5F, 0.0F);
		bipedHead.addChild(armorHead);
		setRotationAngle(armorHead, -10.0F, 0.0F, 0.0F);
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 4, -4.5F, -2.5F, -4.5F, 9, 4, 9, 0.0F, false));
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 17, -5.0F, -2.0F, -5.0F, 10, 4, 10, 0.0F, false));

		pilfer_tip_pivot = new RendererModel(this);
		pilfer_tip_pivot.setRotationPoint(0.0F, -2.5F, -3.5F);
		armorHead.addChild(pilfer_tip_pivot);
		setRotationAngle(pilfer_tip_pivot, -15.0F, 0.0F, 0.0F);
		pilfer_tip_pivot.cubeList.add(new ModelBox(pilfer_tip_pivot, 64, 0, -4.0F, -4.0F, 0.0F, 8, 4, 0, 0.0F, false));

		armorTorso = new RendererModel(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 37, -5.0F, -0.5F, -3.0F, 10, 12, 6, 0.0F, false));

		armorWaist = new RendererModel(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.cubeList.add(new ModelBox(armorWaist, 64, 55, -4.5F, 8.5F, -2.5F, 9, 4, 5, 0.0F, false));

		armorLeftArm = new RendererModel(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 108, 28, -1.5F, -2.0F, -2.5F, 5, 12, 5, 0.0F, false));

		armorRightArm = new RendererModel(this);
		armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 108, 28, -3.5F, -2.0F, -2.5F, 5, 12, 5, 0.0F, false));

		armorLeftBoot = new RendererModel(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 108, 45, 1.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));

		armorLeftLeg = new RendererModel(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 108, 0, 1.5F, -0.5F, -2.5F, 5, 9, 5, 0.0F, false));

		armorRightLeg = new RendererModel(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 108, 14, -6.5F, -0.5F, -2.5F, 5, 9, 5, 0.0F, false));

		armorRightBoot = new RendererModel(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 108, 45, -6.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));
		//To here
	}
}