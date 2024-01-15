package com.homebrewCult.TheBigBang.init;

import java.util.function.Supplier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ModArmorMaterial implements IArmorMaterial {
	
	APPLE("apple", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
			0.0F, () -> Ingredient.fromItems(Items.APPLE)),

	// ********** TIER 1 ARMOR SETS *********
	HWARANG("hwarang", 15, new int[]{2, 5, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
			0.0F, () -> Ingredient.fromItems(ModItems.BLESSED_ADAMANTIUM_INGOT)),
	
	LEGOLIER("legolier", 15, new int[]{2, 5, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
			0.0F, () -> Ingredient.fromItems(ModItems.BLESSED_GOLD_INGOT)),
	
	STARLIGHT("starlight", 15, new int[]{2, 5, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
			0.0F, () -> Ingredient.fromItems(ModItems.BLESSED_MITHRIL_INGOT)),

	NIGHTSHIFT("nightshift", 15, new int[]{2, 5, 6, 2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
			0.0F, () -> Ingredient.fromItems(ModItems.BLESSED_ORIHALCON_INGOT)),

	// ********** TIER 2 ARMOR SETS **********
	JANGOON("jangoon", 33, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
			0.0F, () -> Ingredient.fromItems(ModItems.BLESSED_ADAMANTIUM_INGOT)),

	PIETTE("piette", 33, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
			0.0F, () -> Ingredient.fromItems(ModItems.BLESSED_GOLD_INGOT)),

	GOLDWIND("goldwind", 33, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
			0.0F, () -> Ingredient.fromItems(ModItems.BLESSED_MITHRIL_INGOT)),

	PILFER("pilfer", 33, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
			0.0F, () -> Ingredient.fromItems(ModItems.BLESSED_ORIHALCON_INGOT));
	
	private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final Supplier<Ingredient> repairMaterial;
	
	private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float p_i48533_8_, Supplier<Ingredient> repairMaterialSupplier) {
		this.name = nameIn;
		this.maxDamageFactor = maxDamageFactorIn;
		this.damageReductionAmountArray = damageReductionAmountsIn;
		this.enchantability = enchantabilityIn;
		this.soundEvent = equipSoundIn;
		this.toughness = p_i48533_8_;
		this.repairMaterial = repairMaterialSupplier;
	}

	public int getDurability(EquipmentSlotType slotIn) {
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public SoundEvent getSoundEvent() {
		return this.soundEvent;
	}

	public Ingredient getRepairMaterial() {
		return this.repairMaterial.get();
	}

	@OnlyIn(Dist.CLIENT)
	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}
}
