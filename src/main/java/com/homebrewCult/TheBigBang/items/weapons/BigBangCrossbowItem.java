package com.homebrewCult.TheBigBang.items.weapons;

import com.google.common.collect.Lists;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ICrossbowUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

abstract class BigBangCrossbowItem extends CrossbowItem implements IBigBangWeapon {

    private final IItemTier tier;

    public BigBangCrossbowItem(IItemTier tierIn, Item.Properties builder) {
        super(builder);
        this.tier = tierIn;
    }

    @Override
    public boolean isCrossbow(ItemStack stack) {
        return true;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (isCharged(itemstack)) {
            fireProjectiles(worldIn, playerIn, handIn, itemstack, 3.15F, 1.0F);
            setCharged(itemstack, false);
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        } else if (!playerIn.findAmmo(itemstack).isEmpty()) {
            if (!isCharged(itemstack)) {
                playerIn.setActiveHand(handIn);
            }
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        } else {
            return new ActionResult<>(ActionResultType.FAIL, itemstack);
        }
    }

    public void fireProjectiles(World worldIn, PlayerEntity shooter, Hand handIn, ItemStack stack, float velocityIn, float inaccuracyIn) {
        List<ItemStack> list = getChargedProjectiles(stack);
        float[] afloat = getRandomPitches(shooter.getRNG());
        ItemStack magicAmmo = findMagicAmmo(shooter, this);
        boolean hasMagicAmmo = (magicAmmo != null && !magicAmmo.isEmpty());
        for(int i = 0; i < list.size(); ++i) {
            ItemStack itemstack = list.get(i);
            boolean creative = shooter.abilities.isCreativeMode;
            if (!itemstack.isEmpty()) {
                if (i == 0) {
                    fireProjectile(worldIn, shooter, handIn, stack, itemstack, afloat[i], creative, velocityIn, inaccuracyIn, 0.0F, hasMagicAmmo);
                } else if (i == 1) {
                    fireProjectile(worldIn, shooter, handIn, stack, itemstack, afloat[i], creative, velocityIn, inaccuracyIn, -10.0F, hasMagicAmmo);
                } else if (i == 2) {
                    fireProjectile(worldIn, shooter, handIn, stack, itemstack, afloat[i], creative, velocityIn, inaccuracyIn, 10.0F, hasMagicAmmo);
                }
            }
        }
        if(hasMagicAmmo)
            this.consumeMagicAmmo(shooter, magicAmmo);
        clearProjectiles(stack);
    }

    protected static List<ItemStack> getChargedProjectiles(ItemStack stack) {
        List<ItemStack> arrows = Lists.newArrayList();
        CompoundNBT nbt = stack.getTag();
        if (nbt != null && nbt.contains("ChargedProjectiles", 9)) {
            ListNBT projectileNbtList = nbt.getList("ChargedProjectiles", 10);
            for(int i = 0; i < projectileNbtList.size(); ++i)
                arrows.add(ItemStack.read(projectileNbtList.getCompound(i)));
        }
        return arrows;
    }

    private void fireProjectile(World worldIn, LivingEntity shooter, Hand hand, ItemStack stack, ItemStack ammoStack,
                                float soundPitch, boolean isCreative, float velocity, float inaccuracy, float angle, boolean hasMagicAmmo) {
        if (!worldIn.isRemote) {
            AbstractArrowEntity arrow = createArrow(worldIn, shooter, stack, ammoStack, hasMagicAmmo);
            if (isCreative || angle != 0.0F)
                arrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
            if (shooter instanceof ICrossbowUser) {
                ICrossbowUser user = (ICrossbowUser)shooter;
                user.shoot(user.getAttackTarget(), stack, arrow, angle);
            } else {
                Vec3d vec3d1 = shooter.getLook(1.0F);
                Quaternion quaternion = new Quaternion(new Vector3f(vec3d1), angle, true);
                Vec3d vec3d = shooter.getLook(1.0F);
                Vector3f vector3f = new Vector3f(vec3d);
                vector3f.transform(quaternion);
                arrow.shoot(vector3f.getX(), vector3f.getY(), vector3f.getZ(), velocity, inaccuracy);
            }
            stack.damageItem(1, shooter, (entity) -> entity.sendBreakAnimation(hand));
            worldIn.addEntity(arrow);
            worldIn.playSound(null, shooter.getPosX(), shooter.getPosY(), shooter.getPosZ(), SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, soundPitch);
        }
    }

    private AbstractArrowEntity createArrow(World worldIn, LivingEntity shooter, ItemStack crossbow, ItemStack ammo, boolean hasMagicAmmo) {
        AbstractArrowEntity arrow = hasMagicAmmo ? getUniqueArrow(worldIn, shooter) : ((ArrowItem)ammo.getItem()).createArrow(worldIn, crossbow, shooter);
        if (shooter instanceof PlayerEntity)
            arrow.setIsCritical(true);
        arrow.setHitSound(SoundEvents.ITEM_CROSSBOW_HIT);
        arrow.setIsCritical(true);
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.PIERCING, crossbow);
        if (i > 0)
            arrow.setPierceLevel((byte)i);
        BigBangBowItem.applyEnchantments(crossbow, arrow);
        return arrow;
    }

    abstract AbstractArrowEntity getUniqueArrow(World worldIn, LivingEntity shooter);

    protected static float[] getRandomPitches(Random random) {
        boolean flag = random.nextBoolean();
        return new float[]{1.0F, getRandomPitch(flag), getRandomPitch(!flag)};
    }

    protected static float getRandomPitch(boolean flag) {
        float f = flag ? 0.63F : 0.43F;
        return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
    }

    protected static void clearProjectiles(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getTag();
        if (compoundnbt != null) {
            ListNBT listnbt = compoundnbt.getList("ChargedProjectiles", 9);
            listnbt.clear();
            compoundnbt.put("ChargedProjectiles", listnbt);
        }
    }

    public Predicate<ItemStack> getAmmoPredicate() {
        return ARROWS;
    }

    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return ARROWS;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return true;
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
