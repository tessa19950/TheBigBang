package com.homebrewCult.TheBigBang.items.render;

import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.items.model.OmegaSpearModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OmegaSpearRenderer extends ItemStackTileEntityRenderer {

	private final OmegaSpearModel OMEGA_SPEAR_MODEL = new OmegaSpearModel();
	private ItemStack itemstack = new ItemStack(ModItems.OMEGA_SPEAR_HEAD);
	
	@Override
	public void renderByItem(ItemStack itemStackIn) {
		Item item = itemStackIn.getItem();
		if (item == ModItems.OMEGA_SPEAR) {	
			//Render the Model
			Minecraft.getInstance().getTextureManager().bindTexture(OmegaSpearModel.TEXTURE_LOCATION);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(1.0F, -1.0F, -1.0F);
			this.OMEGA_SPEAR_MODEL.renderer();
			if (itemStackIn.hasEffect()) {
				this.renderEffect(this.OMEGA_SPEAR_MODEL::renderer);
			}
			GlStateManager.popMatrix();
		}
	}
	
	private void renderEffect(Runnable renderModelFunction) {
		GlStateManager.color3f(0.5019608F, 0.2509804F, 0.8F);
		Minecraft.getInstance().getTextureManager().bindTexture(ItemRenderer.RES_ITEM_GLINT);
		ItemRenderer.renderEffect(Minecraft.getInstance().getTextureManager(), renderModelFunction, 1);
	}
}
