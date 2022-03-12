package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.*;
import com.homebrewCult.TheBigBang.entities.mob.*;
import com.homebrewCult.TheBigBang.entities.render.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("unchecked")
@Mod.EventBusSubscriber(modid = TheBigBang.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TheBigBang.MODID)
public class ModEntities {
	public static final EntityType<StumpEntity> STUMP_ENTITY = (EntityType<StumpEntity>)buildEntityType(StumpEntity::new, EntityClassification.CREATURE, 1f, 1.5f, "stump_entity");
	public static final EntityType<DarkStumpEntity> DARK_STUMP_ENTITY = (EntityType<DarkStumpEntity>)buildEntityType(DarkStumpEntity::new, EntityClassification.CREATURE, 1f, 1.5f, "dark_stump_entity");
	public static final EntityType<AxeStumpEntity> AXE_STUMP_ENTITY = (EntityType<AxeStumpEntity>)buildEntityType(AxeStumpEntity::new, EntityClassification.CREATURE, 1f, 1.5f, "axe_stump_entity");
	public static final EntityType<DarkAxeStumpEntity> DARK_AXE_STUMP_ENTITY = (EntityType<DarkAxeStumpEntity>)buildEntityType(DarkAxeStumpEntity::new, EntityClassification.CREATURE, 1f, 1.5f, "dark_axe_stump_entity");
	public static final EntityType<BubblingEntity> BUBBLING_ENTITY = (EntityType<BubblingEntity>)buildEntityType(BubblingEntity::new, EntityClassification.CREATURE, 1f, 1f, "bubbling_entity");
	public static final EntityType<GreenBubblingEntity> GREEN_BUBBLING_ENTITY = (EntityType<GreenBubblingEntity>)buildEntityType(GreenBubblingEntity::new, EntityClassification.CREATURE, 1f, 1f, "green_bubbling_entity");
	public static final EntityType<OctopusEntity> OCTOPUS_ENTITY = (EntityType<OctopusEntity>)buildEntityType(OctopusEntity::new, EntityClassification.CREATURE, 1f, 2.5f, "octopus_entity");
	public static final EntityType<EvilEyeEntity> EVIL_EYE_ENTITY = (EntityType<EvilEyeEntity>)buildEntityType(EvilEyeEntity::new, EntityClassification.MONSTER, 1.2f, 1f, "evil_eye_entity");	
	public static final EntityType<CurseEyeEntity> CURSE_EYE_ENTITY = (EntityType<CurseEyeEntity>)buildEntityType(CurseEyeEntity::new, EntityClassification.MONSTER, 1.2f, 1f, "curse_eye_entity");
	public static final EntityType<ColdEyeEntity> COLD_EYE_ENTITY = (EntityType<ColdEyeEntity>)buildEntityType(ColdEyeEntity::new, EntityClassification.MONSTER, 1.2f, 1f, "cold_eye_entity");
	public static final EntityType<OrangeMushroomEntity> ORANGE_MUSHROOM_ENTITY = (EntityType<OrangeMushroomEntity>)buildEntityType(OrangeMushroomEntity::new, EntityClassification.CREATURE, 1f, 1.4f, "orange_mushroom_entity");
	public static final EntityType<BlueMushroomEntity> BLUE_MUSHROOM_ENTITY = (EntityType<BlueMushroomEntity>)buildEntityType(BlueMushroomEntity::new, EntityClassification.CREATURE, 1f, 1.4f, "blue_mushroom_entity");
	public static final EntityType<ZombieMushroomEntity> ZOMBIE_MUSHROOM_ENTITY = (EntityType<ZombieMushroomEntity>)buildEntityType(ZombieMushroomEntity::new, EntityClassification.CREATURE, 1f, 1.4f, "zombie_mushroom_entity");
	public static final EntityType<JrYetiEntity> JRYETI_ENTITY = (EntityType<JrYetiEntity>)buildEntityType(JrYetiEntity::new, EntityClassification.CREATURE, 0.8f, 0.8f, "jryeti_entity");
	public static final EntityType<DarkJrYetiEntity> DARK_JRYETI_ENTITY = (EntityType<DarkJrYetiEntity>)buildEntityType(DarkJrYetiEntity::new, EntityClassification.CREATURE, 0.8f, 0.8f, "dark_jryeti_entity");
	public static final EntityType<GreenSnailEntity> GREEN_SNAIL_ENTITY = (EntityType<GreenSnailEntity>)buildEntityType(GreenSnailEntity::new, EntityClassification.CREATURE, 0.6f, 0.5f, "green_snail_entity");
	public static final EntityType<BlueSnailEntity> BLUE_SNAIL_ENTITY = (EntityType<BlueSnailEntity>)buildEntityType(BlueSnailEntity::new, EntityClassification.CREATURE, 0.8f, 0.8f, "blue_snail_entity");
	public static final EntityType<RedSnailEntity> RED_SNAIL_ENTITY = (EntityType<RedSnailEntity>)buildEntityType(RedSnailEntity::new, EntityClassification.CREATURE, 0.8f, 0.8f, "red_snail_entity");
	public static final EntityType<RibbonPigEntity> RIBBON_PIG_ENTITY = (EntityType<RibbonPigEntity>)buildEntityType(RibbonPigEntity::new, EntityClassification.CREATURE, 1.6f, 1f, "ribbon_pig_entity");
	public static final EntityType<StoneGolemEntity> STONE_GOLEM_ENTITY = (EntityType<StoneGolemEntity>)buildEntityType(StoneGolemEntity::new, EntityClassification.CREATURE, 1.4f, 3.6f, "stone_golem_entity");
	public static final EntityType<DarkStoneGolemEntity> DARK_STONE_GOLEM_ENTITY = (EntityType<DarkStoneGolemEntity>)buildEntityType(DarkStoneGolemEntity::new, EntityClassification.CREATURE, 1.4f, 3.6f, "dark_stone_golem_entity");
	public static final EntityType<MixedGolemEntity> MIXED_GOLEM_ENTITY = (EntityType<MixedGolemEntity>)buildEntityType(MixedGolemEntity::new, EntityClassification.CREATURE, 1.4f, 3.6f, "mixed_golem_entity");
	public static final EntityType<IceGolemEntity> ICE_GOLEM_ENTITY = (EntityType<IceGolemEntity>)buildEntityType(IceGolemEntity::new, EntityClassification.CREATURE, 1.4f, 3.6f, "ice_golem_entity");
	public static final EntityType<FireGolemEntity> FIRE_GOLEM_ENTITY = (EntityType<FireGolemEntity>)buildEntityType(FireGolemEntity::new, EntityClassification.CREATURE, 1.4f, 3.6f, "fire_golem_entity");
	public static final EntityType<DrakeEntity> DRAKE_ENTITY = (EntityType<DrakeEntity>)buildEntityType(DrakeEntity::new, EntityClassification.CREATURE, 1.8f, 2f, "drake_entity");
	public static final EntityType<CopperDrakeEntity> COPPER_DRAKE_ENTITY = (EntityType<CopperDrakeEntity>)buildEntityType(CopperDrakeEntity::new, EntityClassification.CREATURE, 1.8f, 2f, "copper_drake_entity");
	public static final EntityType<DarkDrakeEntity> DARK_DRAKE_ENTITY = (EntityType<DarkDrakeEntity>)buildEntityType(DarkDrakeEntity::new, EntityClassification.MONSTER, 1.8f, 2f, "dark_drake_entity");
	public static final EntityType<IceDrakeEntity> ICE_DRAKE_ENTITY = (EntityType<IceDrakeEntity>)buildEntityType(IceDrakeEntity::new, EntityClassification.MONSTER, 1.8f, 2f, "ice_drake_entity");
	public static final EntityType<RedDrakeEntity> RED_DRAKE_ENTITY = (EntityType<RedDrakeEntity>)buildEntityType(RedDrakeEntity::new, EntityClassification.MONSTER, 1.8f, 2f, "red_drake_entity");
	public static final EntityType<YetiEntity> YETI_ENTITY = (EntityType<YetiEntity>)buildEntityType(YetiEntity::new, EntityClassification.MONSTER, 2f, 3f, "yeti_entity");
	public static final EntityType<DarkYetiEntity> DARK_YETI_ENTITY = (EntityType<DarkYetiEntity>)buildEntityType(DarkYetiEntity::new, EntityClassification.MONSTER, 2f, 3f, "dark_yeti_entity");
	public static final EntityType<PepeEntity> PEPE_ENTITY = (EntityType<PepeEntity>)buildEntityType(PepeEntity::new, EntityClassification.CREATURE, 0.75f, 1f, "pepe_entity");
	public static final EntityType<DarkPepeEntity> DARK_PEPE_ENTITY = (EntityType<DarkPepeEntity>)buildEntityType(DarkPepeEntity::new, EntityClassification.CREATURE, 0.75f, 1f, "dark_pepe_entity");

