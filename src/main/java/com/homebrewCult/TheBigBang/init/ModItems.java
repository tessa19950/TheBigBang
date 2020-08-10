package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.items.BigBangArmorItemColorHandler;
import com.homebrewCult.TheBigBang.items.BigBangDiskItem;
import com.homebrewCult.TheBigBang.items.LiquidFoodItem;
import com.homebrewCult.TheBigBang.items.QuestItem;
import com.homebrewCult.TheBigBang.items.RydenItem;
import com.homebrewCult.TheBigBang.items.MithrilWandItem;
import com.homebrewCult.TheBigBang.items.armor.AppleArmorItem;
import com.homebrewCult.TheBigBang.items.armor.GoldwindArmorItem;
import com.homebrewCult.TheBigBang.items.armor.HwarangArmorItem;
import com.homebrewCult.TheBigBang.items.armor.JangoonArmorItem;
import com.homebrewCult.TheBigBang.items.armor.LegolierArmorItem;
import com.homebrewCult.TheBigBang.items.armor.NightshiftArmorItem;
import com.homebrewCult.TheBigBang.items.armor.PietteArmorItem;
import com.homebrewCult.TheBigBang.items.armor.PilferArmorItem;
import com.homebrewCult.TheBigBang.items.armor.StarlightArmorItem;
import com.homebrewCult.TheBigBang.items.DangerSignItem;
import com.homebrewCult.TheBigBang.items.GolemStoneItemColorHandler;
import com.homebrewCult.TheBigBang.items.model.AppleArmorModel;
import com.homebrewCult.TheBigBang.items.model.GoldwindArmorModel;
import com.homebrewCult.TheBigBang.items.model.HwarangArmorModel;
import com.homebrewCult.TheBigBang.items.model.JangoonArmorModel;
import com.homebrewCult.TheBigBang.items.model.LegolierArmorModel;
import com.homebrewCult.TheBigBang.items.model.NightshiftArmorModel;
import com.homebrewCult.TheBigBang.items.model.PietteArmorModel;
import com.homebrewCult.TheBigBang.items.model.PilferArmorModel;
import com.homebrewCult.TheBigBang.items.model.StarlightArmorModel;
import com.homebrewCult.TheBigBang.init.ModItems;

import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TheBigBang.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TheBigBang.MODID)
public class ModItems {	
	public static final Item ADAMANTIUM_ORE = new BlockItem(ModBlocks.ADAMANTIUM_ORE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.ADAMANTIUM_ORE.getRegistryName());	
	public static final Item ADAMANTIUM_PLATE = registerMiscItem("adamantium_plate");
	public static final Item ADAMANTIUM_NUGGET = registerMiscItem("adamantium_nugget"); 	
	public static final Item ADAMANTIUM = registerMiscItem("adamantium");
	public static final Item MITHRIL_ORE = new BlockItem(ModBlocks.MITHRIL_ORE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.MITHRIL_ORE.getRegistryName());
	public static final Item MITHRIL = 	registerMiscItem("mithril");
	public static final Item MITHRIL_PLATE = registerMiscItem("mithril_plate");
	public static final Item MITHRIL_NUGGET = registerMiscItem("mithril_nugget");
	public static final Item ORIHALCON_ORE = new BlockItem(ModBlocks.ORIHALCON_ORE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.ORIHALCON_ORE.getRegistryName());
	public static final Item ORIHALCON = registerMiscItem("orihalcon");
	public static final Item ORIHALCON_PLATE = registerMiscItem("orihalcon_plate");;
	public static final Item ORIHALCON_NUGGET = registerMiscItem("orihalcon_nugget");
	public static final Item DANGER_SIGN = null;
	public static final Item GOLEM_STONE = new BlockItem(ModBlocks.GOLEM_STONE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.GOLEM_STONE.getRegistryName());
	public static final Item DARK_GOLEM_STONE = new BlockItem(ModBlocks.DARK_GOLEM_STONE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.DARK_GOLEM_STONE.getRegistryName());
	public static final Item GRASSY_GOLEM_STONE = new BlockItem(ModBlocks.GRASSY_GOLEM_STONE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.GRASSY_GOLEM_STONE.getRegistryName());
	public static final Item GRASSY_DARK_GOLEM_STONE = new BlockItem(ModBlocks.GRASSY_DARK_GOLEM_STONE, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.GRASSY_DARK_GOLEM_STONE.getRegistryName());
	
