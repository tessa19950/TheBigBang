package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.entities.render.BigBangPlayerLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraftforge.client.event.TextureStitchEvent;

public class ModLayers {

	public static void registerLayers() {
		for(PlayerRenderer playerRender : Minecraft.getInstance().getRenderManager().getSkinMap().values()) {
			playerRender.addLayer(new BigBangPlayerLayer<>(playerRender));
		}
	}
	
	public static void onTextureStitch(TextureStitchEvent.Pre event) {
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_FADE_0_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_FADE_1_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_FADE_2_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_FADE_3_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.GENESIS_ANGEL_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.GENESIS_ANGEL_BRIGHT_TEXTURE_LOCATION);
	}
	
}
