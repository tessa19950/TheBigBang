package com.homebrewCult.TheBigBang.entities;

import java.util.Collection;

import com.homebrewCult.TheBigBang.TheBigBang;
import com.homebrewCult.TheBigBang.init.ModEntities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BombArrowEntity extends AbstractArrowEntity {

	public BombArrowEntity(EntityType<?> type, World worldIn) {
		super(ModEntities.BOMB_ARROW, worldIn);
	}
	
	public BombArrowEntity(World worldIn) {
		super(ModEntities.BOMB_ARROW, worldIn);
	}
	
	public BombArrowEntity(World worldIn, double x, double y, double z) {
		super(ModEntities.BOMB_ARROW, x, y, z, worldIn);
	}

	public BombArrowEntity(World worldIn, LivingEntity shooter) {
		super(ModEntities.BOMB_ARROW, shooter, worldIn);
	}
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		super.onHit(raytraceResultIn);	
		RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
		boolean flag = raytraceresult$type == RayTraceResult.Type.ENTITY || raytraceresult$type == RayTraceResult.Type.BLOCK;
		if (flag && !this.world.isRemote) {	
			Vec3d hitvec = this.getPositionVec();
			Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
			this.world.createExplosion(this, hitvec.x, hitvec.y, hitvec.z, 2f, explosion$mode);	
			this.remove();
		}
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(Items.ARROW);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
