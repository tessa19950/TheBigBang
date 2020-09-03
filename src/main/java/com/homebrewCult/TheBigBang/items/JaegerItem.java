package com.homebrewCult.TheBigBang.items;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import com.google.common.collect.Lists;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ICrossbowUser;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.world.World;
import net.minecraft.stats.Stats;
import net.minecraft.entity.item.FireworkRocketEntity;

public class JaegerItem extends CrossbowItem
{
	public JaegerItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
	    if (isCharged(itemstack)) {
	    	fireProjectiles(worldIn, playerIn, handIn, itemstack, getVelocity(itemstack), 1.0F);
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
	
	private static float getVelocity(ItemStack bowstack) {
		return bowstack.getItem() == Items.CROSSBOW && hasChargedProjectile(bowstack, Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
	}
	
	private static boolean hasChargedProjectile(ItemStack stack, Item ammoItem) {
		return getChargedProjectiles(stack).stream().anyMatch((ammostack) -> {
			return ammostack.getItem() == ammoItem;
		});
	}
	   
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		int i = this.getUseDuration(stack) - timeLeft;
	    float f = getCharge(i, stack);
	    
	    //Had to reduce the test down from >= 1.0F because it was never reached
	    if (f >= 0.6F && !isCharged(stack) && hasAmmo(entityLiving, stack)) {
	         setCharged(stack, true);
	         SoundCategory soundcategory = entityLiving instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
	         worldIn.playSound((PlayerEntity)null, entityLiving.posX, entityLiving.posY, entityLiving.posZ, SoundEvents.ITEM_CROSSBOW_LOADING_END, soundcategory, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + 0.2F);
	    }
	}

   public static void fireProjectiles(World worldIn, LivingEntity shooter, Hand handIn, ItemStack stack, float velocityIn, float inaccuracyIn) {
	   List<ItemStack> list = getChargedProjectiles(stack);
	   float[] afloat = getRandomSoundPitches(shooter.getRNG());

	   for(int i = 0; i < list.size(); ++i) {
		   ItemStack itemstack = list.get(i);
		   boolean flag = shooter instanceof PlayerEntity && ((PlayerEntity)shooter).abilities.isCreativeMode;
		   if (!itemstack.isEmpty()) {
			   if (i == 0) {
				   fireProjectile(worldIn, shooter, handIn, stack, itemstack, afloat[i], flag, velocityIn, inaccuracyIn, 0.0F);
			   } else if (i == 1) {
				   fireProjectile(worldIn, shooter, handIn, stack, itemstack, afloat[i], flag, velocityIn, inaccuracyIn, -10.0F);
			   } else if (i == 2) {
				   fireProjectile(worldIn, shooter, handIn, stack, itemstack, afloat[i], flag, velocityIn, inaccuracyIn, 10.0F);
			   }
		   }
	   }
	   fireProjectilesAfter(worldIn, shooter, stack);
   }
   
   private static void fireProjectile(World worldIn, LivingEntity shooter, Hand handIn, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean isCreativeMode, float velocity, float inaccuracy, float projectileAngle) {
	  if (!worldIn.isRemote) {
	     boolean flag = projectile.getItem() == Items.FIREWORK_ROCKET;
	     IProjectile iprojectile;
	     if (flag) {
	        iprojectile = new FireworkRocketEntity(worldIn, projectile, shooter.posX, shooter.posY + (double)shooter.getEyeHeight() - (double)0.15F, shooter.posZ, true);
	     } else {
	        iprojectile = createArrow(worldIn, shooter, crossbow, projectile);
	        if (isCreativeMode || projectileAngle != 0.0F) {
	           ((AbstractArrowEntity)iprojectile).pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
	        }
	     }
	
	     if (shooter instanceof ICrossbowUser) {
	        ICrossbowUser icrossbowuser = (ICrossbowUser)shooter;
	        icrossbowuser.shoot(icrossbowuser.getAttackTarget(), crossbow, iprojectile, projectileAngle);
	     } else {
	        Vec3d vec3d1 = shooter.func_213286_i(1.0F);
	        Quaternion quaternion = new Quaternion(new Vector3f(vec3d1), projectileAngle, true);
	        Vec3d vec3d = shooter.getLook(1.0F);
	        Vector3f vector3f = new Vector3f(vec3d);
	        vector3f.func_214905_a(quaternion);
	        iprojectile.shoot((double)vector3f.getX(), (double)vector3f.getY(), (double)vector3f.getZ(), velocity, inaccuracy);
	     }
	
	     crossbow.damageItem(flag ? 3 : 1, shooter, (p_220017_1_) -> {
	        p_220017_1_.sendBreakAnimation(handIn);
	     });
	     worldIn.addEntity((Entity)iprojectile);
	     worldIn.playSound((PlayerEntity)null, shooter.posX, shooter.posY, shooter.posZ, SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, soundPitch);
	  }
   }
	   
   private static AbstractArrowEntity createArrow(World worldIn, LivingEntity shooter, ItemStack crossbow, ItemStack ammo) {
	   ArrowItem arrowitem = (ArrowItem)(ammo.getItem() instanceof ArrowItem ? ammo.getItem() : Items.ARROW);
	   AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, ammo, shooter);

	   //Better than you average Crossbow
	   abstractarrowentity.setIsCritical(true);
	   abstractarrowentity.setHitSound(SoundEvents.ITEM_CROSSBOW_HIT);
	   return abstractarrowentity;
   }
	 
