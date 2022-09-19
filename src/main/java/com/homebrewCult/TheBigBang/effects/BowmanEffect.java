package com.homebrewCult.TheBigBang.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.EffectType;

public class BowmanEffect extends ArmorBonusEffect {
    public BowmanEffect() {
        super(EffectType.BENEFICIAL, 16768060);
    }

    public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {
        super.applyAttributesModifiersToEntity(entity, attributes, amplifier);
    }

    public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {
        super.removeAttributesModifiersFromEntity(entity, attributes, amplifier);
    }
}
