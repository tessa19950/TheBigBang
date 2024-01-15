package com.homebrewCult.TheBigBang.items.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class BigBangArmorModel extends BipedModel<LivingEntity> {
	
	public final ModelRenderer armorHead;
	public final ModelRenderer armorTorso;
	public final ModelRenderer armorWaist;
	public final ModelRenderer armorLeftArm;
	public final ModelRenderer armorRightArm;
	public final ModelRenderer armorLeftLeg;
	public final ModelRenderer armorLeftBoot;
	public final ModelRenderer armorRightLeg;
	public final ModelRenderer armorRightBoot;
	
	public BigBangArmorModel() {
		super(1f, 0.0F, 128, 64);
		armorHead = new ModelRenderer(this);
		armorTorso = new ModelRenderer(this);
		armorWaist = new ModelRenderer(this);
		armorLeftArm = new ModelRenderer(this);
		armorRightArm = new ModelRenderer(this);
		armorLeftLeg = new ModelRenderer(this);
		armorRightLeg = new ModelRenderer(this);
		armorLeftBoot = new ModelRenderer(this);
		armorRightBoot = new ModelRenderer(this);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		super.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setRotationAngles(LivingEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		super.setRotationAngles(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
	}

	/*
	@Override
	public void setLivingAnimations(LivingEntity entity, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
		super.setLivingAnimations(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
		GlStateManager.pushMatrix();
		if (entityIn.isSneaking()) {
			GlStateManager.translatef(0.0F, 0.2F, 0.0F);
		}
		this.bipedHead.render(entity);
		this.bipedBody.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedRightLeg.render(scale + 0.001f);
		this.bipedLeftLeg.render(scale + 0.001f);
		this.bipedHeadwear.render(scale);
		GlStateManager.popMatrix();
	}
	 */

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public Vector3f getRotation(ModelRenderer model) {
		return new Vector3f(model.rotateAngleX, model.rotateAngleY, model.rotateAngleZ);
	}
	
	public void setRotation(ModelRenderer model, Vector3f rotation) {
		setRotationAngle(model, rotation.getX(), rotation.getY(), rotation.getZ());
	}
	
	public Vector3f getOffset(ModelRenderer model) {
		return new Vector3f(model.rotationPointX, model.rotationPointY, model.rotationPointZ);
	}
	
	public void setOffset(ModelRenderer model, Vector3f offset) {
		model.rotationPointX = offset.getX();
		model.rotationPointY = offset.getY();
		model.rotationPointZ = offset.getZ();
	}
	
}
