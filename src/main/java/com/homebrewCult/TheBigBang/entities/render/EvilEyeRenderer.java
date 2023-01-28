package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.EvilEyeEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractEyeModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EvilEyeRenderer extends MobRenderer<EvilEyeEntity, AbstractEyeModel<EvilEyeEntity>> {
	
	public EvilEyeRenderer(EntityRendererManager manager) {
		super(manager, new AbstractEyeModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EvilEyeEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/evil_eye_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<EvilEyeEntity> {
		public EntityRenderer<? super EvilEyeEntity> createRenderFor(EntityRendererManager manager) {
			return new EvilEyeRenderer(manager);
		}
	}
	
}
