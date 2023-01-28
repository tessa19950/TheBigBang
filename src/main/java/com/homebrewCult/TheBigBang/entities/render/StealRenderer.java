package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.StealEntity;
import com.homebrewCult.TheBigBang.entities.model.StealModel;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class StealRenderer extends EntityRenderer<StealEntity> {

    public static final StealModel MODEL = new StealModel();
    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/steal_entity.png");

    public StealRenderer(EntityRendererManager manager) {
        super(manager);
    }

    public StealRenderer(EntityRendererManager manager, float scaleIn) {
        super(manager);
    }

    @Override
    public void doRender(StealEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        float stealTime = entity.ticksExisted + partialTicks;
        if(stealTime >= 0 && stealTime <= 40) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float)x, (float)y + 1.5f, (float)z);
            float scale = 0.0625F;
            GlStateManager.scalef(scale, scale, scale);
            GlStateManager.rotatef(180, 0, 0, 1);
            GlStateManager.rotatef(entityYaw + 180, 0, 1, 0);

            this.bindEntityTexture(entity);
            int j = 15728880 % 65536;
            int k = 15728880 / 65536;
            GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, (float) j, (float) k);
            GlStateManager.enableBlend();
            float fade = MathHelper.sin((stealTime / 40F) * (float)Math.PI/2);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F - fade);

            MODEL.renderAttack(entity, stealTime);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }

    public static class RenderFactory implements IRenderFactory<StealEntity> {
        public EntityRenderer<? super StealEntity> createRenderFor(EntityRendererManager manager) {
            return new StealRenderer(manager);
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(StealEntity entity) {
        return TEXTURE_LOCATION;
    }
}
