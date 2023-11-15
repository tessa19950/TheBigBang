package com.homebrewCult.TheBigBang.entities.model;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class AbstractBubblingModel<T extends Entity> extends EntityModel<T> {
	
	private float oscillationTimer = 0f;
	private float oscillationSpeed = .2f;
	
	private float limbBaseRot = .5f;
	private Float limbTopSegmentMaxRot = .2f;
	private Float limbBottomSegmentMaxRot = .6f;
	private Float limbBottomSegmentTimeOffset = -2f;

	private final ModelRenderer body;
	private final ModelRenderer tail1_pivot;
	private final ModelRenderer tail2_pivot;
	private final ModelRenderer eye0;
	private final ModelRenderer eye1;
	private final ModelRenderer mouth;

	public AbstractBubblingModel() {
		super();
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 20.0F, 0.0F);
		body.setTextureOffset(0, 16).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, true);
		body.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		tail1_pivot = new ModelRenderer(this);
		tail1_pivot.setRotationPoint(0.0F, -4.0F, 1.0F);
		body.addChild(tail1_pivot);
		tail1_pivot.setTextureOffset(52, 18).addBox(0.0F, -4.0F, -0.5F, 0.0F, 8.0F, 6.0F, 0.0F, false);

		tail2_pivot = new ModelRenderer(this);
		tail2_pivot.setRotationPoint(0.0F, 2.0F, 5.5F);
		tail1_pivot.addChild(tail2_pivot);
		tail2_pivot.setTextureOffset(44, 20).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 4.0F, 0.0F, false);
		tail2_pivot.setTextureOffset(56, 20).addBox(-1.0F, 2.5F, 4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		tail2_pivot.setTextureOffset(52, 14).addBox(-1.5F, 2.0F, 4.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		eye0 = new ModelRenderer(this);
		eye0.setRotationPoint(0.0F, 0.0F, 0.0F);
		eye0.setTextureOffset(32, 0).addBox(0.5F, 17.5F, -3.5F, 3.0F, 3.0F, 2.0F, 0.0F, true);

		eye1 = new ModelRenderer(this);
		eye1.setRotationPoint(0.0F, 0.0F, 0.0F);
		eye1.setTextureOffset(32, 0).addBox(-3.5F, 17.5F, -3.5F, 3.0F, 3.0F, 2.0F, 0.0F, true);

		mouth = new ModelRenderer(this);
		mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		mouth.setTextureOffset(32, 8).addBox(-1.0F, 20.5F, -3.5F, 2.0F, 2.0F, 0.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.tail1_pivot.rotateAngleX = limbBaseRot + ((float)Math.cos(oscillationTimer) * limbTopSegmentMaxRot);
		this.tail2_pivot.rotateAngleX = (float)Math.cos(oscillationTimer + limbBottomSegmentTimeOffset) * limbBottomSegmentMaxRot;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		GlStateManager.translatef(0.0F, 0.001F, 0.0F);
		eye0.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		eye1.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		mouth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);

		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.param);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		GlStateManager.disableBlend();
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);		
		oscillationTimer = (entityIn.ticksExisted + partialTick) * oscillationSpeed;
	}
}