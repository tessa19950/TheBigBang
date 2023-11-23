package com.homebrewCult.TheBigBang.entities.model;

import com.homebrewCult.TheBigBang.entities.ManaRockEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ManaRockModel<T extends ManaRockEntity> extends EntityModel<T> {
    private final ModelRenderer main_body;
    private final ModelRenderer body_r1;
    private final ModelRenderer rope;
    private final ModelRenderer rope_r1;
    private final ModelRenderer rope_r2;
    private final ModelRenderer rope_r3;
    private final ModelRenderer rope_r4;
    private final ModelRenderer tip_up;
    private final ModelRenderer tip_up_a_r1;
    private final ModelRenderer tip_down;
    private final ModelRenderer tip_down_b_r1;
    private final ModelRenderer tip_down_a_r1;
    private final ModelRenderer tip_north;
    private final ModelRenderer tip_north_a_r1;
    private final ModelRenderer tip_north_b_r1;
    private final ModelRenderer tip_east;
    private final ModelRenderer tip_east_a_r1;
    private final ModelRenderer tip_east_b_r1;
    private final ModelRenderer tip_south;
    private final ModelRenderer tip_south_a_r1;
    private final ModelRenderer tip_south_b_r1;
    private final ModelRenderer tip_west;
    private final ModelRenderer tip_west_a_r1;
    private final ModelRenderer tip_west_b_r1;

    public ManaRockModel() {
        textureWidth = 64;
        textureHeight = 64;

        main_body = new ModelRenderer(this);
        main_body.setRotationPoint(0.0F, 8.0F, 5.0F);


        body_r1 = new ModelRenderer(this);
        body_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        main_body.addChild(body_r1);
        setRotationAngle(body_r1, 0.0F, 0.0F, 0.0F);
        body_r1.setTextureOffset(46, 1).addBox(-3.5F, -4.0F, -2.0F, 7.0F, 8.0F, 2.0F, 0.0F, false);
        body_r1.setTextureOffset(37, 12).addBox(-4.0F, -3.0F, -10.0F, 8.0F, 8.0F, 5.0F, 0.0F, false);
        body_r1.setTextureOffset(5, 3).addBox(-5.0F, -4.5F, -9.0F, 5.0F, 9.0F, 8.0F, 0.0F, false);
        body_r1.setTextureOffset(5, 21).addBox(0.0F, -5.0F, -9.5F, 5.0F, 8.0F, 8.0F, 0.0F, false);
        body_r1.setTextureOffset(0, 0).addBox(-1.5F, -10.0F, -6.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
        body_r1.setTextureOffset(23, 0).addBox(-3.0F, -8.0F, -7.0F, 6.0F, 5.0F, 5.0F, 0.0F, false);
        body_r1.setTextureOffset(37, 26).addBox(-2.5F, 3.0F, -7.75F, 7.0F, 5.0F, 6.0F, 0.0F, false);
        body_r1.setTextureOffset(0, 5).addBox(-1.5F, 8.0F, -6.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
        body_r1.setTextureOffset(37, 12).addBox(-4.0F, -3.0F, -10.0F, 8.0F, 8.0F, 5.0F, 0.0F, false);

        rope = new ModelRenderer(this);
        rope.setRotationPoint(0.0F, -0.5F, 0.0F);
        main_body.addChild(rope);


        rope_r1 = new ModelRenderer(this);
        rope_r1.setRotationPoint(5.0F, 0.0F, -5.0F);
        rope.addChild(rope_r1);
        setRotationAngle(rope_r1, 0.0F, -1.5708F, 0.0F);
        rope_r1.setTextureOffset(36, 57).addBox(-5.5F, -1.5F, -0.5F, 9.0F, 4.0F, 2.0F, 0.0F, false);

        rope_r2 = new ModelRenderer(this);
        rope_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
        rope.addChild(rope_r2);
        setRotationAngle(rope_r2, 0.0F, 3.1416F, 0.0F);
        rope_r2.setTextureOffset(36, 57).addBox(-5.5F, -1.5F, -0.5F, 9.0F, 4.0F, 2.0F, 0.0F, false);

        rope_r3 = new ModelRenderer(this);
        rope_r3.setRotationPoint(-5.0F, 0.0F, -5.0F);
        rope.addChild(rope_r3);
        setRotationAngle(rope_r3, 0.0F, 1.5708F, 0.0F);
        rope_r3.setTextureOffset(36, 57).addBox(-5.5F, -1.5F, -0.5F, 10.0F, 4.0F, 2.0F, 0.0F, false);

        rope_r4 = new ModelRenderer(this);
        rope_r4.setRotationPoint(0.0F, 0.0F, -10.0F);
        rope.addChild(rope_r4);
        setRotationAngle(rope_r4, 0.0F, 0.0F, 0.0F);
        rope_r4.setTextureOffset(36, 57).addBox(-5.5F, -1.5F, -0.5F, 9.0F, 4.0F, 2.0F, 0.0F, false);

        tip_up = new ModelRenderer(this);
        tip_up.setRotationPoint(0.0F, 0.0F, 0.0F);
        main_body.addChild(tip_up);


        tip_up_a_r1 = new ModelRenderer(this);
        tip_up_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_up.addChild(tip_up_a_r1);
        setRotationAngle(tip_up_a_r1, 0.0F, 0.0F, 0.0F);
        tip_up_a_r1.setTextureOffset(16, 38).addBox(-4.5F, -12.0F, -5.0F, 9.0F, 8.0F, 0.0F, 0.0F, false);
        tip_up_a_r1.setTextureOffset(0, 30).addBox(0.01F, -12.0F, -8.5F, 0.0F, 8.0F, 8.0F, 0.0F, false);

        tip_down = new ModelRenderer(this);
        tip_down.setRotationPoint(0.0F, 9.0F, -5.0F);
        main_body.addChild(tip_down);


        tip_down_b_r1 = new ModelRenderer(this);
        tip_down_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_down.addChild(tip_down_b_r1);
        setRotationAngle(tip_down_b_r1, 0.0F, 1.5708F, 0.0F);
        tip_down_b_r1.setTextureOffset(0, 46).addBox(-4.5F, -5.0F, 0.01F, 9.0F, 8.0F, 0.0F, 0.0F, false);

        tip_down_a_r1 = new ModelRenderer(this);
        tip_down_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_down.addChild(tip_down_a_r1);
        setRotationAngle(tip_down_a_r1, 0.0F, 0.0F, 0.0F);
        tip_down_a_r1.setTextureOffset(18, 46).addBox(-4.5F, -5.0F, 0.01F, 9.0F, 8.0F, 0.0F, 0.0F, false);

        tip_north = new ModelRenderer(this);
        tip_north.setRotationPoint(0.0F, 1.0F, -11.0F);
        main_body.addChild(tip_north);


        tip_north_a_r1 = new ModelRenderer(this);
        tip_north_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_north.addChild(tip_north_a_r1);
        setRotationAngle(tip_north_a_r1, 0.0F, 1.5708F, -0.7854F);
        tip_north_a_r1.setTextureOffset(6, 56).addBox(-1.5F, -3.5F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);

        tip_north_b_r1 = new ModelRenderer(this);
        tip_north_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_north.addChild(tip_north_b_r1);
        setRotationAngle(tip_north_b_r1, 0.0F, 1.5708F, 0.7854F);
        tip_north_b_r1.setTextureOffset(0, 56).addBox(-1.5F, -3.5F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);

        tip_east = new ModelRenderer(this);
        tip_east.setRotationPoint(6.0F, -1.0F, -5.5F);
        main_body.addChild(tip_east);
        setRotationAngle(tip_east, 0.0F, -1.5708F, 0.0F);


        tip_east_a_r1 = new ModelRenderer(this);
        tip_east_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_east.addChild(tip_east_a_r1);
        setRotationAngle(tip_east_a_r1, 0.0F, 1.5708F, -0.7854F);
        tip_east_a_r1.setTextureOffset(6, 56).addBox(-1.5F, -3.5F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);

        tip_east_b_r1 = new ModelRenderer(this);
        tip_east_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_east.addChild(tip_east_b_r1);
        setRotationAngle(tip_east_b_r1, 0.0F, 1.5708F, 0.7854F);
        tip_east_b_r1.setTextureOffset(0, 56).addBox(-1.5F, -3.5F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);

        tip_south = new ModelRenderer(this);
        tip_south.setRotationPoint(0.0F, 0.5F, 1.0F);
        main_body.addChild(tip_south);
        setRotationAngle(tip_south, 0.0F, 3.1416F, 0.0F);


        tip_south_a_r1 = new ModelRenderer(this);
        tip_south_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_south.addChild(tip_south_a_r1);
        setRotationAngle(tip_south_a_r1, 0.0F, 1.5708F, -0.7854F);
        tip_south_a_r1.setTextureOffset(6, 56).addBox(-1.5F, -3.5F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);

        tip_south_b_r1 = new ModelRenderer(this);
        tip_south_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_south.addChild(tip_south_b_r1);
        setRotationAngle(tip_south_b_r1, 0.0F, 1.5708F, 0.7854F);
        tip_south_b_r1.setTextureOffset(0, 56).addBox(-1.5F, -3.5F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);

        tip_west = new ModelRenderer(this);
        tip_west.setRotationPoint(-6.0F, -0.5F, -5.0F);
        main_body.addChild(tip_west);
        setRotationAngle(tip_west, 0.0F, 1.5708F, 0.0F);


        tip_west_a_r1 = new ModelRenderer(this);
        tip_west_a_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_west.addChild(tip_west_a_r1);
        setRotationAngle(tip_west_a_r1, 0.0F, 1.5708F, -0.7854F);
        tip_west_a_r1.setTextureOffset(6, 56).addBox(-1.5F, -3.5F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);

        tip_west_b_r1 = new ModelRenderer(this);
        tip_west_b_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        tip_west.addChild(tip_west_b_r1);
        setRotationAngle(tip_west_b_r1, 0.0F, 1.5708F, 0.7854F);
        tip_west_b_r1.setTextureOffset(0, 56).addBox(-1.5F, -3.5F, 0.0F, 3.0F, 7.0F, 0.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        rope.showModel = entityIn.getLeashed();
        tip_north.showModel = !entityIn.getLeashed();
        tip_east.showModel = !entityIn.getLeashed();
        tip_south.showModel = !entityIn.getLeashed();
        tip_west.showModel = !entityIn.getLeashed();
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        main_body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
