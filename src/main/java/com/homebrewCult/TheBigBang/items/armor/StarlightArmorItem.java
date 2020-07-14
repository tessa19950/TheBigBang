package com.homebrewCult.TheBigBang.items.armor;

import javax.annotation.Nullable;
import com.homebrewCult.TheBigBang.items.model.StarlightArmorModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

public class StarlightArmorItem extends BigBangArmorItem {

	private StarlightArmorModel model;
	
	public StarlightArmorItem(StarlightArmorModel modelIn, IArmorMaterial materialIn, EquipmentSlotType slot, int defaultColorIn, Properties builder) {
		super(materialIn, slot, defaultColorIn, builder);
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
