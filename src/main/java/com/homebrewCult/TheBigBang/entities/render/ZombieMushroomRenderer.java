package com.homebrewCult.TheBigBang.entities.render;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.entities.mob.ZombieMushroomEntity;
import com.homebrewCult.TheBigBang.entities.model.AbstractMushroomModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ZombieMushroomRenderer extends MobRenderer<ZombieMushroomEntity, AbstractMushroomModel<ZombieMushroomEntity>> {
	
	public ZombieMushroomRenderer(EntityRendererManager manager) {
		super(manager, new AbstractMushroomModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(ZombieMushroomEntity entity) {
		return new ResourceLocation(TheBigBang.MOD_ID, "textures/entity/zombie_mushroom_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<ZombieMushroomEntity> {
		public EntityRenderer<? super ZombieMushroomEntity> createRenderFor(EntityRendererManager manager) {
			return new ZombieMushroomRenderer(manager);
		}
	}
	
}
