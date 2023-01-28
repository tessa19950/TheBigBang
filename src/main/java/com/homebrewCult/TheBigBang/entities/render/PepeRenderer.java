package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.PepeEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractPepeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class PepeRenderer extends MobRenderer<PepeEntity, AbstractPepeModel<PepeEntity>> {
    public PepeRenderer(EntityRendererManager manager) {
        super(manager, new AbstractPepeModel<>(), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(PepeEntity entity) {
        return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/pepe_entity.png");
    }

    public static class RenderFactory implements IRenderFactory<PepeEntity> {
        public EntityRenderer<? super PepeEntity> createRenderFor(EntityRendererManager manager) {
            return new PepeRenderer(manager);
        }
    }
}
