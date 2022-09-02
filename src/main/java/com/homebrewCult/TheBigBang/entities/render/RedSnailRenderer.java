package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.RedSnailEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractSnailModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RedSnailRenderer extends MobRenderer<RedSnailEntity, AbstractSnailModel<RedSnailEntity>> {
	
	public RedSnailRenderer(EntityRendererManager manager) {
		super(manager, new AbstractSnailModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(RedSnailEntity entity) {
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/red_snail_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<RedSnailEntity> {
		public EntityRenderer<? super RedSnailEntity> createRenderFor(EntityRendererManager manager) {
			return new RedSnailRenderer(manager);
		}
	}
	
}
