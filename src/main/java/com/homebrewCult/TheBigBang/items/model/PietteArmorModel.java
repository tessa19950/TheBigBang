package com.homebrewCult.TheBigBang.items.model;
// Made with Blockbench 3.5.1
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class PietteArmorModel extends BigBangArmorModel {
	public final RendererModel armorHead;
	public final RendererModel piette_wing_pivot_left;
	public final RendererModel piette_wing_pivot_right;
	public final RendererModel armorTorso;
	public final RendererModel armorWaist;
	public final RendererModel armorLeftArm;
	public final RendererModel piette_left_pivot;
	public final RendererModel piette_right_pivot;
	public final RendererModel armorRightArm;
	public final RendererModel armorLeftLeg;
	public final RendererModel armorLeftBoot;
	public final RendererModel armorRightLeg;
	public final RendererModel armorRightBoot;

	public PietteArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new RendererModel(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.cubeList.add(new ModelBox(armorHead, 84, 49, -4.5F, -8.5F, -4.5F, 9, 6, 9, 0.0F, false));
		armorHead.cubeList.add(new ModelBox(armorHead, 114, 51, -1.5F, -13.5F, 0.5F, 3, 3, 4, 0.0F, false));
		armorHead.cubeList.add(new ModelBox(armorHead, 120, 60, -1.0F, -10.5F, 1.0F, 2, 2, 2, 0.0F, false));

		piette_wing_pivot_left = new RendererModel(this);
		piette_wing_pivot_left.setRotationPoint(5.0F, -7.0F, 0.0F);
		armorHead.addChild(piette_wing_pivot_left);
		piette_wing_pivot_left.cubeList.add(new ModelBox(piette_wing_pivot_left, 114, 35, 0.0F, -3.5F, -3.5F, 0, 9, 7, 0.0F, false));

		piette_wing_pivot_right = new RendererModel(this);
		piette_wing_pivot_right.setRotationPoint(-5.0F, -7.0F, 0.0F);
		armorHead.addChild(piette_wing_pivot_right);
		piette_wing_pivot_right.cubeList.add(new ModelBox(piette_wing_pivot_right, 114, 35, 0.0F, -3.5F, -3.5F, 0, 9, 7, 0.0F, false));

		armorTorso = new RendererModel(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.cubeList.add(new ModelBox(armorTorso, 64, 0, -5.0F, -0.5F, -3.0F, 10, 13, 6, 0.0F, false));

		armorWaist = new RendererModel(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.cubeList.add(new ModelBox(armorWaist, 64, 19, -4.5F, 8.5F, -2.5F, 9, 4, 5, 0.0F, false));

		armorLeftArm = new RendererModel(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.cubeList.add(new ModelBox(armorLeftArm, 64, 47, -1.5F, -2.0F, -2.5F, 5, 12, 5, 0.0F, false));

		piette_left_pivot = new RendererModel(this);
		piette_left_pivot.setRotationPoint(2.0F, 0.0F, 0.0F);
		armorLeftArm.addChild(piette_left_pivot);
		piette_left_pivot.cubeList.add(new ModelBox(piette_left_pivot, 64, 36, -2.0F, -2.5F, -3.0F, 4, 5, 6, 0.0F, false));

		piette_right_pivot = new RendererModel(this);
		piette_right_pivot.setRotationPoint(-2.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(piette_right_pivot);
		setRotationAngle(piette_right_pivot, 0.0F, 180.0F, 0.0F);
		piette_right_pivot.cubeList.add(new ModelBox(piette_right_pivot, 64, 36, -2.0F, -2.5F, -3.0F, 4, 5, 6, 0.0F, false));

		armorRightArm = new RendererModel(this);
		armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.cubeList.add(new ModelBox(armorRightArm, 64, 47, -3.5F, -2.0F, -2.5F, 5, 12, 5, 0.0F, false));

		armorLeftLeg = new RendererModel(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 106, 9, 2.0F, -0.5F, -3.0F, 5, 6, 6, 0.0F, false));
		armorLeftLeg.cubeList.add(new ModelBox(armorLeftLeg, 107, 0, 1.5F, 5.0F, -2.5F, 5, 4, 5, 0.0F, false));

		armorLeftBoot = new RendererModel(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.cubeList.add(new ModelBox(armorLeftBoot, 106, 21, 2.0F, 8.5F, -3.0F, 5, 4, 6, 0.0F, false));

		armorRightLeg = new RendererModel(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 106, 9, -7.0F, -0.5F, -3.0F, 5, 6, 6, 0.0F, false));
		armorRightLeg.cubeList.add(new ModelBox(armorRightLeg, 107, 0, -6.5F, 5.0F, -2.5F, 5, 4, 5, 0.0F, false));

		armorRightBoot = new RendererModel(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.cubeList.add(new ModelBox(armorRightBoot, 106, 21, -7.0F, 8.5F, -3.0F, 5, 4, 6, 0.0F, false));
		//To here
	}
}