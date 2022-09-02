package com.homebrewCult.TheBigBang.world.danger_sign_structures;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;

public abstract class AbstractDangerSignStructure extends ScatteredStructure<NoFeatureConfig> {

    public AbstractDangerSignStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
        super(config);
    }

    protected int getSeedModifier() { return 64389054; }

    @Override
    public int getSize() {
        return 2;
    }

    public IStartFactory getStartFactory() {
        return AbstractDangerSignStructure.Start::new;
    }

    public class Start extends StructureStart {
        public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biomeIn, MutableBoundingBox boundingbox, int referenceIn, long seed) {
            super(structure, chunkX, chunkZ, biomeIn, boundingbox, referenceIn, seed);
        }

        public void init(ChunkGenerator<?> generator, TemplateManager manager, int chunkX, int chunkZ, Biome biomeIn) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            TheBigBang.print("Generating " + getStructureName() + " at " + blockpos);
            int index = Math.abs(chunkX * chunkZ) % getTemplatePaths().length;
            this.components.add(getStructurePiece(manager, new ResourceLocation(TheBigBang.MODID, getTemplatePaths()[index]), blockpos, Rotation.NONE, getPieceType()));
            //this.recalculateStructureSize();
        }
    }

    abstract IStructurePieceType getPieceType();

    abstract AbstractDangerSignPieceType getStructurePiece(TemplateManager manager, ResourceLocation resourceLocation, BlockPos pos, Rotation rot, IStructurePieceType piece);

    abstract String[] getTemplatePaths();

    /* Maybe I'll use this piece of code sometime...
    //Find the nearest non-air and non-water block
    BlockPos placePos = pos;
    boolean foundSurface = false;
		for(int i = 0; !foundSurface; i++) {
        if(i < 16) {
            Block b = worldIn.getBlockState(pos.down(i)).getBlock();
            if(b == Blocks.AIR || b == Blocks.WATER || b == Blocks.ACACIA_LEAVES || b == Blocks.BIRCH_LEAVES ||
                    b == Blocks.DARK_OAK_LEAVES || b == Blocks.JUNGLE_LEAVES || b == Blocks.OAK_LEAVES || b == Blocks.SPRUCE_LEAVES) {
                placePos = pos.down(i);
            } else {
                foundSurface = true;
            }
        }
    }
     */
}
