package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.ManaRockEntity;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelBox;

public class ManaRockModel extends Model {
    private final RendererModel main_body;
    private final RendererModel rope;
    private final RendererModel tip_north;
    private final RendererModel tip_east;
    private final RendererModel tip_south;
    private final RendererModel tip_west;

    public ManaRockModel() {
        textureWidth = 64;
        textureHeight = 64;

        main_body = new RendererModel(this);
        main_body.setRotationPoint(0.0F, 8.0F, 5.0F);

        RendererModel body_r1 = new RendererModel(this);
        body_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        main_body.addChild(body_r1);
        setRotationAngle(body_r1, 0.0F, 0.0F, 0.0F);
        body_r1.cubeList.add(new ModelBox(body_r1, 46, 1, -3.5F, -4.0F, -2.0F, 7, 8, 2, 0.0F, false));
        body_r1.cubeList.add(new ModelBox(body_r1, 37, 12, -4.0F, -3.0F, -10.0F, 8, 8, 5, 0.0F, false));
        body_r1.cubeList.add(new ModelBox(body_r1, 5, 3, -5.0F, -4.5F, -9.0F, 5, 9, 8, 0.0F, false));
        body_r1.cubeList.add(new ModelBox(body_r1, 5, 21, 0.0F, -5.0F, -9.5F, 5, 8, 8, 0.0F, false));
        body_r1.cubeList.add(new ModelBox(body_r1, 0, 0, -1.5F, -10.0F, -6.5F, 3, 2, 3, 0.0F, false));
        body_r1.cubeList.add(new ModelBox(body_r1, 23, 0, -3.0F, -8.0F, -7.0F, 6, 5, 5, 0.0F, false));
        body_r1.cubeList.add(new ModelBox(body_r1, 37, 26, -2.5F, 3.0F, -7.75F, 7, 5, 6, 0.0F, false));
        body_r1.cubeList.add(new ModelBox(body_r1, 0, 5, -1.5F, 8.0F, -6.5F, 3, 2, 3, 0.0F, false));
        body_r1.cubeList.add(new ModelBox(body_r1, 37, 12, -4.0F, -3.0F, -10.0F, 8, 8, 5, 0.0F, false));

        rope = new RendererModel(this);
        rope.setRotationPoint(0.0F, -0.5F, 0.0F);
        main_body.addChild(rope);

        RendererModel rope_r1 = new RendererModel(this);
        rope_r1.setRotationPoint(5.0F, 0.0F, -5.0F);
        rope.addChild(rope_r1);
        setRotationAngle(rope_r1, 0.0F, -1.5708F, 0.0F);
        rope_r1.cubeList.add(new ModelBox(rope_r1, 36, 57, -5.5F, -1.5F, -0.5F, 9, 4, 2, 0.0F, false));

        RendererModel rope_r2 = new RendererModel(this);
        rope_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
        rope.addChild(rope_r2);
        setRotationAngle(rope_r2, 0.0F, 3.1416F, 0.0F);
        rope_r2.cubeList.add(new ModelBox(rope_r2, 36, 57, -5.5F, -1.5F, -0.5F, 9, 4, 2, 0.0F, false));

        RendererModel rope_r3 = new RendererModel(this);
        rope_r3.setRotationPoint(-5.0F, 0.0F, -5.0F);
        rope.addChild(rope_r3);
        setRotationAngle(rope_r3, 0.0F, 1.5708F, 0.0F);
        rope_r3.cubeList.add(new ModelBox(rope_r3, 36, 57, -5.5F, -1.5F, -0.5F, 10, 4, 2, 0.0F, false));

        RendererModel rope_r4 = new RendererModel(this);
        rope_r4.setRotationPoint(0.0F, 0.0F, -10.0F);
        rope.addChild(rope_r4);
        setRotationAngle(rope_r4, 0.0F, 0.0F, 0.0F);
        rope_r4.cubeList.add(new ModelBox(rope_r4, 36, 57, -5.5F, -1.5F, -0.5F, 9, 4, 2, 0.0F, false));

        RendererModel tip_up = new RendererModel(this);
        tip_up.setRotationPoint(0.0F, 0.0F, 0.0F);
        main_body.addChild(tip_up);

        RendererModel tip_up_a_r1 = new RendererModel(this);
        tip_up_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_up.addChild(tip_up_a_r1);
        setRotationAngle(tip_up_a_r1, 0.0F, 0.0F, 0.0F);
        tip_up_a_r1.cubeList.add(new ModelBox(tip_up_a_r1, 16, 38, -4.5F, -12.0F, -5.0F, 9, 8, 0, 0.0F, false));
        tip_up_a_r1.cubeList.add(new ModelBox(tip_up_a_r1, 0, 30, 0.01F, -12.0F, -8.5F, 0, 8, 8, 0.0F, false));

        RendererModel tip_down = new RendererModel(this);
        tip_down.setRotationPoint(0.0F, 9.0F, -5.0F);
        main_body.addChild(tip_down);

        RendererModel tip_down_b_r1 = new RendererModel(this);
        tip_down_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_down.addChild(tip_down_b_r1);
        setRotationAngle(tip_down_b_r1, 0.0F, 1.5708F, 0.0F);
        tip_down_b_r1.cubeList.add(new ModelBox(tip_down_b_r1, 0, 46, -4.5F, -5.0F, 0.01F, 9, 8, 0, 0.0F, false));

        RendererModel tip_down_a_r1 = new RendererModel(this);
        tip_down_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_down.addChild(tip_down_a_r1);
        setRotationAngle(tip_down_a_r1, 0.0F, 0.0F, 0.0F);
        tip_down_a_r1.cubeList.add(new ModelBox(tip_down_a_r1, 18, 46, -4.5F, -5.0F, 0.01F, 9, 8, 0, 0.0F, false));

        tip_north = new RendererModel(this);
        tip_north.setRotationPoint(0.0F, 1.0F, -11.0F);
        main_body.addChild(tip_north);

        RendererModel tip_north_a_r1 = new RendererModel(this);
        tip_north_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_north.addChild(tip_north_a_r1);
        setRotationAngle(tip_north_a_r1, 0.0F, 1.5708F, -0.7854F);
        tip_north_a_r1.cubeList.add(new ModelBox(tip_north_a_r1, 6, 56, -1.5F, -3.5F, 0.0F, 3, 7, 0, 0.0F, false));

        RendererModel tip_north_b_r1 = new RendererModel(this);
        tip_north_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_north.addChild(tip_north_b_r1);
        setRotationAngle(tip_north_b_r1, 0.0F, 1.5708F, 0.7854F);
        tip_north_b_r1.cubeList.add(new ModelBox(tip_north_b_r1, 0, 56, -1.5F, -3.5F, 0.0F, 3, 7, 0, 0.0F, false));

        tip_east = new RendererModel(this);
        tip_east.setRotationPoint(6.0F, -1.0F, -5.5F);
        main_body.addChild(tip_east);
        setRotationAngle(tip_east, 0.0F, -1.5708F, 0.0F);

        RendererModel tip_east_a_r1 = new RendererModel(this);
        tip_east_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_east.addChild(tip_east_a_r1);
        setRotationAngle(tip_east_a_r1, 0.0F, 1.5708F, -0.7854F);
        tip_east_a_r1.cubeList.add(new ModelBox(tip_east_a_r1, 6, 56, -1.5F, -3.5F, 0.0F, 3, 7, 0, 0.0F, false));

        RendererModel tip_east_b_r1 = new RendererModel(this);
        tip_east_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_east.addChild(tip_east_b_r1);
        setRotationAngle(tip_east_b_r1, 0.0F, 1.5708F, 0.7854F);
        tip_east_b_r1.cubeList.add(new ModelBox(tip_east_b_r1, 0, 56, -1.5F, -3.5F, 0.0F, 3, 7, 0, 0.0F, false));

        tip_south = new RendererModel(this);
        tip_south.setRotationPoint(0.0F, 0.5F, 1.0F);
        main_body.addChild(tip_south);
        setRotationAngle(tip_south, 0.0F, 3.1416F, 0.0F);

        RendererModel tip_south_a_r1 = new RendererModel(this);
        tip_south_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_south.addChild(tip_south_a_r1);
        setRotationAngle(tip_south_a_r1, 0.0F, 1.5708F, -0.7854F);
        tip_south_a_r1.cubeList.add(new ModelBox(tip_south_a_r1, 6, 56, -1.5F, -3.5F, 0.0F, 3, 7, 0, 0.0F, false));

        RendererModel tip_south_b_r1 = new RendererModel(this);
        tip_south_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_south.addChild(tip_south_b_r1);
        setRotationAngle(tip_south_b_r1, 0.0F, 1.5708F, 0.7854F);
        tip_south_b_r1.cubeList.add(new ModelBox(tip_south_b_r1, 0, 56, -1.5F, -3.5F, 0.0F, 3, 7, 0, 0.0F, false));

        tip_west = new RendererModel(this);
        tip_west.setRotationPoint(-6.0F, -0.5F, -5.0F);
        main_body.addChild(tip_west);
        setRotationAngle(tip_west, 0.0F, 1.5708F, 0.0F);

        RendererModel tip_west_a_r1 = new RendererModel(this);
        tip_west_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_west.addChild(tip_west_a_r1);
        setRotationAngle(tip_west_a_r1, 0.0F, 1.5708F, -0.7854F);
        tip_west_a_r1.cubeList.add(new ModelBox(tip_west_a_r1, 6, 56, -1.5F, -3.5F, 0.0F, 3, 7, 0, 0.0F, false));

        RendererModel tip_west_b_r1 = new RendererModel(this);
        tip_west_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_west.addChild(tip_west_b_r1);
        setRotationAngle(tip_west_b_r1, 0.0F, 1.5708F, 0.7854F);
        tip_west_b_r1.cubeList.add(new ModelBox(tip_west_b_r1, 0, 56, -1.5F, -3.5F, 0.0F, 3, 7, 0, 0.0F, false));
    }

    public void render(ManaRockEntity entity, float scale) {
        rope.isHidden = !entity.getLeashed();
        tip_north.isHidden = entity.getLeashed();
        tip_east.isHidden = entity.getLeashed();
        tip_south.isHidden = entity.getLeashed();
        tip_west.isHidden = entity.getLeashed();
        main_body.render(scale);
    }

    public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
