package com.homebrewCult.TheBigBang.effects;

import com.homebrewCult.TheBigBang.TheBigBang;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ThiefEffect extends Effect {
    public ThiefEffect() {
        super(EffectType.BENEFICIAL, 1315860);
        addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "73FF6177-44fD-4498-8973-56108D26DE37",
                0.1D, AttributeModifier.Operation.ADDITION);
    }

    public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {

        super.applyAttributesModifiersToEntity(entity, attributes, amplifier);
    }

    public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {

        super.removeAttributesModifiersFromEntity(entity, attributes, amplifier);
    }

    @SubscribeEvent
    public void onProjectileImpact(ProjectileImpactEvent event) {

    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {

    }
}
