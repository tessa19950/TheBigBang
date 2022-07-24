package com.homebrewCult.TheBigBang.items.armor;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.items.model.LegolierArmorModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class LegolierArmorItem extends BigBangArmorItem {

	private LegolierArmorModel model;
	
	public LegolierArmorItem(LegolierArmorModel modelIn, IArmorMaterial materialIn, EquipmentSlotType slot, int defaultColorIn, Properties builder) {
		super(materialIn, slot, defaultColorIn, builder);
		this.model = modelIn;
	}

	@Override
	protected void onApplyArmorSetEffect(PlayerEntity player) {

	}

	@Override
	protected Set<Item> getArmorSet() {
		return new ImmutableSet.Builder<Item>().add(
				ModItems.LEGOLIER_HELMET.getItem(),
				ModItems.LEGOLIER_CHESTPLATE.getItem(),
				ModItems.LEGOLIER_LEGGINGS.getItem(),
				ModItems.LEGOLIER_BOOTS.getItem()
		).build();
	}

	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
		this.model.bipedHead.showModel = armorSlot == EquipmentSlotType.HEAD;
		this.model.bipedHeadwear.showModel = armorSlot == EquipmentSlotType.HEAD;
		
		this.model.armorTorso.showModel = (armorSlot == EquipmentSlotType.CHEST);
		this.model.bipedRightArm.showModel = (armorSlot == EquipmentSlotType.CHEST);
		this.model.bipedLeftArm.showModel = (armorSlot == EquipmentSlotType.CHEST);
		
		this.model.armorWaist.showModel = (armorSlot == EquipmentSlotType.LEGS);
		this.model.armorRightLeg.showModel = (armorSlot == EquipmentSlotType.LEGS);
		this.model.armorLeftLeg.showModel = (armorSlot == EquipmentSlotType.LEGS);
		
		this.model.armorRightBoot.showModel = (armorSlot == EquipmentSlotType.FEET);
		this.model.armorLeftBoot.showModel = (armorSlot == EquipmentSlotType.FEET);
		
        this.model.isChild = _default.isChild;
        this.model.isSneak = _default.isSneak;
        this.model.isSitting = _default.isSitting;
        this.model.rightArmPose = _default.rightArmPose;
        this.model.leftArmPose = _default.leftArmPose;     
        return (A) model;
	}
}
