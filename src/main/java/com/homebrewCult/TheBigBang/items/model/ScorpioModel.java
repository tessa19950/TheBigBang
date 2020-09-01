package com.homebrewCult.TheBigBang.items.model;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.ResourceLocation;

public class ScorpioModel extends Model {
	
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/scorpio.png");
	private final RendererModel pole;
	private final RendererModel bone;

	public ScorpioModel() {
		textureWidth = 32;
		textureHeight = 32;

		pole = new RendererModel(this);
		pole.setRotationPoint(0.0F, 0.0F, 0.0F);
		pole.cubeList.add(new ModelBox(pole, 4, 0, -0.5F, 15.0F, -0.5F, 1, 12, 1, 0.01F, false));
		pole.cubeList.add(new ModelBox(pole, 0, 0, -0.5F, 3.0F, -0.5F, 1, 12, 1, 0.01F, false));

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 3.0F, 0.0F);
		pole.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.7854F);
		bone.cubeList.add(new ModelBox(bone, 8, 0, -3.0F, -3.0F, 1.2F, 6, 6, 0, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 8, 0, -3.0F, -3.0F, -1.2F, 6, 6, 0, 0.0F, false));
	}
	
	public void renderer() {
		this.pole.render(0.0625F);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
