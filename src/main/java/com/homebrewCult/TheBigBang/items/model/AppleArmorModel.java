package com.homebrewCult.TheBigBang.items.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class AppleArmorModel extends BipedModel<LivingEntity> {
	public final ModelRenderer armorHead;
	public final ModelRenderer armorTorso;
	public final ModelRenderer armorWaist;
	public final ModelRenderer armorLeftArm;
	public final ModelRenderer armorRightArm;
	public final ModelRenderer armorLeftLeg;
	public final ModelRenderer armorLeftBoot;
	public final ModelRenderer armorRightLeg;
	public final ModelRenderer armorRightBoot;

	public static final AppleArmorModel INSTANCE = new AppleArmorModel();

	public AppleArmorModel() {	
		super(1f, 0.0F, 128, 64);
		
		//Paste the Blockbench exported file from here
		armorHead = new ModelRenderer(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.setTextureOffset(64, 0).addBox(-5.5F, -9.5F, -5.5F, 11.0F, 10.0F, 11.0F, 0.0F, false);
		armorHead.setTextureOffset(64, 48).addBox(-4.5F, -18.5F, 0.0F, 9.0F, 9.0F, 0.0F, 0.0F, false);

		armorTorso = new ModelRenderer(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.setTextureOffset(64, 22).addBox(-4.5F, 0.0F, -3.0F, 9.0F, 10.0F, 6.0F, 0.0F, false);

		armorWaist = new ModelRenderer(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.setTextureOffset(64, 38).addBox(-4.5F, 10.0F, -3.0F, 9.0F, 3.0F, 6.0F, 0.0F, false);

		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.setTextureOffset(108, 0).addBox(-1.5F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F, 0.0F, false);

		armorRightArm = new ModelRenderer(this);
		armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		armorRightArm.setTextureOffset(108, 0).addBox(-3.5F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F, 0.0F, false);

		armorLeftLeg = new ModelRenderer(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.setTextureOffset(108, 22).addBox(1.5F, -0.5F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		armorLeftBoot = new ModelRenderer(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);


		armorRightLeg = new ModelRenderer(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.setTextureOffset(108, 22).addBox(-6.5F, -0.5F, -2.5F, 5.0F, 12.0F, 5.0F, 0.0F, false);

		armorRightBoot = new ModelRenderer(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		//To here
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}