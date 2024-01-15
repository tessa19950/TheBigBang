package com.homebrewCult.TheBigBang.layers;

import com.homebrewCult.TheBigBang.entities.mob.RibbonPigEntity;
import com.homebrewCult.TheBigBang.entities.model.RibbonPigModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RibbonSaddleLayer extends LayerRenderer<RibbonPigEntity, RibbonPigModel<RibbonPigEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
   private final RibbonPigModel<RibbonPigEntity> pigModel = new RibbonPigModel<>();

   public RibbonSaddleLayer(IEntityRenderer<RibbonPigEntity, RibbonPigModel<RibbonPigEntity>> p_i50927_1_) {
      super(p_i50927_1_);
   }

   @Override
   public void render(MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, RibbonPigEntity ribbonPigEntity, float v, float v1, float v2, float v3, float v4, float v5) {
      renderCopyCutoutModel(getEntityModel(), pigModel, TEXTURE, matrixStack, iRenderTypeBuffer, i, ribbonPigEntity, v, v1, v3, v4, v5, v2, 1.0F, 1.0F, 1.0F);
   }

   public boolean shouldCombineTextures() {
      return false;
   }
}
