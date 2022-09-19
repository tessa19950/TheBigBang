package com.homebrewCult.TheBigBang.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.EffectType;

public class MagicianEffect extends ArmorBonusEffect {
    public MagicianEffect() {
        super(EffectType.BENEFICIAL, 3966780);
    }

    public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {
        super.applyAttributesModifiersToEntity(entity, attributes, amplifier);
    }

    public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {
        super.removeAttributesModifiersFromEntity(entity, attributes, amplifier);
    }
}
