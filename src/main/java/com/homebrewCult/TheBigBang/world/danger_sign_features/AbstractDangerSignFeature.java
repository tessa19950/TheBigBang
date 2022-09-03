package com.homebrewCult.TheBigBang.world.danger_sign_features;

import java.util.Random;
import java.util.function.Function;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.blocks.DangerSignBlock;
import com.homebrewCult.TheBigBang.gui.quests.Questline;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.util.DangerSignPart;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.StructureMode;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public abstract class AbstractDangerSignFeature extends Feature<NoFeatureConfig> {

	protected Direction templateDirection;
	
	public AbstractDangerSignFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		return this.place(worldIn, generator, rand, pos, config, BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
	}
	
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config, BlockIgnoreStructureProcessor processor) { 		
		//Select a template and add the relevant offset to the build position.
		int randIndex = rand.nextInt(4);
		Template template = getTemplate(((ServerWorld)worldIn.getWorld()).getSaveHandler().getStructureTemplateManager(), randIndex);
		PlacementSettings placeSettings = (new PlacementSettings()).setRandom(rand).addProcessor(processor);
		templateDirection = getTemplateDirection(randIndex); 
		pos = pos.add(getTemplateOffset(randIndex));

		TheBigBang.print("Generating " + getTemplateName() + " at " + pos);
		if (template.addBlocksToWorld(worldIn, pos, placeSettings, 2)) {
			//Replace the Structure Block with a Danger Sign
			for(Template.BlockInfo template$blockinfo : template.func_215381_a(pos, placeSettings, Blocks.STRUCTURE_BLOCK)) {
				if (template$blockinfo.nbt != null) {
					StructureMode structuremode = StructureMode.valueOf(template$blockinfo.nbt.getString("mode"));
					if (structuremode == StructureMode.DATA) {
						this.handleDataMarker(template$blockinfo.nbt.getString("metadata"), template$blockinfo.pos, worldIn, rand);
					}
				}
			}
		}
		return true;	
	}
	
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand) {
    	if (function.startsWith("DangerSign")) {  		   		
    		BlockState state = ModBlocks.DANGER_SIGN.getDefaultState().with(DangerSignBlock.FACING, templateDirection);
    		worldIn.setBlockState(pos, state.with(DangerSignBlock.QUESTLINE, getTemplateQuestline(worldIn.getBiome(pos))), 1);
    		worldIn.setBlockState(pos.up(), state.with(DangerSignBlock.PART, DangerSignPart.TOPLEFT), 1);
    		worldIn.setBlockState(pos.offset(templateDirection.rotateYCCW()), state.with(DangerSignBlock.PART, DangerSignPart.BOTTOMRIGHT), 1);
    		worldIn.setBlockState(pos.offset(templateDirection.rotateYCCW()).up(), state.with(DangerSignBlock.PART, DangerSignPart.TOPRIGHT), 1);
    	}
    }
	
	public Template getTemplate(TemplateManager manager, int index) {
		int i = index + 1;
		return manager.getTemplate(new ResourceLocation(TheBigBang.MODID,this.getTemplateName().replace("thebigbang:", "") + "_" + i));
	}	
	
	public Questline getTemplateQuestline(Biome biome) {
		return Questline.getQuestlineByBiome(biome);
	}
	
	public abstract String getTemplateName();
	
	public abstract Vec3i getTemplateOffset(int index);
	
	public abstract Direction getTemplateDirection(int index);
}
