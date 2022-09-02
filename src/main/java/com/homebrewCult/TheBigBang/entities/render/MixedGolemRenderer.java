package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.MixedGolemEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractGolemModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class MixedGolemRenderer extends MobRenderer<MixedGolemEntity, AbstractGolemModel<MixedGolemEntity>> {
	
	public MixedGolemRenderer(EntityRendererManager manager) {
		super(manager, new AbstractGolemModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(MixedGolemEntity entity) {
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/mixed_golem_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<MixedGolemEntity> {
		public EntityRenderer<? super MixedGolemEntity> createRenderFor(EntityRendererManager manager) {
			return new MixedGolemRenderer(manager);
		}
	}
	
}
