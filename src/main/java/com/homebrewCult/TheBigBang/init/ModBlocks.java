package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.blocks.BlockColorHandler;
import com.homebrewCult.TheBigBang.blocks.DangerSignBlock;
import com.homebrewCult.TheBigBang.blocks.DangerSignTile;
import com.homebrewCult.TheBigBang.blocks.DarkGolemStoneBlock;
import com.homebrewCult.TheBigBang.blocks.GolemStoneBlock;
import com.homebrewCult.TheBigBang.blocks.GrassyDarkGolemStoneBlock;
import com.homebrewCult.TheBigBang.blocks.GrassyGolemStoneBlock;
import com.homebrewCult.TheBigBang.inventory.DangerSignContainer;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TheBigBang.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TheBigBang.MODID)
public class ModBlocks {
	
	@ObjectHolder("mithril_ore")
	public static Block MITHRIL_ORE = null;
	@ObjectHolder("adamantium_ore")
	public static Block ADAMANTIUM_ORE = null;
	@ObjectHolder("orihalcon_ore")
	public static Block ORIHALCON_ORE = null;
	
	@ObjectHolder("danger_sign")
	public static final Block DANGER_SIGN = null;
	@ObjectHolder("danger_sign")
	public static final TileEntityType<DangerSignTile> DANGER_SIGN_TILE = null;
	@ObjectHolder("danger_sign")
	public static final ContainerType<DangerSignContainer> DANGER_SIGN_CONTAINER = null;
	public static final int DANGER_SIGN_GUI_ID = 0;
	
	@ObjectHolder("golem_stone")
	public static final Block GOLEM_STONE = null;
	@ObjectHolder("grassy_golem_stone")
	public static final Block GRASSY_GOLEM_STONE = null;
	@ObjectHolder("dark_golem_stone")
	public static final Block DARK_GOLEM_STONE = null;
	@ObjectHolder("grassy_dark_golem_stone")
	public static final Block GRASSY_DARK_GOLEM_STONE = null;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll (
			new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "mithril_ore"),
			new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "adamantium_ore"),
			new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "orihalcon_ore"),
			
			new GolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "golem_stone"),
			new DarkGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "dark_golem_stone"),
			new GrassyGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "grassy_golem_stone"),
			new GrassyDarkGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "grassy_dark_golem_stone"),
			
			new DangerSignBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.WOOD)).setRegistryName(TheBigBang.MODID, "danger_sign")
		);
	}
	
	public static void registerBlockColors(BlockColors colors) {
		colors.register(BlockColorHandler.INSTANCE, ModBlocks.GRASSY_GOLEM_STONE, ModBlocks.GRASSY_DARK_GOLEM_STONE);
	}
	
	@SubscribeEvent
	public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().register (			
			TileEntityType.Builder.create(DangerSignTile::new, DANGER_SIGN).build(null).setRegistryName(TheBigBang.MODID, "danger_sign")
		);
	}
	
	@SubscribeEvent
	public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register (IForgeContainerType.create((id, inv, data) -> {
			BlockPos pos = data.readBlockPos();
			World world = TheBigBang.proxy.getClientWorld();
			return new DangerSignContainer(id, inv, world, pos);
		}).setRegistryName("danger_sign"));
	}
}