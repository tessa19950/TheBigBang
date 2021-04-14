package com.homebrewCult.TheBigBang.init;

import com.homebrewCult.TheBigBang.layers.BigBangPlayerLayer;
import com.homebrewCult.TheBigBang.layers.YetiSaddleLayer;
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
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_0_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_1_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_2_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_3_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.HYPER_BODY_4_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.DRAGON_CRUSHER_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.THREATEN_0_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.THREATEN_1_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.THREATEN_2_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.THREATEN_3_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.MONSTER_MAGNET_0_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.MONSTER_MAGNET_1_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.MONSTER_MAGNET_2_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.MONSTER_MAGNET_3_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.MONSTER_MAGNET_4_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.MONSTER_MAGNET_5_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.MONSTER_MAGNET_6_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.GENESIS_ANGEL_0_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.GENESIS_ANGEL_1_TEXTURE_LOCATION);
		event.addSprite(BigBangPlayerLayer.HURRICANE_TEXTURE_LOCATION);
		event.addSprite(YetiSaddleLayer.YETI_SADDLE_TEXTURE);
	}
	
}
