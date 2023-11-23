package com.homebrewCult.TheBigBang.entities.model;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class AbstractStumpModel<T extends Entity> extends EntityModel<T> {

	private final ModelRenderer leg_back_right;
	private final ModelRenderer leg_back_left;
	private final ModelRenderer leg_front_right;
	private final ModelRenderer leg_front_left;
	private final ModelRenderer body;
	private final ModelRenderer axe_pivot;
	
	public AbstractStumpModel() {
		super();
		textureWidth = 64;
		textureHeight = 64;

		leg_back_right = new ModelRenderer(this);
		leg_back_right.setRotationPoint(-8.0F, 18.0F, 8.0F);
		leg_back_right.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		leg_back_left = new ModelRenderer(this);
		leg_back_left.setRotationPoint(8.0F, 18.0F, 8.0F);
		leg_back_left.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		leg_front_right = new ModelRenderer(this);
		leg_front_right.setRotationPoint(-8.0F, 18.0F, -8.0F);
		leg_front_right.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		leg_front_left = new ModelRenderer(this);
		leg_front_left.setRotationPoint(8.0F, 18.0F, -8.0F);
		leg_front_left.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-8.0F, -23.0F, -8.0F, 16.0F, 20.0F, 16.0F, 0.0F, false);

		axe_pivot = new ModelRenderer(this);
		axe_pivot.setRotationPoint(0.0F, -28.0F, 0.0F);
		body.addChild(axe_pivot);
		setRotationAngle(axe_pivot, 2.8798F, 0.0F, 0.0F);
		axe_pivot.setTextureOffset(0, 38).addBox(-0.5F, -7.0F, -6.0F, 1.0F, 14.0F, 12.0F, 0.0F, false);
		axe_pivot.setTextureOffset(58, 60).addBox(-0.5F, 5.0F, -6.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(58, 60).addBox(-0.5F, 4.0F, -5.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(58, 60).addBox(-0.5F, 3.0F, -4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(58, 60).addBox(-0.5F, 2.0F, -3.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(58, 60).addBox(-0.5F, 1.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(58, 60).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(58, 60).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(58, 60).addBox(-0.5F, -2.0F, 1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(58, 60).addBox(-0.5F, -4.0F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(52, 60).addBox(-0.5F, -4.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(52, 60).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(52, 60).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(52, 60).addBox(-0.5F, -7.0F, 1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(52, 60).addBox(-0.5F, -6.0F, 2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(46, 60).addBox(-0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(46, 60).addBox(-0.5F, -2.0F, 4.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		axe_pivot.setTextureOffset(52, 60).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		if(this.isChild) {
			GlStateManager.translatef(0.0F, 0.6F, 0.0F);
			GlStateManager.scalef(0.6F, 0.6F, 0.6F);
		}
		leg_back_right.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_back_left.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_front_right.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		leg_front_left.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.leg_front_left.rotateAngleX = MathHelper.cos(limbSwing * 3F) * 3F * limbSwingAmount;
		this.leg_front_right.rotateAngleX = MathHelper.cos(limbSwing * 3F + (float)Math.PI) * 3F * limbSwingAmount;
		this.leg_back_left.rotateAngleX = MathHelper.cos(limbSwing * 3F + (float)Math.PI) * 3F * limbSwingAmount;
		this.leg_back_right.rotateAngleX = MathHelper.cos(limbSwing * 3F) * 3F * limbSwingAmount;
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

