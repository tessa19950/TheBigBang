package com.homebrewCult.TheBigBang.layers;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.math.MathHelper;

public class DragonCrusherLayerModel extends Model {
	private final RendererModel tail1;
	private final RendererModel tail2;
	private final RendererModel tail3;
	private final RendererModel lowerWing_left;
	private final RendererModel upperWing_left;
	private final RendererModel lowerWing_right;
	private final RendererModel upperWing_right;

	public DragonCrusherLayerModel() {
		textureWidth = 64;
		textureHeight = 64;

		tail1 = new RendererModel(this);
		tail1.setRotationPoint(0.0F, 11.0F, 2.0F);
		setRotationAngle(tail1, -1.7453F, 0.0F, 0.0F);
		tail1.cubeList.add(new ModelBox(tail1, 48, 0, 0.0F, -12.0F, -4.0F, 0, 12, 8, 0.0F, false));

		tail2 = new RendererModel(this);
		tail2.setRotationPoint(0.0F, -12.0F, 0.0F);
		tail1.addChild(tail2);
		setRotationAngle(tail2, 0.2618F, 0.0F, 0.0F);
		tail2.cubeList.add(new ModelBox(tail2, 48, 14, 0.0F, -10.0F, -3.0F, 0, 10, 6, 0.0F, false));

		tail3 = new RendererModel(this);
		tail3.setRotationPoint(0.0F, -9.0F, -0.5F);
		tail2.addChild(tail3);
		setRotationAngle(tail3, -0.3491F, 0.0F, 0.0F);
		tail3.cubeList.add(new ModelBox(tail3, 48, 27, 0.0F, -8.0F, -2.5F, 0, 8, 5, 0.0F, false));

		lowerWing_left = new RendererModel(this);
		lowerWing_left.setRotationPoint(2.0F, 2.0F, 2.0F);
		setRotationAngle(lowerWing_left, 0.3491F, 0.0F, -0.3491F);
		lowerWing_left.cubeList.add(new ModelBox(lowerWing_left, 0, 24, 0.0F, 0.0F, 0.0F, 24, 24, 0, 0.0F, false));

		upperWing_left = new RendererModel(this);
		upperWing_left.setRotationPoint(18.0F, 0.0F, 0.0F);
		lowerWing_left.addChild(upperWing_left);
		setRotationAngle(upperWing_left, 0.0F, -0.5236F, -0.0873F);
		upperWing_left.cubeList.add(new ModelBox(upperWing_left, 0, 0, -6.0F, 0.0F, 0.0F, 24, 24, 0, 0.0F, false));

		lowerWing_right = new RendererModel(this);
		lowerWing_right.setRotationPoint(-2.0F, 2.0F, 2.0F);
		setRotationAngle(lowerWing_right, 0.6981F, 0.0F, 1.0472F);
		lowerWing_right.cubeList.add(new ModelBox(lowerWing_right, 0, 24, -24.0F, 0.0F, 0.0F, 24, 24, 0, 0.0F, true));

		upperWing_right = new RendererModel(this);
		upperWing_right.setRotationPoint(-18.0F, 0.0F, 0.0F);
		lowerWing_right.addChild(upperWing_right);
		setRotationAngle(upperWing_right, 0.1745F, 0.5236F, 0.5236F);
		upperWing_right.cubeList.add(new ModelBox(upperWing_right, 0, 0, -18.0F, 0.0F, 0.0F, 24, 24, 0, 0.0F, true));
	}

	public void render(float time) {
		float anim = MathHelper.clamp(time * 0.05F, 0, 1);
		float ease = anim < 0.5F ? 16F * anim * anim * anim * anim * anim : 1F - (float)Math.pow(-2F * anim + 2F, 5F) / 2F;
		setRotationAngle(lowerWing_left, MathHelper.lerp(ease, 0.2F, 0.8F), 0F, MathHelper.lerp(ease, 0.2F, -1F));
		setRotationAngle(upperWing_left, MathHelper.lerp(ease, 1.0F, 0.0F), MathHelper.lerp(ease, 3F, 0.0F), MathHelper.lerp(ease, 1.2F, -0.6F));
		setRotationAngle(lowerWing_right, lowerWing_left.rotateAngleX, -lowerWing_left.rotateAngleY, -lowerWing_left.rotateAngleZ);
		setRotationAngle(upperWing_right, upperWing_left.rotateAngleX, -upperWing_left.rotateAngleY, -upperWing_left.rotateAngleZ);

		float tailWaveH1 = MathHelper.sin(time * 0.2F) * 0.6F;
		float tailWaveH2 = MathHelper.sin(time * 0.2F - 1.6F) * 1.2F;
		float tailWaveH3 = MathHelper.sin(time * 0.2F - 3.2F) * 1.8F;
		float tailWaveV1 = MathHelper.sin(time * 0.1F) * 0.1F;
		float tailWaveV2 = MathHelper.sin(time * 0.1F) * 0.2F;
		float tailWaveV3 = MathHelper.sin(time * 0.1F) * 0.3F;
		setRotationAngle(tail1, -1.4F + tailWaveV1, tailWaveH1, 0.0F);
		setRotationAngle(tail2, tailWaveV2, 0.0F, tailWaveH2);
		setRotationAngle(tail3, tailWaveV3, 0.0F, tailWaveH3);

		tail1.render(1);
		lowerWing_left.render(1);
		lowerWing_right.render(1);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
