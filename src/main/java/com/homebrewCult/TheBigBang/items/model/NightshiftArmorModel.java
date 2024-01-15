package com.homebrewCult.TheBigBang.items.model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class NightshiftArmorModel extends BigBangArmorModel {
	public final ModelRenderer armorHead;
	public final ModelRenderer armorTorso;
	public final ModelRenderer armorWaist;
	public final ModelRenderer armorLeftArm;
	public final ModelRenderer armorRightArm;
	public final ModelRenderer armorLeftLeg;
	public final ModelRenderer armorLeftBoot;
	public final ModelRenderer armorRightLeg;
	public final ModelRenderer armorRightBoot;

	public static final NightshiftArmorModel INSTANCE = new NightshiftArmorModel();

	public NightshiftArmorModel() {	
		super();
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new ModelRenderer(this);
		armorHead.setRotationPoint(0.0F, -8.5F, 0.0F);
		bipedHead.addChild(armorHead);
		setRotationAngle(armorHead, -0.0873F, 0.0F, 0.0F);
		armorHead.setTextureOffset(64, 6).addBox(-5.0F, -1.0F, -4.5F, 10.0F, 4.0F, 10.0F, 0.0F, false);
		armorHead.setTextureOffset(64, 0).addBox(-2.5F, -2.0F, -0.5F, 5.0F, 1.0F, 5.0F, 0.0F, false);

		armorTorso = new ModelRenderer(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.setTextureOffset(64, 20).addBox(-5.5F, -0.5F, -3.5F, 11.0F, 6.0F, 7.0F, 0.0F, false);
		armorTorso.setTextureOffset(64, 33).addBox(-5.0F, 5.5F, -3.0F, 10.0F, 5.0F, 6.0F, 0.0F, false);

		armorWaist = new ModelRenderer(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.setTextureOffset(64, 44).addBox(-4.5F, 8.5F, -2.5F, 9.0F, 4.0F, 5.0F, 0.0F, false);

		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.setTextureOffset(108, 0).addBox(-1.5F, 3.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);

		armorRightArm = new ModelRenderer(this);
		armorRightArm.setRotationPoint(-1.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		setRotationAngle(armorRightArm, 0.0F, 3.1416F, 0.0F);
		armorRightArm.setTextureOffset(108, 0).addBox(-2.5F, 3.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);

		armorLeftLeg = new ModelRenderer(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.setTextureOffset(106, 16).addBox(2.0F, -0.5F, -3.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
		armorLeftLeg.setTextureOffset(108, 38).addBox(1.5F, 4.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		armorLeftBoot = new ModelRenderer(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.setTextureOffset(90, 53).addBox(1.5F, 9.0F, -2.5F, 5.0F, 3.0F, 5.0F, 0.0F, false);
		armorLeftBoot.setTextureOffset(104, 56).addBox(1.0F, 8.5F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);

		armorRightLeg = new ModelRenderer(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.setTextureOffset(106, 27).addBox(-7.0F, -0.5F, -3.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
		armorRightLeg.setTextureOffset(108, 38).addBox(-6.5F, 4.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		armorRightBoot = new ModelRenderer(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.setTextureOffset(90, 53).addBox(-6.5F, 9.0F, -2.5F, 5.0F, 3.0F, 5.0F, 0.0F, false);
		armorRightBoot.setTextureOffset(72, 56).addBox(-7.0F, 8.5F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
		//To here
	}
}