	public static final QuestItem LETTER = (QuestItem) new QuestItem(new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)).setRegistryName(TheBigBang.MODID, "letter");
	public static final Item OCTOPUS_LEG = 	registerMiscItem("octopus_leg");
	public static final Item EVIL_EYE_TAIL = registerMiscItem("evil_eye_tail");
	public static final Item CURSE_EYE_TAIL = registerMiscItem("curse_eye_tail");
	public static final Item COLD_EYE_TAIL = registerMiscItem("cold_eye_tail");
	public static final Item ORANGE_MUSHROOM_CAP = registerMiscItem("orange_mushroom_cap");
	public static final Item BLUE_MUSHROOM_CAP = registerMiscItem("blue_mushroom_cap");
	public static final Item UNDEAD_CHARM = registerMiscItem("undead_charm");
	public static final Item RED_SNAIL_SHELL = registerMiscItem("red_snail_shell");
	public static final Item BLUE_SNAIL_SHELL = registerMiscItem("blue_snail_shell");
	public static final Item GREEN_SNAIL_SHELL = registerMiscItem("green_snail_shell");
	public static final Item JRYETI_SKIN = registerMiscItem("jryeti_skin");
	public static final Item DARK_JRYETI_SKIN = registerMiscItem("dark_jryeti_skin");
	public static final Item YETI_HORN = registerMiscItem("yeti_horn");
	public static final Item DARK_YETI_HORN = registerMiscItem("dark_yeti_horn");
	public static final Item STONE_GOLEM_RUBBLE = registerMiscItem("stone_golem_rubble");
	public static final Item DARK_STONE_GOLEM_RUBBLE = registerMiscItem("dark_stone_golem_rubble");
	public static final Item PIGS_RIBBON = registerMiscItem("pigs_ribbon");
	public static final Item PIECE_OF_ICE = registerMiscItem("piece_of_ice");
	public static final Item BLUE_SLIME_BALL = registerMiscItem("blue_slime_ball");
	
	public static final Item COOKED_OCTOPUS_LEG = registerFoodItem("cooked_octopus_leg", ModFoods.COOKED_OCTOPUS_LEG);
	public static final Item COOKED_MUSHROOM_CAP = registerFoodItem("cooked_mushroom_cap", ModFoods.COOKED_MUSHROOM_CAP);
	public static final Item COOKED_EYE_TAIL = registerFoodItem("cooked_eye_tail", ModFoods.COOKED_EYE_TAIL);
	public static final Item UNAGI = registerFoodItem("unagi", ModFoods.UNAGI);
	public static final Item DANGO = registerFoodItem("dango", ModFoods.DANGO);
	public static final Item TRI_COLOURED_DANGO = registerFoodItem("tri_coloured_dango", ModFoods.TRI_COLOURED_DANGO);
	public static final Item EVIL_EYE_RAMEN = registerFoodItem("evil_eye_ramen", ModFoods.EVIL_EYE_RAMEN);
	public static final Item CURSE_EYE_RAMEN = registerFoodItem("curse_eye_ramen", ModFoods.CURSE_EYE_RAMEN);
	public static final Item COLD_EYE_RAMEN = registerFoodItem("cold_eye_ramen", ModFoods.COLD_EYE_RAMEN);
	public static final Item SUNRISE_DEW = registerFoodItem("sunrise_dew", ModFoods.SUNRISE_DEW, true);
	public static final Item SUNSET_DEW = registerFoodItem("sunset_dew", ModFoods.SUNSET_DEW, true);
	public static final Item FRESH_WATER = registerFoodItem("pure_water", ModFoods.PURE_WATER, true);
	public static final Item CIDER = registerFoodItem("cider", ModFoods.CIDER, true);
	public static final Item VERY_SPECIAL_SUNDAE_ITEM = registerFoodItem("very_special_sundae", ModFoods.VERY_SPECIAL_SUNDAE, false);
	public static final Item ICE_CREAM_POP = registerFoodItem("ice_cream_pop", ModFoods.ICE_CREAM_POP, true);
	public static final Item DRAKES_BLOOD = registerFoodItem("drakes_blood", ModFoods.DRAKE_BLOOD, true);
	public static final Item SAP_OF_ANCIENT_TREE = registerFoodItem("sap_of_ancient_tree", ModFoods.SAP_OF_ANCIENT_TREE, true);
	
	public static final Item MUSIC_DISC_AMORIA = null;
	public static final Item MUSIC_DISC_AQUA = null;
	public static final Item MUSIC_DISC_ARIANT = null;
	public static final Item MUSIC_DISC_DILLEKE = null;
	public static final Item MUSIC_DISC_ELLINIA = null;
	public static final Item MUSIC_DISC_HARBOR = null;
	public static final Item MUSIC_DISC_HENESYS = null;
	public static final Item MUSIC_DISC_KERNING = null;
	public static final Item MUSIC_DISC_LUDIBRIUM = null;
	public static final Item MUSIC_DISC_OMEGA = null;
	public static final Item MUSIC_DISC_ORBIS = null;
	public static final Item MUSIC_DISC_PERION = null;
	public static final Item MUSIC_DISC_RAINDROP = null;
	public static final Item MUSIC_DISC_SQUARE = null;
	
	public static final Item STUMP_SPAWN_EGG = null;
	public static final Item OCTOPUS_SPAWN_EGG = null;
	public static final Item EVIL_EYE_SPAWN_EGG = null;
	public static final Item CURSE_EYE_SPAWN_EGG = null;
	public static final Item COLD_EYE_SPAWN_EGG = null;
	public static final Item ORANGE_MUSHROOM_SPAWN_EGG = null;
	public static final Item BLUE_MUSHROOM_SPAWN_EGG = null;
	public static final Item ZOMBIE_MUSHROOM_SPAWN_EGG = null;
	public static final Item JRYETI_SPAWN_EGG = null;
	public static final Item DARK_JRYETI_SPAWN_EGG = null;
	public static final Item GREEN_SNAIL_SPAWN_EGG = null;
	public static final Item BLUE_SNAIL_SPAWN_EGG = null;
	public static final Item RED_SNAIL_SPAWN_EGG = null;
	public static final Item RIBBON_PIG_SPAWN_EGG = null;
	public static final Item STONE_GOLEM_SPAWN_EGG = null;
	public static final Item DARK_STONE_GOLEM_SPAWN_EGG = null;
	public static final Item MIXED_GOLEM_SPAWN_EGG = null;
	public static final Item ICE_GOLEM_SPAWN_EGG = null;
	public static final Item FIRE_GOLEM_SPAWN_EGG = null;
	public static final Item DRAKE_SPAWN_EGG = null;
	public static final Item COPPER_DRAKE_SPAWN_EGG = null;
	public static final Item DARK_DRAKE_SPAWN_EGG = null;
	public static final Item ICE_DRAKE_SPAWN_EGG = null;
	public static final Item RED_DRAKE_SPAWN_EGG = null;
	public static final Item YETI_SPAWN_EGG = null;
	public static final Item DARK_YETI_SPAWN_EGG = null;
	
	public static final Item MITHRIL_WAND = null;
	public static final Item RYDEN = null;
	
	public static final Item APPLE_HELMET = null;
	public static final Item APPLE_CHESTPLATE = null;
	public static final Item APPLE_LEGGINGS = null;
	public static final Item HWARANG_HELMET = null;
	public static final Item HWARANG_CHESTPLATE = null;
	public static final Item HWARANG_LEGGINGS = null;
	public static final Item HWARANG_BOOTS = null;
	public static final Item LEGOLIER_HELMET = null;
	public static final Item LEGOLIER_CHESTPLATE = null;
	public static final Item LEGOLIER_LEGGINGS = null;
	public static final Item LEGOLIER_BOOTS = null;
	public static final Item NIGHTSHIFT_HELMET = null;
	public static final Item NIGHTSHIFT_CHESTPLATE = null;
	public static final Item NIGHTSHIFT_LEGGINGS = null;
	public static final Item NIGHTSHIFT_BOOTS = null;
	public static final Item STARLIGHT_HELMET = null;
	public static final Item STARLIGHT_CHESTPLATE = null;
	public static final Item STARLIGHT_LEGGINGS = null;
	public static final Item STARLIGHT_BOOTS = null;
	public static final Item JANGOON_HELMET = null;
	public static final Item JANGOON_CHESTPLATE = null;
	public static final Item JANGOON_LEGGINGS = null;
	public static final Item JANGOON_BOOTS = null;
	public static final Item PIETTE_HELMET = null;
	public static final Item PIETTE_CHESTPLATE = null;
	public static final Item PIETTE_LEGGINGS = null;
	public static final Item PIETTE_BOOTS = null;
	public static final Item PILFER_HELMET = null;
	public static final Item PILFER_CHESTPLATE = null;
	public static final Item PILFER_LEGGINGS = null;
	public static final Item PILFER_BOOTS = null;
	public static final Item GOLDWIND_HELMET = null;
	public static final Item GOLDWIND_CHESTPLATE = null;
	public static final Item GOLDWIND_LEGGINGS = null;
	public static final Item GOLDWIND_BOOTS = null;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
				ModItems.ADAMANTIUM,
				ModItems.ADAMANTIUM_PLATE,
				ModItems.ADAMANTIUM_NUGGET,
				ModItems.MITHRIL,
				ModItems.MITHRIL_PLATE,
				ModItems.MITHRIL_NUGGET,
				ModItems.ORIHALCON,
				ModItems.ORIHALCON_PLATE,
				ModItems.ORIHALCON_NUGGET,
				
				ModItems.LETTER,
				ModItems.OCTOPUS_LEG,
				ModItems.EVIL_EYE_TAIL,
				ModItems.CURSE_EYE_TAIL,
				ModItems.COLD_EYE_TAIL,
				ModItems.ORANGE_MUSHROOM_CAP,
				ModItems.BLUE_MUSHROOM_CAP,
				ModItems.UNDEAD_CHARM,
				ModItems.PIGS_RIBBON,
				ModItems.RED_SNAIL_SHELL,
				ModItems.BLUE_SNAIL_SHELL,
				ModItems.GREEN_SNAIL_SHELL,
				ModItems.STONE_GOLEM_RUBBLE,
				ModItems.DARK_STONE_GOLEM_RUBBLE,
				ModItems.JRYETI_SKIN,
				ModItems.DARK_JRYETI_SKIN,
				ModItems.YETI_HORN,
				ModItems.DARK_YETI_HORN,
				ModItems.PIGS_RIBBON,
				ModItems.PIECE_OF_ICE,
				ModItems.BLUE_SLIME_BALL,
				
				ModItems.COOKED_OCTOPUS_LEG,
				ModItems.COOKED_MUSHROOM_CAP,
				ModItems.COOKED_EYE_TAIL,
				ModItems.UNAGI,
				ModItems.DANGO,
				ModItems.TRI_COLOURED_DANGO,
				ModItems.EVIL_EYE_RAMEN,
				ModItems.CURSE_EYE_RAMEN,
				ModItems.COLD_EYE_RAMEN,
				ModItems.SUNRISE_DEW,
				ModItems.SUNSET_DEW,
				ModItems.FRESH_WATER,
				ModItems.CIDER,
				ModItems.VERY_SPECIAL_SUNDAE_ITEM,
				ModItems.ICE_CREAM_POP,
				ModItems.DRAKES_BLOOD,
				ModItems.SAP_OF_ANCIENT_TREE,
				
				ModItems.ADAMANTIUM_ORE,
				ModItems.MITHRIL_ORE,
				ModItems.ORIHALCON_ORE,
				ModItems.GOLEM_STONE,
				ModItems.DARK_GOLEM_STONE,
				ModItems.GRASSY_GOLEM_STONE,
				ModItems.GRASSY_DARK_GOLEM_STONE,
				
				new MithrilWandItem(new Item.Properties().maxStackSize(1).maxDamage(64).group(ItemGroup.COMBAT)).setRegistryName(TheBigBang.MODID, "mithril_wand"),
				new RydenItem(new Item.Properties().maxStackSize(1).maxDamage(64).group(ItemGroup.COMBAT)).setRegistryName(TheBigBang.MODID, "ryden"),
				
				new DangerSignItem(ModBlocks.DANGER_SIGN, new Item.Properties().maxStackSize(1).group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(ModBlocks.DANGER_SIGN.getRegistryName()),
				
				new BigBangDiskItem("Maplestory - Amoria", ModSounds.RECORD_AMORIA, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_amoria"),
				new BigBangDiskItem("Maplestory - Aqua", ModSounds.RECORD_AQUA, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_aqua"),
				new BigBangDiskItem("Maplestory - Ariant", ModSounds.RECORD_ARIANT, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_ariant"),
				new BigBangDiskItem("Maplestory - Dilleke", ModSounds.RECORD_DILLEKE, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_dilleke"),
				new BigBangDiskItem("Maplestory - Ellinia", ModSounds.RECORD_ELLINIA, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_ellinia"),
				new BigBangDiskItem("Maplestory - Harbor", ModSounds.RECORD_HARBOR, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_harbor"),
				new BigBangDiskItem("Maplestory - Henesys", ModSounds.RECORD_HENESYS, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_henesys"),
				new BigBangDiskItem("Maplestory - Kerning", ModSounds.RECORD_KERNING, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_kerning"),
				new BigBangDiskItem("Maplestory - Ludibrium", ModSounds.RECORD_LUDIBRIUM, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_ludibrium"),
				new BigBangDiskItem("Maplestory - Omega", ModSounds.RECORD_OMEGA, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_omega"),
				new BigBangDiskItem("Maplestory - Orbis", ModSounds.RECORD_ORBIS, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_orbis"),
				new BigBangDiskItem("Maplestory - Perion", ModSounds.RECORD_PERION, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_perion"),
				new BigBangDiskItem("Maplestory - Raindrop", ModSounds.RECORD_RAINDROP, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_raindrop"),
				new BigBangDiskItem("Maplestory - Square", ModSounds.RECORD_SQUARE, new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)).setRegistryName(TheBigBang.MODID, "music_disc_square"),
				
				new AppleArmorItem(new AppleArmorModel(), ModArmorMaterial.APPLE, EquipmentSlotType.HEAD, 
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "apple_helmet"),
				new AppleArmorItem(new AppleArmorModel(), ModArmorMaterial.APPLE, EquipmentSlotType.CHEST, 
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "apple_chestplate"),
				new AppleArmorItem(new AppleArmorModel(), ModArmorMaterial.APPLE, EquipmentSlotType.LEGS, 
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "apple_leggings"),
				new HwarangArmorItem(new HwarangArmorModel(), ModArmorMaterial.HWARANG, EquipmentSlotType.HEAD, 11546150,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "hwarang_helmet"),
				new HwarangArmorItem(new HwarangArmorModel(), ModArmorMaterial.HWARANG, EquipmentSlotType.CHEST, 11546150,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "hwarang_chestplate"),
				new HwarangArmorItem(new HwarangArmorModel(), ModArmorMaterial.HWARANG, EquipmentSlotType.LEGS, 11546150,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "hwarang_leggings"),
				new HwarangArmorItem(new HwarangArmorModel(), ModArmorMaterial.HWARANG, EquipmentSlotType.FEET, 16383998,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "hwarang_boots"),
				new LegolierArmorItem(new LegolierArmorModel(), ModArmorMaterial.LEGOLIER, EquipmentSlotType.HEAD, 3949738,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "legolier_helmet"),
				new LegolierArmorItem(new LegolierArmorModel(), ModArmorMaterial.LEGOLIER, EquipmentSlotType.CHEST, 3949738,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "legolier_chestplate"),
				new LegolierArmorItem(new LegolierArmorModel(), ModArmorMaterial.LEGOLIER, EquipmentSlotType.LEGS, 3949738,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "legolier_leggings"),
				new LegolierArmorItem(new LegolierArmorModel(), ModArmorMaterial.LEGOLIER, EquipmentSlotType.FEET, 3949738,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "legolier_boots"),
				new NightshiftArmorItem(new NightshiftArmorModel(), ModArmorMaterial.NIGHTSHIFT, EquipmentSlotType.HEAD, 6192150,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "nightshift_helmet"),
				new NightshiftArmorItem(new NightshiftArmorModel(), ModArmorMaterial.NIGHTSHIFT, EquipmentSlotType.CHEST, 6192150,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "nightshift_chestplate"),
				new NightshiftArmorItem(new NightshiftArmorModel(), ModArmorMaterial.NIGHTSHIFT, EquipmentSlotType.LEGS, 4673362,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "nightshift_leggings"),
				new NightshiftArmorItem(new NightshiftArmorModel(), ModArmorMaterial.NIGHTSHIFT, EquipmentSlotType.FEET, 6192150,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "nightshift_boots"),
				new StarlightArmorItem(new StarlightArmorModel(), ModArmorMaterial.STARLIGHT, EquipmentSlotType.HEAD, 4673362,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "starlight_helmet"),
				new StarlightArmorItem(new StarlightArmorModel(), ModArmorMaterial.STARLIGHT, EquipmentSlotType.CHEST, 4673362,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "starlight_chestplate"),
				new StarlightArmorItem(new StarlightArmorModel(), ModArmorMaterial.STARLIGHT, EquipmentSlotType.LEGS, 4673362,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "starlight_leggings"),
				new StarlightArmorItem(new StarlightArmorModel(), ModArmorMaterial.STARLIGHT, EquipmentSlotType.FEET, 4673362,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "starlight_boots"),
				new JangoonArmorItem(new JangoonArmorModel(), ModArmorMaterial.JANGOON, EquipmentSlotType.HEAD, 11546150,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "jangoon_helmet"),
				new JangoonArmorItem(new JangoonArmorModel(), ModArmorMaterial.JANGOON, EquipmentSlotType.CHEST, 11546150,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "jangoon_chestplate"),
				new JangoonArmorItem(new JangoonArmorModel(), ModArmorMaterial.JANGOON, EquipmentSlotType.LEGS, 4673362,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "jangoon_leggings"),
				new JangoonArmorItem(new JangoonArmorModel(), ModArmorMaterial.JANGOON, EquipmentSlotType.FEET, 11546150,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "jangoon_boots"),
				new PietteArmorItem(new PietteArmorModel(), ModArmorMaterial.PIETTE, EquipmentSlotType.HEAD, 16351261,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "piette_helmet"),
				new PietteArmorItem(new PietteArmorModel(), ModArmorMaterial.PIETTE, EquipmentSlotType.CHEST, 16351261,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "piette_chestplate"),
				new PietteArmorItem(new PietteArmorModel(), ModArmorMaterial.PIETTE, EquipmentSlotType.LEGS, 8606770,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "piette_leggings"),
				new PietteArmorItem(new PietteArmorModel(), ModArmorMaterial.PIETTE, EquipmentSlotType.FEET, 16383998,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "piette_boots"),
				new PilferArmorItem(new PilferArmorModel(), ModArmorMaterial.PILFER, EquipmentSlotType.HEAD, 4673362,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "pilfer_helmet"),
				new PilferArmorItem(new PilferArmorModel(), ModArmorMaterial.PILFER, EquipmentSlotType.CHEST, 8606770,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "pilfer_chestplate"),
				new PilferArmorItem(new PilferArmorModel(), ModArmorMaterial.PILFER, EquipmentSlotType.LEGS, 4673362,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "pilfer_leggings"),
				new PilferArmorItem(new PilferArmorModel(), ModArmorMaterial.PILFER, EquipmentSlotType.FEET, 4673362,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "pilfer_boots"),
				new GoldwindArmorItem(new GoldwindArmorModel(), ModArmorMaterial.GOLDWIND, EquipmentSlotType.HEAD, 8991416,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "goldwind_helmet"),
				new GoldwindArmorItem(new GoldwindArmorModel(), ModArmorMaterial.GOLDWIND, EquipmentSlotType.CHEST, 16383998,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "goldwind_chestplate"),
				new GoldwindArmorItem(new GoldwindArmorModel(), ModArmorMaterial.GOLDWIND, EquipmentSlotType.LEGS, 16383998,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "goldwind_leggings"),
				new GoldwindArmorItem(new GoldwindArmorModel(), ModArmorMaterial.GOLDWIND, EquipmentSlotType.FEET, 8991416,
						new Item.Properties().group(ItemGroup.COMBAT).maxStackSize(1)).setRegistryName(TheBigBang.MODID, "goldwind_boots")
		);
	}
	
	public static void registerItemColors(ItemColors colors) {
		colors.register(GolemStoneItemColorHandler.INSTANCE, ModItems.GRASSY_GOLEM_STONE, ModItems.GRASSY_DARK_GOLEM_STONE);
		colors.register(BigBangArmorItemColorHandler.INSTANCE, ModItems.HWARANG_HELMET, ModItems.HWARANG_CHESTPLATE, ModItems.HWARANG_LEGGINGS, ModItems.HWARANG_BOOTS,
		ModItems.LEGOLIER_HELMET, ModItems.LEGOLIER_CHESTPLATE, ModItems.LEGOLIER_LEGGINGS, ModItems.LEGOLIER_BOOTS,
		ModItems.NIGHTSHIFT_HELMET, ModItems.NIGHTSHIFT_CHESTPLATE, ModItems.NIGHTSHIFT_LEGGINGS, ModItems.NIGHTSHIFT_BOOTS,
		ModItems.STARLIGHT_HELMET, ModItems.STARLIGHT_CHESTPLATE, ModItems.STARLIGHT_LEGGINGS, ModItems.STARLIGHT_BOOTS,
		ModItems.JANGOON_HELMET, ModItems.JANGOON_CHESTPLATE, ModItems.JANGOON_LEGGINGS, ModItems.JANGOON_BOOTS,
		ModItems.PIETTE_HELMET, ModItems.PIETTE_CHESTPLATE, ModItems.PIETTE_LEGGINGS, ModItems.PIETTE_BOOTS,
		ModItems.PILFER_HELMET, ModItems.PILFER_CHESTPLATE, ModItems.PILFER_LEGGINGS, ModItems.PILFER_BOOTS,
		ModItems.GOLDWIND_HELMET, ModItems.GOLDWIND_CHESTPLATE, ModItems.GOLDWIND_LEGGINGS, ModItems.GOLDWIND_BOOTS
		);
	}
	
	public static Item registerMiscItem(String name) {
		return new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(TheBigBang.MODID, name);
	}
	
	public static Item registerFoodItem(String name, Food food) {
		return new Item(new Item.Properties().group(ItemGroup.FOOD).food(food)).setRegistryName(TheBigBang.MODID, name);
	}
	
	public static Item registerFoodItem(String name, Food food, boolean isLiquid) {
		if(isLiquid) {
			return new LiquidFoodItem(new Item.Properties().group(ItemGroup.FOOD).food(food)).setRegistryName(TheBigBang.MODID, name);
		} else {
			return new Item(new Item.Properties().group(ItemGroup.FOOD).food(food)).setRegistryName(TheBigBang.MODID, name);
		}
	}
}
