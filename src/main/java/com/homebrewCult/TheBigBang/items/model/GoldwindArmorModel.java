package com.homebrewCult.TheBigBang.items.model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class GoldwindArmorModel extends BigBangArmorModel {
	public final ModelRenderer armorHead;
	public final ModelRenderer armorTorso;
	public final ModelRenderer armorWaist;
	public final ModelRenderer armorLeftArm;
	public final ModelRenderer armorRightArm;
	public final ModelRenderer armorLeftLeg;
	public final ModelRenderer armorLeftBoot;
	public final ModelRenderer armorRightLeg;
	public final ModelRenderer armorRightBoot;
	public final ModelRenderer goldwind_overhang_back;
	public final ModelRenderer goldwind_overhang_front;

	public static final GoldwindArmorModel INSTANCE = new GoldwindArmorModel();

	public GoldwindArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		armorHead = new ModelRenderer(this);
		armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHead.addChild(armorHead);
		armorHead.setTextureOffset(64, 0).addBox(-4.5F, -9.5F, -4.5F, 9.0F, 5.0F, 9.0F, 0.0F, false);

		armorTorso = new ModelRenderer(this);
		armorTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorTorso);
		armorTorso.setTextureOffset(64, 14).addBox(-5.0F, -0.5F, -3.0F, 10.0F, 9.0F, 6.0F, 0.0F, false);

		armorWaist = new ModelRenderer(this);
		armorWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody.addChild(armorWaist);
		armorWaist.setTextureOffset(64, 29).addBox(-4.5F, 8.0F, -2.5F, 9.0F, 4.0F, 5.0F, 0.0F, false);

		goldwind_overhang_front = new ModelRenderer(this);
		goldwind_overhang_front.setRotationPoint(-0.5F, 10.0F, -2.5F);
		armorWaist.addChild(goldwind_overhang_front);
		goldwind_overhang_front.setTextureOffset(64, 38).addBox(-5.0F, 0.0F, -0.5F, 10.0F, 8.0F, 5.0F, 0.0F, false);

		goldwind_overhang_back = new ModelRenderer(this);
		goldwind_overhang_back.setRotationPoint(-0.5F, 10.0F, 2.5F);
		armorWaist.addChild(goldwind_overhang_back);
		goldwind_overhang_back.setTextureOffset(64, 51).addBox(-5.0F, 0.0F, -4.5F, 10.0F, 8.0F, 5.0F, 0.0F, false);

		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedLeftArm.addChild(armorLeftArm);
		armorLeftArm.setTextureOffset(108, 0).addBox(-1.5F, 3.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
		armorLeftArm.setTextureOffset(110, 10).addBox(1.0F, 3.0F, -3.0F, 3.0F, 6.0F, 6.0F, 0.0F, false);

		armorRightArm = new ModelRenderer(this);
		armorRightArm.setRotationPoint(-1.0F, 0.0F, 0.0F);
		bipedRightArm.addChild(armorRightArm);
		setRotationAngle(armorRightArm, 0.0F, 3.1416F, 0.0F);
		armorRightArm.setTextureOffset(108, 0).addBox(-2.5F, 3.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
		armorRightArm.setTextureOffset(110, 10).addBox(0.0F, 3.0F, -3.0F, 3.0F, 6.0F, 6.0F, 0.0F, false);

		armorLeftLeg = new ModelRenderer(this);
		armorLeftLeg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftLeg);
		armorLeftLeg.setTextureOffset(108, 43).addBox(1.5F, -0.5F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);

		armorLeftBoot = new ModelRenderer(this);
		armorLeftBoot.setRotationPoint(-4.0F, 0.0F, 0.0F);
		bipedLeftLeg.addChild(armorLeftBoot);
		armorLeftBoot.setTextureOffset(108, 54).addBox(1.5F, 7.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
		armorLeftBoot.setTextureOffset(98, 59).addBox(2.5F, 9.5F, -4.5F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		armorLeftBoot.setTextureOffset(104, 56).addBox(2.5F, 10.5F, -4.5F, 3.0F, 0.0F, 2.0F, 0.0F, false);

		armorRightLeg = new ModelRenderer(this);
		armorRightLeg.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightLeg);
		armorRightLeg.setTextureOffset(108, 43).addBox(-6.5F, -0.5F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);

		armorRightBoot = new ModelRenderer(this);
		armorRightBoot.setRotationPoint(4.0F, 0.0F, 0.0F);
		bipedRightLeg.addChild(armorRightBoot);
		armorRightBoot.setTextureOffset(108, 54).addBox(-6.5F, 7.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
		armorRightBoot.setTextureOffset(98, 59).addBox(-5.5F, 9.5F, -4.5F, 3.0F, 3.0F, 2.0F, 0.0F, false);
		armorRightBoot.setTextureOffset(104, 56).addBox(-5.5F, 10.5F, -4.5F, 3.0F, 0.0F, 2.0F, 0.0F, false);
		//To here
	}

	@Override
	public void setRotationAngles(LivingEntity entity, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		super.setRotationAngles(entity, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
		this.goldwind_overhang_front.rotateAngleX = Math.min(this.bipedLeftLeg.rotateAngleX, this.bipedRightLeg.rotateAngleX);
		this.goldwind_overhang_back.rotateAngleX = Math.max(this.bipedLeftLeg.rotateAngleX, this.bipedRightLeg.rotateAngleX);
	}
}