	public static final EntityType<SubiEntity> SUBI = (EntityType<SubiEntity>) EntityType.Builder.create(SubiEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) -> 
		new SubiEntity(world)).size(0.25f, 0.25f).build(TheBigBang.MODID + "subi").setRegistryName(TheBigBang.MODID, "subi");
	public static final EntityType<TobiEntity> TOBI = (EntityType<TobiEntity>) EntityType.Builder.create(TobiEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) -> 
		new TobiEntity(world)).size(0.25f, 0.25f).build(TheBigBang.MODID + "tobi").setRegistryName(TheBigBang.MODID, "tobi");
	public static final EntityType<SteelyEntity> STEELY = (EntityType<SteelyEntity>) EntityType.Builder.create(SteelyEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) -> 
		new SteelyEntity(world)).size(0.25f, 0.25f).build(TheBigBang.MODID + "steely").setRegistryName(TheBigBang.MODID, "steely");
	public static final EntityType<IlbiEntity> ILBI = (EntityType<IlbiEntity>) EntityType.Builder.create(IlbiEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) -> 
		new IlbiEntity(world)).size(0.25f, 0.25f).build(TheBigBang.MODID + "ilbi").setRegistryName(TheBigBang.MODID, "ilbi");
	public static final EntityType<FireGearEntity> FIRE_GEAR = (EntityType<FireGearEntity>) EntityType.Builder.create(FireGearEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) ->
		new FireGearEntity(world)).size(2.0f, 2f).build(TheBigBang.MODID + "fire_gear").setRegistryName(TheBigBang.MODID, "fire_gear");
	public static final EntityType<PoisonMistEntity> POISON_MIST = (EntityType<PoisonMistEntity>) EntityType.Builder.create(PoisonMistEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) ->
		new PoisonMistEntity(world)).size(2.0f, 2f).build(TheBigBang.MODID + "poison_mist").setRegistryName(TheBigBang.MODID, "poison_mist");
	public static final EntityType<GenesisBeamEntity> GENESIS_BEAM = (EntityType<GenesisBeamEntity>) EntityType.Builder.create(GenesisBeamEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) ->
		new GenesisBeamEntity(world)).size(0.25f, 3f).build(TheBigBang.MODID + "genesis_beam").setRegistryName(TheBigBang.MODID, "genesis_beam");
	public static final EntityType<BombArrowEntity> BOMB_ARROW = (EntityType<BombArrowEntity>) EntityType.Builder.create(BombArrowEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) ->
		new BombArrowEntity(world)).size(0.25f, 0.25f).build(TheBigBang.MODID + "bomb_arrow").setRegistryName(TheBigBang.MODID, "bomb_arrow");
	public static final EntityType<SnipingArrowEntity> SNIPING_ARROW = (EntityType<SnipingArrowEntity>) EntityType.Builder.create(SnipingArrowEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) ->
		new SnipingArrowEntity(world)).size(0.25f, 0.25f).build(TheBigBang.MODID + "sniping_arrow").setRegistryName(TheBigBang.MODID, "sniping_arrow");
	public static final EntityType<HurricaneArrowEntity> HURRICANE_ARROW = (EntityType<HurricaneArrowEntity>) EntityType.Builder.create(HurricaneArrowEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) ->
		new HurricaneArrowEntity(world)).size(0.25f, 0.25f).build(TheBigBang.MODID + "hurricane_arrow").setRegistryName(TheBigBang.MODID, "hurricane_arrow");
	public static final EntityType<DragonCrusherStabEntity> DRAGON_CRUSHER_STAB = (EntityType<DragonCrusherStabEntity>) EntityType.Builder.create(DragonCrusherStabEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) ->
		new DragonCrusherStabEntity(world)).size(0.25f, 0.25f).build(TheBigBang.MODID + "dragon_crusher_stab").setRegistryName(TheBigBang.MODID, "dragon_crusher_stab");
	public static final EntityType<StealEntity> STEAL = (EntityType<StealEntity>) EntityType.Builder.create(StealEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) ->
		new StealEntity(world)).size(0.25f, 0.25f).build(TheBigBang.MODID + "steal").setRegistryName(TheBigBang.MODID, "steal");

	public static final EntityType<ManaRockEntity> MANA_ROCK = (EntityType<ManaRockEntity>) EntityType.Builder.create(ManaRockEntity::new, EntityClassification.MISC).setCustomClientFactory((spawnEntity, world) ->
	new ManaRockEntity(world)).size(0.6f, 1.6f).build(TheBigBang.MODID + "mana_rock").setRegistryName(TheBigBang.MODID, "mana_rock");
	
	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {	
		event.getRegistry().registerAll (
				HURRICANE_ARROW, BOMB_ARROW, SNIPING_ARROW, GENESIS_BEAM, SUBI, TOBI, STEELY, ILBI, DRAGON_CRUSHER_STAB, STEAL, FIRE_GEAR, POISON_MIST, MANA_ROCK,
				STUMP_ENTITY, DARK_STUMP_ENTITY, AXE_STUMP_ENTITY, DARK_AXE_STUMP_ENTITY, OCTOPUS_ENTITY, EVIL_EYE_ENTITY, CURSE_EYE_ENTITY, COLD_EYE_ENTITY, BUBBLING_ENTITY, GREEN_BUBBLING_ENTITY,
				ORANGE_MUSHROOM_ENTITY, BLUE_MUSHROOM_ENTITY, ZOMBIE_MUSHROOM_ENTITY, JRYETI_ENTITY, DARK_JRYETI_ENTITY, GREEN_SNAIL_ENTITY, BLUE_SNAIL_ENTITY, RED_SNAIL_ENTITY, 
				RIBBON_PIG_ENTITY, STONE_GOLEM_ENTITY, DARK_STONE_GOLEM_ENTITY, MIXED_GOLEM_ENTITY, ICE_GOLEM_ENTITY, FIRE_GOLEM_ENTITY,
				DRAKE_ENTITY, COPPER_DRAKE_ENTITY, DARK_DRAKE_ENTITY, ICE_DRAKE_ENTITY, RED_DRAKE_ENTITY, YETI_ENTITY, DARK_YETI_ENTITY, PEPE_ENTITY, DARK_PEPE_ENTITY
		);
	}
	
    public static <T extends Entity> EntityType<?> buildEntityType(EntityType.IFactory<T> factoryIn, EntityClassification classification, float width, float height, String name) {
		return EntityType.Builder.create(factoryIn, classification).size(width, height).build(TheBigBang.MODID + name).setRegistryName(TheBigBang.MODID, name);
	}

    @SubscribeEvent
    public static void registerSpawns (FMLCommonSetupEvent event) {
		registerEntityWorldSpawn(STUMP_ENTITY, 2, 4, Biomes.FOREST);
		registerEntityWorldSpawn(OCTOPUS_ENTITY, 2, 8, Biomes.GRAVELLY_MOUNTAINS, Biomes.STONE_SHORE);
		registerEntityWorldSpawn(EVIL_EYE_ENTITY, 1, 2, Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_BADLANDS_PLATEAU, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.WOODED_BADLANDS_PLATEAU);
		registerEntityWorldSpawn(CURSE_EYE_ENTITY, 1, 2, Biomes.SWAMP, Biomes.SWAMP_HILLS);
		registerEntityWorldSpawn(COLD_EYE_ENTITY, 1, 2, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.SNOWY_TUNDRA);
		registerEntityWorldSpawn(ORANGE_MUSHROOM_ENTITY, 2, 6, Biomes.FOREST);
		registerEntityWorldSpawn(BLUE_MUSHROOM_ENTITY, 2, 6, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS);
		registerEntityWorldSpawn(ZOMBIE_MUSHROOM_ENTITY, 2, 6, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS);
		registerEntityWorldSpawn(JRYETI_ENTITY, 2, 4, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.SNOWY_TUNDRA);
		registerEntityWorldSpawn(DARK_JRYETI_ENTITY, 2, 4, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.SNOWY_TUNDRA);
		registerEntityWorldSpawn(GREEN_SNAIL_ENTITY, 4, 10, Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS);
		registerEntityWorldSpawn(BLUE_SNAIL_ENTITY, 2, 8, Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS);
		registerEntityWorldSpawn(RED_SNAIL_ENTITY, 2, 8, Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS);
		registerEntityWorldSpawn(RIBBON_PIG_ENTITY, 2, 8, Biomes.BEACH);
		registerEntityWorldSpawn(PEPE_ENTITY, 4, 10, Biomes.ICE_SPIKES, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_MOUNTAINS, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.SNOWY_TUNDRA);
    }
    
	public static void registerEntityWorldSpawn(EntityType<?> entity, int minGroupCount, int maxGroupCount, Biome... biomes) {
		for(Biome biome : biomes) {
			if(biome != null) {
				biome.getSpawns(entity.getClassification()).add(new SpawnListEntry(entity, 10, minGroupCount, maxGroupCount));
			}
		}
	}	
	
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(StumpEntity.class, new StumpRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(DarkStumpEntity.class, new DarkStumpRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(AxeStumpEntity.class, new AxeStumpRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(DarkAxeStumpEntity.class, new DarkAxeStumpRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(BubblingEntity.class, new BubblingRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(GreenBubblingEntity.class, new GreenBubblingRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(OctopusEntity.class, new OctopusRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EvilEyeEntity.class, new EvilEyeRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(CurseEyeEntity.class, new CurseEyeRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(ColdEyeEntity.class, new ColdEyeRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(OrangeMushroomEntity.class, new OrangeMushroomRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(BlueMushroomEntity.class, new BlueMushroomRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(ZombieMushroomEntity.class, new ZombieMushroomRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(GreenSnailEntity.class, new GreenSnailRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(BlueSnailEntity.class, new BlueSnailRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(RedSnailEntity.class, new RedSnailRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(JrYetiEntity.class, new JrYetiRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(DarkJrYetiEntity.class, new DarkJrYetiRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(RibbonPigEntity.class, new RibbonPigRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(StoneGolemEntity.class, new StoneGolemRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(DarkStoneGolemEntity.class, new DarkStoneGolemRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(MixedGolemEntity.class, new MixedGolemRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(IceGolemEntity.class, new IceGolemRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(FireGolemEntity.class, new FireGolemRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(DrakeEntity.class, new DrakeRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(CopperDrakeEntity.class, new CopperDrakeRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(DarkDrakeEntity.class, new DarkDrakeRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(IceDrakeEntity.class, new IceDrakeRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(RedDrakeEntity.class, new RedDrakeRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(YetiEntity.class, new YetiRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(DarkYetiEntity.class, new DarkYetiRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(PepeEntity.class, new PepeRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(DarkPepeEntity.class, new DarkPepeRenderer.RenderFactory());
		
		RenderingRegistry.registerEntityRenderingHandler(SubiEntity.class, new ThrowingStarRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(TobiEntity.class, new ThrowingStarRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(SteelyEntity.class, new ThrowingStarRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(IlbiEntity.class, new ThrowingStarRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(GenesisBeamEntity.class, new GenesisBeamRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(BombArrowEntity.class, new BombArrowRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(SnipingArrowEntity.class, new SnipingArrowRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(HurricaneArrowEntity.class, new HurricaneArrowRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(DragonCrusherStabEntity.class, new DragonCrusherStabRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(StealEntity.class, new StealRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(ManaRockEntity.class, new ManaRockRenderer.RenderFactory());
	}
	
	@SubscribeEvent
	public static void registerEntityItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll (
				registerEntitySpawnEgg(STUMP_ENTITY, 0x745A36, 0x4C3D26, "stump_spawn_egg"),
				registerEntitySpawnEgg(DARK_STUMP_ENTITY, 0x3F311D, 0x292011, "dark_stump_spawn_egg"),
				registerEntitySpawnEgg(AXE_STUMP_ENTITY, 0x886633, 0x3e2e17, "axe_stump_spawn_egg"),
				registerEntitySpawnEgg(DARK_AXE_STUMP_ENTITY, 0x3F311D, 0x292011, "dark_axe_stump_spawn_egg"),
				registerEntitySpawnEgg(BUBBLING_ENTITY, 0x5EB7CD, 0x004499, "bubbling_spawn_egg"),
				registerEntitySpawnEgg(GREEN_BUBBLING_ENTITY, 0x7BCE6A, 0x51A03E, "green_bubbling_spawn_egg"),
				registerEntitySpawnEgg(OCTOPUS_ENTITY, 0x6356B5, 0xBD4E86, "octopus_spawn_egg"),
				registerEntitySpawnEgg(EVIL_EYE_ENTITY, 0xFFDD00, 0xBB7711, "evil_eye_spawn_egg"),
				registerEntitySpawnEgg(CURSE_EYE_ENTITY, 0x66AA66, 0x99EE22, "curse_eye_spawn_egg"),
				registerEntitySpawnEgg(COLD_EYE_ENTITY, 0xDDDDDD, 0xAAAAAA, "cold_eye_spawn_egg"),
				registerEntitySpawnEgg(ORANGE_MUSHROOM_ENTITY, 0xFF8800, 0xFFEEDD, "orange_mushroom_spawn_egg"),
				registerEntitySpawnEgg(BLUE_MUSHROOM_ENTITY, 0x55BBEE, 0xFFEEDD, "blue_mushroom_spawn_egg"),
				registerEntitySpawnEgg(ZOMBIE_MUSHROOM_ENTITY, 0xBB9988, 0xEEEEFF, "zombie_mushroom_spawn_egg"),
				registerEntitySpawnEgg(JRYETI_ENTITY, 0xFFFFFF, 0x888879, "jryeti_spawn_egg"),
				registerEntitySpawnEgg(DARK_JRYETI_ENTITY, 0x998877, 0x332211, "dark_jryeti_spawn_egg"),
				registerEntitySpawnEgg(GREEN_SNAIL_ENTITY, 0x228888, 0xFFFFFF, "green_snail_spawn_egg"),
				registerEntitySpawnEgg(BLUE_SNAIL_ENTITY, 0x0055AA, 0xFFFFDD, "blue_snail_spawn_egg"),
				registerEntitySpawnEgg(RED_SNAIL_ENTITY, 0xDD0044, 0xFFFFDD, "red_snail_spawn_egg"),
				registerEntitySpawnEgg(RIBBON_PIG_ENTITY, 0xFCEFDF, 0xEB544D, "ribbon_pig_spawn_egg"),
				registerEntitySpawnEgg(STONE_GOLEM_ENTITY, 0xC4C4B5, 0x497224, "stone_golem_spawn_egg"),
				registerEntitySpawnEgg(DARK_STONE_GOLEM_ENTITY, 0x5A5A5A, 0x497224, "dark_stone_golem_spawn_egg"),
				registerEntitySpawnEgg(MIXED_GOLEM_ENTITY, 0xC4C4B5, 0x5A5A5A, "mixed_golem_spawn_egg"),
				registerEntitySpawnEgg(ICE_GOLEM_ENTITY, 0x9ACAE9, 0xF2F2F2, "ice_golem_spawn_egg"),
				registerEntitySpawnEgg(FIRE_GOLEM_ENTITY, 0x952400, 0xEAD901, "fire_golem_spawn_egg"),
				registerEntitySpawnEgg(DRAKE_ENTITY, 0x4C6A6B, 0xCDCDBE, "drake_spawn_egg"),
				registerEntitySpawnEgg(COPPER_DRAKE_ENTITY, 0x6A6A48, 0xC9B6A5, "copper_drake_spawn_egg"),
				registerEntitySpawnEgg(DARK_DRAKE_ENTITY, 0x576B6A, 0xEAEAD9, "dark_drake_spawn_egg"),
				registerEntitySpawnEgg(ICE_DRAKE_ENTITY, 0x7F9FBE, 0xDCDCEC, "ice_drake_spawn_egg"),
				registerEntitySpawnEgg(RED_DRAKE_ENTITY, 0x7A5B5B, 0xEAEADA, "red_drake_spawn_egg"),
				registerEntitySpawnEgg(YETI_ENTITY, 0xFEFEFC, 0xD7A583, "yeti_spawn_egg"),
				registerEntitySpawnEgg(DARK_YETI_ENTITY, 0x968574, 0xD7A583, "dark_yeti_spawn_egg"),
				registerEntitySpawnEgg(PEPE_ENTITY, 0x112233, 0xFFFFFF, "pepe_spawn_egg"),
				registerEntitySpawnEgg(DARK_PEPE_ENTITY, 0x4477DD, 0xFFFFFF, "dark_pepe_spawn_egg"),
				registerEntitySpawnEgg(MANA_ROCK, 0xFFFFFF, 0xFFFFFF, "mana_rock")
		);
	}
	
	public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name) {
		SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(ItemGroup.MISC));
		item.setRegistryName(TheBigBang.MODID, name);
		return item;
	}
}
