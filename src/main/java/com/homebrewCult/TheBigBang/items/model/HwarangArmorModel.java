package com.homebrewCult.TheBigBang.items.model;
// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class HwarangArmorModel extends BigBangArmorModel {
	public final RendererModel armorHead;
	public final RendererModel armorTorso;
	public final RendererModel armorWaist;
	public final RendererModel armorRightArm;
	public final RendererModel armorLeftArm;
	public final RendererModel armorRightLeg;
	public final RendererModel armorRightBoot;
	public final RendererModel armorLeftLeg;
	public final RendererModel armorLeftBoot;
	private final RendererModel hwarang_shoulder_pivot;
	private final RendererModel hwarang_shoulder_pivot2;

	public static final HwarangArmorModel INSTANCE = new HwarangArmorModel();

	public HwarangArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new RendererModel(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 0, -4.5F, -9.0F, -4.5F, 9, 11, 9, 0.0F, false));

		armorTorso = new RendererModel(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 20, -4.5F, -0.5F, -3.0F, 9, 6, 6, 0.0F, false));

		hwarang_shoulder_pivot = new RendererModel(this);
		hwarang_shoulder_pivot.setRotationPoint(-6.0F, 1.0F, 0.0F);
		armorTorso.addChild(hwarang_shoulder_pivot);
		setRotationAngle(hwarang_shoulder_pivot, 0.0F, 180.0F, -15.0F);
		hwarang_shoulder_pivot.cubeList.add(new ModelBox(hwarang_shoulder_pivot, 106, 0, -2.5F, -2.5F, -3.0F, 5, 8, 6, 0.0F, false));

		hwarang_shoulder_pivot2 = new RendererModel(this);
		hwarang_shoulder_pivot2.setRotationPoint(6.0F, 1.0F, 0.0F);
		armorTorso.addChild(hwarang_shoulder_pivot2);
		setRotationAngle(hwarang_shoulder_pivot2, 0.0F, 0.0F, 15.0F);
		hwarang_shoulder_pivot2.cubeList.add(new ModelBox(hwarang_shoulder_pivot2, 106, 0, -2.5F, -2.5F, -3.0F, 5, 8, 6, 0.0F, false));

		armorWaist = new RendererModel(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.cubeList.add(new ModelBox(armorWaist, 64, 32, -4.0F, 5.5F, -2.5F, 8, 7, 5, 0.0F, false));

		armorLeftArm = new RendererModel(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 108, 46, -1.5F, -2.5F, -2.5F, 5, 10, 5, 0.0F, false));

		armorRightArm = new RendererModel(this);
		armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 108, 46, -3.5F, -2.5F, -2.5F, 5, 10, 5, 0.0F, false));

		armorLeftLeg = new RendererModel(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 98, 30, -2.5F, -2.5F, -3.0F, 9, 10, 6, 0.0F, false));

		armorLeftBoot = new RendererModel(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 64, 44, 1.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));

		armorRightLeg = new RendererModel(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 98, 14, -6.5F, -2.5F, -3.0F, 9, 10, 6, 0.0F, false));

		armorRightBoot = new RendererModel(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 64, 44, -6.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));
		//To here
	}
}