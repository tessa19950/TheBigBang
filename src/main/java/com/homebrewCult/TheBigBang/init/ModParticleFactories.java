package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.particles.BlueSlimeParticle;
import com.homebrewCult.TheBigBang.particles.HolyCircleParticle;
import com.homebrewCult.TheBigBang.particles.HolyHexagramParticle;
import com.homebrewCult.TheBigBang.particles.MagicClawParticle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid=TheBigBang.MODID, value=Dist.CLIENT, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModParticleFactories {	
	
	@SuppressWarnings("resource")
	@SubscribeEvent
	public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.ITEM_BLUE_SLIME.get(), BlueSlimeParticle.BlueSlimeFactory::new);
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.MAGIC_CLAW_LEFT.get(), sprite -> new MagicClawParticle.MagicClawFactory(sprite));
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.MAGIC_CLAW_RIGHT.get(), sprite -> new MagicClawParticle.MagicClawFactory(sprite));
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.HOLY_HEXAGRAM.get(), sprite -> new HolyHexagramParticle.HolyHexagramFactory(sprite));
		Minecraft.getInstance().particles.registerFactory(ModParticleTypes.HOLY_CIRCLE.get(), sprite -> new HolyCircleParticle.HolyCircleFactory(sprite));
	}
}