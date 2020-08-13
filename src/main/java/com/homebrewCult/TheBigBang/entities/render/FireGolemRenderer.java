package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.FireGolemEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractGolemModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class FireGolemRenderer extends MobRenderer<FireGolemEntity, AbstractGolemModel<FireGolemEntity>> {
	
	public FireGolemRenderer(EntityRendererManager manager) 
	{
		super(manager, new AbstractGolemModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(FireGolemEntity entity) 
	{
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/fire_golem_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<FireGolemEntity>
	{
		public EntityRenderer<? super FireGolemEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new FireGolemRenderer(manager);
		}
	}
	
}
