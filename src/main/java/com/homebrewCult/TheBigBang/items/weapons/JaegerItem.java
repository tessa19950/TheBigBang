package com.homebrewCult.TheBigBang.items.weapons;

import com.homebrewCult.TheBigBang.entities.SnipingArrowEntity;
import com.homebrewCult.TheBigBang.init.ModParticleTypes;
import com.homebrewCult.TheBigBang.init.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class JaegerItem extends BigBangCrossbowItem {

	public JaegerItem(IItemTier tierIn, Item.Properties builder) {
		super(tierIn, builder);
	}

	@Override
	AbstractArrowEntity getUniqueArrow(World worldIn, LivingEntity shooter) {
		SnipingArrowEntity arrow = new SnipingArrowEntity(worldIn, shooter);
		arrow.setDamage(6.0D);
		return arrow;
	}

	@Override
	public void fireProjectiles(World worldIn, PlayerEntity user, Hand handIn, ItemStack stack, float velocityIn, float inaccuracyIn) {
		if(hasMagicAmmo(user, this)) {
			float pitch = 0.9F + worldIn.rand.nextFloat() * 0.2F;
			worldIn.playSound(null, user.getPosition(), ModSounds.SNIPING_USE, SoundCategory.PLAYERS, 1, pitch);

			for (int i = 0; i < 32; ++i) {
				double x = Math.cos((double) i / 32D * Math.PI * 2D);
				double y = Math.sin((double) i / 32D * Math.PI * 2D);
				double radius = 0.2D;
				Vec3d vec = new Vec3d(x, y, 0).mul(radius, radius, radius);
				vec = vec.rotatePitch(-user.getPitch(1) * (float) Math.PI / 180).rotateYaw(-user.getYaw(1) * (float) Math.PI / 180);

				double speed = 0.3D;
				double radialSpeed = 0.5D;
				Vec3d motion = user.getLookVec().mul(speed, speed, speed).add(vec.mul(radialSpeed, radialSpeed, radialSpeed));
				Vec3d pos = user.getEyePosition(0);
				user.world.addParticle(ModParticleTypes.SYMBOL_GOLD.get(),
						pos.x + vec.x, pos.y + vec.y, pos.z + vec.z,
						motion.x, motion.y, motion.z);
			}
		}
		super.fireProjectiles(worldIn, user, handIn, stack, velocityIn, inaccuracyIn);
	}
}
