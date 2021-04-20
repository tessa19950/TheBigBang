package com.homebrewCult.TheBigBang.blocks.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.blocks.DivineAltarTile;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.mojang.blaze3d.platform.GlStateManager;
import com.sun.javafx.geom.Vec3f;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class DivineAltarTileRenderer<T extends DivineAltarTile> extends TileEntityRenderer<DivineAltarTile> {

    @Override
    public void render(DivineAltarTile tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        ItemStack stack = tileEntityIn.getClientInventory().get(0);
        //TheBigBang.LOGGER.debug("Trying to render " + stack.getItem().getDisplayName(stack).getFormattedText());
        if(tileEntityIn.getBlockState().get(AbstractFurnaceBlock.LIT)) {
            GlStateManager.pushMatrix();
            float time = Objects.requireNonNull(tileEntityIn.getWorld()).getGameTime() + partialTicks;
            float posX = (float) x + 0.5F;
            float hoverOffset = MathHelper.sin(time * 0.1F) * 0.05F;
            float posY = (float) y + 0.9F + hoverOffset;
            float posZ = (float) z + 0.5F;
            float rotation = time * 2F;
            GlStateManager.translatef(posX, posY, posZ);
            GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(rotation, 0.0F, 0.0F, 1.0F);
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();
        }
    }
}
