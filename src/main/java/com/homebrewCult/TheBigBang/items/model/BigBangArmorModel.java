package com.homebrewCult.TheBigBang.items.model;

import javax.vecmath.Vector3f;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;

public class BigBangArmorModel extends BipedModel<LivingEntity> {
	
	public final RendererModel armorHead;
	public final RendererModel armorTorso;
	public final RendererModel armorWaist;
	public final RendererModel armorLeftArm;
	public final RendererModel armorRightArm;
	public final RendererModel armorLeftLeg;
	public final RendererModel armorLeftBoot;
	public final RendererModel armorRightLeg;
	public final RendererModel armorRightBoot;
	
	public BigBangArmorModel() {
		super(1f, 0.0F, 128, 64);
		armorHead = new RendererModel(this);
		armorTorso = new RendererModel(this);
		armorWaist = new RendererModel(this);
		armorLeftArm = new RendererModel(this);
		armorRightArm = new RendererModel(this);
		armorLeftLeg = new RendererModel(this);
		armorRightLeg = new RendererModel(this);
		armorLeftBoot = new RendererModel(this);
		armorRightBoot = new RendererModel(this);
	}
	
	@Override
	public void render(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		GlStateManager.pushMatrix(); 
		if (entityIn.shouldRenderSneaking()) {
			GlStateManager.translatef(0.0F, 0.2F, 0.0F);
		}
		this.bipedHead.render(scale);
	    this.bipedBody.render(scale);
	    this.bipedRightArm.render(scale);
	    this.bipedLeftArm.render(scale);
	    this.bipedRightLeg.render(scale + 0.001f);
	    this.bipedLeftLeg.render(scale + 0.001f);
	    this.bipedHeadwear.render(scale);
	    GlStateManager.popMatrix();
	}

	public void setRotationAngle(RendererModel model, float x, float y, float z) {
		model.rotateAngleX = (x / 180) * (float)Math.PI;
		model.rotateAngleY = (y / 180) * (float)Math.PI;
		model.rotateAngleZ = (z / 180) * (float)Math.PI;
	}
	
	public Vector3f getRotation(RendererModel model) {
		return new Vector3f(model.rotateAngleX, model.rotateAngleY, model.rotateAngleZ);
	}
	
	public void setRotation(RendererModel model, Vector3f rotation) {
		model.rotateAngleX = rotation.x;
		model.rotateAngleY = rotation.y;
		model.rotateAngleZ = rotation.z;
	}
	
	public Vector3f getOffset(RendererModel model) {
		return new Vector3f(model.offsetX, model.offsetY, model.offsetZ);
	}
	
	public void setOffset(RendererModel model, Vector3f offset) {
		model.offsetX = offset.x;
		model.offsetY = offset.y;
		model.offsetZ = offset.z;
	}
	
}
