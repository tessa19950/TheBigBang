package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.BlueMushroomEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractMushroomModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BlueMushroomRenderer extends MobRenderer<BlueMushroomEntity, AbstractMushroomModel<BlueMushroomEntity>> {
	
	public BlueMushroomRenderer(EntityRendererManager manager) {
		super(manager, new AbstractMushroomModel<>(), 1f);
	}

	@Override
	protected ResourceLocation getEntityTexture(BlueMushroomEntity entity) {
		String type = (entity.isAngry && entity.hasChild && entity.isChildHurt) ? "mushmom" : "mushroom";
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/blue_" + type + "_entity.png");
	}

	public static class RenderFactory implements IRenderFactory<BlueMushroomEntity> {
		public EntityRenderer<? super BlueMushroomEntity> createRenderFor(EntityRendererManager manager) {
			return new BlueMushroomRenderer(manager);
		}
	}
}
