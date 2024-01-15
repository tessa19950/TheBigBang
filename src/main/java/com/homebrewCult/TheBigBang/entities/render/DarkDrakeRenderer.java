package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.DarkDrakeEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractDrakeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DarkDrakeRenderer extends MobRenderer<DarkDrakeEntity, AbstractDrakeModel<DarkDrakeEntity>> {
	
	public DarkDrakeRenderer(EntityRendererManager manager) {
		super(manager, new AbstractDrakeModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(DarkDrakeEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/dark_drake_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<DarkDrakeEntity> {
		public EntityRenderer<? super DarkDrakeEntity> createRenderFor(EntityRendererManager manager) {
			return new DarkDrakeRenderer(manager);
		}
	}
	
}
