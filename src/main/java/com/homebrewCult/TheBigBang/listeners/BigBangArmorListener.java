package com.homebrewCult.TheBigBang.listeners;

import com.homebrewCult.TheBigBang.items.armor.BigBangArmorItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Predicate;

public class BigBangArmorListener {

    private static final Predicate<EquipmentSlotType> ARMOR_SLOT_PREDICATE = t ->
            t.equals(EquipmentSlotType.HEAD) || t.equals(EquipmentSlotType.CHEST) || t.equals(EquipmentSlotType.LEGS) || t.equals(EquipmentSlotType.FEET);

    @SubscribeEvent
    public void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if(!(event.getEntity() instanceof PlayerEntity))
            return;
        if(ARMOR_SLOT_PREDICATE.test(event.getSlot())) {
            Item toItem = event.getTo().getItem();
            Item fromItem = event.getFrom().getItem();
            if(toItem instanceof BigBangArmorItem)
                ((BigBangArmorItem) toItem).onArmorEquip((PlayerEntity) event.getEntity());
            if(fromItem instanceof BigBangArmorItem)
                ((BigBangArmorItem) fromItem).onArmorUnequip((PlayerEntity) event.getEntity());
        }
    }

}
