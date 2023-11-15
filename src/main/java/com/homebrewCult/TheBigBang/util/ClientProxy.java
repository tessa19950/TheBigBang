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
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.items.BigBangArmorItemColorHandler;
import com.homebrewCult.TheBigBang.items.ItemColorHandler;
import com.homebrewCult.TheBigBang.layers.BigBangPlayerLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.Entity;
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
		ClientRegistry.bindTileEntitySpecialRenderer(DivineAltarTile.class, new DivineAltarTileRenderer<>());

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
		registerRenderer(StumpEntity.class, new StumpRenderer.RenderFactory());
		registerRenderer(DarkStumpEntity.class, new DarkStumpRenderer.RenderFactory());
		registerRenderer(AxeStumpEntity.class, new AxeStumpRenderer.RenderFactory());
		registerRenderer(DarkAxeStumpEntity.class, new DarkAxeStumpRenderer.RenderFactory());
		registerRenderer(BubblingEntity.class, new BubblingRenderer.RenderFactory());
		registerRenderer(GreenBubblingEntity.class, new GreenBubblingRenderer.RenderFactory());
		registerRenderer(OctopusEntity.class, new OctopusRenderer.RenderFactory());
		registerRenderer(EvilEyeEntity.class, new EvilEyeRenderer.RenderFactory());
		registerRenderer(CurseEyeEntity.class, new CurseEyeRenderer.RenderFactory());
		registerRenderer(ColdEyeEntity.class, new ColdEyeRenderer.RenderFactory());
		registerRenderer(OrangeMushroomEntity.class, new OrangeMushroomRenderer.RenderFactory());
		registerRenderer(BlueMushroomEntity.class, new BlueMushroomRenderer.RenderFactory());
		registerRenderer(ZombieMushroomEntity.class, new ZombieMushroomRenderer.RenderFactory());
		registerRenderer(GreenSnailEntity.class, new GreenSnailRenderer.RenderFactory());
		registerRenderer(BlueSnailEntity.class, new BlueSnailRenderer.RenderFactory());
		registerRenderer(RedSnailEntity.class, new RedSnailRenderer.RenderFactory());
		registerRenderer(JrYetiEntity.class, new JrYetiRenderer.RenderFactory());
		registerRenderer(DarkJrYetiEntity.class, new DarkJrYetiRenderer.RenderFactory());
		registerRenderer(RibbonPigEntity.class, new RibbonPigRenderer.RenderFactory());
		registerRenderer(StoneGolemEntity.class, new StoneGolemRenderer.RenderFactory());
		registerRenderer(DarkStoneGolemEntity.class, new DarkStoneGolemRenderer.RenderFactory());
		registerRenderer(MixedGolemEntity.class, new MixedGolemRenderer.RenderFactory());
		registerRenderer(IceGolemEntity.class, new IceGolemRenderer.RenderFactory());
		registerRenderer(FireGolemEntity.class, new FireGolemRenderer.RenderFactory());
		registerRenderer(DrakeEntity.class, new DrakeRenderer.RenderFactory());
		registerRenderer(CopperDrakeEntity.class, new CopperDrakeRenderer.RenderFactory());
		registerRenderer(DarkDrakeEntity.class, new DarkDrakeRenderer.RenderFactory());
		registerRenderer(IceDrakeEntity.class, new IceDrakeRenderer.RenderFactory());
		registerRenderer(RedDrakeEntity.class, new RedDrakeRenderer.RenderFactory());
		registerRenderer(YetiEntity.class, new YetiRenderer.RenderFactory());
		registerRenderer(DarkYetiEntity.class, new DarkYetiRenderer.RenderFactory());
		registerRenderer(PepeEntity.class, new PepeRenderer.RenderFactory());
		registerRenderer(DarkPepeEntity.class, new DarkPepeRenderer.RenderFactory());

		registerRenderer(SnailShellEntity.class, m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
		registerRenderer(SubiEntity.class, new ThrowingStarRenderer.RenderFactory());
		registerRenderer(TobiEntity.class, new ThrowingStarRenderer.RenderFactory());
		registerRenderer(SteelyEntity.class, new ThrowingStarRenderer.RenderFactory());
		registerRenderer(IlbiEntity.class, new ThrowingStarRenderer.RenderFactory());
		registerRenderer(GenesisBeamEntity.class, new GenesisBeamRenderer.RenderFactory());
		registerRenderer(BombArrowEntity.class, new BombArrowRenderer.RenderFactory());
		registerRenderer(SnipingArrowEntity.class, new SnipingArrowRenderer.RenderFactory());
		registerRenderer(HurricaneArrowEntity.class, new HurricaneArrowRenderer.RenderFactory());
		registerRenderer(DragonCrusherStabEntity.class, new DragonCrusherStabRenderer.RenderFactory());
		registerRenderer(StealEntity.class, new StealRenderer.RenderFactory());
		registerRenderer(ManaRockEntity.class, new ManaRockRenderer.RenderFactory());
	}

	public static <T extends Entity> void registerRenderer(Class<T> entityClass, IRenderFactory<? super T> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

}
