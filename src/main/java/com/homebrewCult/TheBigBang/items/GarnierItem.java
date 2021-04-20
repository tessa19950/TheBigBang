package com.homebrewCult.TheBigBang.items;

import java.util.function.Predicate;
import com.homebrewCult.TheBigBang.entities.ThrowingStarEntity;
import com.homebrewCult.TheBigBang.init.ModItems;
import com.homebrewCult.TheBigBang.init.ModSounds;
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

	public static final Predicate<ItemStack> THROWING_STARS = (itemstack) -> {
		return itemstack.getItem() == ModItems.SUBI || itemstack.getItem() == ModItems.TOBI || itemstack.getItem() == ModItems.STEELY || itemstack.getItem() == ModItems.ILBI;
   	};
	
	public GarnierItem(IItemTier tierIn, Item.Properties builder) {
		super(builder);
		this.tier = tierIn;
	}
	
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity)entityLiving;
			boolean isInfinite = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack ammostack = playerentity.findAmmo(stack);
			if(ammostack.getItem() == Items.ARROW)
				ammostack = new ItemStack(ModItems.STEELY);

			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !ammostack.isEmpty() || isInfinite);
			if (i < 0) return;

            float f = getCharge(i);
			if (!ammostack.isEmpty() || f > 0.1f) {
				if(!worldIn.isRemote) {
					ThrowingStarItem throwingStarItem = (ThrowingStarItem) ammostack.getItem();
					ThrowingStarEntity throwingStarEntity = throwingStarItem.createThrowingStar(worldIn, ammostack, playerentity);
					throwingStarEntity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * getMaxVelocity(), 1);
			 
					int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
					throwingStarEntity.setDamage(throwingStarEntity.getDamage() + (double)j * 0.5D);
	
					if (f == 1f) {
						throwingStarEntity.setIsCritical(true);
					}
					
					int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
					if (k > 0) {
						throwingStarEntity.setKnockbackStrength(k);
					}
	
					if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
						throwingStarEntity.setFire(100);
					}
	
					stack.damageItem(1, playerentity, (p_220009_1_) -> {
						p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
					});
	          
					if (isInfinite) {
						throwingStarEntity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
					}

					float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
					worldIn.playSound(null, entityLiving.getPosition(), getThrowSound(), SoundCategory.PLAYERS, 1, pitch);
					worldIn.addEntity(throwingStarEntity);
				}
				worldIn.playSound((PlayerEntity)null, playerentity.posX, playerentity.posY, playerentity.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + 1 * 0.5F);
				if (!isInfinite) {
					ammostack.shrink(1);
					if (ammostack.isEmpty()) {
						playerentity.inventory.deleteStack(ammostack);
					}
				}
				playerentity.addStat(Stats.ITEM_USED.get(this));
			}
		}
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = !playerIn.findAmmo(itemstack).isEmpty();
		
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
		if (ret != null) return ret;
		
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
}
