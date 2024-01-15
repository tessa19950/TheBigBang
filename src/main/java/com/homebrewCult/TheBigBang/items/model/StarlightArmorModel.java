package com.homebrewCult.TheBigBang.items.model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class StarlightArmorModel extends BigBangArmorModel {
	public final ModelRenderer armorHead;
	public final ModelRenderer hatrim_right_pivot;
	public final ModelRenderer hatrim_left_pivot;
	public final ModelRenderer hattip1_pivot;
	public final ModelRenderer hattip2_pivot;
	public final ModelRenderer hattip3_pivot;
	public final ModelRenderer armorTorso;
	public final ModelRenderer tabardTorso_pivot;
	public final ModelRenderer armorWaist;
	public final ModelRenderer armorLeftArm;
	public final ModelRenderer armorRightArm;
	public final ModelRenderer armorLeftLeg;
	public final ModelRenderer armorLeftBoot;
	public final ModelRenderer armorRightLeg;
	public final ModelRenderer armorRightBoot;
	public final ModelRenderer tabardLegs_front;
	public final ModelRenderer tabardLegs_back;

	public static final StarlightArmorModel INSTANCE = new StarlightArmorModel();

	public StarlightArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new ModelRenderer(this);
		armorHead.setRotationPoint(0.0F, -8.0F, 0.0F);
		bipedHead.addChild(armorHead);
		setRotationAngle(armorHead, -0.1745F, 0.0F, 0.0F);


		hatrim_right_pivot = new ModelRenderer(this);
		hatrim_right_pivot.setRotationPoint(0.0F, -1.0F, 0.0F);
		armorHead.addChild(hatrim_right_pivot);
		setRotationAngle(hatrim_right_pivot, 0.0F, 0.0F, -0.1745F);
		hatrim_right_pivot.setTextureOffset(68, 46).addBox(-7.8F, 1.0F, -8.0F, 8.0F, 2.0F, 16.0F, 0.0F, false);

		hatrim_left_pivot = new ModelRenderer(this);
		hatrim_left_pivot.setRotationPoint(0.0F, -1.0F, 0.0F);
		armorHead.addChild(hatrim_left_pivot);
		setRotationAngle(hatrim_left_pivot, 0.0F, 3.1416F, 0.1745F);
		hatrim_left_pivot.setTextureOffset(68, 46).addBox(-7.8F, 1.0F, -8.0F, 8.0F, 2.0F, 16.0F, 0.0F, false);

		hattip1_pivot = new ModelRenderer(this);
		hattip1_pivot.setRotationPoint(0.0F, 2.0F, 0.0F);
		armorHead.addChild(hattip1_pivot);
		setRotationAngle(hattip1_pivot, 0.0F, 0.0F, 0.0873F);
		hattip1_pivot.setTextureOffset(92, 21).addBox(-4.5F, -5.0F, -4.5F, 9.0F, 5.0F, 9.0F, 0.0F, false);

		hattip2_pivot = new ModelRenderer(this);
		hattip2_pivot.setRotationPoint(0.0F, -5.0F, 0.0F);
		hattip1_pivot.addChild(hattip2_pivot);
		setRotationAngle(hattip2_pivot, 0.0F, 0.0F, -0.2618F);
		hattip2_pivot.setTextureOffset(80, 35).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);

		hattip3_pivot = new ModelRenderer(this);
		hattip3_pivot.setRotationPoint(0.0F, -4.0F, 0.0F);
		hattip2_pivot.addChild(hattip3_pivot);
		setRotationAngle(hattip3_pivot, 0.0F, 0.0F, 0.3491F);
		hattip3_pivot.setTextureOffset(74, 33).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		armorTorso = new ModelRenderer(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.setTextureOffset(108, 0).addBox(-4.0F, 1.0F, -3.5F, 8.0F, 8.0F, 1.0F, 0.0F, false);
		armorTorso.setTextureOffset(64, 11).addBox(-4.5F, -0.5F, -2.5F, 9.0F, 9.0F, 5.0F, 0.0F, false);
		armorTorso.setTextureOffset(64, 0).addBox(-4.5F, -1.0F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F, false);

		tabardTorso_pivot = new ModelRenderer(this);
		tabardTorso_pivot.setRotationPoint(0.0F, 0.0F, 3.0F);
		armorTorso.addChild(tabardTorso_pivot);
		setRotationAngle(tabardTorso_pivot, 0.0F, 3.1416F, 0.0F);
		tabardTorso_pivot.setTextureOffset(108, 0).addBox(-4.0F, 1.0F, -0.5F, 8.0F, 8.0F, 1.0F, 0.0F, false);

		armorWaist = new ModelRenderer(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.setTextureOffset(64, 25).addBox(-4.5F, 8.5F, -2.5F, 9.0F, 3.0F, 5.0F, 0.0F, false);

		tabardLegs_back = new ModelRenderer(this);
		tabardLegs_back.setRotationPoint(0.0F, 10.0F, 3.0F);
		armorWaist.addChild(tabardLegs_back);
		setRotationAngle(tabardLegs_back, 0.0F, 3.1416F, 0.0F);
		tabardLegs_back.setTextureOffset(108, 8).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 12.0F, 1.0F, 0.0F, false);

		tabardLegs_front = new ModelRenderer(this);
		tabardLegs_front.setRotationPoint(0.0F, 10.0F, -3.0F);
		armorWaist.addChild(tabardLegs_front);
		tabardLegs_front.setTextureOffset(108, 8).addBox(-4.5F, 0.0F, -0.5F, 9.0F, 12.0F, 1.0F, 0.0F, false);

		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.setTextureOffset(64, 41).addBox(-1.5F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F, 0.0F, false);

		armorRightArm = new ModelRenderer(this);
		armorRightArm.setRotationPoint(-1.5F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		setRotationAngle(armorRightArm, 0.0F, 3.1416F, 0.0F);
		armorRightArm.setTextureOffset(64, 41).addBox(-3.0F, -2.5F, -2.5F, 5.0F, 13.0F, 5.0F, 0.0F, false);

		armorLeftLeg = new ModelRenderer(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.setTextureOffset(104, 36).addBox(1.5F, -0.5F, -3.0F, 6.0F, 11.0F, 6.0F, 0.0F, false);

		armorLeftBoot = new ModelRenderer(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.setTextureOffset(108, 53).addBox(1.5F, 8.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);

		armorRightLeg = new ModelRenderer(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.setTextureOffset(104, 36).addBox(-7.5F, -0.5F, -3.0F, 6.0F, 11.0F, 6.0F, 0.0F, false);

		armorRightBoot = new ModelRenderer(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.setTextureOffset(108, 53).addBox(-6.5F, 8.5F, -2.5F, 5.0F, 4.0F, 5.0F, 0.0F, false);
		//To here
	}

	@Override
	public void setRotationAngles(LivingEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		super.setRotationAngles(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
		this.tabardLegs_front.rotateAngleX = Math.min(this.bipedLeftLeg.rotateAngleX, this.bipedRightLeg.rotateAngleX);
		this.tabardLegs_back.rotateAngleX = Math.min(this.bipedLeftLeg.rotateAngleX, this.bipedRightLeg.rotateAngleX);
	}
}