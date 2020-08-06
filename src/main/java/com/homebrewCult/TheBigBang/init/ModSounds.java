package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TheBigBang.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TheBigBang.MODID)
public class ModSounds {
	
	public static SoundEvent STUMP_DAMAGE, STUMP_DIE, OCTOPUS_DAMAGE, OCTOPUS_DIE, EYE_DAMAGE, EYE_DIE, MUSHROOM_DAMAGE, MUSHROOM_DIE, SNAIL_DAMAGE, SNAIL_DIE, JR_YETI_DAMAGE, JR_YETI_DIE;
	public static SoundEvent DRAKE_DAMAGE, DRAKE_DIE, COPPER_DRAKE_DAMAGE, COPPER_DRAKE_DIE, DARK_DRAKE_DAMAGE, DARK_DRAKE_DIE, RED_DRAKE_DAMAGE, RED_DRAKE_DIE, ICE_DRAKE_DAMAGE, ICE_DRAKE_DIE;
	public static SoundEvent GOLEM_DAMAGE, GOLEM_DIE, YETI_DAMAGE, YETI_DIE;
	
	public static SoundEvent MAGIC_CLAW_USE;
	
	public static SoundEvent RECORD_AMORIA = registerSound("record.amoria");
	public static SoundEvent RECORD_AQUA = registerSound("record.aqua");
	public static SoundEvent RECORD_ARIANT = registerSound("record.ariant");
	public static SoundEvent RECORD_DILLEKE = registerSound("record.dilleke");
	public static SoundEvent RECORD_ELLINIA = registerSound("record.ellinia");
	public static SoundEvent RECORD_HARBOR = registerSound("record.harbor");
	public static SoundEvent RECORD_HENESYS = registerSound("record.henesys");
	public static SoundEvent RECORD_KERNING = registerSound("record.kerning");
	public static SoundEvent RECORD_LUDIBRIUM = registerSound("record.ludibrium");
	public static SoundEvent RECORD_OMEGA = registerSound("record.omega");
	public static SoundEvent RECORD_ORBIS = registerSound("record.orbis");
	public static SoundEvent RECORD_PERION = registerSound("record.perion");
	public static SoundEvent RECORD_RAINDROP = registerSound("record.raindrop");
	public static SoundEvent RECORD_SQUARE = registerSound("record.square");
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) 
	{
		event.getRegistry().registerAll
		(
				STUMP_DAMAGE = registerSound("entity.stump.damage"),
				STUMP_DIE = registerSound("entity.stump.die"),
				OCTOPUS_DAMAGE = registerSound("entity.octopus.damage"),
				OCTOPUS_DIE = registerSound("entity.octopus.die"),
				EYE_DAMAGE = registerSound("entity.eye.damage"),
				EYE_DIE = registerSound("entity.eye.die"),
				MUSHROOM_DAMAGE = registerSound("entity.mushroom.damage"),
				MUSHROOM_DIE = registerSound("entity.mushroom.die"),
				SNAIL_DAMAGE = registerSound("entity.snail.damage"),
				SNAIL_DIE = registerSound("entity.snail.die"),
				JR_YETI_DAMAGE = registerSound("entity.jr_yeti.damage"),
				JR_YETI_DIE = registerSound("entity.jr_yeti.die"),
				DRAKE_DAMAGE = registerSound("entity.drake.damage"),
				DRAKE_DIE = registerSound("entity.drake.die"),
				COPPER_DRAKE_DAMAGE = registerSound("entity.copper_drake.damage"),
				COPPER_DRAKE_DIE = registerSound("entity.copper_drake.die"),
				DARK_DRAKE_DAMAGE = registerSound("entity.dark_drake.damage"),
				DARK_DRAKE_DIE = registerSound("entity.dark_drake.die"),
				RED_DRAKE_DAMAGE = registerSound("entity.red_drake.damage"),
				RED_DRAKE_DIE = registerSound("entity.red_drake.die"),
				ICE_DRAKE_DAMAGE = registerSound("entity.ice_drake.damage"),
				ICE_DRAKE_DIE = registerSound("entity.ice_drake.die"),
				GOLEM_DAMAGE = registerSound("entity.golem.damage"),
				GOLEM_DIE = registerSound("entity.golem.die"),
				YETI_DAMAGE = registerSound("entity.yeti.damage"),
				YETI_DIE = registerSound("entity.yeti.die"),				
				
				MAGIC_CLAW_USE = registerSound("item.magic_claw.use"),
				
				RECORD_AMORIA, RECORD_AQUA, RECORD_ARIANT, RECORD_DILLEKE, RECORD_ELLINIA, RECORD_HARBOR, RECORD_HENESYS, 
				RECORD_KERNING, RECORD_LUDIBRIUM, RECORD_OMEGA, RECORD_ORBIS, RECORD_PERION, RECORD_RAINDROP, RECORD_SQUARE
		);
	}
	
	public static SoundEvent registerSound(String name)
	{
		SoundEvent event = new SoundEvent(new ResourceLocation(TheBigBang.MODID, name)).setRegistryName(TheBigBang.MODID, name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
