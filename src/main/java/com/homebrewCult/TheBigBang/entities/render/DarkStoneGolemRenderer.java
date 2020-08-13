package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.DarkStoneGolemEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractGolemModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DarkStoneGolemRenderer extends MobRenderer<DarkStoneGolemEntity, AbstractGolemModel<DarkStoneGolemEntity>> {
	
	public DarkStoneGolemRenderer(EntityRendererManager manager) 
	{
		super(manager, new AbstractGolemModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(DarkStoneGolemEntity entity) 
	{
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/dark_stone_golem_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<DarkStoneGolemEntity>
	{
		public EntityRenderer<? super DarkStoneGolemEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new DarkStoneGolemRenderer(manager);
		}
	}
	
}
