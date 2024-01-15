package com.homebrewCult.TheBigBang.items.model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PilferArmorModel extends BigBangArmorModel {
	public final ModelRenderer armorHead;
	public final ModelRenderer pilfer_tip_pivot;
	public final ModelRenderer armorTorso;
	public final ModelRenderer armorWaist;
	public final ModelRenderer armorLeftArm;
	public final ModelRenderer armorRightArm;
	public final ModelRenderer armorLeftLeg;
	public final ModelRenderer armorLeftBoot;
	public final ModelRenderer armorRightLeg;
	public final ModelRenderer armorRightBoot;

	public static final PilferArmorModel INSTANCE = new PilferArmorModel();

	public PilferArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new ModelRenderer(this);
		armorHead.setRotationPoint(0.0F, -5.5F, 0.0F);
		bipedHead.addChild(armorHead);
		setRotationAngle(armorHead, -0.1745F, 0.0F, 0.0F);
		armorHead.setTextureOffset(64, 4).addBox(-4.5F, -2.5F, -4.5F, 9.0F, 4.0F, 9.0F, 0.0F, false);
		armorHead.setTextureOffset(64, 17).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);

		pilfer_tip_pivot = new ModelRenderer(this);
		pilfer_tip_pivot.setRotationPoint(0.0F, -2.5F, -3.5F);
		armorHead.addChild(pilfer_tip_pivot);
		setRotationAngle(pilfer_tip_pivot, -0.2618F, 0.0F, 0.0F);
		pilfer_tip_pivot.setTextureOffset(64, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);

		armorTorso = new ModelRenderer(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.setTextureOffset(64, 37).addBox(-5.0F, -0.5F, -3.0F, 10.0F, 12.0F, 6.0F, 0.0F, false);

		armorWaist = new ModelRenderer(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.setTextureOffset(64, 55).addBox(-4.5F, 8.5F, -2.5F, 9.0F, 4.0F, 5.0F, 0.0F, false);

		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.setTextureOffset(108, 28).addBox(-1.5F, -2.0F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		armorRightArm = new ModelRenderer(this);
		armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.setTextureOffset(108, 28).addBox(-3.5F, -2.0F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		armorLeftBoot = new ModelRenderer(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.setTextureOffset(108, 45).addBox(1.5F, 8.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		armorLeftLeg = new ModelRenderer(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.setTextureOffset(108, 0).addBox(1.5F, -0.5F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, false);

		armorRightLeg = new ModelRenderer(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.setTextureOffset(108, 14).addBox(-6.5F, -0.5F, -2.5F, 5.0F, 9.0F, 5.0F, 0.0F, false);

		armorRightBoot = new ModelRenderer(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.setTextureOffset(108, 45).addBox(-6.5F, 8.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		//To here
	}
}