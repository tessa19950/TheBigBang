package com.homebrewCult.TheBigBang.items.model;
// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class JangoonArmorModel extends BigBangArmorModel {
	public final RendererModel armorHead;
	public final RendererModel jangoon_feather_pivot;
	public final RendererModel bone;
	public final RendererModel armorTorso;
	public final RendererModel jangoon_left_waistpad_pivot;
	public final RendererModel jangoon_left_waistpad_pivot2;
	public final RendererModel armorWaist;
	public final RendererModel armorLeftArm;
	public final RendererModel jangoon_left_shoulder_pivot;
	public final RendererModel armorRightArm;
	public final RendererModel jangoon_right_shoulder_pivot;
	public final RendererModel armorLeftLeg;
	public final RendererModel armorLeftBoot;
	public final RendererModel armorRightLeg;
	public final RendererModel armorRightBoot;

	public static final JangoonArmorModel INSTANCE = new JangoonArmorModel();

	public JangoonArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new RendererModel(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.cubeList.add(new ModelBox(armorHead, 64, 3, -4.5F, -8.5F, -4.5F, 9, 6, 9, 0.0F, false));
		armorHead.cubeList.add(new ModelBox(armorHead, 103, 4, -1.5F, -9.5F, -4.0F, 3, 1, 8, 0.0F, false));

		jangoon_feather_pivot = new RendererModel(this);
		jangoon_feather_pivot.setRotationPoint(0.0F, -11.0F, 0.0F);
		armorHead.addChild(jangoon_feather_pivot);
		setRotationAngle(jangoon_feather_pivot, -15.0F, 0.0F, 0.0F);
		jangoon_feather_pivot.cubeList.add(new ModelBox(jangoon_feather_pivot, 91, 0, -1.0F, -1.5F, -4.0F, 2, 4, 8, 0.0F, false));
		jangoon_feather_pivot.cubeList.add(new ModelBox(jangoon_feather_pivot, 104, 4, -1.0F, 0.5F, -4.0F, 2, 2, 1, 0.0F, false));

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, -12.0F, -0.5F);
		armorHead.addChild(bone);
		setRotationAngle(bone, -20.0F, 0.0F, 0.0F);
		

		armorTorso = new RendererModel(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 37, -5.0F, -0.5F, -3.0F, 10, 12, 6, 0.0F, false));

		jangoon_left_waistpad_pivot = new RendererModel(this);
		jangoon_left_waistpad_pivot.setRotationPoint(3.5F, 10.5F, 0.0F);
		armorTorso.addChild(jangoon_left_waistpad_pivot);
		setRotationAngle(jangoon_left_waistpad_pivot, 0.0F, 0.0F, -15.0F);
		jangoon_left_waistpad_pivot.cubeList.add(new ModelBox(jangoon_left_waistpad_pivot, 64, 24, -2.0F, -2.0F, -3.5F, 4, 6, 7, 0.0F, false));

		jangoon_left_waistpad_pivot2 = new RendererModel(this);
		jangoon_left_waistpad_pivot2.setRotationPoint(-3.5F, 10.5F, 0.0F);
		armorTorso.addChild(jangoon_left_waistpad_pivot2);
		setRotationAngle(jangoon_left_waistpad_pivot2, 0.0F, 180.0F, 15.0F);
		jangoon_left_waistpad_pivot2.cubeList.add(new ModelBox(jangoon_left_waistpad_pivot2, 64, 24, -2.0F, -2.0F, -3.5F, 4, 6, 7, 0.0F, false));

		armorWaist = new RendererModel(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.cubeList.add(new ModelBox(armorWaist, 64, 55, -4.5F, 8.5F, -2.5F, 9, 4, 5, 0.0F, false));

		armorLeftArm = new RendererModel(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 86, 20, -1.5F, -2.5F, -2.5F, 5, 12, 5, 0.0F, false));

		jangoon_left_shoulder_pivot = new RendererModel(this);
		jangoon_left_shoulder_pivot.setRotationPoint(2.0F, -0.5F, 0.0F);
		armorLeftArm.addChild(jangoon_left_shoulder_pivot);
		jangoon_left_shoulder_pivot.cubeList.add(new ModelBox(jangoon_left_shoulder_pivot, 101, 14, -2.0F, -2.5F, -3.0F, 4, 5, 6, 0.0F, false));
		jangoon_left_shoulder_pivot.cubeList.add(new ModelBox(jangoon_left_shoulder_pivot, 101, 14, -2.0F, -2.5F, -3.0F, 4, 5, 6, 0.0F, false));

		armorRightArm = new RendererModel(this);
		armorRightArm.setRotationPoint(-2.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 86, 20, -1.5F, -2.5F, -2.5F, 5, 12, 5, 0.0F, false));

		jangoon_right_shoulder_pivot = new RendererModel(this);
		jangoon_right_shoulder_pivot.setRotationPoint(0.0F, -0.5F, 0.0F);
		armorRightArm.addChild(jangoon_right_shoulder_pivot);
		setRotationAngle(jangoon_right_shoulder_pivot, 0.0F, 180.0F, 0.0F);
		jangoon_right_shoulder_pivot.cubeList.add(new ModelBox(jangoon_right_shoulder_pivot, 101, 14, -2.0F, -2.5F, -3.0F, 4, 5, 6, 0.0F, false));

		armorLeftLeg = new RendererModel(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 106, 41, 2.0F, -0.5F, -3.0F, 5, 8, 6, 0.0F, false));
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 91, 53, 1.5F, 7.0F, -2.5F, 5, 2, 5, 0.0F, false));

		armorLeftBoot = new RendererModel(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 108, 55, 1.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));

		armorRightLeg = new RendererModel(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 106, 27, -7.0F, -0.5F, -3.0F, 5, 8, 6, 0.0F, false));
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 91, 53, -6.5F, 7.0F, -2.5F, 5, 2, 5, 0.0F, false));

		armorRightBoot = new RendererModel(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 108, 55, -6.5F, 8.5F, -2.5F, 5, 4, 5, 0.0F, false));
		//To here
	}
}