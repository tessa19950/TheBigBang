package com.homebrewCult.TheBigBang.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ModFoods {
	public static final Food COOKED_OCTOPUS_LEG = new Food.Builder().hunger(5).saturation(0.6F).meat().build();
	public static final Food COOKED_MUSHROOM_CAP = new Food.Builder().hunger(5).saturation(0.6F).build();
	public static final Food COOKED_EYE_TAIL = new Food.Builder().hunger(8).saturation(0.8F).meat().build();
	
	public static final Food UNAGI = new Food.Builder().hunger(8).saturation(1.2F).effect(new EffectInstance(Effects.REGENERATION, 25, 1), 1.0F).setAlwaysEdible().build();
	public static final Food DANGO = new Food.Builder().hunger(12).saturation(0.8F).effect(new EffectInstance(Effects.REGENERATION, 25, 1), 1.0F).setAlwaysEdible().build();
	public static final Food TRI_COLOURED_DANGO = new Food.Builder().hunger(4).saturation(0.6F).effect(new EffectInstance(Effects.REGENERATION, 50, 1), 1.0F).setAlwaysEdible().build();
	
	public static final Food EVIL_EYE_RAMEN = new Food.Builder().hunger(16).saturation(1.2F).effect(new EffectInstance(Effects.REGENERATION, 25, 1), 1.0F).setAlwaysEdible().build();
	public static final Food CURSE_EYE_RAMEN = new Food.Builder().hunger(16).saturation(1.2F).effect(new EffectInstance(Effects.REGENERATION, 25, 1), 1.0F).setAlwaysEdible().build();
	public static final Food COLD_EYE_RAMEN = new Food.Builder().hunger(16).saturation(1.2F).effect(new EffectInstance(Effects.REGENERATION, 25, 1), 1.0F).setAlwaysEdible().build();
	
	public static final Food SUNRISE_DEW = new Food.Builder().effect(new EffectInstance(Effects.SPEED, 1800, 1), 1.0F).setAlwaysEdible().build();
	public static final Food SUNSET_DEW = new Food.Builder().effect(new EffectInstance(Effects.JUMP_BOOST, 1800, 1), 1.0F).setAlwaysEdible().build();
	public static final Food PURE_WATER = new Food.Builder().hunger(5).saturation(0.6F).effect(new EffectInstance(Effects.SPEED, 3600), 1.0F).setAlwaysEdible().build();
	public static final Food CIDER = new Food.Builder().hunger(5).saturation(0.6F).effect(new EffectInstance(Effects.JUMP_BOOST, 3600), 1.0F).setAlwaysEdible().build();
	public static final Food DRAKE_BLOOD = new Food.Builder().effect(new EffectInstance(Effects.STRENGTH, 1800, 1), 1.0F).setAlwaysEdible().build();
	public static final Food SAP_OF_ANCIENT_TREE = new Food.Builder().effect(new EffectInstance(Effects.HASTE, 1800, 1), 1.0F).setAlwaysEdible().build();
	public static final Food ICE_CREAM_POP = new Food.Builder().hunger(10).saturation(0.8F).effect(new EffectInstance(Effects.STRENGTH, 3600, 1), 1.0F).setAlwaysEdible().build();
	public static final Food VERY_SPECIAL_SUNDAE = new Food.Builder().hunger(10).saturation(0.8F).effect(new EffectInstance(Effects.HASTE, 3600, 1), 1.0F).setAlwaysEdible().build();
}
