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

public class PlateauSignPieceType extends AbstractDangerSignPieceType {
    public PlateauSignPieceType(TemplateManager manager, ResourceLocation pieceIn, BlockPos pos, Rotation rotationIn, IStructurePieceType type) {
        super(manager, pieceIn, pos, rotationIn, type);
    }

    public PlateauSignPieceType(TemplateManager manager, CompoundNBT nbt) {
        super(manager, nbt, ModFeatures.DANGER_SIGN_PLATEAU_PIECE);
    }

    public Vec3i getTemplateOffset(int index) {
        switch(index) {
            case 0: case 1: return new Vec3i(-4,0,-2);
            case 2: return new Vec3i(-2,0,-2);
            case 3: return new Vec3i(-2,0,-4);
            default: return new Vec3i(0,0,0);
        }
    }

    public Direction getTemplateDirection(int index) {
        switch(index) {
            case 0: return Direction.SOUTH;
            case 2: return Direction.EAST;
            case 3: return Direction.WEST;
            default: return Direction.NORTH;
        }
    }

    @Override
    String[] getTemplatePaths() {
        return PlateauSignStructure.TEMPLATE_PATHS;
    }
}
