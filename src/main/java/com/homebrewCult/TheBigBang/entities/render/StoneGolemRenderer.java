package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.StoneGolemEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractGolemModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class StoneGolemRenderer extends MobRenderer<StoneGolemEntity, AbstractGolemModel<StoneGolemEntity>> {
	
	public StoneGolemRenderer(EntityRendererManager manager) {
		super(manager, new AbstractGolemModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(StoneGolemEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/stone_golem_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<StoneGolemEntity> {
		public EntityRenderer<? super StoneGolemEntity> createRenderFor(EntityRendererManager manager) {
			return new StoneGolemRenderer(manager);
		}
	}
	
}
