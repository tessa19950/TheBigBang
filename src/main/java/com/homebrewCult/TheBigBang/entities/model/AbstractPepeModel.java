package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.mob.AbstractPepeEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class AbstractPepeModel <T extends AbstractPepeEntity> extends EntityModel<T>  {
    private final RendererModel left_foot;
    private final RendererModel foot_r1;
    private final RendererModel right_foot;
    private final RendererModel foot_r2;
    private final RendererModel body;
    private final RendererModel left_arm;
    private final RendererModel right_arm;
    private final RendererModel hat;

    public AbstractPepeModel() {
        textureWidth = 64;
        textureHeight = 32;

        left_foot = new RendererModel(this);
        left_foot.setRotationPoint(3.5F, 24.0F, -6.0F);
        setRotationAngle(left_foot, 0.0F, -0.1745F, 0.0F);


        foot_r1 = new RendererModel(this);
        foot_r1.setRotationPoint(-3.5F, 0.0F, 6.0F);
        left_foot.addChild(foot_r1);
        setRotationAngle(foot_r1, -1.5708F, 0.0F, 0.0F);
        foot_r1.cubeList.add(new ModelBox(foot_r1, 0, 0, 1.0F, 4.9F, 0.0F, 5, 6, 0, 0.0F, false));

        right_foot = new RendererModel(this);
        right_foot.setRotationPoint(-3.5F, 24.0F, -6.0F);
        setRotationAngle(right_foot, 0.0F, 0.1745F, 0.0F);


        foot_r2 = new RendererModel(this);
        foot_r2.setRotationPoint(3.5F, 0.0F, 6.0F);
        right_foot.addChild(foot_r2);
        setRotationAngle(foot_r2, -1.5708F, 0.0F, 0.0F);
        foot_r2.cubeList.add(new ModelBox(foot_r2, 0, 0, -6.0F, 4.9F, 0.0F, 5, 6, 0, 0.0F, false));

        body = new RendererModel(this);
        body.setRotationPoint(0.0F, 24.0F, -6.0F);
        body.cubeList.add(new ModelBox(body, 47, 26, -2.0F, -13.0F, -4.0F, 4, 2, 4, 0.0F, false));
        body.cubeList.add(new ModelBox(body, 0, 0, -6.0F, -16.0F, 0.0F, 12, 16, 12, 0.0F, false));

        left_arm = new RendererModel(this);
        left_arm.setRotationPoint(6.0F, -11.0F, 6.0F);
        body.addChild(left_arm);
        setRotationAngle(left_arm, 0.0F, 0.0F, -0.1745F);
        left_arm.cubeList.add(new ModelBox(left_arm, 0, 2, 0.0F, 0.0F, -2.0F, 0, 5, 4, 0.0F, false));

        right_arm = new RendererModel(this);
        right_arm.setRotationPoint(-6.0F, -11.0F, 6.0F);
        body.addChild(right_arm);
        setRotationAngle(right_arm, 0.0F, 0.0F, 0.1745F);
        right_arm.cubeList.add(new ModelBox(right_arm, 0, 2, 0.0F, 0.0F, -2.0F, 0, 5, 4, 0.0F, false));

        hat = new RendererModel(this);
        hat.setRotationPoint(0.0F, -16.0F, 3.5F);
        body.addChild(hat);
        hat.cubeList.add(new ModelBox(hat, 36, 0, -2.5F, -4.0F, 0.0F, 5, 4, 5, 0.0F, false));
    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        hat.showModel = entityIn.getGrowingAge() >= 0 && entityIn.getHat() > 0;
    }

    @Override
    public void render(AbstractPepeEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        left_foot.render(f5);
        right_foot.render(f5);
        body.render(f5);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
