package com.homebrewCult.TheBigBang.entities.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;

public class BigBangArrowModel extends Model {
	
	private final RendererModel bb_main;
	private final RendererModel back_r1;
	private final RendererModel plane1_r1;

	public BigBangArrowModel() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		back_r1 = new RendererModel(this);
		back_r1.setRotationPoint(0.0F, 0.0F, 8.0F);
		bb_main.addChild(back_r1);
		setRotationAngle(back_r1, 0.0F, 0.0F, 0.7854F);
		back_r1.cubeList.add(new ModelBox(back_r1, 0, 0, -3.5F, -3.5F, 0.0F, 7, 7, 0, 0.0F, false));
		back_r1.cubeList.add(new ModelBox(back_r1, 0, 0, 0.0F, -3.5F, -23.0F, 0, 7, 32, 0.0F, false));

		plane1_r1 = new RendererModel(this);
		plane1_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		bb_main.addChild(plane1_r1);
		setRotationAngle(plane1_r1, 0.0F, 0.0F, -0.7854F);
		plane1_r1.cubeList.add(new ModelBox(plane1_r1, 0, 0, 0.0F, -3.5F, -15.0F, 0, 7, 32, 0.0F, false));
	}

	public void render() {
		bb_main.render(1);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
