package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.JrYetiEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractJrYetiModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class JrYetiRenderer extends MobRenderer<JrYetiEntity, AbstractJrYetiModel<JrYetiEntity>> {
	
	public JrYetiRenderer(EntityRendererManager manager) {
		super(manager, new AbstractJrYetiModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(JrYetiEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/jryeti_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<JrYetiEntity> {
		public EntityRenderer<? super JrYetiEntity> createRenderFor(EntityRendererManager manager) {
			return new JrYetiRenderer(manager);
		}
	}
	
}
