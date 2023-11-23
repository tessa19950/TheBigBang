package com.homebrewCult.TheBigBang.entities.model;
import com.homebrewCult.TheBigBang.entities.mob.AbstractEyeEntity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class AbstractEyeModel<T extends Entity> extends EntityModel<T> {
	
	private float oscillationTimer = 0f;
	private float oscillationSpeed = .2f;
	
	private float limbBaseRot = .5f;
	private Float limbTopSegmentMaxRot = .2f;
	private Float limbMiddleSegmentMaxRot = .4f;
	private Float limbBottomSegmentMaxRot = .6f;
	private Float limbMiddleSegmentTimeOffset = -1f;
	private Float limbBottomSegmentTimeOffset = -2f;

	private final ModelRenderer Body;
	private final ModelRenderer LegLowLeft;
	private final ModelRenderer LegLowRight;
	private final ModelRenderer Neck;
	private final ModelRenderer Head;
	private final ModelRenderer CheekLeft;
	private final ModelRenderer CheekRight;
	private final ModelRenderer Mouth;
	private final ModelRenderer Teeth;
	private final ModelRenderer TailStart;
	private final ModelRenderer TailMiddle;
	private final ModelRenderer TailEnd;

	public AbstractEyeModel() {
		super();
		textureWidth = 128;
		textureHeight = 128;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 23.0F, 1.0F);
		Body.setTextureOffset(36, 0).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 10.0F, 12.0F, 0.0F, false);
		Body.setTextureOffset(68, 22).addBox(5.0F, -7.0F, -2.0F, 8.0F, 2.0F, 2.0F, 0.0F, false);
		Body.setTextureOffset(68, 0).addBox(-13.0F, -7.0F, -2.0F, 8.0F, 2.0F, 2.0F, 0.0F, false);

		LegLowLeft = new ModelRenderer(this);
		LegLowLeft.setRotationPoint(-12.0F, -6.0F, -1.0F);
		Body.addChild(LegLowLeft);
		LegLowLeft.setTextureOffset(74, 26).addBox(-1.0F, 1.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		LegLowRight = new ModelRenderer(this);
		LegLowRight.setRotationPoint(12.0F, -6.0F, -1.0F);
		Body.addChild(LegLowRight);
		LegLowRight.setTextureOffset(74, 4).addBox(-1.0F, 1.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		Neck = new ModelRenderer(this);
		Neck.setRotationPoint(0.0F, -5.0F, -5.0F);
		Body.addChild(Neck);
		setRotationAngle(Neck, -0.5236F, 0.0F, 0.0F);
		Neck.setTextureOffset(0, 47).addBox(-4.0F, -5.0F, -10.0F, 8.0F, 8.0F, 10.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.4142F, -9.2426F);
		Neck.addChild(Head);
		setRotationAngle(Head, 0.5236F, 0.0F, 0.0F);
		Head.setTextureOffset(0, 0).addBox(-5.0F, -5.9142F, -3.7574F, 10.0F, 8.0F, 8.0F, 0.0F, false);

		CheekLeft = new ModelRenderer(this);
		CheekLeft.setRotationPoint(0.0F, 2.0858F, -3.7574F);
		Head.addChild(CheekLeft);
		CheekLeft.setTextureOffset(18, 33).addBox(0.0F, -1.0F, -0.5F, 6.0F, 2.0F, 2.0F, 0.0F, false);

		CheekRight = new ModelRenderer(this);
		CheekRight.setRotationPoint(0.0F, 2.0858F, -3.7574F);
		Head.addChild(CheekRight);
		CheekRight.setTextureOffset(18, 29).addBox(-6.0F, -1.0F, -0.5F, 6.0F, 2.0F, 2.0F, 0.0F, false);

		Mouth = new ModelRenderer(this);
		Mouth.setRotationPoint(0.0F, -0.4142F, -1.7574F);
		Head.addChild(Mouth);
		Mouth.setTextureOffset(0, 29).addBox(-3.1F, 1.9F, -1.8F, 6.2F, 6.2F, 3.2F, 0.0F, false);
		Mouth.setTextureOffset(0, 38).addBox(-2.5F, 3.5F, -1.8F, 5.0F, 4.0F, 2.0F, 0.0F, false);
		Mouth.setTextureOffset(8, 21).addBox(-5.0F, 2.0F, -1.8F, 10.0F, 8.0F, 0.0F, 0.0F, false);
		Mouth.setTextureOffset(20, 37).addBox(-2.0F, 7.0F, -2.4F, 4.0F, 3.0F, 2.0F, 0.0F, false);

		Teeth = new ModelRenderer(this);
		Teeth.setRotationPoint(0.0F, -0.2929F, -0.364F);
		Mouth.addChild(Teeth);
		Teeth.setTextureOffset(8, 16).addBox(-5.0F, 2.2929F, -1.536F, 10.0F, 4.0F, 0.0F, 0.0F, false);

		TailStart = new ModelRenderer(this);
		TailStart.setRotationPoint(0.0F, -7.0F, 2.0F);
		Body.addChild(TailStart);
		setRotationAngle(TailStart, 0.7854F, 0.0F, 0.0F);
		TailStart.setTextureOffset(39, 22).addBox(-3.5F, -2.5F, -1.0F, 7.0F, 7.0F, 12.0F, 0.0F, false);

		TailMiddle = new ModelRenderer(this);
		TailMiddle.setRotationPoint(0.0F, 0.5F, 10.0F);
		TailStart.addChild(TailMiddle);
		setRotationAngle(TailMiddle, -0.7854F, 0.0F, 0.0F);
		TailMiddle.setTextureOffset(44, 41).addBox(-2.5F, -2.0F, -1.0F, 5.0F, 5.0F, 9.0F, 0.0F, false);

		TailEnd = new ModelRenderer(this);
		TailEnd.setRotationPoint(0.0F, 0.5F, 7.5F);
		TailMiddle.addChild(TailEnd);
		setRotationAngle(TailEnd, -0.7854F, 0.0F, 0.0F);
		TailEnd.setTextureOffset(49, 55).addBox(-1.5F, -1.5F, -0.5F, 3.0F, 3.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);		
		oscillationTimer = (entityIn.ticksExisted + partialTick) * oscillationSpeed;

		this.LegLowLeft.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.LegLowRight.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;

		AbstractEyeEntity entity = (AbstractEyeEntity)entityIn;
		if(entity.isAngry) {
			this.CheekLeft.rotateAngleZ = -0.3f;
			this.CheekRight.rotateAngleZ = 0.3f;
			this.Mouth.rotationPointX = 0.35f + ((float)Math.cos(oscillationTimer) * 0.05f);
			this.Teeth.rotationPointY = 0f + ((float)Math.cos(oscillationTimer) * -0.08f);
		} else {
			this.CheekLeft.rotateAngleZ = 0;
			this.CheekRight.rotateAngleZ = 0;
			this.Mouth.rotationPointX = 0f;
			this.Teeth.rotationPointY = 0;
		}
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	    this.Head.rotateAngleX = 0.5f + (headPitch * ((float)Math.PI / 180F));
	    this.Neck.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		
		this.TailStart.rotateAngleX = limbBaseRot + ((float)Math.cos(oscillationTimer) * limbTopSegmentMaxRot);
		this.TailMiddle.rotateAngleX = (float)Math.cos(oscillationTimer + limbMiddleSegmentTimeOffset) * limbMiddleSegmentMaxRot;
		this.TailEnd.rotateAngleX = (float)Math.cos(oscillationTimer + limbBottomSegmentTimeOffset) * limbBottomSegmentMaxRot;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}