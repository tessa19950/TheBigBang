package com.homebrewCult.TheBigBang.entities.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class ThrowingStarEntity extends AbstractArrowEntity implements IRendersAsItem {
	
	public ThrowingStarEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public ThrowingStarEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn, double x, double y, double z) {
		super(type, x, y, z, worldIn);
	}

	public ThrowingStarEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn, LivingEntity shooter) {
		super(type, shooter, worldIn);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(this.getThrowingStarAmmoItem());
	}
	
	@Override
	public ItemStack getItem() {
		return new ItemStack(this.getThrowingStarProjectileItem());
	}
	
	abstract Item getThrowingStarAmmoItem();
	
	abstract Item getThrowingStarProjectileItem();
	
	abstract EntityType<?> getThrowingStarEntityType();
}
