package com.homebrewCult.TheBigBang.items.model;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class ScorpioModel extends Model {
	
	public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/scorpio.png");
	public final ModelRenderer pole;
	public final ModelRenderer bone;

	public ScorpioModel() {
		super(RenderType::getEntitySolid);
		textureWidth = 32;
		textureHeight = 32;

		pole = new ModelRenderer(this);
		pole.setRotationPoint(0.0F, 0.0F, 0.0F);
		pole.setTextureOffset(4, 0).addBox(-0.5F, 15.0F, -0.5F, 1.0F, 12.0F, 1.0F, 0.01F, false);
		pole.setTextureOffset(0, 0).addBox(-0.5F, 3.0F, -0.5F, 1.0F, 12.0F, 1.0F, 0.01F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 3.0F, 0.0F);
		pole.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.7854F);
		bone.setTextureOffset(8, 0).addBox(-3.0F, -3.0F, 1.2F, 6.0F, 6.0F, 0.0F, 0.0F, false);
		bone.setTextureOffset(8, 0).addBox(-3.0F, -3.0F, -1.2F, 6.0F, 6.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder iVertexBuilder, int i, int i1, float v, float v1, float v2, float v3) {
		//this.pole.render(0.0625F);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}


}
