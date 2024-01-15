package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.StealEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class StealModel extends BipedModel<PlayerEntity> {

    public StealModel() {
        super(0.2F, 0.0F, 32, 16);
        this.isChild = false;
    }

    @Override
    public void setRotationAngles(PlayerEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        this.isChild = false;

        float s = 1.0F + MathHelper.cos((float)(ageInTicks/40F*Math.PI)) / 2.0F;
        this.bipedBody.rotateAngleX = 0;
        this.bipedRightArm.rotateAngleX = -1;
        this.bipedRightArm.rotateAngleY = (-1 + (ageInTicks/10F)) * s;
        this.bipedRightArm.rotateAngleZ = 0;

        this.bipedLeftLeg.rotateAngleX = (-1 + 2 * ageInTicks/30F) * s;
        this.bipedRightLeg.rotateAngleX = (1 - 2 * ageInTicks/30F) * s;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer,  int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        super.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void renderAttack(StealEntity entity, float animTime) {

    }
}
