package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.world.MapleGardensBiome;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TheBigBang.MOD_ID)
@Mod.EventBusSubscriber(modid = TheBigBang.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {
	
	//public static Biome MAPLE_GARDENS;
	//public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, TheBigBang.MOD_ID);
	//public static final RegistryObject<Biome> MAPLE_GARDENS = BIOMES.register("maple_gardens", MapleGardensBiome::new);
	
	@SubscribeEvent
	public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
		//event.getRegistry().registerAll(
		//		MAPLE_GARDENS = new MapleGardensBiome().setRegistryName(TheBigBang.MOD_ID, "maple_gardens")
		//);
		//BiomeDictionary.addTypes(ModBiomes.MAPLE_GARDENS.get(), Type.PLAINS, Type.FOREST, Type.HILLS, Type.LUSH);
		//BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(ModBiomes.MAPLE_GARDENS.get(), 30));
		//BiomeManager.addSpawnBiome(ModBiomes.MAPLE_GARDENS.get());
	}
}
