package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.particles.*;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid=TheBigBang.MOD_ID, value=Dist.CLIENT, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes {	
	
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, TheBigBang.MOD_ID);
	public static final RegistryObject<BasicParticleType> ITEM_BLUE_SLIME = PARTICLE_TYPES.register("item_blue_slime", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> MAGIC_CLAW_LEFT = PARTICLE_TYPES.register("magic_claw_left", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> MAGIC_CLAW_RIGHT = PARTICLE_TYPES.register("magic_claw_right", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> HOLY_HEXAGRAM = PARTICLE_TYPES.register("holy_hexagram", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> HOLY_CIRCLE = PARTICLE_TYPES.register("holy_circle", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> SYMBOL_BLUE = PARTICLE_TYPES.register("symbol_blue", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> SYMBOL_GOLD = PARTICLE_TYPES.register("symbol_gold", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> GLOWLEAF_BLUE = PARTICLE_TYPES.register("glowleaf_blue", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> GLOWLEAF_GOLD = PARTICLE_TYPES.register("glowleaf_gold", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> FLASH_JUMP_SYMBOLS = PARTICLE_TYPES.register("flash_jump_symbols", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> FLASH_JUMP_RINGS = PARTICLE_TYPES.register("flash_jump_rings", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> FLASH_JUMP_DASHES = PARTICLE_TYPES.register("flash_jump_dashes", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> TELEPORT_LINE = PARTICLE_TYPES.register("teleport_line", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> POISON_CLOUD = PARTICLE_TYPES.register("poison_cloud", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> FIRE_GEAR = PARTICLE_TYPES.register("fire_gear", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> DAMAGE_NUMBER = PARTICLE_TYPES.register("dmg_number", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> CRITICAL_NUMBER = PARTICLE_TYPES.register("crit_number", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> SUMMONING_ROCK = PARTICLE_TYPES.register("summoning_rock", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> MAGIC_ROCK = PARTICLE_TYPES.register("magic_rock", () -> new BasicParticleType(true));

	@SubscribeEvent
	public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.ITEM_BLUE_SLIME.get(), BlueSlimeParticle.BlueSlimeFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.MAGIC_CLAW_LEFT.get(), MagicClawParticle.MagicClawFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.MAGIC_CLAW_RIGHT.get(), MagicClawParticle.MagicClawFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.HOLY_HEXAGRAM.get(), HolyHexagramParticle.HolyHexagramFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.HOLY_CIRCLE.get(), HolyCircleParticle.HolyCircleFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.SYMBOL_BLUE.get(), SymbolParticle.SymbolFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.SYMBOL_GOLD.get(), SymbolParticle.SymbolFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.GLOWLEAF_BLUE.get(), GlowleafParticle.GlowleafFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.GLOWLEAF_GOLD.get(), GlowleafParticle.GlowleafFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.FLASH_JUMP_SYMBOLS.get(), FlashJumpParticle.FlashJumpFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.FLASH_JUMP_RINGS.get(), FlashJumpParticle.FlashJumpFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.FLASH_JUMP_DASHES.get(), FlashJumpParticle.FlashJumpFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.TELEPORT_LINE.get(), TeleportLineParticle.TeleportLineFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.POISON_CLOUD.get(), PoisonCloudParticle.ParticleFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.FIRE_GEAR.get(), FireGearParticle.ParticleFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.DAMAGE_NUMBER.get(), DmgNumberParticle.ParticleFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.CRITICAL_NUMBER.get(), CritNumberParticle.ParticleFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.SUMMONING_ROCK.get(), MissingRockParticle.ParticleFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.MAGIC_ROCK.get(), MissingRockParticle.ParticleFactory::new);
	}

	public static void registerClientParticleFactories() {
		// Not sure if this could be useful for something in the future but eh... yeah
	}
}