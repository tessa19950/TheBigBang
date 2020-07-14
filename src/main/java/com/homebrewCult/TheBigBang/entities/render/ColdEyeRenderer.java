package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.ColdEyeEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractEyeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ColdEyeRenderer extends MobRenderer<ColdEyeEntity, AbstractEyeModel<ColdEyeEntity>> {
	
	public ColdEyeRenderer(EntityRendererManager manager) 
	{
		super(manager, new AbstractEyeModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(ColdEyeEntity entity) 
	{
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/cold_eye_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<ColdEyeEntity>
	{
		public EntityRenderer<? super ColdEyeEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ColdEyeRenderer(manager);
		}
	}
	
}
