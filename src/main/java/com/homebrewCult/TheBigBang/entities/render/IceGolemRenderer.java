package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.IceGolemEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractGolemModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class IceGolemRenderer extends MobRenderer<IceGolemEntity, AbstractGolemModel<IceGolemEntity>> {
	
	public IceGolemRenderer(EntityRendererManager manager) 
	{
		super(manager, new AbstractGolemModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(IceGolemEntity entity) 
	{
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/ice_golem_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<IceGolemEntity>
	{
		public EntityRenderer<? super IceGolemEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new IceGolemRenderer(manager);
		}
	}
	
}
