package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.DrakeEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractDrakeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DrakeRenderer extends MobRenderer<DrakeEntity, AbstractDrakeModel<DrakeEntity>> {
	
	public DrakeRenderer(EntityRendererManager manager) {
		super(manager, new AbstractDrakeModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(DrakeEntity entity) {
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/drake_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<DrakeEntity> {
		public EntityRenderer<? super DrakeEntity> createRenderFor(EntityRendererManager manager) {
			return new DrakeRenderer(manager);
		}
	}
	
}
