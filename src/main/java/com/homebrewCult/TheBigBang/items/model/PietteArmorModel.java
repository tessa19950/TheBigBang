package com.homebrewCult.TheBigBang.items.model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PietteArmorModel extends BigBangArmorModel {
	public final ModelRenderer armorHead;
	public final ModelRenderer piette_wing_pivot_left;
	public final ModelRenderer piette_wing_pivot_right;
	public final ModelRenderer armorTorso;
	public final ModelRenderer armorWaist;
	public final ModelRenderer armorLeftArm;
	public final ModelRenderer piette_left_pivot;
	public final ModelRenderer piette_right_pivot;
	public final ModelRenderer armorRightArm;
	public final ModelRenderer armorLeftLeg;
	public final ModelRenderer armorLeftBoot;
	public final ModelRenderer armorRightLeg;
	public final ModelRenderer armorRightBoot;

	public static final PietteArmorModel INSTANCE = new PietteArmorModel();

	public PietteArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new ModelRenderer(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.setTextureOffset(84, 49).addBox(-4.5F, -8.5F, -4.5F, 9.0F, 6.0F, 9.0F, 0.0F, false);
		armorHead.setTextureOffset(114, 51).addBox(-1.5F, -13.5F, 0.5F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		armorHead.setTextureOffset(120, 60).addBox(-1.0F, -10.5F, 1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		piette_wing_pivot_left = new ModelRenderer(this);
		piette_wing_pivot_left.setRotationPoint(5.0F, -7.0F, 0.0F);
		armorHead.addChild(piette_wing_pivot_left);
		piette_wing_pivot_left.setTextureOffset(114, 35).addBox(0.0F, -3.5F, -3.5F, 0.0F, 9.0F, 7.0F, 0.0F, false);

		piette_wing_pivot_right = new ModelRenderer(this);
		piette_wing_pivot_right.setRotationPoint(-5.0F, -7.0F, 0.0F);
		armorHead.addChild(piette_wing_pivot_right);
		piette_wing_pivot_right.setTextureOffset(114, 35).addBox(0.0F, -3.5F, -3.5F, 0.0F, 9.0F, 7.0F, 0.0F, false);

		armorTorso = new ModelRenderer(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.setTextureOffset(64, 0).addBox(-5.0F, -0.5F, -3.0F, 10.0F, 13.0F, 6.0F, 0.0F, false);

		armorWaist = new ModelRenderer(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.setTextureOffset(64, 19).addBox(-4.5F, 8.5F, -2.5F, 9.0F, 4.0F, 5.0F, 0.0F, false);

		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.setTextureOffset(64, 47).addBox(-1.5F, -2.0F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		piette_left_pivot = new ModelRenderer(this);
		piette_left_pivot.setRotationPoint(2.0F, 0.0F, 0.0F);
		armorLeftArm.addChild(piette_left_pivot);
		piette_left_pivot.setTextureOffset(64, 36).addBox(-2.0F, -2.5F, -3.0F, 4.0F, 5.0F, 6.0F, 0.0F, false);

		piette_right_pivot = new ModelRenderer(this);
		piette_right_pivot.setRotationPoint(-2.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(piette_right_pivot);
		setRotationAngle(piette_right_pivot, 0.0F, 3.1416F, 0.0F);
		piette_right_pivot.setTextureOffset(64, 36).addBox(-2.0F, -2.5F, -3.0F, 4.0F, 5.0F, 6.0F, 0.0F, false);

		armorRightArm = new ModelRenderer(this);
		armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.setTextureOffset(64, 47).addBox(-3.5F, -2.0F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		armorLeftLeg = new ModelRenderer(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.setTextureOffset(106, 9).addBox(2.0F, -0.5F, -3.0F, 5.0F, 6.0F, 6.0F, 0.0F, false);
		armorLeftLeg.setTextureOffset(107, 0).addBox(1.5F, 5.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		armorLeftBoot = new ModelRenderer(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.setTextureOffset(106, 21).addBox(2.0F, 8.5F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);

		armorRightLeg = new ModelRenderer(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.setTextureOffset(106, 9).addBox(-7.0F, -0.5F, -3.0F, 5.0F, 6.0F, 6.0F, 0.0F, false);
		armorRightLeg.setTextureOffset(107, 0).addBox(-6.5F, 5.0F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		armorRightBoot = new ModelRenderer(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.setTextureOffset(106, 21).addBox(-7.0F, 8.5F, -3.0F, 5.0F, 4.0F, 6.0F, 0.0F, false);
		//To here
	}
}