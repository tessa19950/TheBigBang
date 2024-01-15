package com.homebrewCult.TheBigBang.entities.render;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.AbstractStumpEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractStumpModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class StumpRenderer extends MobRenderer<AbstractStumpEntity, AbstractStumpModel<AbstractStumpEntity>> {
	public StumpRenderer(EntityRendererManager manager) 
	{
		super(manager, new AbstractStumpModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(AbstractStumpEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/stump_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<AbstractStumpEntity> {
		public EntityRenderer<? super AbstractStumpEntity> createRenderFor(EntityRendererManager manager) {
			return new StumpRenderer(manager);
		}
	}
}
