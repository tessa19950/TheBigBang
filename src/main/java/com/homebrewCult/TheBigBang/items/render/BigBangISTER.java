package com.homebrewCult.TheBigBang.items.render;

import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.items.model.LamaStaffModel;
import com.homebrewCult.TheBigBang.items.model.OldWoodenStaffModel;
import com.homebrewCult.TheBigBang.items.model.OmegaSpearModel;
import com.homebrewCult.TheBigBang.items.model.ScorpioModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BigBangISTER extends ItemStackTileEntityRenderer {

	private final OmegaSpearModel OMEGA_SPEAR_MODEL = new OmegaSpearModel();
	private static ItemStack OMEGA_SPEAR_HEAD = null;
	private final ScorpioModel SCORPIO_MODEL = new ScorpioModel();
	private static ItemStack SCORPIO_HEAD = null;
	private final LamaStaffModel LAMA_STAFF_MODEL = new LamaStaffModel();
	private static ItemStack LAMA_STAFF_HEAD = null;
	private final OldWoodenStaffModel OLD_WOODEN_STAFF_MODEL = new OldWoodenStaffModel();

	@Override
	public void render(ItemStack itemStackIn, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, int packedOverlay) {
		super.render(itemStackIn, matrixStack, buffer, packedLight, packedOverlay);
		Item item = itemStackIn.getItem();
		if (item == ModItems.OMEGA_SPEAR) {	
			//Render Head Item
			renderOmegaSpearHeadItem(matrixStack, buffer, packedLight, packedOverlay);
			//Render the Model
			Minecraft.getInstance().getTextureManager().bindTexture(OmegaSpearModel.TEXTURE_LOCATION);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(1.0F, -1.0F, -1.0F);
			if (itemStackIn.hasEffect())
				this.renderEffect();
			this.OMEGA_SPEAR_MODEL.render(matrixStack, buffer.getBuffer(RenderType.getSolid()), packedLight, packedOverlay, 1, 1,1,1);
			GlStateManager.popMatrix();
		} else if (item == ModItems.SCORPIO) {
			//Render Head Item
			renderScorpioHeadItem(matrixStack, buffer, packedLight, packedOverlay);
			//Render the Model
			Minecraft.getInstance().getTextureManager().bindTexture(ScorpioModel.TEXTURE_LOCATION);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(1.0F, -1.0F, -1.0F);
			if (itemStackIn.hasEffect())
				this.renderEffect();
			this.SCORPIO_MODEL.render(matrixStack, buffer.getBuffer(RenderType.getSolid()), packedLight, packedOverlay, 1, 1,1,1);
			GlStateManager.popMatrix();
		} else if (item == ModItems.LAMA_STAFF) {
			//Render Head Item
			renderLamaStaffHeadItem(matrixStack, buffer, packedLight, packedOverlay);
			//Render the Model
			Minecraft.getInstance().getTextureManager().bindTexture(LamaStaffModel.TEXTURE_LOCATION);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(1.0F, -1.0F, -1.0F);
			if (itemStackIn.hasEffect())
				this.renderEffect();
			this.LAMA_STAFF_MODEL.render(matrixStack, buffer.getBuffer(RenderType.getSolid()), packedLight, packedOverlay, 1, 1, 1, 1);
			GlStateManager.popMatrix();
		} else if (item == ModItems.OLD_WOODEN_STAFF) {
			//Render the Model
			Minecraft.getInstance().getTextureManager().bindTexture(OldWoodenStaffModel.TEXTURE_LOCATION);
			GlStateManager.pushMatrix();
			GlStateManager.scalef(1.0F, -1.0F, -1.0F);
			if (itemStackIn.hasEffect())
				this.renderEffect();
			this.OLD_WOODEN_STAFF_MODEL.render(matrixStack, buffer.getBuffer(RenderType.getSolid()), packedLight, packedOverlay, 1, 1, 1, 1);
			GlStateManager.popMatrix();
		}
	}
	
	public void renderOmegaSpearHeadItem(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, int packedOverlay) {
		if(OMEGA_SPEAR_HEAD == null) {OMEGA_SPEAR_HEAD = new ItemStack(ModItems.OMEGA_SPEAR_HEAD);};
		GlStateManager.pushMatrix();
      	GlStateManager.rotatef(0.0F, 0.0F, 0.0F, 1.0F);
      	GlStateManager.scalef(0.5F, 0.5F, 1F);
      	GlStateManager.translatef(-0.04F, 0.12F, 0.0F);
      	Minecraft.getInstance().getItemRenderer().renderItem(OMEGA_SPEAR_HEAD, ItemCameraTransforms.TransformType.FIXED, packedLight, packedOverlay, matrixStack, buffer);
		GlStateManager.popMatrix();
	}
	
	public void renderScorpioHeadItem(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, int packedOverlay) {
		if(SCORPIO_HEAD == null) {SCORPIO_HEAD = new ItemStack(ModItems.SCORPIO_HEAD);};
		GlStateManager.pushMatrix();
      	GlStateManager.rotatef(0.0F, 0.0F, 0.0F, 1.0F);
      	GlStateManager.scalef(0.5F, 0.5F, 1F);
      	GlStateManager.translatef(-0.04F, 0.12F, 0.0F);
      	Minecraft.getInstance().getItemRenderer().renderItem(SCORPIO_HEAD, ItemCameraTransforms.TransformType.FIXED, packedLight, packedOverlay, matrixStack, buffer);
		GlStateManager.popMatrix();
	}
	
	public void renderLamaStaffHeadItem(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, int packedOverlay) {
		if(LAMA_STAFF_HEAD == null) {LAMA_STAFF_HEAD = new ItemStack(ModItems.LAMA_STAFF_HEAD);};
		GlStateManager.pushMatrix();
      	GlStateManager.rotatef(0.0F, 0.0F, 0.0F, 1.0F);
      	GlStateManager.scalef(0.5F, 0.5F, 1F);
      	GlStateManager.translatef(-0.04F, 0.12F, 0.0F);
      	Minecraft.getInstance().getItemRenderer().renderItem(LAMA_STAFF_HEAD, ItemCameraTransforms.TransformType.FIXED, packedLight, packedOverlay, matrixStack, buffer);
		GlStateManager.popMatrix();
	}
	
	private void renderEffect() {
		GlStateManager.color4f(0.5019608F, 0.2509804F, 0.8F, 1F);
		Minecraft.getInstance().getTextureManager().bindTexture(ItemRenderer.RES_ITEM_GLINT);
		//ItemRenderer.renderEffect(Minecraft.getInstance().getTextureManager(), renderModelFunction, 1);
	}
}
