package com.homebrewCult.TheBigBang.items.weapons;

import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.ImmutableList;
import com.homebrewCult.TheBigBang.entities.ThrowingStarEntity;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.init.ModSounds;
import com.homebrewCult.TheBigBang.items.ThrowingStarItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class GarnierItem extends ShootableItem {

	private final IItemTier tier;

	public static final Predicate<ItemStack> THROWING_STARS = (itemstack) ->
			itemstack.getItem() == ModItems.SUBI || itemstack.getItem() == ModItems.TOBI || itemstack.getItem() == ModItems.STEELY || itemstack.getItem() == ModItems.ILBI;
	
	public GarnierItem(IItemTier tierIn, Item.Properties builder) {
		super(builder);
		this.tier = tierIn;
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)entityLiving;
			boolean isInfinite = player.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack ammo = player.findAmmo(stack);
			if(ammo.getItem() == Items.ARROW)
				ammo = new ItemStack(ModItems.STEELY);

			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, player, i, !ammo.isEmpty() || isInfinite);
			if (i < 0)
				return;

            float charge = getCharge(i);
			if (!ammo.isEmpty() || charge > 0.1f) {
				if(!worldIn.isRemote) {
					ThrowingStarEntity star = ((ThrowingStarItem)ammo.getItem()).createThrowingStar(worldIn, ammo, player);
					star.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, charge * getMaxVelocity(), 1);
					int enchantment = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
					star.setDamage(star.getDamage() + (double)enchantment * 0.5D);
					if (charge == 1f)
						star.setIsCritical(true);
					int knockback = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
					if (knockback > 0)
						star.setKnockbackStrength(knockback);
					int piercing = EnchantmentHelper.getEnchantmentLevel(Enchantments.PIERCING, stack);
					if (piercing > 0)
						star.setPierceLevel((byte)piercing);
					if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
						star.setFire(100);
					stack.damageItem(1, player, (p) -> p.sendBreakAnimation(player.getActiveHand()));
					if (isInfinite)
						star.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
					float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
					worldIn.playSound(null, entityLiving.getPosition(), getThrowSound(), SoundCategory.PLAYERS, 1, pitch);
					worldIn.addEntity(star);
				}
				worldIn.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 1 * 0.5F);
				if (!isInfinite) {
					ammo.shrink(1);
					if (ammo.isEmpty())
						player.inventory.deleteStack(ammo);
				}
				player.addStat(Stats.ITEM_USED.get(this));
			}
		}
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = !playerIn.findAmmo(itemstack).isEmpty();
		ActionResult<ItemStack> result = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
		if (result != null)
			return result;
		if (!playerIn.abilities.isCreativeMode && !flag) {
			return flag ? new ActionResult<>(ActionResultType.PASS, itemstack) : new ActionResult<>(ActionResultType.FAIL, itemstack);
		} else {
			playerIn.setActiveHand(handIn);
			return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
		}
	}
	
	public float getMaxVelocity() {
		return 2f;
	}
	
	public static float getCharge(int charge) {
		float f = (float)charge / 20.0F;
		f = f * 2f;
		if (f > 1f) {
			f = 1f;
		}
		return f;
	}

	public SoundEvent getThrowSound() {
		return ModSounds.CLAW_USE;
	}

	@Override
	public Predicate<ItemStack> getInventoryAmmoPredicate() {
		return THROWING_STARS;
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.SPEAR;
	}
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return this.tier.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
	}

	@Override
	public int getItemEnchantability() {
		return this.tier.getEnchantability();
	}

	private static final List<Enchantment> VALID_ENCHANTMENTS = ImmutableList.of(
				Enchantments.POWER, Enchantments.FLAME, Enchantments.PUNCH, Enchantments.PIERCING
			);

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(VALID_ENCHANTMENTS.contains(enchantment))
			return true;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
}
