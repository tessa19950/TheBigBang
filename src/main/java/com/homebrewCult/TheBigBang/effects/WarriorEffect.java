package com.homebrewCult.TheBigBang.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.EffectType;

public class WarriorEffect extends ArmorBonusEffect {
    public WarriorEffect() {
        super(EffectType.BENEFICIAL, 10826240);
        addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "940B0CB0-E427-4DC1-8F94-1392AD66F696",
                3D, AttributeModifier.Operation.ADDITION);
        addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "73FF6177-44fD-4498-8973-56108D26DE37",
                0.5D, AttributeModifier.Operation.ADDITION);
        addAttributesModifier(SharedMonsterAttributes.MAX_HEALTH, "73FF6177-44fD-4498-8973-56108D26DE37",
                2D, AttributeModifier.Operation.ADDITION);
    }

    public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {
        super.applyAttributesModifiersToEntity(entity, attributes, amplifier);
    }

    public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {
        super.removeAttributesModifiersFromEntity(entity, attributes, amplifier);
    }
}
