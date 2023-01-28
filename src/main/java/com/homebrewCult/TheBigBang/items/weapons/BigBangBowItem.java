package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.particles.IParticleData;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.function.Predicate;

public abstract class BigBangBowItem extends BowItem implements IBigBangWeapon {

    private final IItemTier tier;

    public BigBangBowItem(IItemTier tierIn, Item.Properties builder) {
        super(builder);
        this.tier = tierIn;
    }

    public void shootArrow(ItemStack bowStack, World worldIn, LivingEntity entityLiving, int charge, boolean freeArrow) {
        PlayerEntity player = (PlayerEntity)entityLiving;
        ItemStack ammo = player.findAmmo(bowStack);
        if (ammo.isEmpty())
            ammo = new ItemStack(Items.ARROW);

        float velocity = getArrowVelocity(charge);
        if (!((double)velocity < 0.1D)) {
            boolean flag1 = player.abilities.isCreativeMode || (ammo.getItem() instanceof ArrowItem &&
                    ((ArrowItem)ammo.getItem()).isInfinite(ammo, bowStack, player) || freeArrow);
            if (!worldIn.isRemote) {
                ArrowItem arrowItem = (ArrowItem)(ammo.getItem() instanceof ArrowItem ? ammo.getItem() : Items.ARROW);
                AbstractArrowEntity arrow = arrowItem.createArrow(worldIn, ammo, player);
                arrow = customizedArrow(arrow, freeArrow ? new ItemStack(ModItems.BLESSED_MAGIC_ROCK) : ammo);
                arrow.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
                if (velocity == 1.0F)
                    arrow.setIsCritical(true);
                applyEnchantments(bowStack, arrow);
                bowStack.damageItem(1, player, (p) -> p.sendBreakAnimation(player.getActiveHand()));
                if (flag1 || player.abilities.isCreativeMode && (ammo.getItem() == Items.SPECTRAL_ARROW || ammo.getItem() == Items.TIPPED_ARROW))
                    arrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                worldIn.addEntity(arrow);
            }

            worldIn.playSound(null, player.posX, player.posY, player.posZ, getShootSound(), SoundCategory.PLAYERS, 1.0F, getShootPitch(velocity));
            if (!flag1 && !player.abilities.isCreativeMode && !freeArrow) {
                ammo.shrink(1);
                if (ammo.isEmpty())
                    player.inventory.deleteStack(ammo);
            }
            player.addStat(Stats.ITEM_USED.get(this));
        }
    }

    protected AbstractArrowEntity customizedArrow(AbstractArrowEntity arrow, ItemStack item) {
        return arrow;
    }

    static void applyEnchantments(ItemStack bowStack, AbstractArrowEntity arrow) {
        int power = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bowStack);
        if (power > 0)
            arrow.setDamage(arrow.getDamage() + (double)power * 0.5D + 0.5D);
        int punch = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bowStack);
        if (punch > 0)
            arrow.setKnockbackStrength(punch);
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bowStack) > 0)
            arrow.setFire(100);
    }

    protected SoundEvent getShootSound() {
        return SoundEvents.ENTITY_ARROW_SHOOT;
    }

    protected float getShootPitch(float velocity) {
        return 1.0F / (random.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return super.isEnchantable(stack);
    }

    @Override
    public int getItemEnchantability() {
        return this.tier.getEnchantability();
    }

    @Override
    public Predicate<ItemStack> getMagicAmmoPredicate() {
        return MAGIC_ROCKS;
    }

    @Override
    public void onSpellAttack(ItemStack stack, World worldIn, PlayerEntity player) { }

    @Override
    public int getChargeDuration(PlayerEntity player) { return 0; }

    @Override
    public IParticleData getChargingParticle() { return null; }

    @Override
    public IParticleData getChargedParticle() { return null; }

    @Override
    public SoundEvent getChargedSound() { return null; }
}
