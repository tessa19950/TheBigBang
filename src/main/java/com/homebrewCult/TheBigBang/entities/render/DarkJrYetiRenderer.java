package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.DarkJrYetiEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractJrYetiModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DarkJrYetiRenderer extends MobRenderer<DarkJrYetiEntity, AbstractJrYetiModel<DarkJrYetiEntity>> {
	
	public DarkJrYetiRenderer(EntityRendererManager manager) 
	{
		super(manager, new AbstractJrYetiModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(DarkJrYetiEntity entity) 
	{
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/dark_jryeti_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<DarkJrYetiEntity>
	{
		public EntityRenderer<? super DarkJrYetiEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new DarkJrYetiRenderer(manager);
		}
	}
	
}
