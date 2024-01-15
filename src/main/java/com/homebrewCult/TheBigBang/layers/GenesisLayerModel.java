package com.homebrewCult.TheBigBang.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class GenesisLayerModel extends Model {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer skirt_front;
	private final ModelRenderer skirt_back;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftUpperWing;
	private final ModelRenderer leftLowerWing;
	private final ModelRenderer rightUpperWing;
	private final ModelRenderer rightLowerWing;

	public GenesisLayerModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 1.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureOffset(0, 16).addBox(-4.0F, 1.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.1F, false);

		skirt_front = new ModelRenderer(this);
		skirt_front.setRotationPoint(0.0F, 11.0F, 0.0F);
		body.addChild(skirt_front);
		setRotationAngle(skirt_front, -0.1745F, 0.0F, 0.0F);
		skirt_front.setTextureOffset(38, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 3.0F, 0.3F, false);

		skirt_back = new ModelRenderer(this);
		skirt_back.setRotationPoint(0.0F, 11.0F, 0.0F);
		body.addChild(skirt_back);
		setRotationAngle(skirt_back, 0.2618F, 0.0F, 0.0F);
		skirt_back.setTextureOffset(38, 32).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 12.0F, 3.0F, 0.2F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		body.addChild(leftArm);
		setRotationAngle(leftArm, 0.0F, 0.0F, 0.1745F);
		leftArm.setTextureOffset(24, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		body.addChild(rightArm);
		setRotationAngle(rightArm, 0.0F, 0.0F, -1.5708F);
		rightArm.setTextureOffset(24, 16).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, true);

		leftUpperWing = new ModelRenderer(this);
		leftUpperWing.setRotationPoint(2.0F, 2.0F, 3.0F);
		setRotationAngle(leftUpperWing, 0.0F, 0.0F, -0.7854F);
		leftUpperWing.setTextureOffset(0, 32).addBox(-1.0F, -3.0F, 0.0F, 19.0F, 16.0F, 0.0F, 0.0F, true);

		leftLowerWing = new ModelRenderer(this);
		leftLowerWing.setRotationPoint(18.0F, -3.0F, 0.0F);
		leftUpperWing.addChild(leftLowerWing);
		setRotationAngle(leftLowerWing, 0.0F, 0.0436F, 1.0472F);
		leftLowerWing.setTextureOffset(0, 48).addBox(0.0F, 0.0F, 0.0F, 32.0F, 16.0F, 0.0F, 0.0F, true);

		rightUpperWing = new ModelRenderer(this);
		rightUpperWing.setRotationPoint(-2.0F, 2.0F, 3.0F);
		setRotationAngle(rightUpperWing, 0.0F, 0.0F, 0.1745F);
		rightUpperWing.setTextureOffset(0, 32).addBox(-18.0F, -3.0F, 0.0F, 19.0F, 16.0F, 0.0F, 0.0F, false);

		rightLowerWing = new ModelRenderer(this);
		rightLowerWing.setRotationPoint(-18.0F, -3.0F, 0.0F);
		rightUpperWing.addChild(rightLowerWing);
		rightLowerWing.setTextureOffset(0, 48).addBox(-32.0F, 0.0F, 0.0F, 32.0F, 16.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {

	}

	public void render(float genesisTime) {
		float pct = genesisTime / 45F;
		float sinPct = MathHelper.sin(pct * ((float)Math.PI / 2));
		
		float upWingZ = MathHelper.lerp(sinPct, -0.2F, 0.8F);
		float lowWingY = MathHelper.lerp(sinPct, -2.6F, 0.0F);
		float lowWingZ = MathHelper.lerp(sinPct, 0F, 1.0F);
		float armZ = MathHelper.lerp(sinPct, 0.2F, 1.6F);		
		
		this.setRotationAngle(this.leftUpperWing, 0, 0, -upWingZ);
		this.setRotationAngle(this.rightUpperWing, 0, 0, upWingZ);
		this.setRotationAngle(this.leftLowerWing, 0, -lowWingY, lowWingZ);
		this.setRotationAngle(this.rightLowerWing, 0, lowWingY, -lowWingZ);
		this.setRotationAngle(this.leftArm, 0, 0, armZ);
		this.setRotationAngle(this.rightArm, 0, 0, -armZ);
		this.setRotationAngle(this.skirt_front, -0.2F - MathHelper.sin(pct * 30F) * 0.2F, 0, MathHelper.sin(pct * 20F) * 0.1F);
		this.setRotationAngle(this.skirt_back, 0.2F + MathHelper.sin(pct * 30F) * 0.2F, 0, -MathHelper.sin(pct * 20F) * 0.1F);
		
		//head.render(1);
		//body.render(1);
		//leftUpperWing.render(1);
		//rightUpperWing.render(1);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
