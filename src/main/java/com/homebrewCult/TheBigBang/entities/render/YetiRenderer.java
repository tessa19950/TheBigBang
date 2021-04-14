package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.YetiEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractYetiModel;

import com.homebrewCult.TheBigBang.layers.YetiSaddleLayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class YetiRenderer extends MobRenderer<YetiEntity, AbstractYetiModel<YetiEntity>> {
	
	public YetiRenderer(EntityRendererManager manager) {
		super(manager, new AbstractYetiModel<>(), 0.5f);
		this.layerRenderers.add(new YetiSaddleLayer(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(YetiEntity entity) {
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/yeti_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<YetiEntity> {
		public EntityRenderer<? super YetiEntity> createRenderFor(EntityRendererManager manager) {
			return new YetiRenderer(manager);
		}
	}
	
}
