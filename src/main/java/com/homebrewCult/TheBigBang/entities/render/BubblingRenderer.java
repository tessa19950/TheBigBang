package com.homebrewCult.TheBigBang.entities.render;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.AbstractBubblingEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractBubblingModel;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class BubblingRenderer extends MobRenderer<AbstractBubblingEntity, AbstractBubblingModel<AbstractBubblingEntity>> {

	public BubblingRenderer(EntityRendererManager manager) {
		super(manager, new AbstractBubblingModel<>(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(AbstractBubblingEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/bubbling_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<AbstractBubblingEntity> {
		public EntityRenderer<? super AbstractBubblingEntity> createRenderFor(EntityRendererManager manager) {
			return new BubblingRenderer(manager);
		}
	}

	@Override
	protected void preRenderCallback(AbstractBubblingEntity bubbling, float partialTickTime) {
		applySquish(bubbling, partialTickTime);
	}

	protected static void applySquish(AbstractBubblingEntity bubbling, float partialTickTime) {
		float f = 0.999F;
		GlStateManager.scalef(f, f, f);
		float squish;
		if(!bubbling.isSitting()) squish = MathHelper.lerp(partialTickTime, bubbling.prevSquishFactor, bubbling.squishFactor) / 1.5F;
		else squish = -0.2F;
		float size = 1.0F / (squish + 1.0F);
		GlStateManager.scalef(size, 1.0F / size, size);
		GlStateManager.translatef(0, -0.02F, 0);
	}
}
