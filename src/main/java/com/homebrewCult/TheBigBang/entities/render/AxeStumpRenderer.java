package com.homebrewCult.TheBigBang.entities.render;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.AbstractStumpEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractStumpModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class AxeStumpRenderer extends MobRenderer<AbstractStumpEntity, AbstractStumpModel<AbstractStumpEntity>> {
	public AxeStumpRenderer(EntityRendererManager manager) {
		super(manager, new AbstractStumpModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(AbstractStumpEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/axe_stump_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<AbstractStumpEntity> {
		public EntityRenderer<? super AbstractStumpEntity> createRenderFor(EntityRendererManager manager) {
			return new AxeStumpRenderer(manager);
		}
	}
}
