package com.homebrewCult.TheBigBang.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.EffectType;

public class ThiefEffect extends ArmorBonusEffect {
    public ThiefEffect() {
        super(EffectType.BENEFICIAL, 1315860);
        addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "73FF6177-44fD-4498-8973-56108D26DE37",
                0.01D, AttributeModifier.Operation.ADDITION);
    }

    public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {
        super.applyAttributesModifiersToEntity(entity, attributes, amplifier);
    }

    public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {
        super.removeAttributesModifiersFromEntity(entity, attributes, amplifier);
    }
}
