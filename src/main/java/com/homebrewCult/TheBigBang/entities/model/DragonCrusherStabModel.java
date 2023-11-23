package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.DragonCrusherStabEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class DragonCrusherStabModel<T extends DragonCrusherStabEntity> extends EntityModel<T> {
    private final ModelRenderer head;
    private final ModelRenderer neck_r1;
    private final ModelRenderer upper_jaw;
    private final ModelRenderer lower_jaw_bone;
    private final ModelRenderer horn_left_bone;
    private final ModelRenderer horn_right_bone;
    private final ModelRenderer ear_left_bone;
    private final ModelRenderer ear_right_bone;

    public DragonCrusherStabModel() {
        super();
        textureWidth = 32;
        textureHeight = 32;

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 24.0F, -0.5F);
        head.setTextureOffset(2, 1).addBox(-2.5F, -4.0F, -2.0F, 5.0F, 4.0F, 5.0F, 0.0F, false);

        neck_r1 = new ModelRenderer(this);
        neck_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(neck_r1);
        setRotationAngle(neck_r1, -1.5708F, 0.0F, 0.0F);
        neck_r1.setTextureOffset(24, 0).addBox(-1.0F, -15.0F, -3.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

        upper_jaw = new ModelRenderer(this);
        upper_jaw.setRotationPoint(0.0F, -2.5F, -2.0F);
        head.addChild(upper_jaw);
        upper_jaw.setTextureOffset(0, 13).addBox(-1.5F, 0.0F, -4.1F, 3.0F, 2.0F, 4.0F, 0.0F, false);

        lower_jaw_bone = new ModelRenderer(this);
        lower_jaw_bone.setRotationPoint(0.0F, 0.0F, -2.0F);
        head.addChild(lower_jaw_bone);
        lower_jaw_bone.setTextureOffset(11, 11).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

        horn_left_bone = new ModelRenderer(this);
        horn_left_bone.setRotationPoint(2.5F, -4.0F, 0.0F);
        head.addChild(horn_left_bone);
        setRotationAngle(horn_left_bone, -1.2217F, 0.3491F, 0.0F);
        horn_left_bone.setTextureOffset(0, 23).addBox(-2.0F, -7.0F, 0.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        horn_right_bone = new ModelRenderer(this);
        horn_right_bone.setRotationPoint(-2.5F, -4.0F, 0.0F);
        head.addChild(horn_right_bone);
        setRotationAngle(horn_right_bone, -1.2217F, -0.3491F, 0.0F);
        horn_right_bone.setTextureOffset(0, 23).addBox(0.0F, -7.0F, 0.0F, 2.0F, 7.0F, 2.0F, 0.0F, true);

        ear_left_bone = new ModelRenderer(this);
        ear_left_bone.setRotationPoint(2.5F, -2.0F, 0.0F);
        head.addChild(ear_left_bone);
        setRotationAngle(ear_left_bone, 0.0F, -0.7854F, 0.0F);
        ear_left_bone.setTextureOffset(0, 0).addBox(0.0F, -2.5F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);

        ear_right_bone = new ModelRenderer(this);
        ear_right_bone.setRotationPoint(-2.5F, -2.0F, 0.0F);
        head.addChild(ear_right_bone);
        setRotationAngle(ear_right_bone, 0.0F, 0.6981F, 0.0F);
        ear_right_bone.setTextureOffset(0, 0).addBox(-3.0F, -2.5F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, true);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        float time = entityIn.ticksExisted + partialTick;
        lower_jaw_bone.rotateAngleX = MathHelper.sin(MathHelper.clamp(time * 0.1F, 0, (float)Math.PI)/2F) * 1.5F;
        ear_right_bone.rotateAngleY = MathHelper.sin(MathHelper.clamp(time * 0.1F, 0, (float)Math.PI/2F)) * ((float)Math.PI/2);
        ear_left_bone.rotateAngleY = -ear_right_bone.rotateAngleY;
        horn_right_bone.rotateAngleX = -0.2F - (MathHelper.sin(MathHelper.clamp(time * 0.1F, 0, (float)Math.PI)/2F) * 2F);
        horn_right_bone.rotateAngleY = -0.8F + (MathHelper.sin(MathHelper.clamp(time * 0.1F, 0, (float)Math.PI)/2F) * 1.2F);
        horn_left_bone.rotateAngleX = horn_right_bone.rotateAngleX;
        horn_left_bone.rotateAngleY = -horn_right_bone.rotateAngleY;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
