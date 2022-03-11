package com.homebrewCult.TheBigBang.layers;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;

public class TeleportLayerModel extends Model {
    private final RendererModel root;

	public TeleportLayerModel() {
        textureWidth = 64;
        textureHeight = 64;

        root = new RendererModel(this);
        root.setRotationPoint(0.0F, 24.0F, 0.0F);
        root.cubeList.add(new ModelBox(root, 0, 0, -3.0F, -44.0F, -3.0F, 6, 8, 6, 0.0F, false));
        root.cubeList.add(new ModelBox(root, 0, 0, -5.0F, -36.0F, -5.0F, 10, 8, 10, 0.0F, false));
        root.cubeList.add(new ModelBox(root, 0, 0, -8.0F, -27.0F, -8.0F, 16, 18, 16, 1.0F, false));
        root.cubeList.add(new ModelBox(root, 0, 0, -5.0F, -8.0F, -5.0F, 10, 8, 10, 0.0F, false));
        root.cubeList.add(new ModelBox(root, 0, 0, -3.0F, 0.0F, -3.0F, 6, 8, 6, 0.0F, false));
    }

    public void render(float time) {
        root.render(1);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
