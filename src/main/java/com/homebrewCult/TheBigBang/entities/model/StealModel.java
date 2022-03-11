package com.homebrewCult.TheBigBang.entities.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.player.PlayerEntity;

public class StealModel extends BipedModel<PlayerEntity> {

    public StealModel() {
        super(0.2F, 0.0F, 32, 16);
        this.isChild = false;
    }

    public void renderAttack() {
        this.isChild = false;
        this.bipedBody.rotateAngleX = 0;
        this.bipedHead.render(1);
        this.bipedBody.render(1);
        this.bipedLeftArm.render(1);
        this.bipedRightArm.render(1);
        this.bipedLeftLeg.render(1);
        this.bipedRightLeg.render(1);
    }
}
