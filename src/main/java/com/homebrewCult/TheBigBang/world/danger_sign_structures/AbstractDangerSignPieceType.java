package com.homebrewCult.TheBigBang.world.danger_sign_structures;

import com.homebrewCult.TheBigBang.blocks.DangerSignBlock;
import com.homebrewCult.TheBigBang.gui.quests.Questline;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.util.DangerSignPart;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public abstract class AbstractDangerSignPieceType extends TemplateStructurePiece {

    private final ResourceLocation piece;
    private final Rotation rotation;
    protected Direction templateDirection;

    //This might also become relevant in 1.15
    public AbstractDangerSignPieceType(TemplateManager manager, ResourceLocation pieceIn, BlockPos pos, Rotation rotationIn, IStructurePieceType type) {
        super(type, 1);
        this.piece = pieceIn;
        this.templatePosition = pos;
        this.rotation = rotationIn;
        setupTemplate(manager);
    }

    public AbstractDangerSignPieceType(TemplateManager manager, CompoundNBT nbt, IStructurePieceType type) {
        super(type, nbt);
        this.piece = new ResourceLocation(nbt.getString("Template"));
        this.rotation = Rotation.valueOf(nbt.getString("Rot"));
        setupTemplate(manager);
    }

    private void setupTemplate(TemplateManager manager) {
        Template template = manager.getTemplateDefaulted(this.piece);
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
        this.setup(template, this.templatePosition, placementsettings);
    }

    protected void readAdditional(CompoundNBT tagCompound) {
        super.readAdditional(tagCompound);
        tagCompound.putString("Template", this.piece.toString());
        tagCompound.putString("Rot", this.rotation.name());
    }

    @Override
    public boolean addComponentParts(IWorld worldIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
        int index = Math.abs(templatePosition.getX()/16 * templatePosition.getZ()/16) % getTemplatePaths().length;
        this.templateDirection = getTemplateDirection(index);
        Vec3i center = this.templatePosition.subtract(getTemplateOffset(index));
        int y = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, center.getX(), center.getZ());
        this.templatePosition = this.templatePosition.add(0, y + getTemplateOffset(index).getY(), 0);
        return super.addComponentParts(worldIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
    }

    /* In 1.15 it's probably gonna be more like this.
    public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
        int i = this.rotation == Rotation.NONE ? 8 : -7;
        int j = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.templatePosition.getX() + i, this.templatePosition.getZ() + i);
        this.templatePosition = this.templatePosition.add(0, j - 1, 0);
        return super.create(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
    }
     */

    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
        if (function.startsWith("DangerSign")) {
            BlockState state = ModBlocks.DANGER_SIGN.getDefaultState().with(DangerSignBlock.FACING, templateDirection);
            worldIn.setBlockState(pos, state.with(DangerSignBlock.QUESTLINE, getTemplateQuestline(worldIn.getBiome(pos))), 1);
            worldIn.setBlockState(pos.up(), state.with(DangerSignBlock.PART, DangerSignPart.TOPLEFT), 1);
            worldIn.setBlockState(pos.offset(templateDirection.rotateYCCW()), state.with(DangerSignBlock.PART, DangerSignPart.BOTTOMRIGHT), 1);
            worldIn.setBlockState(pos.offset(templateDirection.rotateYCCW()).up(), state.with(DangerSignBlock.PART, DangerSignPart.TOPRIGHT), 1);
        }
    }

    public Questline getTemplateQuestline(Biome biome) {
        return Questline.getQuestlineByBiome(biome);
    }

    abstract Vec3i getTemplateOffset(int index);

    abstract Direction getTemplateDirection(int index);

    abstract String[] getTemplatePaths();
}
