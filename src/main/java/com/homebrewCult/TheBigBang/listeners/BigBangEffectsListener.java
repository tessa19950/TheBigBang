package com.homebrewCult.TheBigBang.listeners;

import com.homebrewCult.TheBigBang.effects.ArmorBonusEffect;
import com.homebrewCult.TheBigBang.entities.ThrowingStarEntity;
import com.homebrewCult.TheBigBang.init.ModEffects;
import com.homebrewCult.TheBigBang.items.armor.BigBangArmorItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Consumer;

public class BigBangEffectsListener {

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        if(!(event.getSource().getTrueSource() instanceof PlayerEntity))
            return;
        if(event.getSource().isMagicDamage()) {
            tryApplyEffectBonus(event.getSource().getTrueSource(), ModEffects.MAGICIAN_EFFECT.get(), effect
                    -> event.setAmount(event.getAmount() + 3.0F * (effect.getAmplifier() + 1)));
        }
    }

    @SubscribeEvent
    public void onProjectileImpact(ProjectileImpactEvent event) {
        if(event.getEntity() instanceof AbstractArrowEntity) {
            AbstractArrowEntity projectile = (AbstractArrowEntity) event.getEntity();
            if(projectile instanceof ThrowingStarEntity)
                tryApplyEffectBonus(projectile.getShooter(), ModEffects.THIEF_EFFECT.get(), effect
                        -> projectile.setDamage(projectile.getDamage() + 2.0F * (effect.getAmplifier() + 1)));
            else
                tryApplyEffectBonus(projectile.getShooter(), ModEffects.BOWMAN_EFFECT.get(), effect
                        -> projectile.setDamage(projectile.getDamage() + 2.0F * (effect.getAmplifier() + 1)));
        }
    }

    @SubscribeEvent
    public void onLivingJump(LivingEvent.LivingJumpEvent event) {
        if(event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            tryApplyEffectBonus(event.getEntity(), ModEffects.THIEF_EFFECT.get(), effect -> {
                Vec3d motion = player.getMotion();
                int i = effect.getAmplifier() + 1;
                player.setMotion(motion.add(motion.getX() * 0.25 * i, 0.08 * i, motion.getZ() * 0.25 * i));
            });
        }
    }

    private void tryApplyEffectBonus(Entity entity, Effect type, Consumer<EffectInstance> runnable) {
        if(entity instanceof PlayerEntity) {
            EffectInstance effect = ((LivingEntity)entity).getActivePotionEffect(type);
            if (effect != null)
                runnable.accept(effect);
        }
    }

    @SubscribeEvent
    public void onPotionExpire(PotionEvent.PotionExpiryEvent event) {

        if(event.getPotionEffect() != null && event.getPotionEffect().getPotion() instanceof ArmorBonusEffect)
            event.setCanceled(true);
    }

    @SubscribeEvent
    public void onPotionRemove(PotionEvent.PotionRemoveEvent event) {
        if(event.getPotionEffect() != null && event.getPotion() instanceof ArmorBonusEffect) {
            Entity entity = event.getEntity();
            if(!(entity instanceof PlayerEntity))
                return;
            Iterable<ItemStack> armor = event.getEntity().getArmorInventoryList();
            for (ItemStack itemStack : armor) {
                if (itemStack.getItem() instanceof BigBangArmorItem)
                    event.setCanceled(((BigBangArmorItem) itemStack.getItem()).isWearingFullSet((PlayerEntity) entity));
                return;
            }

        }
    }
}
