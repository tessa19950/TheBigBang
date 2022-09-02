package com.homebrewCult.TheBigBang.util;

import com.homebrewCult.TheBigBang.effects.BowmanEffect;
import com.homebrewCult.TheBigBang.effects.MagicianEffect;
import com.homebrewCult.TheBigBang.effects.ThiefEffect;
import com.homebrewCult.TheBigBang.effects.WarriorEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ServerProxy implements IProxy {
	
	@Override
	public void Init() {
		final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(new WarriorEffect());
		MinecraftForge.EVENT_BUS.register(new MagicianEffect());
		MinecraftForge.EVENT_BUS.register(new BowmanEffect());
		MinecraftForge.EVENT_BUS.register(new ThiefEffect());
	}
	
	@Override
	public World getClientWorld() {
		throw new IllegalStateException("Only run this on the client!");
	}
	
	@Override
	public PlayerEntity getClientPlayer() {
		throw new IllegalStateException("Only run this on the client!");
	}

}
