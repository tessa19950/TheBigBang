package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.IceDrakeEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractDrakeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class IceDrakeRenderer extends MobRenderer<IceDrakeEntity, AbstractDrakeModel<IceDrakeEntity>> {
	
	public IceDrakeRenderer(EntityRendererManager manager) {
		super(manager, new AbstractDrakeModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(IceDrakeEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/ice_drake_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<IceDrakeEntity> {
		public EntityRenderer<? super IceDrakeEntity> createRenderFor(EntityRendererManager manager) {
			return new IceDrakeRenderer(manager);
		}
	}
	
}
