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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AppleArmorItem extends ArmorItem {

	private String name;
	
	public AppleArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		this.name = materialIn.toString().toLowerCase();
	}

	@Nullable
	@Override
	@OnlyIn(Dist.CLIENT)
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
		AppleArmorModel model = AppleArmorModel.INSTANCE;

		model.bipedHead.showModel = armorSlot == EquipmentSlotType.HEAD;
		model.bipedHeadwear.showModel = armorSlot == EquipmentSlotType.HEAD;
		model.armorTorso.showModel = (armorSlot == EquipmentSlotType.CHEST);
		model.bipedRightArm.showModel = (armorSlot == EquipmentSlotType.CHEST);
		model.bipedLeftArm.showModel = (armorSlot == EquipmentSlotType.CHEST);
		model.armorWaist.showModel = (armorSlot == EquipmentSlotType.LEGS);
		model.bipedRightLeg.showModel = (armorSlot == EquipmentSlotType.LEGS) || (armorSlot == EquipmentSlotType.FEET);
		model.bipedLeftLeg.showModel = (armorSlot == EquipmentSlotType.LEGS) || (armorSlot == EquipmentSlotType.FEET);

        model.isChild = _default.isChild;
        model.isSneak = _default.isSneak;
        model.isSitting = _default.isSitting;
        model.rightArmPose = _default.rightArmPose;
        model.leftArmPose = _default.leftArmPose;
		return (A) model;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return TheBigBang.MODID + ":textures/models/armor/" + name + ".png";
	}
}
