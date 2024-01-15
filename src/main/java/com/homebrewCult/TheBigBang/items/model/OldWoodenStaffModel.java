package com.homebrewCult.TheBigBang.items.model;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class OldWoodenStaffModel extends Model {
	
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/old_wooden_staff.png");
	public final ModelRenderer pole;
	public final ModelRenderer top_pivot;
	public final ModelRenderer top_corner_pivot;
	public final ModelRenderer start_corner_pivot;
	public final ModelRenderer start_pivot;

	public OldWoodenStaffModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 32;
		textureHeight = 32;

		pole = new ModelRenderer(this);
		pole.setRotationPoint(0.0F, 0.0F, 0.0F);
		pole.setTextureOffset(0, 0).addBox(-0.5F, 9.0F, -0.5F, 1.0F, 18.0F, 1.0F, 0.01F, false);
		pole.setTextureOffset(20, 8).addBox(-1.5F, 7.5F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);

		top_pivot = new ModelRenderer(this);
		top_pivot.setRotationPoint(0.0F, -3.0F, 0.0F);
		pole.addChild(top_pivot);
		setRotationAngle(top_pivot, 0.0F, 0.0F, 0.7854F);
		top_pivot.setTextureOffset(4, 0).addBox(-1.5F, -1.5F, -2.0F, 6.0F, 4.0F, 4.0F, 0.01F, false);

		top_corner_pivot = new ModelRenderer(this);
		top_corner_pivot.setRotationPoint(0.0F, -3.0F, 0.0F);
		pole.addChild(top_corner_pivot);
		setRotationAngle(top_corner_pivot, 0.0F, 0.0F, 0.7854F);
		top_corner_pivot.setTextureOffset(4, 8).addBox(-1.0F, 2.5F, -1.5F, 3.0F, 4.0F, 3.0F, 0.01F, false);

		start_corner_pivot = new ModelRenderer(this);
		start_corner_pivot.setRotationPoint(0.0F, 3.5F, 0.0F);
		pole.addChild(start_corner_pivot);
		setRotationAngle(start_corner_pivot, 0.0F, 0.0F, -0.7854F);
		start_corner_pivot.setTextureOffset(4, 15).addBox(-1.5F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, 0.01F, false);
		start_corner_pivot.setTextureOffset(24, 4).addBox(-3.5F, 1.5F, -1.0F, 2.0F, 2.0F, 2.0F, 0.01F, false);

		start_pivot = new ModelRenderer(this);
		start_pivot.setRotationPoint(0.0F, 7.75F, 0.0F);
		pole.addChild(start_pivot);
		setRotationAngle(start_pivot, 0.0F, 0.0F, -0.7854F);
		
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		pole.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
