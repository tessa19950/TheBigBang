package com.homebrewCult.TheBigBang.blocks.render;

import com.homebrewCult.TheBigBang.blocks.DivineAltarTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.Objects;

public class DivineAltarTileRenderer<T extends DivineAltarTile> extends TileEntityRenderer<DivineAltarTile> {

    public DivineAltarTileRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(DivineAltarTile divineAltarTile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
        int x = divineAltarTile.getPos().getX();
        int y = divineAltarTile.getPos().getY();
        int z = divineAltarTile.getPos().getZ();

        ItemStack stack = divineAltarTile.getClientInventory().get(0);
        if(divineAltarTile.getBlockState().get(AbstractFurnaceBlock.LIT)) {
            GlStateManager.pushMatrix();
            float time = Objects.requireNonNull(divineAltarTile.getWorld()).getGameTime() + partialTicks;
            float posX = (float) x + 0.5F;
            float hoverOffset = MathHelper.sin(time * 0.1F) * 0.05F;
            float posY = (float) y + 0.9F + hoverOffset;
            float posZ = (float) z + 0.5F;
            float rotation = time * 2F;
            GlStateManager.translatef(posX, posY, posZ);
            GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(rotation, 0.0F, 0.0F, 1.0F);
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, i, i1, matrixStack, iRenderTypeBuffer);
            GlStateManager.popMatrix();
        }
    }
}
