package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.GreenSnailEntity;
import com.homebrewCult.TheBigBang.entities.model.SmallSnailModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GreenSnailRenderer extends MobRenderer<GreenSnailEntity, SmallSnailModel<GreenSnailEntity>> {
	
	public GreenSnailRenderer(EntityRendererManager manager) {
		super(manager, new SmallSnailModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(GreenSnailEntity entity) {
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/green_snail_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<GreenSnailEntity> {
		public EntityRenderer<? super GreenSnailEntity> createRenderFor(EntityRendererManager manager) {
			return new GreenSnailRenderer(manager);
		}
	}
	
}
