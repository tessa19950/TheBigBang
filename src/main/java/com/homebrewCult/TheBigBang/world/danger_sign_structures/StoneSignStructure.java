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

public class StoneSignStructure extends AbstractDangerSignStructure{

    public static final String[] TEMPLATE_PATHS = new String[]{ "stone_sign_1", "stone_sign_2", "stone_sign_3", "stone_sign_4" };

    public StoneSignStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
        super(config);
    }

    @Override
    IStructurePieceType getPieceType() {
        return ModFeatures.DANGER_SIGN_STONE_PIECE;
    }

    @Override
    AbstractDangerSignPieceType getStructurePiece(TemplateManager manager, ResourceLocation resourceLocation, BlockPos pos, Rotation rot, IStructurePieceType piece) {
        return new StoneSignPieceType(manager, resourceLocation, pos, rot, piece);
    }

    @Override
    String[] getTemplatePaths() {
        return TEMPLATE_PATHS;
    }

    @Override
    public String getStructureName() {
        return TheBigBang.MODID + ":danger_sign_stone";
    }
}
