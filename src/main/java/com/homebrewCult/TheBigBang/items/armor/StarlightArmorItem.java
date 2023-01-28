package com.homebrewCult.TheBigBang.items.armor;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModEffects;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.items.model.StarlightArmorModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Set;

public class StarlightArmorItem extends BigBangArmorItem {
	
	public StarlightArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, int defaultColorIn, Properties builder) {
		super(materialIn, slot, defaultColorIn, builder);
	}

	@Override
	protected void applyArmorSetEffect(PlayerEntity player) {
		player.addPotionEffect(new EffectInstance(ModEffects.MAGICIAN_EFFECT.get(),
				1728000, 0, false, false));
	}

	@Override
	protected void removeArmorSetEffect(PlayerEntity player) {
		player.removePotionEffect(ModEffects.MAGICIAN_EFFECT.get());
	}

	@Override
	protected Set<Item> getArmorSet() {
		return new ImmutableSet.Builder<Item>().add(
				ModItems.STARLIGHT_HELMET.getItem(),
				ModItems.STARLIGHT_CHESTPLATE.getItem(),
				ModItems.STARLIGHT_LEGGINGS.getItem(),
				ModItems.STARLIGHT_BOOTS.getItem()
		).build();
	}

	@Nullable
	@Override
	@OnlyIn(Dist.CLIENT)
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
		StarlightArmorModel model = StarlightArmorModel.INSTANCE;

		model.bipedHead.showModel = armorSlot == EquipmentSlotType.HEAD;
		model.bipedHeadwear.showModel = armorSlot == EquipmentSlotType.HEAD;
		model.armorTorso.showModel = (armorSlot == EquipmentSlotType.CHEST);
		model.bipedRightArm.showModel = (armorSlot == EquipmentSlotType.CHEST);
		model.bipedLeftArm.showModel = (armorSlot == EquipmentSlotType.CHEST);
		model.armorWaist.showModel = (armorSlot == EquipmentSlotType.LEGS);
		model.armorRightLeg.showModel = (armorSlot == EquipmentSlotType.LEGS);
		model.armorLeftLeg.showModel = (armorSlot == EquipmentSlotType.LEGS);
		model.armorRightBoot.showModel = (armorSlot == EquipmentSlotType.FEET);
		model.armorLeftBoot.showModel = (armorSlot == EquipmentSlotType.FEET);
		
        model.isChild = _default.isChild;
        model.isSneak = _default.isSneak;
        model.isSitting = _default.isSitting;
        model.rightArmPose = _default.rightArmPose;
        model.leftArmPose = _default.leftArmPose;
        return (A) model;
	}
}
