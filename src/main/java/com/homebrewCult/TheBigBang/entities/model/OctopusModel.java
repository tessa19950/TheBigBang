package com.homebrewCult.TheBigBang.entities.model;

import com.google.common.primitives.Floats;
import com.homebrewCult.TheBigBang.entities.mob.OctopusEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OctopusModel <T extends OctopusEntity> extends EntityModel<T> {

	private final ModelRenderer bb_main;
	private final ModelRenderer Eye1;
	private final ModelRenderer Eye2;
	private final ModelRenderer Eye3;
	private final ModelRenderer Eye4;
	private final ModelRenderer Eye5;
	private final ModelRenderer Eye6;
	private final ModelRenderer Eye7;
	private final ModelRenderer Eye8;
	private final ModelRenderer Leg1;
	private final ModelRenderer Segment1;
	private final ModelRenderer Segment2;
	private final ModelRenderer Segment3;
	private final ModelRenderer Leg2;
	private final ModelRenderer Segment4;
	private final ModelRenderer Segment5;
	private final ModelRenderer Segment6;
	private final ModelRenderer Leg3;
	private final ModelRenderer Segment7;
	private final ModelRenderer Segment8;
	private final ModelRenderer Segment9;
	private final ModelRenderer Leg4;
	private final ModelRenderer Segment10;
	private final ModelRenderer Segment11;
	private final ModelRenderer Segment12;
	private final ModelRenderer Leg5;
	private final ModelRenderer Segment13;
	private final ModelRenderer Segment14;
	private final ModelRenderer Segment15;
	private final ModelRenderer Leg6;
	private final ModelRenderer Segment16;
	private final ModelRenderer Segment17;
	private final ModelRenderer Segment18;
	
	private Float oscillationTimer = 0f;
	
	private Float limbTopSegmentMaxRot = 0.75f;
	private Float limbMiddleSegmentMaxRot = 1f;
	private Float limbBottomSegmentMaxRot = 1.25f;
	
	private Float limbMiddleSegmentTimeOffset = -1f;
	private Float limbBottomSegmentTimeOffset = -2f;
	
	public OctopusModel() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 0).addBox(-8.0F, -36.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		Eye1 = new ModelRenderer(this);
		Eye1.setRotationPoint(0.0F, -4.0F, -8.0F);
		Eye1.setTextureOffset(0, 32).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

		Eye2 = new ModelRenderer(this);
		Eye2.setRotationPoint(-7.0F, -4.0F, -7.0F);
		setRotationAngle(Eye2, 0.0F, 0.7854F, 0.0F);
		Eye2.setTextureOffset(0, 32).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

		Eye3 = new ModelRenderer(this);
		Eye3.setRotationPoint(-8.0F, -4.0F, 0.0F);
		setRotationAngle(Eye3, 0.0F, 1.5708F, 0.0F);
		Eye3.setTextureOffset(0, 32).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

		Eye4 = new ModelRenderer(this);
		Eye4.setRotationPoint(-7.0F, -4.0F, 7.0F);
		setRotationAngle(Eye4, 0.0F, 2.3562F, 0.0F);
		Eye4.setTextureOffset(0, 32).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

		Eye5 = new ModelRenderer(this);
		Eye5.setRotationPoint(0.0F, -4.0F, 8.0F);
		setRotationAngle(Eye5, 0.0F, 3.1416F, 0.0F);
		Eye5.setTextureOffset(0, 32).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

		Eye6 = new ModelRenderer(this);
		Eye6.setRotationPoint(7.0F, -4.0F, 7.0F);
		setRotationAngle(Eye6, 0.0F, -2.3562F, 0.0F);
		Eye6.setTextureOffset(0, 32).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

		Eye7 = new ModelRenderer(this);
		Eye7.setRotationPoint(8.0F, -4.0F, 0.0F);
		setRotationAngle(Eye7, 0.0F, -1.5708F, 0.0F);
		Eye7.setTextureOffset(0, 32).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

		Eye8 = new ModelRenderer(this);
		Eye8.setRotationPoint(7.0F, -4.0F, -7.0F);
		setRotationAngle(Eye8, 0.0F, -0.7854F, 0.0F);
		Eye8.setTextureOffset(0, 32).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);

		Leg1 = new ModelRenderer(this);
		Leg1.setRotationPoint(0.0F, 24.0F, 0.0F);


		Segment1 = new ModelRenderer(this);
		Segment1.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg1.addChild(Segment1);
		Segment1.setTextureOffset(22, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		Segment2 = new ModelRenderer(this);
		Segment2.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment1.addChild(Segment2);
		Segment2.setTextureOffset(32, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Segment3 = new ModelRenderer(this);
		Segment3.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment2.addChild(Segment3);
		Segment3.setTextureOffset(42, 32).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Leg2 = new ModelRenderer(this);
		Leg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg2, 0.0F, -1.0472F, 0.0F);


		Segment4 = new ModelRenderer(this);
		Segment4.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg2.addChild(Segment4);
		Segment4.setTextureOffset(22, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		Segment5 = new ModelRenderer(this);
		Segment5.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment4.addChild(Segment5);
		Segment5.setTextureOffset(32, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Segment6 = new ModelRenderer(this);
		Segment6.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment5.addChild(Segment6);
		Segment6.setTextureOffset(42, 32).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Leg3 = new ModelRenderer(this);
		Leg3.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg3, 0.0F, -2.0944F, 0.0F);


		Segment7 = new ModelRenderer(this);
		Segment7.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg3.addChild(Segment7);
		Segment7.setTextureOffset(22, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		Segment8 = new ModelRenderer(this);
		Segment8.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment7.addChild(Segment8);
		Segment8.setTextureOffset(32, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Segment9 = new ModelRenderer(this);
		Segment9.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment8.addChild(Segment9);
		Segment9.setTextureOffset(42, 32).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Leg4 = new ModelRenderer(this);
		Leg4.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg4, 0.0F, 3.1416F, 0.0F);


		Segment10 = new ModelRenderer(this);
		Segment10.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg4.addChild(Segment10);
		Segment10.setTextureOffset(22, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		Segment11 = new ModelRenderer(this);
		Segment11.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment10.addChild(Segment11);
		Segment11.setTextureOffset(32, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Segment12 = new ModelRenderer(this);
		Segment12.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment11.addChild(Segment12);
		Segment12.setTextureOffset(42, 32).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Leg5 = new ModelRenderer(this);
		Leg5.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg5, 0.0F, 2.0944F, 0.0F);


		Segment13 = new ModelRenderer(this);
		Segment13.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg5.addChild(Segment13);
		Segment13.setTextureOffset(22, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		Segment14 = new ModelRenderer(this);
		Segment14.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment13.addChild(Segment14);
		Segment14.setTextureOffset(32, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Segment15 = new ModelRenderer(this);
		Segment15.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment14.addChild(Segment15);
		Segment15.setTextureOffset(42, 32).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		Leg6 = new ModelRenderer(this);
		Leg6.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg6, 0.0F, 1.0472F, 0.0F);

		Segment16 = new ModelRenderer(this);
		Segment16.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg6.addChild(Segment16);
		Segment16.setTextureOffset(22, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		Segment17 = new ModelRenderer(this);
		Segment17.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment16.addChild(Segment17);
		Segment17.setTextureOffset(32, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		Segment18 = new ModelRenderer(this);
		Segment18.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment17.addChild(Segment18);
		Segment18.setTextureOffset(42, 32).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
	}


	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Eye1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Eye2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Eye3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Eye4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Eye5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Eye6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Eye7.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Eye8.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Leg1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Leg2.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Leg3.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Leg4.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Leg5.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		Leg6.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
		oscillationTimer = (entityIn.ticksExisted + partialTick) * .5f;
	}

	@Override
	public void setRotationAngles(OctopusEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.Eye1.rotateAngleX = Floats.constrainToRange(headPitch, -45, 45) * ((float)Math.PI / 180F);
		this.Eye1.rotateAngleY = Floats.constrainToRange(netHeadYaw, -45, 45) * ((float)Math.PI / 180F);
		this.Eye2.rotateAngleX = Floats.constrainToRange(headPitch, -45, 45) * ((float)Math.PI / 180F);
		this.Eye2.rotateAngleY = Floats.constrainToRange(netHeadYaw, 0, 90) * ((float)Math.PI / 180F);
		this.Eye3.rotateAngleX = Floats.constrainToRange(headPitch, -45, 45) * ((float)Math.PI / 180F);
		this.Eye3.rotateAngleY = Floats.constrainToRange(netHeadYaw, 45, 135) * ((float)Math.PI / 180F);
		this.Eye4.rotateAngleX = Floats.constrainToRange(headPitch, -45, 45) * ((float)Math.PI / 180F);
		this.Eye4.rotateAngleY = Floats.constrainToRange(netHeadYaw, 90, 180) * ((float)Math.PI / 180F);
		this.Eye5.rotateAngleX = Floats.constrainToRange(headPitch, -45, 45) * ((float)Math.PI / 180F);
		this.Eye5.rotateAngleY = Floats.constrainToRange(netHeadYaw, 180, 180) * ((float)Math.PI / 180F);
		this.Eye6.rotateAngleX = Floats.constrainToRange(headPitch, -45, 45) * ((float)Math.PI / 180F);
		this.Eye6.rotateAngleY = Floats.constrainToRange(netHeadYaw, -180, -90) * ((float)Math.PI / 180F);
		this.Eye7.rotateAngleX = Floats.constrainToRange(headPitch, -45, 45) * ((float)Math.PI / 180F);
		this.Eye7.rotateAngleY = Floats.constrainToRange(netHeadYaw, -135, -45) * ((float)Math.PI / 180F);
		this.Eye8.rotateAngleX = Floats.constrainToRange(headPitch, -45, 45) * ((float)Math.PI / 180F);
		this.Eye8.rotateAngleY = Floats.constrainToRange(netHeadYaw, -90, 0) * ((float)Math.PI / 180F);

		this.Segment1.rotateAngleX = -0.3f + ((float)Math.cos(oscillationTimer) * limbTopSegmentMaxRot);
		this.Segment2.rotateAngleX = (float)Math.cos(oscillationTimer + limbMiddleSegmentTimeOffset) * limbMiddleSegmentMaxRot;
		this.Segment3.rotateAngleX = (float)Math.cos(oscillationTimer + limbBottomSegmentTimeOffset) * limbBottomSegmentMaxRot;

		this.Segment4.rotateAngleX = -0.3f + -((float)Math.cos(oscillationTimer) * limbTopSegmentMaxRot);
		this.Segment5.rotateAngleX = (float)Math.cos(oscillationTimer + -limbMiddleSegmentTimeOffset) * limbMiddleSegmentMaxRot;
		this.Segment6.rotateAngleX = (float)Math.cos(oscillationTimer + -limbBottomSegmentTimeOffset) * limbBottomSegmentMaxRot;

		this.Segment7.rotateAngleX = -0.3f + ((float)Math.cos(oscillationTimer + 1) * limbTopSegmentMaxRot);
		this.Segment8.rotateAngleX = (float)Math.cos(oscillationTimer + 1 + limbMiddleSegmentTimeOffset) * limbMiddleSegmentMaxRot;
		this.Segment9.rotateAngleX = (float)Math.cos(oscillationTimer + 1 + limbBottomSegmentTimeOffset) * limbBottomSegmentMaxRot;

		this.Segment10.rotateAngleX = -0.3f + -((float)Math.cos(oscillationTimer + 1) * limbTopSegmentMaxRot);
		this.Segment11.rotateAngleX = (float)Math.cos(oscillationTimer + 1 + -limbMiddleSegmentTimeOffset) * limbMiddleSegmentMaxRot;
		this.Segment12.rotateAngleX = (float)Math.cos(oscillationTimer + 1 + -limbBottomSegmentTimeOffset) * limbBottomSegmentMaxRot;

		this.Segment13.rotateAngleX = -0.3f + ((float)Math.cos(oscillationTimer + 2) * limbTopSegmentMaxRot);
		this.Segment14.rotateAngleX = (float)Math.cos(oscillationTimer + 2 + limbMiddleSegmentTimeOffset) * limbMiddleSegmentMaxRot;
		this.Segment15.rotateAngleX = (float)Math.cos(oscillationTimer + 2 + limbBottomSegmentTimeOffset) * limbBottomSegmentMaxRot;

		this.Segment16.rotateAngleX = -0.3f + -((float)Math.cos(oscillationTimer + 2) * limbTopSegmentMaxRot);
		this.Segment17.rotateAngleX = (float)Math.cos(oscillationTimer + 2 + -limbMiddleSegmentTimeOffset) * limbMiddleSegmentMaxRot;
		this.Segment18.rotateAngleX = (float)Math.cos(oscillationTimer + 2 + -limbBottomSegmentTimeOffset) * limbBottomSegmentMaxRot;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
