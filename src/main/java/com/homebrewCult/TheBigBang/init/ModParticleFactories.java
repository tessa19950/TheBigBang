package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.particles.*;

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
	}
}