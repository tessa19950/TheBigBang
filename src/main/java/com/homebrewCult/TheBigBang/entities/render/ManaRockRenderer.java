package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.ManaRockEntity;
import com.homebrewCult.TheBigBang.entities.model.ManaRockModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.HangingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class ManaRockRenderer extends EntityRenderer<ManaRockEntity> {

    public static final ManaRockModel MODEL = new ManaRockModel();
    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/mana_rock.png");

    public ManaRockRenderer(EntityRendererManager manager) {
        super(manager);
    }

    public ManaRockRenderer(EntityRendererManager manager, float scaleIn) {
        super(manager);
    }

    @Override
    public void render(ManaRockEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        GlStateManager.pushMatrix();
        float heightOffset = ((float)Math.sin((entity.ticksExisted + entity.getEntityId() + partialTicks) * 0.1F) * 0.1F) + 0.5F;
        GlStateManager.translatef((float)entity.getPosX(), (float)entity.getPosY() + heightOffset, (float)entity.getPosZ());
        float rotation = (entity.ticksExisted + partialTicks) * 1f;
        GlStateManager.rotatef(rotation, 0.0F, 1.0F, 0.0F);
        this.bindEntityTexture(entity);
        //MODEL.render(entity,0.06F);
        GlStateManager.popMatrix();
        this.renderLeash(entity, entity.getPosX(), entity.getPosY(), entity.getPosZ(), heightOffset, partialTicks);

        super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(ManaRockEntity entity) {
        return TEXTURE_LOCATION;
    }

    public static class RenderFactory implements IRenderFactory<ManaRockEntity> {
        public EntityRenderer<? super ManaRockEntity> createRenderFor(EntityRendererManager manager) {
            return new ManaRockRenderer(manager);
        }
    }

    protected void renderLeash(ManaRockEntity entityLivingIn, double x, double y, double z, float entityYaw, float partialTicks) {
        Entity leashHolder = entityLivingIn.getLeashHolder();
        if (leashHolder != null) {
            y = y + entityYaw - 1;
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            double d0 = MathHelper.lerp(partialTicks * 0.5F, leashHolder.rotationYaw, leashHolder.prevRotationYaw) * ((float)Math.PI / 180F);
            double d1 = MathHelper.lerp(partialTicks * 0.5F, leashHolder.rotationPitch, leashHolder.prevRotationPitch) * ((float)Math.PI / 180F);
            double d2 = Math.cos(d0);
            double d3 = Math.sin(d0);
            double d4 = Math.sin(d1);
            if (leashHolder instanceof HangingEntity) {
                d2 = 0.0D;
                d3 = 0.0D;
                d4 = -1.0D;
            }

            double d5 = Math.cos(d1);
            double d6 = MathHelper.lerp(partialTicks, leashHolder.prevPosX, leashHolder.getPosX()) - d2 * 0.7D - d3 * 0.5D * d5;
            double d7 = -entityYaw + 0.6D + MathHelper.lerp(partialTicks, leashHolder.prevPosY + (double)leashHolder.getEyeHeight() * 0.7D, leashHolder.getPosY() + (double)leashHolder.getEyeHeight() * 0.7D) - d4 * 0.5D - 0.25D;
            double d8 = MathHelper.lerp(partialTicks, leashHolder.prevPosZ, leashHolder.getPosZ()) - d3 * 0.7D + d2 * 0.5D * d5;
            double d9 = (double)(MathHelper.lerp(partialTicks, entityLivingIn.renderYawOffset, entityLivingIn.prevRenderYawOffset) * ((float)Math.PI / 180F)) + (Math.PI / 2D);

            Vec3d offset = leashHolder.getPositionVec().subtract(entityLivingIn.getPositionVec()).normalize();
            d2 = offset.x * 0.3; //Math.cos(d9) * 0.2D;
            d3 = offset.z * 0.3; //Math.sin(d9) * 0.2D;
            double d10 = MathHelper.lerp(partialTicks, entityLivingIn.prevPosX, entityLivingIn.getPosX()) + d2;
            double d11 = MathHelper.lerp(partialTicks, entityLivingIn.prevPosY, entityLivingIn.getPosY());
            double d12 = MathHelper.lerp(partialTicks, entityLivingIn.prevPosZ, entityLivingIn.getPosZ()) + d3;
            x = x + d2;
            z = z + d3;
            double d13 = (float)(d6 - d10);
            double d14 = (float)(d7 - d11);
            double d15 = (float)(d8 - d12);
            GlStateManager.disableTexture();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();

            bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
            for(int j = 0; j <= 24; ++j) {
                float f = 0.5F;
                float f1 = 0.4F;
                float f2 = 0.3F;
                if (j % 2 == 0) {
                    f *= 0.7F;
                    f1 *= 0.7F;
                    f2 *= 0.7F;
                }

                float f3 = (float)j / 24.0F;
                bufferbuilder.pos(x + d13 * (double)f3 + 0.0D, y + d14 * (double)(f3 * f3 + f3) * 0.5D + (double)((24.0F - (float)j) / 18.0F + 0.125F), z + d15 * (double)f3).color(f, f1, f2, 1.0F).endVertex();
                bufferbuilder.pos(x + d13 * (double)f3 + 0.025D, y + d14 * (double)(f3 * f3 + f3) * 0.5D + (double)((24.0F - (float)j) / 18.0F + 0.125F) + 0.025D, z + d15 * (double)f3).color(f, f1, f2, 1.0F).endVertex();
            }

            tessellator.draw();
            bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
            for(int k = 0; k <= 24; ++k) {
                float f4 = 0.5F;
                float f5 = 0.4F;
                float f6 = 0.3F;
                if (k % 2 == 0) {
                    f4 *= 0.7F;
                    f5 *= 0.7F;
                    f6 *= 0.7F;
                }
                float f7 = (float)k / 24.0F;
                bufferbuilder.pos(x + d13 * (double)f7 + 0.0D, y + d14 * (double)(f7 * f7 + f7) * 0.5D + (double)((24.0F - (float)k) / 18.0F + 0.125F) + 0.025D, z + d15 * (double)f7).color(f4, f5, f6, 1.0F).endVertex();
                bufferbuilder.pos(x + d13 * (double)f7 + 0.025D, y + d14 * (double)(f7 * f7 + f7) * 0.5D + (double)((24.0F - (float)k) / 18.0F + 0.125F), z + d15 * (double)f7 + 0.025D).color(f4, f5, f6, 1.0F).endVertex();
            }

            tessellator.draw();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture();
            GlStateManager.enableCull();
        }
    }

    private void bindEntityTexture(Entity entity) {

    }
}
