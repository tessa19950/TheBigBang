package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.mob.AbstractPepeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class AbstractPepeModel <T extends AbstractPepeEntity> extends EntityModel<T>  {
    private final ModelRenderer left_foot;
    private final ModelRenderer foot_r1;
    private final ModelRenderer right_foot;
    private final ModelRenderer foot_r2;
    private final ModelRenderer body;
    private final ModelRenderer left_arm;
    private final ModelRenderer right_arm;
    private final ModelRenderer hat;

    public AbstractPepeModel() {
        textureWidth = 64;
        textureHeight = 32;

        left_foot = new ModelRenderer(this);
        left_foot.setRotationPoint(3.5F, 24.0F, -6.0F);
        setRotationAngle(left_foot, 0.0F, -0.1745F, 0.0F);


        foot_r1 = new ModelRenderer(this);
        foot_r1.setRotationPoint(-3.5F, 0.0F, 6.0F);
        left_foot.addChild(foot_r1);
        setRotationAngle(foot_r1, -1.5708F, 0.0F, 0.0F);
        foot_r1.setTextureOffset(0, 0).addBox(1.0F, 4.9F, 0.0F, 5.0F, 6.0F, 0.0F, 0.0F, false);

        right_foot = new ModelRenderer(this);
        right_foot.setRotationPoint(-3.5F, 24.0F, -6.0F);
        setRotationAngle(right_foot, 0.0F, 0.1745F, 0.0F);


        foot_r2 = new ModelRenderer(this);
        foot_r2.setRotationPoint(3.5F, 0.0F, 6.0F);
        right_foot.addChild(foot_r2);
        setRotationAngle(foot_r2, -1.5708F, 0.0F, 0.0F);
        foot_r2.setTextureOffset(0, 0).addBox(-6.0F, 4.9F, 0.0F, 5.0F, 6.0F, 0.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 24.0F, -6.0F);
        body.setTextureOffset(47, 26).addBox(-2.0F, -13.0F, -4.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
        body.setTextureOffset(0, 0).addBox(-6.0F, -16.0F, 0.0F, 12.0F, 16.0F, 12.0F, 0.0F, false);

        left_arm = new ModelRenderer(this);
        left_arm.setRotationPoint(6.0F, -11.0F, 6.0F);
        body.addChild(left_arm);
        setRotationAngle(left_arm, 0.0F, 0.0F, -0.1745F);
        left_arm.setTextureOffset(0, 2).addBox(0.0F, 0.0F, -2.0F, 0.0F, 5.0F, 4.0F, 0.0F, false);

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(-6.0F, -11.0F, 6.0F);
        body.addChild(right_arm);
        setRotationAngle(right_arm, 0.0F, 0.0F, 0.1745F);
        right_arm.setTextureOffset(0, 2).addBox(0.0F, 0.0F, -2.0F, 0.0F, 5.0F, 4.0F, 0.0F, false);

        hat = new ModelRenderer(this);
        hat.setRotationPoint(0.0F, -16.0F, 3.5F);
        body.addChild(hat);
        hat.setTextureOffset(36, 0).addBox(-2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        hat.showModel = entityIn.getGrowingAge() >= 0 && entityIn.getHat() > 0;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        left_foot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        right_foot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