   private static void fireProjectilesAfter(World worldIn, LivingEntity shooter, ItemStack stack) {
      if (shooter instanceof ServerPlayerEntity) {
         ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)shooter;
         if (!worldIn.isRemote) {
            CriteriaTriggers.SHOT_CROSSBOW.func_215111_a(serverplayerentity, stack);
         }
         serverplayerentity.addStat(Stats.ITEM_USED.get(stack.getItem()));
      }
      clearProjectiles(stack);
   }
	   
   private static void clearProjectiles(ItemStack stack) {
      CompoundNBT compoundnbt = stack.getTag();
      if (compoundnbt != null) {
         ListNBT listnbt = compoundnbt.getList("ChargedProjectiles", 9);
         listnbt.clear();
         compoundnbt.put("ChargedProjectiles", listnbt);
      }
   }
	   
   private static float[] getRandomSoundPitches(Random rand) {
      boolean flag = rand.nextBoolean();
      return new float[]{1.0F, getRandomSoundPitch(flag), getRandomSoundPitch(!flag)};
   }

   private static float getRandomSoundPitch(boolean flagIn) {
      float f = flagIn ? 0.63F : 0.43F;
      return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
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
	   
   public static void setCharged(ItemStack stack, boolean chargedIn) {
	  CompoundNBT compoundnbt = stack.getOrCreateTag();
	  compoundnbt.putBoolean("Charged", chargedIn);
   }
	   
   public static boolean isCharged(ItemStack stack) {
      CompoundNBT compoundnbt = stack.getTag();
      return compoundnbt != null && compoundnbt.getBoolean("Charged");
   }
	  
   public int getUseDuration(ItemStack stack) {
      return getChargeTime(stack) + 3;
   }
	   

   public static int getChargeTime(ItemStack stack) {
      int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
      return i == 0 ? 25 : 25 - 5 * i;
   }
   
   private static float getCharge(int useTime, ItemStack stack) {
      float f = (float)useTime / (float)getChargeTime(stack);
      if (f > 0.6F) {
         f = 0.6F;
      }
      return f;
   }
	   
   private static boolean hasAmmo(LivingEntity entityIn, ItemStack stack) {
	   int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.MULTISHOT, stack);
	   int j = i == 0 ? 1 : 3;
	   
	   boolean flag = entityIn instanceof PlayerEntity && ((PlayerEntity)entityIn).abilities.isCreativeMode;
	   ItemStack itemstack = entityIn.findAmmo(stack);
	   ItemStack itemstack1 = itemstack.copy();
	   for(int k = 0; k < j; ++k) {
		   if (k > 0) {
			   itemstack = itemstack1.copy();
		   }
		   if (itemstack.isEmpty() && flag) {
			   itemstack = new ItemStack(Items.ARROW);
			   itemstack1 = itemstack.copy();
		   }
		   if (!func_220023_a(entityIn, stack, itemstack, k > 0, flag)) {
			   return false;
		   }
	   }
	   return true;
   }
	      
   private static boolean func_220023_a(LivingEntity entityIn, ItemStack crossbow, ItemStack projectile, boolean p_220023_3_, boolean p_220023_4_) {
	   if (projectile.isEmpty()) {
		   return false;
	   } else {
		   boolean flag = p_220023_4_ && projectile.getItem() instanceof ArrowItem;
		   ItemStack itemstack;
		   
		   if (!flag && !p_220023_4_ && !p_220023_3_) {
			   itemstack = projectile.split(1);
			   
			   if (projectile.isEmpty() && entityIn instanceof PlayerEntity) {
				   ((PlayerEntity)entityIn).inventory.deleteStack(projectile);
			   }
		   } else {
			   itemstack = projectile.copy();
		   }
		   addChargedProjectile(crossbow, itemstack);
		   return true;
	   }
   }
	   
   private static void addChargedProjectile(ItemStack crossbow, ItemStack projectile) {
	   CompoundNBT compoundnbt = crossbow.getOrCreateTag();
	   ListNBT listnbt;
	   if (compoundnbt.contains("ChargedProjectiles", 9)) {
		   	listnbt = compoundnbt.getList("ChargedProjectiles", 10);
       } else {
    	   	listnbt = new ListNBT();
	   }
	   CompoundNBT compoundnbt1 = new CompoundNBT();
	   projectile.write(compoundnbt1);
	   listnbt.add(compoundnbt1);
	   compoundnbt.put("ChargedProjectiles", listnbt);
   }
   
   @Override
   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
	   return true;
   }
}
