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

public abstract class BigBangArmorItem extends DyeableArmorItem implements IItemColor, IDyeableArmorItem {

	private final String name;
	protected int defaultColor;
	private Set<Item> armorSet;
	private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[] {
			EquipmentSlotType.HEAD, EquipmentSlotType.CHEST,
			EquipmentSlotType.LEGS, EquipmentSlotType.FEET
	};
	
	public BigBangArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, int defaultColorIn, Properties builder) {
		super(materialIn, slot, builder);
		this.name = materialIn.getName();
		this.defaultColor = defaultColorIn;
	}

	public void onArmorEquip(PlayerEntity player) {
		if(armorSet == null)
			armorSet = getArmorSet();
		if(Arrays.stream(ARMOR_SLOTS).allMatch(slot -> armorSet.stream().anyMatch(player.getItemStackFromSlot(slot).getItem()::equals)))
			onApplyArmorSetEffect(player);
	}

	protected abstract void onApplyArmorSetEffect(PlayerEntity player);

	protected abstract Set<Item> getArmorSet();

	public void onArmorUnequip(PlayerEntity player) {
		TheBigBang.LOGGER.debug("Unequipped Armor");
		TheBigBang.LOGGER.debug("Removing Effect");
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String layer){
		if(layer == null) {
			return TheBigBang.MODID + ":textures/models/armor/" + name + "_overlay.png";
		} else {
			return TheBigBang.MODID + ":textures/models/armor/" + name + ".png";
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
	public int getColor(ItemStack stack, int index) {
		if(index == 0) {
			return 0xFFFFFF;
		} else {
			BigBangArmorItem item = (BigBangArmorItem) stack.getItem(); 
			if(item != null) {
				CompoundNBT compoundnbt = stack.getChildTag("display");
				return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : item.getDefaultColor();
			} else {
				return 0xFFFFFF;
			}
		}
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
