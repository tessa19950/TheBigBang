package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.RedDrakeEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractDrakeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RedDrakeRenderer extends MobRenderer<RedDrakeEntity, AbstractDrakeModel<RedDrakeEntity>> {
	
	public RedDrakeRenderer(EntityRendererManager manager) 
	{
		super(manager, new AbstractDrakeModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(RedDrakeEntity entity) 
	{
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/red_drake_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<RedDrakeEntity>
	{
		public EntityRenderer<? super RedDrakeEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new RedDrakeRenderer(manager);
		}
	}
	
}
