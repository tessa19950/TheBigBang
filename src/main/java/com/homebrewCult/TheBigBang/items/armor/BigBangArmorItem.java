package com.homebrewCult.TheBigBang.items.armor;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;

public abstract class BigBangArmorItem extends DyeableArmorItem implements IDyeableArmorItem {

	private final String name;
	protected int defaultColor;
	private Set<Item> armorSet;
	private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[] {
			EquipmentSlotType.HEAD, EquipmentSlotType.CHEST,
			EquipmentSlotType.LEGS, EquipmentSlotType.FEET
	};
	
	public BigBangArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, int defaultColorIn, Properties builder) {
		super(materialIn, slot, builder);
		this.name = materialIn.toString().toLowerCase();
		this.defaultColor = defaultColorIn;
	}

	public void onArmorEquip(PlayerEntity player) {
		if(isWearingFullSet(player))
			applyArmorSetEffect(player);
	}

	public void onArmorUnequip(PlayerEntity player) {
		removeArmorSetEffect(player);
	}

	protected abstract void applyArmorSetEffect(PlayerEntity player);

	protected abstract void removeArmorSetEffect(PlayerEntity player);

	public boolean isWearingFullSet(PlayerEntity player) {
		if(armorSet == null)
			armorSet = getArmorSet();
		return Arrays.stream(ARMOR_SLOTS).allMatch(slot -> armorSet.stream().anyMatch(player.getItemStackFromSlot(slot).getItem()::equals));
	}

	protected abstract Set<Item> getArmorSet();

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String layer){
		if(layer == null) {
			return TheBigBang.MOD_ID + ":textures/models/armor/" + name + "_overlay.png";
		} else {
			return TheBigBang.MOD_ID + ":textures/models/armor/" + name + ".png";
		}
	}

	public int getDefaultColor() {
		return this.defaultColor;
	}
	
	@Override
	public boolean hasColor(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("display");
		return compoundnbt != null && compoundnbt.contains("color", 99);
   	}
	
	@Override
	public int getColor(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("display");
		return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : defaultColor;
	}

	@Override
	public void removeColor(ItemStack stack) {
		CompoundNBT compoundnbt = stack.getChildTag("display");
		if (compoundnbt != null && compoundnbt.contains("color")) {
			compoundnbt.remove("color");
		}
	}
	
	@Override
	public void setColor(ItemStack stack, int color) {
		stack.getOrCreateChildTag("display").putInt("color", color);
	}
}
