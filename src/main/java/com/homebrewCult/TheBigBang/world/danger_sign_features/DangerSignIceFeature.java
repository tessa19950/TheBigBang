package com.homebrewCult.TheBigBang.world.danger_sign_features;

import java.util.function.Function;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class DangerSignIceFeature extends AbstractDangerSignFeature {
	
	public DangerSignIceFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}
	
	@Override
	public String getTemplateName() {
		return "ice_sign";
	}
	
	@Override
	public Vec3i getTemplateOffset(int index) {
		switch(index) {
			case 0: case 1: return new Vec3i(-3,-2,-2);
			case 2: case 3: return new Vec3i(-2,-2,-3);
			default: return new Vec3i(0,0,0);
		}
	}
	
	@Override
	public Direction getTemplateDirection(int index) {
		switch(index) {
			case 1: return Direction.SOUTH;
			case 2: return Direction.EAST;
			case 3: return Direction.WEST;
			default: return Direction.NORTH;
		}
	}
}
