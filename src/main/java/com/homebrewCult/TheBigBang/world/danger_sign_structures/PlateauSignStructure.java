package com.homebrewCult.TheBigBang.world.danger_sign_structures;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModFeatures;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public class PlateauSignStructure extends AbstractDangerSignStructure {

    public static final String[] TEMPLATE_PATHS = new String[]{ "plateau_sign_1", "plateau_sign_2", "plateau_sign_3", "plateau_sign_4" };

    public PlateauSignStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public IStructurePieceType getPieceType() {
        return ModFeatures.DANGER_SIGN_PLATEAU_PIECE;
    }

    @Override
    AbstractDangerSignPieceType getStructurePiece(TemplateManager manager, ResourceLocation resourceLocation, BlockPos pos, Rotation rot, IStructurePieceType piece) {
        return new PlateauSignPieceType(manager, resourceLocation, pos, rot, piece);
    }

    @Override
    protected String[] getTemplatePaths() {
        return TEMPLATE_PATHS;
    }

    @Override
    public String getStructureName() {
        return TheBigBang.getNamespacedKey("danger_sign_plateau");
    }
}
