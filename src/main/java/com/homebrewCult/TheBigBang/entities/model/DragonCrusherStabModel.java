package com.homebrewCult.TheBigBang.entities.model;

import com.sun.jna.platform.win32.WinBase;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class DragonCrusherStabModel extends Model {
    private final RendererModel head;
    private final RendererModel neck_r1;
    private final RendererModel upper_jaw;
    private final RendererModel lower_jaw_bone;
    private final RendererModel horn_left_bone;
    private final RendererModel horn_right_bone;
    private final RendererModel ear_left_bone;
    private final RendererModel ear_right_bone;

    public DragonCrusherStabModel() {
        textureWidth = 32;
        textureHeight = 32;

        head = new RendererModel(this);
        head.setRotationPoint(0.0F, 24.0F, -0.5F);
        head.cubeList.add(new ModelBox(head, 2, 1, -2.5F, -4.0F, -2.0F, 5, 4, 5, 0.0F, false));

        neck_r1 = new RendererModel(this);
        neck_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(neck_r1);
        setRotationAngle(neck_r1, -1.5708F, 0.0F, 0.0F);
        neck_r1.cubeList.add(new ModelBox(neck_r1, 24, 0, -1.0F, -15.0F, -3.0F, 2, 12, 2, 0.0F, false));

        upper_jaw = new RendererModel(this);
        upper_jaw.setRotationPoint(0.0F, -2.5F, -2.0F);
        head.addChild(upper_jaw);
        upper_jaw.cubeList.add(new ModelBox(upper_jaw, 0, 13, -1.5F, 0.0F, -4.1F, 3, 2, 4, 0.0F, false));

        lower_jaw_bone = new RendererModel(this);
        lower_jaw_bone.setRotationPoint(0.0F, 0.0F, -2.0F);
        head.addChild(lower_jaw_bone);
        lower_jaw_bone.cubeList.add(new ModelBox(lower_jaw_bone, 11, 11, -1.0F, -2.0F, -3.0F, 2, 2, 3, 0.0F, false));

        horn_left_bone = new RendererModel(this);
        horn_left_bone.setRotationPoint(2.5F, -4.0F, 0.0F);
        head.addChild(horn_left_bone);
        setRotationAngle(horn_left_bone, -1.2217F, 0.3491F, 0.0F);
        horn_left_bone.cubeList.add(new ModelBox(horn_left_bone, 0, 23, -2.0F, -7.0F, 0.0F, 2, 7, 2, 0.0F, false));

        horn_right_bone = new RendererModel(this);
        horn_right_bone.setRotationPoint(-2.5F, -4.0F, 0.0F);
        head.addChild(horn_right_bone);
        setRotationAngle(horn_right_bone, -1.2217F, -0.3491F, 0.0F);
        horn_right_bone.cubeList.add(new ModelBox(horn_right_bone, 0, 23, 0.0F, -7.0F, 0.0F, 2, 7, 2, 0.0F, true));

        ear_left_bone = new RendererModel(this);
        ear_left_bone.setRotationPoint(2.5F, -2.0F, 0.0F);
        head.addChild(ear_left_bone);
        setRotationAngle(ear_left_bone, 0.0F, -0.7854F, 0.0F);
        ear_left_bone.cubeList.add(new ModelBox(ear_left_bone, 0, 0, 0.0F, -2.5F, 0.0F, 3, 5, 0, 0.0F, false));

        ear_right_bone = new RendererModel(this);
        ear_right_bone.setRotationPoint(-2.5F, -2.0F, 0.0F);
        head.addChild(ear_right_bone);
        setRotationAngle(ear_right_bone, 0.0F, 0.6981F, 0.0F);
        ear_right_bone.cubeList.add(new ModelBox(ear_right_bone, 0, 0, -3.0F, -2.5F, 0.0F, 3, 5, 0, 0.0F, true));
    }

    public void render(float time) {
        lower_jaw_bone.rotateAngleX = MathHelper.sin(MathHelper.clamp(time * 0.1F, 0, (float)Math.PI)/2F) * 1.5F;
        ear_right_bone.rotateAngleY = MathHelper.sin(MathHelper.clamp(time * 0.1F, 0, (float)Math.PI/2F)) * ((float)Math.PI/2);
        ear_left_bone.rotateAngleY = -ear_right_bone.rotateAngleY;
        horn_right_bone.rotateAngleX = -0.2F - (MathHelper.sin(MathHelper.clamp(time * 0.1F, 0, (float)Math.PI)/2F) * 2F);
        horn_right_bone.rotateAngleY = -0.8F + (MathHelper.sin(MathHelper.clamp(time * 0.1F, 0, (float)Math.PI)/2F) * 1.2F);
        horn_left_bone.rotateAngleX = horn_right_bone.rotateAngleX;
        horn_left_bone.rotateAngleY = -horn_right_bone.rotateAngleY;
        head.render(1);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
