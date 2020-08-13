package com.homebrewCult.TheBigBang.entities.model;

import com.google.common.primitives.Floats;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OctopusModel <T extends Entity> extends QuadrupedModel<T> {
	
	private final RendererModel bb_main;	
	private final RendererModel Eye1;
	private final RendererModel Eye2;
	private final RendererModel Eye3;
	private final RendererModel Eye4;
	private final RendererModel Eye5;
	private final RendererModel Eye6;
	private final RendererModel Eye7;
	private final RendererModel Eye8;
	private final RendererModel Leg1;
	private final RendererModel Segment1;
	private final RendererModel Segment2;
	private final RendererModel Segment3;
	private final RendererModel Leg2;
	private final RendererModel Segment4;
	private final RendererModel Segment5;
	private final RendererModel Segment6;
	private final RendererModel Leg3;
	private final RendererModel Segment7;
	private final RendererModel Segment8;
	private final RendererModel Segment9;
	private final RendererModel Leg4;
	private final RendererModel Segment10;
	private final RendererModel Segment11;
	private final RendererModel Segment12;
	private final RendererModel Leg5;
	private final RendererModel Segment13;
	private final RendererModel Segment14;
	private final RendererModel Segment15;
	private final RendererModel Leg6;
	private final RendererModel Segment16;
	private final RendererModel Segment17;
	private final RendererModel Segment18;
	
	private Float oscillationTimer = 0f;
	
	private Float limbTopSegmentMaxRot = 0.75f;
	private Float limbMiddleSegmentMaxRot = 1f;
	private Float limbBottomSegmentMaxRot = 1.25f;
	
	private Float limbMiddleSegmentTimeOffset = -1f;
	private Float limbBottomSegmentTimeOffset = -2f;
	
	public OctopusModel() {
		super(6, 0.0F);
		
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -36.0F, -8.0F, 16, 16, 16, 0.0F, false));

		Eye1 = new RendererModel(this);
		Eye1.setRotationPoint(0.0F, -4.0F, -8.0F);
		Eye1.cubeList.add(new ModelBox(Eye1, 0, 32, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		Eye2 = new RendererModel(this);
		Eye2.setRotationPoint(-7.0F, -4.0F, -7.0F);
		setRotationAngle(Eye2, 0.0F, 0.7854F, 0.0F);
		Eye2.cubeList.add(new ModelBox(Eye2, 0, 32, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		Eye3 = new RendererModel(this);
		Eye3.setRotationPoint(-8.0F, -4.0F, 0.0F);
		setRotationAngle(Eye3, 0.0F, 1.5708F, 0.0F);
		Eye3.cubeList.add(new ModelBox(Eye3, 0, 32, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		Eye4 = new RendererModel(this);
		Eye4.setRotationPoint(-7.0F, -4.0F, 7.0F);
		setRotationAngle(Eye4, 0.0F, 2.3562F, 0.0F);
		Eye4.cubeList.add(new ModelBox(Eye4, 0, 32, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		Eye5 = new RendererModel(this);
		Eye5.setRotationPoint(0.0F, -4.0F, 8.0F);
		setRotationAngle(Eye5, 0.0F, 3.1416F, 0.0F);
		Eye5.cubeList.add(new ModelBox(Eye5, 0, 32, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		Eye6 = new RendererModel(this);
		Eye6.setRotationPoint(7.0F, -4.0F, 7.0F);
		setRotationAngle(Eye6, 0.0F, -2.3562F, 0.0F);
		Eye6.cubeList.add(new ModelBox(Eye6, 0, 32, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		Eye7 = new RendererModel(this);
		Eye7.setRotationPoint(8.0F, -4.0F, 0.0F);
		setRotationAngle(Eye7, 0.0F, -1.5708F, 0.0F);
		Eye7.cubeList.add(new ModelBox(Eye7, 0, 32, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		Eye8 = new RendererModel(this);
		Eye8.setRotationPoint(7.0F, -4.0F, -7.0F);
		setRotationAngle(Eye8, 0.0F, -0.7854F, 0.0F);
		Eye8.cubeList.add(new ModelBox(Eye8, 0, 32, -2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F, false));

		Leg1 = new RendererModel(this);
		Leg1.setRotationPoint(0.0F, 24.0F, 0.0F);

		Segment1 = new RendererModel(this);
		Segment1.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg1.addChild(Segment1);
		Segment1.cubeList.add(new ModelBox(Segment1, 22, 32, -1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		Segment2 = new RendererModel(this);
		Segment2.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment1.addChild(Segment2);
		Segment2.cubeList.add(new ModelBox(Segment2, 32, 32, -1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F, false));

		Segment3 = new RendererModel(this);
		Segment3.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment2.addChild(Segment3);
		Segment3.cubeList.add(new ModelBox(Segment3, 42, 32, -0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F, false));

		Leg2 = new RendererModel(this);
		Leg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg2, 0.0F, -1.0472F, 0.0F);

		Segment4 = new RendererModel(this);
		Segment4.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg2.addChild(Segment4);
		Segment4.cubeList.add(new ModelBox(Segment4, 22, 32, -1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		Segment5 = new RendererModel(this);
		Segment5.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment4.addChild(Segment5);
		Segment5.cubeList.add(new ModelBox(Segment5, 32, 32, -1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F, false));

		Segment6 = new RendererModel(this);
		Segment6.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment5.addChild(Segment6);
		Segment6.cubeList.add(new ModelBox(Segment6, 42, 32, -0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F, false));

		Leg3 = new RendererModel(this);
		Leg3.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg3, 0.0F, -2.0944F, 0.0F);

		Segment7 = new RendererModel(this);
		Segment7.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg3.addChild(Segment7);
		Segment7.cubeList.add(new ModelBox(Segment7, 22, 32, -1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		Segment8 = new RendererModel(this);
		Segment8.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment7.addChild(Segment8);
		Segment8.cubeList.add(new ModelBox(Segment8, 32, 32, -1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F, false));

		Segment9 = new RendererModel(this);
		Segment9.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment8.addChild(Segment9);
		Segment9.cubeList.add(new ModelBox(Segment9, 42, 32, -0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F, false));

		Leg4 = new RendererModel(this);
		Leg4.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg4, 0.0F, 3.1416F, 0.0F);

		Segment10 = new RendererModel(this);
		Segment10.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg4.addChild(Segment10);
		Segment10.cubeList.add(new ModelBox(Segment10, 22, 32, -1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		Segment11 = new RendererModel(this);
		Segment11.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment10.addChild(Segment11);
		Segment11.cubeList.add(new ModelBox(Segment11, 32, 32, -1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F, false));

		Segment12 = new RendererModel(this);
		Segment12.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment11.addChild(Segment12);
		Segment12.cubeList.add(new ModelBox(Segment12, 42, 32, -0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F, false));

		Leg5 = new RendererModel(this);
		Leg5.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg5, 0.0F, 2.0944F, 0.0F);

		Segment13 = new RendererModel(this);
		Segment13.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg5.addChild(Segment13);
		Segment13.cubeList.add(new ModelBox(Segment13, 22, 32, -1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		Segment14 = new RendererModel(this);
		Segment14.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment13.addChild(Segment14);
		Segment14.cubeList.add(new ModelBox(Segment14, 32, 32, -1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F, false));

		Segment15 = new RendererModel(this);
		Segment15.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment14.addChild(Segment15);
		Segment15.cubeList.add(new ModelBox(Segment15, 42, 32, -0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F, false));

		Leg6 = new RendererModel(this);
		Leg6.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(Leg6, 0.0F, 1.0472F, 0.0F);

		Segment16 = new RendererModel(this);
		Segment16.setRotationPoint(0.0F, -20.0F, -7.0F);
		Leg6.addChild(Segment16);
		Segment16.cubeList.add(new ModelBox(Segment16, 22, 32, -1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F, false));

		Segment17 = new RendererModel(this);
		Segment17.setRotationPoint(0.0F, 6.0F, 0.0F);
		Segment16.addChild(Segment17);
		Segment17.cubeList.add(new ModelBox(Segment17, 32, 32, -1.0F, 0.0F, -1.0F, 2, 5, 2, 0.0F, false));

		Segment18 = new RendererModel(this);
		Segment18.setRotationPoint(0.0F, 5.0F, 0.0F);
		Segment17.addChild(Segment18);
		Segment18.cubeList.add(new ModelBox(Segment18, 42, 32, -0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F, false));
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bb_main.render(scale);
		Eye1.render(scale);
		Eye2.render(scale);
		Eye3.render(scale);
		Eye4.render(scale);
		Eye5.render(scale);
		Eye6.render(scale);
		Eye7.render(scale);
		Eye8.render(scale);
		Leg1.render(scale);
		Leg2.render(scale);
		Leg3.render(scale);
		Leg4.render(scale);
		Leg5.render(scale);
		Leg6.render(scale);
	}
	
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);		
		oscillationTimer = (entityIn.ticksExisted + partialTick) * .5f;
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {	
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
}
