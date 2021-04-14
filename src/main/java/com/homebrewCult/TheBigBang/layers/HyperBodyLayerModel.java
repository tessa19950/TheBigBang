package com.homebrewCult.TheBigBang.layers;

import com.homebrewCult.TheBigBang.TheBigBang;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class HyperBodyLayerModel extends Model {
	private final RendererModel coffin_front;
	private final RendererModel coffin_back;

	public HyperBodyLayerModel() {
		textureWidth = 128;
		textureHeight = 64;

		coffin_front = new RendererModel(this);
		coffin_front.setRotationPoint(1.0F, 24.0F, 0.0F);
		coffin_front.cubeList.add(new ModelBox(coffin_front, 0, 0, -10.0F, -33.0F, -5.0F, 18, 15, 5, 0.0F, false));
		coffin_front.cubeList.add(new ModelBox(coffin_front, 120, 0, -11.5F, -31.0F, -6.0F, 3, 13, 1, 0.0F, false));
		coffin_front.cubeList.add(new ModelBox(coffin_front, 111, 0, 8.0F, -20.5F, -6.0F, 1, 8, 1, 0.0F, false));
		coffin_front.cubeList.add(new ModelBox(coffin_front, 103, 0, 8.0F, -25.0F, -10.0F, 1, 6, 3, 0.0F, false));
		coffin_front.cubeList.add(new ModelBox(coffin_front, 93, 0, 8.0F, -23.5F, -7.0F, 1, 3, 4, 0.0F, false));
		coffin_front.cubeList.add(new ModelBox(coffin_front, 0, 40, -12.0F, -18.0F, -7.0F, 20, 5, 7, 0.0F, false));
		coffin_front.cubeList.add(new ModelBox(coffin_front, 0, 52, -10.0F, -13.0F, -7.0F, 20, 5, 7, 0.0F, true));
		coffin_front.cubeList.add(new ModelBox(coffin_front, 55, 38, -10.0F, -8.0F, -5.0F, 18, 8, 5, 0.0F, false));

		coffin_back = new RendererModel(this);
		coffin_back.setRotationPoint(1.0F, 24.0F, 0.0F);
		coffin_back.cubeList.add(new ModelBox(coffin_back, 0, 20, -10.0F, -33.0F, 0.0F, 18, 15, 5, 0.0F, false));
		coffin_back.cubeList.add(new ModelBox(coffin_back, 48, 2, -12.0F, -18.0F, 0.0F, 20, 5, 5, 0.0F, true));
		coffin_back.cubeList.add(new ModelBox(coffin_back, 48, 14, -10.0F, -13.0F, 0.0F, 20, 5, 5, 0.0F, false));
		coffin_back.cubeList.add(new ModelBox(coffin_back, 55, 25, -10.0F, -8.0F, 0.0F, 18, 8, 5, 0.0F, false));
	}

	public void render(float hyperBodyTime) {
		float f1 = 0;
		if(hyperBodyTime < 10) {
			f1 = MathHelper.sin((float)Math.PI * 1.5f);
		} else if (hyperBodyTime > 20) {
			f1 = (float)Math.PI * 2f;
		} else {
			float f = MathHelper.clamp(hyperBodyTime - 10, 0f, 30f) * 0.4f;
			f1 = MathHelper.sin(((float)Math.PI * 1.5f) + f);
		}
		coffin_front.offsetZ = MathHelper.clamp(f1 * 12f, -100f, 0f);
		coffin_back.offsetZ = MathHelper.clamp(-f1 * 12f, 0f, 100f);
		coffin_front.render(1);
		coffin_back.render(1);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
