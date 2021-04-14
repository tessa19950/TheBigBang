package com.homebrewCult.TheBigBang.layers;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.math.MathHelper;

public class GenesisLayerModel extends Model {
	private final RendererModel head;
	private final RendererModel body;
	private final RendererModel skirt_front;
	private final RendererModel skirt_back;
	private final RendererModel leftArm;
	private final RendererModel rightArm;
	private final RendererModel leftUpperWing;
	private final RendererModel leftLowerWing;
	private final RendererModel rightUpperWing;
	private final RendererModel rightLowerWing;

	public GenesisLayerModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new RendererModel(this);
		head.setRotationPoint(0.0F, 1.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F, false));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 16, -4.0F, 1.0F, -2.0F, 8, 12, 4, 0.1F, false));

		skirt_front = new RendererModel(this);
		skirt_front.setRotationPoint(0.0F, 11.0F, 0.0F);
		body.addChild(skirt_front);
		setRotationAngle(skirt_front, -0.1745F, 0.0F, 0.0F);
		skirt_front.cubeList.add(new ModelBox(skirt_front, 38, 17, -4.0F, 0.0F, -2.0F, 8, 12, 3, 0.3F, false));

		skirt_back = new RendererModel(this);
		skirt_back.setRotationPoint(0.0F, 11.0F, 0.0F);
		body.addChild(skirt_back);
		setRotationAngle(skirt_back, 0.2618F, 0.0F, 0.0F);
		skirt_back.cubeList.add(new ModelBox(skirt_back, 38, 32, -4.0F, 0.0F, -1.0F, 8, 12, 3, 0.2F, false));

		leftArm = new RendererModel(this);
		leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, 0.0F, 0.0F, 0.1745F);
		leftArm.cubeList.add(new ModelBox(leftArm, 24, 16, -2.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F, false));

		rightArm = new RendererModel(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, 0.0F, 0.0F, -1.5708F);
		rightArm.cubeList.add(new ModelBox(rightArm, 24, 16, -1.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F, true));

		leftUpperWing = new RendererModel(this);
		leftUpperWing.setRotationPoint(2.0F, 2.0F, 3.0F);
		setRotationAngle(leftUpperWing, 0.0F, 0.0F, -0.7854F);
		leftUpperWing.cubeList.add(new ModelBox(leftUpperWing, 0, 32, -1.0F, -3.0F, 0.0F, 19, 16, 0, 0.0F, true));

		leftLowerWing = new RendererModel(this);
		leftLowerWing.setRotationPoint(18.0F, -3.0F, 0.0F);
		leftUpperWing.addChild(leftLowerWing);
		setRotationAngle(leftLowerWing, 0.0F, 0.0436F, 1.0472F);
		leftLowerWing.cubeList.add(new ModelBox(leftLowerWing, 0, 48, 0.0F, 0.0F, 0.0F, 32, 16, 0, 0.0F, true));

		rightUpperWing = new RendererModel(this);
		rightUpperWing.setRotationPoint(-2.0F, 2.0F, 3.0F);
		setRotationAngle(rightUpperWing, 0.0F, 0.0F, 0.1745F);
		rightUpperWing.cubeList.add(new ModelBox(rightUpperWing, 0, 32, -18.0F, -3.0F, 0.0F, 19, 16, 0, 0.0F, false));

		rightLowerWing = new RendererModel(this);
		rightLowerWing.setRotationPoint(-18.0F, -3.0F, 0.0F);
		rightUpperWing.addChild(rightLowerWing);
		setRotationAngle(rightLowerWing, 3.1416F, -0.3491F, -2.3562F);
		rightLowerWing.cubeList.add(new ModelBox(rightLowerWing, 0, 48, -32.0F, 0.0F, 0.0F, 32, 16, 0, 0.0F, false));
	}

	public void render(float genesisTime) {
		float pct = genesisTime / 45F;
		float sinPct = MathHelper.sin(pct * ((float)Math.PI / 2));
		
		float upWingZ = MathHelper.lerp(sinPct, -0.2F, 0.8F);
		float lowWingY = MathHelper.lerp(sinPct, -2.6F, 0.0F);
		float lowWingZ = MathHelper.lerp(sinPct, 0F, 1.0F);
		float armZ = MathHelper.lerp(sinPct, 0.2F, 1.6F);		
		
		this.setRotationAngle(this.leftUpperWing, 0, 0, -upWingZ);
		this.setRotationAngle(this.rightUpperWing, 0, 0, upWingZ);
		this.setRotationAngle(this.leftLowerWing, 0, -lowWingY, lowWingZ);
		this.setRotationAngle(this.rightLowerWing, 0, lowWingY, -lowWingZ);
		this.setRotationAngle(this.leftArm, 0, 0, armZ);
		this.setRotationAngle(this.rightArm, 0, 0, -armZ);
		this.setRotationAngle(this.skirt_front, -0.2F - MathHelper.sin(pct * 30F) * 0.2F, 0, MathHelper.sin(pct * 20F) * 0.1F);
		this.setRotationAngle(this.skirt_back, 0.2F + MathHelper.sin(pct * 30F) * 0.2F, 0, -MathHelper.sin(pct * 20F) * 0.1F);
		
		head.render(1);
		body.render(1);
		leftUpperWing.render(1);
		rightUpperWing.render(1);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
