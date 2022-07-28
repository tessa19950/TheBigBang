package com.homebrewCult.TheBigBang.effects;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MagicianEffect extends Effect {
    public MagicianEffect() {
        super(EffectType.BENEFICIAL, 3966780);
    }

    public void applyAttributesModifiersToEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {

        super.applyAttributesModifiersToEntity(entity, attributes, amplifier);
    }

    public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap attributes, int amplifier) {

        super.removeAttributesModifiersFromEntity(entity, attributes, amplifier);
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        if(event.getSource().getTrueSource() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
            EffectInstance effect = player.getActivePotionEffect(ModEffects.MAGICIAN_EFFECT.get());
            if(effect != null)
                event.setAmount(event.getAmount() + 3.0F * (effect.getAmplifier() + 1));
        }
    }
}
