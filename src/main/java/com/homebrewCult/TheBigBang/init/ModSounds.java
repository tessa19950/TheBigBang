package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = TheBigBang.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TheBigBang.MODID)
public class ModSounds {
	
	public static final SoundEvent STUMP_DAMAGE = null;
	public static final SoundEvent STUMP_DIE = null;
	public static final SoundEvent STUMP_AMBIENT = null;
	public static final SoundEvent OCTOPUS_DAMAGE = null;
	public static final SoundEvent OCTOPUS_DIE = null;
	public static final SoundEvent OCTOPUS_AMBIENT = null;
	public static final SoundEvent EYE_DAMAGE = null;
	public static final SoundEvent EYE_DIE = null;
	public static final SoundEvent EYE_AMBIENT = null;			
	public static final SoundEvent MUSHROOM_DAMAGE = null;		
	public static final SoundEvent MUSHROOM_DIE = null;	
	public static final SoundEvent MUSHROOM_AMBIENT = null;	
	public static final SoundEvent SNAIL_DAMAGE = null;	
	public static final SoundEvent SNAIL_DIE = null;	
	public static final SoundEvent SNAIL_AMBIENT = null;	
	public static final SoundEvent JR_YETI_DAMAGE = null;	
	public static final SoundEvent JR_YETI_DIE = null;;	
	public static final SoundEvent JR_YETI_AMBIENT = null;;	
	public static final SoundEvent GOLEM_DAMAGE = null;							
	public static final SoundEvent GOLEM_DIE = null;
	public static final SoundEvent GOLEM_AMBIENT = null; 
	public static final SoundEvent YETI_DAMAGE = null;
	public static final SoundEvent YETI_DIE = null;
	public static final SoundEvent YETI_AMBIENT = null;
	public static final SoundEvent DRAKE_DAMAGE = null;
	public static final SoundEvent DRAKE_DIE = null;
	public static final SoundEvent DRAKE_AMBIENT = null;
	public static final SoundEvent COPPER_DRAKE_DAMAGE = null;
	public static final SoundEvent COPPER_DRAKE_DIE = null;
	public static final SoundEvent COPPER_DRAKE_AMBIENT = null;
	public static final SoundEvent DARK_DRAKE_DAMAGE = null;
	public static final SoundEvent DARK_DRAKE_DIE = null;
	public static final SoundEvent DARK_DRAKE_AMBIENT = null;
	public static final SoundEvent RED_DRAKE_DAMAGE = null;
	public static final SoundEvent RED_DRAKE_DIE = null;
	public static final SoundEvent RED_DRAKE_AMBIENT = null;
	public static final SoundEvent ICE_DRAKE_DAMAGE = null;
	public static final SoundEvent ICE_DRAKE_DIE = null;
	public static final SoundEvent ICE_DRAKE_AMBIENT = null;
	
	public static final SoundEvent MAGIC_CLAW_USE = null;
	
	public static final SoundEvent RECORD_AMORIA = registerSound("record.amoria");
	public static final SoundEvent RECORD_AQUA = registerSound("record.aqua");
	public static final SoundEvent RECORD_ARIANT = registerSound("record.ariant");
	public static final SoundEvent RECORD_DILLEKE = registerSound("record.dilleke");
	public static final SoundEvent RECORD_ELLINIA = registerSound("record.ellinia");
	public static final SoundEvent RECORD_HARBOR = registerSound("record.harbor");
	public static final SoundEvent RECORD_HENESYS = registerSound("record.henesys");
	public static final SoundEvent RECORD_KERNING = registerSound("record.kerning");
	public static final SoundEvent RECORD_LUDIBRIUM = registerSound("record.ludibrium");
	public static final SoundEvent RECORD_OMEGA = registerSound("record.omega");
	public static final SoundEvent RECORD_ORBIS = registerSound("record.orbis");
	public static final SoundEvent RECORD_PERION = registerSound("record.perion");
	public static final SoundEvent RECORD_RAINDROP = registerSound("record.raindrop");
	public static final SoundEvent RECORD_SQUARE = registerSound("record.square");
	
	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) 
	{
		//Register Sets of Sounds for each Entity
		String[] entityNames = new String[] {"stump", "octopus", "eye", "mushroom", "snail", "jr_yeti", "golem", "yeti", "drake", "copper_drake", "dark_drake", "red_drake", "ice_drake"}; 
		for(String name : entityNames) {
			event.getRegistry().registerAll(
					registerSound(name + "_damage"),
					registerSound(name + "_die"),
					registerSound(name + "_ambient")
			);
		}
		
		//Register Single Sounds for other things
		event.getRegistry().registerAll(
				registerSound("magic_claw_use"),
				RECORD_AMORIA, RECORD_AQUA, RECORD_ARIANT, RECORD_DILLEKE, RECORD_ELLINIA, RECORD_HARBOR, RECORD_HENESYS, 
				RECORD_KERNING, RECORD_LUDIBRIUM, RECORD_OMEGA, RECORD_ORBIS, RECORD_PERION, RECORD_RAINDROP, RECORD_SQUARE
		);
	}
	
	public static SoundEvent registerSound(String name)
	{
		SoundEvent event = new SoundEvent(new ResourceLocation(TheBigBang.MODID, name)).setRegistryName(TheBigBang.MODID, name);
		//ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
