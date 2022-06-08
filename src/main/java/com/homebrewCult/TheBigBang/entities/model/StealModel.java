package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.StealEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class StealModel extends BipedModel<PlayerEntity> {

    public StealModel() {
        super(0.2F, 0.0F, 32, 16);
        this.isChild = false;
    }

    public void renderAttack(StealEntity entity, float animTime) {
        this.isChild = false;


        float s = 1.0F + MathHelper.cos((float)(animTime/40F*Math.PI)) / 2.0F;
        this.bipedBody.rotateAngleX = 0;
        this.bipedRightArm.rotateAngleX = -1;
        this.bipedRightArm.rotateAngleY = (-1 + (animTime/10F)) * s;
        this.bipedRightArm.rotateAngleZ = 0;

        this.bipedLeftLeg.rotateAngleX = (-1 + 2 * animTime/30F) * s;
        this.bipedRightLeg.rotateAngleX = (1 - 2 * animTime/30F) * s;

        this.bipedHead.render(1);
        this.bipedBody.render(1);
        this.bipedLeftArm.render(1);
        this.bipedRightArm.render(1);
        this.bipedLeftLeg.render(1);
        this.bipedRightLeg.render(1);
    }
}
