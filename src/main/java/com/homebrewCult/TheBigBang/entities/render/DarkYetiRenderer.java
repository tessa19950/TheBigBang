package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.DarkYetiEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractYetiModel;

import com.homebrewCult.TheBigBang.layers.YetiSaddleLayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DarkYetiRenderer extends MobRenderer<DarkYetiEntity, AbstractYetiModel<DarkYetiEntity>> {
	
	public DarkYetiRenderer(EntityRendererManager manager) {
		super(manager, new AbstractYetiModel<>(), 0.5f);
		this.layerRenderers.add(new YetiSaddleLayer(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(DarkYetiEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/dark_yeti_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<DarkYetiEntity> {
		public EntityRenderer<? super DarkYetiEntity> createRenderFor(EntityRendererManager manager) {
			return new DarkYetiRenderer(manager);
		}
	}
	
}
