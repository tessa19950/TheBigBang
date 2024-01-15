package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface IBigBangWeapon {

    Predicate<ItemStack> MAGIC_ROCKS = (stack) -> stack.getItem().equals(ModItems.BLESSED_MAGIC_ROCK);

    Predicate<ItemStack> SUMMONING_ROCKS = (stack) -> stack.getItem().equals(ModItems.BLESSED_SUMMONING_ROCK);

    default ActionResult<ItemStack> trySpellAttack(ItemStack weaponStack, World worldIn, PlayerEntity player, int timeLeft) {
        boolean isCharged = weaponStack.getUseDuration() - timeLeft > getChargeDuration(player);
        if(player != null && isCharged) {
            ItemStack ammoStack = findMagicAmmo(player, this);
            ActionResultType result = player.isCreative() || !ammoStack.isEmpty() ? ActionResultType.SUCCESS : ActionResultType.FAIL;
            if(result.equals(ActionResultType.SUCCESS)) {
                onSpellAttack(weaponStack, worldIn, player);
                weaponStack.damageItem(1, player, (p_220009_1_) -> {
                    p_220009_1_.sendBreakAnimation(player.getActiveHand());
                });
                consumeMagicAmmo(player, ammoStack);
            }
            return new ActionResult<>(result, weaponStack);
        } else {
            return new ActionResult<>(ActionResultType.FAIL, weaponStack);
        }
    }

    default void onSpellCharging(ItemStack weaponStack, World worldIn, PlayerEntity player, int timeLeft) {
        int chargeTime = weaponStack.getUseDuration() - timeLeft;
        int chargeDuration = getChargeDuration(player);
        boolean hasAmmo = hasMagicAmmo(player, this);
        if(chargeTime == chargeDuration) {
            if(!hasAmmo)
                playMissingAmmoEffect(player, worldIn);
            else {
                float pitch = 1.1F + worldIn.rand.nextFloat() * 0.2F;
                worldIn.playSound(player, player.getPosition(), getChargedSound(), SoundCategory.PLAYERS, 0.5F, pitch);
                if(getChargedParticle() != null && chargeDuration > 10)
                    spawnChargedParticle(player, worldIn);
            }
            return;
        }
        if(chargeTime > chargeDuration && !hasAmmo)
            return;
        if (this.getChargingParticle() != null)
            spawnChargingParticle(chargeTime, player, worldIn);
    }

    default void playMissingAmmoEffect(PlayerEntity player, World worldIn) {
        float pitch = 1.1F + worldIn.rand.nextFloat() * 0.2F;
        worldIn.playSound(player, player.getPosition(), SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.PLAYERS, 0.5F, pitch);

        IParticleData particle = getMagicAmmoPredicate().equals(MAGIC_ROCKS) ? ModParticleTypes.MAGIC_ROCK.get() : ModParticleTypes.SUMMONING_ROCK.get();
        Vec3d motion = player.getMotion().mul(1.5D,1.5D,1.5D);
        if(Minecraft.getInstance().gameSettings.thirdPersonView == 0) {
            Vec3d look = player.getLookVec();
            double x = player.getPosX() + look.x * 0.5D;
            double y = player.getPosY() + player.getEyeHeight() + look.y * 0.5D;
            double z = player.getPosZ() + look.z * 0.5D;
            worldIn.addParticle(particle, x, y, z, motion.x + look.x * 0.01D, look.y * 0.01D, motion.z + look.z * 0.01D);
        } else {
            double x = player.getPosX();
            double y = player.getPosY() + player.getEyeHeight() + 0.8;
            double z = player.getPosZ();
            worldIn.addParticle(particle, x, y, z, motion.x, 0.01D, motion.z);
        }
    }

    default void spawnChargedParticle(PlayerEntity player, World worldIn) {
        for(int i = 0; i < 16; ++i) {
            for(int j = 0; j < 8; ++j) {
                double x = Math.cos((float) Math.PI * 2F / 16F * (float) i) * 1.2F;
                double y = Math.sin((float) Math.PI * 2F / 16F * (float) i) * 1.2F;
                Vec3d vec = new Vec3d(x, y, 0);
                vec = vec.rotateYaw((float)Math.PI / 8F * j);
                double x1 = player.getPosX() + vec.x;
                double y1 = player.getPosY() + 1 + vec.y;
                double z1 = player.getPosZ() + vec.z;
                worldIn.addParticle(this.getChargedParticle(), x1, y1, z1, vec.x * 0.1F, vec.y * 0.03F, vec.z * 0.1F);
            }
        }
    }

    default void spawnChargingParticle(int chargeTime, PlayerEntity player, World worldIn) {
        double t = chargeTime + player.ticksExisted;
        double y = Math.sin(t * 0.05D) * 0.5D;
        double y1 = player.getPosY() + 1.5D + y;
        double x = Math.sin(t * 0.2D) * (1D - Math.abs(y * 0.8F));
        double x1 = player.getPosX() + x;
        double x2 = (player.getMotion().x * 2F) + (x * 0.03F);
        double z = Math.cos(t * 0.2D) * (1D - Math.abs(y * 0.8F));
        double z1 = player.getPosZ() + z;
        double z2 = (player.getMotion().z * 2F) + (z * 0.03F);
        worldIn.addParticle(this.getChargingParticle(), x1, y1, z1, x2, 0D, z2);
    }

    void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player);

    default boolean hasMagicAmmo(PlayerEntity player, IBigBangWeapon weapon) {
        ItemStack ammo = findMagicAmmo(player, weapon);
        return ammo != null && !ammo.isEmpty();
    }

    default ItemStack findMagicAmmo(PlayerEntity player, IBigBangWeapon weapon) {
        Predicate<ItemStack> predicate = getMagicAmmoPredicate();
        ItemStack itemstack = getHeldMagicAmmo(player, predicate);
        if (!itemstack.isEmpty()) {
            return itemstack;
        } else {
            for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack1 = player.inventory.getStackInSlot(i);
                if (predicate.test(itemstack1)) {
                    return itemstack1;
                }
            }
            return player.abilities.isCreativeMode ? new ItemStack(getMagicAmmoItem(weapon)) : ItemStack.EMPTY;
        }
    }

    default ItemStack getHeldMagicAmmo(PlayerEntity player, Predicate<ItemStack> isAmmo) {
        if (isAmmo.test(player.getHeldItem(Hand.OFF_HAND))) {
            return player.getHeldItem(Hand.OFF_HAND);
        } else {
            return isAmmo.test(player.getHeldItem(Hand.MAIN_HAND)) ? player.getHeldItem(Hand.MAIN_HAND) : ItemStack.EMPTY;
        }
    }

    default Item getMagicAmmoItem(IBigBangWeapon weapon) {
        return weapon.getMagicAmmoPredicate().equals(MAGIC_ROCKS) ? ModItems.BLESSED_MAGIC_ROCK : ModItems.BLESSED_SUMMONING_ROCK;
    }

    default Item getDormantMagicAmmoItem(IBigBangWeapon weapon) {
        return weapon.getMagicAmmoPredicate().equals(MAGIC_ROCKS) ? ModItems.DORMANT_MAGIC_ROCK : ModItems.DORMANT_SUMMONING_ROCK;
    }

    default boolean tryConsumeMagicAmmo(PlayerEntity player) {
        ItemStack ammo = findMagicAmmo(player, this);
        if(ammo != null && !ammo.isEmpty()) {
            consumeMagicAmmo(player, ammo);
            return true;
        }
        return false;
    }

    default void consumeMagicAmmo(PlayerEntity playerEntity, ItemStack ammoStack) {
        if (playerEntity.isCreative())
            return;
        ammoStack.shrink(1);
        if (ammoStack.isEmpty())
            playerEntity.inventory.deleteStack(ammoStack);
        playerEntity.inventory.addItemStackToInventory(new ItemStack(getDormantMagicAmmoItem(this)));
    }

    Predicate<ItemStack> getMagicAmmoPredicate();

    int getChargeDuration(PlayerEntity player);

    @Nullable IParticleData getChargingParticle();

    @Nullable IParticleData getChargedParticle();

    SoundEvent getChargedSound();

    default Entity getBestTargetInCone(ItemStack stack, World worldIn, LivingEntity player, int spellRange, double angleThreshold) {
        return getBestTargetInCone(stack, worldIn, player, spellRange, angleThreshold, false);
    }

    default Entity getBestTargetInCone(ItemStack stack, World worldIn, LivingEntity player, int spellRange, double angleThreshold, boolean requireLineOfSight) {
        AxisAlignedBB AABB = new AxisAlignedBB(player.getPosition().add(-spellRange, -spellRange, -spellRange), player.getPosition().add(spellRange, spellRange, spellRange));
        List<Entity> targets = new ArrayList<>(worldIn.getEntitiesWithinAABB(MobEntity.class, AABB, (entityIn) -> true));
        @Nullable
        Entity bestTarget = null;
        double bestDis = spellRange;
        double bestAngle = angleThreshold;
        for (Entity t : targets) {
            double tDis = player.getPositionVec().distanceTo(t.getPositionVec());
            Vec3d tDir = t.getPositionVec().subtract(player.getPositionVec()).normalize();
            double tAngle = Math.acos(player.getLookVec().dotProduct(tDir)) / Math.PI * 180;
            boolean lineOfSight = !requireLineOfSight || player.canEntityBeSeen(t);
            if (tAngle < bestDis && tDis < bestAngle && lineOfSight) {
                bestTarget = t;
                bestDis = tDis;
                bestAngle = tAngle;
            }
        }
        return bestTarget;
    }

    default List<Entity> getTargetsInCone(ItemStack stack, World worldIn, LivingEntity player, int spellRange, double angleThreshold, int maxTargetCount) {
        return getTargetsInCone(stack, worldIn, player, spellRange, angleThreshold, maxTargetCount, false);
    }

    default List<Entity> getTargetsInCone(ItemStack stack, World worldIn, LivingEntity player, int spellRange, double angleThreshold, int maxTargetCount, boolean requireLineOfSight) {
        AxisAlignedBB AABB = new AxisAlignedBB(player.getPosition().add(-spellRange, -spellRange, -spellRange), player.getPosition().add(spellRange, spellRange, spellRange));
        List<Entity> targets = new ArrayList<>(worldIn.getEntitiesWithinAABB(MobEntity.class, AABB, (entityIn) -> true));
        List<Entity> validTargets = new ArrayList<>();
        int validatedCount = 0;
        for (Entity t : targets) {
            if (validatedCount > maxTargetCount)
                break;
            double tDis = player.getPositionVec().distanceTo(t.getPositionVec());
            Vec3d tDir = t.getPositionVec().subtract(player.getPositionVec()).normalize();
            double tAngle = Math.acos(player.getLookVec().dotProduct(tDir)) / Math.PI * 180;
            if (tAngle < angleThreshold && tDis < spellRange) {
                validTargets.add(t);
                ++validatedCount;
                worldIn.addParticle(ModParticleTypes.HOLY_HEXAGRAM.get(), t.getPosX(), t.getPosY() + 0.01D, t.getPosZ(), 0, 0, 0);
            }
        }
        return validTargets;
    }

    default int getEffectMultiplier(LivingEntity entity, Effect effectType) {
        EffectInstance effect = entity.getActivePotionEffect(effectType);
        return effect == null ? 0 : (1 + effect.getAmplifier());
    }

    default boolean magicAttackEntityAsMob(LivingEntity attacker, LivingEntity target, int damageIn) {
        if(attacker == null || target == null)
            return false;
        float damage = (float)damageIn;
        float knockback = 0;
        damage += EnchantmentHelper.getModifierForCreature(attacker.getHeldItemMainhand(), attacker.getCreatureAttribute());
        knockback += (float)EnchantmentHelper.getKnockbackModifier(attacker);
        int i = EnchantmentHelper.getFireAspectModifier(attacker);
        if (i > 0)
            target.setFire(i * 4);
        if(!target.attackEntityFrom(DamageSource.causeMobDamage(attacker).setMagicDamage(), damage))
            return false;
        if (knockback > 0.0F) {
            target.knockBack(attacker, knockback * 0.5F,
                    MathHelper.sin(attacker.rotationYaw * ((float)Math.PI / 180F)),
                    -MathHelper.cos(attacker.rotationYaw * ((float)Math.PI / 180F)));
            attacker.setMotion(attacker.getMotion().mul(0.6D, 1.0D, 0.6D));
        }
        if (target instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)target;
            ItemStack itemstack = attacker.getHeldItemMainhand();
            ItemStack itemstack1 = playerentity.isHandActive() ? playerentity.getActiveItemStack() : ItemStack.EMPTY;
            if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.canDisableShield(itemstack1, playerentity, attacker) && itemstack1.isShield(playerentity)) {
                float f2 = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(attacker) * 0.05F;
                if (target.world.rand.nextFloat() < f2) {
                    playerentity.getCooldownTracker().setCooldown(itemstack.getItem(), 100);
                    attacker.world.setEntityState(playerentity, (byte)30);
                }
            }
        }
        EnchantmentHelper.applyThornEnchantments(target, attacker);
        EnchantmentHelper.applyArthropodEnchantments(attacker, target);
        return true;
    }
}
