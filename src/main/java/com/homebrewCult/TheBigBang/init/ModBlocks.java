package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.blocks.*;
import com.homebrewCult.TheBigBang.blocks.render.DivineAltarTileRenderer;
import com.homebrewCult.TheBigBang.inventory.DangerSignContainer;
import com.homebrewCult.TheBigBang.inventory.DivineAltarContainer;
import com.homebrewCult.TheBigBang.world.maple_gardens_features.RedMapleTree;
import com.homebrewCult.TheBigBang.world.maple_gardens_features.YellowMapleTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TheBigBang.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TheBigBang.MODID)
public class ModBlocks {
	
	public static Block MITHRIL_ORE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "mithril_ore");
	public static Block ADAMANTIUM_ORE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "adamantium_ore");
	public static Block ORIHALCON_ORE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "orihalcon_ore");
	
	public static Block RED_MAPLE_LEAVES = new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)).setRegistryName(TheBigBang.MODID, "red_maple_leaves");
	public static Block RED_MAPLE_SAPLING = new MapleSaplingBlock(new RedMapleTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName(TheBigBang.MODID, "red_maple_sapling");
	public static Block YELLOW_MAPLE_LEAVES = new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)).setRegistryName(TheBigBang.MODID, "yellow_maple_leaves");
	public static Block YELLOW_MAPLE_SAPLING = new MapleSaplingBlock(new YellowMapleTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName(TheBigBang.MODID, "yellow_maple_sapling");
	
	public static final Block DANGER_SIGN = null;
	public static final Block DIVINE_ALTAR = null;

	@ObjectHolder("danger_sign")
	public static final TileEntityType<DangerSignTile> DANGER_SIGN_TILE = null;
	@ObjectHolder("divine_altar")
	public static final TileEntityType<DivineAltarTile> DIVINE_ALTAR_TILE = null;

	@ObjectHolder("danger_sign")
	public static final ContainerType<DangerSignContainer> DANGER_SIGN_CONTAINER = null;
	@ObjectHolder("divine_altar")
	public static final ContainerType<DivineAltarContainer> DIVINE_ALTAR_CONTAINER = null;
	
	public static final Block GOLEM_STONE = new GolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "golem_stone");
	public static final Block DARK_GOLEM_STONE = new DarkGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "dark_golem_stone");
	public static final Block GRASSY_GOLEM_STONE = new GrassyGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "grassy_golem_stone");
	public static final Block GRASSY_DARK_GOLEM_STONE = new GrassyDarkGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "grassy_dark_golem_stone");
	public static final Block ROPE_FENCE = new RopeFenceBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0f, 1.0f).sound(SoundType.WOOD)).setRegistryName(TheBigBang.MODID, "rope_fence");
	public static final Block ROPE_LADDER = new RopeLadderBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 1.0F).sound(SoundType.WOOD)).setRegistryName(TheBigBang.MODID, "rope_ladder");
	public static final Block ORANGE_MUSHROOM_BLOCK = new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD)).setRegistryName(TheBigBang.MODID, "orange_mushroom_block");
	public static final Block ORANGE_MUSHROOM_SLAB = new SlabBlock(Block.Properties.from(ORANGE_MUSHROOM_BLOCK)).setRegistryName(TheBigBang.MODID, "orange_mushroom_slab");
	public static final Block ORANGE_MUSHROOM_STAIRS = new StairsBlock(() -> ORANGE_MUSHROOM_BLOCK.getDefaultState(), Block.Properties.from(ORANGE_MUSHROOM_BLOCK)).setRegistryName(TheBigBang.MODID, "orange_mushroom_stairs");
	public static final Block BLUE_MUSHROOM_BLOCK = new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD)).setRegistryName(TheBigBang.MODID, "blue_mushroom_block");
	public static final Block BLUE_MUSHROOM_SLAB = new SlabBlock(Block.Properties.from(BLUE_MUSHROOM_BLOCK)).setRegistryName(TheBigBang.MODID, "blue_mushroom_slab");
	public static final Block BLUE_MUSHROOM_STAIRS = new StairsBlock(() -> BLUE_MUSHROOM_BLOCK.getDefaultState(), Block.Properties.from(BLUE_MUSHROOM_BLOCK)).setRegistryName(TheBigBang.MODID, "blue_mushroom_stairs");

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll (
			ModBlocks.MITHRIL_ORE,
			ModBlocks.ADAMANTIUM_ORE,
			ModBlocks.ORIHALCON_ORE,
			
			ModBlocks.GOLEM_STONE,
			ModBlocks.DARK_GOLEM_STONE,
			ModBlocks.GRASSY_GOLEM_STONE,
			ModBlocks.GRASSY_DARK_GOLEM_STONE,
			ModBlocks.RED_MAPLE_LEAVES,
			ModBlocks.RED_MAPLE_SAPLING,
			ModBlocks.YELLOW_MAPLE_LEAVES,
			ModBlocks.YELLOW_MAPLE_SAPLING,
			ModBlocks.ROPE_FENCE,
			ModBlocks.ROPE_LADDER,
			ModBlocks.ORANGE_MUSHROOM_BLOCK,
			ModBlocks.ORANGE_MUSHROOM_SLAB,
			ModBlocks.ORANGE_MUSHROOM_STAIRS,
			ModBlocks.BLUE_MUSHROOM_BLOCK,
			ModBlocks.BLUE_MUSHROOM_SLAB,
			ModBlocks.BLUE_MUSHROOM_STAIRS,
			
			new DangerSignBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.WOOD)).setRegistryName(TheBigBang.MODID, "danger_sign"),
			new DivineAltarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "monster_furnace"),
			new DivineAltarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "divine_altar")
		);
	}
	
	public static void registerBlockColors(BlockColors colors) {
		colors.register(BlockColorHandler.INSTANCE, 
				ModBlocks.GRASSY_GOLEM_STONE, 
				ModBlocks.GRASSY_DARK_GOLEM_STONE,
				ModBlocks.RED_MAPLE_LEAVES		
				);
	}
	
	@SubscribeEvent
	public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().registerAll (			
			TileEntityType.Builder.create(DangerSignTile::new, DANGER_SIGN).build(null).setRegistryName(TheBigBang.MODID, "danger_sign"),
			TileEntityType.Builder.create(DivineAltarTile::new, DIVINE_ALTAR).build(null).setRegistryName(TheBigBang.MODID, "divine_altar")
		);
	}
	
	@SubscribeEvent
	public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register (IForgeContainerType.create((id, inv, data) -> {
			BlockPos pos = data.readBlockPos();
			World world = TheBigBang.proxy.getClientWorld();
			return new DangerSignContainer(id, inv, world, pos);
		}).setRegistryName("danger_sign"));
		event.getRegistry().register (IForgeContainerType.create((id, inv, data) -> {
			BlockPos pos = data.readBlockPos();
			World world = TheBigBang.proxy.getClientWorld();
			return new DivineAltarContainer(id, inv, world, pos);
		}).setRegistryName("divine_altar"));
	}

	//Call from clientLoad in Main Class
	public static void registerBlockRenderers(final FMLClientSetupEvent event) {
		ClientRegistry.bindTileEntitySpecialRenderer(DivineAltarTile.class, new DivineAltarTileRenderer<>());
	}
}