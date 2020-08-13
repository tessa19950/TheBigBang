package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.TheBigBang;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.ResourceLocation;

//Made with Blockbench 3.6.5
//Exported for Minecraft version 1.14
//Paste this class into your mod and generate all required imports

public class ThrowingStarModel extends Model {
	
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/item/steely_projectile.png");
	private final RendererModel modelRenderer;

	public ThrowingStarModel() {
		textureWidth = 16;
		textureHeight = 16;

		modelRenderer = new RendererModel(this);
		modelRenderer.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(modelRenderer, 0.7854F, 0.0F, 0.0F);
		modelRenderer.cubeList.add(new ModelBox(modelRenderer, 0, 0, 0.0F, -8.0F, -8.0F, 0, 16, 16, 0.0F, false));
	}

	public void renderer() {
		this.modelRenderer.render(0.05f);
	}
	   
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
