package com.homebrewCult.TheBigBang.entities.model;
import com.homebrewCult.TheBigBang.entities.mob.RibbonPigEntity;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class RibbonPigModel<T extends Entity> extends QuadrupedModel<T> {

	private final RendererModel ribbon;

	private float oscillationTimer = 0f;
	private float oscillationSpeed = .2f;
	private float ribbonMaxRot = .3f;
	
	public RibbonPigModel() {
		super(6, 0f);
		
		textureWidth = 64;
		textureHeight = 32;

		//Nose
		this.headModel.cubeList.add(new ModelBox(headModel, 16, 16, -2.0F, 0.0F, -9.0F, 4, 3, 1, 0.0F, true));
		
		ribbon = new RendererModel(this);
		ribbon.setRotationPoint(0.0F, -2.5F, 1.0F);
		ribbon.cubeList.add(new ModelBox(ribbon, 32, 4, -5.0F, 0.0F, 0.0F, 10, 0, 4, 0.0F, false));
		this.field_78148_b.addChild(ribbon);
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
		oscillationTimer = (entityIn.ticksExisted + partialTick) * oscillationSpeed;
		RibbonPigEntity e = (RibbonPigEntity) entityIn;
		if (e.getSaddled()) {
			this.ribbon.rotateAngleX = (float)Math.PI;
		} else {
			this.ribbon.rotateAngleX = -0.5f + ((float)Math.cos(oscillationTimer) * ribbonMaxRot);
		}
		
	}
	
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}