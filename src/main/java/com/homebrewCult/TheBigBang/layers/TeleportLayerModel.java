package com.homebrewCult.TheBigBang.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class TeleportLayerModel extends Model {
    private final ModelRenderer root;

	public TeleportLayerModel() {
        super(RenderType::getEntitySolid);
        textureWidth = 64;
        textureHeight = 64;

        root = new ModelRenderer(this);
        root.setRotationPoint(0.0F, 24.0F, 0.0F);
        root.addBox(-3.0F, -44.0F, -3.0F, 6, 8, 6, 0.0F, false);
        root.addBox(-5.0F, -36.0F, -5.0F, 10, 8, 10, 0.0F, false);
        root.addBox(-8.0F, -27.0F, -8.0F, 16, 18, 16, 1.0F, false);
        root.addBox(-5.0F, -8.0F, -5.0F, 10, 8, 10, 0.0F, false);
        root.addBox(-3.0F, 0.0F, -3.0F, 6, 8, 6, 0.0F, false);
    }

    public void render(float time) {
        //root.render(1);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {

    }
}
