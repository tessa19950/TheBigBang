package com.homebrewCult.TheBigBang.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface IProxy {

	void Init();
	
	World getClientWorld();
	
	PlayerEntity getClientPlayer();
	
}
