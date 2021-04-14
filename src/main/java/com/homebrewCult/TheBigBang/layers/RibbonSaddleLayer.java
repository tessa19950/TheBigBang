package com.homebrewCult.TheBigBang.layers;

import com.homebrewCult.TheBigBang.entities.mob.RibbonPigEntity;
import com.homebrewCult.TheBigBang.entities.model.RibbonPigModel;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
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

   public void render(RibbonPigEntity entityIn, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
      if (entityIn.getSaddled()) {
         this.bindTexture(TEXTURE);
         this.getEntityModel().setModelAttributes(this.pigModel);
         this.pigModel.render(entityIn, p_212842_2_, p_212842_3_, p_212842_5_, p_212842_6_, p_212842_7_, p_212842_8_);
      }
   }

   public boolean shouldCombineTextures() {
      return false;
   }
}
