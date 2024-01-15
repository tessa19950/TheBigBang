package com.homebrewCult.TheBigBang.util;

import com.homebrewCult.TheBigBang.blocks.BlockColorHandler;
import com.homebrewCult.TheBigBang.blocks.DivineAltarTile;
import com.homebrewCult.TheBigBang.blocks.render.DivineAltarTileRenderer;
import com.homebrewCult.TheBigBang.entities.*;
import com.homebrewCult.TheBigBang.entities.mob.*;
import com.homebrewCult.TheBigBang.entities.render.*;
import com.homebrewCult.TheBigBang.gui.DangerSignScreen;
import com.homebrewCult.TheBigBang.gui.MonsterFurnaceScreen;
import com.homebrewCult.TheBigBang.init.ModBlocks;
import com.homebrewCult.TheBigBang.init.ModEntities;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.items.BigBangArmorItemColorHandler;
import com.homebrewCult.TheBigBang.items.ItemColorHandler;
import com.homebrewCult.TheBigBang.layers.BigBangPlayerLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy {
	
	@Override
	public void Init() {
		ScreenManager.registerFactory(ModBlocks.DANGER_SIGN_CONTAINER, DangerSignScreen::new);
		ScreenManager.registerFactory(ModBlocks.DIVINE_ALTAR_CONTAINER, MonsterFurnaceScreen::new);
		ClientRegistry.bindTileEntityRenderer(ModBlocks.DIVINE_ALTAR_TILE, DivineAltarTileRenderer::new);

		//ModParticleTypes.registerClientParticleFactories();

		registerEntityRenderers();

		for(PlayerRenderer playerRender : Minecraft.getInstance().getRenderManager().getSkinMap().values())
			playerRender.addLayer(new BigBangPlayerLayer<>(playerRender));

		registerBlockItemColors();
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getInstance().world;
	}

	@Override
	public PlayerEntity getClientPlayer() {
		return Minecraft.getInstance().player;
	}

	private void registerBlockItemColors() {
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

	private void registerEntityRenderers() {
		registerRenderer(StumpEntity.class, ModEntities.STUMP_ENTITY, new StumpRenderer.RenderFactory());
		registerRenderer(DarkStumpEntity.class, ModEntities.DARK_STUMP_ENTITY, new DarkStumpRenderer.RenderFactory());
		registerRenderer(AxeStumpEntity.class, ModEntities.AXE_STUMP_ENTITY, new AxeStumpRenderer.RenderFactory());
		registerRenderer(DarkAxeStumpEntity.class, ModEntities.DARK_AXE_STUMP_ENTITY, new DarkAxeStumpRenderer.RenderFactory());
		registerRenderer(BubblingEntity.class, ModEntities.BUBBLING_ENTITY, new BubblingRenderer.RenderFactory());
		registerRenderer(GreenBubblingEntity.class, ModEntities.GREEN_BUBBLING_ENTITY, new GreenBubblingRenderer.RenderFactory());
		registerRenderer(OctopusEntity.class, ModEntities.OCTOPUS_ENTITY, new OctopusRenderer.RenderFactory());
		registerRenderer(EvilEyeEntity.class, ModEntities.EVIL_EYE_ENTITY, new EvilEyeRenderer.RenderFactory());
		registerRenderer(CurseEyeEntity.class, ModEntities.CURSE_EYE_ENTITY, new CurseEyeRenderer.RenderFactory());
		registerRenderer(ColdEyeEntity.class, ModEntities.COLD_EYE_ENTITY, new ColdEyeRenderer.RenderFactory());
		registerRenderer(OrangeMushroomEntity.class, ModEntities.ORANGE_MUSHROOM_ENTITY, new OrangeMushroomRenderer.RenderFactory());
		registerRenderer(BlueMushroomEntity.class, ModEntities.BLUE_MUSHROOM_ENTITY, new BlueMushroomRenderer.RenderFactory());
		registerRenderer(ZombieMushroomEntity.class, ModEntities.ZOMBIE_MUSHROOM_ENTITY, new ZombieMushroomRenderer.RenderFactory());
		registerRenderer(GreenSnailEntity.class, ModEntities.GREEN_SNAIL_ENTITY, new GreenSnailRenderer.RenderFactory());
		registerRenderer(BlueSnailEntity.class, ModEntities.BLUE_SNAIL_ENTITY, new BlueSnailRenderer.RenderFactory());
		registerRenderer(RedSnailEntity.class, ModEntities.RED_SNAIL_ENTITY, new RedSnailRenderer.RenderFactory());
		registerRenderer(JrYetiEntity.class, ModEntities.JRYETI_ENTITY, new JrYetiRenderer.RenderFactory());
		registerRenderer(DarkJrYetiEntity.class, ModEntities.DARK_JRYETI_ENTITY, new DarkJrYetiRenderer.RenderFactory());
		registerRenderer(RibbonPigEntity.class, ModEntities.RIBBON_PIG_ENTITY, new RibbonPigRenderer.RenderFactory());
		registerRenderer(StoneGolemEntity.class, ModEntities.STONE_GOLEM_ENTITY, new StoneGolemRenderer.RenderFactory());
		registerRenderer(DarkStoneGolemEntity.class, ModEntities.DARK_STONE_GOLEM_ENTITY, new DarkStoneGolemRenderer.RenderFactory());
		registerRenderer(MixedGolemEntity.class, ModEntities.MIXED_GOLEM_ENTITY, new MixedGolemRenderer.RenderFactory());
		registerRenderer(IceGolemEntity.class, ModEntities.ICE_GOLEM_ENTITY, new IceGolemRenderer.RenderFactory());
		registerRenderer(FireGolemEntity.class, ModEntities.FIRE_GOLEM_ENTITY, new FireGolemRenderer.RenderFactory());
		registerRenderer(DrakeEntity.class, ModEntities.DRAKE_ENTITY, new DrakeRenderer.RenderFactory());
		registerRenderer(CopperDrakeEntity.class, ModEntities.COPPER_DRAKE_ENTITY, new CopperDrakeRenderer.RenderFactory());
		registerRenderer(DarkDrakeEntity.class, ModEntities.DARK_DRAKE_ENTITY, new DarkDrakeRenderer.RenderFactory());
		registerRenderer(IceDrakeEntity.class, ModEntities.ICE_DRAKE_ENTITY, new IceDrakeRenderer.RenderFactory());
		registerRenderer(RedDrakeEntity.class, ModEntities.RED_DRAKE_ENTITY, new RedDrakeRenderer.RenderFactory());
		registerRenderer(YetiEntity.class, ModEntities.YETI_ENTITY, new YetiRenderer.RenderFactory());
		registerRenderer(DarkYetiEntity.class, ModEntities.DARK_YETI_ENTITY, new DarkYetiRenderer.RenderFactory());
		registerRenderer(PepeEntity.class, ModEntities.PEPE_ENTITY, new PepeRenderer.RenderFactory());
		registerRenderer(DarkPepeEntity.class, ModEntities.DARK_PEPE_ENTITY, new DarkPepeRenderer.RenderFactory());

		registerRenderer(SnailShellEntity.class, ModEntities.SNAIL_SHELL, manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
		registerRenderer(SubiEntity.class, ModEntities.SUBI, new ThrowingStarRenderer.RenderFactory());
		registerRenderer(TobiEntity.class, ModEntities.TOBI, new ThrowingStarRenderer.RenderFactory());
		registerRenderer(SteelyEntity.class, ModEntities.STEELY, new ThrowingStarRenderer.RenderFactory());
		registerRenderer(IlbiEntity.class, ModEntities.ILBI, new ThrowingStarRenderer.RenderFactory());
		registerRenderer(GenesisBeamEntity.class, ModEntities.GENESIS_BEAM, new GenesisBeamRenderer.RenderFactory());
		registerRenderer(BombArrowEntity.class, ModEntities.BOMB_ARROW, new BombArrowRenderer.RenderFactory());
		registerRenderer(SnipingArrowEntity.class, ModEntities.SNIPING_ARROW, new SnipingArrowRenderer.RenderFactory());
		registerRenderer(HurricaneArrowEntity.class, ModEntities.HURRICANE_ARROW, new HurricaneArrowRenderer.RenderFactory());
		registerRenderer(DragonCrusherStabEntity.class, ModEntities.DRAGON_CRUSHER_STAB, new DragonCrusherStabRenderer.RenderFactory());
		registerRenderer(StealEntity.class, ModEntities.STEAL, new StealRenderer.RenderFactory());
		registerRenderer(ManaRockEntity.class, ModEntities.MANA_ROCK, new ManaRockRenderer.RenderFactory());
	}

	public static <T extends Entity> void registerRenderer(Class<T> entityClass, EntityType entityType, IRenderFactory<? super T> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityType, renderFactory);
	}

}
