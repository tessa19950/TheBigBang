package com.homebrewCult.TheBigBang.items.model;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.ResourceLocation;

public class OldWoodenStaffModel extends Model {
	
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/old_wooden_staff.png");
	private final RendererModel pole;
	private final RendererModel top_pivot;
	private final RendererModel top_corner_pivot;
	private final RendererModel start_corner_pivot;
	private final RendererModel start_pivot;

	public OldWoodenStaffModel() {
		textureWidth = 32;
		textureHeight = 32;

		pole = new RendererModel(this);
		pole.setRotationPoint(0.0F, 0.0F, 0.0F);
		pole.cubeList.add(new ModelBox(pole, 0, 0, -0.5F, 9.0F, -0.5F, 1, 18, 1, 0.01F, false));
		pole.cubeList.add(new ModelBox(pole, 20, 8, -1.5F, 7.5F, -1.5F, 3, 2, 3, 0.0F, false));

		top_pivot = new RendererModel(this);
		top_pivot.setRotationPoint(0.0F, -3.0F, 0.0F);
		pole.addChild(top_pivot);
		setRotationAngle(top_pivot, 0.0F, 0.0F, 0.7854F);
		top_pivot.cubeList.add(new ModelBox(top_pivot, 4, 0, -1.5F, -1.5F, -2.0F, 6, 4, 4, 0.01F, false));

		top_corner_pivot = new RendererModel(this);
		top_corner_pivot.setRotationPoint(0.0F, -3.0F, 0.0F);
		pole.addChild(top_corner_pivot);
		setRotationAngle(top_corner_pivot, 0.0F, 0.0F, 0.7854F);
		top_corner_pivot.cubeList.add(new ModelBox(top_corner_pivot, 4, 8, -1.0F, 2.5F, -1.5F, 3, 4, 3, 0.01F, false));

		start_corner_pivot = new RendererModel(this);
		start_corner_pivot.setRotationPoint(0.0F, 3.5F, 0.0F);
		pole.addChild(start_corner_pivot);
		setRotationAngle(start_corner_pivot, 0.0F, 0.0F, -0.7854F);
		start_corner_pivot.cubeList.add(new ModelBox(start_corner_pivot, 4, 15, -1.5F, -3.5F, -1.0F, 2, 7, 2, 0.01F, false));
		start_corner_pivot.cubeList.add(new ModelBox(start_corner_pivot, 24, 4, -3.5F, 1.5F, -1.0F, 2, 2, 2, 0.01F, false));

		start_pivot = new RendererModel(this);
		start_pivot.setRotationPoint(0.0F, 7.75F, 0.0F);
		pole.addChild(start_pivot);
		setRotationAngle(start_pivot, 0.0F, 0.0F, -0.7854F);
		
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
