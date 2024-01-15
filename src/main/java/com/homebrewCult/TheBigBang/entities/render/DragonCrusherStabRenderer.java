package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.DragonCrusherStabEntity;
import com.homebrewCult.TheBigBang.entities.model.DragonCrusherStabModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class DragonCrusherStabRenderer extends EntityRenderer<DragonCrusherStabEntity> {

    public static final DragonCrusherStabModel MODEL = new DragonCrusherStabModel();
    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/dragon_crusher_stab_entity.png");

    protected DragonCrusherStabRenderer(EntityRendererManager renderManager) { super(renderManager); }

    public DragonCrusherStabRenderer(EntityRendererManager manager, float scaleIn) {
        super(manager);
    }

    @Override
    public void render(DragonCrusherStabEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        float ticksExisted = entity.ticksExisted + partialTicks;
        float pi = (float)Math.PI;
        GlStateManager.pushMatrix();
        GlStateManager.translatef((float)entity.getPosX(), (float)entity.getPosY(), (float)entity.getPosZ());
        GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch), 0.0F, 0.0F, 1.0F);
        GlStateManager.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float k = MathHelper.sin(MathHelper.clamp(ticksExisted * 0.2F, 0, pi/2F)) * 2.0F;
        GlStateManager.translatef(0, -2F, -k);
        float scale = 0.08F;
        GlStateManager.scalef(scale, scale, scale);

        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        float i = MathHelper.clamp(-pi * 1.5F + (ticksExisted * 0.3F), -pi * 1.5F, pi * 0.5F);
        float j = (1 - MathHelper.sin(i)) * 0.5F;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, j);
        this.bindEntityTexture(entity);
        //MODEL.render(ticksExisted);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();

        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public static class RenderFactory implements IRenderFactory<DragonCrusherStabEntity> {
        public EntityRenderer<? super DragonCrusherStabEntity> createRenderFor(EntityRendererManager manager) {
            return new DragonCrusherStabRenderer(manager);
        }
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(DragonCrusherStabEntity entity) { return TEXTURE_LOCATION; }

    private void bindEntityTexture(Entity entity) {

    }
}
