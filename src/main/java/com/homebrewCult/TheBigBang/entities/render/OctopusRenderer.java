package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.OctopusEntity;
import com.homebrewCult.TheBigBang.entities.model.OctopusModel;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class OctopusRenderer extends MobRenderer<OctopusEntity, OctopusModel<OctopusEntity>> {
	
	public OctopusRenderer(EntityRendererManager manager) {
		super(manager, new OctopusModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(OctopusEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/octopus_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<OctopusEntity> {
		public EntityRenderer<? super OctopusEntity> createRenderFor(EntityRendererManager manager) {
			return new OctopusRenderer(manager);
		}
	}
	
}
