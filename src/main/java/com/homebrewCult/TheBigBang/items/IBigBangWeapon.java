package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public interface IBigBangWeapon {

    Predicate<ItemStack> MAGIC_ROCKS = (stack) -> {
        return stack.getItem().equals(ModItems.BLESSED_MAGIC_ROCK);
    };

    Predicate<ItemStack> SUMMONING_ROCKS = (stack) -> {
        return stack.getItem().equals(ModItems.BLESSED_SUMMONING_ROCK);
    };

    default ActionResult<ItemStack> trySpellAttack(ItemStack weaponStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        PlayerEntity playerEntity = entityLiving instanceof PlayerEntity ? (PlayerEntity) entityLiving : null;
        boolean isCharged = weaponStack.getUseDuration() - timeLeft > getChargeDuration();
        if(playerEntity != null && isCharged) {
            boolean isCreative = playerEntity.isCreative();
            ItemStack ammoStack = findMagicAmmo(playerEntity, this);
            ActionResultType result = isCreative || !ammoStack.isEmpty() ? ActionResultType.SUCCESS : ActionResultType.FAIL;
            if(result.equals(ActionResultType.SUCCESS)) {
                onSpellAttack(weaponStack, worldIn, playerEntity);
                weaponStack.damageItem(1, playerEntity, (p_220009_1_) -> {
                    p_220009_1_.sendBreakAnimation(playerEntity.getActiveHand());
                });
                if (!isCreative) {
                    ammoStack.shrink(1);
                    if (ammoStack.isEmpty()) {
                        playerEntity.inventory.deleteStack(ammoStack);
                    }
                    playerEntity.inventory.addItemStackToInventory(new ItemStack(getDormantMagicAmmoItem(this)));
                }
            }
            return  ActionResult.newResult(result, weaponStack);
        } else {
            return ActionResult.newResult(ActionResultType.FAIL, weaponStack);
        }
    }

    default void onSpellCharging(ItemStack weaponStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        int chargeTime = weaponStack.getUseDuration() - timeLeft;
        if(chargeTime == getChargeDuration()) {
            float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
            worldIn.playSound((PlayerEntity) entityLiving, entityLiving.getPosition(), getChargedSound(), SoundCategory.PLAYERS, 0.5F, pitch);
            for(int i = 0; i < 16; ++i) {
                for(int j = 0; j < 8; ++j) {
                    double x = Math.cos((float) Math.PI * 2F / 16F * (float) i) * 1.2F;
                    double y = Math.sin((float) Math.PI * 2F / 16F * (float) i) * 1.2F;
                    Vec3d vec = new Vec3d(x, y, 0);
                    vec = vec.rotateYaw((float)Math.PI / 8F * j);
                    double x1 = entityLiving.posX + vec.x;
                    double y1 = entityLiving.posY + 1 + vec.y;
                    double z1 = entityLiving.posZ + vec.z;
                    worldIn.addParticle(this.getChargedParticle(), x1, y1, z1, vec.x * 0.1F, vec.y * 0.03F, vec.z * 0.1F);
                }
            }
        } else {
            double t = chargeTime + entityLiving.ticksExisted;
            double y = Math.sin(t * 0.05D) * 0.5D;
            double y1 = entityLiving.posY + 1.5D + y;
            double x = Math.sin(t * 0.2D) * (1D - Math.abs(y * 0.8F));
            double x1 = entityLiving.posX + x;
            double x2 = (entityLiving.getMotion().x * 2F) + (x * 0.03F);
            double z = Math.cos(t * 0.2D) * (1D - Math.abs(y * 0.8F));
            double z1 = entityLiving.posZ + z;
            double z2 = (entityLiving.getMotion().z * 2F) + (z * 0.03F);
            worldIn.addParticle(this.getChargingParticle(), x1, y1, z1, x2, 0D, z2);
        }
    }

    void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player);

    default ItemStack findMagicAmmo(PlayerEntity player, IBigBangWeapon weapon) {
        Predicate<ItemStack> predicate = weapon.getAmmoPredicate();
        ItemStack itemstack = weapon.getHeldMagicAmmo(player, predicate);
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

    default ItemStack getHeldMagicAmmo(LivingEntity player, Predicate<ItemStack> isAmmo) {
        if (isAmmo.test(player.getHeldItem(Hand.OFF_HAND))) {
            return player.getHeldItem(Hand.OFF_HAND);
        } else {
            return isAmmo.test(player.getHeldItem(Hand.MAIN_HAND)) ? player.getHeldItem(Hand.MAIN_HAND) : ItemStack.EMPTY;
        }
    }

    default Item getMagicAmmoItem(IBigBangWeapon weapon) {
        return weapon.getAmmoPredicate().equals(MAGIC_ROCKS) ? ModItems.BLESSED_MAGIC_ROCK : ModItems.BLESSED_SUMMONING_ROCK;
    }

    default Item getDormantMagicAmmoItem(IBigBangWeapon weapon) {
        return weapon.getAmmoPredicate().equals(MAGIC_ROCKS) ? ModItems.DORMANT_MAGIC_ROCK : ModItems.DORMANT_SUMMONING_ROCK;
    }

    Predicate<ItemStack> getAmmoPredicate();

    int getChargeDuration();

    IParticleData getChargingParticle();

    IParticleData getChargedParticle();

    SoundEvent getChargedSound();

    default Entity getBestTargetInCone(ItemStack stack, World worldIn, LivingEntity entityLiving, int spellRange, double angleThreshold) {
        AxisAlignedBB AABB = new AxisAlignedBB(entityLiving.getPosition().add(-spellRange, -spellRange, -spellRange), entityLiving.getPosition().add(spellRange, spellRange, spellRange));
        List<Entity> targets = new ArrayList<Entity>();
        targets.addAll(worldIn.getEntitiesWithinAABB(MobEntity.class, AABB, (entityIn) -> {
            return true;
        }));
        @Nullable
        Entity bestTarget = null;
        double bestDis = spellRange;
        double bestAngle = angleThreshold;
        for (Entity t : targets) {
            double tDis = entityLiving.getPositionVec().distanceTo(t.getPositionVec());
            Vec3d tDir = t.getPositionVec().subtract(entityLiving.getPositionVec()).normalize();
            double tAngle = Math.acos(entityLiving.getLookVec().dotProduct(tDir)) / Math.PI * 180;
            if (tAngle < bestDis && tDis < bestAngle) {
                bestTarget = t;
                bestDis = tDis;
                bestAngle = tAngle;
            }
        }
        return bestTarget;
    }

    default List<Entity> getTargetsInCone(ItemStack stack, World worldIn, LivingEntity entityLiving, int spellRange, double angleThreshold, int maxTargetCount) {
        AxisAlignedBB AABB = new AxisAlignedBB(entityLiving.getPosition().add(-spellRange, -spellRange, -spellRange), entityLiving.getPosition().add(spellRange, spellRange, spellRange));
        List<Entity> targets = new ArrayList<Entity>();
        targets.addAll(worldIn.getEntitiesWithinAABB(MobEntity.class, AABB, (entityIn) -> {
            return true;
        }));
        List<Entity> validTargets = new ArrayList<Entity>();
        int validatedCount = 0;
        for (Entity t : targets) {
            if (validatedCount > maxTargetCount) {
                break;
            }
            double tDis = entityLiving.getPositionVec().distanceTo(t.getPositionVec());
            Vec3d tDir = t.getPositionVec().subtract(entityLiving.getPositionVec()).normalize();
            double tAngle = Math.acos(entityLiving.getLookVec().dotProduct(tDir)) / Math.PI * 180;
            if (tAngle < angleThreshold && tDis < spellRange) {
                validTargets.add(t);
                ++validatedCount;
                worldIn.addParticle(ModParticleTypes.HOLY_HEXAGRAM.get(), t.posX, t.posY + 0.01D, t.posZ, 0, 0, 0);
            }
        }
        return validTargets;
    }
}
