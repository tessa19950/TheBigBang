package com.homebrewCult.TheBigBang.entities.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;

public class GenesisBeamModel extends Model {
	private final RendererModel main;

	public GenesisBeamModel() {
		textureWidth = 32;
		textureHeight = 32;

		main = new RendererModel(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.cubeList.add(new ModelBox(main, 0, 0, -2.0F, -12.0F, -2.0F, 4, 12, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 0, -2.0F, -24.0F, -2.0F, 4, 12, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 0, -2.0F, -36.0F, -2.0F, 4, 12, 4, 0.0F, false));
		main.cubeList.add(new ModelBox(main, 0, 0, -2.0F, -48.0F, -2.0F, 4, 12, 4, 0.0F, false));
	}
	
	public void renderer() {
		main.render(1);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
