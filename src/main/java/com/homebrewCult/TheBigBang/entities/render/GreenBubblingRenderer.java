package com.homebrewCult.TheBigBang.entities.render;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.AbstractBubblingEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractBubblingModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class GreenBubblingRenderer extends MobRenderer<AbstractBubblingEntity, AbstractBubblingModel<AbstractBubblingEntity>> {
	public GreenBubblingRenderer(EntityRendererManager manager) {
		super(manager, new AbstractBubblingModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(AbstractBubblingEntity entity) {
		return new ResourceLocation(TheBigBang.MODID, "textures/entity/green_bubbling_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<AbstractBubblingEntity> {
		public EntityRenderer<? super AbstractBubblingEntity> createRenderFor(EntityRendererManager manager) {
			return new GreenBubblingRenderer(manager);
		}
	}

}
