package com.homebrewCult.TheBigBang.util;

import com.homebrewCult.TheBigBang.gui.DangerSignScreen;
import com.homebrewCult.TheBigBang.gui.MonsterFurnaceScreen;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {
	
	@Override
	public void Init() {
		ScreenManager.registerFactory(ModBlocks.DANGER_SIGN_CONTAINER, DangerSignScreen::new);
		ScreenManager.registerFactory(ModBlocks.DIVINE_ALTAR_CONTAINER, MonsterFurnaceScreen::new);
		ModItems.registerItemColors(Minecraft.getInstance().getItemColors());
		ModBlocks.registerBlockColors(Minecraft.getInstance().getBlockColors());
	}
	
	@SuppressWarnings("resource")
	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}
	
	@SuppressWarnings("resource")
	@Override
	public PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;
	}
	
}
