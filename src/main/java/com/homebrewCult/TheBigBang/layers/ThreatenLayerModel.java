package com.homebrewCult.TheBigBang.layers;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.math.MathHelper;

public class ThreatenLayerModel extends Model {

	private final RendererModel tail;
	private final RendererModel body;
	private final RendererModel leftArm;
	private final RendererModel leftLowerArm;
	private final RendererModel rightArm;
	private final RendererModel rightLowerArm;
	private final RendererModel leftEye;
	private final RendererModel rightEye;

	public ThreatenLayerModel() {
		textureWidth = 64;
		textureHeight = 64;

		tail = new RendererModel(this);
		tail.setRotationPoint(0.0F, 0.0F, 7.0F);
		tail.cubeList.add(new ModelBox(tail, 0, 38, -6.0F, -16.0F, -6.0F, 12, 16, 10, 0.0F, false));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, -14.0F, 0.0F);
		tail.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -8.0F, -24.0F, -8.0F, 16, 24, 14, 0.0F, false));

		leftArm = new RendererModel(this);
		leftArm.setRotationPoint(3.0F, -9.0F, -1.0F);
		body.addChild(leftArm);

		leftLowerArm = new RendererModel(this);
		leftLowerArm.setRotationPoint(5.5F, 0.5F, -0.5F);
		leftArm.addChild(leftLowerArm);
		setRotationAngle(leftLowerArm, 0.0F, 0.0F, -1.5708F);
		leftLowerArm.cubeList.add(new ModelBox(leftLowerArm, 44, 38, -2.5F, -2.5F, -2.5F, 5, 12, 5, 0.0F, false));

		rightArm = new RendererModel(this);
		rightArm.setRotationPoint(-3.0F, -9.0F, -1.0F);
		body.addChild(rightArm);

		rightLowerArm = new RendererModel(this);
		rightLowerArm.setRotationPoint(-5.5F, 0.5F, -0.5F);
		rightArm.addChild(rightLowerArm);
		setRotationAngle(rightLowerArm, 0.0F, 0.0F, 1.5708F);
		rightLowerArm.cubeList.add(new ModelBox(rightLowerArm, 44, 38, -2.5F, -2.5F, -2.5F, 5, 12, 5, 0.0F, false));

		leftEye = new RendererModel(this);
		leftEye.setRotationPoint(4.0F, -18.0F, -8.1F);
		body.addChild(leftEye);
		leftEye.cubeList.add(new ModelBox(leftEye, 0, 0, -3.5F, -2.0F, 0.0F, 7, 4, 0, 0.0F, false));

		rightEye = new RendererModel(this);
		rightEye.setRotationPoint(-4.0F, -18.0F, -8.1F);
		body.addChild(rightEye);
		rightEye.cubeList.add(new ModelBox(rightEye, 0, 4, -3.5F, -2.0F, 0.0F, 7, 4, 0, 0.0F, false));
	}
	
	public void render(float time) {
		float p = (float)Math.PI/2;
		setRotationAngle(tail, -1 + MathHelper.sin(MathHelper.clamp(time/10F, 0, p)) * 0.75f, 0, 0);
		setRotationAngle(body, MathHelper.sin(MathHelper.clamp(time/15F, 0, p)) * 0.75f, 0, 0);
		setRotationAngle(leftArm, 0, 0, 2 - MathHelper.sin(MathHelper.clamp(time/10, 0, p)) * 2.8f);
		setRotationAngle(rightArm, 0, 0, -leftArm.rotateAngleZ);
		tail.render(1);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
