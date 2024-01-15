package com.homebrewCult.TheBigBang.items.model;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class LamaStaffModel extends Model {
	
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/lama_staff.png");
	public final ModelRenderer pole;
	public final ModelRenderer orb_pivot;
	public final ModelRenderer wrap2_pivot;
	public final ModelRenderer wrap_pivot;

	public LamaStaffModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 32;
		textureHeight = 32;

		pole = new ModelRenderer(this);
		pole.setRotationPoint(0.0F, 0.0F, 0.0F);
		pole.setTextureOffset(4, 0).addBox(-0.5F, 15.0F, -0.5F, 1.0F, 12.0F, 1.0F, 0.01F, false);
		pole.setTextureOffset(0, 0).addBox(-0.5F, 3.0F, -0.5F, 1.0F, 12.0F, 1.0F, 0.01F, false);

		orb_pivot = new ModelRenderer(this);
		orb_pivot.setRotationPoint(0.0F, 0.0F, 0.0F);
		pole.addChild(orb_pivot);
		setRotationAngle(orb_pivot, -0.7854F, 0.0F, 0.6545F);
		orb_pivot.setTextureOffset(10, 4).addBox(0.05F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		wrap2_pivot = new ModelRenderer(this);
		wrap2_pivot.setRotationPoint(0.0F, 11.5F, 0.0F);
		pole.addChild(wrap2_pivot);
		setRotationAngle(wrap2_pivot, 0.0F, 0.0F, -0.5236F);
		wrap2_pivot.setTextureOffset(8, 0).addBox(-1.3F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		wrap_pivot = new ModelRenderer(this);
		wrap_pivot.setRotationPoint(0.0F, 7.5F, 0.0F);
		pole.addChild(wrap_pivot);
		setRotationAngle(wrap_pivot, 0.0F, 0.0F, -0.5236F);
		wrap_pivot.setTextureOffset(8, 0).addBox(-1.3F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
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
