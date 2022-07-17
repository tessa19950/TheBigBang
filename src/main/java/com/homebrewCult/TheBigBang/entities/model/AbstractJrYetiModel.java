package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.mob.AbstractJrYetiEntity;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made with Blockbench
//Paste this code into your mod.

@OnlyIn(Dist.CLIENT)
public class AbstractJrYetiModel<T extends AbstractJrYetiEntity> extends QuadrupedModel<T> {
	
	private float oscillationTimer = 0f;
	
	private final RendererModel Body_Bone;
	private final RendererModel TopFur_Bone;
	private final RendererModel SideFur_Bone2;
	private final RendererModel SideFur_Bone1;
	private final RendererModel FeetFur_Bone4;
	private final RendererModel FeetFur_Bone3;
	private final RendererModel FeetFur_Bone2;
	private final RendererModel FeetFur_Bone1;

	public AbstractJrYetiModel() {
		super(6,0);
		
		textureWidth = 64;
		textureHeight = 64;

		Body_Bone = new RendererModel(this);
		Body_Bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body_Bone.cubeList.add(new ModelBox(Body_Bone, 0, 0, -7.0F, -12.0F, -7.0F, 14, 12, 14, 0.0F, false));
		Body_Bone.cubeList.add(new ModelBox(Body_Bone, 0, 29, -6.0F, -14.0F, -6.0F, 12, 2, 12, 0.0F, false));

		TopFur_Bone = new RendererModel(this);
		TopFur_Bone.setRotationPoint(0.0F, -14.0F, -1.0F);
		Body_Bone.addChild(TopFur_Bone);
		TopFur_Bone.cubeList.add(new ModelBox(TopFur_Bone, 42, 0, -3.0F, -3.0F, 1.0F, 6, 3, 0, 0.0F, false));

		SideFur_Bone2 = new RendererModel(this);
		SideFur_Bone2.setRotationPoint(-7.0F, -8.0F, 0.0F);
		setRotationAngle(SideFur_Bone2, -0.1745F, 1.5708F, 0.0F);
		Body_Bone.addChild(SideFur_Bone2);
		SideFur_Bone2.cubeList.add(new ModelBox(SideFur_Bone2, 42, 3, -3.0F, 0.0F, 0.0F, 6, 3, 0, 0.0F, false));

		SideFur_Bone1 = new RendererModel(this);
		SideFur_Bone1.setRotationPoint(7.0F, -8.0F, 0.0F);
		setRotationAngle(SideFur_Bone1, -0.1745F, -1.5708F, 0.0F);
		Body_Bone.addChild(SideFur_Bone1);
		SideFur_Bone1.cubeList.add(new ModelBox(SideFur_Bone1, 42, 3, -3.0F, 0.0F, 0.0F, 6, 3, 0, 0.0F, false));

		FeetFur_Bone4 = new RendererModel(this);
		FeetFur_Bone4.setRotationPoint(7.0F, -3.0F, 0.0F);
		setRotationAngle(FeetFur_Bone4, -0.3491F, -1.5708F, 0.0F);
		Body_Bone.addChild(FeetFur_Bone4);
		FeetFur_Bone4.cubeList.add(new ModelBox(FeetFur_Bone4, 0, 26, -7.0F, 0.0F, 0.0F, 14, 3, 0, 0.0F, false));

		FeetFur_Bone3 = new RendererModel(this);
		FeetFur_Bone3.setRotationPoint(0.0F, -3.0F, 7.0F);
		setRotationAngle(FeetFur_Bone3, -0.3491F, 3.1416F, 0.0F);
		Body_Bone.addChild(FeetFur_Bone3);
		FeetFur_Bone3.cubeList.add(new ModelBox(FeetFur_Bone3, 0, 26, -7.0F, 0.0F, 0.0F, 14, 3, 0, 0.0F, false));

		FeetFur_Bone2 = new RendererModel(this);
		FeetFur_Bone2.setRotationPoint(-7.0F, -3.0F, 0.0F);
		setRotationAngle(FeetFur_Bone2, -0.3491F, 1.5708F, 0.0F);
		Body_Bone.addChild(FeetFur_Bone2);
		FeetFur_Bone2.cubeList.add(new ModelBox(FeetFur_Bone2, 0, 26, -7.0F, 0.0F, 0.0F, 14, 3, 0, 0.0F, false));

		FeetFur_Bone1 = new RendererModel(this);
		FeetFur_Bone1.setRotationPoint(0.0F, -3.0F, -7.0F);
		setRotationAngle(FeetFur_Bone1, -0.3491F, 0.0F, 0.0F);
		Body_Bone.addChild(FeetFur_Bone1);
		FeetFur_Bone1.cubeList.add(new ModelBox(FeetFur_Bone1, 0, 26, -7.0F, 0.0F, 0.0F, 14, 3, 0, 0.0F, false));
	}
	
	@Override
	public void setLivingAnimations(T jrYeti, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(jrYeti, limbSwing, limbSwingAmount, partialTick);
		
		RendererModel[] furStrips = {this.FeetFur_Bone1, this.FeetFur_Bone2, this.FeetFur_Bone3, this.FeetFur_Bone4, this.SideFur_Bone1, this.SideFur_Bone2};
		if(jrYeti.isSitting()) {
			for (RendererModel fur : furStrips) {
				fur.rotateAngleX = (fur == this.SideFur_Bone1 || fur == this.SideFur_Bone2) ? -0.2f : -1.5F;
			}
			this.Body_Bone.offsetY = 0.15F;
		} else {
			if (valueInRange(jrYeti.getMotion().x, -0.0001D, 0.0001D) && valueInRange(jrYeti.getMotion().z, -0.0001D, 0.0001D)) {
				oscillationTimer = (jrYeti.ticksExisted + partialTick) * 0.05f;
				for (RendererModel fur : furStrips)
					fur.rotateAngleX = -0.2f - (float) Math.abs(0.3f * Math.cos(oscillationTimer));
				this.Body_Bone.offsetY = 0.05f - (float) Math.abs(0.05f * Math.cos(oscillationTimer));
			} else {
				oscillationTimer = (jrYeti.ticksExisted + partialTick) * 0.4f;
				for (RendererModel fur : furStrips)
					fur.rotateAngleX = -0.2f - (float) Math.abs(0.9f * Math.cos(oscillationTimer));
				this.Body_Bone.offsetY = 0.05f - (float) Math.abs(0.1f * Math.cos(oscillationTimer));
			}
		}
	}

	@Override
	public void render(AbstractJrYetiEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Body_Bone.render(f5);
	}
	
	public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
		RendererModel.rotateAngleX = x;
		RendererModel.rotateAngleY = y;
		RendererModel.rotateAngleZ = z;
	}
	
	private boolean valueInRange(double inValue, double min, double max) {
		return inValue > min && inValue < max;
	}
}