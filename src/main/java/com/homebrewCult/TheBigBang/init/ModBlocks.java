package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.blocks.*;
import com.homebrewCult.TheBigBang.inventory.DangerSignContainer;
import com.homebrewCult.TheBigBang.inventory.DivineAltarContainer;
import com.homebrewCult.TheBigBang.world.maple_gardens_features.RedMapleTree;
import com.homebrewCult.TheBigBang.world.maple_gardens_features.YellowMapleTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TheBigBang.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TheBigBang.MOD_ID)
public class ModBlocks {
	
	public static Block MITHRIL_ORE = null;
	public static Block ADAMANTIUM_ORE = null;
	public static Block ORIHALCON_ORE = null;
	
	public static Block RED_MAPLE_LEAVES = null; //new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)).setRegistryName(TheBigBang.MOD_ID, "red_maple_leaves");
	public static Block RED_MAPLE_SAPLING = null; //new MapleSaplingBlock(new RedMapleTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName(TheBigBang.MOD_ID, "red_maple_sapling");
	public static Block YELLOW_MAPLE_LEAVES = null; //new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)).setRegistryName(TheBigBang.MOD_ID, "yellow_maple_leaves");
	public static Block YELLOW_MAPLE_SAPLING = null; //new MapleSaplingBlock(new YellowMapleTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName(TheBigBang.MOD_ID, "yellow_maple_sapling");
	
	public static Block DANGER_SIGN = null;
	public static Block DIVINE_ALTAR = null;

	@ObjectHolder("danger_sign")
	public static final TileEntityType<DangerSignTile> DANGER_SIGN_TILE = null;
	@ObjectHolder("divine_altar")
	public static final TileEntityType<DivineAltarTile> DIVINE_ALTAR_TILE = null;

	@ObjectHolder("danger_sign")
	public static final ContainerType<DangerSignContainer> DANGER_SIGN_CONTAINER = null;
	@ObjectHolder("divine_altar")
	public static final ContainerType<DivineAltarContainer> DIVINE_ALTAR_CONTAINER = null;
	
	public static Block GOLEM_STONE = null;
	public static Block DARK_GOLEM_STONE = null;
	public static Block GRASSY_GOLEM_STONE = null;
	public static Block GRASSY_DARK_GOLEM_STONE = null;
	public static Block ROPE_FENCE = null;
	public static Block ROPE_LADDER = null;
	public static Block ORANGE_MUSHROOM_BLOCK = null;
	public static Block ORANGE_MUSHROOM_SLAB = null;
	public static Block ORANGE_MUSHROOM_STAIRS = null;
	public static Block BLUE_MUSHROOM_BLOCK = null;
	public static Block BLUE_MUSHROOM_SLAB = null;
	public static Block BLUE_MUSHROOM_STAIRS = null;
	public static Block MIXED_COBBLESTONE = null;

	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
			MITHRIL_ORE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(5).sound(SoundType.STONE))
					.setRegistryName(TheBigBang.MOD_ID, "mithril_ore"),
			ADAMANTIUM_ORE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(5).sound(SoundType.STONE))
					.setRegistryName(TheBigBang.MOD_ID, "adamantium_ore"),
			ORIHALCON_ORE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(5).sound(SoundType.STONE))
					.setRegistryName(TheBigBang.MOD_ID, "orihalcon_ore"),
			DANGER_SIGN = new DangerSignBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.WOOD))
					.setRegistryName(TheBigBang.MOD_ID, "danger_sign"),
			DIVINE_ALTAR = new DivineAltarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE))
					.setRegistryName(TheBigBang.MOD_ID, "divine_altar"),
			GOLEM_STONE = new GolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE))
					.setRegistryName(TheBigBang.MOD_ID, "golem_stone"),
			DARK_GOLEM_STONE = new DarkGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE))
					.setRegistryName(TheBigBang.MOD_ID, "dark_golem_stone"),
			GRASSY_GOLEM_STONE = new GrassyGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f).lightValue(0).sound(SoundType.STONE))
					.setRegistryName(TheBigBang.MOD_ID, "grassy_golem_stone"),
			GRASSY_DARK_GOLEM_STONE = new GrassyDarkGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f).lightValue(0).sound(SoundType.STONE))
					.setRegistryName(TheBigBang.MOD_ID, "grassy_dark_golem_stone"),
			ROPE_FENCE = new RopeFenceBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0f, 1.0f).sound(SoundType.WOOD))
					.setRegistryName(TheBigBang.MOD_ID, "rope_fence"),
			ROPE_LADDER = new RopeLadderBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.0F, 1.0F).sound(SoundType.WOOD))
					.setRegistryName(TheBigBang.MOD_ID, "rope_ladder"),
			ORANGE_MUSHROOM_BLOCK = new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD))
					.setRegistryName(TheBigBang.MOD_ID, "orange_mushroom_block"),
			ORANGE_MUSHROOM_SLAB = new SlabBlock(Block.Properties.from(ORANGE_MUSHROOM_BLOCK))
					.setRegistryName(TheBigBang.MOD_ID, "orange_mushroom_slab"),
			ORANGE_MUSHROOM_STAIRS = new StairsBlock(ORANGE_MUSHROOM_BLOCK::getDefaultState, Block.Properties.from(ORANGE_MUSHROOM_BLOCK))
					.setRegistryName(TheBigBang.MOD_ID, "orange_mushroom_stairs"),
			BLUE_MUSHROOM_BLOCK = new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD))
					.setRegistryName(TheBigBang.MOD_ID, "blue_mushroom_block"),
			BLUE_MUSHROOM_SLAB = new SlabBlock(Block.Properties.from(BLUE_MUSHROOM_BLOCK))
					.setRegistryName(TheBigBang.MOD_ID, "blue_mushroom_slab"),
			BLUE_MUSHROOM_STAIRS = new StairsBlock(BLUE_MUSHROOM_BLOCK::getDefaultState, Block.Properties.from(BLUE_MUSHROOM_BLOCK))
					.setRegistryName(TheBigBang.MOD_ID, "blue_mushroom_stairs"),
			RED_MAPLE_LEAVES = new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES))
					.setRegistryName(TheBigBang.MOD_ID, "red_maple_leaves"),
			YELLOW_MAPLE_LEAVES = new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES))
					.setRegistryName(TheBigBang.MOD_ID, "yellow_maple_leaves"),
			RED_MAPLE_SAPLING = new MapleSaplingBlock(new RedMapleTree(), Block.Properties.from(Blocks.OAK_SAPLING))
					.setRegistryName(TheBigBang.MOD_ID, "red_maple_sapling"),
			YELLOW_MAPLE_SAPLING = new MapleSaplingBlock(new YellowMapleTree(), Block.Properties.from(Blocks.OAK_SAPLING))
					.setRegistryName(TheBigBang.MOD_ID, "yellow_maple_sapling"),
			MIXED_COBBLESTONE = new Block(Block.Properties.from(Blocks.COBBLESTONE))
					.setRegistryName(TheBigBang.MOD_ID, "mixed_cobblestone")
		);
	}
	
	@SubscribeEvent
	public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().registerAll (			
			TileEntityType.Builder.create(DangerSignTile::new, DANGER_SIGN).build(null).setRegistryName(TheBigBang.MOD_ID, "danger_sign"),
			TileEntityType.Builder.create(DivineAltarTile::new, DIVINE_ALTAR).build(null).setRegistryName(TheBigBang.MOD_ID, "divine_altar")
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
}