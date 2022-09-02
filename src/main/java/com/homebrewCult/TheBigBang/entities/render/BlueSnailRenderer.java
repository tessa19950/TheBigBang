package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.BlueSnailEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractSnailModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BlueSnailRenderer extends MobRenderer<BlueSnailEntity, AbstractSnailModel<BlueSnailEntity>> {
	
	public BlueSnailRenderer(EntityRendererManager manager) {
		super(manager, new AbstractSnailModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(BlueSnailEntity entity) {
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/blue_snail_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<BlueSnailEntity> {
		public EntityRenderer<? super BlueSnailEntity> createRenderFor(EntityRendererManager manager) {
			return new BlueSnailRenderer(manager);
		}
	}
	
}
