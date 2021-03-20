package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.DarkPepeEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractPepeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DarkPepeRenderer extends MobRenderer<DarkPepeEntity, AbstractPepeModel<DarkPepeEntity>> {
    public DarkPepeRenderer(EntityRendererManager manager) {
        super(manager, new AbstractPepeModel<>(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(DarkPepeEntity entity) {
        return new ResourceLocation(TheBigBang.MODID, "textures/entity/dark_guard_pepe_entity.png");
    }

    public static class RenderFactory implements IRenderFactory<DarkPepeEntity> {
        public EntityRenderer<? super DarkPepeEntity> createRenderFor(EntityRendererManager manager) {
            return new DarkPepeRenderer(manager);
        }
    }
}
