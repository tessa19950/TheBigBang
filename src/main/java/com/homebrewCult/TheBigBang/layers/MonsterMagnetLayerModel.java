package com.homebrewCult.TheBigBang.layers;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;

public class MonsterMagnetLayerModel extends Model{

	private final RendererModel portal;
	private final RendererModel segment1;
	private final RendererModel segment2;
	private final RendererModel segment3;
	private final RendererModel segment4;
	private final RendererModel magnet;

	public MonsterMagnetLayerModel() {
		textureWidth = 64;
		textureHeight = 32;

		portal = new RendererModel(this);
		portal.setRotationPoint(0.0F, -20.0F, 0.0F);
		setRotationAngle(portal, 0.0F, 0.0F, 0.7854F);

		segment1 = new RendererModel(this);
		segment1.setRotationPoint(0.0F, 0.0F, 0.0F);
		portal.addChild(segment1);
		segment1.cubeList.add(new ModelBox(segment1, 0, 0, -3.0F, -24.0F, 0.0F, 27, 21, 0, 0.0F, false));

		segment2 = new RendererModel(this);
		segment2.setRotationPoint(0.0F, 0.0F, 0.0F);
		portal.addChild(segment2);
		setRotationAngle(segment2, 0.0F, 0.0F, 1.5708F);
		segment2.cubeList.add(new ModelBox(segment2, 0, 0, -3.0F, -24.0F, 0.0F, 27, 21, 0, 0.0F, false));

		segment3 = new RendererModel(this);
		segment3.setRotationPoint(0.0F, 0.0F, 0.0F);
		portal.addChild(segment3);
		setRotationAngle(segment3, 0.0F, 0.0F, -3.1416F);
		segment3.cubeList.add(new ModelBox(segment3, 0, 0, -3.0F, -24.0F, 0.0F, 27, 21, 0, 0.0F, false));

		segment4 = new RendererModel(this);
		segment4.setRotationPoint(0.0F, 0.0F, 0.0F);
		portal.addChild(segment4);
		setRotationAngle(segment4, 0.0F, 0.0F, -1.5708F);
		segment4.cubeList.add(new ModelBox(segment4, 0, 0, -3.0F, -24.0F, 0.0F, 27, 21, 0, 0.0F, false));

		magnet = new RendererModel(this);
		magnet.setRotationPoint(0.0F, -20.0F, 0.0F);
		magnet.cubeList.add(new ModelBox(magnet, 0, 24, -1.5F, -4.0F, -4.0F, 3, 3, 5, 0.0F, false));
		magnet.cubeList.add(new ModelBox(magnet, 0, 24, -1.5F, 1.0F, -4.0F, 3, 3, 5, 0.0F, false));
		magnet.cubeList.add(new ModelBox(magnet, 16, 21, -1.5F, -4.0F, 1.0F, 3, 8, 3, 0.0F, false));
	}
	
	public void renderPortal(float time) {
		portal.rotateAngleZ = time/10F;
		portal.render(1);
	}

	public void renderMagnet(float time) {
		magnet.render(1);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
