package com.homebrewCult.TheBigBang.world.danger_sign_structures;

import com.homebrewCult.TheBigBang.init.ModFeatures;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class DesertSignPieceType extends AbstractDangerSignPieceType {
    public DesertSignPieceType(TemplateManager manager, ResourceLocation pieceIn, BlockPos pos, Rotation rotationIn, IStructurePieceType type) {
        super(manager, pieceIn, pos, rotationIn, type);
    }

    public DesertSignPieceType(TemplateManager manager, CompoundNBT nbt) {
        super(manager, nbt, ModFeatures.DANGER_SIGN_DESERT_PIECE);
    }

    @Override
    public Vec3i getTemplateOffset(int index) {
        switch(index) {
            case 0: return new Vec3i(-3,-6,-2);
            case 1: return new Vec3i(-3,-4,-2);
            case 2: case 3: return new Vec3i(-2,-2,-3);
            default: return new Vec3i(0,0,0);
        }
    }

    @Override
    public Direction getTemplateDirection(int index) {
        switch(index) {
            case 1: return Direction.SOUTH;
            case 2: return Direction.EAST;
            case 3: return Direction.WEST;
            default: return Direction.NORTH;
        }
    }

    @Override
    String[] getTemplatePaths() {
        return DesertSignStructure.TEMPLATE_PATHS;
    }
}
