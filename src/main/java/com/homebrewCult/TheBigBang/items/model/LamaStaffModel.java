package com.homebrewCult.TheBigBang.items.model;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.ResourceLocation;

public class LamaStaffModel extends Model {
	
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MODID, "textures/entity/lama_staff.png");
	private final RendererModel pole;
	private final RendererModel orb_pivot;
	private final RendererModel wrap2_pivot;
	private final RendererModel wrap_pivot;

	public LamaStaffModel() {
		textureWidth = 32;
		textureHeight = 32;

		pole = new RendererModel(this);
		pole.setRotationPoint(0.0F, 0.0F, 0.0F);
		pole.cubeList.add(new ModelBox(pole, 4, 0, -0.5F, 15.0F, -0.5F, 1, 12, 1, 0.01F, false));
		pole.cubeList.add(new ModelBox(pole, 0, 0, -0.5F, 3.0F, -0.5F, 1, 12, 1, 0.01F, false));

		orb_pivot = new RendererModel(this);
		orb_pivot.setRotationPoint(0.0F, 0.0F, 0.0F);
		pole.addChild(orb_pivot);
		setRotationAngle(orb_pivot, -0.7854F, 0.0F, 0.6545F);
		orb_pivot.cubeList.add(new ModelBox(orb_pivot, 10, 4, 0.05F, 0.0F, 0.0F, 1, 1, 1, 0.0F, false));

		wrap2_pivot = new RendererModel(this);
		wrap2_pivot.setRotationPoint(0.0F, 11.5F, 0.0F);
		pole.addChild(wrap2_pivot);
		setRotationAngle(wrap2_pivot, 0.0F, 0.0F, -0.5236F);
		wrap2_pivot.cubeList.add(new ModelBox(wrap2_pivot, 8, 0, -1.3F, 0.0F, -1.0F, 2, 1, 2, 0.0F, false));

		wrap_pivot = new RendererModel(this);
		wrap_pivot.setRotationPoint(0.0F, 7.5F, 0.0F);
		pole.addChild(wrap_pivot);
		setRotationAngle(wrap_pivot, 0.0F, 0.0F, -0.5236F);
		wrap_pivot.cubeList.add(new ModelBox(wrap_pivot, 8, 0, -1.3F, 0.0F, -1.0F, 2, 1, 2, 0.0F, false));
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
