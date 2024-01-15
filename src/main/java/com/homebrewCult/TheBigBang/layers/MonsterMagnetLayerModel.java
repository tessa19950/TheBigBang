package com.homebrewCult.TheBigBang.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class MonsterMagnetLayerModel extends Model{

	private final ModelRenderer portal;
	private final ModelRenderer segment1;
	private final ModelRenderer segment2;
	private final ModelRenderer segment3;
	private final ModelRenderer segment4;
	private final ModelRenderer magnet;

	public MonsterMagnetLayerModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 64;
		textureHeight = 32;

		portal = new ModelRenderer(this);
		portal.setRotationPoint(0.0F, -20.0F, 0.0F);
		setRotationAngle(portal, 0.0F, 0.0F, 0.7854F);


		segment1 = new ModelRenderer(this);
		segment1.setRotationPoint(0.0F, 0.0F, 0.0F);
		portal.addChild(segment1);
		segment1.setTextureOffset(0, 0).addBox(-3.0F, -24.0F, 0.0F, 27.0F, 21.0F, 0.0F, 0.0F, false);

		segment2 = new ModelRenderer(this);
		segment2.setRotationPoint(0.0F, 0.0F, 0.0F);
		portal.addChild(segment2);
		setRotationAngle(segment2, 0.0F, 0.0F, 1.5708F);
		segment2.setTextureOffset(0, 0).addBox(-3.0F, -24.0F, 0.0F, 27.0F, 21.0F, 0.0F, 0.0F, false);

		segment3 = new ModelRenderer(this);
		segment3.setRotationPoint(0.0F, 0.0F, 0.0F);
		portal.addChild(segment3);
		setRotationAngle(segment3, 0.0F, 0.0F, -3.1416F);
		segment3.setTextureOffset(0, 0).addBox(-3.0F, -24.0F, 0.0F, 27.0F, 21.0F, 0.0F, 0.0F, false);

		segment4 = new ModelRenderer(this);
		segment4.setRotationPoint(0.0F, 0.0F, 0.0F);
		portal.addChild(segment4);
		setRotationAngle(segment4, 0.0F, 0.0F, -1.5708F);
		segment4.setTextureOffset(0, 0).addBox(-3.0F, -24.0F, 0.0F, 27.0F, 21.0F, 0.0F, 0.0F, false);

		magnet = new ModelRenderer(this);
		magnet.setRotationPoint(0.0F, -20.0F, 0.0F);
		magnet.setTextureOffset(0, 24).addBox(-1.5F, -4.0F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
		magnet.setTextureOffset(0, 24).addBox(-1.5F, 1.0F, -4.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);
		magnet.setTextureOffset(16, 21).addBox(-1.5F, -4.0F, 1.0F, 3.0F, 8.0F, 3.0F, 0.0F, false);
	}
	
	public void renderPortal(float time) {
		portal.rotateAngleZ = time/10F;
		//portal.render(1);
	}

	public void renderMagnet(float time) {
		//magnet.render(1);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		portal.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		magnet.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
