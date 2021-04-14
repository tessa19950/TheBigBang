package com.homebrewCult.TheBigBang.items;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import com.google.common.collect.Lists;
import com.homebrewCult.TheBigBang.entities.SnipingArrowEntity;

import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ICrossbowUser;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class JaegerItem extends CrossbowItem {

	private final IItemTier tier;
	public JaegerItem(IItemTier tierIn, Item.Properties builder) {
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
	
	public static void fireProjectiles(World worldIn, LivingEntity shooter, Hand handIn, ItemStack stack, float velocityIn, float inaccuracyIn) {
		List<ItemStack> list = getChargedProjectiles(stack);
		float[] afloat = getRandomPitches(shooter.getRNG());
		for(int i = 0; i < list.size(); ++i) {
			ItemStack itemstack = list.get(i);
			boolean creative = shooter instanceof PlayerEntity && ((PlayerEntity)shooter).abilities.isCreativeMode;
			if (!itemstack.isEmpty()) {
				if (i == 0) {
					fireProjectile(worldIn, shooter, handIn, stack, itemstack, afloat[i], creative, velocityIn, inaccuracyIn, 0.0F);
				} else if (i == 1) {
					fireProjectile(worldIn, shooter, handIn, stack, itemstack, afloat[i], creative, velocityIn, inaccuracyIn, -10.0F);
				} else if (i == 2) {
					fireProjectile(worldIn, shooter, handIn, stack, itemstack, afloat[i], creative, velocityIn, inaccuracyIn, 10.0F);
				}
			}
		}
		clearProjectiles(stack);
	}
	
	private static void fireProjectile(World worldIn, LivingEntity shooter, Hand hand, ItemStack stack, ItemStack ammoStack, float soundPitch, boolean isCreative, float velocity, float inaccuracy, float angle) {
		if (!worldIn.isRemote) {
			IProjectile iprojectile = createArrow(worldIn, shooter, stack, ammoStack);
			if (isCreative || angle != 0.0F) {
				((AbstractArrowEntity)iprojectile).pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
			}
			if (shooter instanceof ICrossbowUser) {
				ICrossbowUser icrossbowuser = (ICrossbowUser)shooter;
	            icrossbowuser.shoot(icrossbowuser.getAttackTarget(), stack, iprojectile, angle);
			} else {
				Vec3d vec3d1 = shooter.func_213286_i(1.0F);
				Quaternion quaternion = new Quaternion(new Vector3f(vec3d1), angle, true);
	            Vec3d vec3d = shooter.getLook(1.0F);
	            Vector3f vector3f = new Vector3f(vec3d);
	            vector3f.func_214905_a(quaternion);
	            iprojectile.shoot((double)vector3f.getX(), (double)vector3f.getY(), (double)vector3f.getZ(), velocity, inaccuracy);
			}
			stack.damageItem(1, shooter, (entity) -> {
				entity.sendBreakAnimation(hand);
	        });
	        worldIn.addEntity((SnipingArrowEntity)iprojectile);
	        worldIn.playSound((PlayerEntity)null, shooter.posX, shooter.posY, shooter.posZ, SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, soundPitch);
		}
	}
	
	private static List<ItemStack> getChargedProjectiles(ItemStack stack) {
		List<ItemStack> list = Lists.newArrayList();
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("ChargedProjectiles", 9)) {
			ListNBT listnbt = compoundnbt.getList("ChargedProjectiles", 10);
			if (listnbt != null) {
				for(int i = 0; i < listnbt.size(); ++i) {
					CompoundNBT compoundnbt1 = listnbt.getCompound(i);
					list.add(ItemStack.read(compoundnbt1));
				}
			}
		}	
		return list;
	}
	
	private static AbstractArrowEntity createArrow(World worldIn, LivingEntity shooter, ItemStack crossbow, ItemStack ammo) {	
		SnipingArrowEntity abstractarrowentity = new SnipingArrowEntity(worldIn, shooter);
		if (shooter instanceof PlayerEntity) {
			abstractarrowentity.setIsCritical(true);
		}
		abstractarrowentity.setHitSound(SoundEvents.ITEM_CROSSBOW_HIT);
		abstractarrowentity.func_213865_o(true);
		
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.PIERCING, crossbow);
		if (i > 0) {
			abstractarrowentity.func_213872_b((byte)i);
		}
		
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, crossbow);
		if (j > 0) {
			abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double)j * 0.5D + 0.5D);
		}
		
		int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, crossbow);
		if (k > 0) {
			abstractarrowentity.setKnockbackStrength(k);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, crossbow) > 0) {
			abstractarrowentity.setFire(100);
		}
		
		return abstractarrowentity;
	}	
	
	private static float[] getRandomPitches(Random random) {
		boolean flag = random.nextBoolean();
		return new float[]{1.0F, getRandomPitch(flag), getRandomPitch(!flag)};
	}
	
	private static float getRandomPitch(boolean flag) {
		float f = flag ? 0.63F : 0.43F;
		return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
	}
	
	private static void clearProjectiles(ItemStack stack) {
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
		return this.tier.getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
	}

	@Override
	public int getItemEnchantability() {
		return this.tier.getEnchantability();
	}
}
