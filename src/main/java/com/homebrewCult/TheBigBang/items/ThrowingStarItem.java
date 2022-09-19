package com.homebrewCult.TheBigBang.items;

import com.homebrewCult.TheBigBang.entities.IlbiEntity;
import com.homebrewCult.TheBigBang.entities.SteelyEntity;
import com.homebrewCult.TheBigBang.entities.SubiEntity;
import com.homebrewCult.TheBigBang.entities.ThrowingStarEntity;
import com.homebrewCult.TheBigBang.entities.TobiEntity;
import com.homebrewCult.TheBigBang.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public abstract class ThrowingStarItem extends Item  {

	public ThrowingStarItem(Properties properties) {
		super(properties);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (!playerIn.abilities.isCreativeMode)
			itemstack.shrink(1);

		worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ,
				SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		if (!worldIn.isRemote) {
			ThrowingStarEntity throwingStar = null;
			Item type = itemstack.getItem();
			if(type == ModItems.ILBI) {
				throwingStar = new IlbiEntity(worldIn, playerIn);
			} else if (type == ModItems.STEELY) {
				throwingStar = new SteelyEntity(worldIn, playerIn);
			} else if (type == ModItems.TOBI) {
				throwingStar = new TobiEntity(worldIn, playerIn);
			} else {
				throwingStar = new SubiEntity(worldIn, playerIn);
			}
			throwingStar.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.addEntity(throwingStar);
		}

		playerIn.addStat(Stats.ITEM_USED.get(this));
		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	}
	
	public abstract ThrowingStarEntity createThrowingStar(World worldIn, ItemStack stack, LivingEntity shooter);
}
