package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.blocks.BlockColorHandler;
import com.homebrewCult.TheBigBang.blocks.DangerSignBlock;
import com.homebrewCult.TheBigBang.blocks.DangerSignTile;
import com.homebrewCult.TheBigBang.blocks.DarkGolemStoneBlock;
import com.homebrewCult.TheBigBang.blocks.GolemStoneBlock;
import com.homebrewCult.TheBigBang.blocks.GrassyDarkGolemStoneBlock;
import com.homebrewCult.TheBigBang.blocks.GrassyGolemStoneBlock;
import com.homebrewCult.TheBigBang.blocks.MapleSaplingBlock;
import com.homebrewCult.TheBigBang.blocks.MonsterFurnaceBlock;
import com.homebrewCult.TheBigBang.blocks.MonsterFurnaceTile;
import com.homebrewCult.TheBigBang.inventory.DangerSignContainer;
import com.homebrewCult.TheBigBang.inventory.MonsterFurnaceContainer;
import com.homebrewCult.TheBigBang.world.maple_gardens_features.RedMapleTree;
import com.homebrewCult.TheBigBang.world.maple_gardens_features.YellowMapleTree;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.OakTree;
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
	
	public static Block MITHRIL_ORE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "mithril_ore");
	public static Block ADAMANTIUM_ORE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "adamantium_ore");
	public static Block ORIHALCON_ORE = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "orihalcon_ore");
	
	public static Block RED_MAPLE_LEAVES = new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)).setRegistryName(TheBigBang.MODID, "red_maple_leaves");
	public static Block RED_MAPLE_SAPLING = new MapleSaplingBlock(new RedMapleTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName(TheBigBang.MODID, "red_maple_sapling");
	public static Block YELLOW_MAPLE_LEAVES = new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)).setRegistryName(TheBigBang.MODID, "yellow_maple_leaves");
	public static Block YELLOW_MAPLE_SAPLING = new MapleSaplingBlock(new YellowMapleTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT)).setRegistryName(TheBigBang.MODID, "yellow_maple_sapling");
	
	public static final Block DANGER_SIGN = null;
	public static final Block MONSTER_FURNACE = null;
	@ObjectHolder("danger_sign")
	public static final TileEntityType<DangerSignTile> DANGER_SIGN_TILE = null;
	@ObjectHolder("monster_furnace")
	public static final TileEntityType<MonsterFurnaceTile> MONSTER_FURNACE_TILE = null;
	@ObjectHolder("danger_sign")
	public static final ContainerType<DangerSignContainer> DANGER_SIGN_CONTAINER = null;
	@ObjectHolder("monster_furnace")
	public static final ContainerType<MonsterFurnaceContainer> MONSTER_FURNACE_CONTAINER = null;
	
	public static final Block GOLEM_STONE = new GolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "golem_stone");
	public static final Block DARK_GOLEM_STONE = new DarkGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "dark_golem_stone");
	public static final Block GRASSY_GOLEM_STONE = new GrassyGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "grassy_golem_stone");
	public static final Block GRASSY_DARK_GOLEM_STONE = new GrassyDarkGolemStoneBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "grassy_dark_golem_stone");
	
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
			
			new DangerSignBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.WOOD)).setRegistryName(TheBigBang.MODID, "danger_sign"),
			new MonsterFurnaceBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).lightValue(0).sound(SoundType.STONE)).setRegistryName(TheBigBang.MODID, "monster_furnace")
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
			TileEntityType.Builder.create(MonsterFurnaceTile::new, MONSTER_FURNACE).build(null).setRegistryName(TheBigBang.MODID, "monster_furnace")
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
			return new MonsterFurnaceContainer(id, inv, world, pos);
		}).setRegistryName("monster_furnace"));
	}
}