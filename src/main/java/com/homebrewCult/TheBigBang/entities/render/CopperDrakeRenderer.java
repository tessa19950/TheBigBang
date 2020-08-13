package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.CopperDrakeEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractDrakeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class CopperDrakeRenderer extends MobRenderer<CopperDrakeEntity, AbstractDrakeModel<CopperDrakeEntity>> {
	
	public CopperDrakeRenderer(EntityRendererManager manager) 
	{
		super(manager, new AbstractDrakeModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(CopperDrakeEntity entity) 
	{
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/copper_drake_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<CopperDrakeEntity>
	{
		public EntityRenderer<? super CopperDrakeEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new CopperDrakeRenderer(manager);
		}
	}
	
}
