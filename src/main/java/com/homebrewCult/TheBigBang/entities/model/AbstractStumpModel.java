package com.homebrewCult.TheBigBang.entities.model;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class AbstractStumpModel<T extends Entity> extends QuadrupedModel<T> {
	
	private final RendererModel leg_back_right;
	private final RendererModel leg_back_left;
	private final RendererModel leg_front_right;
	private final RendererModel leg_front_left;
	private final RendererModel body;
	private final RendererModel axe_pivot;
	
	public AbstractStumpModel() 
	{
		super(6, 0.0F);
		textureWidth = 64;
		textureHeight = 64;
		
		leg_back_right = new RendererModel(this);
		leg_back_right.setRotationPoint(-8.0F, 18.0F, 8.0F);
		leg_back_right.cubeList.add(new ModelBox(leg_back_right, 0, 0, -2.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));

		leg_back_left = new RendererModel(this);
		leg_back_left.setRotationPoint(8.0F, 18.0F, 8.0F);
		leg_back_left.cubeList.add(new ModelBox(leg_back_left, 0, 0, -2.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));

		leg_front_right = new RendererModel(this);
		leg_front_right.setRotationPoint(-8.0F, 18.0F, -8.0F);
		leg_front_right.cubeList.add(new ModelBox(leg_front_right, 0, 0, -2.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));

		leg_front_left = new RendererModel(this);
		leg_front_left.setRotationPoint(8.0F, 18.0F, -8.0F);
		leg_front_left.cubeList.add(new ModelBox(leg_front_left, 0, 0, -2.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -8.0F, -23.0F, -8.0F, 16, 20, 16, 0.0F, false));

		axe_pivot = new RendererModel(this);
		axe_pivot.setRotationPoint(0.0F, -28.0F, 0.0F);
		body.addChild(axe_pivot);
		setRotationAngle(axe_pivot, 165.0F, 0.0F, 0.0F);
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 0, 38, -0.5F, -7.0F, -6.0F, 1, 14, 12, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 58, 60, -0.5F, 5.0F, -6.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 58, 60, -0.5F, 4.0F, -5.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 58, 60, -0.5F, 3.0F, -4.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 58, 60, -0.5F, 2.0F, -3.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 58, 60, -0.5F, 1.0F, -2.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 58, 60, -0.5F, 0.0F, -1.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 58, 60, -0.5F, -1.0F, 0.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 58, 60, -0.5F, -2.0F, 1.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 58, 60, -0.5F, -4.0F, 3.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 52, 60, -0.5F, -4.0F, -2.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 52, 60, -0.5F, -5.0F, -1.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 52, 60, -0.5F, -6.0F, 0.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 52, 60, -0.5F, -7.0F, 1.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 52, 60, -0.5F, -6.0F, 2.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 46, 60, -0.5F, -1.0F, 3.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 46, 60, -0.5F, -2.0F, 4.0F, 1, 2, 2, 0.0F, false));
		axe_pivot.cubeList.add(new ModelBox(axe_pivot, 52, 60, -0.5F, -3.0F, -1.0F, 1, 2, 2, 0.0F, false));
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
	{
		if(this.isChild)
		{
	        GlStateManager.translatef(0.0F, 0.6F, 0.0F);
			GlStateManager.scalef(0.6F, 0.6F, 0.6F);
		}	
		leg_back_right.render(f5);
		leg_back_left.render(f5);
		leg_front_right.render(f5);
		leg_front_left.render(f5);
		body.render(f5);
	}
	
	
	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) 
	{
		this.leg_front_left.rotateAngleX = MathHelper.cos(limbSwing * 3F) * 3F * limbSwingAmount;
		this.leg_front_right.rotateAngleX = MathHelper.cos(limbSwing * 3F + (float)Math.PI) * 3F * limbSwingAmount;
		this.leg_back_left.rotateAngleX = MathHelper.cos(limbSwing * 3F + (float)Math.PI) * 3F * limbSwingAmount;
		this.leg_back_right.rotateAngleX = MathHelper.cos(limbSwing * 3F) * 3F * limbSwingAmount;
	}
	

	public void setRotationAngle(RendererModel renderer, float x, float y, float z) {
		renderer.rotateAngleX = (x / 180) * (float)Math.PI;
		renderer.rotateAngleY = (y / 180) * (float)Math.PI;
		renderer.rotateAngleZ = (z / 180) * (float)Math.PI;
	}	

}

