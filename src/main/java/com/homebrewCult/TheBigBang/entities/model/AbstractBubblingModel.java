package com.homebrewCult.TheBigBang.entities.model;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class AbstractBubblingModel<T extends Entity> extends QuadrupedModel<T> {
	
	private float oscillationTimer = 0f;
	private float oscillationSpeed = .2f;
	
	private float limbBaseRot = .5f;
	private Float limbTopSegmentMaxRot = .2f;
	private Float limbBottomSegmentMaxRot = .6f;
	private Float limbBottomSegmentTimeOffset = -2f;
	
	private final RendererModel body;
	private final RendererModel tail1_pivot;
	private final RendererModel tail2_pivot;
	private final RendererModel eye0;
	private final RendererModel eye1;
	private final RendererModel mouth;

	public AbstractBubblingModel() {
		super(6,0);	
		textureWidth = 64;
		textureHeight = 32;

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 20.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 16, -3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 0, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F, false));

		tail1_pivot = new RendererModel(this);
		tail1_pivot.setRotationPoint(0.0F, -4.0F, 1.0F);
		body.addChild(tail1_pivot);
		tail1_pivot.cubeList.add(new ModelBox(tail1_pivot, 52, 18, 0.0F, -4.0F, -0.5F, 0, 8, 6, 0.0F, false));

		tail2_pivot = new RendererModel(this);
		tail2_pivot.setRotationPoint(0.0F, 2.0F, 5.5F);
		tail1_pivot.addChild(tail2_pivot);
		tail2_pivot.cubeList.add(new ModelBox(tail2_pivot, 44, 20, 0.0F, -4.0F, 0.0F, 0, 8, 4, 0.0F, false));
		tail2_pivot.cubeList.add(new ModelBox(tail2_pivot, 56, 20, -1.0F, 2.5F, 4.0F, 2, 2, 2, 0.0F, false));
		tail2_pivot.cubeList.add(new ModelBox(tail2_pivot, 52, 14, -1.5F, 2.0F, 4.0F, 3, 3, 3, 0.0F, false));

		eye0 = new RendererModel(this);
		eye0.setRotationPoint(0.0F, 0.0F, 0.0F);
		eye0.cubeList.add(new ModelBox(eye0, 32, 0, 0.5F, 17.5F, -3.5F, 3, 3, 2, 0.0F, true));

		eye1 = new RendererModel(this);
		eye1.setRotationPoint(0.0F, 0.0F, 0.0F);
		eye1.cubeList.add(new ModelBox(eye1, 32, 0, -3.5F, 17.5F, -3.5F, 3, 3, 2, 0.0F, true));

		mouth = new RendererModel(this);
		mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		mouth.cubeList.add(new ModelBox(mouth, 32, 8, -1.0F, 20.5F, -3.5F, 2, 2, 0, 0.0F, true));
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
	    GlStateManager.translatef(0.0F, 0.001F, 0.0F);
		mouth.render(scale);
		eye0.render(scale);
		eye1.render(scale);
		
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		body.render(scale);
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
	}
	
	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);		
		oscillationTimer = (entityIn.ticksExisted + partialTick) * oscillationSpeed;
	}
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		
		this.tail1_pivot.rotateAngleX = limbBaseRot + ((float)Math.cos(oscillationTimer) * limbTopSegmentMaxRot);
		this.tail2_pivot.rotateAngleX = (float)Math.cos(oscillationTimer + limbBottomSegmentTimeOffset) * limbBottomSegmentMaxRot;
	}
	
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}