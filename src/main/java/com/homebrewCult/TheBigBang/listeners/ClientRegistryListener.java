package com.homebrewCult.TheBigBang.listeners;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.blocks.DivineAltarTile;
import com.homebrewCult.TheBigBang.blocks.render.DivineAltarTileRenderer;
import com.homebrewCult.TheBigBang.entities.*;
import com.homebrewCult.TheBigBang.entities.mob.*;
import com.homebrewCult.TheBigBang.entities.render.*;
import com.homebrewCult.TheBigBang.layers.BigBangPlayerLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid= TheBigBang.MODID, value= Dist.CLIENT, bus=Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistryListener {

    @SubscribeEvent
    public void onClientSetup(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(DivineAltarTile.class, new DivineAltarTileRenderer<>());

        for(PlayerRenderer playerRender : Minecraft.getInstance().getRenderManager().getSkinMap().values())
            playerRender.addLayer(new BigBangPlayerLayer<>(playerRender));

        RenderingRegistry.registerEntityRenderingHandler(StumpEntity.class, new StumpRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(DarkStumpEntity.class, new DarkStumpRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(AxeStumpEntity.class, new AxeStumpRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(DarkAxeStumpEntity.class, new DarkAxeStumpRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(BubblingEntity.class, new BubblingRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(GreenBubblingEntity.class, new GreenBubblingRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(OctopusEntity.class, new OctopusRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EvilEyeEntity.class, new EvilEyeRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(CurseEyeEntity.class, new CurseEyeRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ColdEyeEntity.class, new ColdEyeRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(OrangeMushroomEntity.class, new OrangeMushroomRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(BlueMushroomEntity.class, new BlueMushroomRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ZombieMushroomEntity.class, new ZombieMushroomRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(GreenSnailEntity.class, new GreenSnailRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(BlueSnailEntity.class, new BlueSnailRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(RedSnailEntity.class, new RedSnailRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(JrYetiEntity.class, new JrYetiRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(DarkJrYetiEntity.class, new DarkJrYetiRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(RibbonPigEntity.class, new RibbonPigRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(StoneGolemEntity.class, new StoneGolemRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(DarkStoneGolemEntity.class, new DarkStoneGolemRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(MixedGolemEntity.class, new MixedGolemRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(IceGolemEntity.class, new IceGolemRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(FireGolemEntity.class, new FireGolemRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(DrakeEntity.class, new DrakeRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(CopperDrakeEntity.class, new CopperDrakeRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(DarkDrakeEntity.class, new DarkDrakeRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(IceDrakeEntity.class, new IceDrakeRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(RedDrakeEntity.class, new RedDrakeRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(YetiEntity.class, new YetiRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(DarkYetiEntity.class, new DarkYetiRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(PepeEntity.class, new PepeRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(DarkPepeEntity.class, new DarkPepeRenderer.RenderFactory());

        RenderingRegistry.registerEntityRenderingHandler(SnailShellEntity.class, m -> new SpriteRenderer<>(m, Minecraft.getInstance().getItemRenderer()));
        RenderingRegistry.registerEntityRenderingHandler(SubiEntity.class, new ThrowingStarRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(TobiEntity.class, new ThrowingStarRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(SteelyEntity.class, new ThrowingStarRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(IlbiEntity.class, new ThrowingStarRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(GenesisBeamEntity.class, new GenesisBeamRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(BombArrowEntity.class, new BombArrowRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(SnipingArrowEntity.class, new SnipingArrowRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(HurricaneArrowEntity.class, new HurricaneArrowRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(DragonCrusherStabEntity.class, new DragonCrusherStabRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(StealEntity.class, new StealRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(ManaRockEntity.class, new ManaRockRenderer.RenderFactory());
    }

}
