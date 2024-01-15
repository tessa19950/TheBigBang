package com.homebrewCult.TheBigBang.items.model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class HwarangArmorModel extends BigBangArmorModel {
	public final ModelRenderer armorHead;
	public final ModelRenderer armorTorso;
	public final ModelRenderer hwarang_shoulder_pivot;
	public final ModelRenderer hwarang_shoulder_pivot2;
	public final ModelRenderer armorWaist;
	public final ModelRenderer armorLeftArm;
	public final ModelRenderer armorRightArm;
	public final ModelRenderer armorLeftLeg;
	public final ModelRenderer armorLeftBoot;
	public final ModelRenderer armorRightLeg;
	public final ModelRenderer armorRightBoot;

	public static final HwarangArmorModel INSTANCE = new HwarangArmorModel();

	public HwarangArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new ModelRenderer(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.setTextureOffset(64, 0).addBox(-4.5F, -9.0F, -4.5F, 9.0F, 11.0F, 9.0F, 0.0F, false);

		armorTorso = new ModelRenderer(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.setTextureOffset(64, 20).addBox(-4.5F, -0.5F, -3.0F, 9.0F, 6.0F, 6.0F, 0.0F, false);

		hwarang_shoulder_pivot = new ModelRenderer(this);
		hwarang_shoulder_pivot.setRotationPoint(-6.0F, 1.0F, 0.0F);
		armorTorso.addChild(hwarang_shoulder_pivot);
		setRotationAngle(hwarang_shoulder_pivot, 0.0F, 3.1416F, -0.2618F);
		hwarang_shoulder_pivot.setTextureOffset(106, 0).addBox(-2.5F, -2.5F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);

		hwarang_shoulder_pivot2 = new ModelRenderer(this);
		hwarang_shoulder_pivot2.setRotationPoint(6.0F, 1.0F, 0.0F);
		armorTorso.addChild(hwarang_shoulder_pivot2);
		setRotationAngle(hwarang_shoulder_pivot2, 0.0F, 0.0F, 0.2618F);
		hwarang_shoulder_pivot2.setTextureOffset(106, 0).addBox(-2.5F, -2.5F, -3.0F, 5.0F, 8.0F, 6.0F, 0.0F, false);

		armorWaist = new ModelRenderer(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.setTextureOffset(64, 32).addBox(-4.0F, 5.5F, -2.5F, 8.0F, 7.0F, 5.0F, 0.0F, false);

		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.setTextureOffset(108, 46).addBox(-1.5F, -2.5F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		armorRightArm = new ModelRenderer(this);
		armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.setTextureOffset(108, 46).addBox(-3.5F, -2.5F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);

		armorLeftLeg = new ModelRenderer(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.setTextureOffset(98, 30).addBox(-2.5F, -2.5F, -3.0F, 9.0F, 10.0F, 6.0F, 0.0F, false);

		armorLeftBoot = new ModelRenderer(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.setTextureOffset(64, 44).addBox(1.5F, 8.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		armorRightLeg = new ModelRenderer(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.setTextureOffset(98, 14).addBox(-6.5F, -2.5F, -3.0F, 9.0F, 10.0F, 6.0F, 0.0F, false);

		armorRightBoot = new ModelRenderer(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.setTextureOffset(64, 44).addBox(-6.5F, 8.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		//To here
	}
}