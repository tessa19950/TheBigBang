package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.entities.SnipingArrowEntity;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class JaegerItem extends BigBangCrossbowItem {

	public JaegerItem(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}

	@Override
	AbstractArrowEntity getUniqueArrow(World worldIn, LivingEntity shooter) {
		return new SnipingArrowEntity(worldIn, shooter);
	}

	@Override
	public void fireProjectiles(World worldIn, PlayerEntity shooter, Hand handIn, ItemStack stack, float velocityIn, float inaccuracyIn) {
		if(hasMagicAmmo(shooter, this)) {
			float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
			worldIn.playSound(null, shooter.getPosition(), ModSounds.SNIPING_USE, SoundCategory.PLAYERS, 1, pitch);
		}
		super.fireProjectiles(worldIn, shooter, handIn, stack, velocityIn, inaccuracyIn);
	}
}
