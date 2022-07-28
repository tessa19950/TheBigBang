package com.homebrewCult.TheBigBang.util;

import com.homebrewCult.TheBigBang.blocks.BlockColorHandler;
import com.homebrewCult.TheBigBang.gui.DangerSignScreen;
import com.homebrewCult.TheBigBang.gui.MonsterFurnaceScreen;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.items.BigBangArmorItemColorHandler;
import com.homebrewCult.TheBigBang.items.ItemColorHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {
	
	@Override
	public void Init() {
		ScreenManager.registerFactory(ModBlocks.DANGER_SIGN_CONTAINER, DangerSignScreen::new);
		ScreenManager.registerFactory(ModBlocks.DIVINE_ALTAR_CONTAINER, MonsterFurnaceScreen::new);

		Minecraft.getInstance().getBlockColors().register(BlockColorHandler.INSTANCE,
				ModBlocks.GRASSY_GOLEM_STONE, ModBlocks.GRASSY_DARK_GOLEM_STONE
		);
		Minecraft.getInstance().getItemColors().register(ItemColorHandler.INSTANCE,
				ModItems.GRASSY_GOLEM_STONE, ModItems.GRASSY_DARK_GOLEM_STONE
		);
		Minecraft.getInstance().getItemColors().register(BigBangArmorItemColorHandler.INSTANCE,
				ModItems.HWARANG_HELMET, ModItems.HWARANG_CHESTPLATE, ModItems.HWARANG_LEGGINGS, ModItems.HWARANG_BOOTS,
				ModItems.LEGOLIER_HELMET, ModItems.LEGOLIER_CHESTPLATE, ModItems.LEGOLIER_LEGGINGS, ModItems.LEGOLIER_BOOTS,
				ModItems.NIGHTSHIFT_HELMET, ModItems.NIGHTSHIFT_CHESTPLATE, ModItems.NIGHTSHIFT_LEGGINGS, ModItems.NIGHTSHIFT_BOOTS,
				ModItems.STARLIGHT_HELMET, ModItems.STARLIGHT_CHESTPLATE, ModItems.STARLIGHT_LEGGINGS, ModItems.STARLIGHT_BOOTS,
				ModItems.JANGOON_HELMET, ModItems.JANGOON_CHESTPLATE, ModItems.JANGOON_LEGGINGS, ModItems.JANGOON_BOOTS,
				ModItems.PIETTE_HELMET, ModItems.PIETTE_CHESTPLATE, ModItems.PIETTE_LEGGINGS, ModItems.PIETTE_BOOTS,
				ModItems.PILFER_HELMET, ModItems.PILFER_CHESTPLATE, ModItems.PILFER_LEGGINGS, ModItems.PILFER_BOOTS,
				ModItems.GOLDWIND_HELMET, ModItems.GOLDWIND_CHESTPLATE, ModItems.GOLDWIND_LEGGINGS, ModItems.GOLDWIND_BOOTS
		);
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
