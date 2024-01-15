package com.homebrewCult.TheBigBang.items.model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class JangoonArmorModel extends BigBangArmorModel {
	public final ModelRenderer armorHead;
	public final ModelRenderer jangoon_feather_pivot;
	public final ModelRenderer bone;
	public final ModelRenderer armorTorso;
	public final ModelRenderer jangoon_left_waistpad_pivot;
	public final ModelRenderer jangoon_left_waistpad_pivot2;
	public final ModelRenderer armorWaist;
	public final ModelRenderer armorLeftArm;
	public final ModelRenderer jangoon_left_shoulder_pivot;
	public final ModelRenderer armorRightArm;
	public final ModelRenderer jangoon_right_shoulder_pivot;
	public final ModelRenderer armorLeftLeg;
	public final ModelRenderer armorLeftBoot;
	public final ModelRenderer armorRightLeg;
	public final ModelRenderer armorRightBoot;

	public static final JangoonArmorModel INSTANCE = new JangoonArmorModel();

	public JangoonArmorModel() {
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new ModelRenderer(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.setTextureOffset(64, 3).addBox(-4.5F, -8.5F, -4.5F, 9.0F, 6.0F, 9.0F, 0.0F, false);
		armorHead.setTextureOffset(103, 4).addBox(-1.5F, -9.5F, -4.0F, 3.0F, 1.0F, 8.0F, 0.0F, false);

		jangoon_feather_pivot = new ModelRenderer(this);
		jangoon_feather_pivot.setRotationPoint(0.0F, -11.0F, 0.0F);
		armorHead.addChild(jangoon_feather_pivot);
		setRotationAngle(jangoon_feather_pivot, -0.2618F, 0.0F, 0.0F);
		jangoon_feather_pivot.setTextureOffset(91, 0).addBox(-1.0F, -1.5F, -4.0F, 2.0F, 4.0F, 8.0F, 0.0F, false);
		jangoon_feather_pivot.setTextureOffset(104, 4).addBox(-1.0F, 0.5F, -4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -12.0F, -0.5F);
		armorHead.addChild(bone);
		setRotationAngle(bone, -0.3491F, 0.0F, 0.0F);


		armorTorso = new ModelRenderer(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.setTextureOffset(64, 37).addBox(-5.0F, -0.5F, -3.0F, 10.0F, 12.0F, 6.0F, 0.0F, false);

		jangoon_left_waistpad_pivot = new ModelRenderer(this);
		jangoon_left_waistpad_pivot.setRotationPoint(3.5F, 10.5F, 0.0F);
		armorTorso.addChild(jangoon_left_waistpad_pivot);
		setRotationAngle(jangoon_left_waistpad_pivot, 0.0F, 0.0F, -0.2618F);
		jangoon_left_waistpad_pivot.setTextureOffset(64, 24).addBox(-2.0F, -2.0F, -3.5F, 4.0F, 6.0F, 7.0F, 0.0F, false);

		jangoon_left_waistpad_pivot2 = new ModelRenderer(this);
		jangoon_left_waistpad_pivot2.setRotationPoint(-3.5F, 10.5F, 0.0F);
		armorTorso.addChild(jangoon_left_waistpad_pivot2);
		setRotationAngle(jangoon_left_waistpad_pivot2, 0.0F, 3.1416F, 0.2618F);
		jangoon_left_waistpad_pivot2.setTextureOffset(64, 24).addBox(-2.0F, -2.0F, -3.5F, 4.0F, 6.0F, 7.0F, 0.0F, false);

		armorWaist = new ModelRenderer(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.setTextureOffset(64, 55).addBox(-4.5F, 8.5F, -2.5F, 9.0F, 4.0F, 5.0F, 0.0F, false);

		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.setTextureOffset(86, 20).addBox(-1.5F, -2.5F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		jangoon_left_shoulder_pivot = new ModelRenderer(this);
		jangoon_left_shoulder_pivot.setRotationPoint(2.0F, -0.5F, 0.0F);
		armorLeftArm.addChild(jangoon_left_shoulder_pivot);
		jangoon_left_shoulder_pivot.setTextureOffset(101, 14).addBox(-2.0F, -2.5F, -3.0F, 4.0F, 5.0F, 6.0F, 0.0F, false);
		jangoon_left_shoulder_pivot.setTextureOffset(101, 14).addBox(-2.0F, -2.5F, -3.0F, 4.0F, 5.0F, 6.0F, 0.0F, false);

		armorRightArm = new ModelRenderer(this);
		armorRightArm.setRotationPoint(-2.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.setTextureOffset(86, 20).addBox(-1.5F, -2.5F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		jangoon_right_shoulder_pivot = new ModelRenderer(this);
		jangoon_right_shoulder_pivot.setRotationPoint(0.0F, -0.5F, 0.0F);
		armorRightArm.addChild(jangoon_right_shoulder_pivot);
		setRotationAngle(jangoon_right_shoulder_pivot, 0.0F, 3.1416F, 0.0F);
		jangoon_right_shoulder_pivot.setTextureOffset(101, 14).addBox(-2.0F, -2.5F, -3.0F, 4.0F, 5.0F, 6.0F, 0.0F, false);

		armorLeftLeg = new ModelRenderer(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.setTextureOffset(106, 41).addBox(2.0F, -0.5F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);
		armorLeftLeg.setTextureOffset(91, 53).addBox(1.5F, 7.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F, false);

		armorLeftBoot = new ModelRenderer(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.setTextureOffset(108, 55).addBox(1.5F, 8.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		armorRightLeg = new ModelRenderer(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.setTextureOffset(106, 27).addBox(-7.0F, -0.5F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);
		armorRightLeg.setTextureOffset(91, 53).addBox(-6.5F, 7.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F, false);

		armorRightBoot = new ModelRenderer(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.setTextureOffset(108, 55).addBox(-6.5F, 8.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		//To here
	}
}