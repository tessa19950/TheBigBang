package com.homebrewCult.TheBigBang.effects;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WarriorEffect extends Effect {
    public WarriorEffect() {
        super(EffectType.BENEFICIAL, 10826240);
        addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "940B0CB0-E427-4DC1-8F94-1392AD66F696",
                0.1D, AttributeModifier.Operation.ADDITION);
    }

    public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {

        super.applyAttributesModifiersToEntity(entity, attributes, amplifier);
    }

    public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {

        super.removeAttributesModifiersFromEntity(entity, attributes, amplifier);
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {

    }

}
