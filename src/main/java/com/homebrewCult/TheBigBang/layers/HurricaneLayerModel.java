package com.homebrewCult.TheBigBang.layers;

import com.homebrewCult.TheBigBang.TheBigBang;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.math.MathHelper;

public class HurricaneLayerModel extends Model {
	private final RendererModel body;
	private final RendererModel wing1;
	private final RendererModel wing2;
	private final RendererModel wing3;
	private final RendererModel wing4;
	private final RendererModel wing5;
	private final RendererModel wing6;
	private final RendererModel[] wings;

	public HurricaneLayerModel() {
		textureWidth = 64;
		textureHeight = 64;

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 1.0F, -6.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -11.0F, -11.0F, -5.0F, 22, 22, 10, 0.0F, false));

		wing1 = new RendererModel(this);
		wing1.setRotationPoint(0.0F, -10.0F, -4.0F);
		wing1.cubeList.add(new ModelBox(wing1, 0, 21, 0.0F, -24.0F, -5.0F, 0, 26, 17, 0.0F, false));

		wing2 = new RendererModel(this);
		wing2.setRotationPoint(11.0F, -7.0F, -4.0F);
		setRotationAngle(wing2, 0.0F, 0.0F, 1.0472F);
		wing2.cubeList.add(new ModelBox(wing2, 0, 21, 0.0F, -24.0F, -5.0F, 0, 26, 17, 0.0F, false));

		wing3 = new RendererModel(this);
		wing3.setRotationPoint(11.0F, 9.0F, -4.0F);
		setRotationAngle(wing3, 0.0F, 0.0F, 2.0944F);
		wing3.cubeList.add(new ModelBox(wing3, 0, 21, 0.0F, -24.0F, -5.0F, 0, 26, 17, 0.0F, false));

		wing4 = new RendererModel(this);
		wing4.setRotationPoint(0.0F, 12.0F, -4.0F);
		setRotationAngle(wing4, 0.0F, 0.0F, -3.1416F);
		wing4.cubeList.add(new ModelBox(wing4, 0, 21, 0.0F, -24.0F, -5.0F, 0, 26, 17, 0.0F, false));

		wing5 = new RendererModel(this);
		wing5.setRotationPoint(-11.0F, 9.0F, -4.0F);
		setRotationAngle(wing5, 0.0F, 0.0F, -2.0944F);
		wing5.cubeList.add(new ModelBox(wing5, 0, 21, 0.0F, -24.0F, -5.0F, 0, 26, 17, 0.0F, false));

		wing6 = new RendererModel(this);
		wing6.setRotationPoint(-11.0F, -7.0F, -4.0F);
		setRotationAngle(wing6, 0.0F, 0.0F, -1.0472F);
		wing6.cubeList.add(new ModelBox(wing6, 0, 21, 0.0F, -24.0F, -5.0F, 0, 26, 17, 0.0F, false));
		
		wings = new RendererModel[] {wing1, wing2, wing3, wing4, wing5, wing6};
	}

	public void render(float pullTime) {
		float pullPct = MathHelper.clamp((pullTime - 1) / 2, 0, 1);
		TheBigBang.LOGGER.debug("Pct = " + pullPct);
		body.rotateAngleZ = MathHelper.clamp(pullTime - 1, 0, 72000) * MathHelper.clamp(pullTime - 1, 0, 72000);
		for(int i = 0; i < 6; ++i) {
			float y = (float) Math.sin(pullTime * 8 + 2 + (i * 0.1f)) * -0.25f;
			float z = (float) Math.sin(pullTime * 8 + (i * 0.1f)) * 0.25f;
			this.setRotationAngle(wings[i], 0, y, (i * 1.047f) + z);
		}
		body.render(1);
		wing1.render(1);
		wing2.render(1);
		wing3.render(1);
		wing4.render(1);
		wing5.render(1);
		wing6.render(1);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
