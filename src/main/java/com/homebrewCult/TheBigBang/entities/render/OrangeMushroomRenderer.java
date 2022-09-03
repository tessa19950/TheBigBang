package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.BlueMushroomEntity;
import com.homebrewCult.TheBigBang.entities.mob.OrangeMushroomEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractMushroomModel;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class OrangeMushroomRenderer extends MobRenderer<OrangeMushroomEntity, AbstractMushroomModel<OrangeMushroomEntity>> {
	
	public OrangeMushroomRenderer(EntityRendererManager manager) 
	{
		super(manager, new AbstractMushroomModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(OrangeMushroomEntity entity) {
		String type = (entity.hasChild() && entity.isChildHurt()) ? "mushmom" : "mushroom";
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/orange_" + type + "_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<OrangeMushroomEntity> {
		public EntityRenderer<? super OrangeMushroomEntity> createRenderFor(EntityRendererManager manager) {
			return new OrangeMushroomRenderer(manager);
		}
	}
}
