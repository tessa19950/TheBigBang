package com.homebrewCult.TheBigBang.world.danger_sign_structures;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModFeatures;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.PillagerOutpostConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class IceSignStructure extends AbstractDangerSignStructure{

    public static final String[] TEMPLATE_PATHS = new String[]{ "ice_sign_1", "ice_sign_2", "ice_sign_3", "ice_sign_4" };

    public IceSignStructure(Function<Dynamic<?>, ? extends PillagerOutpostConfig> config) {
        super(config);
    }

    @Override
    IStructurePieceType getPieceType() {
        return ModFeatures.DANGER_SIGN_ICE_PIECE;
    }

    @Override
    AbstractDangerSignPieceType getStructurePiece(TemplateManager manager, ResourceLocation resourceLocation, BlockPos pos, Rotation rot, IStructurePieceType piece) {
        return new IceSignPieceType(manager, resourceLocation, pos, rot, piece);
    }

    @Override
    String[] getTemplatePaths() {
        return TEMPLATE_PATHS;
    }

    @Override
    public String getStructureName() {
        return TheBigBang.MODID + ":danger_sign_ice";
    }
}
