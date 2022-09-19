package com.homebrewCult.TheBigBang.listeners;

import com.homebrewCult.TheBigBang.config.BigBangConfig;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.items.armor.BigBangArmorItem;
import com.homebrewCult.TheBigBang.util.IQuestEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;
import java.util.function.Predicate;

public class BigBangListener {

    private static final Random rand = new Random();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onLivingDamage(LivingDamageEvent event) {
        if(!BigBangConfig.USE_DAMAGE_NUMBERS_CONFIG.get())
            return;
        if(event.getSource().getTrueSource() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();

            boolean isCrit = false;
            if(event.getSource().getImmediateSource() instanceof AbstractArrowEntity)
                isCrit = ((AbstractArrowEntity)event.getSource().getImmediateSource()).getIsCritical();
            else if(event.getSource().getImmediateSource() instanceof PlayerEntity)
                isCrit = player.fallDistance > 0.0F;
            BasicParticleType particle = isCrit ? ModParticleTypes.CRITICAL_NUMBER.get() : ModParticleTypes.DAMAGE_NUMBER.get();

            Vec3d headPos = event.getEntity().getEyePosition(0);
            if(event.getAmount() < 10) {
                ((ServerWorld) player.world).spawnParticle(particle,
                        headPos.getX(), headPos.getY() + 1.0D, headPos.getZ(),
                        0, 1.0D + event.getAmount(), 0.0D, 0.0D, 1.0D
                );
            } else {
                Vec3d dir = headPos.subtract(player.getPositionVec()).normalize().rotateYaw(1.57F);
                String amount = String.valueOf((int)event.getAmount());
                double offset = ((double) amount.length() - 1.0D) * 0.2D;
                Vec3d basePos = headPos.add(dir.getX() * offset, 1, dir.getZ() * offset);
                for (int i = 0; i < amount.length(); i++) {
                    int number = Integer.parseInt(amount.substring(i, i + 1));
                    double height = rand.nextDouble() * (i % 2 == 0 ? 0.05D : -0.05D);
                    ((ServerWorld) player.world).spawnParticle(particle,
                            basePos.getX() - dir.getX() * i * 0.4D, basePos.getY() + height, basePos.getZ() - dir.getZ() * i * 0.4D,
                            0, 1.0D + number, 0.0D, 0.0D, 1.0D
                    );
                }
            }
        }
    }

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

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        if(event.getEntityLiving() instanceof IQuestEntity)
            ((IQuestEntity)event.getEntityLiving()).onQuestEntityDeath(event.getEntityLiving(), event.getSource());
    }
}
