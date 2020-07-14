package com.homebrewCult.TheBigBang.entities.model;
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
	
	private final RendererModel bb_main;
	private final RendererModel body;
	private final RendererModel head;
	private final RendererModel leg0;
	private final RendererModel leg1;
	private final RendererModel leg2;
	private final RendererModel leg3;

	public RibbonPigModel() {
		super(6, 0f);
		
		textureWidth = 64;
		textureHeight = 32;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 36, 4, -5.0F, -15.0F, 0.0F, 10, 4, 0, 0.0F, false));

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 11.0F, 2.0F);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 28, 8, -5.0F, -9.0F, -10.0F, 10, 14, 8, 0.0F, true));

		head = new RendererModel(this);
		head.setRotationPoint(0.0F, 12.0F, -6.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, 0.0F, -8.0F, 8, 8, 8, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 16, 16, -2.0F, 3.0F, -9.0F, 4, 3, 1, 0.0F, true));

		leg0 = new RendererModel(this);
		leg0.setRotationPoint(3.0F, 18.0F, 7.0F);
		leg0.cubeList.add(new ModelBox(leg0, 0, 19, -2.0F, 3.0F, -4.0F, 4, 3, 4, 0.0F, true));

		leg1 = new RendererModel(this);
		leg1.setRotationPoint(-3.0F, 18.0F, 7.0F);
		leg1.cubeList.add(new ModelBox(leg1, 0, 19, -2.0F, 3.0F, -4.0F, 4, 3, 4, 0.0F, true));

		leg2 = new RendererModel(this);
		leg2.setRotationPoint(3.0F, 18.0F, -5.0F);
		leg2.cubeList.add(new ModelBox(leg2, 0, 19, -2.0F, 3.0F, -2.0F, 4, 3, 4, 0.0F, true));

		leg3 = new RendererModel(this);
		leg3.setRotationPoint(-3.0F, 18.0F, -5.0F);
		leg3.cubeList.add(new ModelBox(leg3, 0, 19, -2.0F, 3.0F, -2.0F, 4, 3, 4, 0.0F, true));
	}

	@Override
	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
		bb_main.render(scale);
		body.render(scale);
		head.render(scale);
		leg0.render(scale);
		leg1.render(scale);
		leg2.render(scale);
		leg3.render(scale);
	}
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
}