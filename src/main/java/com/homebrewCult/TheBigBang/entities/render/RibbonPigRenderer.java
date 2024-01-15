package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.RibbonPigEntity;
import com.homebrewCult.TheBigBang.entities.model.RibbonPigModel;
import com.homebrewCult.TheBigBang.layers.RibbonSaddleLayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RibbonPigRenderer extends MobRenderer<RibbonPigEntity, RibbonPigModel<RibbonPigEntity>> {
	
	public RibbonPigRenderer(EntityRendererManager manager) {
		super(manager, new RibbonPigModel<>(), 0.7f);
		this.layerRenderers.add(new RibbonSaddleLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(RibbonPigEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/ribbon_pig_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<RibbonPigEntity> {
		public EntityRenderer<? super RibbonPigEntity> createRenderFor(EntityRendererManager manager) {
			return new RibbonPigRenderer(manager);
		}
	}
}
