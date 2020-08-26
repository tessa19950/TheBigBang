package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.particles.BlueSlimeParticle;
import com.homebrewCult.TheBigBang.particles.HolyHexagramParticle;
import com.homebrewCult.TheBigBang.particles.MagicClawParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid=TheBigBang.MODID, value=Dist.CLIENT, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleTypes {	
	
	private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, TheBigBang.MODID);
	public static final RegistryObject<BasicParticleType> ITEM_BLUE_SLIME = PARTICLE_TYPES.register("item_blue_slime", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> MAGIC_CLAW_LEFT = PARTICLE_TYPES.register("magic_claw_left", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> MAGIC_CLAW_RIGHT = PARTICLE_TYPES.register("magic_claw_right", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> HOLY_HEXAGRAM = PARTICLE_TYPES.register("holy_hexagram", () -> new BasicParticleType(true));
	
	public static void particleInit() {
		PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(ITEM_BLUE_SLIME.get(), BlueSlimeParticle.BlueSlimeFactory::new);
		Minecraft.getInstance().particles.registerFactory(MAGIC_CLAW_LEFT.get(), sprite -> new MagicClawParticle.MagicClawFactory(sprite));
		Minecraft.getInstance().particles.registerFactory(MAGIC_CLAW_RIGHT.get(), sprite -> new MagicClawParticle.MagicClawFactory(sprite));
		Minecraft.getInstance().particles.registerFactory(HOLY_HEXAGRAM.get(), sprite -> new HolyHexagramParticle.HolyHexagramFactory(sprite));
	}
}