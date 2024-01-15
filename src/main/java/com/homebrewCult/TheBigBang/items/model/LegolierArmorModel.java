package com.homebrewCult.TheBigBang.items.model;
import net.minecraft.client.renderer.model.ModelRenderer;

public class LegolierArmorModel extends BigBangArmorModel {	
	public final ModelRenderer hatFeatherPivot;

	public static final LegolierArmorModel INSTANCE = new LegolierArmorModel();

	public LegolierArmorModel() {	
		super();
		//Paste the Blockbench exported file from here
		textureWidth = 128;
		textureHeight = 64;

		hatFeatherPivot = new ModelRenderer(this);
		hatFeatherPivot.setRotationPoint(4.5F, -2.0F, 0.5F);
		armorHead.addChild(hatFeatherPivot);
		setRotationAngle(hatFeatherPivot, 0.0F, 0.0F, 0.1745F);
		hatFeatherPivot.setTextureOffset(70, 31).addBox(0.0F, -5.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, false);

		//To here
	}
}