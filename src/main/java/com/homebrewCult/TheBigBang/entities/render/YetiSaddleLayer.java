package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.AbstractYetiEntity;
import com.homebrewCult.TheBigBang.entities.mob.RibbonPigEntity;
import com.homebrewCult.TheBigBang.entities.mob.YetiEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractYetiModel;
import com.homebrewCult.TheBigBang.entities.model.RibbonPigModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class YetiSaddleLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer {
    public static final ResourceLocation YETI_SADDLE_TEXTURE = new ResourceLocation(TheBigBang.MODID, "textures/entity/yeti_saddle.png");
    private final AbstractYetiModel<AbstractYetiEntity> yetiModel = new AbstractYetiModel<>();

    public YetiSaddleLayer(IEntityRenderer<?, ?> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(Entity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        if(entityIn instanceof AbstractYetiEntity) {
            if (((AbstractYetiEntity) entityIn).getSaddled()) {
                this.bindTexture(YETI_SADDLE_TEXTURE);
                this.getEntityModel().setModelAttributes(this.yetiModel);
                this.yetiModel.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
            }
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
