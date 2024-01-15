package com.homebrewCult.TheBigBang.layers;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.AbstractYetiEntity;
import com.homebrewCult.TheBigBang.entities.mob.YetiEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractYetiModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class YetiSaddleLayer<T extends AbstractYetiEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    public static final ResourceLocation YETI_SADDLE_TEXTURE = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/yeti_saddle.png");
    private final M yetiModel = (M) new AbstractYetiModel<>();

    public YetiSaddleLayer(IEntityRenderer<T, M> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
        renderCopyCutoutModel(getEntityModel(), yetiModel, YETI_SADDLE_TEXTURE, matrixStack, iRenderTypeBuffer, i, entity, v, v1, v3, v4, v5, v2, 1.0F, 1.0F, 1.0F);
    }
}
