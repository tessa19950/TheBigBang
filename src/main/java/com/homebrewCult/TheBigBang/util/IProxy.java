package com.homebrewCult.TheBigBang.util;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {

	void Init();
	
	World getClientWorld();
	
	PlayerEntity getClientPlayer();

	default void registerBlockColors(BlockColors blockColors) {}

	default void registerItemColors(ItemColors itemColors) {}
}
