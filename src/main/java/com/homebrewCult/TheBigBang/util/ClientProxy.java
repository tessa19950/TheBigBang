package com.homebrewCult.TheBigBang.util;

import com.homebrewCult.TheBigBang.gui.DangerSignScreen;
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
		ModItems.registerItemColors(Minecraft.getInstance().getItemColors());
		ModBlocks.registerBlockColors(Minecraft.getInstance().getBlockColors());
	}
	
	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}
	
	@Override
	public PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;
	}
	
}
