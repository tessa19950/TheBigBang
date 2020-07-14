package com.homebrewCult.TheBigBang.items.armor;

import javax.annotation.Nullable;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.items.model.AppleArmorModel;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

public class AppleArmorItem extends ArmorItem {

	private String name;
	private AppleArmorModel model;
	
	public AppleArmorItem(AppleArmorModel modelIn, IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		this.name = materialIn.getName();
		this.model = modelIn;
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
		this.model.bipedRightLeg.showModel = (armorSlot == EquipmentSlotType.LEGS) || (armorSlot == EquipmentSlotType.FEET);
		this.model.bipedLeftLeg.showModel = (armorSlot == EquipmentSlotType.LEGS) || (armorSlot == EquipmentSlotType.FEET);
		
        this.model.isChild = _default.isChild;
        this.model.isSneak = _default.isSneak;
        this.model.isSitting = _default.isSitting;
        this.model.rightArmPose = _default.rightArmPose;
        this.model.leftArmPose = _default.leftArmPose;
        return (A) model;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return TheBigBang.MODID + ":textures/models/armor/" + name + ".png";
	}
}
